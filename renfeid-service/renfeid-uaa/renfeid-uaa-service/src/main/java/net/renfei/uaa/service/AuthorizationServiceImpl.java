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
import net.renfei.common.api.entity.ListData;
import net.renfei.common.api.entity.UserInfo;
import net.renfei.common.api.entity.UserSignInLog;
import net.renfei.common.api.exception.BusinessException;
import net.renfei.common.api.utils.ListUtils;
import net.renfei.common.api.utils.RSAUtils;
import net.renfei.common.api.utils.StringUtils;
import net.renfei.common.core.config.RedisConfig;
import net.renfei.common.core.config.SystemConfig;
import net.renfei.common.core.entity.SystemLogEntity;
import net.renfei.common.core.service.*;
import net.renfei.common.core.utils.DateUtils;
import net.renfei.uaa.api.entity.UserDetail;
import net.renfei.common.core.utils.AESUtils;
import net.renfei.common.core.utils.IpUtils;
import net.renfei.pro.discuz.service.DiscuzService;
import net.renfei.uaa.api.AuthorizationService;
import net.renfei.uaa.api.JwtService;
import net.renfei.uaa.api.UserService;
import net.renfei.uaa.api.entity.*;
import net.renfei.uaa.repositories.UaaSecretKeyMapper;
import net.renfei.uaa.repositories.entity.UaaSecretKeyExample;
import net.renfei.uaa.repositories.entity.UaaSecretKeyWithBLOBs;
import net.renfei.uaa.utils.GoogleAuthenticator;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.BeanUtils;
import org.springframework.stereotype.Service;
import org.springframework.util.ObjectUtils;

