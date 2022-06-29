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
import net.renfei.common.core.entity.SystemLogEntity;
import net.renfei.common.core.entity.SystemTypeEnum;
import net.renfei.common.core.repositories.CoreLogsMapper;
import net.renfei.common.core.repositories.entity.CoreLogsWithBLOBs;
import net.renfei.common.core.service.SystemLogService;
import net.renfei.common.core.utils.IpUtils;
import org.springframework.beans.BeanUtils;
import org.springframework.stereotype.Service;

import javax.servlet.http.HttpServletRequest;
import java.util.Date;

/**
 * 系统审计日志服务
 *
 * @author renfei
 */
@Service
public class SystemLogServiceImpl implements SystemLogService {
    private final CoreLogsMapper coreLogsMapper;

    public SystemLogServiceImpl(CoreLogsMapper coreLogsMapper) {
        this.coreLogsMapper = coreLogsMapper;
    }

    @Override
    public void save(SystemLogEntity systemLogEntity) {
        CoreLogsWithBLOBs logs = new CoreLogsWithBLOBs();
        BeanUtils.copyProperties(systemLogEntity, logs);
        logs.setId(null);
        if (logs.getLogTime() == null) {
            logs.setLogTime(new Date());
        }
        coreLogsMapper.insertSelective(logs);
    }

    @Override
    public void save(LogLevelEnum logLevel, SystemTypeEnum systemType, OperationTypeEnum operationType,
                     String desc, String userUuid, String username, HttpServletRequest request) {
        CoreLogsWithBLOBs logs = new CoreLogsWithBLOBs();
        logs.setLogTime(new Date());
        logs.setLogLevel(logLevel.toString());
        logs.setLogModule(systemType.toString());
        logs.setLogType(operationType.toString());
        logs.setLogDesc(desc);
        logs.setUserUuid(userUuid);
        logs.setUserName(username);
        if (request != null) {
            logs.setRequMethod(request.getMethod());
            logs.setRequUri(request.getRequestURI());
            logs.setRequIp(IpUtils.getIpAddress(request));
            logs.setRequAgent(request.getHeader("User-Agent"));
            logs.setRequReferrer(request.getHeader("Referer"));
        }
        coreLogsMapper.insertSelective(logs);
    }
}
