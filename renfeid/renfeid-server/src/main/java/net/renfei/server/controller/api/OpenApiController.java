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
package net.renfei.server.controller.api;

import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.tags.Tag;
import net.renfei.common.api.constant.APIResult;
import net.renfei.common.api.exception.BusinessException;
import net.renfei.common.core.annotation.OperationLog;
import net.renfei.common.core.entity.SystemTypeEnum;
import net.renfei.common.core.service.WordSegmentationService;
import net.renfei.server.controller.AbstractController;
import net.renfei.server.entity.ExtractKeywordAo;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

/**
 * 开放接口
 *
 * @author renfei
 */
@RestController
@RequestMapping("/api")
@Tag(name = "开放API接口", description = "开放API接口")
public class OpenApiController extends AbstractController {
    private final WordSegmentationService wordSegmentationService;

    public OpenApiController(WordSegmentationService wordSegmentationService) {
        this.wordSegmentationService = wordSegmentationService;
    }

    @PostMapping("extractKeywords")
    @Operation(summary = "从内容中提取关键字（基于词频）", tags = {"开放API接口"})
    @OperationLog(module = SystemTypeEnum.API, desc = "从内容中提取关键字")
    public APIResult<List<String>> extractKeywords(@RequestBody ExtractKeywordAo extractKeyword) {
        if (extractKeyword == null || extractKeyword.getContent() == null || extractKeyword.getContent().isEmpty()) {
            throw new BusinessException("请求体为空，请查证后重试。");
        }
        if (extractKeyword.getContent().length() > 10000) {
            throw new BusinessException("目前关键词提取支持最大长度为[10000]，请删减后重试。");
        }
        return new APIResult<>(wordSegmentationService.extractKeywords(extractKeyword.getContent(), 2, 10));
    }
}
