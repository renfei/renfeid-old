package net.renfei.services;

import net.renfei.model.system.QuartzJob;
import org.quartz.SchedulerException;

/**
 * Quartz 定时任务服务
 *
 * @author renfei
 */
public interface QuartzService {
    /**
     * 添加一个定时任务
     *
     * @param quartzJob
     * @throws ClassNotFoundException
     * @throws SchedulerException
     */
    void addJob(QuartzJob quartzJob) throws ClassNotFoundException, SchedulerException;

    /**
     * 暂停一个定时任务
     *
     * @param jobName  任务名称
     * @param jobGroup 任务组名称
     * @throws SchedulerException
     */
    void pauseJob(String jobName, String jobGroup) throws SchedulerException;

    /**
     * 继续一个定时任务
     *
     * @param jobName  任务名称
     * @param jobGroup 任务组名称
     * @throws SchedulerException
     */
    void resumeJob(String jobName, String jobGroup) throws SchedulerException;

    /**
     * 更新一个定时任务
     *
     * @param jobName  任务名称
     * @param jobGroup 任务组名称
     * @param cron     定时表达式
     * @throws SchedulerException
     */
    void updateJob(String jobName, String jobGroup, String cron) throws SchedulerException;
}
