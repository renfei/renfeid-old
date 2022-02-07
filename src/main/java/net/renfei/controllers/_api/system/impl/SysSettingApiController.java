package net.renfei.controllers._api.system.impl;

import net.renfei.annotation.OperationLog;
import net.renfei.controllers.BaseController;
import net.renfei.controllers._api.system.SysSettingApi;
import net.renfei.model.APIResult;
import net.renfei.model.log.OperationTypeEnum;
import net.renfei.model.system.SystemOperationStatusEnum;
import net.renfei.model.system.SystemTypeEnum;
import net.renfei.services.SysService;
import org.springframework.web.bind.annotation.RestController;

/**
 * 系统设置接口
 *
 * @author renfei
 */
@RestController
public class SysSettingApiController extends BaseController implements SysSettingApi {
    private final SysService sysService;

    public SysSettingApiController(SysService sysService) {
        this.sysService = sysService;
    }

    /**
     * 获取系统运行状态
     *
     * @return
     */
    @Override
    @OperationLog(module = SystemTypeEnum.SYS_SETTING, desc = "获取系统运行状态", operation = OperationTypeEnum.RETRIEVE)
    public APIResult<SystemOperationStatusEnum> querySystemOperationStatus() {
        return new APIResult<>(sysService.querySystemOperationStatus());
    }

    /**
     * 设置系统运行状态
     * OPENE：完全开放，所有功能可以正常使用
     * DEGRADED：降级运行，只接受 GET/HEAD 请求（后台接口和登陆除外）
     * CLOSED：关闭状态，所有请求均被拦截（后台接口和登陆除外）
     *
     * @param systemOperationStatusEnum 状态枚举
     * @return
     */
    @Override
    @OperationLog(module = SystemTypeEnum.SYS_SETTING, desc = "设置系统运行状态", operation = OperationTypeEnum.UPDATE)
    public APIResult settingSystemOperationStatus(SystemOperationStatusEnum systemOperationStatusEnum) {
        sysService.settingSystemOperationStatus(systemOperationStatusEnum);
        return APIResult.success();
    }
}
