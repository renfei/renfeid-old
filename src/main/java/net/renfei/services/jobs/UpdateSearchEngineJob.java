package net.renfei.services.jobs;

import lombok.extern.slf4j.Slf4j;
import net.renfei.services.EmailService;
import net.renfei.services.SearchService;
import org.quartz.JobExecutionContext;
import org.quartz.JobExecutionException;
import org.springframework.scheduling.quartz.QuartzJobBean;

import java.util.ArrayList;
import java.util.List;

/**
 * 每天凌晨3点半执行更新搜索引擎
 * 0 30 3 * * ?
 *
 * @author renfei
 */
@Slf4j
public class UpdateSearchEngineJob extends QuartzJobBean {
    private final EmailService emailService;
    private final SearchService searchService;

    public UpdateSearchEngineJob(EmailService emailService, SearchService searchService) {
        this.emailService = emailService;
        this.searchService = searchService;
    }

    @Override
    protected void executeInternal(JobExecutionContext context) throws JobExecutionException {
        log.info("== UpdateSearchEngineJob >>>>");
        try {
            searchService.deleteIndex();
            searchService.createIndex();
        } catch (Exception exception) {
            List<String> data = new ArrayList<>();
            data.add("UpdateSearchEngineJob：更新搜索引擎");
            data.add("定时执行任务失败。");
            data.add("异常信息：");
            data.add(exception.getMessage());
            log.error("UpdateSearchEngineJob：更新搜索引擎", exception);
            emailService.send("i@renfei.net", "RenFei", "定时任务【UpdateSearchEngineJob】执行失败通知", data);
        }
        log.info("== UpdateSearchEngineJob <<<<");
    }
}
