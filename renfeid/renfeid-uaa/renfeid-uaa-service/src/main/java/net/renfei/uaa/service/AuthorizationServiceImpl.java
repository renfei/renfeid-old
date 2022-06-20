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
package net.renfei.uaa.service;

import net.renfei.common.api.constant.APIResult;
import net.renfei.common.api.constant.enums.StateCodeEnum;
import net.renfei.common.api.entity.UserInfo;
import net.renfei.common.api.exception.BusinessException;
import net.renfei.common.api.utils.ListUtils;
import net.renfei.common.api.utils.RSAUtils;
import net.renfei.common.api.utils.StringUtils;
import net.renfei.common.core.config.RedisConfig;
import net.renfei.common.core.config.SystemConfig;
import net.renfei.uaa.api.entity.UserDetail;
import net.renfei.common.core.service.RedisService;
import net.renfei.common.core.service.SystemService;
import net.renfei.common.core.service.VerificationCodeService;
import net.renfei.common.core.utils.AESUtils;
import net.renfei.common.core.utils.IpUtils;
import net.renfei.proprietary.discuz.service.DiscuzService;
import net.renfei.uaa.api.AuthorizationService;
import net.renfei.uaa.api.JwtService;
import net.renfei.uaa.api.UserService;
import net.renfei.uaa.api.entity.*;
import net.renfei.uaa.repositories.UaaSecretKeyMapper;
import net.renfei.uaa.repositories.entity.UaaSecretKeyExample;
import net.renfei.uaa.repositories.entity.UaaSecretKeyWithBLOBs;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.BeanUtils;
import org.springframework.stereotype.Service;
import org.springframework.util.ObjectUtils;

import javax.servlet.http.HttpServletRequest;
import java.util.Date;
import java.util.Map;
import java.util.UUID;
import java.util.regex.Pattern;

import static net.renfei.common.core.config.SystemConfig.MAX_USERNAME_LENGTH;

/**
 * 认证服务
 *
 * @author renfei
 */
@Service
public class AuthorizationServiceImpl implements AuthorizationService {
    public final static String REDIS_TOKEN_KEY = RedisConfig.REDIS_KEY_DATABASE + ":token:";
    private final static Logger logger = LoggerFactory.getLogger(AuthorizationServiceImpl.class);
    private final static Pattern SPECIAL_PATTERN = Pattern.compile("[ _`~!@#$%^&*()+=|{}':;',\\[\\].<>/?~！@#￥%……&*（）——+|{}【】‘；：”“’。，、？]|\n|\r|\t");
    private final JwtService jwtService;
    private final UserService userService;
    private final SystemConfig systemConfig;
    private final RedisService redisService;
    private final SystemService systemService;
    private final DiscuzService discuzService;
    private final UaaSecretKeyMapper uaaSecretKeyMapper;
    private final VerificationCodeService verificationCodeService;

    public AuthorizationServiceImpl(JwtService jwtService,
                                    UserService userService,
                                    SystemConfig systemConfig,
                                    RedisService redisService,
                                    SystemService systemService,
                                    DiscuzService discuzService,
                                    UaaSecretKeyMapper uaaSecretKeyMapper,
                                    VerificationCodeService verificationCodeService) {
        this.jwtService = jwtService;
        this.userService = userService;
        this.systemConfig = systemConfig;
        this.redisService = redisService;
        this.systemService = systemService;
        this.discuzService = discuzService;
        this.uaaSecretKeyMapper = uaaSecretKeyMapper;
        this.verificationCodeService = verificationCodeService;
    }

    /**
     * 向服务器申请服务器公钥
     *
     * @return
     */
    @Override
    public APIResult<SecretKey> requestServerSecretKey() {
        Map<Integer, String> map = RSAUtils.genKeyPair(2048);
        if (ObjectUtils.isEmpty(map)) {
            logger.error("生成服务器公钥失败。");
            throw new RuntimeException("生成服务器公钥失败。");
        }
        String uuid = UUID.randomUUID().toString();
        SecretKey secretKey = new SecretKey();
        secretKey.setUuid(uuid);
        secretKey.setPublicKey(map.get(0));
        // 私钥不外传
        secretKey.setPrivateKey(null);
        // 保存数据库
        UaaSecretKeyWithBLOBs uaaSecretKey = new UaaSecretKeyWithBLOBs();
        uaaSecretKey.setPublicKey(map.get(0));
        uaaSecretKey.setPrivateKey(map.get(1));
        uaaSecretKey.setUuid(uuid);
        uaaSecretKey.setCreateTime(new Date());
        uaaSecretKeyMapper.insertSelective(uaaSecretKey);
        return new APIResult<>(secretKey);
    }

