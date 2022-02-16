package net.renfei.controllers._api.system;

import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.tags.Tag;
import net.renfei.model.APIResult;
import net.renfei.model.ListData;
import net.renfei.model.log.LogLevelEnum;
import net.renfei.model.log.OperationTypeEnum;
import net.renfei.model.system.SystemTypeEnum;
import net.renfei.repositories.model.SysLogsWithBLOBs;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;

import java.util.Date;

/**
 * 系统操作日志审计接口
 *
 * @author renfei
 */
@Tag(name = "系统操作日志审计接口", description = "系统操作日志审计接口")
@RequestMapping("/_/api")
public interface SysLogsApi {
    /**
     * 获取系统运行状态
     *
     * @return
     */
    @GetMapping("sys/operation/logs")
    @Operation(summary = "获取系统操作日志", tags = {"系统操作日志审计接口"}, description = "获取系统操作日志")
    APIResult<ListData<SysLogsWithBLOBs>>
    querySystemOperationLogs(@RequestParam(value = "startTime", required = false) Date startTime,
                             @RequestParam(value = "endTime", required = false) Date endTime,
                             @RequestParam(value = "logLevel", required = false) LogLevelEnum logLevel,
                             @RequestParam(value = "systemType", required = false) SystemTypeEnum systemType,
                             @RequestParam(value = "operationType", required = false) OperationTypeEnum operationType,
                             @RequestParam(value = "userName", required = false) String userName,
                             @RequestParam(value = "ip", required = false) String ip,
                             @RequestParam(value = "pages", required = false) String pages,
                             @RequestParam(value = "rows", required = false) String rows);
}
