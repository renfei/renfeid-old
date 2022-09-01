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

import net.renfei.cms.api.PostService;
import net.renfei.cms.api.entity.Post;
import net.renfei.common.api.constant.APIResult;
import net.renfei.common.api.entity.ListData;
import net.renfei.common.api.utils.StringUtils;
import net.renfei.common.core.config.SystemConfig;
import net.renfei.common.search.entity.SearchItem;
import net.renfei.common.search.entity.TypeEnum;
import net.renfei.proprietary.discuz.repositories.entity.DiscuzForumPostDO;
import net.renfei.proprietary.discuz.service.DiscuzService;
import org.springframework.stereotype.Service;
import org.springframework.util.ObjectUtils;

import java.util.ArrayList;
import java.util.Date;
import java.util.List;
import java.util.UUID;

/**
 * 聚合服务 所有数据
 *
 * @author renfei
 */
@Service
public class AggregateService {
    private final PostService postService;
    private final SystemConfig systemConfig;
    private final DiscuzService discuzService;

    public AggregateService(PostService postService,
                            SystemConfig systemConfig,
                            DiscuzService discuzService) {
        this.postService = postService;
        this.systemConfig = systemConfig;
        this.discuzService = discuzService;
    }

    public List<SearchItem> queryAllData(boolean haveDiscuz) {
        List<SearchItem> searchItemAll = new ArrayList<>();
        // == Post
        APIResult<ListData<Post>> postList =
                postService.queryPostList(null, null, 1, Integer.MAX_VALUE, false, "post_date DESC");
        if (postList.getCode() == 200
                && postList.getData() != null
                && postList.getData().getData() != null
                && !postList.getData().getData().isEmpty()) {
            for (Post post : postList.getData().getData()
            ) {
                SearchItem searchItem = new SearchItem();
                searchItem.setUuid(UUID.randomUUID().toString().toUpperCase());
                searchItem.setType(TypeEnum.POSTS.getName());
                searchItem.setTitle(post.getPostTitle());
                searchItem.setContent(StringUtils.delHtmlTags(post.getPostContent()));
                searchItem.setImage(getImgUrl(post.getFeaturedImage()));
                searchItem.setUrl(systemConfig.getSiteDomainName() + TypeEnum.POSTS.getUrl() + "/" + post.getId());
                searchItem.setOriginalId(Long.parseLong(post.getId()));
                searchItem.setDate(post.getPostDate());
                searchItemAll.add(searchItem);
            }
        }
        // 微博、相册、工具箱...
        // == Discuz
        if(haveDiscuz){
            List<DiscuzForumPostDO> discuzForumPostList = discuzService.getAllPost();
            if (!ObjectUtils.isEmpty(discuzForumPostList)) {
                for (DiscuzForumPostDO post : discuzForumPostList
                ) {
                    SearchItem searchItem = new SearchItem();
                    searchItem.setUuid(UUID.randomUUID().toString().toUpperCase());
                    searchItem.setType(TypeEnum.DISCUZ.getName());
                    searchItem.setTitle(post.getSubject());
                    searchItem.setContent(StringUtils.delHtmlTags(post.getMessage()));
                    searchItem.setImage(getImgUrl(null));
                    searchItem.setUrl(TypeEnum.DISCUZ.getUrl() + "/thread-" + post.getTid() + "-1-1.html");
                    searchItem.setOriginalId(Long.valueOf(post.getTid()));
                    searchItem.setDate(new Date((long) post.getDateline() * 1000));
                    searchItemAll.add(searchItem);
                }
            }
        }
        return searchItemAll;
    }

    private String getImgUrl(String url) {
        if (ObjectUtils.isEmpty(url)) {
            return "https://cdn.renfei.net/images/default_posts.jpg";
        }
        return url;
    }
}
