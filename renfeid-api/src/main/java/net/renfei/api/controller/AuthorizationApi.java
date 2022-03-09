package net.renfei.api.controller;

import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.tags.Tag;
import net.renfei.annotation.OperationLog;
import net.renfei.exception.NeedU2FException;
import net.renfei.model.APIResult;
import net.renfei.model.ReportPublicKeyVO;
import net.renfei.model.auth.SignInSuccessVO;
import net.renfei.model.auth.SignInVO;
import net.renfei.model.auth.SignUpActivationVO;
import net.renfei.model.auth.SignUpVO;
import net.renfei.model.log.OperationTypeEnum;
import net.renfei.model.system.SystemTypeEnum;
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
    @OperationLog(module = SystemTypeEnum.AUTH, desc = "向服务器申请服务器公钥")
    APIResult<String> getSecretKey();

    /**
     * 上报客户端公钥，并下发AES秘钥
     *
     * @param reportPublicKeyVO
     * @return
     */
    @PostMapping("secretKey")
    @Operation(summary = "上报客户端公钥，并下发AES秘钥", tags = {"认证接口"})
    @OperationLog(module = SystemTypeEnum.AUTH, desc = "上报客户端公钥", operation = OperationTypeEnum.CREATE)
    APIResult<Map<String, String>> setSecretKey(@RequestBody ReportPublicKeyVO reportPublicKeyVO);

    /**
     * 登陆
     *
     * @param signInVO
     * @return
     */
    @PostMapping("signIn")
    @Operation(summary = "登陆接口", tags = {"认证接口"})
    APIResult<SignInSuccessVO> doSignIn(@RequestBody SignInVO signInVO) throws NeedU2FException;

    /**
     * 注册接口
     *
     * @param signUpVO
     * @return
     */
    @PostMapping("signUp")
    @Operation(summary = "注册接口", tags = {"认证接口"})
    APIResult doSignUp(@RequestBody SignUpVO signUpVO);

    /**
     * 注册接口
     *
     * @param signUpActivationVO
     * @return
     */
    @PostMapping("signUp/activation")
    @Operation(summary = "账户激活接口", tags = {"认证接口"})
    APIResult doSignUpActivation(@RequestBody SignUpActivationVO signUpActivationVO);
}