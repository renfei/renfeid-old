package net.renfei.controllers.api;

import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.tags.Tag;
import net.renfei.model.APIResult;
import net.renfei.model.system.RegionVO;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;

import java.util.List;

@RequestMapping("/api")
@Tag(name = "中国行政区划编码查询接口", description = "中国行政区划编码查询接口")
public interface RegionCodeApi {

    @Operation(summary = "查询省、直辖市、自治区、特别行政区", tags = {"中国行政区划编码查询接口"})
    @GetMapping("regionCode")
    APIResult<List<RegionVO>> getRegionByCode();

    @GetMapping("regionCode/{regionCode}")
    @Operation(summary = "查询下级行政区划", tags = {"中国行政区划编码查询接口"})
    APIResult<List<RegionVO>> getRegionByCode(@PathVariable("regionCode") String regionCode);
}
