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

import io.swagger.v3.oas.annotations.media.Schema;
import lombok.Data;

import java.io.Serializable;
import java.util.Date;
import java.util.Map;

/**
 * 定时任务请求对象
 *
 * @author renfei
 */
@Data
@Schema(title = "定时任务请求对象")
public class CronJobAo implements Serializable {
    private static final long serialVersionUID = -3316408227872898096L;
    @Schema(description = "定时任务实现类名（需要实现org.quartz.Job接口）")
    private String className;
    @Schema(description = "cron 定时表达式")
    private String cronExpression;
    @Schema(description = "定时任务实现类初始化参数")
    private Map<String, Object> param;
}
