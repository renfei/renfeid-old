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
package net.renfei.server.controller.dash.uaa;

import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.Parameter;
import io.swagger.v3.oas.annotations.tags.Tag;
import net.renfei.common.api.constant.APIResult;
import net.renfei.common.core.annotation.OperationLog;
import net.renfei.common.core.entity.OperationTypeEnum;
import net.renfei.common.core.entity.SystemTypeEnum;
import net.renfei.server.controller.AbstractController;
import net.renfei.uaa.api.MenuService;
import net.renfei.uaa.api.entity.MenuTree;
import org.springframework.web.bind.annotation.*;

import java.util.List;

/**
 * 菜单接口（后台）
 *
 * @author renfei
 */
@RestController
@RequestMapping("/_/api/uaa")
@Tag(name = "后台菜单管理接口", description = "后台菜单管理接口")
public class MenuController extends AbstractController {
    private final MenuService menuService;

    public MenuController(MenuService menuService) {
        this.menuService = menuService;
    }

    @GetMapping("menu/tree/all")
    @Operation(summary = "获取全部菜单树", tags = {"后台菜单管理接口"}, description = "这个菜单树是系统全部菜单")
    @OperationLog(module = SystemTypeEnum.SYS_MENU, desc = "获取全部菜单树")
    public APIResult<List<MenuTree>> queryAllMenuTree() {
        return menuService.queryAllMenuTree();
    }

    @GetMapping("menu/tree")
    @Operation(summary = "获取当前登录用户的菜单树", tags = {"后台菜单管理接口"}, description = "这个菜单树是当前登录用户拥有的菜单")
    @OperationLog(module = SystemTypeEnum.SYS_MENU, desc = "获取菜单树")
    public APIResult<List<MenuTree>> queryMenuTreeByUser() {
        return menuService.queryMenuTreeByUser(currentUserDetail());
    }

    @PostMapping("menu")
    @Operation(summary = "创建菜单", tags = {"后台菜单管理接口"})
    @OperationLog(module = SystemTypeEnum.SYS_MENU, desc = "创建菜单", operation = OperationTypeEnum.CREATE)
    public APIResult<MenuTree> createMenu(@RequestBody MenuTree menuTree) {
        return menuService.createMenu(menuTree);
    }

    @PutMapping("menu/{id}")
    @Operation(summary = "修改菜单", tags = {"后台菜单管理接口"}, parameters = {
            @Parameter(name = "id", description = "菜单ID")
    })
    @OperationLog(module = SystemTypeEnum.SYS_MENU, desc = "修改菜单", operation = OperationTypeEnum.UPDATE)
    public APIResult<MenuTree> updateMenu(@PathVariable("id") long menuId, @RequestBody MenuTree menuTree) {
        return menuService.updateMenu(menuId, menuTree);
    }

    @DeleteMapping("menu/{id}")
    @Operation(summary = "删除菜单", tags = {"后台菜单管理接口"}, parameters = {
            @Parameter(name = "id", description = "菜单ID")
    })
    @OperationLog(module = SystemTypeEnum.SYS_MENU, desc = "删除菜单", operation = OperationTypeEnum.DELETE)
    public APIResult deleteMenu(@PathVariable("id") long menuId) {
        return menuService.deleteMenu(menuId);
    }
}
