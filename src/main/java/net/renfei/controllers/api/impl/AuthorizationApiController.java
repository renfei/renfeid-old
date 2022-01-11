package net.renfei.controllers.api.impl;

import net.renfei.controllers.BaseController;
import net.renfei.controllers.api.AuthorizationApi;
import net.renfei.domain.user.User;
import net.renfei.exception.NeedU2FException;
import net.renfei.model.APIResult;
import net.renfei.model.ReportPublicKeyVO;
import net.renfei.model.StateCodeEnum;
import net.renfei.model.auth.ReCaptchaVerify;
import net.renfei.model.auth.ReCaptchaVerifyResponse;
import net.renfei.model.auth.SignInVO;
import net.renfei.model.auth.SignUpVO;
import net.renfei.services.AccountService;
import net.renfei.services.ReCaptchaService;
import net.renfei.services.SysService;
import net.renfei.utils.IpUtils;
import net.renfei.utils.JacksonUtil;
import net.renfei.utils.JwtTokenUtils;
import net.renfei.utils.PasswordUtils;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.util.ObjectUtils;
import org.springframework.web.bind.annotation.RestController;

import java.util.Map;

import static net.renfei.config.SystemConfig.RE_CAPTCHA_MIN_SOURCE;
import static net.renfei.config.SystemConfig.SESSION_AUTH_MODE;

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
    private final ReCaptchaService reCaptchaService;

    public AuthorizationApiController(JwtTokenUtils jwtUtils,
                                      SysService sysService,
                                      AccountService accountService,
                                      ReCaptchaService reCaptchaService) {
        this.jwtUtils = jwtUtils;
        this.sysService = sysService;
        this.accountService = accountService;
        this.reCaptchaService = reCaptchaService;
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

    /**
     * 登陆
     *
     * @param signInVO
     * @return
     */
    @Override
    public APIResult<String> doSignIn(SignInVO signInVO) throws NeedU2FException {
        if (getSignUser() != null) {
            return new APIResult<>("已登陆无需再次登陆。");
        }
        if (ObjectUtils.isEmpty(signInVO.getReCAPTCHAToken())) {
            return APIResult.builder()
                    .code(StateCodeEnum.Failure)
                    .message("我们的服务器好像对你很感兴趣，想知道你是人类还是同类？刷新一下再试试")
                    .build();
        }
        ReCaptchaVerify reCaptchaVerify = new ReCaptchaVerify();
        assert SYSTEM_CONFIG != null;
        reCaptchaVerify.setSecret(SYSTEM_CONFIG.getGoogle().getReCAPTCHA().getServerKey());
        reCaptchaVerify.setResponse(signInVO.getReCAPTCHAToken());
        reCaptchaVerify.setRemoteip(IpUtils.getIpAddress(request));
        ReCaptchaVerifyResponse verifyResponse = reCaptchaService.siteVerify(reCaptchaVerify);
        if (verifyResponse.getSuccess()) {
            // 低于验证阈值，拦截
            if (verifyResponse.getScore() < RE_CAPTCHA_MIN_SOURCE) {
                return APIResult.builder()
                        .code(StateCodeEnum.Failure)
                        .message("我们的服务器好像对你很感兴趣，想知道你是人类还是同类？刷新一下再试试")
                        .build();
            }
        } else {
            // 调用失败，放弃校验，不能影响用户登陆操作
            logger.error("reCaptchaService 调用失败，返回内容：{}", JacksonUtil.obj2String(verifyResponse));
        }
        signInVO.setUserName(signInVO.getUserName().trim().toLowerCase());
        signInVO.setPassword(sysService.decrypt(signInVO.getPassword(), signInVO.getKeyUuid()));
        // 用户登陆服务
        User user = accountService.signIn(signInVO, request);
        if (SESSION_AUTH_MODE.equals(SYSTEM_CONFIG.getAuthMode())) {
            request.getSession().setAttribute(SESSION_KEY, user);
            return new APIResult<>(user.getUcScript());
        } else {
            // 签发TOKEN
            String token = jwtUtils.createJWT(user.getUserName(), request);
            return new APIResult<>(token);
        }
    }

    /**
     * 注册接口
     *
     * @param signUpVO
     * @return
     */
    @Override
    public APIResult doSignUp(SignUpVO signUpVO) {
        if (getSignUser() != null) {
            return APIResult.builder().code(StateCodeEnum.Failure).message("您已经登录，无需重复注册。").build();
        }
        ReCaptchaVerify reCaptchaVerify = new ReCaptchaVerify();
        assert SYSTEM_CONFIG != null;
        reCaptchaVerify.setSecret(SYSTEM_CONFIG.getGoogle().getReCAPTCHA().getServerKey());
        reCaptchaVerify.setResponse(signUpVO.getReCAPTCHAToken());
        reCaptchaVerify.setRemoteip(IpUtils.getIpAddress(request));
        ReCaptchaVerifyResponse verifyResponse = reCaptchaService.siteVerify(reCaptchaVerify);
        if (verifyResponse.getSuccess()) {
            // 低于验证阈值，拦截
            if (verifyResponse.getScore() < RE_CAPTCHA_MIN_SOURCE) {
                return APIResult.builder()
                        .code(StateCodeEnum.Failure)
                        .message("我们的服务器好像对你很感兴趣，想知道你是人类还是同类？刷新一下再试试")
                        .build();
            }
        } else {
            // 调用失败，放弃校验，不能影响用户注册操作
            logger.error("reCaptchaService 调用失败，返回内容：{}", JacksonUtil.obj2String(verifyResponse));
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
}
