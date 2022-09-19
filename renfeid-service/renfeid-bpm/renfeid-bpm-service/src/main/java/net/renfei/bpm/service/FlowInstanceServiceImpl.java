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

import net.renfei.bpm.api.FlowInstanceService;
import net.renfei.bpm.constant.ProcessConstants;
import net.renfei.common.api.exception.BusinessException;
import net.renfei.common.core.service.SystemService;
import net.renfei.uaa.api.entity.UserDetail;
import org.flowable.common.engine.api.FlowableObjectNotFoundException;
import org.flowable.engine.history.HistoricProcessInstance;
import org.flowable.task.api.Task;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;
import java.util.Map;
import java.util.Objects;

/**
 * @author renfei
 */
@Service
public class FlowInstanceServiceImpl extends AbstractFlowableService implements FlowInstanceService {
    private final static Logger logger = LoggerFactory.getLogger(FlowInstanceServiceImpl.class);
    private final SystemService systemService;

    public FlowInstanceServiceImpl(SystemService systemService) {
        this.systemService = systemService;
    }

    @Override
    public List<Task> queryListByInstanceId(String instanceId) {
        return taskService.createTaskQuery().processInstanceId(instanceId).active().list();
    }

    /**
     * 激活或挂起流程实例
     *
     * @param activate   激活还是挂起
     * @param instanceId 流程实例ID
     */
    @Override
    public void activateOrSuspend(boolean activate, String instanceId) {
        if (activate) {
            runtimeService.activateProcessInstanceById(instanceId);
        } else {
            runtimeService.suspendProcessInstanceById(instanceId);
        }
    }

    /**
     * 删除流程实例ID
     *
     * @param instanceId   流程实例ID
     * @param deleteReason 删除原因
     */
    @Override
    @Transactional(rollbackFor = Exception.class)
    public void delete(String instanceId, String deleteReason) {
        // 查询历史数据
        HistoricProcessInstance historicProcessInstance = getHistoricProcessInstanceById(instanceId);
        if (historicProcessInstance.getEndTime() != null) {
            historyService.deleteHistoricProcessInstance(historicProcessInstance.getId());
            return;
        }
        // 删除流程实例
        runtimeService.deleteProcessInstance(instanceId, deleteReason);
        // 删除历史流程实例
        historyService.deleteHistoricProcessInstance(instanceId);
    }

    /**
     * 根据实例ID查询历史实例数据
     *
     * @param processInstanceId
     * @return
     */
    @Override
    public HistoricProcessInstance getHistoricProcessInstanceById(String processInstanceId) {
        HistoricProcessInstance historicProcessInstance =
                historyService.createHistoricProcessInstanceQuery().processInstanceId(processInstanceId).singleResult();
        if (Objects.isNull(historicProcessInstance)) {
            throw new FlowableObjectNotFoundException("流程实例不存在: " + processInstanceId);
        }
        return historicProcessInstance;
    }
}
