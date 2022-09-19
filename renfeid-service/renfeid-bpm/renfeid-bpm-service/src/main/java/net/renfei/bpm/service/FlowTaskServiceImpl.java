/*
 *   Copyright 2022 RenFei(i@renfei.net)
 *
 * Licensed under the Apache License, Version 2.0 (the "License");
 * you may not use this file except in compliance with the License.
 * You may obtain a copy of the License at
 *
 *   http://www.apache.org/licenses/LICENSE-2.0
 *
 * Unless required by applicable law or agreed to in writing, software
 * distributed under the License is distributed on an "AS IS" BASIS,
 * WITHOUT WARRANTIES OR CONDITIONS OF ANY KIND, either express or implied.
 * See the License for the specific language governing permissions and
 * limitations under the License.
 */
package net.renfei.bpm.service;

import com.google.common.collect.Lists;
import net.renfei.bpm.api.FlowTaskService;
import net.renfei.bpm.api.entity.*;
import net.renfei.bpm.constant.ProcessConstants;
import net.renfei.bpm.flow.CustomProcessDiagramGenerator;
import net.renfei.bpm.utils.FindNextNodeUtil;
import net.renfei.bpm.utils.FlowableUtils;
import net.renfei.common.api.entity.ListData;
import net.renfei.common.api.exception.BusinessException;
import net.renfei.common.core.service.SystemService;
import net.renfei.uaa.api.RoleService;
import net.renfei.uaa.api.UserService;
import net.renfei.uaa.api.entity.RoleDetail;
import net.renfei.uaa.api.entity.UserDetail;
import org.apache.commons.lang3.StringUtils;
import org.apache.zookeeper.util.SecurityUtils;
import org.flowable.bpmn.model.*;
import org.flowable.bpmn.model.Process;
import org.flowable.common.engine.api.FlowableException;
import org.flowable.common.engine.api.FlowableObjectNotFoundException;
import org.flowable.common.engine.impl.identity.Authentication;
import org.flowable.engine.ProcessEngineConfiguration;
import org.flowable.engine.history.HistoricActivityInstance;
import org.flowable.engine.history.HistoricProcessInstance;
import org.flowable.engine.history.HistoricProcessInstanceQuery;
import org.flowable.engine.repository.ProcessDefinition;
import org.flowable.engine.runtime.Execution;
import org.flowable.engine.runtime.ProcessInstance;
import org.flowable.engine.task.Comment;
import org.flowable.identitylink.api.history.HistoricIdentityLink;
import org.flowable.image.ProcessDiagramGenerator;
import org.flowable.task.api.DelegationState;
import org.flowable.task.api.Task;
import org.flowable.task.api.TaskQuery;
import org.flowable.task.api.history.HistoricTaskInstance;
import org.flowable.task.api.history.HistoricTaskInstanceQuery;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.util.ObjectUtils;

import java.io.InputStream;
import java.util.*;

/**
 * 流程任务服务
 *
 * @author renfei
 */
@Service
public class FlowTaskServiceImpl extends AbstractFlowableService implements FlowTaskService {
    private final SystemService systemService;
    private final UserService userService;
    private final RoleService roleService;

    public FlowTaskServiceImpl(SystemService systemService,
                               UserService userService,
                               RoleService roleService) {
        this.systemService = systemService;
        this.userService = userService;
        this.roleService = roleService;
    }

    /**
     * 审批任务
     *
     * @param flowTaskAo 请求实体参数
     */
    @Override
    public void complete(FlowTaskAO flowTaskAo) {
        Task task = taskService.createTaskQuery().taskId(flowTaskAo.getTaskId()).singleResult();
        if (Objects.isNull(task)) {
            throw new BusinessException("任务不存在");
        }
        if (DelegationState.PENDING.equals(task.getDelegationState())) {
            taskService.addComment(flowTaskAo.getTaskId(), flowTaskAo.getInstanceId(), FlowableCommentTypeEnum.DELEGATE.getType(), flowTaskAo.getComment());
            taskService.resolveTask(flowTaskAo.getTaskId(), flowTaskAo.getValues());
        } else {
            taskService.addComment(flowTaskAo.getTaskId(), flowTaskAo.getInstanceId(), FlowableCommentTypeEnum.NORMAL.getType(), flowTaskAo.getComment());
            taskService.setAssignee(flowTaskAo.getTaskId(), flowTaskAo.getAssigneeUserId());
            taskService.complete(flowTaskAo.getTaskId(), flowTaskAo.getValues());
        }
    }

