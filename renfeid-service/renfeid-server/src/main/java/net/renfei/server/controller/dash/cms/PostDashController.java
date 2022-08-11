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
package net.renfei.server.controller.dash.cms;

import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.Parameter;
import io.swagger.v3.oas.annotations.tags.Tag;
import net.renfei.cms.api.PostService;
import net.renfei.cms.api.constant.enums.PostStatusEnum;
import net.renfei.cms.api.entity.Post;
import net.renfei.common.api.constant.APIResult;
import net.renfei.common.api.entity.ListData;
import net.renfei.common.core.annotation.OperationLog;
import net.renfei.common.core.entity.OperationTypeEnum;
import net.renfei.common.core.entity.SystemTypeEnum;
import net.renfei.server.controller.AbstractController;
import org.springframework.format.annotation.DateTimeFormat;
import org.springframework.web.bind.annotation.*;

import java.util.Date;
import java.util.List;

/**
 * CMS管理接口
 *
 * @author renfei
 */
@RestController
@RequestMapping("/_/api/cms")
@Tag(name = "文章内容接口", description = "文章内容接口")
public class PostDashController extends AbstractController {
    private final PostService postService;

    public PostDashController(PostService postService) {
        this.postService = postService;
    }

    @GetMapping("posts")
    @Operation(summary = "查询文章内容列表", tags = {"文章内容接口"},
            parameters = {
                    @Parameter(name = "categoryId", description = "内容分类ID"),
                    @Parameter(name = "title", description = "内容标题"),
                    @Parameter(name = "postStatus", description = "文章状态"),
                    @Parameter(name = "startDate", description = "发布时间起始时间"),
                    @Parameter(name = "endDate", description = "发布时间结束时间"),
                    @Parameter(name = "pages", description = "页码"),
                    @Parameter(name = "rows", description = "每页数据量")
            })
    @OperationLog(module = SystemTypeEnum.POSTS, desc = "查询文章内容列表")
    public APIResult<ListData<Post>> queryPostList(@RequestParam(value = "categoryId", required = false) Long categoryId,
                                                   @RequestParam(value = "title", required = false) String title,
                                                   @RequestParam(value = "postStatus", required = false) PostStatusEnum postStatus,
                                                   @RequestParam(value = "startDate", required = false)
                                                   @DateTimeFormat(pattern = "yyyy-MM-dd HH:mm:ss") Date startDate,
                                                   @RequestParam(value = "endDate", required = false)
                                                   @DateTimeFormat(pattern = "yyyy-MM-dd HH:mm:ss") Date endDate,
                                                   @RequestParam(value = "pages", required = false, defaultValue = "1") Integer pages,
                                                   @RequestParam(value = "rows", required = false, defaultValue = "10") Integer rows) {
        return postService.queryPostList(null, categoryId, title, postStatus, startDate, endDate,
                pages == null ? 1 : pages, rows == null ? 10 : rows, null);
    }

    @GetMapping("posts/{id}")
    @Operation(summary = "获取文章内容详情", tags = {"文章内容接口"}, parameters = {
            @Parameter(name = "id", description = "文章内容ID")
    })
    @OperationLog(module = SystemTypeEnum.POSTS, desc = "获取文章内容详情")
    public APIResult<Post> queryPostById(@PathVariable("id") long postId) {
        return postService.queryPostById(postId, null, true, false);
    }

    @GetMapping("posts/archival/{id}")
    @Operation(summary = "获取历史版本文章内容列表", tags = {"历史版本文章内容接口"}, parameters = {
            @Parameter(name = "id", description = "文章内容ID")
    })
    @OperationLog(module = SystemTypeEnum.POSTS, desc = "获取历史版本文章内容列表")
    public APIResult<List<Post>> queryPostArchivalListById(@PathVariable("id") long postId) {
        return postService.queryPostArchivalListById(postId);
    }

    @GetMapping("posts/archival/{id}/{archivalId}")
    @Operation(summary = "获取历史版本文章内容详情", tags = {"历史版本文章内容接口"}, parameters = {
            @Parameter(name = "id", description = "文章内容ID")
    })
    @OperationLog(module = SystemTypeEnum.POSTS, desc = "获取历史版本文章内容详情")
    public APIResult<Post> queryPostArchivalById(@PathVariable("id") long postId,
                                                 @PathVariable("archivalId") long archivalId) {
        return postService.queryPostArchivalById(postId, archivalId);
    }

    @PostMapping("posts")
    @Operation(summary = "创建文章内容", tags = {"文章内容接口"})
    @OperationLog(module = SystemTypeEnum.POSTS, desc = "创建文章内容", operation = OperationTypeEnum.CREATE)
    public APIResult<Post> createPost(@RequestBody Post post) {
        return postService.createPost(post);
    }

    @PutMapping("posts/{id}")
    @Operation(summary = "修改文章内容", tags = {"文章内容接口"},
            parameters = {@Parameter(name = "id", description = "内容ID")})
    @OperationLog(module = SystemTypeEnum.POSTS, desc = "修改文章内容", operation = OperationTypeEnum.UPDATE)
    public APIResult<Post> updatePost(@PathVariable("id") long postId, @RequestBody Post post) {
        return postService.updatePost(postId, post);
    }

    @PutMapping("posts/{id}/offline")
    @Operation(summary = "下线文章内容", tags = {"文章内容接口"}, description = "下线内容，上线请重新编辑发布",
            parameters = {@Parameter(name = "id", description = "内容ID")})
    @OperationLog(module = SystemTypeEnum.POSTS, desc = "下线文章内容", operation = OperationTypeEnum.UPDATE)
    public APIResult offlinePost(@PathVariable("id") long postId) {
        return postService.offlinePost(postId);
    }

    @DeleteMapping("posts/{id}")
    @Operation(summary = "删除文章内容", tags = {"文章内容接口"},
            parameters = {@Parameter(name = "id", description = "内容ID")})
    @OperationLog(module = SystemTypeEnum.POSTS, desc = "删除文章内容", operation = OperationTypeEnum.DELETE)
    public APIResult deletePost(@PathVariable("id") long postId) {
        return postService.deletePost(postId);
    }
}
