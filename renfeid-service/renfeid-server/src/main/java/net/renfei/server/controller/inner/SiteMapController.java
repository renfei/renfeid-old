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
package net.renfei.server.controller.inner;

import io.swagger.v3.oas.annotations.tags.Tag;
import net.renfei.common.api.constant.APIResult;
import net.renfei.common.core.annotation.OperationLog;
import net.renfei.common.core.entity.SystemTypeEnum;
import net.renfei.server.controller.AbstractController;
import net.renfei.server.entity.SiteMapXml;
import net.renfei.server.service.SiteMapService;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

/**
 * 网站地图接口
 *
 * @author renfei
 */
@RestController
@RequestMapping("/-/api/sitemap")
@Tag(name = "网站地图接口", description = "网站地图接口")
public class SiteMapController extends AbstractController {
    private final SiteMapService siteMapService;

    public SiteMapController(SiteMapService siteMapService) {
        this.siteMapService = siteMapService;
    }

    @GetMapping("")
    @OperationLog(module = SystemTypeEnum.HOME, desc = "查看网站地图sitemap.xml")
    public APIResult<List<SiteMapXml>> querySiteMapXml() {
        return new APIResult<>(siteMapService.querySiteMapXml());
    }
}