    /**
     * 驳回任务
     *
     * @param flowTaskAo 请求实体参数
     */
    @Override
    public void reject(FlowTaskAO flowTaskAo) {
        if (taskService.createTaskQuery().taskId(flowTaskAo.getTaskId()).singleResult().isSuspended()) {
            throw new BusinessException("任务处于挂起状态！");
        }
        // 当前任务 task
        final Task task = taskService.createTaskQuery().taskId(flowTaskAo.getTaskId()).singleResult();
        // 获取流程定义信息
        final ProcessDefinition processDefinition = repositoryService.createProcessDefinitionQuery().processDefinitionId(task.getProcessDefinitionId()).singleResult();
        // 获取所有节点信息
        final Process process = repositoryService.getBpmnModel(processDefinition.getId()).getProcesses().get(0);
        // 获取全部节点列表，包含子节点
        final Collection<FlowElement> allElements = FlowableUtils.getAllElements(process.getFlowElements(), null);
        // 获取当前任务节点元素
        FlowElement source = null;
        if (allElements != null) {
            for (FlowElement flowElement : allElements) {
                // 类型为用户节点
                if (flowElement.getId().equals(task.getTaskDefinitionKey())) {
                    // 获取节点信息
                    source = flowElement;
                }
            }
        }

        // 目的获取所有跳转到的节点 targetIds
        // 获取当前节点的所有父级用户任务节点
        // 深度优先算法思想：延边迭代深入
        final List<UserTask> parentUserTaskList = FlowableUtils.iteratorFindParentUserTasks(source, null, null);
        if (parentUserTaskList == null || parentUserTaskList.size() == 0) {
            throw new BusinessException("当前节点为初始任务节点，不能驳回！");
        }
        // 获取活动 ID 即节点 Key
        List<String> parentUserTaskKeyList = new ArrayList<>();
        parentUserTaskList.forEach(item -> parentUserTaskKeyList.add(item.getId()));
        // 获取全部历史节点活动实例，即已经走过的节点历史，数据采用开始时间升序
        List<HistoricTaskInstance> historicTaskInstanceList = historyService.createHistoricTaskInstanceQuery().processInstanceId(task.getProcessInstanceId()).orderByHistoricTaskInstanceStartTime().asc().list();
        // 数据清洗，将回滚导致的脏数据清洗掉
        assert allElements != null;
        List<String> lastHistoricTaskInstanceList = FlowableUtils.historicTaskInstanceClean(allElements, historicTaskInstanceList);
        // 此时历史任务实例为倒序，获取最后走的节点
        List<String> targetIds = new ArrayList<>();
        // 循环结束标识，遇到当前目标节点的次数
        int number = 0;
        StringBuilder parentHistoricTaskKey = new StringBuilder();
        for (String historicTaskInstanceKey : lastHistoricTaskInstanceList) {
            // 当会签时候会出现特殊的，连续都是同一个节点历史数据的情况，这种时候跳过
            if (parentHistoricTaskKey.toString().equals(historicTaskInstanceKey)) {
                continue;
            }
            parentHistoricTaskKey = new StringBuilder(historicTaskInstanceKey);
            if (historicTaskInstanceKey.equals(task.getTaskDefinitionKey())) {
                number++;
            }
            // 在数据清洗后，历史节点就是唯一一条从起始到当前节点的历史记录，理论上每个点只会出现一次
            // 在流程中如果出现循环，那么每次循环中间的点也只会出现一次，再出现就是下次循环
            // number == 1，第一次遇到当前节点
            // number == 2，第二次遇到，代表最后一次的循环范围
            if (number == 2) {
                break;
            }
            // 如果当前历史节点，属于父级的节点，说明最后一次经过了这个点，需要退回这个点
            if (parentUserTaskKeyList.contains(historicTaskInstanceKey)) {
                targetIds.add(historicTaskInstanceKey);
            }
        }

        // 目的获取所有需要被跳转的节点 currentIds
        // 取其中一个父级任务，因为后续要么存在公共网关，要么就是串行公共线路
        UserTask oneUserTask = parentUserTaskList.get(0);
        // 获取所有正常进行的任务节点 Key，这些任务不能直接使用，需要找出其中需要撤回的任务
        List<Task> runTaskList = taskService.createTaskQuery().processInstanceId(task.getProcessInstanceId()).list();
        List<String> runTaskKeyList = new ArrayList<>();
        runTaskList.forEach(item -> runTaskKeyList.add(item.getTaskDefinitionKey()));
        // 需驳回任务列表
        List<String> currentIds = new ArrayList<>();
        // 通过父级网关的出口连线，结合 runTaskList 比对，获取需要撤回的任务
        List<UserTask> currentUserTaskList = FlowableUtils.iteratorFindChildUserTasks(oneUserTask, runTaskKeyList, null, null);
        currentUserTaskList.forEach(item -> currentIds.add(item.getId()));


        // 规定：并行网关之前节点必须需存在唯一用户任务节点，如果出现多个任务节点，则并行网关节点默认为结束节点，原因为不考虑多对多情况
        if (targetIds.size() > 1 && currentIds.size() > 1) {
            throw new BusinessException("任务出现多对多情况，无法撤回！");
        }

        // 循环获取那些需要被撤回的节点的ID，用来设置驳回原因
        List<String> currentTaskIds = new ArrayList<>();
        currentIds.forEach(currentId -> runTaskList.forEach(runTask -> {
            if (currentId.equals(runTask.getTaskDefinitionKey())) {
                currentTaskIds.add(runTask.getId());
            }
        }));
        // 设置驳回意见
        currentTaskIds.forEach(item -> taskService.addComment(item, task.getProcessInstanceId(), FlowableCommentTypeEnum.REJECT.getType(), flowTaskAo.getComment()));

        try {
            // 如果父级任务多于 1 个，说明当前节点不是并行节点，原因为不考虑多对多情况
            if (targetIds.size() > 1) {
                // 1 对 多任务跳转，currentIds 当前节点(1)，targetIds 跳转到的节点(多)
                runtimeService.createChangeActivityStateBuilder()
                        .processInstanceId(task.getProcessInstanceId()).
                        moveSingleActivityIdToActivityIds(currentIds.get(0), targetIds).changeState();
            }
            // 如果父级任务只有一个，因此当前任务可能为网关中的任务
            if (targetIds.size() == 1) {
                // 1 对 1 或 多 对 1 情况，currentIds 当前要跳转的节点列表(1或多)，targetIds.get(0) 跳转到的节点(1)
                runtimeService.createChangeActivityStateBuilder()
                        .processInstanceId(task.getProcessInstanceId())
                        .moveActivityIdsToSingleActivityId(currentIds, targetIds.get(0)).changeState();
            }
        } catch (FlowableObjectNotFoundException e) {
            throw new BusinessException("未找到流程实例，流程可能已发生变化！");
        } catch (FlowableException e) {
            throw new BusinessException("无法取消或开始活动！");
        }
    }

