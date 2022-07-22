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
 * 文章内容分类管理接口
 *
 * @author renfei
 */
@RestController
@RequestMapping("/_/api/cms")
@Tag(name = "文章内容分类管理接口", description = "文章内容分类管理接口")
public class PostCategoryDashController extends AbstractController {
    private final PostCategoryService postCategoryService;

    public PostCategoryDashController(PostCategoryService postCategoryService) {
        this.postCategoryService = postCategoryService;
    }

    @GetMapping("posts/category")
    @Operation(summary = "查询文章内容分类列表", tags = {"文章内容分类管理接口"},
            parameters = {
                    @Parameter(name = "enName", description = "分类英文名称"),
                    @Parameter(name = "zhName", description = "分类中文名称"),
                    @Parameter(name = "secretLevel", description = "密级"),
                    @Parameter(name = "pages", description = "页码"),
                    @Parameter(name = "rows", description = "每页数据量")
            })
    @OperationLog(module = SystemTypeEnum.POSTS, desc = "查询文章内容分类列表")
    public APIResult<ListData<PostCategory>>
    queryPostCategoryList(@RequestParam(value = "enName", required = false) String enName,
                          @RequestParam(value = "zhName", required = false) String zhName,
                          @RequestParam(value = "secretLevel", required = false) SecretLevelEnum secretLevel,
                          @RequestParam(value = "pages", required = false, defaultValue = "1") int pages,
                          @RequestParam(value = "rows", required = false, defaultValue = "10") int rows) {
        return postCategoryService.queryPostCategoryList(enName, zhName, secretLevel, pages, rows);
    }

    @PostMapping("posts/category")
    @Operation(summary = "创建文章内容分类", tags = {"文章内容分类管理接口"})
    @OperationLog(module = SystemTypeEnum.POSTS, desc = "创建文章内容分类", operation = OperationTypeEnum.CREATE)
    public APIResult<PostCategory> createPostCategory(@RequestBody PostCategory postCategory) {
        return postCategoryService.createPostCategory(postCategory);
    }

    @PutMapping("posts/category/{id}")
    @Operation(summary = "修改文章内容分类", tags = {"文章内容分类管理接口"}, parameters = {
            @Parameter(name = "id", description = "文章内容分类ID")
    })
    @OperationLog(module = SystemTypeEnum.POSTS, desc = "修改文章内容分类", operation = OperationTypeEnum.UPDATE)
    public APIResult<PostCategory> updatePostCategory(@PathVariable("id") long categoryId,
                                                      @RequestBody PostCategory postCategory) {
        return postCategoryService.updatePostCategory(categoryId, postCategory);
    }

    @DeleteMapping("posts/category/{id}")
    @Operation(summary = "删除文章内容分类", tags = {"文章内容分类管理接口"}, parameters = {
            @Parameter(name = "id", description = "文章内容分类ID")
    })
    @OperationLog(module = SystemTypeEnum.POSTS, desc = "删除文章内容分类", operation = OperationTypeEnum.DELETE)
    public APIResult deletePostCategory(@PathVariable("id") long categoryId) {
        return postCategoryService.deletePostCategory(categoryId);
    }
}
