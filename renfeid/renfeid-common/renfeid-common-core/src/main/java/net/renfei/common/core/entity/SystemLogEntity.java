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
package net.renfei.common.core.entity;

import lombok.Data;

import java.util.Date;

/**
 * 系统日志实体
 *
 * @author renfei
 */
@Data
public class SystemLogEntity {
    private Long id;
    private Date logTime;
    private String logLevel;
    private String logModule;
    private String logType;
    private String requMethod;
    private String requUri;
    private String requReferrer;
    private String userUuid;
    private String userName;
    private String requIp;
    private String logDesc;
    private String requParam;
    private String respParam;
    private String requAgent;
}
