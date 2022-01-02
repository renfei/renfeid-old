package net.renfei.controllers.api.impl;

import net.renfei.controllers.BaseController;
import net.renfei.controllers.api.AuthorizationApi;
import net.renfei.model.APIResult;
import net.renfei.model.ReportPublicKeyVO;
import net.renfei.model.StateCodeEnum;
import net.renfei.services.SysService;
import net.renfei.utils.JwtTokenUtils;
import org.springframework.util.ObjectUtils;
import org.springframework.web.bind.annotation.RestController;

import java.util.Map;

/**
 * 认证接口
 *
 * @author renfei
 */
@RestController
public class AuthorizationApiController extends BaseController implements AuthorizationApi {
    private final JwtTokenUtils jwtUtils;
    private final SysService sysService;

    public AuthorizationApiController(JwtTokenUtils jwtUtils, SysService sysService) {
        this.jwtUtils = jwtUtils;
        this.sysService = sysService;
    }

    /**
     * 向服务器申请服务器公钥
     *
     * @return
     */
    @Override
    public APIResult<String> getSecretKey() {
        Map<Integer, String> map = sysService.secretKey();
        if (ObjectUtils.isEmpty(map)) {
            return APIResult.builder()
                    .code(StateCodeEnum.Error)
                    .build();
        }
        return APIResult.builder()
                .code(StateCodeEnum.OK)
                .message(map.get(1))
                .data(map.get(0))
                .build();
    }

    /**
     * 上报客户端公钥，并下发AES秘钥
     *
     * @param reportPublicKeyVO
     * @return
     */
    @Override
    public APIResult<Map<String, String>> setSecretKey(ReportPublicKeyVO reportPublicKeyVO) {
        return new APIResult<>(sysService.setSecretKey(reportPublicKeyVO));
    }
}
