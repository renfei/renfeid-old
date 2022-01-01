package net.renfei.controllers.api.impl;

import net.renfei.controllers.BaseController;
import net.renfei.controllers.api.IP2LocationApi;
import net.renfei.exception.IP2LocationException;
import net.renfei.ip2location.IPResult;
import net.renfei.model.APIResult;
import net.renfei.model.StateCodeEnum;
import net.renfei.services.IP2LocationService;
import net.renfei.utils.IpUtils;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RestController;

/**
 * 开放API接口（IP地址信息）
 *
 * @author renfei
 */
@RestController
public class IP2LocationApiController extends BaseController implements IP2LocationApi {
    private final IP2LocationService ip2LocationService;

    public IP2LocationApiController(IP2LocationService ip2LocationService) {
        this.ip2LocationService = ip2LocationService;
    }

    @Override
    public APIResult<IPResult> queryIpInfo() throws IP2LocationException {
        return new APIResult<>(ip2LocationService.ipQuery(IpUtils.getIpAddress(request)));
    }

    @Override
    public APIResult<IPResult> queryIpInfo(@PathVariable("ip") String ip) throws IP2LocationException {
        return new APIResult<>(ip2LocationService.ipQuery(ip));
    }
}
