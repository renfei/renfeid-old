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

import net.renfei.common.api.constant.APIResult;
import net.renfei.common.api.constant.enums.StateCodeEnum;
import net.renfei.common.core.entity.CronJobAo;
import net.renfei.common.core.entity.CronJobVo;
import net.renfei.common.core.service.QuartzService;
import org.quartz.*;
import org.quartz.impl.matchers.GroupMatcher;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Service;
import org.springframework.util.ObjectUtils;

import java.util.*;

/**
 * Quartz 定时任务服务
 *
 * @author renfei
 */
@Service
public class QuartzServiceImpl implements QuartzService {
    private final static Logger logger = LoggerFactory.getLogger(QuartzServiceImpl.class);
    private final Scheduler scheduler;

    public QuartzServiceImpl(Scheduler scheduler) {
        this.scheduler = scheduler;
    }

    /**
     * 获取任务列表
     *
     * @return
     */
    @Override
    public APIResult<List<CronJobVo>> queryJobList() {
        List<CronJobVo> cronJobVoList = new ArrayList<>();
        try {
            for (String groupName : scheduler.getJobGroupNames()) {
                for (JobKey jobKey : scheduler.getJobKeys(GroupMatcher.jobGroupEquals(groupName))) {
                    String jobName = jobKey.getName();
                    String jobGroup = jobKey.getGroup();
                    JobDetail jobDetail = scheduler.getJobDetail(jobKey);
                    List<Trigger> triggers = (List<Trigger>) scheduler.getTriggersOfJob(jobKey);
                    for (Trigger trigger : triggers
                    ) {
                        Date nextFireTime = trigger.getNextFireTime();
                        CronJobVo cronJobVo = new CronJobVo();
                        cronJobVo.setJobName(jobName);
                        cronJobVo.setGroupName(jobGroup);
                        cronJobVo.setClassName(jobDetail.getJobClass().getName());
                        cronJobVo.setCronExpression(((CronTrigger) trigger).getCronExpression());
                        cronJobVo.setState(scheduler.getTriggerState(trigger.getKey()).toString());
                        cronJobVo.setNextFireTime(nextFireTime);
                        cronJobVo.setParam(jobDetail.getJobDataMap());
                        cronJobVoList.add(cronJobVo);
                    }
                }
            }
        } catch (SchedulerException e) {
            logger.error(e.getMessage(), e);
            return APIResult.builder()
                    .code(StateCodeEnum.Error)
                    .message("内部服务器错误，暂时无法提供服务，请稍后再试。")
                    .build();
        }
        return new APIResult<>(cronJobVoList);
    }

    /**
     * 添加定时任务
     *
     * @param jobName   任务名称
     * @param jobGroup  任务组名
     * @param cronJobAo 任务请求对象
     * @return
     */
    @Override
    public APIResult createJob(String jobName, String jobGroup, CronJobAo cronJobAo) {
        if (ObjectUtils.isEmpty(jobName)) {
            return APIResult.builder()
                    .code(StateCodeEnum.Failure)
                    .message("任务名[jobName]不能为空！")
                    .build();
        }
        if (ObjectUtils.isEmpty(jobGroup)) {
            return APIResult.builder()
                    .code(StateCodeEnum.Failure)
                    .message("任务组名[jobGroup]不能为空！")
                    .build();
        }
        if (ObjectUtils.isEmpty(cronJobAo.getClassName())) {
            return APIResult.builder()
                    .code(StateCodeEnum.Failure)
                    .message("任务类名[className]不能为空！")
                    .build();
        }
        if (ObjectUtils.isEmpty(cronJobAo.getCronExpression())) {
            return APIResult.builder()
                    .code(StateCodeEnum.Failure)
                    .message("定时表达式[cronExpression]不能为空！")
                    .build();
        }
        Class<? extends Job> clazz = null;
        try {
            if (Job.class.isAssignableFrom(Class.forName(cronJobAo.getClassName()))) {
                clazz = (Class<? extends Job>) Class.forName(cronJobAo.getClassName());
            } else {
                logger.info("任务类名[{}]未实现[org.quartz.Job]接口，无法作为定时任务进行执行。", cronJobAo.getClassName());
                return APIResult.builder()
                        .code(StateCodeEnum.Failure)
                        .message(String.format("任务类名[%s]未实现[org.quartz.Job]接口，无法作为定时任务进行执行。", cronJobAo.getClassName()))
                        .build();
            }
        } catch (ClassNotFoundException e) {
            logger.info("任务类名[{}]未找到。", cronJobAo.getClassName());
            return APIResult.builder()
                    .code(StateCodeEnum.Failure)
                    .message(String.format("任务类名[%s]未找到。", cronJobAo.getClassName()))
                    .build();
        }
        JobDetail jobDetail = JobBuilder.newJob(clazz)
                .withIdentity(jobName, jobGroup)
                .build();
        if (!ObjectUtils.isEmpty(cronJobAo.getParam())) {
            cronJobAo.getParam().forEach((key, value) -> jobDetail.getJobDataMap().put(key, value));
        }
        try {
            scheduler.scheduleJob(jobDetail, buildCronTrigger(jobName, jobGroup, cronJobAo.getCronExpression()));
            if (!scheduler.isStarted()) {
                scheduler.start();
            }
        } catch (SchedulerException e) {
            logger.error(e.getMessage(), e);
            return APIResult.builder()
                    .code(StateCodeEnum.Error)
                    .message("内部服务器错误，暂时无法提供服务，请稍后再试。")
                    .build();
        }
        return APIResult.success();
    }

