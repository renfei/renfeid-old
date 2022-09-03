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
package net.renfei.common.core.service;

import net.renfei.common.api.constant.APIResult;
import net.renfei.common.core.entity.SiteFriendlyLinkVo;
import net.renfei.common.core.repositories.entity.CoreSiteFriendlyLinkWithBLOBs;

import java.util.List;

/**
 * 友情链接服务
 *
 * @author renfei
 */
public interface SiteFriendlyLinkService {
    /**
     * 查询友情链接列表
     *
     * @return
     */
    List<SiteFriendlyLinkVo> queryFriendlyLink();

    /**
     * 查询全部友情链接列表
     *
     * @return
     */
    List<CoreSiteFriendlyLinkWithBLOBs> queryAllFriendlyLink();

    /**
     * 创建友情链接
     *
     * @param friendlyLink
     */
    APIResult createFriendlyLink(CoreSiteFriendlyLinkWithBLOBs friendlyLink);

    /**
     * 修改友情链接
     *
     * @param friendlyLink
     * @return
     */
    APIResult updateFriendlyLink(CoreSiteFriendlyLinkWithBLOBs friendlyLink);

    /**
     * 删除友情链接
     *
     * @param id
     * @return
     */
    APIResult deleteFriendlyLink(long id);
}
