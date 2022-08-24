/*
 *   Copyright 2022 RenFei(i@renfei.net)
 *
 * Licensed under the Apache License, Version 2.0 (the "License");
 * you may not use this file except in compliance with the License.
 * You may obtain a copy of the License at
 *
 *   http://www.apache.org/licenses/LICENSE-2.0
 *
 * Unless required by applicable law or agreed to in writing, software
 * distributed under the License is distributed on an "AS IS" BASIS,
 * WITHOUT WARRANTIES OR CONDITIONS OF ANY KIND, either express or implied.
 * See the License for the specific language governing permissions and
 * limitations under the License.
 */
package net.renfei.server.controller.api;

import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.tags.Tag;
import net.renfei.common.api.constant.APIResult;
import net.renfei.common.api.constant.enums.StateCodeEnum;
import net.renfei.common.api.entity.ListData;
import net.renfei.common.api.entity.UserInfo;
import net.renfei.common.api.entity.UserSignInLog;
import net.renfei.common.api.exception.BusinessException;
import net.renfei.common.core.annotation.OperationLog;
import net.renfei.common.core.entity.OperationTypeEnum;
import net.renfei.common.core.entity.SystemTypeEnum;
import net.renfei.common.core.service.SystemService;
import net.renfei.server.controller.AbstractController;
import net.renfei.uaa.api.AuthorizationService;
import net.renfei.uaa.api.entity.*;
import org.springframework.web.bind.annotation.*;

import javax.servlet.http.HttpServletResponse;

/**
 * 认证接口
 *
 * @author renfei
 */
@RestController
@RequestMapping("/api/auth")
@Tag(name = "认证接口", description = "用户认证与鉴权接口")
public class AuthorizationController extends AbstractController {
    private final SystemService systemService;
    private final AuthorizationService authorizationService;

    public AuthorizationController(SystemService systemService,
                                   AuthorizationService authorizationService) {
        this.systemService = systemService;
        this.authorizationService = authorizationService;
    }

    /**
     * 向服务器申请服务器公钥
     *
     * @return
     */
    @GetMapping("secretKey")
    @Operation(summary = "向服务器申请服务器公钥", tags = {"认证接口"})
    @OperationLog(module = SystemTypeEnum.AUTH, desc = "向服务器申请服务器公钥")
    public APIResult<SecretKey> requestServerSecretKey() {
        return authorizationService.requestServerSecretKey();
    }

    /**
     * 上报客户端公钥，并下发AES秘钥
     *
     * @param secretKey
     * @return
     */
    @PostMapping("secretKey")
    @Operation(summary = "上报客户端公钥，并下发AES秘钥", tags = {"认证接口"})
    @OperationLog(module = SystemTypeEnum.AUTH, desc = "上报客户端公钥", operation = OperationTypeEnum.CREATE)
    public APIResult<SecretKey> settingClientSecretKey(@RequestBody SecretKey secretKey) {
        return authorizationService.settingClientSecretKey(secretKey);
    }

    @PostMapping("signIn")
    @Operation(summary = "登录接口", tags = {"认证接口"})
    @OperationLog(module = SystemTypeEnum.AUTH, desc = "登录", operation = OperationTypeEnum.SIGNIN)
    public APIResult<SignInVo> signIn(@RequestBody SignInAo sign) {
        UserDetail userDetail = systemService.currentUserDetail();
        if (userDetail != null) {
            throw new BusinessException("您已经登录，无需再次登录，如需更换账号请退出登录。");
        }
        return authorizationService.signIn(sign, request);
    }

    @PostMapping("signUp")
    @Operation(summary = "注册接口", tags = {"认证接口"})
    @OperationLog(module = SystemTypeEnum.AUTH, desc = "注册", operation = OperationTypeEnum.SIGNUP)
    public APIResult signUp(@RequestBody SignUpAo signUp) {
        UserDetail userDetail = systemService.currentUserDetail();
        if (userDetail != null) {
            throw new BusinessException("您已经登录，无需重复注册。");
        }
        return authorizationService.signUp(signUp, request);
    }

