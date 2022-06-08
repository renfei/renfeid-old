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
import net.renfei.common.api.constant.enums.SecretLevelEnum;
import net.renfei.common.api.exception.BusinessException;
import net.renfei.common.api.exception.NeedU2FException;
import net.renfei.common.api.utils.ListUtils;
import net.renfei.common.api.utils.StringUtils;
import net.renfei.common.core.config.SystemConfig;
import net.renfei.common.core.entity.LogLevelEnum;
import net.renfei.common.core.entity.OperationTypeEnum;
import net.renfei.common.core.entity.SystemTypeEnum;
import net.renfei.common.core.service.RedisService;
import net.renfei.common.core.service.SystemLogService;
import net.renfei.common.core.service.VerificationCodeService;
import net.renfei.common.core.utils.DateUtils;
import net.renfei.uaa.api.JwtService;
import net.renfei.uaa.api.UserService;
import net.renfei.common.core.entity.UserDetail;
import net.renfei.uaa.api.entity.SignInAo;
import net.renfei.uaa.repositories.UaaUserMapper;
import net.renfei.uaa.repositories.entity.UaaUser;
import net.renfei.uaa.repositories.entity.UaaUserExample;
import net.renfei.uaa.utils.GoogleAuthenticator;
import net.renfei.uaa.utils.PasswordUtils;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Service;
import org.springframework.util.ObjectUtils;

import javax.servlet.http.HttpServletRequest;

import java.util.Date;

import static net.renfei.uaa.service.AuthorizationServiceImpl.REDIS_TOKEN_KEY;

/**
 * 用户服务
 *
 * @author renfei
 */
@Service
public class UserServiceImpl implements UserService {
    private final static Logger logger = LoggerFactory.getLogger(UserServiceImpl.class);
    private final JwtService jwtService;
    private final RedisService redisService;
    private final SystemConfig systemConfig;
    private final UaaUserMapper uaaUserMapper;
    private final SystemLogService systemLogService;
    private final VerificationCodeService verificationCodeService;

    public UserServiceImpl(JwtService jwtService,
                           RedisService redisService,
                           SystemConfig systemConfig,
                           UaaUserMapper uaaUserMapper,
                           SystemLogService systemLogService,
                           VerificationCodeService verificationCodeService) {
        this.jwtService = jwtService;
        this.redisService = redisService;
        this.systemConfig = systemConfig;
        this.uaaUserMapper = uaaUserMapper;
        this.systemLogService = systemLogService;
        this.verificationCodeService = verificationCodeService;
    }

    @Override
    public APIResult<UserDetail> getUserDetailByToken(String token) {
        return this.getUserDetailByToken(token, null);
    }

    @Override
    public APIResult<UserDetail> getUserDetailByToken(String token, String ip) {
        if (ip == null || ip.isEmpty()) {
            if (jwtService.validate(token).getCode() != 200) {
                // JWT 校验失败
                logger.warn("Token校验失败：{}", token);
                throw new RuntimeException("Token校验失败。");
            }
        } else if (jwtService.validate(token, ip).getCode() != 200) {
            // JWT 校验失败
            logger.warn("Token校验失败：{}", token);
            throw new RuntimeException("Token校验失败。");
        }
        String username = jwtService.getUsername(token).getData();
        // 查验 redis
        Object object = redisService.get(REDIS_TOKEN_KEY + username);
        if (object == null || !token.equals(object.toString())) {
            logger.warn("Token on redis校验失败：{}", token);
            throw new RuntimeException("Token校验失败。");
        }
        // 根据 username 获取 UserDetail
        UaaUserExample example = new UaaUserExample();
        example.createCriteria()
                .andUsernameEqualTo(username);
        UaaUser uaaUser = ListUtils.getOne(uaaUserMapper.selectByExample(example));
        if (uaaUser == null) {
            logger.error("根据用户名：{}，未找到用户信息", username);
            throw new RuntimeException("未找到用户信息。");
        }
        return new APIResult<>(convert(uaaUser));
    }

