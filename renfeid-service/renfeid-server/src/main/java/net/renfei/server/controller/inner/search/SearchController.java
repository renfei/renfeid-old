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
package net.renfei.server.controller.inner.search;

import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.Parameter;
import io.swagger.v3.oas.annotations.tags.Tag;
import net.renfei.common.api.constant.APIResult;
import net.renfei.common.api.constant.enums.StateCodeEnum;
import net.renfei.common.api.entity.ListData;
import net.renfei.common.core.entity.HotSearch;
import net.renfei.common.core.entity.LogLevelEnum;
import net.renfei.common.core.entity.OperationTypeEnum;
import net.renfei.common.core.entity.SystemTypeEnum;
import net.renfei.common.core.service.SystemLogService;
import net.renfei.common.core.service.SystemService;
import net.renfei.common.search.entity.SearchItem;
import net.renfei.common.search.entity.TypeEnum;
import net.renfei.common.search.service.SearchService;
import net.renfei.server.controller.AbstractController;
import net.renfei.uaa.api.entity.UserDetail;
import org.springframework.util.ObjectUtils;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.context.request.RequestContextHolder;
import org.springframework.web.context.request.ServletRequestAttributes;

import java.text.DecimalFormat;
import java.util.List;

/**
 * 搜索引擎接口
 *
 * @author renfei
 */
@RestController
@RequestMapping("/-/api/search")
@Tag(name = "搜索引擎接口", description = "搜索引擎接口")
public class SearchController extends AbstractController {
    private final static Integer MAX_SEARCH_LENGTH = 38;
    private final SearchService searchService;
    private final SystemService systemService;
    private final SystemLogService systemLogService;

    public SearchController(SearchService searchService,
                            SystemService systemService,
                            SystemLogService systemLogService) {
        this.searchService = searchService;
        this.systemService = systemService;
        this.systemLogService = systemLogService;
    }

    @GetMapping("")
    @Operation(summary = "搜索", tags = {"搜索引擎接口"},
            parameters = {
                    @Parameter(name = "categoryId", description = "内容分类ID"),
                    @Parameter(name = "pages", description = "页码"),
                    @Parameter(name = "rows", description = "每页数据量")
            })
    public APIResult<ListData<SearchItem>> search(@RequestParam(value = "type", required = false, defaultValue = "ALL") TypeEnum type,
                                                  @RequestParam(value = "w") String query,
                                                  @RequestParam(value = "p", required = false, defaultValue = "1") String page) {
        if (ObjectUtils.isEmpty(query)) {
            return APIResult.builder()
                    .code(StateCodeEnum.Failure)
                    .message("请输入要搜索的内容")
                    .build();
        }
        if (query.length() > MAX_SEARCH_LENGTH) {
            query = query.substring(0, MAX_SEARCH_LENGTH);
        }
        Long startTime = System.nanoTime();
        ListData<SearchItem> searchItemListData = searchService.search(type, query, page, "10");
        Long endTime = System.nanoTime();
        double timed = (endTime - startTime) / 1000000000D;
        DecimalFormat df = new DecimalFormat("######0.000000");
        UserDetail userDetail = systemService.currentUserDetail();
        ServletRequestAttributes servletRequestAttributes =(ServletRequestAttributes) RequestContextHolder.getRequestAttributes();
        // 设置子线程共享
        RequestContextHolder.setRequestAttributes(servletRequestAttributes,true);
        if (userDetail == null) {
            systemLogService.save(LogLevelEnum.INFO, SystemTypeEnum.SEARCH, OperationTypeEnum.RETRIEVE, query, null, null);
        } else {
            systemLogService.save(LogLevelEnum.INFO, SystemTypeEnum.SEARCH, OperationTypeEnum.RETRIEVE, query, userDetail.getUuid(), userDetail.getUsername());
        }
        return APIResult.builder()
                .code(StateCodeEnum.OK)
                .message(df.format(timed))
                .data(searchItemListData)
                .build();
    }

    @GetMapping("hot")
    @Operation(summary = "获取热搜列表", tags = {"搜索引擎接口"}, parameters = {@Parameter(name = "size", description = "获取数量")})
    public APIResult<List<HotSearch>> queryHotSearchList(@RequestParam(value = "size", required = false, defaultValue = "15") int size) {
        return new APIResult<>(systemLogService.queryHotSearchList(size));
    }
}
