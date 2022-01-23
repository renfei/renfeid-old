package net.renfei.controllers._api;

import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.tags.Tag;
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
@Tag(name = "定时任务接口", description = "定时任务接口")
public interface QuartzServiceApi {
    /**
     * 添加一个定时任务
     *
     * @param quartzJob
     */
    @PostMapping("job")
    @Operation(summary = "添加定时任务", tags = {"定时任务接口"})
    APIResult addJob(@RequestBody QuartzJob quartzJob);

    /**
     * 暂停一个定时任务
     *
     * @param jobName  任务名称
     * @param jobGroup 任务组名称
     */
    @PutMapping("job/pause")
    @Operation(summary = "暂停定时任务", tags = {"定时任务接口"})
    APIResult pauseJob(String jobName, String jobGroup);

    /**
     * 继续一个定时任务
     *
     * @param jobName  任务名称
     * @param jobGroup 任务组名称
     */
    @PutMapping("job/resume")
    @Operation(summary = "继续定时任务", tags = {"定时任务接口"})
    APIResult resumeJob(String jobName, String jobGroup);

    /**
     * 更新一个定时任务
     *
     * @param jobName  任务名称
     * @param jobGroup 任务组名称
     * @param cron     定时表达式
     */
    @PutMapping("job")
    @Operation(summary = "更新定时任务", tags = {"定时任务接口"})
    APIResult updateJob(String jobName, String jobGroup, String cron);
}
