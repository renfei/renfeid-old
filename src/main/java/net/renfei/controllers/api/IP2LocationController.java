package net.renfei.controllers.api;

import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.tags.Tag;
import net.renfei.controllers.BaseController;
import net.renfei.domain.ip2location.IPResult;
import net.renfei.exception.IP2LocationException;
import net.renfei.model.APIResult;
import net.renfei.model.StateCode;
import net.renfei.services.IP2LocationService;
import net.renfei.utils.IpUtils;
import org.springframework.context.annotation.Lazy;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

/**
 * 开放API接口（IP地址信息）
 *
 * @author renfei
 */
@Lazy
@RestController
@RequestMapping("/api")
@Tag(name = "开放API接口（IP地址信息）")
public class IP2LocationController extends BaseController {
    private final IP2LocationService ip2LocationService;

    public IP2LocationController(IP2LocationService ip2LocationService) {
        this.ip2LocationService = ip2LocationService;
    }

    @GetMapping("ip")
    @Operation(summary = "查询本机的IP地址信息")
    public APIResult<IPResult> queryIpInfo() {
        try {
            return new APIResult<>(ip2LocationService.ipQuery(IpUtils.getIpAddress(request)));
        } catch (IP2LocationException ip2LocationException) {
            return APIResult.builder()
                    .code(StateCode.Failure)
                    .message(ip2LocationException.getMessage())
                    .build();
        }
    }

    @GetMapping("ip/{ip}")
    @Operation(summary = "查询指定的IP地址信息")
    public APIResult<IPResult> queryIpInfo(@PathVariable("ip") String ip) {
        try {
            return new APIResult<>(ip2LocationService.ipQuery(ip));
        } catch (IP2LocationException ip2LocationException) {
            return APIResult.builder()
                    .code(StateCode.Failure)
                    .message(ip2LocationException.getMessage())
                    .build();
        }
    }
}
