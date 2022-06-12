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
import net.renfei.uaa.api.entity.MenuTree;
import net.renfei.uaa.api.entity.UserDetail;

import java.util.List;

/**
 * 菜单服务（后台）
 *
 * @author renfei
 */
public interface MenuService {
    /**
     * 查询所有菜单树
     *
     * @return
     */
    APIResult<List<MenuTree>> queryAllMenuTree();

    /**
     * 根据用户查询拥有的菜单树
     *
     * @param userDetail 用户详情
     * @return
     */
    APIResult<List<MenuTree>> queryMenuTreeByUser(UserDetail userDetail);

    /**
     * 创建菜单
     *
     * @param menuTree 菜单实体
     * @return
     */
    APIResult<MenuTree> createMenu(MenuTree menuTree);

    /**
     * 修改菜单
     *
     * @param menuId   菜单ID
     * @param menuTree 菜单实体
     * @return
     */
    APIResult<MenuTree> updateMenu(long menuId, MenuTree menuTree);

    /**
     * 删除菜单
     *
     * @param menuId
     * @return
     */
    APIResult deleteMenu(long menuId);
}