    /**
     * 上报客户端公钥，并下发AES秘钥
     *
     * @param secretKey 客户端加密后的秘钥
     * @return
     */
    @Override
    public APIResult<SecretKey> settingClientSecretKey(SecretKey secretKey) {
        if (secretKey == null || secretKey.getUuid() == null || secretKey.getUuid().isEmpty()) {
            throw new BusinessException("服务器端秘钥UUID错误，请查证后重试。");
        }
        UaaSecretKeyExample example = new UaaSecretKeyExample();
        example.createCriteria().andUuidEqualTo(secretKey.getUuid());
        UaaSecretKeyWithBLOBs uaaSecretKey = ListUtils.getOne(uaaSecretKeyMapper.selectByExampleWithBLOBs(example));
        if (uaaSecretKey == null) {
            throw new BusinessException("服务器端秘钥UUID错误，请查证后重试。");
        }
        String clientKey;
        try {
            clientKey = RSAUtils.decrypt(secretKey.getPublicKey(), secretKey.getPrivateKey());
        } catch (Exception ex) {
            logger.error(ex.getMessage());
            throw new BusinessException("客户端公钥解密失败。");
        }
        String aes = StringUtils.getRandomString(16);
        String aesEnc;
        try {
            aesEnc = RSAUtils.encrypt(aes, clientKey.replaceAll("\n", ""));
        } catch (Exception ex) {
            logger.error(ex.getMessage());
            throw new RuntimeException("服务器内部错误，使用RSA客户端公钥加密失败。");
        }
        //保存AES
        String uuid = UUID.randomUUID().toString();
        UaaSecretKeyWithBLOBs clientPrivateKey = new UaaSecretKeyWithBLOBs();
        clientPrivateKey.setUuid(uuid);
        clientPrivateKey.setCreateTime(new Date());
        clientPrivateKey.setPrivateKey(aes);
        uaaSecretKeyMapper.insertSelective(clientPrivateKey);
        SecretKey responseSecretKey = new SecretKey();
        responseSecretKey.setUuid(uuid);
        responseSecretKey.setPublicKey(aesEnc);
        responseSecretKey.setPrivateKey(aesEnc);
        return new APIResult<>(responseSecretKey);
    }

    /**
     * 根据秘钥ID解密AES密文
     *
     * @param string 密文
     * @param keyId  秘钥ID
     * @return 明文
     */
    @Override
    public APIResult<String> decryptAesByKeyId(String string, String keyId) {
        UaaSecretKeyExample example = new UaaSecretKeyExample();
        example.createCriteria().andUuidEqualTo(keyId);
        UaaSecretKeyWithBLOBs uaaSecretKey = ListUtils.getOne(uaaSecretKeyMapper.selectByExampleWithBLOBs(example));
        if (uaaSecretKey == null) {
            throw new BusinessException("AESKeyId不存在");
        }
        try {
            string = AESUtils.decrypt(string, uaaSecretKey.getPrivateKey());
        } catch (Exception ex) {
            logger.error(ex.getMessage(), ex);
            throw new BusinessException("加密密文解密失败");
        }
        return new APIResult<>(string);
    }

    /**
     * 登录
     *
     * @param signIn 登录请求对象
     * @return 登录响应
     */
    @Override
    public APIResult<SignInVo> signIn(SignInAo signIn, HttpServletRequest request) {
        signIn.setUserName(signIn.getUserName().trim().toLowerCase());
        signIn.setPassword(this.decryptAesByKeyId(
                signIn.getPassword(), signIn.getKeyUuid()
        ).getData());
        UserDetail userDetail = userService.signIn(signIn, request).getData();
        String token = jwtService.createJWT(userDetail.getUsername(), IpUtils.getIpAddress(request)).getData();
        // 将此用户的其他token从redis中删除
        redisService.del(REDIS_TOKEN_KEY + userDetail.getUsername());
        redisService.set(REDIS_TOKEN_KEY + userDetail.getUsername(), token);
        redisService.expire(REDIS_TOKEN_KEY + userDetail.getUsername(), systemConfig.getJwt().getExpiration() / 1000);
        SignInVo signInVo = new SignInVo();
        signInVo.setAccessToken(token);
        if (systemConfig.getUCenter().getEnable()) {
            signInVo.setUcScript(discuzService.uCenterSynLogin(userDetail.getUsername()));
        }
        return new APIResult<>(signInVo);
    }

