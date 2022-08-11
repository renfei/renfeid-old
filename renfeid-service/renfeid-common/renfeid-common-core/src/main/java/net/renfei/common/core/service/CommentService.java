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
package net.renfei.common.core.service;

import net.renfei.common.api.constant.APIResult;
import net.renfei.common.api.entity.ListData;
import net.renfei.common.core.entity.Comment;
import net.renfei.common.core.entity.CommentTree;
import net.renfei.common.core.entity.SystemTypeEnum;

import javax.servlet.http.HttpServletRequest;
import java.util.List;

/**
 * 评论服务
 *
 * @author renfei
 */
public interface CommentService {
    /**
     * 提交评论
     *
     * @param comment 评论实体对象
     * @param isOwner 是否是官方回复
     * @param request 请求对象
     * @return
     */
    APIResult submitComment(Comment comment, boolean isOwner, HttpServletRequest request);

    /**
     * 查询评论列表
     *
     * @param haveDelete 是否包含已删除的评论
     * @param pages      页码
     * @param rows       每页容量
     * @return
     */
    APIResult<ListData<Comment>> queryCommentList(boolean haveDelete, int pages, int rows);

    /**
     * 回复评论（后台使用）
     *
     * @param commentId 评论ID
     * @param content   内容
     * @param request   请求对象
     * @return
     */
    APIResult replyComment(long commentId, String content, HttpServletRequest request);

    /**
     * 删除评论
     *
     * @param commentId 评论ID
     * @return
     */
    APIResult deleteComment(long commentId);

    /**
     * 查询评论树
     *
     * @param sysType  系统模块
     * @param objectId 评论对象ID
     * @param reply    被评论的父对象
     * @return
     */
    List<CommentTree> queryCommentTree(SystemTypeEnum sysType, long objectId, CommentTree reply);

    /**
     * 查询最后的评论列表
     *
     * @param sysType  系统模块
     * @param quantity 数量
     * @return
     */
    List<Comment> queryLastComment(SystemTypeEnum sysType, int quantity);
}
