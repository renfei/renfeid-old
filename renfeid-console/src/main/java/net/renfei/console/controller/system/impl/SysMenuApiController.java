package net.renfei.console.controller.system.impl;

import net.renfei.annotation.OperationLog;
import net.renfei.console.controller.system.SysMenuApi;
import net.renfei.controller.BaseController;
import net.renfei.model.APIResult;
import net.renfei.model.ListData;
import net.renfei.model.log.OperationTypeEnum;
import net.renfei.model.system.MenuDataItemVo;
import net.renfei.model.system.SystemTypeEnum;
import net.renfei.repositories.model.SysMenu;
import net.renfei.services.SysService;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

/**
 * 系统菜单接口
 *
 * @author renfei
 */
@RestController
public class SysMenuApiController extends BaseController implements SysMenuApi {
    private final SysService sysService;

    public SysMenuApiController(SysService sysService) {
        this.sysService = sysService;
    }

    /**
     * 根据登陆的人权限获取菜单树
     * 超管可以获取全部菜单
     *
     * @return
     */
    @Override
    @OperationLog(module = SystemTypeEnum.SYS_MENU, desc = "获取系统菜单树", operation = OperationTypeEnum.RETRIEVE)
    public APIResult<List<MenuDataItemVo>> getMenuTreeByUser() {
        return new APIResult<>(sysService.getMenuTreeByUser(getSignUser()));
    }

    /**
     * 获取系统菜单列表
     * 超管和安全管理员可以获取全部，用分配权限，其他人只能获取到自己拥有的菜单
     *
     * @param pages 页码
     * @param rows  每页行数
     * @return
     */
    @Override
    @OperationLog(module = SystemTypeEnum.SYS_MENU, desc = "获取系统菜列表", operation = OperationTypeEnum.RETRIEVE)
    public APIResult<ListData<SysMenu>> getMenuList(String pages, String rows) {
        return new APIResult<>(sysService.getSysMenuList(getSignUser(), pages, rows));
    }

    /**
     * 添加系统菜单
     *
     * @param sysMenu 系统菜单
     */
    @Override
    @OperationLog(module = SystemTypeEnum.SYS_MENU, desc = "添加系统菜", operation = OperationTypeEnum.CREATE)
    public APIResult addSysMenu(SysMenu sysMenu) {
        sysService.addSysMenu(getSignUser(), sysMenu);
        return APIResult.success();
    }

    /**
     * 修改系统菜单
     *
     * @param sysMenu 系统菜单
     */
    @Override
    @OperationLog(module = SystemTypeEnum.SYS_MENU, desc = "修改系统菜单", operation = OperationTypeEnum.UPDATE)
    public APIResult updateSysMenu(Long id, SysMenu sysMenu) {
        sysMenu.setId(id);
        sysService.updateSysMenu(getSignUser(), sysMenu);
        return APIResult.success();
    }

    /**
     * 删除系统菜单
     * 下面的子菜单不会被删除，而是会断开树形链接
     *
     * @param id
     * @return
     */
    @Override
    @OperationLog(module = SystemTypeEnum.SYS_MENU, desc = "删除系统菜单", operation = OperationTypeEnum.DELETE)
    public APIResult deleteSysMenu(Long id) {
        sysService.deleteSysMenu(getSignUser(), id);
        return APIResult.success();
    }
}
