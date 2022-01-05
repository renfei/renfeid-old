package net.renfei.services.system;

import net.renfei.model.system.QuartzJob;
import net.renfei.services.BaseService;
import net.renfei.services.QuartzService;
import net.renfei.utils.QuartzUtils;
import org.quartz.Scheduler;
import org.quartz.SchedulerException;
import org.springframework.stereotype.Service;

/**
 * Quartz 定时任务服务
 *
 * @author renfei
 */
@Service
public class QuartzServiceImpl extends BaseService implements QuartzService {
    private final Scheduler scheduler;

    public QuartzServiceImpl(Scheduler scheduler) {
        this.scheduler = scheduler;
    }

    /**
     * 添加一个定时任务
     *
     * @param quartzJob
     * @throws ClassNotFoundException
     * @throws SchedulerException
     */
    @Override
    public void addJob(QuartzJob quartzJob) throws ClassNotFoundException, SchedulerException {
        Class clazz = Class.forName(quartzJob.getReference());
        QuartzUtils.createJob(scheduler, clazz, quartzJob.getJobName(), quartzJob.getJobGroup(), quartzJob.getCron(), quartzJob.getParam());
    }

    /**
     * 暂停一个定时任务
     *
     * @param jobName  任务名称
     * @param jobGroup 任务组名称
     * @throws SchedulerException
     */
    @Override
    public void pauseJob(String jobName, String jobGroup) throws SchedulerException {
        QuartzUtils.pauseJob(scheduler, jobName, jobGroup);
    }

    /**
     * 继续一个定时任务
     *
     * @param jobName  任务名称
     * @param jobGroup 任务组名称
     * @throws SchedulerException
     */
    @Override
    public void resumeJob(String jobName, String jobGroup) throws SchedulerException {
        QuartzUtils.resumeJob(scheduler, jobName, jobGroup);
    }

    /**
     * 更新一个定时任务
     *
     * @param jobName  任务名称
     * @param jobGroup 任务组名称
     * @param cron     定时表达式
     * @throws SchedulerException
     */
    @Override
    public void updateJob(String jobName, String jobGroup, String cron) throws SchedulerException {
        QuartzUtils.refreshJob(scheduler, jobName, jobGroup, cron);
    }
}
