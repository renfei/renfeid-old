package net.renfei.services.jobs;

import org.quartz.JobExecutionContext;
import org.quartz.JobExecutionException;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.scheduling.quartz.QuartzJobBean;

/**
 * 定时任务演示
 *
 * @author renfei
 */
public class ExampleJob extends QuartzJobBean {
    private static final Logger logger = LoggerFactory.getLogger(ExampleJob.class);

    @Override
    protected void executeInternal(JobExecutionContext context) throws JobExecutionException {
        logger.info("定时任务演示（net.renfei.services.jobs.ExampleJob）被运行。");
    }
}
