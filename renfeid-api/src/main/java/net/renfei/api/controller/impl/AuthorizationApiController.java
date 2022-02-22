package net.renfei.api.controller.impl;

import net.renfei.annotation.OperationLog;
import net.renfei.controller.BaseController;
import net.renfei.api.controller.AuthorizationApi;
import net.renfei.domain.UserDomain;
import net.renfei.domain.user.User;
import net.renfei.exception.BusinessException;
import net.renfei.exception.NeedU2FException;
import net.renfei.model.APIResult;
import net.renfei.model.ReportPublicKeyVO;
import net.renfei.model.StateCodeEnum;
import net.renfei.model.auth.*;
import net.renfei.model.log.OperationTypeEnum;
import net.renfei.model.system.SystemTypeEnum;
import net.renfei.model.system.UserDetail;
import net.renfei.services.AccountService;
import net.renfei.services.SysService;
import net.renfei.utils.JwtTokenUtils;
import net.renfei.utils.PasswordUtils;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.util.ObjectUtils;
import org.springframework.web.bind.annotation.RestController;

import java.util.Map;

import static net.renfei.config.SystemConfig.MAX_USERNAME_LENGTH;
import static net.renfei.config.SystemConfig.SESSION_KEY;

/**
 * 认证接口
 *
 * @author renfei
 */
@RestController
public class AuthorizationApiController extends BaseController implements AuthorizationApi {
    private static final Logger logger = LoggerFactory.getLogger(AuthorizationApiController.class);
    private final JwtTokenUtils jwtUtils;
    private final SysService sysService;
    private final AccountService accountService;

    public AuthorizationApiController(JwtTokenUtils jwtUtils,
                                      SysService sysService,
                                      AccountService accountService) {
        this.jwtUtils = jwtUtils;
        this.sysService = sysService;
        this.accountService = accountService;
    }

    /**
     * 向服务器申请服务器公钥
     *
     * @return
     */
    @Override
    @OperationLog(module = SystemTypeEnum.API, desc = "向服务器申请服务器公钥", operation = OperationTypeEnum.CREATE)
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
    @OperationLog(module = SystemTypeEnum.API, desc = "上报客户端公钥，并下发AES秘钥", operation = OperationTypeEnum.CREATE)
    public APIResult<Map<String, String>> setSecretKey(ReportPublicKeyVO reportPublicKeyVO) {
        return new APIResult<>(sysService.setSecretKey(reportPublicKeyVO));
    }

    /**
     * 登陆
     *
     * @param signInVO
     * @return
     */
    @Override
    @OperationLog(module = SystemTypeEnum.API, desc = "访问登陆接口", operation = OperationTypeEnum.SIGNIN)
    public APIResult<SignInSuccessVO> doSignIn(SignInVO signInVO) throws NeedU2FException {
        if (getSignUser() != null) {
            return APIResult.builder()
                    .code(StateCodeEnum.OK)
                    .message("已登陆无需再次登陆。")
                    .data("")
                    .build();
        }
        signInVO.setUserName(signInVO.getUserName().trim().toLowerCase());
        signInVO.setPassword(sysService.decrypt(signInVO.getPassword(), signInVO.getKeyUuid()));
        // 用户登陆服务
        User user = accountService.signIn(signInVO, request);
        UserDetail userDetail = new UserDetail(new UserDomain(user));
        request.getSession().setAttribute(SESSION_KEY, userDetail);
        // 签发TOKEN
        String token = jwtUtils.createJWT(user.getUserName(), request);
        SignInSuccessVO signInSuccessVO = new SignInSuccessVO();
        signInSuccessVO.setAccessToken(token);
        signInSuccessVO.setUcScript(user.getUcScript());
        return new APIResult<>(signInSuccessVO);
    }

    /**
     * 注册接口
     *
     * @param signUpVO
     * @return
     */
    @Override
    @OperationLog(module = SystemTypeEnum.API, desc = "访问注册接口", operation = OperationTypeEnum.CREATE)
    public APIResult doSignUp(SignUpVO signUpVO) {
        if (getSignUser() != null) {
            return APIResult.builder().code(StateCodeEnum.Failure).message("您已经登录，无需重复注册。").build();
        }
        if (signUpVO.getUserName().trim().toLowerCase().length() >= MAX_USERNAME_LENGTH) {
            return APIResult.builder()
                    .code(StateCodeEnum.Failure)
                    .message("用户名长度超过系统允许的最大值：" + MAX_USERNAME_LENGTH)
                    .build();
        }
        if (signUpVO.getEmail().length() >= MAX_USERNAME_LENGTH) {
            return APIResult.builder()
                    .code(StateCodeEnum.Failure)
                    .message("邮箱地址长度超过系统允许最大值：" + MAX_USERNAME_LENGTH)
                    .build();
        }
        signUpVO.setUserName(signUpVO.getUserName().trim().toLowerCase());
        signUpVO.setPassword(sysService.decrypt(signUpVO.getPassword(), signUpVO.getKeyUuid()));
        try {
            accountService.signUp(signUpVO, request);
        } catch (PasswordUtils.CannotPerformOperationException e) {
            logger.error(e.getMessage(), e);
            return APIResult.builder()
                    .code(StateCodeEnum.Error)
                    .message(StateCodeEnum.Error.getDescribe())
                    .build();
        }
        return APIResult.success();
    }

    /**
     * 账户激活接口
     *
     * @param signUpActivationVO
     * @return
     */
    @Override
    @OperationLog(module = SystemTypeEnum.API, desc = "访问账户激活接口", operation = OperationTypeEnum.UPDATE)
    public APIResult doSignUpActivation(SignUpActivationVO signUpActivationVO) {
        try {
            accountService.activation(signUpActivationVO);
        } catch (BusinessException e) {
            return APIResult.builder()
                    .code(StateCodeEnum.Failure)
                    .message(e.getMessage())
                    .build();
        }
        return APIResult.success();
    }
}
