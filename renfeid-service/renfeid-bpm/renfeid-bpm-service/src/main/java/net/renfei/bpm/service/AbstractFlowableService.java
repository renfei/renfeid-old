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

import org.flowable.engine.*;

import javax.annotation.Resource;

/**
 * Flowable 服务抽象类
 *
 * @author renfei
 */
public abstract class AbstractFlowableService {
    /**
     * 流程文件后缀名
     */
    protected static final String BPMN_FILE_SUFFIX = ".bpmn";
    /**
     * 初始化人员
     */
    public static final String PROCESS_INITIATOR = "INITIATOR";
    /**
     * 需要由用户执行的任务是 BPM 引擎的核心
     * 围绕任务的所有内容都在 TaskService 中
     */
    @Resource
    protected TaskService taskService;
    @Resource
    protected ProcessEngine processEngine;
    /**
     * 它处理启动流程定义的新流程实例
     */
    @Resource
    protected RuntimeService runtimeService;
    /**
     * Flowable 引擎收集的所有历史数据
     */
    @Resource
    protected HistoryService historyService;
    /**
     * 它支持组和用户的管理（创建、更新、删除、查询……）
     */
    @Resource
    protected IdentityService identityService;
    /**
     * 该服务提供用于管理和操作部署和流程定义的操作
     */
    @Resource
    protected RepositoryService repositoryService;
    /**
     * 可用于更改流程定义的一部分，而无需重新部署它
     */
    @Resource
    protected DynamicBpmnService dynamicBpmnService;
}
