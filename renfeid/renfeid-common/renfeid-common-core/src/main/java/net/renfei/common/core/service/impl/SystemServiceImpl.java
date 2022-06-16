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

import net.renfei.common.api.constant.enums.SystemSettingEnum;
import net.renfei.common.api.constant.enums.SystemStatusEnum;
import net.renfei.common.core.entity.LogLevelEnum;
import net.renfei.common.core.entity.OperationTypeEnum;
import net.renfei.common.core.entity.SystemTypeEnum;
import net.renfei.common.core.repositories.CoreSystemSettingMapper;
import net.renfei.common.core.repositories.entity.CoreSystemSetting;
import net.renfei.common.core.repositories.entity.CoreSystemSettingExample;
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
import org.springframework.transaction.annotation.Transactional;

import javax.annotation.PreDestroy;
import javax.servlet.http.HttpServletRequest;
import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.ArrayList;
import java.util.List;

/**
 * 系统级服务
 *
 * @author renfei
 */
@Service
public class SystemServiceImpl implements SystemService {
    private final static Logger logger = LoggerFactory.getLogger(SystemServiceImpl.class);
    private ApplicationContext context;
    private final ContextRefresher contextRefresher;
    private final SystemLogService systemLogService;
    private final CoreSystemSettingMapper coreSystemSettingMapper;

    public SystemServiceImpl(ContextRefresher contextRefresher,
                             SystemLogService systemLogService,
                             CoreSystemSettingMapper coreSystemSettingMapper) {
        this.contextRefresher = contextRefresher;
        this.systemLogService = systemLogService;
        this.coreSystemSettingMapper = coreSystemSettingMapper;
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
    public List<String> querySystemSetting(SystemSettingEnum settingEnum) {
        CoreSystemSettingExample example = new CoreSystemSettingExample();
        example.createCriteria().andSettingKeyEqualTo(settingEnum.toString());
        List<CoreSystemSetting> systemSettings = coreSystemSettingMapper.selectByExample(example);
        List<String> stringList = new ArrayList<>();
        for (CoreSystemSetting systemSetting : systemSettings
        ) {
            stringList.add(systemSetting.getSettingValue());
        }
        return stringList;
    }

    @Override
    @Transactional(rollbackFor = Exception.class)
    public void updateSystemSetting(SystemSettingEnum settingEnum, List<String> values) {
        CoreSystemSettingExample example = new CoreSystemSettingExample();
        example.createCriteria().andSettingKeyEqualTo(settingEnum.toString());
        coreSystemSettingMapper.deleteByExample(example);
        for (String value : values
        ) {
            CoreSystemSetting coreSystemSetting = new CoreSystemSetting();
            coreSystemSetting.setSettingKey(settingEnum.toString());
            coreSystemSetting.setSettingValue(value);
            coreSystemSettingMapper.insertSelective(coreSystemSetting);
        }
    }

    @Override
    public SystemStatusEnum querySystemRunningStatus() {
        List<String> strings = this.querySystemSetting(SystemSettingEnum.SYSTEM_RUNNING_STATUS);
        if (strings.isEmpty()) {
            return SystemStatusEnum.OPENED;
        } else {
            return SystemStatusEnum.valueOf(strings.get(0));
        }
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