    @DeleteMapping("signOut")
    @Operation(summary = "登出接口", tags = {"认证接口"})
    @OperationLog(module = SystemTypeEnum.AUTH, desc = "登出", operation = OperationTypeEnum.SIGNOUT)
    public APIResult signOut() {
        return authorizationService.signOut(systemService.currentUserDetail(), request);
    }

    @PostMapping("signUp/activation")
    @Operation(summary = "账户激活接口", tags = {"认证接口"})
    @OperationLog(module = SystemTypeEnum.AUTH, desc = "账户激活", operation = OperationTypeEnum.UPDATE)
    public APIResult doSignUpActivation(@RequestBody SignUpActivationAo signUpActivation) {
        authorizationService.activation(signUpActivation);
        return APIResult.success();
    }

    @GetMapping("current/user")
    @Operation(summary = "获取当前登录的用户信息接口", tags = {"认证接口"})
    @OperationLog(module = SystemTypeEnum.AUTH, desc = "获取当前登录的用户信息", operation = OperationTypeEnum.RETRIEVE)
    public APIResult<UserInfo> requestCurrentUserInfo(HttpServletResponse response) {
        UserInfo userInfo = authorizationService.requestCurrentUserInfo();
        if (userInfo == null) {
            response.setStatus(401);
            return APIResult.builder()
                    .code(StateCodeEnum.Unauthorized)
                    .message(StateCodeEnum.Unauthorized.getDescribe())
                    .build();
        }
        return new APIResult<>(userInfo);
    }

    @GetMapping("current/signin/log")
    @Operation(summary = "获取当前登录用户登录日志接口", tags = {"认证接口"})
    @OperationLog(module = SystemTypeEnum.AUTH, desc = "获取当前登录用户登录日志", operation = OperationTypeEnum.RETRIEVE)
    public APIResult<ListData<UserSignInLog>> queryCurrentUserSignInLog(HttpServletResponse response,
                                                                        @RequestParam(value = "pages", required = false, defaultValue = "1") int pages,
                                                                        @RequestParam(value = "rows", required = false, defaultValue = "10") int rows) {
        ListData<UserSignInLog> userSignInLog = authorizationService.queryCurrentUserSignInLog(pages, rows);
        if (userSignInLog == null) {
            response.setStatus(401);
            return APIResult.builder()
                    .code(StateCodeEnum.Unauthorized)
                    .message(StateCodeEnum.Unauthorized.getDescribe())
                    .build();
        }
        return new APIResult<>(userSignInLog);
    }

    @GetMapping("current/u2f/secret")
    @Operation(summary = "生成两步认证秘钥", tags = {"认证接口"})
    @OperationLog(module = SystemTypeEnum.AUTH, desc = "生成两步认证秘钥", operation = OperationTypeEnum.RETRIEVE)
    public APIResult<TotpVo> generateU2FSecretKey(HttpServletResponse response) {
        TotpVo totpVo = authorizationService.generateU2FSecretKey();
        if (totpVo == null) {
            response.setStatus(401);
            return APIResult.builder()
                    .code(StateCodeEnum.Unauthorized)
                    .message(StateCodeEnum.Unauthorized.getDescribe())
                    .build();
        }
        return new APIResult<>(totpVo);
    }

    @PostMapping("current/u2f")
    @Operation(summary = "开启两步认证", tags = {"认证接口"})
    @OperationLog(module = SystemTypeEnum.AUTH, desc = "开启两步认证", operation = OperationTypeEnum.UPDATE)
    public APIResult openU2f(@RequestBody TotpAo totpAo) {
        return authorizationService.openU2f(totpAo);
    }

    @PutMapping("current/u2f")
    @Operation(summary = "关闭两步认证", tags = {"认证接口"})
    @OperationLog(module = SystemTypeEnum.AUTH, desc = "关闭两步认证", operation = OperationTypeEnum.UPDATE)
    public APIResult closeU2f(@RequestBody TotpAo totpAo) {
        return authorizationService.closeU2f(totpAo);
    }

    @PutMapping("current/password")
    @Operation(summary = "修改密码", tags = {"认证接口"})
    @OperationLog(module = SystemTypeEnum.AUTH, desc = "修改密码", operation = OperationTypeEnum.UPDATE)
    public APIResult updatePassword(@RequestBody UpdatePasswordAo updatePassword) {
        return authorizationService.updatePassword(updatePassword);
    }
}
