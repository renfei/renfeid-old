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
package net.renfei.bpm.api;

import net.renfei.bpm.api.entity.*;
import net.renfei.common.api.entity.ListData;
import org.flowable.bpmn.model.UserTask;

import java.io.InputStream;
import java.util.List;
import java.util.Map;

/**
 * 流程任务服务
 *
 * @author renfei
 */
public interface FlowTaskService {
    /**
     * 审批任务
     *
     * @param flowTaskAo 请求实体参数
     */
    void complete(FlowTaskAO flowTaskAo);

    /**
     * 驳回任务
     *
     * @param flowTaskAo 请求实体参数
     */
    void reject(FlowTaskAO flowTaskAo);

    /**
     * 退回任务
     *
     * @param flowTaskAo 请求实体参数
     */
    void fallback(FlowTaskAO flowTaskAo);

    /**
     * 查询所有可回退的节点
     *
     * @param flowTaskAo 请求实体参数
     * @return
     */
    List<UserTask> queryCanFallbackTaskList(FlowTaskAO flowTaskAo);

    /**
     * 删除任务
     *
     * @param flowTaskAo 请求实体参数
     */
    void deleteTask(FlowTaskAO flowTaskAo);

    /**
     * 认领/签收任务
     *
     * @param flowTaskAo 请求实体参数
     */
    void claim(FlowTaskAO flowTaskAo);

    /**
     * 取消认领/签收任务
     *
     * @param flowTaskAo 请求实体参数
     */
    void unClaim(FlowTaskAO flowTaskAo);

    /**
     * 委派任务
     *
     * @param flowTaskAo 请求实体参数
     */
    void delegateTask(FlowTaskAO flowTaskAo);

    /**
     * 转办任务
     *
     * @param flowTaskAo 请求实体参数
     */
    void assignTask(FlowTaskAO flowTaskAo);

    /**
     * 我发起的流程
     *
     * @param pages 页码
     * @param rows  每页容量
     * @return
     */
    ListData<FlowTaskVO> queryMyProcess(int pages, int rows);

    /**
     * 取消流程
     *
     * @param flowTaskAo 请求实体参数
     * @return
     */
    void stopProcess(FlowTaskAO flowTaskAo);

    /**
     * 撤回流程
     *
     * @param flowTaskAo
     * @return
     */
    void revokeProcess(FlowTaskAO flowTaskAo);

    /**
     * 获取待办列表
     *
     * @param pages 页码
     * @param rows  每页容量
     * @return
     */
    ListData<FlowTaskVO> queryTodoList(int pages, int rows);

    /**
     * 获取已办列表
     *
     * @param pages 页码
     * @param rows  每页容量
     * @return
     */
    ListData<FlowTaskVO> queryFinishedList(int pages, int rows);

    /**
     * 流程历史流转记录
     *
     * @param procInsId 流程实例Id
     * @return
     */
    FlowableRecord flowRecord(String procInsId, String deployId);

    /**
     * 获取流程过程图
     *
     * @param processId
     * @return
     */
    InputStream diagram(String processId);

    /**
     * 获取流程执行过程
     *
     * @param procInsId 流程实例id
     * @return
     */
    List<FlowViewerDTO> getFlowViewer(String procInsId, String executionId);

    /**
     * 获取流程变量
     *
     * @param taskId
     * @return
     */
    Map<String, Object> getProcessVariables(String taskId);

    /**
     * 获取下一节点
     *
     * @param flowTaskAo 任务
     * @return
     */
    FlowNextDTO queryNextFlowNode(FlowTaskAO flowTaskAo);
}
