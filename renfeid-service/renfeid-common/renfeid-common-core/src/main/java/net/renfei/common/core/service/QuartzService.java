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
package net.renfei.common.core.service;

import net.renfei.common.api.constant.APIResult;
import net.renfei.common.core.entity.CronJobAo;
import net.renfei.common.core.entity.CronJobVo;

import java.util.List;

/**
 * Quartz 定时任务服务
 *
 * @author renfei
 */
public interface QuartzService {
    /**
     * 获取任务列表
     *
     * @return
     */
    APIResult<List<CronJobVo>> queryJobList();

    /**
     * 添加定时任务
     *
     * @param jobName   任务名称
     * @param jobGroup  任务组名
     * @param cronJobAo 任务请求对象
     * @return
     */
    APIResult createJob(String jobName, String jobGroup, CronJobAo cronJobAo);

    /**
     * 重排定时任务
     *
     * @param jobName  任务名称
     * @param jobGroup 任务组名
     * @param cron     定时表达式
     * @return
     */
    APIResult rescheduleJob(String jobName, String jobGroup, String cron);

    /**
     * 暂停定时任务
     *
     * @param jobName  任务名称
     * @param jobGroup 任务组名
     * @return
     */
    APIResult pauseJob(String jobName, String jobGroup);

    /**
     * 恢复定时任务
     *
     * @param jobName  任务名称
     * @param jobGroup 任务组名
     * @return
     */
    APIResult resumeJob(String jobName, String jobGroup);

    /**
     * 删除定时任务
     *
     * @param jobName  任务名称
     * @param jobGroup 任务组名
     * @return
     */
    APIResult deleteJob(String jobName, String jobGroup);
}
