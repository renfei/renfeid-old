package net.renfei.controllers.api;

import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.tags.Tag;
import net.renfei.controllers.BaseController;
import net.renfei.model.APIResult;
import net.renfei.model.system.RegionVO;
import net.renfei.services.SysService;
import org.springframework.context.annotation.Lazy;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

/**
 * 开放API接口（中国行政区划）
 *
 * @author renfei
 */
@Lazy
@RestController
@RequestMapping("/api")
@Tag(name = "开放API接口（中国行政区划）")
public class RegionCodeController extends BaseController {
    private final SysService sysService;

    public RegionCodeController(SysService sysService) {
        this.sysService = sysService;
    }

    @GetMapping("regionCode")
    @Operation(summary = "查询省、直辖市、自治区、特别行政区")
    public APIResult<List<RegionVO>> getRegionByCode() {
        return new APIResult<>(sysService.getRegion(""));
    }

    @GetMapping("regionCode/{regionCode}")
    @Operation(summary = "查询下级行政区划")
    public APIResult<List<RegionVO>> getRegionByCode(@PathVariable("regionCode") String regionCode) {
        return new APIResult<>(sysService.getRegion(regionCode));
    }
}
