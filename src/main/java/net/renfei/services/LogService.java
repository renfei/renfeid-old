package net.renfei.services;

import net.renfei.model.log.LogLevelEnum;
import net.renfei.model.log.OperationTypeEnum;
import net.renfei.model.log.SysLogDTO;
import net.renfei.model.system.SystemTypeEnum;

import javax.servlet.http.HttpServletRequest;

/**
 * 日志服务
 *
 * @author renfei
 */
public interface LogService {
    int save(SysLogDTO sysLog);

    int log(LogLevelEnum level, SystemTypeEnum systemType, OperationTypeEnum operationType, String desc, HttpServletRequest request);
}