    /**
     * 退回任务
     *
     * @param flowTaskAo 请求实体参数
     */
    @Override
    public void fallback(FlowTaskAO flowTaskAo) {
        if (taskService.createTaskQuery().taskId(flowTaskAo.getTaskId()).singleResult().isSuspended()) {
            throw new BusinessException("任务处于挂起状态！");
        }
        // 当前任务 task
        final Task task = taskService.createTaskQuery().taskId(flowTaskAo.getTaskId()).singleResult();
        // 获取流程定义信息
        final ProcessDefinition processDefinition = repositoryService.createProcessDefinitionQuery().processDefinitionId(task.getProcessDefinitionId()).singleResult();
        // 获取所有节点信息
        final Process process = repositoryService.getBpmnModel(processDefinition.getId()).getProcesses().get(0);
        // 获取全部节点列表，包含子节点
        final Collection<FlowElement> allElements = FlowableUtils.getAllElements(process.getFlowElements(), null);
        // 获取当前任务节点元素
        FlowElement source = null;
        // 获取跳转的节点元素
        FlowElement target = null;
        if (allElements != null) {
            for (FlowElement flowElement : allElements) {
                // 当前任务节点元素
                if (flowElement.getId().equals(task.getTaskDefinitionKey())) {
                    source = flowElement;
                }
                // 跳转的节点元素
                if (flowElement.getId().equals(flowTaskAo.getTargetKey())) {
                    target = flowElement;
                }
            }
        }

        // 从当前节点向前扫描
        // 如果存在路线上不存在目标节点，说明目标节点是在网关上或非同一路线上，不可跳转
        // 否则目标节点相对于当前节点，属于串行
        final Boolean isSequential = FlowableUtils.iteratorCheckSequentialReferTarget(source, flowTaskAo.getTargetKey(), null, null);
        if (!isSequential) {
            throw new BusinessException("当前节点相对于目标节点，不属于串行关系，无法回退");
        }


        // 获取所有正常进行的任务节点 Key，这些任务不能直接使用，需要找出其中需要撤回的任务
        final List<Task> runTaskList = taskService.createTaskQuery().processInstanceId(task.getProcessInstanceId()).list();
        final List<String> runTaskKeyList = new ArrayList<>();
        runTaskList.forEach(item -> runTaskKeyList.add(item.getTaskDefinitionKey()));
        // 需退回任务列表
        final List<String> currentIds = new ArrayList<>();
        // 通过父级网关的出口连线，结合 runTaskList 比对，获取需要撤回的任务
        final List<UserTask> currentUserTaskList = FlowableUtils.iteratorFindChildUserTasks(target, runTaskKeyList, null, null);
        currentUserTaskList.forEach(item -> currentIds.add(item.getId()));

        // 循环获取那些需要被撤回的节点的ID，用来设置驳回原因
        final List<String> currentTaskIds = new ArrayList<>();
        currentIds.forEach(currentId -> runTaskList.forEach(runTask -> {
            if (currentId.equals(runTask.getTaskDefinitionKey())) {
                currentTaskIds.add(runTask.getId());
            }
        }));
        // 设置回退意见
        currentTaskIds.forEach(currentTaskId -> taskService.addComment(currentTaskId, task.getProcessInstanceId(), FlowableCommentTypeEnum.REBACK.getType(), flowTaskAo.getComment()));

        try {
            // 1 对 1 或 多 对 1 情况，currentIds 当前要跳转的节点列表(1或多)，targetKey 跳转到的节点(1)
            runtimeService.createChangeActivityStateBuilder()
                    .processInstanceId(task.getProcessInstanceId())
                    .moveActivityIdsToSingleActivityId(currentIds, flowTaskAo.getTargetKey()).changeState();
        } catch (FlowableObjectNotFoundException e) {
            throw new BusinessException("未找到流程实例，流程可能已发生变化");
        } catch (FlowableException e) {
            throw new BusinessException("无法取消或开始活动");
        }
    }

    /**
     * 查询所有可回退的节点
     *
     * @param flowTaskAo 请求实体参数
     * @return
     */
    @Override
    public List<UserTask> queryCanFallbackTaskList(FlowTaskAO flowTaskAo) {
        // 当前任务 task
        final Task task = taskService.createTaskQuery().taskId(flowTaskAo.getTaskId()).singleResult();
        // 获取流程定义信息
        final ProcessDefinition processDefinition = repositoryService.createProcessDefinitionQuery().processDefinitionId(task.getProcessDefinitionId()).singleResult();
        // 获取所有节点信息，暂不考虑子流程情况
        final Process process = repositoryService.getBpmnModel(processDefinition.getId()).getProcesses().get(0);
        final Collection<FlowElement> flowElements = process.getFlowElements();
        // 获取当前任务节点元素
        UserTask source = null;
        if (flowElements != null) {
            for (FlowElement flowElement : flowElements) {
                // 类型为用户节点
                if (flowElement.getId().equals(task.getTaskDefinitionKey())) {
                    source = (UserTask) flowElement;
                }
            }
        }
        // 获取节点的所有路线
        final List<List<UserTask>> roads = FlowableUtils.findRoad(source, null, null, null);
        // 可回退的节点列表
        List<UserTask> userTaskList = new ArrayList<>();
        for (List<UserTask> road : roads) {
            if (userTaskList.size() == 0) {
                // 还没有可回退节点直接添加
                userTaskList = road;
            } else {
                // 如果已有回退节点，则比对取交集部分
                userTaskList.retainAll(road);
            }
        }
        return userTaskList;
    }

