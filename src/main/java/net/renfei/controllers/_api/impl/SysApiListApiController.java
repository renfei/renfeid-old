package net.renfei.controllers._api.impl;

import net.renfei.controllers.BaseController;
import net.renfei.controllers._api.SysApiListApi;
import net.renfei.model.APIResult;
import net.renfei.model.ListData;
import net.renfei.model.system.SysApi;
import net.renfei.services.SysService;
import org.springframework.web.bind.annotation.RestController;

/**
 * 系统接口列表
 * 用于给角色分配权限使用
 *
 * @author renfei
 */
@RestController
public class SysApiListApiController extends BaseController implements SysApiListApi {
    private final SysService sysService;

    public SysApiListApiController(SysService sysService) {
        this.sysService = sysService;
    }

    @Override
    public APIResult<ListData<SysApi>> getSysApiList(String pages, String rows) {
        return new APIResult<>(sysService.getSysApiList(getSignUser(), pages, rows));
    }
}
