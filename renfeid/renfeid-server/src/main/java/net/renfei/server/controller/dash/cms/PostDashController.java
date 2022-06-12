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

/**
 * CMS管理接口
 *
 * @author renfei
 */
@RestController
@RequestMapping("/_/api/cms")
public class PostDashController extends AbstractController {
    private final PostService postService;

    public PostDashController(PostService postService) {
        this.postService = postService;
    }

    @GetMapping("posts")
    @OperationLog(module = SystemTypeEnum.POSTS, desc = "查询文章列表")
    public APIResult<ListData<Post>> queryPostList(@RequestParam(value = "categoryId", required = false) Long categoryId,
                                                   @RequestParam(value = "postStatus", required = false) PostStatusEnum postStatus,
                                                   @RequestParam(value = "startDate", required = false)
                                                   @DateTimeFormat(pattern = "yyyy-MM-dd HH:mm:ss") Date startDate,
                                                   @RequestParam(value = "endDate", required = false)
                                                   @DateTimeFormat(pattern = "yyyy-MM-dd HH:mm:ss") Date endDate,
                                                   @RequestParam(value = "pages", required = false) Integer pages,
                                                   @RequestParam(value = "rows", required = false) Integer rows) {
        return postService.queryPostList(categoryId, postStatus, startDate, endDate,
                pages == null ? 1 : pages, rows == null ? 10 : rows);
    }

    @GetMapping("posts/{id}")
    @OperationLog(module = SystemTypeEnum.POSTS, desc = "获取文章详情")
    public APIResult<Post> queryPostById(@PathVariable("id") long postId) {
        return postService.queryPostById(postId, null, true, false);
    }

    @PostMapping("posts")
    @OperationLog(module = SystemTypeEnum.POSTS, desc = "创建文章", operation = OperationTypeEnum.CREATE)
    public APIResult<Post> createPost(@RequestBody Post post) {
        return postService.createPost(post);
    }

    @PutMapping("posts/{id}")
    @OperationLog(module = SystemTypeEnum.POSTS, desc = "修改文章", operation = OperationTypeEnum.UPDATE)
    public APIResult<Post> updatePost(@PathVariable("id") long postId, @RequestBody Post post) {
        return postService.updatePost(postId, post);
    }

    @PutMapping("posts/{id}/offline")
    @OperationLog(module = SystemTypeEnum.POSTS, desc = "下线文章", operation = OperationTypeEnum.UPDATE)
    public APIResult offlinePost(@PathVariable("id") long postId) {
        return postService.offlinePost(postId);
    }

    @DeleteMapping("posts/{id}")
    @OperationLog(module = SystemTypeEnum.POSTS, desc = "删除文章", operation = OperationTypeEnum.DELETE)
    public APIResult deletePost(@PathVariable("id") long postId) {
        return postService.deletePost(postId);
    }
}
