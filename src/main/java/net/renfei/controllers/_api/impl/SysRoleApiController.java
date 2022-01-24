package net.renfei.controllers._api.impl;

import net.renfei.annotation.OperationLog;
import net.renfei.controllers.BaseController;
import net.renfei.controllers._api.SysRoleApi;
import net.renfei.exception.ForbiddenException;
import net.renfei.model.APIResult;
import net.renfei.model.ListData;
import net.renfei.model.StateCodeEnum;
import net.renfei.model.log.OperationTypeEnum;
import net.renfei.model.system.SystemTypeEnum;
import net.renfei.repositories.model.SysRole;
import net.renfei.services.SysService;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.web.bind.annotation.RestController;

/**
 * 系统角色与权限接口
 *
 * @author renfei
 */
@RestController
public class SysRoleApiController extends BaseController implements SysRoleApi {
    private static final Logger logger = LoggerFactory.getLogger(SysRoleApiController.class);
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
        try {
            sysService.addSysRole(getSignUser(), sysRole);
        } catch (ForbiddenException e) {
            logger.warn("当前用户：{}，告警信息：{}", getSignUser(), e.getMessage());
            return APIResult.builder()
                    .code(StateCodeEnum.Forbidden)
                    .message(e.getMessage())
                    .build();
        }
        return APIResult.success();
    }

    @Override
    @OperationLog(module = SystemTypeEnum.SYS_ROLE, desc = "修改系统角色", operation = OperationTypeEnum.UPDATE)
    public APIResult updateSysRole(Long id, SysRole sysRole) {
        sysRole.setId(id);
        try {
            sysService.updateSysRole(getSignUser(), sysRole);
        } catch (ForbiddenException e) {
            logger.warn("当前用户：{}，告警信息：{}", getSignUser(), e.getMessage());
            return APIResult.builder()
                    .code(StateCodeEnum.Forbidden)
                    .message(e.getMessage())
                    .build();
        }
        return APIResult.success();
    }

    @Override
    @OperationLog(module = SystemTypeEnum.SYS_ROLE, desc = "删除系统角色", operation = OperationTypeEnum.DELETE)
    public APIResult deleteSysRole(Long id) {
        try {
            sysService.deleteSysRole(getSignUser(), id);
        } catch (ForbiddenException e) {
            logger.warn("当前用户：{}，告警信息：{}", getSignUser(), e.getMessage());
            return APIResult.builder()
                    .code(StateCodeEnum.Forbidden)
                    .message(e.getMessage())
                    .build();
        }
        return APIResult.success();
    }
}
