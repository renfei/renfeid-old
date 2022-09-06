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
package net.renfei.server.controller.inner.cms;

import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.Parameter;
import io.swagger.v3.oas.annotations.tags.Tag;
import net.renfei.cms.api.PostService;
import net.renfei.cms.api.PostTagService;
import net.renfei.cms.api.entity.Post;
import net.renfei.common.api.constant.APIResult;
import net.renfei.common.api.constant.enums.StateCodeEnum;
import net.renfei.common.api.entity.ListData;
import net.renfei.common.core.annotation.OperationLog;
import net.renfei.common.core.entity.SystemTypeEnum;
import net.renfei.server.controller.AbstractController;
import org.springframework.util.ObjectUtils;
import org.springframework.web.bind.annotation.*;

import java.util.List;

/**
 * CMS 内部接口
 *
 * @author renfei
 */
@RestController
@RequestMapping("/-/api/cms")
@Tag(name = "文章内容接口", description = "文章内容接口")
public class PostsController extends AbstractController {
    private final PostService postService;
    private final PostTagService postTagService;

    public PostsController(PostService postService,
                           PostTagService postTagService) {
        this.postService = postService;
        this.postTagService = postTagService;
    }

    @GetMapping("posts")
    @Operation(summary = "查询已发布的文章列表", tags = {"文章内容接口"},
            parameters = {
                    @Parameter(name = "categoryId", description = "内容分类ID"),
                    @Parameter(name = "pages", description = "页码"),
                    @Parameter(name = "rows", description = "每页数据量")
            })
    @OperationLog(module = SystemTypeEnum.POSTS, desc = "查询已发布的文章列表")
    public APIResult<ListData<Post>> queryPostList(@RequestParam(value = "categoryId", required = false) Long categoryId,
                                                   @RequestParam(value = "pages", required = false, defaultValue = "1") int pages,
                                                   @RequestParam(value = "rows", required = false, defaultValue = "10") int rows) {
        return postService.queryPostList(null, categoryId, pages, rows, true, null);
    }

    @GetMapping("posts/hot")
    @Operation(summary = "查询已发布的热点文章列表", tags = {"文章内容接口"},
            parameters = {
                    @Parameter(name = "quantity", description = "需要查询的数量")
            })
    @OperationLog(module = SystemTypeEnum.POSTS, desc = "查询已发布的热点文章列表")
    public APIResult<ListData<Post>> queryHotPostList(@RequestParam(value = "quantity", required = false, defaultValue = "10") int quantity) {
        return postService.queryPostList(null, null, 1, quantity, true, "avg_views DESC,post_date DESC");
    }

    @GetMapping("posts/{id}/related")
    @Operation(summary = "查询内容关联的文章列表", tags = {"文章内容接口"},
            parameters = {
                    @Parameter(name = "postId", description = "内容ID"),
                    @Parameter(name = "quantity", description = "需要查询的数量")
            })
    @OperationLog(module = SystemTypeEnum.POSTS, desc = "查询内容关联的文章列表")
    public APIResult<ListData<Post>> queryRelatedPostList(@PathVariable("id") long postId,
                                                          @RequestParam(value = "quantity", required = false, defaultValue = "10") int quantity) {
        APIResult<Post> postResult = postService.queryPostById(postId, null, false, true);
        if (postResult.getData() != null) {
            List<Long> longs = postTagService.queryRelatedPostIdByTag(postResult.getData().getTags());
            return postService.queryPostList(longs, null, 1, quantity, true, "avg_views DESC,post_date DESC");
        }
        return APIResult.builder()
                .code(StateCodeEnum.OK)
                .message("OK")
                .build();
    }

    @GetMapping("posts/tag/{tagEnName}")
    @Operation(summary = "根据标签查询已发布的文章列表", tags = {"文章内容接口"},
            parameters = {
                    @Parameter(name = "tagEnName", description = "内容标签分类"),
                    @Parameter(name = "pages", description = "页码"),
                    @Parameter(name = "rows", description = "每页数据量")
            })
    @OperationLog(module = SystemTypeEnum.POSTS, desc = "查询已发布的文章列表")
    public APIResult<ListData<Post>> queryPostListByTag(@PathVariable("tagEnName") String tagEnName,
                                                        @RequestParam(value = "pages", required = false, defaultValue = "1") int pages,
                                                        @RequestParam(value = "rows", required = false, defaultValue = "10") int rows) {
        if (ObjectUtils.isEmpty(tagEnName)) {
            return APIResult.builder()
                    .code(StateCodeEnum.NotFound)
                    .message("标签不存在")
                    .build();
        }
        net.renfei.cms.api.entity.Tag tag = postTagService.queryTagByEnName(tagEnName);
        if (tag == null) {
            return APIResult.builder()
                    .code(StateCodeEnum.NotFound)
                    .message("标签不存在")
                    .build();
        }
        return postService.queryPostListByTag(Long.parseLong(tag.getId()), pages, rows, true);
    }

    @GetMapping("posts/{id}")
    @Operation(summary = "获取文章内容详情", tags = {"文章内容接口"}, parameters = {
            @Parameter(name = "id", description = "文章内容ID"),
            @Parameter(name = "password", description = "文章内容密码")
    })
    @OperationLog(module = SystemTypeEnum.POSTS, desc = "获取文章详情")
    public APIResult<Post> queryPostById(@PathVariable("id") long postId,
                                         @RequestParam(value = "password", required = false) String password) {
        APIResult<Post> postResult = postService.queryPostById(postId, password, false, true);
        postService.addViews(Long.parseLong(postResult.getData().getId()));
        return postResult;
    }
}