    @Override
    public APIResult signUp(SignUpAo signUp, HttpServletRequest request) {
        if (systemConfig.getEnableSignUp()) {
            if (signUp.getUserName().trim().toLowerCase().length() >= MAX_USERNAME_LENGTH) {
                return APIResult.builder().code(StateCodeEnum.Failure).message("用户名长度超过系统允许的最大值：" + MAX_USERNAME_LENGTH).build();
            }
            if (signUp.getEmail().length() >= MAX_USERNAME_LENGTH) {
                return APIResult.builder().code(StateCodeEnum.Failure).message("邮箱地址长度超过系统允许最大值：" + MAX_USERNAME_LENGTH).build();
            }
            if (ObjectUtils.isEmpty(signUp.getUserName().trim())) {
                return APIResult.builder().code(StateCodeEnum.Failure).message("用户名不能为空").build();
            }
            if (signUp.getUserName().trim().getBytes().length < 4) {
                return APIResult.builder().code(StateCodeEnum.Failure).message("用户名长度过短，请重起一个名字吧").build();
            }
            if (ObjectUtils.isEmpty(signUp.getEmail().trim())) {
                return APIResult.builder().code(StateCodeEnum.Failure).message("电子邮箱不能为空").build();
            }
            if (StringUtils.isEmail(signUp.getUserName().trim())) {
                return APIResult.builder().code(StateCodeEnum.Failure).message("您不能使用电子邮件地址作为您的用户名").build();
            }
            if (StringUtils.isChinaPhone(signUp.getUserName().trim())) {
                return APIResult.builder().code(StateCodeEnum.Failure).message("您不能使用手机号码作为您的用户名，注册成功后您可以绑定您的手机号码").build();
            }
            if (StringUtils.isDomain(signUp.getUserName().trim())) {
                return APIResult.builder().code(StateCodeEnum.Failure).message("用户名格式不正确，请换个用户名试试").build();
            }
            if (SPECIAL_PATTERN.matcher(signUp.getUserName().trim()).find()) {
                return APIResult.builder().code(StateCodeEnum.Failure).message("用户名包含非法字符，请重起一个名字吧").build();
            }
            if (ObjectUtils.isEmpty(signUp.getPassword())) {
                return APIResult.builder().code(StateCodeEnum.Failure).message("密码不能为空").build();
            }
            if (!StringUtils.isEmail(signUp.getEmail().trim())) {
                return APIResult.builder().code(StateCodeEnum.Failure).message("您填写的电子邮箱地址格式不正确").build();
            }
            signUp.setPassword(this.decryptAesByKeyId(
                    signUp.getPassword(), signUp.getKeyUuid()
            ).getData());
            APIResult apiResult = userService.signUp(signUp, request);
            if (apiResult.getCode() == 200 && systemConfig.getUCenter().getEnable()) {
                // 同步注册Discuz论坛
                discuzService.uCenterSynSignUp(signUp.getUserName(), signUp.getEmail(), request);
            }
            return apiResult;
        } else {
            return APIResult.builder().code(StateCodeEnum.Failure).message("系统当前禁止新用户注册，请联系系统管理员启用新用户注册功能").build();
        }
    }

    @Override
    public APIResult signOut(UserDetail userDetail, HttpServletRequest request) {
        if (userDetail != null) {
            redisService.del(REDIS_TOKEN_KEY + userDetail.getUsername());
        }
        return APIResult.success();
    }

    @Override
    public void activation(SignUpActivationAo signUpActivation) {
        if (!StringUtils.isEmail(signUpActivation.getEmailOrPhone())
                && !StringUtils.isChinaPhone(signUpActivation.getEmailOrPhone())) {
            // 验证的地址既不是手机也不是邮箱，直接拒绝
            throw new BusinessException("请填写正确的邮箱或手机号");
        }
        if (!verificationCodeService.verificationCode(signUpActivation.getCode(), signUpActivation.getEmailOrPhone(), "SIGN_UP")) {
            // 验证码找不到，拒绝
            throw new BusinessException("验证码错误或已过期");
        }
        userService.activation(signUpActivation.getEmailOrPhone());
    }

    @Override
    public UserInfo requestCurrentUserInfo() {
        UserDetail userDetail = systemService.currentUserDetail();
        if (userDetail != null) {
            UserInfo userInfo = new UserInfo();
            BeanUtils.copyProperties(userDetail, userInfo);
            return userInfo;
        }
        return null;
    }
}
