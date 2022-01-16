package net.renfei.services.jobs;

import net.renfei.services.EmailService;
import net.renfei.services.SearchService;
import org.quartz.JobExecutionContext;
import org.quartz.JobExecutionException;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.scheduling.quartz.QuartzJobBean;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;

/**
 * 每天凌晨3点半执行更新搜索引擎
 * 0 30 3 * * ?
 *
 * @author renfei
 */
@Service
public class UpdateSearchEngineJob extends QuartzJobBean {
    private static final Logger logger = LoggerFactory.getLogger(UpdateSearchEngineJob.class);
    private final EmailService emailService;
    private final SearchService searchService;

    public UpdateSearchEngineJob(EmailService emailService, SearchService searchService) {
        this.emailService = emailService;
        this.searchService = searchService;
    }

    @Override
    protected void executeInternal(JobExecutionContext context) throws JobExecutionException {
        logger.info("== UpdateSearchEngineJob >>>>");
        try {
            searchService.deleteIndex();
            searchService.createIndex();
        } catch (Exception exception) {
            List<String> data = new ArrayList<>();
            data.add("UpdateSearchEngineJob：更新搜索引擎");
            data.add("定时执行任务失败。");
            data.add("异常信息：");
            data.add(exception.getMessage());
            logger.error("UpdateSearchEngineJob：更新搜索引擎", exception);
            emailService.send("i@renfei.net", "RenFei", "定时任务【UpdateSearchEngineJob】执行失败通知", data);
        }
        logger.info("== UpdateSearchEngineJob <<<<");
    }
}
