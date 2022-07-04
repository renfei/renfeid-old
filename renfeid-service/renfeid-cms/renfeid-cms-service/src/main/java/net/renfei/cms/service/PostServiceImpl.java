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
import net.renfei.cms.repositories.CmsCategoryMapper;
import net.renfei.cms.repositories.CmsPostsArchivalMapper;
import net.renfei.cms.repositories.CmsPostsMapper;
import net.renfei.cms.repositories.entity.CmsCategory;
import net.renfei.cms.repositories.entity.CmsPostsArchivalWithBLOBs;
import net.renfei.cms.repositories.entity.CmsPostsExample;
import net.renfei.cms.repositories.entity.CmsPostsWithBLOBs;
import net.renfei.common.api.constant.APIResult;
import net.renfei.common.api.constant.enums.SecretLevelEnum;
import net.renfei.common.api.constant.enums.StateCodeEnum;
import net.renfei.common.api.entity.ListData;
import net.renfei.common.api.exception.BusinessException;
import net.renfei.common.api.exception.OutOfSecretLevelException;
import net.renfei.common.core.config.SystemConfig;
import net.renfei.common.core.service.RedisService;
import net.renfei.common.core.service.SnowflakeService;
import net.renfei.common.core.service.SystemService;
import net.renfei.uaa.api.entity.UserDetail;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.ArrayList;
import java.util.Date;
import java.util.List;

import static net.renfei.common.core.config.RedisConfig.REDIS_KEY_DATABASE;

/**
 * 文章服务
 *
 * @author renfei
 */
@Service
public class PostServiceImpl implements PostService {
    private final static String REDIS_KEY = REDIS_KEY_DATABASE + ":post:";
    private final RedisService redisService;
    private final SystemConfig systemConfig;
    private final SystemService systemService;
    private final CmsPostsMapper cmsPostsMapper;
    private final SnowflakeService snowflakeService;
    private final CmsCategoryMapper cmsCategoryMapper;
    private final CmsPostsArchivalMapper cmsPostsArchivalMapper;

    public PostServiceImpl(RedisService redisService,
                           SystemConfig systemConfig,
                           SystemService systemService,
                           CmsPostsMapper cmsPostsMapper,
                           SnowflakeService snowflakeService,
                           CmsCategoryMapper cmsCategoryMapper,
                           CmsPostsArchivalMapper cmsPostsArchivalMapper) {
        this.redisService = redisService;
        this.systemConfig = systemConfig;
        this.systemService = systemService;
        this.cmsPostsMapper = cmsPostsMapper;
        this.snowflakeService = snowflakeService;
        this.cmsCategoryMapper = cmsCategoryMapper;
        this.cmsPostsArchivalMapper = cmsPostsArchivalMapper;
    }

    @Override
    public APIResult<ListData<Post>> queryPostList(Long categoryId, int pages, int rows, boolean useCache) {
        UserDetail userDetail = systemService.currentUserDetail();
        SecretLevelEnum userSecretLevel =
                userDetail == null ? SecretLevelEnum.UNCLASSIFIED : userDetail.getSecretLevel();
        ListData<Post> postListData = null;
        if (systemConfig.getEnableCache() && useCache && SecretLevelEnum.UNCLASSIFIED.equals(userSecretLevel)) {
            // 启用缓存，并且是非密用户
            String redisKey = REDIS_KEY + (categoryId == null ? "all:" : "category:" + categoryId + ":");
            redisKey += "page_" + pages + "_" + rows;
            if (redisService.hasKey(redisKey)) {
                Object object = redisService.get(redisKey);
                if (object instanceof ListData) {
                    postListData = (ListData<Post>) object;
                }
            }
            if (postListData == null) {
                postListData = this.queryPostList(categoryId, null, PostStatusEnum.PUBLISH, null, new Date(), pages, rows).getData();
                redisService.set(redisKey, postListData);
            }
            return new APIResult<>(postListData);
        } else {
            return this.queryPostList(categoryId, null, PostStatusEnum.PUBLISH, null, new Date(), pages, rows);
        }
    }

