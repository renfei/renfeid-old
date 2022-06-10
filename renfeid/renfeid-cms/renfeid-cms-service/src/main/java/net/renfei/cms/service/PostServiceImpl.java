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
package net.renfei.cms.service;

import com.github.pagehelper.Page;
import com.github.pagehelper.PageHelper;
import net.renfei.cms.api.PostService;
import net.renfei.cms.api.constant.enums.PostStatusEnum;
import net.renfei.cms.api.entity.Post;
import net.renfei.cms.repositories.CmsPostsMapper;
import net.renfei.cms.repositories.entity.CmsPostsExample;
import net.renfei.cms.repositories.entity.CmsPostsWithBLOBs;
import net.renfei.common.api.constant.APIResult;
import net.renfei.common.api.constant.enums.SecretLevelEnum;
import net.renfei.common.api.entity.ListData;
import net.renfei.common.core.service.SystemService;
import net.renfei.uaa.api.entity.UserDetail;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.Date;
import java.util.List;

/**
 * 文章服务
 *
 * @author renfei
 */
@Service
public class PostServiceImpl implements PostService {
    private final SystemService systemService;
    private final CmsPostsMapper cmsPostsMapper;

    public PostServiceImpl(SystemService systemService,
                           CmsPostsMapper cmsPostsMapper) {
        this.systemService = systemService;
        this.cmsPostsMapper = cmsPostsMapper;
    }

    @Override
    public APIResult<ListData<Post>> queryPostList(Long categoryId, int pages, int rows) {
        return this.queryPostList(categoryId, PostStatusEnum.PUBLISH, null, new Date(), pages, rows);
    }

    @Override
    public APIResult<ListData<Post>> queryPostList(Long categoryId, PostStatusEnum postStatus,
                                                   Date startDate, Date endDate, int pages, int rows) {
        UserDetail userDetail = systemService.currentUserDetail();
        SecretLevelEnum userSecretLevel =
                userDetail == null ? SecretLevelEnum.UNCLASSIFIED : userDetail.getSecretLevel();
        CmsPostsExample example = new CmsPostsExample();
        example.setOrderByClause("post_date DESC");
        CmsPostsExample.Criteria criteria = example.createCriteria();
        criteria.andSecretLevelLessThanOrEqualTo(userSecretLevel.getLevel());
        if (startDate != null) {
            criteria.andPostDateGreaterThanOrEqualTo(startDate);
        }
        if (endDate != null) {
            criteria.andPostDateLessThanOrEqualTo(endDate);
        }
        if (postStatus != null) {
            criteria.andPostStatusEqualTo(postStatus.toString());
        }
        if (categoryId != null) {
            criteria.andCategoryIdEqualTo(categoryId);
        }
        ListData<Post> postListData = new ListData<>();
        try (Page<CmsPostsWithBLOBs> page = PageHelper.startPage(pages, rows)) {
            cmsPostsMapper.selectByExampleWithBLOBs(example);
            postListData.setPageNum(page.getPageNum());
            postListData.setPageSize(page.getPageSize());
            postListData.setStartRow(page.getStartRow());
            postListData.setEndRow(page.getEndRow());
            postListData.setTotal(page.getTotal());
            postListData.setPages(page.getPages());
            List<Post> postList = new ArrayList<>();
            for (CmsPostsWithBLOBs cmsPosts : page.getResult()
            ) {
                postList.add(convert(cmsPosts));
            }
            postListData.setData(postList);
        }
        return new APIResult<>(postListData);
    }

    private Post convert(CmsPostsWithBLOBs cmsPosts) {
        Post post = new Post();
        post.setId(cmsPosts.getId());
        post.setCategoryId(cmsPosts.getCategoryId());
        post.setPostAuthor(cmsPosts.getPostAuthor());
        post.setPostDate(cmsPosts.getPostDate());
        post.setPostStatus(PostStatusEnum.valueOf(cmsPosts.getPostStatus()));
        post.setPostViews(cmsPosts.getPostViews());
        post.setCommentStatus(cmsPosts.getCommentStatus());
        post.setPostPassword(cmsPosts.getPostPassword());
        post.setPostModified(cmsPosts.getPostModified());
        post.setPostModifiedUser(cmsPosts.getPostModifiedUser());
        post.setPostParent(cmsPosts.getPostParent());
        post.setVersionNumber(cmsPosts.getVersionNumber());
        post.setThumbsUp(cmsPosts.getThumbsUp());
        post.setThumbsDown(cmsPosts.getThumbsDown());
        post.setAvgViews(cmsPosts.getAvgViews());
        post.setAvgComment(cmsPosts.getAvgComment());
        post.setPageRank(cmsPosts.getPageRank());
        post.setSecretLevel(SecretLevelEnum.valueOf(cmsPosts.getSecretLevel()));
        post.setOriginal(cmsPosts.getIsOriginal());
        post.setFeaturedImage(cmsPosts.getFeaturedImage());
        post.setPostTitle(cmsPosts.getPostTitle());
        post.setPostKeyword(cmsPosts.getPostKeyword());
        post.setPostExcerpt(cmsPosts.getPostExcerpt());
        post.setPostContent(cmsPosts.getPostContent());
        post.setSourceName(cmsPosts.getSourceName());
        post.setSourceUrl(cmsPosts.getSourceUrl());
        return post;
    }
}
