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
import net.renfei.common.api.entity.ListData;
import net.renfei.uaa.api.entity.Authority;
import net.renfei.uaa.api.entity.AuthorityTypeEnum;
import net.renfei.uaa.api.entity.RoleDetail;

import javax.servlet.http.HttpServletRequest;
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
    APIResult<ListData<RoleDetail>> queryRoleList(boolean haveBuiltInRoles, String roleName, int pages, int rows);

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

    /**
     * 给用户授予角色
     *
     * @param userId         用户ID
     * @param roleDetailList 角色列表
     * @param request        请求对象
     * @return
     */
    APIResult<List<RoleDetail>> authorizationRoleByUser(long userId, List<RoleDetail> roleDetailList, HttpServletRequest request);

    /**
     * 查询角色拥有的权限列表
     *
     * @param roleId 角色ID
     * @return
     */
    APIResult<List<Authority>> queryAuthorityListByRole(long roleId);

    /**
     * 创建角色
     *
     * @param roleDetail 角色详情
     * @param request    请求对象
     * @return
     */
    APIResult<RoleDetail> createRole(RoleDetail roleDetail, HttpServletRequest request);

    /**
     * 修改角色
     *
     * @param roleId     角色ID
     * @param roleDetail 角色详情
     * @param request    请求对象
     * @return
     */
    APIResult<RoleDetail> updateRole(long roleId, RoleDetail roleDetail, HttpServletRequest request);

    /**
     * 删除角色
     *
     * @param roleId  角色ID
     * @param request 请求对象
     * @return
     */
    APIResult deleteRole(long roleId, HttpServletRequest request);
}