import javax.servlet.http.HttpServletRequest;
import java.net.URLDecoder;
import java.util.*;
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
    private final EmailService emailService;
    private final SystemService systemService;
    private final DiscuzService discuzService;
    private final SystemLogService systemLogService;
    private final IP2LocationService ip2LocationService;
    private final UaaSecretKeyMapper uaaSecretKeyMapper;
    private final VerificationCodeService verificationCodeService;

    public AuthorizationServiceImpl(JwtService jwtService,
                                    UserService userService,
                                    SystemConfig systemConfig,
                                    RedisService redisService,
                                    EmailService emailService,
                                    SystemService systemService,
                                    DiscuzService discuzService,
                                    SystemLogService systemLogService,
                                    IP2LocationService ip2LocationService,
                                    UaaSecretKeyMapper uaaSecretKeyMapper,
                                    VerificationCodeService verificationCodeService) {
        this.jwtService = jwtService;
        this.userService = userService;
        this.systemConfig = systemConfig;
        this.redisService = redisService;
        this.emailService = emailService;
        this.systemService = systemService;
        this.discuzService = discuzService;
        this.systemLogService = systemLogService;
        this.ip2LocationService = ip2LocationService;
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
        Map<Integer, String> map = RSAUtils.genKeyPair(4096);
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
            clientKey = URLDecoder.decode(RSAUtils.decrypt(secretKey.getPublicKey(), uaaSecretKey.getPrivateKey()), "utf-8");
        } catch (Exception ex) {
            logger.error(ex.getMessage(), ex);
            throw new BusinessException("客户端公钥解密失败。");
        }
        String aes = StringUtils.getRandomString(16);
        String aesEnc;
        try {
            aesEnc = RSAUtils.encrypt(aes, clientKey);
        } catch (Exception ex) {
            logger.error(ex.getMessage(), ex);
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
            userInfo.setU2fEnable(!ObjectUtils.isEmpty(userDetail.getTotp()));
            return userInfo;
        }
        return null;
    }

    @Override
    public ListData<UserSignInLog> queryCurrentUserSignInLog(int pages, int rows) {
        UserDetail userDetail = systemService.currentUserDetail();
        if (userDetail != null) {
            ListData<SystemLogEntity> systemLogEntityListData = systemLogService.queryUserSignInLog(userDetail.getUsername(), pages, rows);
            ListData<UserSignInLog> userSignInLogListData = new ListData<>();
            userSignInLogListData.setPageNum(systemLogEntityListData.getPageNum());
            userSignInLogListData.setPageSize(systemLogEntityListData.getPageSize());
            userSignInLogListData.setStartRow(systemLogEntityListData.getStartRow());
            userSignInLogListData.setEndRow(systemLogEntityListData.getEndRow());
            userSignInLogListData.setTotal(systemLogEntityListData.getTotal());
            userSignInLogListData.setPages(systemLogEntityListData.getPages());
            if (systemLogEntityListData.getData() != null && !systemLogEntityListData.getData().isEmpty()) {
                List<UserSignInLog> userSignInLogs = new ArrayList<>();
                for (SystemLogEntity systemLogEntity : systemLogEntityListData.getData()
                ) {
                    UserSignInLog userSignInLog = new UserSignInLog();
                    BeanUtils.copyProperties(systemLogEntity, userSignInLog);
                    if (!ObjectUtils.isEmpty(systemLogEntity.getRequIp())) {
                        try {
                            userSignInLog.setAddress(ip2LocationService.ipQueryAddress(systemLogEntity.getRequIp()));
                        } catch (Exception e) {
                            logger.warn(e.getMessage(), e);
                        }
                    }
                    userSignInLogs.add(userSignInLog);
                }
                userSignInLogListData.setData(userSignInLogs);
            }
            return userSignInLogListData;
        }
        return null;
    }

    @Override
    public TotpVo generateU2FSecretKey() {
        UserDetail userDetail = systemService.currentUserDetail();
        if (userDetail == null) {
            return null;
        }
        String secretKey = GoogleAuthenticator.generateSecretKey(systemConfig.getTotpSecret());
        TotpVo totpVo = new TotpVo();
        totpVo.setSecretKey(secretKey);
        totpVo.setTotpString(GoogleAuthenticator.genTotpString("RENFEI.NET", userDetail.getUsername(), secretKey));
        return totpVo;
    }

    @Override
    public APIResult openU2f(TotpAo totpAo) {
        UserDetail userDetail = systemService.currentUserDetail();
        if (userDetail == null) {
            return APIResult.builder()
                    .code(StateCodeEnum.Unauthorized)
                    .message(StateCodeEnum.Unauthorized.getDescribe())
                    .build();
        }
        if (ObjectUtils.isEmpty(userDetail.getTotp())) {
            totpAo.setPwd(this.decryptAesByKeyId(totpAo.getPwd(), totpAo.getKeyId()).getData());
            totpAo.setSecretKey(this.decryptAesByKeyId(totpAo.getSecretKey(), totpAo.getKeyId()).getData());
            if (userService.verifyPassword(userDetail, totpAo.getPwd())) {
                if (GoogleAuthenticator.authcode(totpAo.getTotp(), totpAo.getSecretKey())) {
                    userService.addTotp(userDetail, totpAo.getSecretKey());
                    emailService.send(
                            userDetail.getEmail(),
                            userDetail.getUsername(),
                            "操作通知：您开启了U2F两步认证",
                            "您开启了U2F两步认证，您的账户安全性固若金汤。如果不是您本人操作，请立即联系我们，您的账户可能被盗用。"
                    );
                    return APIResult.success();
                } else {
                    return APIResult.builder()
                            .code(StateCodeEnum.Failure)
                            .message("您输入的两步认证码与二维码校验失败，请重试")
                            .build();
                }
            } else {
                return APIResult.builder()
                        .code(StateCodeEnum.Failure)
                        .message("账户密码不正确")
                        .build();
            }
        } else {
            return APIResult.builder()
                    .code(StateCodeEnum.Failure)
                    .message("您的两步认证U2F已经是开启状态，无需再次开启")
                    .build();
        }
    }

    @Override
    public APIResult closeU2f(TotpAo totpAo) {
        UserDetail userDetail = systemService.currentUserDetail();
        if (userDetail == null) {
            return APIResult.builder()
                    .code(StateCodeEnum.Unauthorized)
                    .message(StateCodeEnum.Unauthorized.getDescribe())
                    .build();
        }
        totpAo.setPwd(this.decryptAesByKeyId(totpAo.getPwd(), totpAo.getKeyId()).getData());
        if (userService.verifyPassword(userDetail, totpAo.getPwd())) {
            if (userService.verifyTotp(userDetail, totpAo.getTotp())) {
                userService.removeTotp(userDetail);
                return APIResult.success();
            } else {
                return APIResult.builder()
                        .code(StateCodeEnum.Failure)
                        .message("两步认证码认证失败，请重试")
                        .build();
            }
        } else {
            return APIResult.builder()
                    .code(StateCodeEnum.Failure)
                    .message("账户密码不正确")
                    .build();
        }
    }

    @Override
    public APIResult updatePassword(UpdatePasswordAo updatePassword) {
        UserDetail userDetail = systemService.currentUserDetail();
        if (userDetail == null) {
            return APIResult.builder()
                    .code(StateCodeEnum.Unauthorized)
                    .message(StateCodeEnum.Unauthorized.getDescribe())
                    .build();
        }
        try {
            updatePassword.setOldPwd(this.decryptAesByKeyId(updatePassword.getOldPwd(), updatePassword.getKeyId()).getData());
            updatePassword.setNewPwd(this.decryptAesByKeyId(updatePassword.getNewPwd(), updatePassword.getKeyId()).getData());
            userService.updatePassword(userDetail, updatePassword.getOldPwd(), updatePassword.getNewPwd());
            return APIResult.success();
        } catch (BusinessException businessException) {
            return APIResult.builder().code(StateCodeEnum.Failure).message(businessException.getMessage()).build();
        }
    }

    @Override
    public APIResult sendFindPasswordVerCode(String account) {
        if (ObjectUtils.isEmpty(account)) {
            return APIResult.builder()
                    .code(StateCodeEnum.Failure)
                    .message("请输入您的的邮箱地址或者手机号。")
                    .build();
        }
        UserDetail userDetail = null;
        boolean useEmail = true;
        if (StringUtils.isEmail(account)) {
            // 使用邮箱找回密码
            userDetail = userService.getUserDetailByEmail(account).getData();
        } else if (StringUtils.isChinaPhone(account)) {
            // 使用手机号找回密码
            userDetail = userService.getUserDetailByPhone(account).getData();
            useEmail = false;
        } else {
            return APIResult.builder()
                    .code(StateCodeEnum.Failure)
                    .message("请输入您的的邮箱地址或者手机号。")
                    .build();
        }
        if (userDetail != null && useEmail) {
            verificationCodeService.sendVerificationCode(false, DateUtils.nextHours(2),
                    userDetail.getEmail(), "RESET_PASSWORD", userDetail, null);
        } else if (userDetail != null && !ObjectUtils.isEmpty(userDetail.getPhone())) {
            verificationCodeService.sendVerificationCode(false, DateUtils.nextHours(2),
                    userDetail.getPhone(), "RESET_PASSWORD", userDetail, null);
        }
        return APIResult.success();
    }

    @Override
    public APIResult sendFindUsernameVerCode(String account) {
        if (ObjectUtils.isEmpty(account)) {
            return APIResult.builder()
                    .code(StateCodeEnum.Failure)
                    .message("请输入您的的邮箱地址或者手机号。")
                    .build();
        }
        UserDetail userDetail = null;
        boolean useEmail = true;
        if (StringUtils.isEmail(account)) {
            // 使用邮箱找回密码
            userDetail = userService.getUserDetailByEmail(account).getData();
        } else if (StringUtils.isChinaPhone(account)) {
            // 使用手机号找回密码
            userDetail = userService.getUserDetailByPhone(account).getData();
            useEmail = false;
        } else {
            return APIResult.builder()
                    .code(StateCodeEnum.Failure)
                    .message("请输入您的的邮箱地址或者手机号。")
                    .build();
        }
        if (userDetail != null && useEmail) {
            List<String> contents = new ArrayList<>();
            String userName = userDetail.getUsername();
            contents.add("尊敬的 " + userName + " :");
            contents.add("这封信是由[RENFEI.NET]系统自动发送的。");
            contents.add("您收到这封邮件，是由于在[RENFEI.NET]进行了找回用户名的操作。如果您并没有访问过[RENFEI.NET]或没有进行上述操作，请忽略这封邮件。您不需要退订或进行其他进一步的操作。");
            contents.add("----------------------------------------------------------------------");
            contents.add("用户名找回操作");
            contents.add("----------------------------------------------------------------------");
            contents.add("我们帮您找回了您注册时使用的用户名，期待您的归来！");
            contents.add("您的用户名是：");
            contents.add("<span style=\"color:red;font-size:18px;font-weight:800;\">" + userName + "</span>");
            contents.add("----");
            contents.add("感谢您的访问，祝您使用愉快！");
            emailService.send(
                    userDetail.getEmail(),
                    userDetail.getUsername(),
                    "我们帮您找回了您的用户名",
                    contents
            );
        } else if (userDetail != null && !ObjectUtils.isEmpty(userDetail.getPhone())) {
            verificationCodeService.sendVerificationCode(false, DateUtils.nextHours(2),
                    userDetail.getPhone(), "FIND_USERNAME", userDetail, null);
        }
        return APIResult.success();
    }

    public APIResult resetPasswordByVerCode(ResetPasswordAo resetPasswordAo) {
        // 为了复用对象，此处 TOTP 字段是邮箱地址或者手机号
        if (ObjectUtils.isEmpty(resetPasswordAo.gettOtp())) {
            return APIResult.builder()
                    .code(StateCodeEnum.Failure)
                    .message("请输入您的的邮箱地址或者手机号。")
                    .build();
        }
        UserDetail userDetail;
        if (StringUtils.isEmail(resetPasswordAo.gettOtp())) {
            // 使用邮箱找回密码
            userDetail = userService.getUserDetailByEmail(resetPasswordAo.gettOtp()).getData();
        } else if (StringUtils.isChinaPhone(resetPasswordAo.gettOtp())) {
            // 使用手机号找回密码
            userDetail = userService.getUserDetailByPhone(resetPasswordAo.gettOtp()).getData();
        } else {
            return APIResult.builder()
                    .code(StateCodeEnum.Failure)
                    .message("根据您输入的邮箱地址或者手机号未找到对应的账户。")
                    .build();
        }
        if (!verificationCodeService.verificationCode(resetPasswordAo.getVerCode(),
                resetPasswordAo.gettOtp(), "RESET_PASSWORD")) {
            return APIResult.builder()
                    .code(StateCodeEnum.Failure)
                    .message("验证码错误或已经失效。")
                    .build();
        }
        resetPasswordAo.setPassword(this.decryptAesByKeyId(
                resetPasswordAo.getPassword(), resetPasswordAo.getKeyUuid()
        ).getData());
        userService.resetPassword(Long.parseLong(userDetail.getId()), resetPasswordAo);
        return APIResult.success();
    }

    @Override
    public APIResult<String> findUsernameByVerCode(FindUsernameAo findUsernameAo) {
        if (!verificationCodeService.verificationCode(findUsernameAo.getVerCode(),
                findUsernameAo.getPhone(), "FIND_USERNAME")) {
            return APIResult.builder()
                    .code(StateCodeEnum.Failure)
                    .message("验证码错误或已经失效。")
                    .build();
        }
        UserDetail userDetail;
        if (StringUtils.isChinaPhone(findUsernameAo.getPhone())) {
            // 使用手机号找回密码
            userDetail = userService.getUserDetailByPhone(findUsernameAo.getPhone()).getData();
            if (userDetail != null) {
                return new APIResult<>(userDetail.getUsername());
            } else {
                return APIResult.builder()
                        .code(StateCodeEnum.Failure)
                        .message("您的手机号似乎没有注册。")
                        .build();
            }
        } else {
            return APIResult.builder()
                    .code(StateCodeEnum.Failure)
                    .message("请输入正确的手机号码。")
                    .build();
        }
    }
}
