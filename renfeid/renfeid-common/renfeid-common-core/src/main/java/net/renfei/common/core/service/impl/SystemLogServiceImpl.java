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

import net.renfei.common.core.entity.SystemLogEntity;
import net.renfei.common.core.repositories.CoreLogsMapper;
import net.renfei.common.core.repositories.entity.CoreLogsWithBLOBs;
import net.renfei.common.core.service.SystemLogService;
import org.springframework.beans.BeanUtils;
import org.springframework.stereotype.Service;

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
}
