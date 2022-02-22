package net.renfei.console.controller.system.impl;

import net.renfei.annotation.OperationLog;
import net.renfei.console.controller.system.SysLogsApi;
import net.renfei.controller.BaseController;
import net.renfei.model.APIResult;
import net.renfei.model.ListData;
import net.renfei.model.log.LogLevelEnum;
import net.renfei.model.log.OperationTypeEnum;
import net.renfei.model.system.SystemTypeEnum;
import net.renfei.repositories.model.SysLogsWithBLOBs;
import net.renfei.services.SysService;
import org.springframework.web.bind.annotation.RestController;

import java.util.Date;

/**
 * 系统操作日志审计接口
 *
 * @author renfei
 */
@RestController
public class SysLogsApiController extends BaseController implements SysLogsApi {
    private final SysService sysService;

    public SysLogsApiController(SysService sysService) {
        this.sysService = sysService;
    }

    @Override
    @OperationLog(module = SystemTypeEnum.SYS_LOGS, desc = "获取系统操作日志", operation = OperationTypeEnum.RETRIEVE)
    public APIResult<ListData<SysLogsWithBLOBs>>
    querySystemOperationLogs(Date startTime, Date endTime, LogLevelEnum logLevel, SystemTypeEnum systemType,
                             OperationTypeEnum operationType, String userName, String ip, String pages, String rows) {
        return new APIResult<>(sysService.querySystemLog(getSignUser(), startTime,
                endTime, logLevel, systemType, operationType, userName, ip, pages, rows));
    }
}
