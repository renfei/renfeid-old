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
package net.renfei.uaa.service;

import com.github.pagehelper.Page;
import com.github.pagehelper.PageHelper;
import net.renfei.common.api.constant.APIResult;
import net.renfei.common.api.entity.ListData;
import net.renfei.uaa.api.SystemApiService;
import net.renfei.uaa.api.entity.SystemApi;
import net.renfei.uaa.repositories.UaaSystemApiMapper;
import net.renfei.uaa.repositories.entity.UaaSystemApi;
import net.renfei.uaa.repositories.entity.UaaSystemApiExample;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;

/**
 * 系统API服务
 *
 * @author renfei
 */
@Service
public class SystemApiServiceImpl implements SystemApiService {
    private final UaaSystemApiMapper uaaSystemApiMapper;

    public SystemApiServiceImpl(UaaSystemApiMapper uaaSystemApiMapper) {
        this.uaaSystemApiMapper = uaaSystemApiMapper;
    }

    /**
     * 获取全部系统API列表
     *
     * @return
     */
    @Override
    public APIResult<ListData<SystemApi>> allSystemApiList() {
        UaaSystemApiExample example = new UaaSystemApiExample();
        ListData<SystemApi> systemApiListData = new ListData<>();
        try (Page<UaaSystemApi> page = PageHelper.startPage(1, Integer.MAX_VALUE)) {
            uaaSystemApiMapper.selectByExample(example);
            systemApiListData.setPageNum(page.getPageNum());
            systemApiListData.setPageSize(page.getPageSize());
            systemApiListData.setStartRow(page.getStartRow());
            systemApiListData.setEndRow(page.getEndRow());
            systemApiListData.setTotal(page.getTotal());
            systemApiListData.setPages(page.getPages());
            List<SystemApi> systemApiList = new ArrayList<>();
            for (UaaSystemApi uaaSystemApi : page.getResult()
            ) {
                systemApiList.add(convert(uaaSystemApi));
            }
            systemApiListData.setData(systemApiList);
        }
        return new APIResult<>(systemApiListData);
    }

    private SystemApi convert(UaaSystemApi uaaSystemApi) {
        if (uaaSystemApi == null) {
            return null;
        }
        SystemApi systemApi = new SystemApi();
        systemApi.setId(uaaSystemApi.getId());
        systemApi.setUrlPath(uaaSystemApi.getUrlPath());
        systemApi.setMethodName(uaaSystemApi.getMethodName());
        systemApi.setSummary(uaaSystemApi.getSummary());
        systemApi.setDescription(uaaSystemApi.getDescription());
        return systemApi;
    }
}