    /**
     * 删除任务
     *
     * @param flowTaskAo 请求实体参数
     */
    @Override
    public void deleteTask(FlowTaskAO flowTaskAo) {
        taskService.deleteTask(flowTaskAo.getTaskId(), flowTaskAo.getComment());
    }

    /**
     * 认领/签收任务
     *
     * @param flowTaskAo 请求实体参数
     */
    @Override
    @Transactional(rollbackFor = Exception.class)
    public void claim(FlowTaskAO flowTaskAo) {
        taskService.claim(flowTaskAo.getTaskId(), flowTaskAo.getUserId());
    }

    /**
     * 取消认领/签收任务
     *
     * @param flowTaskAo 请求实体参数
     */
    @Override
    @Transactional(rollbackFor = Exception.class)
    public void unClaim(FlowTaskAO flowTaskAo) {
        taskService.unclaim(flowTaskAo.getTaskId());
    }

    /**
     * 委派任务
     *
     * @param flowTaskAo 请求实体参数
     */
    @Override
    @Transactional(rollbackFor = Exception.class)
    public void delegateTask(FlowTaskAO flowTaskAo) {
        taskService.delegateTask(flowTaskAo.getTaskId(), flowTaskAo.getAssignee());
    }

    /**
     * 转办任务
     *
     * @param flowTaskAo 请求实体参数
     */
    @Override
    @Transactional(rollbackFor = Exception.class)
    public void assignTask(FlowTaskAO flowTaskAo) {
        taskService.setAssignee(flowTaskAo.getTaskId(), flowTaskAo.getComment());
    }

    /**
     * 我发起的流程
     *
     * @param pages 页码
     * @param rows  每页容量
     * @return
     */
    @Override
    public ListData<FlowTaskVO> queryMyProcess(int pages, int rows) {
        UserDetail userDetail = systemService.currentUserDetail();
        ListData<FlowTaskVO> listData = new ListData<>();
        listData.setPageNum(pages);
        listData.setPageSize(rows);
        HistoricProcessInstanceQuery historicProcessInstanceQuery = historyService.createHistoricProcessInstanceQuery()
                .startedBy(userDetail.getId())
                .orderByProcessInstanceStartTime()
                .desc();
        List<HistoricProcessInstance> historicProcessInstances = historicProcessInstanceQuery.listPage(rows * (pages - 1), rows);
        listData.setTotal(historicProcessInstanceQuery.count());
        List<FlowTaskVO> flowList = new ArrayList<>();
        for (HistoricProcessInstance hisIns : historicProcessInstances) {
            FlowTaskVO flowTask = new FlowTaskVO();
            flowTask.setCreateTime(hisIns.getStartTime());
            flowTask.setFinishTime(hisIns.getEndTime());
            flowTask.setProcInsId(hisIns.getId());

            // 计算耗时
            if (Objects.nonNull(hisIns.getEndTime())) {
                long time = hisIns.getEndTime().getTime() - hisIns.getStartTime().getTime();
                flowTask.setDuration(getDate(time));
            } else {
                long time = System.currentTimeMillis() - hisIns.getStartTime().getTime();
                flowTask.setDuration(getDate(time));
            }
            // 流程定义信息
            ProcessDefinition pd = repositoryService.createProcessDefinitionQuery()
                    .processDefinitionId(hisIns.getProcessDefinitionId())
                    .singleResult();
            flowTask.setDeployId(pd.getDeploymentId());
            flowTask.setProcDefName(pd.getName());
            flowTask.setProcDefVersion(pd.getVersion());
            flowTask.setCategory(pd.getCategory());
            flowTask.setProcDefVersion(pd.getVersion());
            // 当前所处流程 todo: 本地启动放开以下注释
//            List<Task> taskList = taskService.createTaskQuery().processInstanceId(hisIns.getId()).list();
//            if (CollectionUtils.isNotEmpty(taskList)) {
//                flowTask.setTaskId(taskList.get(0).getId());
//            } else {
//                List<HistoricTaskInstance> historicTaskInstance = historyService.createHistoricTaskInstanceQuery().processInstanceId(hisIns.getId()).orderByHistoricTaskInstanceEndTime().desc().list();
//                flowTask.setTaskId(historicTaskInstance.get(0).getId());
//            }
            flowList.add(flowTask);
        }
        listData.setData(flowList);
        return listData;
    }

