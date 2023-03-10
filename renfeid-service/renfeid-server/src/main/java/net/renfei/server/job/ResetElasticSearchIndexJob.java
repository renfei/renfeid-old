package net.renfei.server.job;

import net.renfei.common.core.service.EmailService;
import net.renfei.common.search.entity.SearchItem;
import net.renfei.common.search.service.SearchService;
import net.renfei.server.service.AggregateService;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.scheduling.annotation.Scheduled;
import org.springframework.stereotype.Component;

import java.util.ArrayList;
import java.util.List;

/**
 * 定时任务：更新搜索引擎的索引内容
 *
 * @author renfei
 */
@Component
public class ResetElasticSearchIndexJob {
    private final static Logger logger = LoggerFactory.getLogger(ResetElasticSearchIndexJob.class);
    private final EmailService emailService;
    private final SearchService searchService;
    private final AggregateService aggregateService;

    public ResetElasticSearchIndexJob(EmailService emailService,
                                      SearchService searchService,
                                      AggregateService aggregateService) {
        this.emailService = emailService;
        this.searchService = searchService;
        this.aggregateService = aggregateService;
    }

    @Scheduled(cron = "0 10 3 * * *")
    public void executeInternal() {
        logger.info("ResetElasticSearchIndexJob Start.");
        try {
            List<SearchItem> searchItemAll = aggregateService.queryAllData(true);
            if (searchItemAll != null && !searchItemAll.isEmpty()) {
                // 先删除，再重建
                searchService.deleteIndex();
                searchService.createIndex(searchItemAll);
            }
        } catch (Exception exception) {
            List<String> data = new ArrayList<>();
            data.add("ResetElasticSearchIndexJob：更新搜索引擎");
            data.add("定时执行任务失败。");
            data.add("异常信息：");
            data.add(exception.getMessage());
            logger.error("ResetElasticSearchIndexJob：更新搜索引擎", exception);
            emailService.send("i@renfei.net", "RenFei", "定时任务【ResetElasticSearchIndexJob】执行失败通知", data);
        }
        logger.info("ResetElasticSearchIndexJob End.");
    }
}
