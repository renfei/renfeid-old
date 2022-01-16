package net.renfei.services.system;

import net.renfei.config.SystemConfig;
import net.renfei.domain.user.User;
import net.renfei.model.log.LogLevelEnum;
import net.renfei.model.log.OperationTypeEnum;
import net.renfei.model.log.SysLogDTO;
import net.renfei.model.system.SystemTypeEnum;
import net.renfei.repositories.SysLogsMapper;
import net.renfei.repositories.model.SysLogsWithBLOBs;
import net.renfei.services.BaseService;
import net.renfei.services.LogService;
import net.renfei.utils.IpUtils;
import net.renfei.utils.JacksonUtil;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.BeanUtils;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.stereotype.Service;

import javax.servlet.http.HttpServletRequest;
import java.util.Date;
import java.util.HashMap;
import java.util.Map;

import static net.renfei.config.SystemConfig.SESSION_AUTH_MODE;
import static net.renfei.controllers.BaseController.SESSION_KEY;

/**
 * 日志服务
 *
 * @author renfei
 */
@Service
public class LogServiceImpl extends BaseService implements LogService {
    private static final Logger logger = LoggerFactory.getLogger(LogServiceImpl.class);
    private final SysLogsMapper sysLogsMapper;
    private final SystemConfig systemConfig;

    public LogServiceImpl(SysLogsMapper sysLogsMapper,
                          SystemConfig systemConfig) {
        this.sysLogsMapper = sysLogsMapper;
        this.systemConfig = systemConfig;
    }

    @Override
    public int save(SysLogDTO sysLog) {
        SysLogsWithBLOBs sysLogs = new SysLogsWithBLOBs();
        BeanUtils.copyProperties(sysLog, sysLogs);
        return sysLogsMapper.insertSelective(sysLogs);
    }

    @Override
    public int log(LogLevelEnum level, SystemTypeEnum systemType, OperationTypeEnum operationType, String desc, HttpServletRequest request) {
        SysLogsWithBLOBs sysLogs = new SysLogsWithBLOBs();
        sysLogs.setLogLevel(level.toString());
        sysLogs.setLogModule(systemType.toString());
        sysLogs.setLogType(operationType.toString());
        sysLogs.setLogDesc(desc);
        sysLogs.setLogTime(new Date());
        if (request != null) {
            User user = null;
            if (SESSION_AUTH_MODE.equals(systemConfig.getAuthMode())) {
                Object session = request.getSession().getAttribute(SESSION_KEY);
                if (session instanceof User) {
                    user = (User) session;
                }
            } else {
                Authentication authentication = SecurityContextHolder.getContext().getAuthentication();
                if (authentication != null && authentication.getPrincipal() instanceof User) {
                    user = (User) authentication.getPrincipal();
                }
            }
            if (user != null) {
                sysLogs.setUserUuid(user.getUuid());
                sysLogs.setUserName(user.getUserName());
            }
            // 请求的参数
            try {
                Map<String, String> rtnMap = convertMap(request.getParameterMap());
                // 将参数所在的数组转换成json
                String params = JacksonUtil.obj2String(rtnMap);
                sysLogs.setRequMethod(request.getMethod());
                sysLogs.setRequUri(request.getRequestURI());
                sysLogs.setRequIp(IpUtils.getIpAddress(request));
                sysLogs.setRequParam(params);
                sysLogs.setRequAgent(request.getHeader("User-Agent"));
            } catch (Exception exception) {
                logger.error(exception.getMessage(), exception);
            }
        }
        return sysLogsMapper.insertSelective(sysLogs);
    }

    /**
     * 转换request 请求参数
     *
     * @param paramMap request获取的参数数组
     */
    public static Map<String, String> convertMap(Map<String, String[]> paramMap) {
        Map<String, String> rtnMap = new HashMap<String, String>();
        for (String key : paramMap.keySet()) {
            rtnMap.put(key, paramMap.get(key)[0]);
        }
        return rtnMap;
    }
}
