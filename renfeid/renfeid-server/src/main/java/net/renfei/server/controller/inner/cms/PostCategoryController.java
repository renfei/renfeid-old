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
import net.renfei.cms.api.PostCategoryService;
import net.renfei.cms.api.entity.PostCategory;
import net.renfei.common.api.constant.APIResult;
import net.renfei.common.api.constant.enums.SecretLevelEnum;
import net.renfei.common.api.entity.ListData;
import net.renfei.common.core.annotation.OperationLog;
import net.renfei.common.core.entity.SystemTypeEnum;
import net.renfei.common.core.service.SystemService;
import net.renfei.server.controller.AbstractController;
import net.renfei.uaa.api.entity.UserDetail;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

/**
 * 内容分类接口
 *
 * @author renfei
 */
@RestController
@RequestMapping("/-/api/cms")
@Tag(name = "内容分类接口", description = "内容分类接口")
public class PostCategoryController extends AbstractController {
    private final SystemService systemService;
    private final PostCategoryService postCategoryService;

    public PostCategoryController(SystemService systemService,
                                  PostCategoryService postCategoryService) {
        this.systemService = systemService;
        this.postCategoryService = postCategoryService;
    }

    @GetMapping("posts/category")
    @Operation(summary = "查询文章内容分类列表", tags = {"内容分类接口"},
            parameters = {
                    @Parameter(name = "enName", description = "分类英文名称"),
                    @Parameter(name = "pages", description = "页码"),
                    @Parameter(name = "rows", description = "每页数据量")
            })
    @OperationLog(module = SystemTypeEnum.POSTS, desc = "查询文章内容分类列表")
    public APIResult<ListData<PostCategory>>
    queryPostCategoryList(@RequestParam(value = "enName", required = false) String enName,
                          @RequestParam(value = "pages", required = false) int pages,
                          @RequestParam(value = "rows", required = false) int rows) {
        SecretLevelEnum secretLevel = SecretLevelEnum.UNCLASSIFIED;
        UserDetail currentUserDetail = systemService.currentUserDetail();
        if (currentUserDetail != null) {
            secretLevel = currentUserDetail.getSecretLevel();
        }
        return postCategoryService.queryPostCategoryList(enName, null, secretLevel, pages, rows);
    }
}