    /**
     * 重排定时任务
     *
     * @param jobName  任务名称
     * @param jobGroup 任务组名
     * @param cron     定时表达式
     * @return
     */
    @Override
    public APIResult rescheduleJob(String jobName, String jobGroup, String cron) {
        TriggerKey triggerKey = TriggerKey.triggerKey(jobName, jobGroup);
        try {
            scheduler.rescheduleJob(triggerKey, buildCronTrigger(jobName, jobGroup, cron));
            if (!scheduler.isStarted()) {
                scheduler.start();
            }
        } catch (SchedulerException e) {
            logger.error(e.getMessage(), e);
            return APIResult.builder()
                    .code(StateCodeEnum.Error)
                    .message("内部服务器错误，暂时无法提供服务，请稍后再试。")
                    .build();
        }
        return APIResult.success();
    }

    /**
     * 暂停定时任务
     *
     * @param jobName  任务名称
     * @param jobGroup 任务组名
     * @return
     */
    @Override
    public APIResult pauseJob(String jobName, String jobGroup) {
        try {
            scheduler.pauseJob(JobKey.jobKey(jobName, jobGroup));
            if (!scheduler.isStarted()) {
                scheduler.start();
            }
        } catch (SchedulerException e) {
            logger.error(e.getMessage(), e);
            return APIResult.builder()
                    .code(StateCodeEnum.Error)
                    .message("内部服务器错误，暂时无法提供服务，请稍后再试。")
                    .build();
        }
        return APIResult.success();
    }

    /**
     * 恢复定时任务
     *
     * @param jobName  任务名称
     * @param jobGroup 任务组名
     * @return
     */
    @Override
    public APIResult resumeJob(String jobName, String jobGroup) {
        try {
            scheduler.resumeJob(JobKey.jobKey(jobName, jobGroup));
            if (!scheduler.isStarted()) {
                scheduler.start();
            }
        } catch (SchedulerException e) {
            logger.error(e.getMessage(), e);
            return APIResult.builder()
                    .code(StateCodeEnum.Error)
                    .message("内部服务器错误，暂时无法提供服务，请稍后再试。")
                    .build();
        }
        return APIResult.success();
    }

    /**
     * 删除定时任务
     *
     * @param jobName  任务名称
     * @param jobGroup 任务组名
     * @return
     */
    @Override
    public APIResult deleteJob(String jobName, String jobGroup) {
        TriggerKey triggerKey = TriggerKey.triggerKey(jobName, jobGroup);
        try {
            scheduler.deleteJob(JobKey.jobKey(jobName, jobGroup));
            scheduler.unscheduleJob(triggerKey);
            if (!scheduler.isStarted()) {
                scheduler.start();
            }
        } catch (SchedulerException e) {
            logger.error(e.getMessage(), e);
            return APIResult.builder()
                    .code(StateCodeEnum.Error)
                    .message("内部服务器错误，暂时无法提供服务，请稍后再试。")
                    .build();
        }
        return APIResult.success();
    }

    private Trigger buildCronTrigger(String jobName, String jobGroup, String cron) {
        CronScheduleBuilder scheduleBuilder = CronScheduleBuilder.cronSchedule(cron);
        return TriggerBuilder.newTrigger()
                .withIdentity(jobName, jobGroup).withSchedule(scheduleBuilder)
                .build();
    }
}
