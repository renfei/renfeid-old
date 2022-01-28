package net.renfei.controllers._api.system;

import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.tags.Tag;
import net.renfei.model.APIResult;
import net.renfei.model.ListData;
import net.renfei.model.system.MenuDataItemVo;
import net.renfei.repositories.model.SysMenu;
import org.springframework.web.bind.annotation.*;

import java.util.List;

/**
 * 系统菜单接口
 *
 * @author renfei
 */
@Tag(name = "系统菜单接口", description = "系统菜单接口，用于获取菜单和管理菜单")
@RequestMapping("/_/api")
public interface SysMenuApi {
    /**
     * 获取系统菜单树
     *
     * @return
     */
    @GetMapping("sys/menu/tree")
    @Operation(summary = "获取系统菜单树", tags = {"系统菜单接口"}, description = "获取后台左侧菜单树")
    APIResult<List<MenuDataItemVo>> getMenuTreeByUser();

    /**
     * 获取系统菜单列表
     *
     * @param pages 页码
     * @param rows  每页行数
     * @return
     */
    @GetMapping("sys/menu")
    @Operation(summary = "获取系统菜单列表", tags = {"系统菜单接口"},
            description = "获取后台菜单列表，超管和安全管理员可以获取全部，其他人只能获取到自己拥有的菜单")
    APIResult<ListData<SysMenu>> getMenuList(@RequestParam(value = "pages", required = false) String pages,
                                             @RequestParam(value = "rows", required = false) String rows);

    @PostMapping("sys/menu")
    @Operation(summary = "添加系统菜单", tags = {"系统菜单接口"}, description = "添加系统菜单")
    APIResult addSysMenu(@RequestBody SysMenu sysMenu);

    @PutMapping("sys/menu/{id}")
    @Operation(summary = "修改系统菜单", tags = {"系统菜单接口"}, description = "修改系统菜单")
    APIResult updateSysMenu(@PathVariable("id") Long id, @RequestBody SysMenu sysMenu);

    @DeleteMapping("sys/menu/{id}")
    @Operation(summary = "删除系统菜单", tags = {"系统菜单接口"},
            description = "删除系统菜单，下面的子菜单不会被删除，而是会断开树形链接")
    APIResult deleteSysMenu(@PathVariable("id") Long id);
}
