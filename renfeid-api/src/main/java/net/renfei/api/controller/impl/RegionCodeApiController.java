package net.renfei.api.controller.impl;

import net.renfei.annotation.OperationLog;
import net.renfei.controller.BaseController;
import net.renfei.api.controller.RegionCodeApi;
import net.renfei.model.APIResult;
import net.renfei.model.system.RegionVO;
import net.renfei.model.system.SystemTypeEnum;
import net.renfei.services.SysService;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

/**
 * 开放API接口（中国行政区划）
 *
 * @author renfei
 */
@RestController
public class RegionCodeApiController extends BaseController implements RegionCodeApi {
    private final SysService sysService;

    public RegionCodeApiController(SysService sysService) {
        this.sysService = sysService;
    }

    @Override
    @OperationLog(module = SystemTypeEnum.API, desc = "访问行政区划查询接口")
    public APIResult<List<RegionVO>> getRegionByCode() {
        return new APIResult<>(sysService.getRegion(""));
    }

    @Override
    @OperationLog(module = SystemTypeEnum.API, desc = "访问行政区划查询接口")
    public APIResult<List<RegionVO>> getRegionByCode(@PathVariable("regionCode") String regionCode) {
        return new APIResult<>(sysService.getRegion(regionCode));
    }
}
