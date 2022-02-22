package net.renfei.api.controller;

import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.tags.Tag;
import net.renfei.exception.IP2LocationException;
import net.renfei.ip2location.IPResult;
import net.renfei.model.APIResult;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;

@RequestMapping("/api")
@Tag(name = "IP地址信息", description = "IP地址信息查询接口")
public interface IP2LocationApi {

    @GetMapping("ip")
    @Operation(summary = "查询本机的IP地址信息", tags = {"IP地址信息"})
    APIResult<IPResult> queryIpInfo() throws IP2LocationException;

    @GetMapping("ip/{ip}")
    @Operation(summary = "查询指定的IP地址信息", tags = {"IP地址信息"})
    APIResult<IPResult> queryIpInfo(@PathVariable("ip") String ip) throws IP2LocationException;
}