    /**
     * 取消流程
     *
     * @param flowTaskAo
     * @return
     */
    @Override
    public void stopProcess(FlowTaskAO flowTaskAo) {
        UserDetail userDetail = systemService.currentUserDetail();
        List<Task> task = taskService.createTaskQuery().processInstanceId(flowTaskAo.getInstanceId()).list();
        if (task == null || task.isEmpty()) {
            throw new BusinessException("流程未启动或已执行完成，取消申请失败");
        }
        // 获取当前需撤回的流程实例
        ProcessInstance processInstance =
                runtimeService.createProcessInstanceQuery()
                        .processInstanceId(flowTaskAo.getInstanceId())
                        .singleResult();
        BpmnModel bpmnModel = repositoryService.getBpmnModel(processInstance.getProcessDefinitionId());
        if (Objects.nonNull(bpmnModel)) {
            Process process = bpmnModel.getMainProcess();
            List<EndEvent> endNodes = process.findFlowElementsOfType(EndEvent.class, false);
            if (endNodes != null && !endNodes.isEmpty()) {
                Authentication.setAuthenticatedUserId(userDetail.getId());
//                taskService.addComment(task.getId(), processInstance.getProcessInstanceId(), FlowComment.STOP.getType(),
//                        StringUtils.isBlank(flowTaskVo.getComment()) ? "取消申请" : flowTaskVo.getComment());
                // 获取当前流程最后一个节点
                String endId = endNodes.get(0).getId();
                List<Execution> executions =
                        runtimeService.createExecutionQuery().parentId(processInstance.getProcessInstanceId()).list();
                List<String> executionIds = new ArrayList<>();
                executions.forEach(execution -> executionIds.add(execution.getId()));
                // 变更流程为已结束状态
                runtimeService.createChangeActivityStateBuilder()
                        .moveExecutionsToSingleActivityId(executionIds, endId).changeState();
            }
        }
    }

    @Override
    public void revokeProcess(FlowTaskAO flowTaskAo) {
        Task task = taskService.createTaskQuery().processInstanceId(flowTaskAo.getInstanceId()).singleResult();
        if (task == null) {
            throw new BusinessException("流程未启动或已执行完成，无法撤回");
        }
        UserDetail userDetail = systemService.currentUserDetail();
        List<HistoricTaskInstance> htiList = historyService.createHistoricTaskInstanceQuery()
                .processInstanceId(task.getProcessInstanceId())
                .orderByTaskCreateTime()
                .asc()
                .list();
        String myTaskId = null;
        HistoricTaskInstance myTask = null;
        for (HistoricTaskInstance hti : htiList) {
            if (userDetail.getId().equals(hti.getAssignee())) {
                myTaskId = hti.getId();
                myTask = hti;
                break;
            }
        }
        if (null == myTaskId) {
            throw new BusinessException("该任务非当前用户提交，无法撤回");
        }

        String processDefinitionId = myTask.getProcessDefinitionId();
        BpmnModel bpmnModel = repositoryService.getBpmnModel(processDefinitionId);

        //变量
        // Map<String, VariableInstance> variables = runtimeService.getVariableInstances(currentTask.getExecutionId());
        String myActivityId = null;
        List<HistoricActivityInstance> haiList = historyService.createHistoricActivityInstanceQuery()
                .executionId(myTask.getExecutionId()).finished().list();
        for (HistoricActivityInstance hai : haiList) {
            if (myTaskId.equals(hai.getTaskId())) {
                myActivityId = hai.getActivityId();
                break;
            }
        }
        FlowNode myFlowNode = (FlowNode) bpmnModel.getMainProcess().getFlowElement(myActivityId);

        Execution execution = runtimeService.createExecutionQuery().executionId(task.getExecutionId()).singleResult();
        String activityId = execution.getActivityId();
        FlowNode flowNode = (FlowNode) bpmnModel.getMainProcess().getFlowElement(activityId);

        // TODO: 记录原活动方向
        List<SequenceFlow> oriSequenceFlows = new ArrayList<>(flowNode.getOutgoingFlows());
    }

    /**
     * 获取待办列表
     *
     * @param pages 页码
     * @param rows  每页容量
     * @return
     */
    @Override
    public ListData<FlowTaskVO> queryTodoList(int pages, int rows) {
        UserDetail userDetail = systemService.currentUserDetail();
        ListData<FlowTaskVO> listData = new ListData<>();
        listData.setPageNum(pages);
        listData.setPageSize(rows);
        TaskQuery taskQuery = taskService.createTaskQuery()
                .active()
                .includeProcessVariables()
                .taskAssignee(userDetail.getId())
                .orderByTaskCreateTime().desc();
        listData.setTotal(taskQuery.count());
        List<Task> taskList = taskQuery.listPage(rows * (pages - 1), rows);
        List<FlowTaskVO> flowList = new ArrayList<>();
        for (Task task : taskList) {
            FlowTaskVO flowTask = new FlowTaskVO();
            // 当前流程信息
            flowTask.setTaskId(task.getId());
            flowTask.setTaskDefKey(task.getTaskDefinitionKey());
            flowTask.setCreateTime(task.getCreateTime());
            flowTask.setProcDefId(task.getProcessDefinitionId());
            flowTask.setExecutionId(task.getExecutionId());
            flowTask.setTaskName(task.getName());
            // 流程定义信息
            ProcessDefinition pd = repositoryService.createProcessDefinitionQuery()
                    .processDefinitionId(task.getProcessDefinitionId())
                    .singleResult();
            flowTask.setDeployId(pd.getDeploymentId());
            flowTask.setProcDefName(pd.getName());
            flowTask.setProcDefVersion(pd.getVersion());
            flowTask.setProcInsId(task.getProcessInstanceId());

            // 流程发起人信息
            HistoricProcessInstance historicProcessInstance = historyService.createHistoricProcessInstanceQuery()
                    .processInstanceId(task.getProcessInstanceId())
                    .singleResult();
            UserDetail userDetailById = userService.getUserDetailById(Long.parseLong(historicProcessInstance.getStartUserId()));
            flowTask.setStartUserId(userDetailById.getId());
            flowTask.setStartUserName(userDetailById.getUsername());
            // flowTask.setStartDeptName(userDetailById.getDept().getDeptName());
            flowList.add(flowTask);
        }

        listData.setData(flowList);
        return listData;
    }

