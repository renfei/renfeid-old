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

import net.renfei.common.api.exception.BusinessException;
import org.flowable.engine.*;
import org.flowable.task.api.Task;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.util.ObjectUtils;

import java.util.List;
import java.util.Map;

/**
 * Flowable 服务
 *
 * @author renfei
 */
@Service
public class FlowableService {
    /**
     * 需要由用户执行的任务是 BPM 引擎的核心
     * 围绕任务的所有内容都在 TaskService 中
     */
    private final TaskService taskService;
    /**
     * 它处理启动流程定义的新流程实例
     */
    private final RuntimeService runtimeService;
    /**
     * Flowable 引擎收集的所有历史数据
     */
    private final HistoryService historyService;
    /**
     * 它支持组和用户的管理（创建、更新、删除、查询……）
     */
    private final IdentityService identityService;
    /**
     * 该服务提供用于管理和操作部署和流程定义的操作
     */
    private final RepositoryService repositoryService;
    /**
     * 可用于更改流程定义的一部分，而无需重新部署它
     */
    private final DynamicBpmnService dynamicBpmnService;

    public FlowableService(TaskService taskService,
                           RuntimeService runtimeService,
                           HistoryService historyService,
                           IdentityService identityService,
                           RepositoryService repositoryService,
                           DynamicBpmnService dynamicBpmnService) {
        this.taskService = taskService;
        this.runtimeService = runtimeService;
        this.historyService = historyService;
        this.identityService = identityService;
        this.repositoryService = repositoryService;
        this.dynamicBpmnService = dynamicBpmnService;
    }

    @Transactional(rollbackFor = Exception.class)
    public void startProcess(String processDefinitionKey, String businessKey, Map<String, Object> variables) {
        if (ObjectUtils.isEmpty(processDefinitionKey)) {
            throw new BusinessException("processDefinitionKey 不能为空");
        }
        if (ObjectUtils.isEmpty(businessKey) && ObjectUtils.isEmpty(variables)) {
            runtimeService.startProcessInstanceByKey(processDefinitionKey);
        } else if (ObjectUtils.isEmpty(variables)) {
            runtimeService.startProcessInstanceByKey(processDefinitionKey, businessKey);
        } else if (ObjectUtils.isEmpty(businessKey)) {
            runtimeService.startProcessInstanceByKey(processDefinitionKey, variables);
        } else {
            runtimeService.startProcessInstanceByKey(processDefinitionKey, businessKey, variables);
        }

    }

    @Transactional(rollbackFor = Exception.class)
    public List<Task> queryTasks(String assignee) {
        return taskService.createTaskQuery()
                .taskAssignee(assignee)
                .list();
    }

    @Transactional(rollbackFor = Exception.class)
    public void completeTask(String taskId, Map<String, Object> variables) {
        taskService.complete(taskId, variables);
    }
}
