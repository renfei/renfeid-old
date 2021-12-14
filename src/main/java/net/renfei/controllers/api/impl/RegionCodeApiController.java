package net.renfei.controllers.api.impl;

import net.renfei.controllers.BaseController;
import net.renfei.controllers.api.RegionCodeApi;
import net.renfei.model.APIResult;
import net.renfei.model.system.RegionVO;
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

    public APIResult<List<RegionVO>> getRegionByCode() {
        return new APIResult<>(sysService.getRegion(""));
    }

    public APIResult<List<RegionVO>> getRegionByCode(@PathVariable("regionCode") String regionCode) {
        return new APIResult<>(sysService.getRegion(regionCode));
    }
}