    /**
     * 获取已办列表
     *
     * @param pages 页码
     * @param rows  每页容量
     * @return
     */
    @Override
    public ListData<FlowTaskVO> queryFinishedList(int pages, int rows) {
        UserDetail userDetail = systemService.currentUserDetail();
        ListData<FlowTaskVO> listData = new ListData<>();
        listData.setPageNum(pages);
        listData.setPageSize(rows);
        HistoricTaskInstanceQuery taskInstanceQuery = historyService.createHistoricTaskInstanceQuery()
                .includeProcessVariables()
                .finished()
                .taskAssignee(userDetail.getId())
                .orderByHistoricTaskInstanceEndTime()
                .desc();
        List<HistoricTaskInstance> historicTaskInstanceList = taskInstanceQuery.listPage(rows * (pages - 1), rows);
        List<FlowTaskVO> hisTaskList = Lists.newArrayList();
        for (HistoricTaskInstance histTask : historicTaskInstanceList) {
            FlowTaskVO flowTask = new FlowTaskVO();
            // 当前流程信息
            flowTask.setTaskId(histTask.getId());
            // 审批人员信息
            flowTask.setCreateTime(histTask.getCreateTime());
            flowTask.setFinishTime(histTask.getEndTime());
            flowTask.setDuration(getDate(histTask.getDurationInMillis()));
            flowTask.setProcDefId(histTask.getProcessDefinitionId());
            flowTask.setTaskDefKey(histTask.getTaskDefinitionKey());
            flowTask.setTaskName(histTask.getName());

            // 流程定义信息
            ProcessDefinition pd = repositoryService.createProcessDefinitionQuery()
                    .processDefinitionId(histTask.getProcessDefinitionId())
                    .singleResult();
            flowTask.setDeployId(pd.getDeploymentId());
            flowTask.setProcDefName(pd.getName());
            flowTask.setProcDefVersion(pd.getVersion());
            flowTask.setProcInsId(histTask.getProcessInstanceId());
            flowTask.setHisProcInsId(histTask.getProcessInstanceId());

            // 流程发起人信息
            HistoricProcessInstance historicProcessInstance = historyService.createHistoricProcessInstanceQuery()
                    .processInstanceId(histTask.getProcessInstanceId())
                    .singleResult();
            UserDetail userDetailById = userService.getUserDetailById(Long.parseLong(historicProcessInstance.getStartUserId()));
            flowTask.setStartUserId(userDetailById.getId());
            flowTask.setStartUserName(userDetailById.getUsername());
            // flowTask.setStartDeptName(userDetailById.getDept().getDeptName());
            hisTaskList.add(flowTask);
        }
        listData.setTotal(taskInstanceQuery.count());
        listData.setData(hisTaskList);
        return listData;
    }

    /**
     * 流程历史流转记录
     *
     * @param procInsId 流程实例Id
     * @return
     */
    @Override
    public FlowableRecord flowRecord(String procInsId, String deployId) {
        FlowableRecord flowableRecord = new FlowableRecord();
        flowableRecord.setProcessInstanceId(procInsId);
        flowableRecord.setDeployId(deployId);
        if (StringUtils.isNotBlank(procInsId)) {
            List<HistoricActivityInstance> list = historyService
                    .createHistoricActivityInstanceQuery()
                    .processInstanceId(procInsId)
                    .orderByHistoricActivityInstanceStartTime()
                    .desc().list();
            List<FlowTaskVO> hisFlowList = new ArrayList<>();
            for (HistoricActivityInstance histIns : list) {
                if (StringUtils.isNotBlank(histIns.getTaskId())) {
                    FlowTaskVO flowTask = new FlowTaskVO();
                    flowTask.setTaskId(histIns.getTaskId());
                    flowTask.setTaskName(histIns.getActivityName());
                    flowTask.setCreateTime(histIns.getStartTime());
                    flowTask.setFinishTime(histIns.getEndTime());
                    if (StringUtils.isNotBlank(histIns.getAssignee())) {
                        UserDetail userDetailById = userService.getUserDetailById(Long.parseLong(histIns.getAssignee()));
                        flowTask.setAssigneeId(Long.parseLong(userDetailById.getId()));
                        flowTask.setAssigneeName(userDetailById.getUsername());
                        // flowTask.setDeptName(sysUser.getDept().getDeptName());
                    }
                    // 展示审批人员
                    List<HistoricIdentityLink> linksForTask = historyService.getHistoricIdentityLinksForTask(histIns.getTaskId());
                    StringBuilder stringBuilder = new StringBuilder();
                    for (HistoricIdentityLink identityLink : linksForTask) {
                        // 获选人,候选组/角色(多个)
                        if ("candidate".equals(identityLink.getType())) {
                            if (StringUtils.isNotBlank(identityLink.getUserId())) {
                                UserDetail userDetailById = userService.getUserDetailById(Long.parseLong(identityLink.getUserId()));
                                stringBuilder.append(userDetailById.getUsername()).append(",");
                            }
                            // if (StringUtils.isNotBlank(identityLink.getGroupId())) {
                            //     SysRole sysRole = sysRoleService.selectRoleById(Long.parseLong(identityLink.getGroupId()));
                            //     stringBuilder.append(sysRole.getRoleName()).append(",");
                            // }
                        }
                    }
                    if (StringUtils.isNotBlank(stringBuilder)) {
                        flowTask.setCandidate(stringBuilder.substring(0, stringBuilder.length() - 1));
                    }

                    flowTask.setDuration(histIns.getDurationInMillis() == null || histIns.getDurationInMillis() == 0 ? null : getDate(histIns.getDurationInMillis()));
                    // 获取意见评论内容
                    List<Comment> commentList = taskService.getProcessInstanceComments(histIns.getProcessInstanceId());
                    commentList.forEach(comment -> {
                        if (histIns.getTaskId().equals(comment.getTaskId())) {
                            FlowCommentDTO flowCommentDTO = new FlowCommentDTO();
                            flowCommentDTO.setType(comment.getType());
                            flowCommentDTO.setComment(comment.getFullMessage());
                            flowTask.setComment(flowCommentDTO);
                        }
                    });
                    hisFlowList.add(flowTask);
                }
            }
            flowableRecord.setFlowList(hisFlowList);
            // 查询当前任务是否完成
            List<Task> taskList = taskService.createTaskQuery().processInstanceId(procInsId).list();
            flowableRecord.setFinished(!ObjectUtils.isEmpty(taskList));
        }
        return flowableRecord;
    }

