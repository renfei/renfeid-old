package net.renfei.services.flowable;

import net.renfei.services.BaseService;
import net.renfei.services.FlowableService;
import org.flowable.engine.*;
import org.springframework.stereotype.Service;

/**
 * 流程引擎服务
 *
 * @author renfei
 */
@Service
public class FlowableServiceImpl extends BaseService implements FlowableService {
    /**
     * 流程节点任务服务
     */
    private final TaskService taskService;
    /**
     * 流程引擎
     */
    private final ProcessEngine processEngine;
    /**
     * 流程实例服务
     */
    private final RuntimeService runtimeService;
    /**
     * 历史数据服务
     */
    private final HistoryService historyService;
    /**
     * 用户及组管理服务
     */
    private final IdentityService identityService;
    /**
     * 部署服务
     */
    private final RepositoryService repositoryService;

    public FlowableServiceImpl(TaskService taskService,
                               ProcessEngine processEngine,
                               RuntimeService runtimeService,
                               HistoryService historyService,
                               IdentityService identityService,
                               RepositoryService repositoryService) {
        this.taskService = taskService;
        this.processEngine = processEngine;
        this.runtimeService = runtimeService;
        this.historyService = historyService;
        this.identityService = identityService;
        this.repositoryService = repositoryService;
    }
}
