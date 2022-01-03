package net.renfei.services;

import net.renfei.model.log.SysLogDTO;

/**
 * 日志服务
 *
 * @author renfei
 */
public interface LogService {
    int save(SysLogDTO sysLog);
}
