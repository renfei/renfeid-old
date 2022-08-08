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
package net.renfei.cms.api;

import net.renfei.cms.api.constant.enums.PostStatusEnum;
import net.renfei.cms.api.entity.Post;
import net.renfei.common.api.constant.APIResult;
import net.renfei.common.api.entity.ListData;

import java.util.Date;
import java.util.List;

/**
 * 文章服务
 *
 * @author renfei
 */
public interface PostService {
    /**
     * 查询已发布的文章列表
     *
     * @param categoryId 分类ID
     * @param pages      页码
     * @param rows       每页行数
     * @param useCache   使用缓存（只缓存非密内容）
     * @return
     */
    APIResult<ListData<Post>> queryPostList(Long categoryId, int pages, int rows, boolean useCache);

    /**
     * 查询文章列表
     *
     * @param categoryId 分类ID
     * @param postStatus 文章状态
     * @param startDate  开始时间
     * @param endDate    结束时间
     * @param pages      页码
     * @param rows       每页行数
     * @return
     */
    APIResult<ListData<Post>> queryPostList(Long categoryId, String title, PostStatusEnum postStatus,
                                            Date startDate, Date endDate, int pages, int rows);

    /**
     * 根据标签查询已发布的文章列表
     *
     * @param tagId    标签ID
     * @param pages    页码
     * @param rows     每页行数
     * @param useCache 使用缓存（只缓存非密内容）
     * @return
     */
    APIResult<ListData<Post>> queryPostListByTag(long tagId, int pages, int rows, boolean useCache);

    /**
     * 根据标签查询已发布的文章列表
     *
     * @param tagId      标签ID
     * @param postStatus 文章状态
     * @param pages      页码
     * @param rows       每页行数
     * @return
     */
    APIResult<ListData<Post>> queryPostListByTag(long tagId, PostStatusEnum postStatus, int pages, int rows);

    /**
     * 根据ID获取文章详情
     *
     * @param postId   文章ID
     * @param useCache 是否使用缓存
     * @param password 查看文章的密码
     * @param isAdmin  是否是管理员
     * @param useCache 是否使用缓存
     * @return
     */
    APIResult<Post> queryPostById(long postId, String password, boolean isAdmin, boolean useCache);

    /**
     * 根据ID获取历史文章列表
     *
     * @param postId 文章ID
     * @return
     */
    APIResult<List<Post>> queryPostArchivalListById(long postId);

    /**
     * 根据ID获取历史文章详情
     *
     * @param postId     文章ID
     * @param archivalId 历史版本ID
     * @return
     */
    APIResult<Post> queryPostArchivalById(long postId, long archivalId);

    /**
     * 创建一篇新内容
     *
     * @param post 文章内容
     * @return
     */
    APIResult<Post> createPost(Post post);

    /**
     * 修改一篇新内容
     *
     * @param postId 内容ID
     * @param post   文章内容
     * @return
     */
    APIResult<Post> updatePost(long postId, Post post);

    /**
     * 下线内容，上线请重新编辑发布
     *
     * @param postId 内容ID
     * @return
     */
    APIResult offlinePost(long postId);

    /**
     * 删除内容
     *
     * @param postId 内容ID
     * @return
     */
    APIResult deletePost(long postId);

    APIResult addViews(long postId);
}
