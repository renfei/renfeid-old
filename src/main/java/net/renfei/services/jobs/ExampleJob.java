package net.renfei.services.jobs;

import lombok.extern.slf4j.Slf4j;
import org.quartz.JobExecutionContext;
import org.quartz.JobExecutionException;
import org.springframework.scheduling.quartz.QuartzJobBean;

/**
 * 定时任务演示
 *
 * @author renfei
 */
@Slf4j
public class ExampleJob extends QuartzJobBean {
    @Override
    protected void executeInternal(JobExecutionContext context) throws JobExecutionException {
        log.info("定时任务演示（net.renfei.services.jobs.ExampleJob）被运行。");
    }
}
