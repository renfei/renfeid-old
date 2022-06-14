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

import net.renfei.cms.api.PostCategoryService;
import net.renfei.cms.api.entity.PostCategory;
import net.renfei.common.api.constant.APIResult;
import net.renfei.common.api.constant.enums.SecretLevelEnum;
import net.renfei.common.api.entity.ListData;
import net.renfei.common.core.annotation.OperationLog;
import net.renfei.common.core.entity.OperationTypeEnum;
import net.renfei.common.core.entity.SystemTypeEnum;
import net.renfei.server.controller.AbstractController;
import org.springframework.web.bind.annotation.*;

/**
 * 内容分类管理接口
 *
 * @author renfei
 */
@RestController
@RequestMapping("/_/api/cms")
public class PostCategoryDashController extends AbstractController {
    private final PostCategoryService postCategoryService;

    public PostCategoryDashController(PostCategoryService postCategoryService) {
        this.postCategoryService = postCategoryService;
    }

    @GetMapping("posts/category")
    @OperationLog(module = SystemTypeEnum.POSTS, desc = "查询文章分类列表")
    public APIResult<ListData<PostCategory>>
    queryPostCategoryList(@RequestParam(value = "enName", required = false) String enName,
                          @RequestParam(value = "zhName", required = false) String zhName,
                          @RequestParam(value = "secretLevel", required = false) SecretLevelEnum secretLevel,
                          @RequestParam(value = "pages", required = false) int pages,
                          @RequestParam(value = "rows", required = false) int rows) {
        return postCategoryService.queryPostCategoryList(enName, zhName, secretLevel, pages, rows);
    }

    @PostMapping("posts/category")
    @OperationLog(module = SystemTypeEnum.POSTS, desc = "创建文章分类", operation = OperationTypeEnum.CREATE)
    public APIResult<PostCategory> createPostCategory(@RequestBody PostCategory postCategory) {
        return postCategoryService.createPostCategory(postCategory);
    }

    @PutMapping("posts/category/{id}")
    @OperationLog(module = SystemTypeEnum.POSTS, desc = "修改文章分类", operation = OperationTypeEnum.UPDATE)
    public APIResult<PostCategory> updatePostCategory(@PathVariable("id") long categoryId,
                                                      @RequestBody PostCategory postCategory) {
        return postCategoryService.updatePostCategory(categoryId, postCategory);
    }

    @DeleteMapping("posts/category/{id}")
    @OperationLog(module = SystemTypeEnum.POSTS, desc = "删除文章分类", operation = OperationTypeEnum.DELETE)
    public APIResult deletePostCategory(@PathVariable("id") long categoryId) {
        return postCategoryService.deletePostCategory(categoryId);
    }
}
