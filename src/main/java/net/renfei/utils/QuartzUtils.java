package net.renfei.utils;

import org.quartz.*;

import java.util.Map;

/**
 * 定时任务的工具类
 *
 * @author renfei
 */
public class QuartzUtils {
    public static void createJob(Scheduler scheduler, Class<? extends Job> jobClass,
                                 String jobName, String jobGroup,
                                 String cron, Map<String, Object> param) throws SchedulerException {
        JobDetail jobDetail = JobBuilder.newJob(jobClass)
                .withIdentity(jobName, jobGroup)
                .build();
        if (param != null && !param.isEmpty()) {
            param.forEach((key, value) -> jobDetail.getJobDataMap().put(key, value));
        }
        scheduler.scheduleJob(jobDetail, buildCronTrigger(jobName, jobGroup, cron));
    }

    public static void refreshJob(Scheduler scheduler, String jobName, String jobGroup,
                                  String cron) throws SchedulerException {
        TriggerKey triggerKey = TriggerKey.triggerKey(jobName, jobGroup);
        scheduler.rescheduleJob(triggerKey, buildCronTrigger(jobName, jobGroup, cron));
    }

    private static Trigger buildCronTrigger(String jobName, String jobGroup, String cron) {
        CronScheduleBuilder scheduleBuilder = CronScheduleBuilder.cronSchedule(cron);
        return TriggerBuilder.newTrigger()
                .withIdentity(jobName, jobGroup).withSchedule(scheduleBuilder)
                .build();
    }

    public static void resumeJob(Scheduler scheduler, String jobName, String jobGroup) throws SchedulerException {
        scheduler.resumeJob(JobKey.jobKey(jobName, jobGroup));
    }

    public static void pauseJob(Scheduler scheduler, String jobName, String jobGroup) throws SchedulerException {
        scheduler.pauseJob(JobKey.jobKey(jobName, jobGroup));
    }

    public static void deleteJob(Scheduler scheduler, String jobName, String jobGroup) throws SchedulerException {
        scheduler.unscheduleJob(TriggerKey.triggerKey(jobName, jobGroup));
        scheduler.deleteJob(JobKey.jobKey(jobName, jobGroup));
    }
}
