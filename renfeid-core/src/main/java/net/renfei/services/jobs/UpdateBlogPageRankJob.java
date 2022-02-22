package net.renfei.services.jobs;

import net.renfei.services.BlogService;
import net.renfei.services.EmailService;
import org.quartz.JobExecutionContext;
import org.quartz.JobExecutionException;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
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
@Service
public class UpdateBlogPageRankJob extends QuartzJobBean {
    private static final Logger logger = LoggerFactory.getLogger(UpdateBlogPageRankJob.class);
    private final BlogService blogService;
    private final EmailService emailService;

    public UpdateBlogPageRankJob(BlogService blogService, EmailService emailService) {
        this.blogService = blogService;
        this.emailService = emailService;
    }

    @Override
    protected void executeInternal(JobExecutionContext context) throws JobExecutionException {
        logger.info("== UpdatePostPageRankJob >>>>");
        try {
            blogService.updatePageRank();
        } catch (Exception exception) {
            List<String> data = new ArrayList<>();
            data.add("UpdatePostPageRankJob：更新文章评级");
            data.add("定时执行任务失败。");
            data.add("异常信息：");
            data.add(exception.getMessage());
            logger.error("UpdatePostPageRankJob：更新文章评级", exception);
            emailService.send("i@renfei.net", "RenFei", "定时任务【UpdatePostPageRankJob】执行失败通知", data);
        }
        logger.info("== UpdatePostPageRankJob <<<<");
    }
}