    @Override
    public APIResult<UserDetail> signIn(SignInAo signIn, HttpServletRequest request) {
        UaaUserExample example = new UaaUserExample();
        UaaUserExample.Criteria criteria = example.createCriteria();
        if (StringUtils.isChinaPhone(signIn.getUserName())) {
            criteria.andPhoneEqualTo(signIn.getUserName());
        } else if (StringUtils.isEmail(signIn.getUserName())) {
            criteria.andEmailEqualTo(signIn.getUserName());
        } else {
            criteria.andUsernameEqualTo(signIn.getUserName());
        }
        UaaUser uaaUser = ListUtils.getOne(uaaUserMapper.selectByExample(example));
        if (uaaUser == null) {
            throw new BusinessException("用户未注册，请先注册");
        }
        if (uaaUser.getEmail() != null && !uaaUser.getEmail().isEmpty() && !uaaUser.getEmailVerified()) {
            // 邮箱未激活，发送激活邮件
            verificationCodeService.sendVerificationCode(true, DateUtils.nextHours(2),
                    uaaUser.getEmail(), "SIGN_UP", convert(uaaUser),
                    systemConfig.getSiteDomainName() + "/auth/signUp/activation");
            throw new BusinessException("当前账户邮箱未激活，我们已经为您发送了一封激活邮件");
        } else if (StringUtils.isChinaPhone(signIn.getUserName()) && !uaaUser.getPhoneVerified()) {
            throw new BusinessException("当前账户手机号码未通过验证，不能使用手机号码登录");
        }
        if (!uaaUser.getEnabled()) {
            systemLogService.save(LogLevelEnum.WARN, SystemTypeEnum.AUTH, OperationTypeEnum.SIGNIN,
                    String.format("账号：%s，尝试登录系统，因账号未启用被拒绝登录。",
                            signIn.getUserName()), uaaUser.getUuid(), uaaUser.getUsername(), request);
            logger.warn("账号：{}，尝试登录系统，因账号未启用被拒绝登录。", signIn.getUserName());
            throw new BusinessException("当前账户未启用，请联系安全保密管理员对账号进行启用");
        }
        if (uaaUser.getLocked()) {
            systemLogService.save(LogLevelEnum.WARN, SystemTypeEnum.AUTH, OperationTypeEnum.SIGNIN,
                    String.format("账号：%s，尝试登录系统，因账号锁定拒绝登录。",
                            signIn.getUserName()), uaaUser.getUuid(), uaaUser.getUsername(), request);
            logger.warn("账号：{}，尝试登录系统，因账号锁定拒绝登录。", signIn.getUserName());
            throw new BusinessException("当前账户被锁定，请联系安全保密管理员对账号进行解锁");
        }
        if (uaaUser.getLockTime() != null) {
            // 判断锁定时间
            if (new Date().before(uaaUser.getLockTime())) {
                String lockDate = DateUtils.getDate(uaaUser.getLockTime(), "yyyy-MM-dd hh:mm:ss");
                systemLogService.save(LogLevelEnum.WARN, SystemTypeEnum.AUTH, OperationTypeEnum.SIGNIN,
                        String.format("账号：%s，尝试登录系统，因账号处于锁定期被拒绝登录，锁定期[%s]后将自动解锁。",
                                signIn.getUserName(), lockDate), uaaUser.getUuid(), uaaUser.getUsername(), request);
                logger.warn("账号：{}，尝试登录系统，因账号处于锁定期被拒绝登录，锁定期[{}]后将自动解锁。", signIn.getUserName(), lockDate);
                throw new BusinessException("当前账户已被锁定至[" + lockDate + "]，请稍后再试");
            }
        }
        if (uaaUser.getPasswordExpirationTime() != null) {
            // 判断密码有效期
            if (new Date().before(uaaUser.getPasswordExpirationTime())) {
                systemLogService.save(LogLevelEnum.WARN, SystemTypeEnum.AUTH, OperationTypeEnum.SIGNIN,
                        String.format("账号：%s，尝试登录系统，因账号长时间未修改密码被拒绝登录。",
                                signIn.getUserName()), uaaUser.getUuid(), uaaUser.getUsername(), request);
                logger.warn("账号：{}，尝试登录系统，因账号长时间未修改密码被拒绝登录。", signIn.getUserName());
                throw new BusinessException("当前账户由于长时间未修改密码，被限制使用，请联系安全保密管理员重置密码后再登录。");
            }
        }
        if (!ObjectUtils.isEmpty(uaaUser.getTotp()) && ObjectUtils.isEmpty(uaaUser.getTotp())) {
            throw new NeedU2FException("当前账户开启了两步认证(U2F)，需要提供两步认证码");
        }
        if (signIn.getUseVerCode()) {
            // 验证码登录
            if (!verificationCodeService.verificationCode(signIn.getPassword(), signIn.getUserName(), "SIGN_IN")) {
                throw new BusinessException("验证码错误或已过期");
            }
        } else {
            // 密码登录
            if (!PasswordUtils.verifyPassword(signIn.getPassword(), uaaUser.getPassword())) {
                // 记录错误，如果错误超过6次，锁定时间为 (N-6)*1分钟
                uaaUser.setTrialErrorTimes(uaaUser.getTrialErrorTimes() + 1);
                if (uaaUser.getTrialErrorTimes() > 6) {
                    // 锁定时间
                    uaaUser.setLockTime(DateUtils.nextMinutes(uaaUser.getTrialErrorTimes() - 6));
                }
                uaaUserMapper.updateByPrimaryKeySelective(uaaUser);
                systemLogService.save(LogLevelEnum.WARN, SystemTypeEnum.AUTH, OperationTypeEnum.SIGNIN,
                        String.format("账号：%s，尝试登录系统，因密码错误被拒绝登录。",
                                signIn.getUserName()), uaaUser.getUuid(), uaaUser.getUsername(), request);
                logger.warn("账号：{}，尝试登录系统，因密码错误被拒绝登录。", signIn.getUserName());
                throw new BusinessException("用户名或密码错误");
            }
        }
        // 两步认证
        if (!ObjectUtils.isEmpty(uaaUser.getTotp())) {
            if (!GoogleAuthenticator.authcode(signIn.gettOtp(), uaaUser.getTotp())) {
                throw new BusinessException("两步认证(U2F)失败，请重试");
            }
        }
        systemLogService.save(LogLevelEnum.INFO, SystemTypeEnum.AUTH, OperationTypeEnum.SIGNIN,
                String.format("账号：%s，登入系统。", signIn.getUserName()),
                uaaUser.getUuid(), uaaUser.getUsername(), request);
        logger.info("账号：{}，登入系统。", signIn.getUserName());
        return new APIResult<>(convert(uaaUser));
    }

