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
package net.renfei.server.controller.inner.core;

import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.tags.Tag;
import net.renfei.common.api.constant.APIResult;
import net.renfei.common.core.annotation.OperationLog;
import net.renfei.common.core.entity.SiteFriendlyLinkVo;
import net.renfei.common.core.entity.SystemTypeEnum;
import net.renfei.common.core.service.SiteFriendlyLinkService;
import net.renfei.server.controller.AbstractController;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

/**
 * 友情链接接口
 *
 * @author renfei
 */
@RestController
@RequestMapping("/-/api/core/friendlylink")
@Tag(name = "友情链接接口", description = "友情链接接口")
public class SiteFriendlyLinkController extends AbstractController {
    private final SiteFriendlyLinkService siteFriendlyLinkService;

    public SiteFriendlyLinkController(SiteFriendlyLinkService siteFriendlyLinkService) {
        this.siteFriendlyLinkService = siteFriendlyLinkService;
    }

    @GetMapping("")
    @Operation(summary = "查询友情链接列表", tags = {"友情链接接口"})
    @OperationLog(module = SystemTypeEnum.SYSTEM, desc = "查询友情链接列表")
    public APIResult<List<SiteFriendlyLinkVo>> queryFriendlyLink() {
        return new APIResult<>(siteFriendlyLinkService.queryFriendlyLink());
    }
}
