package net.renfei.console.controller.system.impl;

import net.renfei.annotation.OperationLog;
import net.renfei.controller.BaseController;
import net.renfei.console.controller.system.QuartzServiceApi;
import net.renfei.model.APIResult;
import net.renfei.model.StateCodeEnum;
import net.renfei.model.log.OperationTypeEnum;
import net.renfei.model.system.QuartzJob;
import net.renfei.model.system.SystemTypeEnum;
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
    @OperationLog(module = SystemTypeEnum.API, desc = "创建一个定时任务", operation = OperationTypeEnum.CREATE)
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
    @OperationLog(module = SystemTypeEnum.API, desc = "暂停一个定时任务", operation = OperationTypeEnum.UPDATE)
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
    @OperationLog(module = SystemTypeEnum.API, desc = "继续一个定时任务", operation = OperationTypeEnum.UPDATE)
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
    @OperationLog(module = SystemTypeEnum.API, desc = "更新一个定时任务", operation = OperationTypeEnum.UPDATE)
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
