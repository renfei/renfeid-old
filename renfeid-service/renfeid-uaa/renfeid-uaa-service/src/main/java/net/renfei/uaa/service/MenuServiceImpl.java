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

import net.renfei.common.api.constant.APIResult;
import net.renfei.common.api.constant.enums.StateCodeEnum;
import net.renfei.uaa.api.MenuService;
import net.renfei.uaa.api.entity.AuthorityTypeEnum;
import net.renfei.uaa.api.entity.MenuTree;
import net.renfei.uaa.api.entity.RoleDetail;
import net.renfei.uaa.api.entity.UserDetail;
import net.renfei.uaa.repositories.UaaMenuMapper;
import net.renfei.uaa.repositories.entity.UaaMenuExample;
import net.renfei.uaa.repositories.entity.UaaMenuWithBLOBs;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.Date;
import java.util.List;

/**
 * 菜单服务（后台）
 *
 * @author renfei
 */
@Service
public class MenuServiceImpl implements MenuService {
    private final UaaMenuMapper uaaMenuMapper;

    public MenuServiceImpl(UaaMenuMapper uaaMenuMapper) {
        this.uaaMenuMapper = uaaMenuMapper;
    }

    public APIResult<List<MenuTree>> queryAllMenuTree() {
        UaaMenuExample example = new UaaMenuExample();
        example.setOrderByClause("menu_order");
        example.createCriteria()
                .andPidIsNull();
        List<UaaMenuWithBLOBs> uaaMenuList = uaaMenuMapper.selectByExampleWithBLOBs(example);
        List<MenuTree> menuTreeList = new ArrayList<>();
        for (UaaMenuWithBLOBs uaaMenu : uaaMenuList
        ) {
            menuTreeList.add(convert(uaaMenu));
        }
        queryMenuTree(null, menuTreeList);
        return new APIResult<>(menuTreeList);
    }

    @Override
    public APIResult<List<MenuTree>> queryMenuTreeByUser(UserDetail userDetail) {
        List<Long> menuIds = new ArrayList<>();
        for (RoleDetail roleDetail : userDetail.getRoleDetailList()
        ) {
            if (roleDetail.getAuthorityList() != null && !roleDetail.getAuthorityList().isEmpty()) {
                roleDetail.getAuthorityList()
                        .stream()
                        .filter(authority -> AuthorityTypeEnum.MENU.equals(authority.getAuthorityType()))
                        .forEach(authority -> menuIds.add(authority.getTargetId()));
            }
        }
        UaaMenuExample example = new UaaMenuExample();
        example.setOrderByClause("menu_order");
        example.createCriteria()
                .andIdIn(menuIds)
                .andEnableEqualTo(true)
                .andPidIsNull();
        List<UaaMenuWithBLOBs> uaaMenuList = uaaMenuMapper.selectByExampleWithBLOBs(example);
        List<MenuTree> menuTreeList = new ArrayList<>();
        for (UaaMenuWithBLOBs uaaMenu : uaaMenuList
        ) {
            menuTreeList.add(convert(uaaMenu));
        }
        queryMenuTree(menuIds, menuTreeList);
        return new APIResult<>(menuTreeList);
    }

    @Override
    public APIResult<MenuTree> createMenu(MenuTree menuTree) {
        if (menuTree.getMenuName() == null || menuTree.getMenuName().isEmpty()) {
            return APIResult.builder()
                    .code(StateCodeEnum.Failure)
                    .message("菜单名称不能为空")
                    .build();
        }
        UaaMenuWithBLOBs uaaMenu = convert(menuTree);
        uaaMenu.setId(null);
        uaaMenu.setAddTime(new Date());
        uaaMenuMapper.insertSelective(uaaMenu);
        return new APIResult<>(convert(uaaMenu));
    }