    /**
     * 获取流程过程图
     *
     * @param processId
     * @return
     */
    @Override
    public InputStream diagram(String processId) {
        String processDefinitionId;
        // 获取当前的流程实例
        ProcessInstance processInstance = runtimeService.createProcessInstanceQuery().processInstanceId(processId).singleResult();
        // 如果流程已经结束，则得到结束节点
        if (Objects.isNull(processInstance)) {
            HistoricProcessInstance pi = historyService.createHistoricProcessInstanceQuery().processInstanceId(processId).singleResult();

            processDefinitionId = pi.getProcessDefinitionId();
        } else {// 如果流程没有结束，则取当前活动节点
            // 根据流程实例ID获得当前处于活动状态的ActivityId合集
            ProcessInstance pi = runtimeService.createProcessInstanceQuery().processInstanceId(processId).singleResult();
            processDefinitionId = pi.getProcessDefinitionId();
        }

        // 获得活动的节点
        List<HistoricActivityInstance> highLightedFlowList = historyService.createHistoricActivityInstanceQuery().processInstanceId(processId).orderByHistoricActivityInstanceStartTime().asc().list();

        List<String> highLightedFlows = new ArrayList<>();
        List<String> highLightedNodes = new ArrayList<>();
        //高亮线
        for (HistoricActivityInstance tempActivity : highLightedFlowList) {
            if ("sequenceFlow".equals(tempActivity.getActivityType())) {
                //高亮线
                highLightedFlows.add(tempActivity.getActivityId());
            } else {
                //高亮节点
                highLightedNodes.add(tempActivity.getActivityId());
            }
        }

        //获取流程图
        BpmnModel bpmnModel = repositoryService.getBpmnModel(processDefinitionId);
        ProcessEngineConfiguration configuration = processEngine.getProcessEngineConfiguration();
        //获取自定义图片生成器
        ProcessDiagramGenerator diagramGenerator = new CustomProcessDiagramGenerator();
        return diagramGenerator.generateDiagram(bpmnModel, "png", highLightedNodes, highLightedFlows, configuration.getActivityFontName(),
                configuration.getLabelFontName(), configuration.getAnnotationFontName(), configuration.getClassLoader(), 1.0, true);
    }

    /**
     * 获取流程执行过程
     *
     * @param procInsId 流程实例id
     * @return
     */
    @Override
    public List<FlowViewerDTO> getFlowViewer(String procInsId, String executionId) {
        List<FlowViewerDTO> flowViewerList = new ArrayList<>();
        FlowViewerDTO flowViewerDto;
        // 获取任务开始节点(临时处理方式)
        List<HistoricActivityInstance> startNodeList = historyService.createHistoricActivityInstanceQuery()
                .processInstanceId(procInsId)
                .orderByHistoricActivityInstanceStartTime()
                .asc().listPage(0, 3);
        for (HistoricActivityInstance startInstance : startNodeList) {
            if (!"sequenceFlow".equals(startInstance.getActivityType())) {
                flowViewerDto = new FlowViewerDTO();
                if (!"sequenceFlow".equals(startInstance.getActivityType())) {
                    flowViewerDto.setKey(startInstance.getActivityId());
                    // 根据流程节点处理时间校验改节点是否已完成
                    flowViewerDto.setCompleted(!Objects.isNull(startInstance.getEndTime()));
                    flowViewerList.add(flowViewerDto);
                }
            }
        }
        // 历史节点
        List<HistoricActivityInstance> hisActIns = historyService.createHistoricActivityInstanceQuery()
                .executionId(executionId)
                .orderByHistoricActivityInstanceStartTime()
                .asc().list();
        for (HistoricActivityInstance activityInstance : hisActIns) {
            if (!"sequenceFlow".equals(activityInstance.getActivityType())) {
                flowViewerDto = new FlowViewerDTO();
                flowViewerDto.setKey(activityInstance.getActivityId());
                // 根据流程节点处理时间校验改节点是否已完成
                flowViewerDto.setCompleted(!Objects.isNull(activityInstance.getEndTime()));
                flowViewerList.add(flowViewerDto);
            }
        }
        return flowViewerList;
    }

