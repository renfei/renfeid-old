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
package net.renfei.cms.jobs;

import net.renfei.cms.api.PostService;
import net.renfei.common.core.service.EmailService;
import org.quartz.JobExecutionContext;
import org.quartz.JobExecutionException;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.scheduling.quartz.QuartzJobBean;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;

/**
 * 定时任务：更新博客文章的排名指数
 *
 * @author renfei
 */
@Service
public class UpdateBlogPageRankJob extends QuartzJobBean {
    private static final Logger logger = LoggerFactory.getLogger(UpdateBlogPageRankJob.class);
    private final PostService postService;
    private final EmailService emailService;

    public UpdateBlogPageRankJob(PostService postService,
                                 EmailService emailService) {
        this.postService = postService;
        this.emailService = emailService;
    }

    @Override
    protected void executeInternal(JobExecutionContext context) throws JobExecutionException {
        executeInternal();
    }

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
