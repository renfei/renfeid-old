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

import net.renfei.bpm.api.entity.FlowProcDefDTO;
import net.renfei.common.api.entity.ListData;

import java.io.IOException;
import java.io.InputStream;
import java.util.Map;

/**
 * 流程定义服务
 *
 * @author renfei
 */
public interface FlowDefinitionService {
    /**
     * 查询流程定义列表
     *
     * @param name  名称
     * @param pages 当前页码
     * @param rows  每页条数
     * @return
     */
    ListData<FlowProcDefDTO> queryFlowableDefinition(String name, int pages, int rows);

    /**
     * 导入流程文件
     *
     * @param name
     * @param category
     * @param inputStream
     */
    void importFile(String name, String category, InputStream inputStream);

    /**
     * 读取流程XML
     *
     * @param deployId
     * @return
     * @throws IOException
     */
    String readXml(String deployId) throws IOException;

    /**
     * 读取图片文件
     *
     * @param deployId
     * @return
     */
    InputStream readImage(String deployId);

    /**
     * 根据流程定义ID启动流程实例
     *
     * @param procDefId   流程定义ID
     * @param variables
     */
    void startProcessInstanceById(String procDefId, Map<String, Object> variables);

    /**
     * 激活或挂起流程定义
     *
     * @param activate 是否激活
     * @param deployId 部署ID
     */
    void activateOrSuspend(boolean activate, String deployId);

    /**
     * 删除流程定义
     *
     * @param deployId 部署ID
     */
    void delete(String deployId);
}
