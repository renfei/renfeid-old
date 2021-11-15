package net.renfei.controllers.api;

import net.renfei.controllers.BaseController;
import net.renfei.model.APIResult;
import net.renfei.model.system.RegionVO;
import net.renfei.services.SysService;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

/**
 * 行政区划代码
 *
 * @author renfei
 */
@RestController
@RequestMapping("/api")
public class RegionCodeController extends BaseController {
    private final SysService sysService;

    public RegionCodeController(SysService sysService) {
        this.sysService = sysService;
    }

    @GetMapping("regionCode")
    public APIResult<List<RegionVO>> getRegionByCode() {
        return new APIResult<>(sysService.getRegion(""));
    }

    @GetMapping("regionCode/{regionCode}")
    public APIResult<List<RegionVO>> getRegionByCode(@PathVariable("regionCode") String regionCode) {
        return new APIResult<>(sysService.getRegion(regionCode));
    }
}
