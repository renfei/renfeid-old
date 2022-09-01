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
package net.renfei.server.service;

import net.renfei.cms.api.PostCategoryService;
import net.renfei.cms.api.PostService;
import net.renfei.cms.api.PostTagService;
import net.renfei.cms.api.entity.Post;
import net.renfei.cms.api.entity.PostCategory;
import net.renfei.cms.api.entity.Tag;
import net.renfei.common.api.constant.APIResult;
import net.renfei.common.api.constant.enums.SecretLevelEnum;
import net.renfei.common.api.entity.ListData;
import net.renfei.common.core.config.SystemConfig;
import net.renfei.common.core.service.RedisService;
import net.renfei.common.search.entity.SearchItem;
import net.renfei.server.entity.ChangefreqEnum;
import net.renfei.server.entity.SiteMapXml;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Service;
import org.springframework.util.ObjectUtils;

import java.util.ArrayList;
import java.util.Date;
import java.util.List;

import static net.renfei.common.core.config.RedisConfig.REDIS_KEY_DATABASE;

/**
 * 网站地图服务
 *
 * @author renfei
 */
@Service
public class SiteMapService {
    private final static Logger logger = LoggerFactory.getLogger(SiteMapService.class);
    private final static String REDIS_KEY = REDIS_KEY_DATABASE + ":sitemap";
    private final PostService postService;
    private final SystemConfig systemConfig;
    private final RedisService redisService;
    private final PostTagService postTagService;
    private final AggregateService aggregateService;
    private final PostCategoryService postCategoryService;

    public SiteMapService(PostService postService,
                          SystemConfig systemConfig,
                          RedisService redisService,
                          PostTagService postTagService,
                          AggregateService aggregateService,
                          PostCategoryService postCategoryService) {
        this.postService = postService;
        this.systemConfig = systemConfig;
        this.redisService = redisService;
        this.postTagService = postTagService;
        this.aggregateService = aggregateService;
        this.postCategoryService = postCategoryService;
    }


    public List<SiteMapXml> querySiteMapXml() {
        List<SiteMapXml> siteMapXmlList = null;
        if (systemConfig.getEnableCache()) {
            // 启用了缓存
            if (redisService.hasKey(REDIS_KEY)) {
                Object object = redisService.get(REDIS_KEY);
                if (object instanceof List) {
                    siteMapXmlList = (List<SiteMapXml>) object;
                }
            }
        }
        if (siteMapXmlList == null) {
            siteMapXmlList = new ArrayList<>();
            Date lastDate = new Date();
            // 首页
            siteMapXmlList.add(new SiteMapXml(systemConfig.getSiteDomainName(), ChangefreqEnum.daily, "1", lastDate));
            // 文章
            APIResult<ListData<Post>> postList =
                    postService.queryPostList(null, null, 1, 1, true, "post_date DESC");
            if (postList.getCode() == 200
                    && postList.getData() != null
                    && postList.getData().getData() != null
                    && !postList.getData().getData().isEmpty()) {
                // 文章
                siteMapXmlList.add(new SiteMapXml(systemConfig.getSiteDomainName() + "/posts",
                        ChangefreqEnum.daily, "1", postList.getData().getData().get(0).getPostDate()));
            }
            // 微博、相册、工具箱...
            // 全部数据：注意数据量，可能内存溢出哦
            List<SearchItem> searchItems = aggregateService.queryAllData(false);
            if (!ObjectUtils.isEmpty(searchItems)) {
                for (SearchItem item : searchItems
                ) {
                    siteMapXmlList.add(new SiteMapXml(item.getUrl(), ChangefreqEnum.daily, "0.9", item.getDate()));
                }
            }
            // 文章分类
            APIResult<ListData<PostCategory>> listDataAPIResult =
                    postCategoryService.queryPostCategoryList(null, null,
                            SecretLevelEnum.UNCLASSIFIED, 1, Integer.MAX_VALUE);
            if (listDataAPIResult.getCode() == 200
                    && listDataAPIResult.getData() != null
                    && listDataAPIResult.getData().getData() != null
                    && !listDataAPIResult.getData().getData().isEmpty()) {
                for (PostCategory postCategory : listDataAPIResult.getData().getData()
                ) {
                    siteMapXmlList.add(new SiteMapXml(systemConfig.getSiteDomainName() + "/posts/cat/" + postCategory.getEnName(),
                            ChangefreqEnum.daily, "0.8", lastDate));
                }
            }
            // 文章标签
            List<Tag> tagList = postTagService.queryAllTag();
            if (tagList != null && !tagList.isEmpty()) {
                for (Tag tag : tagList
                ) {
                    siteMapXmlList.add(new SiteMapXml(systemConfig.getSiteDomainName() + "/posts/tag/" + tag.getEnName(),
                            ChangefreqEnum.daily, "0.8", lastDate));
                }
            }
            if (systemConfig.getEnableCache()) {
                redisService.set(REDIS_KEY, siteMapXmlList);
            }
        }
        return siteMapXmlList;
    }
}