    private UserDetail convert(UaaUser uaaUser) {
        if (uaaUser == null) {
            return null;
        }
        UserDetail userDetail = new UserDetail();
        userDetail.setId(uaaUser.getId());
        userDetail.setUuid(uaaUser.getUuid());
        userDetail.setUsername(uaaUser.getUsername());
        userDetail.setEmail(uaaUser.getEmail());
        userDetail.setEmailVerified(uaaUser.getEmailVerified());
        userDetail.setPhone(uaaUser.getPhone());
        userDetail.setPhoneVerified(uaaUser.getPhoneVerified());
        userDetail.setRegistrationDate(uaaUser.getRegistrationDate());
        userDetail.setPassword(uaaUser.getPassword());
        userDetail.setTotp(uaaUser.getTotp());
        userDetail.setRegistrationIp(uaaUser.getRegistrationIp());
        userDetail.setTrialErrorTimes(uaaUser.getTrialErrorTimes());
        userDetail.setLockTime(uaaUser.getLockTime());
        userDetail.setSecretLevel(SecretLevelEnum.valueOf(uaaUser.getSecretLevel()));
        userDetail.setBuiltInUser(uaaUser.getBuiltInUser());
        userDetail.setPasswordExpirationTime(uaaUser.getPasswordExpirationTime());
        userDetail.setLocked(uaaUser.getLocked());
        userDetail.setEnabled(uaaUser.getEnabled());
        userDetail.setLastName(uaaUser.getLastName());
        userDetail.setFirstName(uaaUser.getFirstName());
        return userDetail;
    }
}
