/*
 *   Copyright 2022 RenFei(i@renfei.net)
 *
 * Licensed under the Apache License, Version 2.0 (the "License");
 * you may not use this file except in compliance with the License.
 * You may obtain a copy of the License at
 *
 *   http://www.apache.org/licenses/LICENSE-2.0
 *
 * Unless required by applicable law or agreed to in writing, software
 * distributed under the License is distributed on an "AS IS" BASIS,
 * WITHOUT WARRANTIES OR CONDITIONS OF ANY KIND, either express or implied.
 * See the License for the specific language governing permissions and
 * limitations under the License.
 */
package net.renfei.server.job;

import net.renfei.common.core.service.EmailService;
import net.renfei.common.search.entity.SearchItem;
import net.renfei.common.search.service.SearchService;
import net.renfei.server.service.AggregateService;
import org.quartz.JobExecutionContext;
import org.quartz.JobExecutionException;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.scheduling.quartz.QuartzJobBean;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;

/**
 * 定时任务：更新搜索引擎的索引内容
 *
 * @author renfei
 */
@Service
public class ResetElasticSearchIndexJob extends QuartzJobBean {
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

    @Override
    protected void executeInternal(JobExecutionContext context) throws JobExecutionException {
        executeInternal();
    }

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
