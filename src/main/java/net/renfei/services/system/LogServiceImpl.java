package net.renfei.services.system;

import net.renfei.model.log.SysLogDTO;
import net.renfei.repositories.SysLogsMapper;
import net.renfei.repositories.model.SysLogsWithBLOBs;
import net.renfei.services.BaseService;
import net.renfei.services.LogService;
import org.springframework.beans.BeanUtils;
import org.springframework.stereotype.Service;

/**
 * 日志服务
 *
 * @author renfei
 */
@Service
public class LogServiceImpl extends BaseService implements LogService {
    private final SysLogsMapper sysLogsMapper;

    public LogServiceImpl(SysLogsMapper sysLogsMapper) {
        this.sysLogsMapper = sysLogsMapper;
    }

    @Override
    public int save(SysLogDTO sysLog) {
        SysLogsWithBLOBs sysLogs = new SysLogsWithBLOBs();
        BeanUtils.copyProperties(sysLog, sysLogs);
        return sysLogsMapper.insertSelective(sysLogs);
    }
}
