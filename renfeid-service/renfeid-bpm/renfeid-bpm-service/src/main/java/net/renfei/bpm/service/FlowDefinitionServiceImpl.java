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

import com.github.pagehelper.Page;
import com.github.pagehelper.PageHelper;
import net.renfei.bpm.api.FlowDefinitionService;
import net.renfei.bpm.api.entity.FlowProcDefDTO;
import net.renfei.bpm.api.entity.FlowableCommentTypeEnum;
import net.renfei.bpm.repositories.FlowDeployMapper;
import net.renfei.common.api.entity.ListData;
import net.renfei.common.api.exception.BusinessException;
import net.renfei.common.core.service.SystemService;
import net.renfei.uaa.api.entity.UserDetail;
import org.apache.commons.io.IOUtils;
import org.flowable.bpmn.model.BpmnModel;
import org.flowable.engine.repository.Deployment;
import org.flowable.engine.repository.ProcessDefinition;
import org.flowable.engine.runtime.ProcessInstance;
import org.flowable.image.impl.DefaultProcessDiagramGenerator;
import org.flowable.task.api.Task;
import org.springframework.stereotype.Service;

import java.io.IOException;
import java.io.InputStream;
import java.nio.charset.StandardCharsets;
import java.util.Collections;
import java.util.Map;
import java.util.Objects;

/**
 * 流程定义服务
 *
 * @author renfei
 */
@Service
public class FlowDefinitionServiceImpl extends AbstractFlowableService implements FlowDefinitionService {
    private final FlowDeployMapper flowDeployMapper;
    private final SystemService systemService;

    public FlowDefinitionServiceImpl(FlowDeployMapper flowDeployMapper,
                                     SystemService systemService) {
        this.flowDeployMapper = flowDeployMapper;
        this.systemService = systemService;
    }

    /**
     * 查询流程定义列表
     *
     * @param name  名称
     * @param pages 当前页码
     * @param rows  每页条数
     * @return
     */
    @Override
    public ListData<FlowProcDefDTO> queryFlowableDefinition(String name, int pages, int rows) {
        try (Page<FlowProcDefDTO> page = PageHelper.startPage(pages, rows)) {
            flowDeployMapper.queryDeployment(name);
            return new ListData<>(page);
        }
    }

    /**
     * 导入流程文件
     *
     * @param name
     * @param category
     * @param inputStream
     */
    @Override
    public void importFile(String name, String category, InputStream inputStream) {
        Deployment deploy = repositoryService
                .createDeployment()
                .addInputStream(name + BPMN_FILE_SUFFIX, inputStream)
                .name(name)
                .category(category)
                .deploy();
        ProcessDefinition definition = repositoryService
                .createProcessDefinitionQuery()
                .deploymentId(deploy.getId())
                .singleResult();
        repositoryService.setProcessDefinitionCategory(definition.getId(), category);
    }

    /**
     * 读取流程XML
     *
     * @param deployId
     * @return
     * @throws IOException
     */
    @Override
    public String readXml(String deployId) throws IOException {
        ProcessDefinition definition = repositoryService.createProcessDefinitionQuery().deploymentId(deployId).singleResult();
        InputStream inputStream = repositoryService.getResourceAsStream(definition.getDeploymentId(), definition.getResourceName());
        return IOUtils.toString(inputStream, StandardCharsets.UTF_8);
    }

    /**
     * 读取图片文件
     *
     * @param deployId
     * @return
     */
    @Override
    public InputStream readImage(String deployId) {
        ProcessDefinition processDefinition = repositoryService.createProcessDefinitionQuery().deploymentId(deployId).singleResult();
        //获得图片流
        DefaultProcessDiagramGenerator diagramGenerator = new DefaultProcessDiagramGenerator();
        BpmnModel bpmnModel = repositoryService.getBpmnModel(processDefinition.getId());
        //输出为图片
        return diagramGenerator.generateDiagram(
                bpmnModel,
                "png",
                Collections.emptyList(),
                Collections.emptyList(),
                "宋体",
                "宋体",
                "宋体",
                null,
                1.0,
                false);
    }

    /**
     * 根据流程定义ID启动流程实例
     *
     * @param procDefId   流程定义ID
     * @param variables
     */
    @Override
    public void startProcessInstanceById(String procDefId, Map<String, Object> variables) {
        ProcessDefinition processDefinition = repositoryService.createProcessDefinitionQuery().processDefinitionId(procDefId)
                .latestVersion().singleResult();
        if (Objects.nonNull(processDefinition) && processDefinition.isSuspended()) {
            throw new BusinessException("流程已被挂起,请先激活流程");
        }
        // 设置流程发起人Id到流程中
        UserDetail userDetail = systemService.currentUserDetail();
        identityService.setAuthenticatedUserId(userDetail.getId());
        variables.put(PROCESS_INITIATOR, "");
        ProcessInstance processInstance = runtimeService.startProcessInstanceById(procDefId, variables);
        // 给第一步申请人节点设置任务执行人和意见
        Task task = taskService.createTaskQuery().processInstanceId(processInstance.getProcessInstanceId()).singleResult();
        if (Objects.nonNull(task)) {
            taskService.addComment(task.getId(), processInstance.getProcessInstanceId(), FlowableCommentTypeEnum.NORMAL.getType(), userDetail.getUsername() + "发起流程申请");
            // TODO: 第一个节点不设置为申请人节点有点问题？
            // taskService.setAssignee(task.getId(), sysUser.getUserId().toString());
            taskService.complete(task.getId(), variables);
        }
    }

    /**
     * 激活或挂起流程定义
     *
     * @param activate 是否激活
     * @param deployId 部署ID
     */
    @Override
    public void activateOrSuspend(boolean activate, String deployId) {
        ProcessDefinition procDef = repositoryService.createProcessDefinitionQuery().deploymentId(deployId).singleResult();
        if (activate) {
            // 激活
            repositoryService.activateProcessDefinitionById(procDef.getId(), true, null);
        } else {
            // 挂起
            repositoryService.suspendProcessDefinitionById(procDef.getId(), true, null);
        }
    }

    /**
     * 删除流程定义
     *
     * @param deployId 部署ID
     */
    @Override
    public void delete(String deployId) {
        // true 允许级联删除 ,不设置会导致数据库外键关联异常
        repositoryService.deleteDeployment(deployId, true);
    }
}