    @Override
    public APIResult<ListData<Post>> queryPostList(Long categoryId, String title, PostStatusEnum postStatus,
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
        if (title != null) {
            criteria.andPostTitleLike("%" + title + "%");
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

    @Override
    public APIResult<Post> queryPostById(long postId, String password, boolean isAdmin, boolean useCache) {
        UserDetail userDetail = systemService.currentUserDetail();
        SecretLevelEnum userSecretLevel =
                userDetail == null ? SecretLevelEnum.UNCLASSIFIED : userDetail.getSecretLevel();
        Post post = null;
        String redisKey = REDIS_KEY + postId;
        if (systemConfig.getEnableCache() && useCache && SecretLevelEnum.UNCLASSIFIED.equals(userSecretLevel)) {
            // 启用缓存，并且是非密用户
            if (redisService.hasKey(redisKey)) {
                Object object = redisService.get(redisKey);
                if (object instanceof Post) {
                    post = (Post) object;
                }
            }
        }
        if (post == null) {
            post = convert(cmsPostsMapper.selectByPrimaryKey(postId));
            if (post != null && systemConfig.getEnableCache()
                    && useCache && SecretLevelEnum.UNCLASSIFIED.equals(post.getSecretLevel())) {
                // 启用缓存，并且是非密内容
                redisService.set(redisKey, post);
            }
        }
        if (post == null) {
            throw new BusinessException("内容不存在");
        }
        if (SecretLevelEnum.outOfSecretLevel(userSecretLevel, post.getSecretLevel())) {
            throw new BusinessException("您所在密级无权查看此内容");
        }
        if (!isAdmin) {
            // 如果不是管理员，那么还需要判断内容的状态是否已经发布
            if (!PostStatusEnum.PUBLISH.equals(post.getPostStatus())) {
                throw new BusinessException("内容不存在");
            }
            if (new Date().before(post.getPostDate())) {
                throw new BusinessException("内容不存在");
            }
            if (post.getPostPassword() != null && !post.getPostPassword().isEmpty()) {
                if (password == null || password.isEmpty()) {
                    throw new BusinessException("内容受到密码保护，需要输入密码访问");
                }
                if (post.getPostPassword().equals(password)) {
                    throw new BusinessException("密码错误");
                }
            }
        }
        return new APIResult<>(post);
    }

    @Override
    @Transactional(rollbackFor = Exception.class)
    public APIResult<Post> createPost(Post post) {
        postCheck(post.getPostTitle(), "标题不能为空");
        postCheck(post.getPostContent(), "内容不能为空");
        postCheck(post.getFeaturedImage(), "特色图像不能为空");
        if (post.getCategoryId() == null) {
            throw new BusinessException("分类ID不能为空");
        }
        if (post.getPostDate() == null) {
            throw new BusinessException("发布时间不能为空");
        }
        if (post.getSecretLevel() == null) {
            throw new BusinessException("保密等级不能为空");
        }
        postCheckSecretLevel(post);
        UserDetail currentUserDetail = systemService.currentUserDetail();
        post.setPostAuthor(currentUserDetail.getId());
        if (systemConfig.getEnablePostAuditing()) {
            // TODO 文章审核流程机制
            post.setPostStatus(PostStatusEnum.REVIEW);
        } else {
            post.setPostStatus(PostStatusEnum.PUBLISH);
        }
        post.setPostViews(0L);
        post.setPostModified(null);
        post.setPostModifiedUser(null);
        post.setPostParent(0L);
        post.setVersionNumber(1);
        post.setThumbsUp(0L);
        post.setThumbsDown(0L);
        post.setId(snowflakeService.getId("").getId());
        cmsPostsMapper.insertSelective(convert(post));
        // 插入一份归档记录
        cmsPostsArchivalMapper.insertSelective(convertArchival(post));
        return new APIResult<>(post);
    }

    @Override
    @Transactional(rollbackFor = Exception.class)
    public APIResult<Post> updatePost(long postId, Post post) {
        Post oldPost = convert(cmsPostsMapper.selectByPrimaryKey(postId));
        if (oldPost == null) {
            throw new BusinessException("根据内容ID未找到内容，请重试");
        }
        postCheck(post.getPostTitle(), "标题不能为空");
        postCheck(post.getPostContent(), "内容不能为空");
        postCheck(post.getFeaturedImage(), "特色图像不能为空");
        if (post.getCategoryId() == null) {
            throw new BusinessException("分类ID不能为空");
        }
        if (post.getPostDate() == null) {
            throw new BusinessException("发布时间不能为空");
        }
        if (post.getSecretLevel() == null) {
            throw new BusinessException("保密等级不能为空");
        }
        postCheckSecretLevel(oldPost);
        postCheckSecretLevel(post);
        UserDetail currentUserDetail = systemService.currentUserDetail();
        oldPost.setPostTitle(post.getPostTitle());
        oldPost.setCategoryId(post.getCategoryId());
        oldPost.setFeaturedImage(post.getFeaturedImage());
        oldPost.setPostKeyword(post.getPostKeyword());
        oldPost.setPostExcerpt(post.getPostExcerpt());
        oldPost.setPostContent(post.getPostContent());
        oldPost.setPostDate(post.getPostDate());
        if (systemConfig.getEnablePostAuditing()) {
            // TODO 文章审核流程机制
            oldPost.setPostStatus(PostStatusEnum.REVIEW);
        } else {
            oldPost.setPostStatus(PostStatusEnum.PUBLISH);
        }
        oldPost.setCommentStatus(post.getCommentStatus());
        oldPost.setPostPassword(post.getPostPassword());
        oldPost.setPostModified(new Date());
        oldPost.setPostModifiedUser(currentUserDetail.getId());
        oldPost.setVersionNumber(oldPost.getVersionNumber() + 1);
        cmsPostsMapper.updateByPrimaryKeyWithBLOBs(convert(oldPost));
        oldPost.setPostParent(oldPost.getId());
        oldPost.setId(snowflakeService.getId("").getId());
        cmsPostsArchivalMapper.insertSelective(convertArchival(oldPost));
        oldPost.setId(oldPost.getPostParent());
        return new APIResult<>(oldPost);
    }

    @Override
    @Transactional(rollbackFor = Exception.class)
    public APIResult offlinePost(long postId) {
        Post oldPost = convert(cmsPostsMapper.selectByPrimaryKey(postId));
        if (oldPost == null) {
            return APIResult.builder()
                    .code(StateCodeEnum.Failure)
                    .message("根据内容ID未找到内容，请重试")
                    .build();
        }
        UserDetail currentUserDetail = systemService.currentUserDetail();
        if (SecretLevelEnum.outOfSecretLevel(currentUserDetail.getSecretLevel(), oldPost.getSecretLevel())) {
            throw new OutOfSecretLevelException("内容密级超过您账号的密级，请求被拒绝");
        }
        oldPost.setPostStatus(PostStatusEnum.OFFLINE);
        oldPost.setPostModified(new Date());
        oldPost.setPostModifiedUser(currentUserDetail.getId());
        oldPost.setVersionNumber(oldPost.getVersionNumber() + 1);
        cmsPostsMapper.updateByPrimaryKeyWithBLOBs(convert(oldPost));
        oldPost.setPostParent(oldPost.getId());
        oldPost.setId(snowflakeService.getId("").getId());
        cmsPostsArchivalMapper.insertSelective(convertArchival(oldPost));
        return APIResult.success();
    }

    @Override
    public APIResult deletePost(long postId) {
        Post oldPost = convert(cmsPostsMapper.selectByPrimaryKey(postId));
        if (oldPost == null) {
            return APIResult.builder()
                    .code(StateCodeEnum.Failure)
                    .message("根据内容ID未找到内容，请重试")
                    .build();
        }
        UserDetail currentUserDetail = systemService.currentUserDetail();
        if (SecretLevelEnum.outOfSecretLevel(currentUserDetail.getSecretLevel(), oldPost.getSecretLevel())) {
            throw new OutOfSecretLevelException("内容密级超过您账号的密级，请求被拒绝");
        }
        oldPost.setPostStatus(PostStatusEnum.DELETED);
        oldPost.setPostModified(new Date());
        oldPost.setPostModifiedUser(currentUserDetail.getId());
        oldPost.setVersionNumber(oldPost.getVersionNumber() + 1);
        cmsPostsMapper.updateByPrimaryKeyWithBLOBs(convert(oldPost));
        oldPost.setPostParent(oldPost.getId());
        oldPost.setId(snowflakeService.getId("").getId());
        cmsPostsArchivalMapper.insertSelective(convertArchival(oldPost));
        return APIResult.success();
    }

    @Override
    public APIResult addViews(long postId) {
        Post post = convert(cmsPostsMapper.selectByPrimaryKey(postId));
        post.setPostViews(post.getPostViews() + 1);
        cmsPostsMapper.updateByPrimaryKeyWithBLOBs(convert(post));
        return APIResult.success();
    }

    private void postCheck(String value, String prompt) {
        if (value == null || value.isEmpty()) {
            throw new BusinessException(prompt);
        }
    }

    private void postCheckSecretLevel(Post post) {
        if (SecretLevelEnum.outOfSecretLevel(systemConfig.getMaxSecretLevel(), post.getSecretLevel())) {
            throw new OutOfSecretLevelException("内容密级超过当前系统允许的最大等级，请求被拒绝");
        }
        UserDetail currentUserDetail = systemService.currentUserDetail();
        if (SecretLevelEnum.outOfSecretLevel(currentUserDetail.getSecretLevel(), post.getSecretLevel())) {
            throw new OutOfSecretLevelException("内容密级超过您账号的密级，请求被拒绝");
        }
        CmsCategory cmsCategory = cmsCategoryMapper.selectByPrimaryKey(post.getCategoryId());
        if (cmsCategory == null) {
            throw new BusinessException("分类栏目ID不正确，未能找到该分类");
        }
        if (SecretLevelEnum.outOfSecretLevel(SecretLevelEnum.valueOf(cmsCategory.getSecretLevel()), post.getSecretLevel())) {
            throw new OutOfSecretLevelException("内容密级超过内容分类栏目的密级，请求被拒绝");
        }
    }

    private Post convert(CmsPostsWithBLOBs cmsPosts) {
        if (cmsPosts == null) {
            return null;
        }
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

    private CmsPostsWithBLOBs convert(Post post) {
        if (post == null) {
            return null;
        }
        CmsPostsWithBLOBs cmsPosts = new CmsPostsWithBLOBs();
        cmsPosts.setId(post.getId());
        cmsPosts.setCategoryId(post.getCategoryId());
        cmsPosts.setPostAuthor(post.getPostAuthor());
        cmsPosts.setPostDate(post.getPostDate());
        cmsPosts.setPostStatus(post.getPostStatus().toString());
        cmsPosts.setPostViews(post.getPostViews());
        cmsPosts.setCommentStatus(post.getCommentStatus());
        cmsPosts.setPostPassword(post.getPostPassword());
        cmsPosts.setPostModified(post.getPostModified());
        cmsPosts.setPostModifiedUser(post.getPostModifiedUser());
        cmsPosts.setPostParent(post.getPostParent());
        cmsPosts.setVersionNumber(post.getVersionNumber());
        cmsPosts.setThumbsUp(post.getThumbsUp());
        cmsPosts.setThumbsDown(post.getThumbsDown());
        cmsPosts.setAvgViews(post.getAvgViews());
        cmsPosts.setAvgComment(post.getAvgComment());
        cmsPosts.setPageRank(post.getPageRank());
        cmsPosts.setSecretLevel(post.getSecretLevel().getLevel());
        cmsPosts.setIsOriginal(post.getOriginal());
        cmsPosts.setFeaturedImage(post.getFeaturedImage());
        cmsPosts.setPostTitle(post.getPostTitle());
        cmsPosts.setPostKeyword(post.getPostKeyword());
        cmsPosts.setPostExcerpt(post.getPostExcerpt());
        cmsPosts.setPostContent(post.getPostContent());
        cmsPosts.setSourceName(post.getSourceName());
        cmsPosts.setSourceUrl(post.getSourceUrl());
        return cmsPosts;
    }

    private CmsPostsArchivalWithBLOBs convertArchival(Post post) {
        if (post == null) {
            return null;
        }
        CmsPostsArchivalWithBLOBs cmsPosts = new CmsPostsArchivalWithBLOBs();
        cmsPosts.setId(post.getId());
        cmsPosts.setCategoryId(post.getCategoryId());
        cmsPosts.setPostAuthor(post.getPostAuthor());
        cmsPosts.setPostDate(post.getPostDate());
        cmsPosts.setPostStatus(post.getPostStatus().toString());
        cmsPosts.setPostViews(post.getPostViews());
        cmsPosts.setCommentStatus(post.getCommentStatus());
        cmsPosts.setPostPassword(post.getPostPassword());
        cmsPosts.setPostModified(post.getPostModified());
        cmsPosts.setPostModifiedUser(post.getPostModifiedUser());
        cmsPosts.setPostParent(post.getPostParent());
        cmsPosts.setVersionNumber(post.getVersionNumber());
        cmsPosts.setThumbsUp(post.getThumbsUp());
        cmsPosts.setThumbsDown(post.getThumbsDown());
        cmsPosts.setAvgViews(post.getAvgViews());
        cmsPosts.setAvgComment(post.getAvgComment());
        cmsPosts.setPageRank(post.getPageRank());
        cmsPosts.setSecretLevel(post.getSecretLevel().getLevel());
        cmsPosts.setIsOriginal(post.getOriginal());
        cmsPosts.setFeaturedImage(post.getFeaturedImage());
        cmsPosts.setPostTitle(post.getPostTitle());
        cmsPosts.setPostKeyword(post.getPostKeyword());
        cmsPosts.setPostExcerpt(post.getPostExcerpt());
        cmsPosts.setPostContent(post.getPostContent());
        cmsPosts.setSourceName(post.getSourceName());
        cmsPosts.setSourceUrl(post.getSourceUrl());
        return cmsPosts;
    }
}
