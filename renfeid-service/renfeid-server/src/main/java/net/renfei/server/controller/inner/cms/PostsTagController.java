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
import io.swagger.v3.oas.annotations.tags.Tag;
import net.renfei.cms.api.PostTagService;
import net.renfei.common.api.constant.APIResult;
import net.renfei.common.core.annotation.OperationLog;
import net.renfei.common.core.entity.SystemTypeEnum;
import net.renfei.server.controller.AbstractController;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

/**
 * CMS 内部接口
 *
 * @author renfei
 */
@RestController
@RequestMapping("/-/api/cms")
@Tag(name = "文章标签接口", description = "文章标签接口")
public class PostsTagController extends AbstractController {
    private final PostTagService postTagService;

    public PostsTagController(PostTagService postTagService) {
        this.postTagService = postTagService;
    }

    @GetMapping("tag")
    @Operation(summary = "查询所有内容标签", tags = {"文章标签接口"})
    @OperationLog(module = SystemTypeEnum.POSTS, desc = "查询所有内容标签")
    public APIResult<List<net.renfei.cms.api.entity.Tag>> queryAllTag() {
        return new APIResult<>(postTagService.queryAllTag());
    }
}
