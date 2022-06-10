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
package net.renfei.uaa.api;

import net.renfei.common.api.constant.APIResult;
import net.renfei.uaa.api.entity.AuthorityTypeEnum;
import net.renfei.uaa.api.entity.RoleDetail;

import java.util.List;

/**
 * 角色服务
 *
 * @author renfei
 */
public interface RoleService {
    /**
     * 获取全部角色列表
     *
     * @param haveBuiltInRoles 是否包含内置角色
     * @return 角色列表
     */
    APIResult<List<RoleDetail>> allRoleList(boolean haveBuiltInRoles, int pages, int rows);

    /**
     * 根据资源ID获取所需的角色列表
     *
     * @param authorityTypeEnum 资源类型
     * @param resourceId        资源ID
     * @return 所需的角色列表
     */
    APIResult<List<RoleDetail>> queryRoleListByResource(AuthorityTypeEnum authorityTypeEnum, long resourceId);

    /**
     * 根据用户ID获取拥有的角色列表
     *
     * @param userId 用户ID
     * @return 用户拥有的角色列表
     */
    APIResult<List<RoleDetail>> queryRoleListByUser(long userId, int pages, int rows);
}
