package net.renfei.controllers._api.system.impl;

import net.renfei.annotation.OperationLog;
import net.renfei.controllers.BaseController;
import net.renfei.controllers._api.system.SysRoleApi;
import net.renfei.model.APIResult;
import net.renfei.model.ListData;
import net.renfei.model.log.OperationTypeEnum;
import net.renfei.model.system.SystemTypeEnum;
import net.renfei.repositories.model.SysRole;
import net.renfei.services.SysService;
import org.springframework.web.bind.annotation.RestController;

/**
 * 系统角色与权限接口
 *
 * @author renfei
 */
@RestController
public class SysRoleApiController extends BaseController implements SysRoleApi {
    private final SysService sysService;

    public SysRoleApiController(SysService sysService) {
        this.sysService = sysService;
    }

    @Override
    @OperationLog(module = SystemTypeEnum.SYS_ROLE, desc = "获取系统角色列表", operation = OperationTypeEnum.RETRIEVE)
    public APIResult<ListData<SysRole>> getSysRoleList(String pages, String rows) {
        return new APIResult<>(sysService.getSysRoleList(getSignUser(), pages, rows));
    }

    @Override
    @OperationLog(module = SystemTypeEnum.SYS_ROLE, desc = "添加系统角色", operation = OperationTypeEnum.CREATE)
    public APIResult addSysRole(SysRole sysRole) {
        sysService.addSysRole(getSignUser(), sysRole);
        return APIResult.success();
    }

    @Override
    @OperationLog(module = SystemTypeEnum.SYS_ROLE, desc = "修改系统角色", operation = OperationTypeEnum.UPDATE)
    public APIResult updateSysRole(Long id, SysRole sysRole) {
        sysRole.setId(id);
        sysService.updateSysRole(getSignUser(), sysRole);
        return APIResult.success();
    }

    @Override
    @OperationLog(module = SystemTypeEnum.SYS_ROLE, desc = "删除系统角色", operation = OperationTypeEnum.DELETE)
    public APIResult deleteSysRole(Long id) {
        sysService.deleteSysRole(getSignUser(), id);
        return APIResult.success();
    }
}
