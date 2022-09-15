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
package net.renfei.server.controller.dash.core;

import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.Parameter;
import io.swagger.v3.oas.annotations.tags.Tag;
import net.renfei.common.api.constant.APIResult;
import net.renfei.common.api.entity.ListData;
import net.renfei.common.core.annotation.OperationLog;
import net.renfei.common.core.entity.Comment;
import net.renfei.common.core.entity.OperationTypeEnum;
import net.renfei.common.core.entity.SystemTypeEnum;
import net.renfei.common.core.service.CommentService;
import net.renfei.server.controller.AbstractController;
import net.renfei.server.entity.ReplyCommentAo;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.*;

/**
 * 评论管理接口
 *
 * @author renfei
 */
@RestController
@RequestMapping("/_/api/core/comment")
@Tag(name = "评论管理接口", description = "评论管理接口")
public class CommentDashController extends AbstractController {
    private final CommentService commentService;

    public CommentDashController(CommentService commentService) {
        this.commentService = commentService;
    }

    @GetMapping("")
    @PreAuthorize("hasPermission('','core:comment:query')")
    @Operation(summary = "查询评论列表", tags = {"评论管理接口"},
            parameters = {
                    @Parameter(name = "haveDelete", description = "是否包含已删除评论"),
                    @Parameter(name = "pages", description = "页码"),
                    @Parameter(name = "rows", description = "每页数据量")
            })
    @OperationLog(module = SystemTypeEnum.COMMENT, desc = "查询评论列表")
    public APIResult<ListData<Comment>> queryCommentList(@RequestParam(value = "haveDelete", required = false) Boolean haveDelete,
                                                         @RequestParam(value = "pages", required = false) Integer pages,
                                                         @RequestParam(value = "rows", required = false) Integer rows) {
        return commentService.queryCommentList(haveDelete, pages, rows);
    }

    @PostMapping("{id}/reply")
    @PreAuthorize("hasPermission('','core:comment:reply')")
    @Operation(summary = "后台回复评论", tags = {"评论管理接口"},
            parameters = {
                    @Parameter(name = "commentId", description = "回复的评论ID")
            })
    @OperationLog(module = SystemTypeEnum.COMMENT, desc = "后台回复评论", operation = OperationTypeEnum.CREATE)
    APIResult replyComment(@PathVariable("id") long commentId, @RequestBody ReplyCommentAo replyCommentAo) {
        return commentService.replyComment(commentId, replyCommentAo.getContent(), request);
    }

    @DeleteMapping("{id}")
    @PreAuthorize("hasPermission('','core:comment:delete')")
    @Operation(summary = "后台删除评论", tags = {"评论管理接口"},
            parameters = {
                    @Parameter(name = "commentId", description = "评论ID")
            })
    @OperationLog(module = SystemTypeEnum.COMMENT, desc = "后台删除评论", operation = OperationTypeEnum.DELETE)
    APIResult deleteComment(@PathVariable("id") long commentId) {
        return commentService.deleteComment(commentId);
    }
}
