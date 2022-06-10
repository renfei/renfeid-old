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
package net.renfei.common.core.service.impl;

import net.renfei.common.core.entity.LogLevelEnum;
import net.renfei.common.core.entity.OperationTypeEnum;
import net.renfei.common.core.entity.SystemTypeEnum;
import net.renfei.common.core.service.SystemLogService;
import net.renfei.common.core.service.SystemService;
import net.renfei.uaa.api.entity.UserDetail;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.BeansException;
import org.springframework.cloud.context.refresh.ContextRefresher;
import org.springframework.context.ApplicationContext;
import org.springframework.context.ConfigurableApplicationContext;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContext;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.stereotype.Service;

import javax.annotation.PreDestroy;
import javax.servlet.http.HttpServletRequest;
import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;

/**
 * 系统级服务
 *
 * @author renfei
 */
@Service
public class SystemServiceImpl implements SystemService {
    private final static Logger logger = LoggerFactory.getLogger(SystemServiceImpl.class);
    private final ContextRefresher contextRefresher;
    private final SystemLogService systemLogService;
    private ApplicationContext context;

    public SystemServiceImpl(ContextRefresher contextRefresher,
                             SystemLogService systemLogService) {
        this.contextRefresher = contextRefresher;
        this.systemLogService = systemLogService;
    }

    /**
     * 【危险】在主机上执行命令
     *
     * @param command 命令
     * @return
     * @throws IOException
     */
    @Override
    public String execCommand(String... command) throws IOException {
        Process process = Runtime.getRuntime().exec(command);
        BufferedReader br = new BufferedReader(new InputStreamReader(process.getInputStream()));
        StringBuilder sb = new StringBuilder();
        String line;
        while ((line = br.readLine()) != null) {
            sb.append(line).append("\n");
        }
        return sb.toString();
    }

    /**
     * 刷新系统配置（无需停机更新配置）
     */
    @Override
    public void refreshConfiguration() {
        contextRefresher.refresh();
    }

    /**
     * 【危险】关闭系统，主动退出
     */
    @Override
    public void shutdownSystem(HttpServletRequest request) {
        UserDetail userDetail = this.currentUserDetail();
        String userUuid = null, username = null;
        if (userDetail != null) {
            userUuid = userDetail.getUuid();
            username = userDetail.getUsername();
        }
        systemLogService.save(LogLevelEnum.FATAL, SystemTypeEnum.SYSTEM, OperationTypeEnum.DELETE,
                "系统停车！！！ System shutdown!!!", userUuid, username, request);
        ConfigurableApplicationContext ctx = (ConfigurableApplicationContext) context;
        ctx.close();
    }

    /**
     * 获取当前登陆的用户详情
     *
     * @return
     */
    @Override
    public UserDetail currentUserDetail() {
        SecurityContext securityContext = SecurityContextHolder.getContext();
        if (securityContext != null) {
            Authentication authentication = securityContext.getAuthentication();
            if (authentication != null) {
                Object principal = authentication.getPrincipal();
                if (principal != null) {
                    if (principal instanceof UserDetail) {
                        return (UserDetail) principal;
                    }
                }
            }
        }
        return null;
    }

    @Override
    public void setApplicationContext(ApplicationContext applicationContext) throws BeansException {
        context = applicationContext;
    }

    @PreDestroy
    public void preDestroy() {
        systemLogService.save(LogLevelEnum.FATAL, SystemTypeEnum.SYSTEM, OperationTypeEnum.DELETE,
                "系统停车！！！ System shutdown!!!", null, null, null);
        logger.error("系统停车！！！ System shutdown!!!");
    }

}