    @Override
    public APIResult<MenuTree> updateMenu(long menuId, MenuTree menuTree) {
        UaaMenuWithBLOBs oldUaaMenu = uaaMenuMapper.selectByPrimaryKey(menuId);
        if (oldUaaMenu == null) {
            return APIResult.builder()
                    .code(StateCodeEnum.Failure)
                    .message("根据菜单ID未找到菜单，请重试")
                    .build();
        }
        UaaMenuWithBLOBs uaaMenu = convert(menuTree);
        uaaMenu.setId(menuId);
        uaaMenu.setAddTime(oldUaaMenu.getAddTime());
        uaaMenuMapper.updateByPrimaryKeyWithBLOBs(uaaMenu);
        return new APIResult<>(convert(uaaMenu));
    }

    public APIResult deleteMenu(long menuId) {
        uaaMenuMapper.deleteByPrimaryKey(menuId);
        return APIResult.success();
    }

    private void queryMenuTree(List<Long> menuIds, List<MenuTree> menuTreeList) {
        for (MenuTree menuTree : menuTreeList
        ) {
            UaaMenuExample example = new UaaMenuExample();
            example.setOrderByClause("menu_order");
            UaaMenuExample.Criteria criteria = example.createCriteria().andPidEqualTo(menuTree.getId());
            if (menuIds != null && !menuIds.isEmpty()) {
                criteria.andIdIn(menuIds).andEnableEqualTo(true);
            }
            List<UaaMenuWithBLOBs> uaaMenuList = uaaMenuMapper.selectByExampleWithBLOBs(example);
            if (!uaaMenuList.isEmpty()) {
                List<MenuTree> childMenuTreeList = new ArrayList<>();
                for (UaaMenuWithBLOBs uaaMenu : uaaMenuList
                ) {
                    childMenuTreeList.add(convert(uaaMenu));
                }
                menuTree.setChild(childMenuTreeList);
                queryMenuTree(menuIds, childMenuTreeList);
            }
        }
    }

    private MenuTree convert(UaaMenuWithBLOBs uaaMenu) {
        MenuTree menuTree = new MenuTree();
        menuTree.setId(uaaMenu.getId());
        menuTree.setPid(uaaMenu.getPid());
        menuTree.setMenuName(uaaMenu.getMenuName());
        menuTree.setMenuIcon(uaaMenu.getMenuIcon());
        menuTree.setMenuTarget(uaaMenu.getMenuTarget());
        menuTree.setMenuClass(uaaMenu.getMenuClass());
        menuTree.setMenuTitle(uaaMenu.getMenuTitle());
        menuTree.setMenuOnclick(uaaMenu.getMenuOnclick());
        menuTree.setMenuOrder(uaaMenu.getMenuOrder());
        menuTree.setEnable(uaaMenu.getEnable());
        menuTree.setAddTime(uaaMenu.getAddTime());
        menuTree.setUpdateTime(uaaMenu.getUpdateTime());
        menuTree.setMenuHref(uaaMenu.getMenuHref());
        menuTree.setMenuCss(uaaMenu.getMenuCss());
        menuTree.setExtendJson(uaaMenu.getExtendJson());
        return menuTree;
    }

    private UaaMenuWithBLOBs convert(MenuTree menuTree) {
        UaaMenuWithBLOBs uaaMenu = new UaaMenuWithBLOBs();
        uaaMenu.setId(menuTree.getId());
        uaaMenu.setPid(menuTree.getPid());
        uaaMenu.setMenuName(menuTree.getMenuName());
        uaaMenu.setMenuIcon(menuTree.getMenuIcon());
        uaaMenu.setMenuTarget(menuTree.getMenuTarget());
        uaaMenu.setMenuClass(menuTree.getMenuClass());
        uaaMenu.setMenuTitle(menuTree.getMenuTitle());
        uaaMenu.setMenuOnclick(menuTree.getMenuOnclick());
        uaaMenu.setMenuOrder(menuTree.getMenuOrder());
        uaaMenu.setEnable(menuTree.getEnable());
        uaaMenu.setAddTime(menuTree.getAddTime());
        uaaMenu.setUpdateTime(menuTree.getUpdateTime());
        uaaMenu.setMenuHref(menuTree.getMenuHref());
        uaaMenu.setMenuCss(menuTree.getMenuCss());
        uaaMenu.setExtendJson(menuTree.getExtendJson());
        return uaaMenu;
    }
}
