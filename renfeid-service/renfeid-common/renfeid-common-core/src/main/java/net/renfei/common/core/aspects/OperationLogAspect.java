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
package net.renfei.common.core.aspects;

import net.renfei.common.api.utils.JacksonUtil;
import net.renfei.common.core.annotation.OperationLog;
import net.renfei.common.core.config.SystemConfig;
import net.renfei.common.core.entity.SystemLogEntity;
import net.renfei.common.core.entity.SystemTypeEnum;
import net.renfei.common.core.service.SystemLogService;
import net.renfei.common.core.service.SystemService;
import net.renfei.common.core.utils.IpUtils;
import net.renfei.uaa.api.entity.UserDetail;
import org.aspectj.lang.JoinPoint;
import org.aspectj.lang.Signature;
import org.aspectj.lang.annotation.AfterReturning;
import org.aspectj.lang.annotation.Aspect;
import org.aspectj.lang.annotation.Pointcut;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Component;
import org.springframework.web.context.request.RequestContextHolder;
import org.springframework.web.context.request.ServletRequestAttributes;
import org.springframework.web.servlet.ModelAndView;

import javax.servlet.http.HttpServletRequest;
import java.lang.reflect.Field;
import java.lang.reflect.Method;
import java.util.Date;
import java.util.HashMap;
import java.util.Map;

/**
 * 在切面记录操作日志
 *
 * @author renfei
 */
@Aspect
@Component
public class OperationLogAspect {
    private final static Logger logger = LoggerFactory.getLogger(OperationLogAspect.class);
    private final SystemConfig systemConfig;
    private final SystemService systemService;
    private final SystemLogService systemLogService;


    public OperationLogAspect(SystemConfig systemConfig,
                              SystemService systemService,
                              SystemLogService systemLogService) {
        this.systemConfig = systemConfig;
        this.systemService = systemService;
        this.systemLogService = systemLogService;
    }

    /**
     * 设置操作日志切入点 记录操作日志 在注解的位置切入代码
     */
    @Pointcut("@annotation(net.renfei.common.core.annotation.OperationLog)")
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
        SystemLogEntity systemLogEntity = new SystemLogEntity();
        Signature signature = joinPoint.getSignature();
        Method method = currentMethod(joinPoint, signature.getName());
        if (method != null) {
            // 注解信息
            OperationLog annotation = method.getAnnotation(OperationLog.class);
            systemLogEntity.setLogLevel(annotation.leve().toString());
            systemLogEntity.setLogModule(annotation.module().toString());
            systemLogEntity.setLogType(annotation.operation().toString());
            // 请求信息
            ServletRequestAttributes servletRequestAttributes = (ServletRequestAttributes) RequestContextHolder.getRequestAttributes();
            assert servletRequestAttributes != null;
            HttpServletRequest request = servletRequestAttributes.getRequest();
            UserDetail userDetail = systemService.currentUserDetail();
            if (userDetail != null) {
                systemLogEntity.setUserUuid(userDetail.getUuid());
                systemLogEntity.setUserName(userDetail.getUsername());
            }
            systemLogEntity.setRequMethod(request.getMethod());
            systemLogEntity.setRequUri(request.getRequestURI());
            systemLogEntity.setRequIp(IpUtils.getIpAddress(request));
            Map<String, String> rtnMap = convertMap(request.getParameterMap());
            if ("POST".equals(request.getMethod()) || "PUT".equals(request.getMethod())) {
                if (joinPoint.getArgs().length > 0) {
                    Object object = joinPoint.getArgs()[0];
                    rtnMap.put("RequestBody", JacksonUtil.obj2String(getKeyAndValue(object)));
                }
            }
            // 将参数所在的数组转换成json
            String params = JacksonUtil.obj2String(rtnMap);
            systemLogEntity.setRequParam(params);
            systemLogEntity.setRequAgent(request.getHeader("User-Agent"));
            systemLogEntity.setRequReferrer(request.getHeader("Referer"));
            if (!SystemTypeEnum.SYS_LOGS.equals(annotation.module())) {
                // 如果是查看日志请求，那么不记录返回内容，否则返回内容会刷新一次大一倍
                if (retObj != null) {
                    if (!(retObj instanceof ModelAndView)) {
                        systemLogEntity.setRespParam(JacksonUtil.obj2String(retObj));
                    }
                }
            }
            systemLogEntity.setLogTime(new Date());
            systemLogEntity.setLogDesc(annotation.desc());
            systemLogService.save(systemLogEntity);
        }
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

    private static Map<String, Object> getKeyAndValue(Object obj) {
        Map<String, Object> map = new HashMap<>();
        // 得到类对象
        Class<?> userCla = obj.getClass();
        /* 得到类中的所有属性集合 */
        Field[] fs = userCla.getDeclaredFields();
        for (Field f : fs) {
            // 设置些属性是可以访问的
            f.setAccessible(true);
            Object val;
            try {
                val = f.get(obj);
                // 得到此属性的值
                // 设置键值
                map.put(f.getName(), val);
            } catch (IllegalArgumentException | IllegalAccessException e) {
                logger.error(e.getMessage(), e);
            }

        }
        return map;
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
