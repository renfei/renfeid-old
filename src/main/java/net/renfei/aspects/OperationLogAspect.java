package net.renfei.aspects;

import lombok.extern.slf4j.Slf4j;
import net.renfei.annotation.OperationLog;
import net.renfei.config.SystemConfig;
import net.renfei.domain.user.User;
import net.renfei.model.log.SysLogDTO;
import net.renfei.services.LogService;
import net.renfei.utils.IpUtils;
import net.renfei.utils.JacksonUtil;
import org.aspectj.lang.JoinPoint;
import org.aspectj.lang.Signature;
import org.aspectj.lang.annotation.AfterReturning;
import org.aspectj.lang.annotation.Aspect;
import org.aspectj.lang.annotation.Pointcut;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.stereotype.Component;
import org.springframework.web.context.request.RequestContextHolder;
import org.springframework.web.context.request.ServletRequestAttributes;
import org.springframework.web.servlet.ModelAndView;

import javax.servlet.http.HttpServletRequest;
import java.lang.reflect.Method;
import java.util.Date;
import java.util.Map;

import static net.renfei.controllers.BaseController.SESSION_KEY;
import static net.renfei.services.system.LogServiceImpl.convertMap;

/**
 * 在切面记录操作日志
 *
 * @author renfei
 */
@Slf4j
@Aspect
@Component
public class OperationLogAspect {
    private final SystemConfig systemConfig;
    private final LogService logService;

    public OperationLogAspect(SystemConfig systemConfig,
                              LogService logService) {
        this.systemConfig = systemConfig;
        this.logService = logService;
    }

    /**
     * 设置操作日志切入点 记录操作日志 在注解的位置切入代码
     */
    @Pointcut("@annotation(net.renfei.annotation.OperationLog)")
    public void operationLogPointCut() {
    }

    /**
     * 方法执行完以后切进去，记录日志
     *
     * @param joinPoint 切点
     * @param retObj    方法返回对象
     */
    @AfterReturning(value = "operationLogPointCut()", returning = "retObj")
    public void saveOperationLog(JoinPoint joinPoint, Object retObj) {
        SysLogDTO operationLog = new SysLogDTO();
        Signature signature = joinPoint.getSignature();
        Method method = currentMethod(joinPoint, signature.getName());
        // 注解信息
        OperationLog annotation = method.getAnnotation(OperationLog.class);
        operationLog.setLogLevel(annotation.leve().toString());
        operationLog.setLogModule(annotation.module().toString());
        operationLog.setLogType(annotation.operation().toString());
        // 请求信息
        ServletRequestAttributes servletRequestAttributes = (ServletRequestAttributes) RequestContextHolder.getRequestAttributes();
        assert servletRequestAttributes != null;
        HttpServletRequest request = servletRequestAttributes.getRequest();
        if (request != null) {
            User user = null;
            if ("SESSION".equals(systemConfig.getAuthMode())) {
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
                operationLog.setUserUuid(user.getUuid());
                operationLog.setUserName(user.getUserName());
            }
            operationLog.setRequMethod(request.getMethod());
            operationLog.setRequUri(request.getRequestURI());
            operationLog.setRequIp(IpUtils.getIpAddress(request));
            Map<String, String> rtnMap = convertMap(request.getParameterMap());
            // 将参数所在的数组转换成json
            String params = JacksonUtil.obj2String(rtnMap);
            operationLog.setRequParam(params);
            operationLog.setRequAgent(request.getHeader("User-Agent"));
        }
        if (retObj != null) {
            if (!(retObj instanceof ModelAndView)) {
                operationLog.setRespParam(JacksonUtil.obj2String(retObj));
            }
        }
        operationLog.setLogTime(new Date());
        operationLog.setLogDesc(annotation.desc());

        logService.save(operationLog);
    }

    /**
     * 获取当前执行的方法
     *
     * @param joinPoint  连接点
     * @param methodName 方法名称
     * @return 方法
     */
    private Method currentMethod(JoinPoint joinPoint, String methodName) {
        /**
         * 获取目标类的所有方法，找到当前要执行的方法
         */
        Method[] methods = joinPoint.getTarget().getClass().getMethods();
        Method resultMethod = null;
        for (Method method : methods) {
            if (method.getName().equals(methodName)) {
                resultMethod = method;
                break;
            }
        }
        return resultMethod;
    }
}
