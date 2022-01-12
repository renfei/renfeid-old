package net.renfei.services.jobs;

import lombok.extern.slf4j.Slf4j;
import net.renfei.services.BlogService;
import net.renfei.services.EmailService;
import org.quartz.JobExecutionContext;
import org.quartz.JobExecutionException;
import org.springframework.scheduling.quartz.QuartzJobBean;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;

/**
 * 每天凌晨2点半执行更新文章评级
 * 0 30 2 * * ?
 *
 * @author renfei
 */
@Slf4j
@Service
public class UpdateBlogPageRankJob extends QuartzJobBean {
    private final BlogService blogService;
    private final EmailService emailService;

    public UpdateBlogPageRankJob(BlogService blogService, EmailService emailService) {
        this.blogService = blogService;
        this.emailService = emailService;
    }

    @Override
    protected void executeInternal(JobExecutionContext context) throws JobExecutionException {
        log.info("== UpdatePostPageRankJob >>>>");
        try {
            blogService.updatePageRank();
        } catch (Exception exception) {
            List<String> data = new ArrayList<>();
            data.add("UpdatePostPageRankJob：更新文章评级");
            data.add("定时执行任务失败。");
            data.add("异常信息：");
            data.add(exception.getMessage());
            log.error("UpdatePostPageRankJob：更新文章评级", exception);
            emailService.send("i@renfei.net", "RenFei", "定时任务【UpdatePostPageRankJob】执行失败通知", data);
        }
        log.info("== UpdatePostPageRankJob <<<<");
    }
}
