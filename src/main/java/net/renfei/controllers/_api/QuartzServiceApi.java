package net.renfei.controllers._api;

import net.renfei.model.APIResult;
import net.renfei.model.system.QuartzJob;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;

/**
 * 定时任务服务接口
 *
 * @author renfei
 */
@RequestMapping("/_/api/quartz")
public interface QuartzServiceApi {
    /**
     * 添加一个定时任务
     *
     * @param quartzJob
     */
    @PostMapping("job")
    APIResult addJob(@RequestBody QuartzJob quartzJob);

    /**
     * 暂停一个定时任务
     *
     * @param jobName  任务名称
     * @param jobGroup 任务组名称
     */
    @PutMapping("job/pause")
    APIResult pauseJob(String jobName, String jobGroup);

    /**
     * 继续一个定时任务
     *
     * @param jobName  任务名称
     * @param jobGroup 任务组名称
     */
    @PutMapping("job/resume")
    APIResult resumeJob(String jobName, String jobGroup);

    /**
     * 更新一个定时任务
     *
     * @param jobName  任务名称
     * @param jobGroup 任务组名称
     * @param cron     定时表达式
     */
    @PutMapping("job")
    APIResult updateJob(String jobName, String jobGroup, String cron);
}
