package net.renfei.cms.jobs;

import net.renfei.cms.api.PostService;
import net.renfei.common.core.service.EmailService;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.scheduling.annotation.Scheduled;
import org.springframework.stereotype.Component;

import java.util.ArrayList;
import java.util.List;

/**
 * 定时任务：更新博客文章的排名指数
 *
 * @author renfei
 */
@Component
public class UpdateBlogPageRankJob {
    private static final Logger logger = LoggerFactory.getLogger(UpdateBlogPageRankJob.class);
    private final PostService postService;
    private final EmailService emailService;

    public UpdateBlogPageRankJob(PostService postService,
                                 EmailService emailService) {
        this.postService = postService;
        this.emailService = emailService;
    }

    @Scheduled(cron = "0 20 2 * * *")
    public void executeInternal() {
        logger.info("UpdateBlogPageRankJob Start.");
        try {
            postService.updatePageRank();
        } catch (Exception exception) {
            List<String> data = new ArrayList<>();
            data.add("UpdateBlogPageRankJob：更新文章评级");
            data.add("定时执行任务失败。");
            data.add("异常信息：");
            data.add(exception.getMessage());
            logger.error("UpdateBlogPageRankJob：更新文章评级", exception);
            emailService.send("i@renfei.net", "RenFei", "定时任务【UpdateBlogPageRankJob】执行失败通知", data);
        }
        logger.info("UpdateBlogPageRankJob End.");
    }
}
