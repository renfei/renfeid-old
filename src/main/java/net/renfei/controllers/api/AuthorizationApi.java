package net.renfei.controllers.api;

import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.tags.Tag;
import net.renfei.model.APIResult;
import net.renfei.model.ReportPublicKeyVO;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;

import java.util.Map;

/**
 * @author renfei
 */
@RequestMapping("/-/api/auth")
@Tag(name = "认证接口", description = "认证接口")
public interface AuthorizationApi {
    /**
     * 向服务器申请服务器公钥
     *
     * @return
     */
    @GetMapping("secretKey")
    @Operation(summary = "向服务器申请服务器公钥", tags = {"认证接口"})
    APIResult<String> getSecretKey();

    /**
     * 上报客户端公钥，并下发AES秘钥
     *
     * @param reportPublicKeyVO
     * @return
     */
    @PostMapping("secretKey")
    APIResult<Map<String, String>> setSecretKey(@RequestBody ReportPublicKeyVO reportPublicKeyVO);
}
