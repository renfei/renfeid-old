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
package net.renfei.server.controller.inner.comment;

import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.Parameter;
import io.swagger.v3.oas.annotations.tags.Tag;
import net.renfei.common.api.constant.APIResult;
import net.renfei.common.core.annotation.OperationLog;
import net.renfei.common.core.entity.Comment;
import net.renfei.common.core.entity.CommentTree;
import net.renfei.common.core.entity.OperationTypeEnum;
import net.renfei.common.core.entity.SystemTypeEnum;
import net.renfei.common.core.service.CommentService;
import net.renfei.server.controller.AbstractController;
import net.renfei.server.entity.CommentAo;
import org.springframework.util.ObjectUtils;
import org.springframework.web.bind.annotation.*;

import java.util.List;

/**
 * 评论接口
 *
 * @author renfei
 */
@RestController
@RequestMapping("/-/api/comment")
@Tag(name = "评论接口", description = "评论接口")
public class CommentController extends AbstractController {
    private final CommentService commentService;

    public CommentController(CommentService commentService) {
        this.commentService = commentService;
    }

    @GetMapping("{systemTypeEnum}/{id}")
    @Operation(summary = "查询评论树接口", tags = {"评论接口"},
            parameters = {
                    @Parameter(name = "systemTypeEnum", description = "系统类型"),
                    @Parameter(name = "id", description = "评论目标的ID")
            })
    @OperationLog(module = SystemTypeEnum.COMMENT, desc = "查询评论树接口", operation = OperationTypeEnum.RETRIEVE)
    APIResult<List<CommentTree>> queryCommentTree(@PathVariable("systemTypeEnum") SystemTypeEnum sysType,
                                                  @PathVariable("id") long objectId) {
        return new APIResult<>(commentService.queryCommentTree(sysType, objectId, null));
    }

    @PostMapping("{systemTypeEnum}/{id}")
    @Operation(summary = "提交评论接口", tags = {"评论接口"},
            parameters = {
                    @Parameter(name = "systemTypeEnum", description = "系统类型"),
                    @Parameter(name = "id", description = "评论目标的ID")
            })
    @OperationLog(module = SystemTypeEnum.COMMENT, desc = "提交评论", operation = OperationTypeEnum.CREATE)
    APIResult submitComments(@PathVariable("systemTypeEnum") SystemTypeEnum systemTypeEnum,
                             @PathVariable("id") long id,
                             @RequestBody CommentAo commentAo) {
        Comment comment = convert(commentAo);
        comment.setSysType(systemTypeEnum);
        comment.setObjectId(id + "");
        return commentService.submitComment(comment, false, request);
    }

    private Comment convert(CommentAo commentAo) {
        Comment comment = new Comment();
        comment.setParentId(ObjectUtils.isEmpty(commentAo.getParentId()) ? null : commentAo.getParentId() + "");
        comment.setAuthor(commentAo.getAuthor());
        comment.setAuthorEmail(commentAo.getAuthorEmail());
        comment.setAuthorUrl(commentAo.getAuthorUrl());
        comment.setContent(commentAo.getContent());
        return comment;
    }
}
