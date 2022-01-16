package net.renfei.controllers._api.impl;

import net.renfei.controllers.BaseController;
import net.renfei.controllers._api.QuartzServiceApi;
import net.renfei.model.APIResult;
import net.renfei.model.StateCodeEnum;
import net.renfei.model.system.QuartzJob;
import net.renfei.services.QuartzService;
import org.quartz.SchedulerException;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.web.bind.annotation.RestController;

/**
 * 定时任务服务接口
 *
 * @author renfei
 */
@RestController
public class QuartzServiceApiController extends BaseController implements QuartzServiceApi {
    private static final Logger logger = LoggerFactory.getLogger(QuartzServiceApiController.class);
    private final QuartzService quartzService;

    public QuartzServiceApiController(QuartzService quartzService) {
        this.quartzService = quartzService;
    }

    @Override
    public APIResult addJob(QuartzJob quartzJob) {
        try {
            quartzService.addJob(quartzJob);
        } catch (ClassNotFoundException e) {
            logger.error(e.getMessage(), e);
            return APIResult.builder()
                    .code(StateCodeEnum.Failure)
                    .message("Class不存在")
                    .build();
        } catch (SchedulerException e) {
            logger.error(e.getMessage(), e);
            return APIResult.builder()
                    .code(StateCodeEnum.Failure)
                    .message(e.getMessage())
                    .build();
        }
        return APIResult.success();
    }

    @Override
    public APIResult pauseJob(String jobName, String jobGroup) {
        try {
            quartzService.pauseJob(jobName, jobGroup);
        } catch (SchedulerException e) {
            logger.error(e.getMessage(), e);
            return APIResult.builder()
                    .code(StateCodeEnum.Failure)
                    .message(e.getMessage())
                    .build();
        }
        return APIResult.success();
    }

    @Override
    public APIResult resumeJob(String jobName, String jobGroup) {
        try {
            quartzService.resumeJob(jobName, jobGroup);
        } catch (SchedulerException e) {
            logger.error(e.getMessage(), e);
            return APIResult.builder()
                    .code(StateCodeEnum.Failure)
                    .message(e.getMessage())
                    .build();
        }
        return APIResult.success();
    }

    @Override
    public APIResult updateJob(String jobName, String jobGroup, String cron) {
        try {
            quartzService.updateJob(jobName, jobGroup, cron);
        } catch (SchedulerException e) {
            logger.error(e.getMessage(), e);
            return APIResult.builder()
                    .code(StateCodeEnum.Failure)
                    .message(e.getMessage())
                    .build();
        }
        return APIResult.success();
    }
}