    /**
     * 获取流程变量
     *
     * @param taskId
     * @return
     */
    @Override
    public Map<String, Object> getProcessVariables(String taskId) {
        // 流程变量
        HistoricTaskInstance historicTaskInstance = historyService.createHistoricTaskInstanceQuery().includeProcessVariables().finished().taskId(taskId).singleResult();
        if (Objects.nonNull(historicTaskInstance)) {
            return historicTaskInstance.getProcessVariables();
        } else {
            return taskService.getVariables(taskId);
        }
    }

    /**
     * 获取下一节点
     *
     * @param flowTaskAo 任务
     * @return
     */
    @Override
    public FlowNextDTO queryNextFlowNode(FlowTaskAO flowTaskAo) {
        // Step 1. 获取当前节点并找到下一步节点
        Task task = taskService.createTaskQuery().taskId(flowTaskAo.getTaskId()).singleResult();
        FlowNextDTO flowNextDto = new FlowNextDTO();
        if (Objects.nonNull(task)) {
            // Step 2. 获取当前流程所有流程变量(网关节点时需要校验表达式)
            Map<String, Object> variables = taskService.getVariables(task.getId());
            List<UserTask> nextUserTask = FindNextNodeUtil.getNextUserTasks(repositoryService, task, variables);
            if (!ObjectUtils.isEmpty(nextUserTask)) {
                for (UserTask userTask : nextUserTask) {
                    MultiInstanceLoopCharacteristics multiInstance = userTask.getLoopCharacteristics();
                    // 会签节点
                    if (Objects.nonNull(multiInstance)) {
                        ListData<UserDetail> data = userService.queryUserList(null, null, null, null, null, false, true, 1, Integer.MAX_VALUE).getData();
                        if (data != null && data.getData() != null && !data.getData().isEmpty()) {
                            flowNextDto.setVars(ProcessConstants.PROCESS_MULTI_INSTANCE_USER);
                            flowNextDto.setType(ProcessConstants.PROCESS_MULTI_INSTANCE);
                            flowNextDto.setUserList(data.getData());
                        }
                    } else {

                        // 读取自定义节点属性 判断是否是否需要动态指定任务接收人员、组
                        String dataType = userTask.getAttributeValue(ProcessConstants.NAMASPASE, ProcessConstants.PROCESS_CUSTOM_DATA_TYPE);
                        String userType = userTask.getAttributeValue(ProcessConstants.NAMASPASE, ProcessConstants.PROCESS_CUSTOM_USER_TYPE);

                        // 处理加载动态指定下一节点接收人员信息
                        if (ProcessConstants.DATA_TYPE.equals(dataType)) {
                            // 指定单个人员
                            if (ProcessConstants.USER_TYPE_ASSIGNEE.equals(userType)) {
                                ListData<UserDetail> data = userService.queryUserList(null, null, null, null, null, false, true, 1, Integer.MAX_VALUE).getData();
                                if (data != null && data.getData() != null && !data.getData().isEmpty()) {
                                    flowNextDto.setVars(ProcessConstants.PROCESS_APPROVAL);
                                    flowNextDto.setType(ProcessConstants.USER_TYPE_ASSIGNEE);
                                    flowNextDto.setUserList(data.getData());
                                }
                            }
                            // 候选人员(多个)
                            if (ProcessConstants.USER_TYPE_USERS.equals(userType)) {
                                ListData<UserDetail> data = userService.queryUserList(null, null, null, null, null, false, true, 1, Integer.MAX_VALUE).getData();
                                if (data != null && data.getData() != null && !data.getData().isEmpty()) {
                                    flowNextDto.setVars(ProcessConstants.PROCESS_APPROVAL);
                                    flowNextDto.setType(ProcessConstants.USER_TYPE_USERS);
                                    flowNextDto.setUserList(data.getData());
                                }
                            }
                            // 候选组
                            if (ProcessConstants.USER_TYPE_ROUPS.equals(userType)) {
                                ListData<RoleDetail> data = roleService.queryRoleList(false, null, 1, Integer.MAX_VALUE).getData();
                                if (data != null && data.getData() != null && !data.getData().isEmpty()) {
                                    flowNextDto.setVars(ProcessConstants.PROCESS_APPROVAL);
                                    flowNextDto.setType(ProcessConstants.USER_TYPE_ROUPS);
                                    flowNextDto.setRoleList(data.getData());
                                }
                            }
                        } else {
                            flowNextDto.setType(ProcessConstants.FIXED);
                        }
                    }
                }
            } else {
                return null;
            }
        }
        return flowNextDto;
    }

    /**
     * 流程完成时间处理
     *
     * @param ms
     * @return
     */
    private String getDate(long ms) {

        long day = ms / (24 * 60 * 60 * 1000);
        long hour = (ms / (60 * 60 * 1000) - day * 24);
        long minute = ((ms / (60 * 1000)) - day * 24 * 60 - hour * 60);
        long second = (ms / 1000 - day * 24 * 60 * 60 - hour * 60 * 60 - minute * 60);

        if (day > 0) {
            return day + "天" + hour + "小时" + minute + "分钟";
        }
        if (hour > 0) {
            return hour + "小时" + minute + "分钟";
        }
        if (minute > 0) {
            return minute + "分钟";
        }
        if (second > 0) {
            return second + "秒";
        } else {
            return 0 + "秒";
        }
    }
}
