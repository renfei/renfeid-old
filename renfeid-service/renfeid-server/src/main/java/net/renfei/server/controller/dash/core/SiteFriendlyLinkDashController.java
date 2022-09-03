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
package net.renfei.server.controller.dash.core;

import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.tags.Tag;
import net.renfei.common.api.constant.APIResult;
import net.renfei.common.core.annotation.OperationLog;
import net.renfei.common.core.entity.OperationTypeEnum;
import net.renfei.common.core.entity.SystemTypeEnum;
import net.renfei.common.core.repositories.entity.CoreSiteFriendlyLinkWithBLOBs;
import net.renfei.common.core.service.SiteFriendlyLinkService;
import net.renfei.server.controller.AbstractController;
import org.springframework.web.bind.annotation.*;

import java.util.List;

/**
 * 友情链接管理接口
 *
 * @author renfei
 */
@RestController
@RequestMapping("/_/api/core/friendlylink")
@Tag(name = "友情链接管理接口", description = "友情链接管理接口")
public class SiteFriendlyLinkDashController extends AbstractController {
    private final SiteFriendlyLinkService siteFriendlyLinkService;

    public SiteFriendlyLinkDashController(SiteFriendlyLinkService siteFriendlyLinkService) {
        this.siteFriendlyLinkService = siteFriendlyLinkService;
    }

    @GetMapping("")
    @Operation(summary = "查询全部友情链接列表", tags = {"友情链接管理接口"})
    @OperationLog(module = SystemTypeEnum.SYSTEM, desc = "查询全部友情链接列表")
    public APIResult<List<CoreSiteFriendlyLinkWithBLOBs>> queryAllFriendlyLink() {
        return new APIResult<>(siteFriendlyLinkService.queryAllFriendlyLink());
    }

    @PostMapping("")
    @Operation(summary = "创建友情链", tags = {"友情链接管理接口"})
    @OperationLog(module = SystemTypeEnum.SYSTEM, desc = "创建友情链", operation = OperationTypeEnum.CREATE)
    public APIResult createFriendlyLink(@RequestBody CoreSiteFriendlyLinkWithBLOBs friendlyLink) {
        return siteFriendlyLinkService.createFriendlyLink(friendlyLink);
    }

    @PutMapping("{id}")
    @Operation(summary = "修改友情链", tags = {"友情链接管理接口"})
    @OperationLog(module = SystemTypeEnum.SYSTEM, desc = "修改友情链", operation = OperationTypeEnum.UPDATE)
    public APIResult updateFriendlyLink(@PathVariable("id") long id,
                                        @RequestBody CoreSiteFriendlyLinkWithBLOBs friendlyLink) {
        friendlyLink.setId(id);
        return siteFriendlyLinkService.updateFriendlyLink(friendlyLink);
    }

    @DeleteMapping("{id}")
    @Operation(summary = "删除友情链", tags = {"友情链接管理接口"})
    @OperationLog(module = SystemTypeEnum.SYSTEM, desc = "删除友情链", operation = OperationTypeEnum.DELETE)
    public APIResult deleteFriendlyLink(@PathVariable("id") long id) {
        return siteFriendlyLinkService.deleteFriendlyLink(id);
    }
}
