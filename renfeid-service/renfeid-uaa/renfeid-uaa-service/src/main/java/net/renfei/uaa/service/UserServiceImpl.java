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

import com.github.pagehelper.Page;
import com.github.pagehelper.PageHelper;
import net.renfei.common.api.constant.APIResult;
import net.renfei.common.api.constant.enums.SecretLevelEnum;
import net.renfei.common.api.constant.enums.StateCodeEnum;
import net.renfei.common.api.entity.ListData;
import net.renfei.common.api.exception.BusinessException;
import net.renfei.common.api.exception.NeedU2FException;
import net.renfei.common.api.exception.OutOfSecretLevelException;
import net.renfei.common.api.utils.ListUtils;
import net.renfei.common.api.utils.StringUtils;
import net.renfei.common.core.config.SystemConfig;
import net.renfei.common.core.entity.LogLevelEnum;
import net.renfei.common.core.entity.OperationTypeEnum;
import net.renfei.common.core.entity.SystemTypeEnum;
import net.renfei.common.core.service.*;
import net.renfei.common.core.utils.DateUtils;
import net.renfei.common.core.utils.IpUtils;
import net.renfei.uaa.api.JwtService;
import net.renfei.uaa.api.RoleService;
import net.renfei.uaa.api.UserService;
import net.renfei.uaa.api.entity.*;
import net.renfei.uaa.repositories.UaaUserKeepNameMapper;
import net.renfei.uaa.repositories.UaaUserMapper;
import net.renfei.uaa.repositories.entity.UaaUser;
import net.renfei.uaa.repositories.entity.UaaUserExample;
import net.renfei.uaa.repositories.entity.UaaUserKeepName;
import net.renfei.uaa.repositories.entity.UaaUserKeepNameExample;
import net.renfei.uaa.utils.GoogleAuthenticator;
import net.renfei.uaa.utils.PasswordUtils;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.BeanUtils;
import org.springframework.stereotype.Service;
import org.springframework.util.ObjectUtils;

import javax.servlet.http.HttpServletRequest;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;
import java.util.UUID;

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
    private final RoleService roleService;
    private final RedisService redisService;
    private final SystemConfig systemConfig;
    private final UaaUserMapper uaaUserMapper;
    private final SystemService systemService;
    private final SnowflakeService snowflakeService;
    private final SystemLogService systemLogService;
    private final UaaUserKeepNameMapper uaaUserKeepNameMapper;
    private final VerificationCodeService verificationCodeService;

    public UserServiceImpl(JwtService jwtService,
                           RoleService roleService,
                           RedisService redisService,
                           SystemConfig systemConfig,
                           UaaUserMapper uaaUserMapper,
                           SystemService systemService,
                           SnowflakeService snowflakeService,
                           SystemLogService systemLogService,
                           UaaUserKeepNameMapper uaaUserKeepNameMapper,
                           VerificationCodeService verificationCodeService) {
        this.jwtService = jwtService;
        this.roleService = roleService;
        this.redisService = redisService;
        this.systemConfig = systemConfig;
        this.uaaUserMapper = uaaUserMapper;
        this.systemService = systemService;
        this.snowflakeService = snowflakeService;
        this.systemLogService = systemLogService;
        this.uaaUserKeepNameMapper = uaaUserKeepNameMapper;
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
                return APIResult.builder()
                        .code(StateCodeEnum.Unauthorized)
                        .message("Token校验失败")
                        .build();
            }
        } else if (jwtService.validate(token, ip).getCode() != 200) {
            // JWT 校验失败
            logger.warn("Token校验失败：{}", token);
            return APIResult.builder()
                    .code(StateCodeEnum.Unauthorized)
                    .message("Token校验失败")
                    .build();
        }
        String username = jwtService.getUsername(token).getData();
        // 查验 redis
        Object object = redisService.get(REDIS_TOKEN_KEY + username);
        if (object == null || !token.equals(object.toString())) {
            logger.warn("Token on redis校验失败：{}", token);
            return APIResult.builder()
                    .code(StateCodeEnum.Unauthorized)
                    .message("Token校验失败")
                    .build();
        }
        // 根据 username 获取 UserDetail
        UaaUserExample example = new UaaUserExample();
        example.createCriteria()
                .andUsernameEqualTo(username);
        UaaUser uaaUser = ListUtils.getOne(uaaUserMapper.selectByExample(example));
        if (uaaUser == null) {
            logger.error("根据用户名：{}，未找到用户信息", username);
            return APIResult.builder()
                    .code(StateCodeEnum.Unauthorized)
                    .message("未找到用户信息")
                    .build();
        }
        UserDetail userDetail = convert(uaaUser);
        this.fillRoleDetailList(userDetail);
        return new APIResult<>(userDetail);
    }

    @Override
    public APIResult<UserDetail> getUserDetailByEmail(String email) {
        UaaUserExample example = new UaaUserExample();
        UaaUserExample.Criteria criteria = example.createCriteria();
        criteria.andEmailEqualTo(email);
        return new APIResult<>(convert(ListUtils.getOne(uaaUserMapper.selectByExample(example))));
    }

    @Override
    public APIResult<UserDetail> getUserDetailByPhone(String phone) {
        UaaUserExample example = new UaaUserExample();
        UaaUserExample.Criteria criteria = example.createCriteria();
        criteria.andPhoneEqualTo(phone);
        return new APIResult<>(convert(ListUtils.getOne(uaaUserMapper.selectByExample(example))));
    }

    /**
     * 根据 id 获取用户详情对象
     * 无论是否被禁用或锁定都会被查询出来
     *
     * @param id id
     * @return 用户详情对象
     */
    @Override
    public UserDetail getUserDetailById(long id) {
        return convert(uaaUserMapper.selectByPrimaryKey(id));
    }

    @Override
    public APIResult<UserDetail> getUserDetailByUsername(String username) {
        UaaUserExample example = new UaaUserExample();
        UaaUserExample.Criteria criteria = example.createCriteria();
        criteria.andUsernameEqualTo(username);
        return new APIResult<>(convert(ListUtils.getOne(uaaUserMapper.selectByExample(example))));
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
        if (!ObjectUtils.isEmpty(uaaUser.getTotp()) && ObjectUtils.isEmpty(signIn.gettOtp())) {
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
        uaaUser.setTrialErrorTimes(0);
        uaaUserMapper.updateByPrimaryKeySelective(uaaUser);
        UserDetail userDetail = convert(uaaUser);
        this.fillRoleDetailList(userDetail);
        return new APIResult<>(userDetail);
    }

    @Override
    public APIResult signUp(SignUpAo signUp, HttpServletRequest request) {
        this.checkDuplicateUsername(signUp.getUserName());
        this.checkDuplicateEmail(signUp.getEmail());
        UaaUser uaaUser = new UaaUser();
        uaaUser.setId(snowflakeService.getId("").getId());
        uaaUser.setUuid(UUID.randomUUID().toString().replace("-", "").toUpperCase());
        uaaUser.setUsername(signUp.getUserName().trim().toLowerCase());
        uaaUser.setEmail(signUp.getEmail().trim().toLowerCase());
        try {
            uaaUser.setPassword(PasswordUtils.createHash(signUp.getPassword()));
        } catch (PasswordUtils.CannotPerformOperationException e) {
            logger.error(e.getMessage(), e);
            return APIResult.builder().code(StateCodeEnum.Error).message("服务器内部错误，密码加密时遇到一些问题，请联系系统管理员").build();
        }
        uaaUser.setRegistrationDate(new Date());
        uaaUser.setRegistrationIp(IpUtils.getIpAddress(request));
        // 互联网注册的用户，默认非密等级
        uaaUser.setSecretLevel(SecretLevelEnum.UNCLASSIFIED.getLevel());
        // 互联网注册的用户，默认启用
        uaaUser.setEnabled(true);
        uaaUserMapper.insertSelective(uaaUser);
        // 发送激活邮件
        verificationCodeService.sendVerificationCode(true, DateUtils.nextHours(2),
                uaaUser.getEmail(), "SIGN_UP", convert(uaaUser), systemConfig.getSiteDomainName() + "/auth/signUp/activation");
        return APIResult.success();
    }

    @Override
    public void activation(String emailOrPhone) {
        boolean isEmail = false, isPhone = false;
        if (StringUtils.isEmail(emailOrPhone)) {
            isEmail = true;
        } else if (StringUtils.isChinaPhone(emailOrPhone)) {
            isPhone = true;
        } else {
            throw new BusinessException("验证码发送失败发送地址不正确");
        }
        // 查找对应的账户
        UaaUserExample example = new UaaUserExample();
        UaaUserExample.Criteria criteria = example.createCriteria();
        if (isEmail) {
            criteria.andEmailEqualTo(emailOrPhone);
        }
        if (isPhone) {
            criteria.andPhoneEqualTo(emailOrPhone);
        }
        UaaUser uaaUser = ListUtils.getOne(uaaUserMapper.selectByExample(example));
        if (uaaUser == null) {
            logger.error("账户{}激活失败，未找到用户", emailOrPhone);
            throw new BusinessException("验证码错误或已过期");
        }
        if (isEmail) {
            uaaUser.setEmailVerified(true);
        }
        if (isPhone) {
            uaaUser.setPhoneVerified(true);
        }
        uaaUserMapper.updateByPrimaryKeySelective(uaaUser);
    }

    @Override
    public APIResult<ListData<UserDetail>> queryUserList(String username, String email, String phone, String ip,
                                                         SecretLevelEnum secretLevel, Boolean locked, Boolean enabled,
                                                         int pages, int rows) {
        UserDetail currentUserDetail = systemService.currentUserDetail();
        UaaUserExample example = new UaaUserExample();
        example.setOrderByClause("registration_date DESC");
        UaaUserExample.Criteria criteria = example.createCriteria().andBuiltInUserEqualTo(false);
        if (username != null && !username.isEmpty()) {
            criteria.andUsernameLike("%" + username + "%");
        }
        if (email != null && !email.isEmpty()) {
            criteria.andEmailLike("%" + email + "%");
        }
        if (phone != null && !phone.isEmpty()) {
            criteria.andPhoneLike("%" + phone + "%");
        }
        if (ip != null && !ip.isEmpty()) {
            criteria.andRegistrationIpEqualTo(ip);
        }
        if (secretLevel != null) {
            if (SecretLevelEnum.outOfSecretLevel(currentUserDetail.getSecretLevel(), secretLevel)) {
                throw new OutOfSecretLevelException("根据您账户当前的保密等级，您无权查询比您更高保密等级的数据，请求已被拒绝。");
            }
            criteria.andSecretLevelEqualTo(secretLevel.getLevel());
        } else {
            criteria.andSecretLevelLessThanOrEqualTo(currentUserDetail.getSecretLevel().getLevel());
        }
        if (locked != null) {
            criteria.andLockedEqualTo(locked);
        }
        if (enabled != null) {
            criteria.andEnabledEqualTo(enabled);
        }
        ListData<UserDetail> userDetailListData = new ListData<>();
        try (Page<UaaUser> page = PageHelper.startPage(pages, rows)) {
            uaaUserMapper.selectByExample(example);
            userDetailListData.setPageNum(page.getPageNum());
            userDetailListData.setPageSize(page.getPageSize());
            userDetailListData.setStartRow(page.getStartRow());
            userDetailListData.setEndRow(page.getEndRow());
            userDetailListData.setTotal(page.getTotal());
            userDetailListData.setPages(page.getPages());
            List<UserDetail> userDetails = new ArrayList<>();
            for (UaaUser uaaUser : page.getResult()
            ) {
                UserDetail userDetail = new UserDetail();
                BeanUtils.copyProperties(uaaUser, userDetail);
                userDetail.setId(uaaUser.getId() + "");
                userDetail.setSecretLevel(SecretLevelEnum.valueOf(uaaUser.getSecretLevel()));
                this.fillRoleDetailList(userDetail);
                userDetails.add(userDetail);
            }
            userDetailListData.setData(userDetails);
        }
        return new APIResult<>(userDetailListData);
    }

    @Override
    public APIResult<UserDetail> createUser(UserDetail userDetail, HttpServletRequest request) {
        userDetail.setId(snowflakeService.getId("").getId() + "");
        userDetail.setUuid(UUID.randomUUID().toString().replace("-", "").toUpperCase());
        // 检查用户名
        this.checkDuplicateUsername(userDetail.getUsername());
        userDetail.setUsername(userDetail.getUsername());
        // 检查邮箱
        this.checkDuplicateEmail(userDetail.getEmail());
        userDetail.setEmail(userDetail.getEmail());
        userDetail.setEmailVerified(userDetail.getEmailVerified());
        if (userDetail.getPhone() != null && !userDetail.getPhone().isEmpty()) {
            // 检查手机号
            this.checkDuplicatePhone(userDetail.getPhone());
            userDetail.setPhone(userDetail.getPhone());
        }
        userDetail.setPhoneVerified(userDetail.getPhoneVerified());
        userDetail.setRegistrationDate(new Date());
        try {
            userDetail.setPassword(PasswordUtils.createHash(userDetail.getPassword()));
        } catch (PasswordUtils.CannotPerformOperationException e) {
            logger.error(e.getMessage(), e);
            throw new BusinessException("内部错误，密码加密失败");
        }
        userDetail.setTotp(null);
        userDetail.setRegistrationIp(IpUtils.getIpAddress(request));
        userDetail.setTrialErrorTimes(null);
        userDetail.setLockTime(null);
        userDetail.setSecretLevel(SecretLevelEnum.UNCLASSIFIED);
        userDetail.setBuiltInUser(false);
        userDetail.setPasswordExpirationTime(null);
        userDetail.setLocked(false);
        userDetail.setEnabled(false);
        userDetail.setLastName(userDetail.getLastName());
        userDetail.setFirstName(userDetail.getFirstName());
        uaaUserMapper.insertSelective(convert(userDetail));
        return new APIResult<>(userDetail);
    }

    @Override
    public APIResult<UserDetail> updateUser(long userId, UserDetail userDetail, HttpServletRequest request) {
        UaaUser uaaUser = uaaUserMapper.selectByPrimaryKey(userId);
        UserDetail currentUserDetail = systemService.currentUserDetail();
        if (uaaUser == null) {
            throw new BusinessException("根据用户ID未找到该用户，请查证");
        }
        UserDetail oldUserDetail = convert(uaaUser);
        if (uaaUser.getBuiltInUser()) {
            // 内置用户，禁止编辑，去数据库里改
            systemLogService.save(LogLevelEnum.WARN, SystemTypeEnum.ACCOUNT, OperationTypeEnum.UPDATE,
                    "尝试修改内置用户资料，被拒绝。", currentUserDetail.getUuid(), currentUserDetail.getUsername(), request);
            throw new BusinessException("内置用户，禁止编辑，请求被拒绝");
        }
        if (userId != Long.parseLong(currentUserDetail.getId())
                && SecretLevelEnum.outOfSecretLevel(currentUserDetail.getSecretLevel(), oldUserDetail.getSecretLevel())) {
            // 如果不是修改自己的，那么需要判断密级是否跨级修改
            systemLogService.save(LogLevelEnum.WARN, SystemTypeEnum.ACCOUNT, OperationTypeEnum.UPDATE,
                    "尝试修改高于自己密级的用户，被拒绝。", currentUserDetail.getUuid(), currentUserDetail.getUsername(), request);
            throw new OutOfSecretLevelException("您修改的用户密级高于您的密级，您无权编辑，请求被拒绝");
        }
        // 只能修改以下内容，修改密码、更改密级等有专门的接口
        if (userDetail.getEmail() != null && !userDetail.getEmail().isEmpty()) {
            uaaUser.setEmail(userDetail.getEmail());
            // 因为是后台管理员更新的，认为是可信的，直接是已认证状态
            uaaUser.setEmailVerified(true);
        }

        if (userDetail.getPhone() != null && !userDetail.getPhone().isEmpty()) {
            uaaUser.setPhone(userDetail.getPhone());
            // 因为是后台管理员更新的，认为是可信的，直接是已认证状态
            uaaUser.setPhoneVerified(true);
        }
        uaaUser.setLastName(userDetail.getLastName());
        uaaUser.setFirstName(userDetail.getFirstName());
        uaaUserMapper.updateByPrimaryKeySelective(uaaUser);
        return new APIResult<>(userDetail);
    }

    @Override
    public APIResult determineUserSecretLevel(long userId, SecretLevelEnum secretLevel, HttpServletRequest request) {
        UserDetail currentUserDetail = systemService.currentUserDetail();
        if (SecretLevelEnum.outOfSecretLevel(currentUserDetail.getSecretLevel(), systemConfig.getMaxSecretLevel())) {
            // 判断密级是否跨级修改
            systemLogService.save(LogLevelEnum.WARN, SystemTypeEnum.ACCOUNT, OperationTypeEnum.UPDATE,
                    "尝试定密密级高于系统允许的最大密级，被拒绝。", currentUserDetail.getUuid(), currentUserDetail.getUsername(), request);
            throw new OutOfSecretLevelException("您定密的密级高于系统允许的最大密级，请求被拒绝");
        }
        if (SecretLevelEnum.outOfSecretLevel(currentUserDetail.getSecretLevel(), secretLevel)) {
            // 判断密级是否跨级修改
            systemLogService.save(LogLevelEnum.WARN, SystemTypeEnum.ACCOUNT, OperationTypeEnum.UPDATE,
                    "尝试给用户定密高于自己的密级，被拒绝。", currentUserDetail.getUuid(), currentUserDetail.getUsername(), request);
            throw new OutOfSecretLevelException("您定密的密级高于您自身的密级，请求被拒绝");
        }
        UaaUser uaaUser = uaaUserMapper.selectByPrimaryKey(userId);
        if (uaaUser == null) {
            throw new BusinessException("根据用户ID未找到该用户，请查证");
        }
        UserDetail oldUserDetail = convert(uaaUser);
        if (uaaUser.getBuiltInUser()) {
            // 内置用户，禁止编辑，去数据库里改
            systemLogService.save(LogLevelEnum.WARN, SystemTypeEnum.ACCOUNT, OperationTypeEnum.UPDATE,
                    "尝试给内置用户定密，被拒绝。", currentUserDetail.getUuid(), currentUserDetail.getUsername(), request);
            throw new BusinessException("内置用户，禁止编辑，请求被拒绝");
        }
        if (userId != Long.parseLong(currentUserDetail.getId())
                && SecretLevelEnum.outOfSecretLevel(currentUserDetail.getSecretLevel(), oldUserDetail.getSecretLevel())) {
            // 如果不是修改自己的，那么需要判断密级是否跨级修改
            systemLogService.save(LogLevelEnum.WARN, SystemTypeEnum.ACCOUNT, OperationTypeEnum.UPDATE,
                    "尝试修改高于自己密级的用户，被拒绝。", currentUserDetail.getUuid(), currentUserDetail.getUsername(), request);
            throw new OutOfSecretLevelException("您修改的用户密级高于您的密级，您无权编辑，请求被拒绝");
        }
        uaaUser.setSecretLevel(secretLevel.getLevel());
        uaaUserMapper.updateByPrimaryKeySelective(uaaUser);
        return APIResult.success();
    }

    @Override
    public APIResult enableUser(long userId, boolean enable, HttpServletRequest request) {
        UaaUser uaaUser = uaaUserMapper.selectByPrimaryKey(userId);
        if (uaaUser == null) {
            throw new BusinessException("根据用户ID未找到该用户，请查证");
        }
        uaaUser.setEnabled(enable);
        if (!enable) {
            // 如果是禁用，Token一起删除
            redisService.del(REDIS_TOKEN_KEY + uaaUser.getUsername());
        }
        uaaUserMapper.updateByPrimaryKeySelective(uaaUser);
        return APIResult.success();
    }

    @Override
    public APIResult lockUser(long userId, boolean lock) {
        UaaUser uaaUser = uaaUserMapper.selectByPrimaryKey(userId);
        if (uaaUser == null) {
            throw new BusinessException("根据用户ID未找到该用户，请查证");
        }
        uaaUser.setLocked(lock);
        if (!lock) {
            // 如果是解锁，那么试错次数清空
            uaaUser.setTrialErrorTimes(0);
            uaaUser.setPasswordExpirationTime(null);
        }
        uaaUserMapper.updateByPrimaryKeySelective(uaaUser);
        return APIResult.success();
    }

    public APIResult resetPassword(long userId, ResetPasswordAo resetPassword) {
        UaaUser uaaUser = uaaUserMapper.selectByPrimaryKey(userId);
        if (uaaUser == null) {
            throw new BusinessException("根据用户ID未找到该用户，请查证");
        }
        try {
            uaaUser.setPassword(PasswordUtils.createHash(resetPassword.getPassword()));
        } catch (PasswordUtils.CannotPerformOperationException e) {
            logger.error(e.getMessage(), e);
            throw new BusinessException("内部错误，密码加密失败");
        }
        uaaUserMapper.updateByPrimaryKeySelective(uaaUser);
        return APIResult.success();
    }

    @Override
    public APIResult<List<RoleDetail>> authorizationRoleByUser(long userId, List<RoleDetail> roleDetailList, HttpServletRequest request) {
        return roleService.authorizationRoleByUser(userId, roleDetailList, request);
    }

    @Override
    public List<UserDetail> queryUserListByRoleName(String roleName) {
        APIResult<ListData<RoleDetail>> listDataAPIResult = roleService.queryRoleList(true, roleName, 1, 1);
        if (listDataAPIResult.getData() != null
                && listDataAPIResult.getData().getTotal() > 0
                && !listDataAPIResult.getData().getData().isEmpty()) {
            RoleDetail roleDetail = listDataAPIResult.getData().getData().get(0);
            List<Long> userIds = roleService.queryUserIdListByRole(Long.parseLong(roleDetail.getId()));
            if (!userIds.isEmpty()) {
                UaaUserExample example = new UaaUserExample();
                example.createCriteria().andIdIn(userIds);
                List<UaaUser> uaaUsers = uaaUserMapper.selectByExample(example);
                List<UserDetail> userDetails = new ArrayList<>();
                for (UaaUser uaaUser : uaaUsers) {
                    userDetails.add(convert(uaaUser));
                }
                return userDetails;
            }
        }
        return new ArrayList<>();
    }

    @Override
    public boolean verifyPassword(UserDetail userDetail, String password) {
        UaaUserExample example = new UaaUserExample();
        UaaUserExample.Criteria criteria = example.createCriteria();
        criteria.andUsernameEqualTo(userDetail.getUsername());
        UaaUser uaaUser = ListUtils.getOne(uaaUserMapper.selectByExample(example));
        if (uaaUser == null) {
            return false;
        }
        if (!uaaUser.getEnabled()) {
            logger.warn("账号：{}，校验密码时，因账号未启用被拒绝。", userDetail.getUsername());
            throw new BusinessException("当前账户未启用，请联系安全保密管理员对账号进行启用后再操作");
        }
        if (uaaUser.getLocked()) {
            logger.warn("账号：{}，校验密码时，因账号锁定状态被拒绝。", userDetail.getUsername());
            throw new BusinessException("当前账户被锁定，请联系安全保密管理员对账号进行解锁后再操作");
        }
        if (uaaUser.getLockTime() != null) {
            // 判断锁定时间
            if (new Date().before(uaaUser.getLockTime())) {
                logger.warn("账号：{}，校验密码时，因账号锁定状态被拒绝。", userDetail.getUsername());
                throw new BusinessException("当前账户被锁定，请联系安全保密管理员对账号进行解锁后再操作");
            }
        }
        if (uaaUser.getPasswordExpirationTime() != null) {
            // 判断密码有效期
            if (new Date().before(uaaUser.getPasswordExpirationTime())) {
                logger.warn("账号：{}，校验密码时，因账号过期被拒绝。", userDetail.getUsername());
                throw new BusinessException("当前账户密码已经过期，请联系安全保密管理员对账号进行密码修改后再操作");
            }
        }
        return PasswordUtils.verifyPassword(password, uaaUser.getPassword());
    }

    @Override
    public void updatePassword(UserDetail userDetail, String oldPwd, String newPwd) {
        if (this.verifyPassword(userDetail, oldPwd)) {
            UaaUserExample example = new UaaUserExample();
            UaaUserExample.Criteria criteria = example.createCriteria();
            criteria.andUsernameEqualTo(userDetail.getUsername());
            UaaUser uaaUser = ListUtils.getOne(uaaUserMapper.selectByExample(example));
            try {
                uaaUser.setPassword(PasswordUtils.createHash(newPwd));
            } catch (PasswordUtils.CannotPerformOperationException e) {
                throw new BusinessException("密码加密失败，请重试");
            }
            uaaUserMapper.updateByPrimaryKey(uaaUser);
        } else {
            throw new BusinessException("当前账户密码错误");
        }
    }

    @Override
    public boolean verifyTotp(UserDetail userDetail, String totp) {
        UaaUserExample example = new UaaUserExample();
        UaaUserExample.Criteria criteria = example.createCriteria();
        criteria.andUsernameEqualTo(userDetail.getUsername());
        UaaUser uaaUser = ListUtils.getOne(uaaUserMapper.selectByExample(example));
        if (uaaUser == null) {
            return false;
        }
        if (!uaaUser.getEnabled()) {
            logger.warn("账号：{}，校验TOTP时，因账号未启用被拒绝。", userDetail.getUsername());
            throw new BusinessException("当前账户未启用，请联系安全保密管理员对账号进行启用后再操作");
        }
        if (uaaUser.getLocked()) {
            logger.warn("账号：{}，校验TOTP时，因账号锁定状态被拒绝。", userDetail.getUsername());
            throw new BusinessException("当前账户被锁定，请联系安全保密管理员对账号进行解锁后再操作");
        }
        if (uaaUser.getLockTime() != null) {
            // 判断锁定时间
            if (new Date().before(uaaUser.getLockTime())) {
                logger.warn("账号：{}，校验TOTP时，因账号锁定状态被拒绝。", userDetail.getUsername());
                throw new BusinessException("当前账户被锁定，请联系安全保密管理员对账号进行解锁后再操作");
            }
        }
        if (uaaUser.getPasswordExpirationTime() != null) {
            // 判断密码有效期
            if (new Date().before(uaaUser.getPasswordExpirationTime())) {
                logger.warn("账号：{}，校验TOTP时，因账号过期被拒绝。", userDetail.getUsername());
                throw new BusinessException("当前账户密码已经过期，请联系安全保密管理员对账号进行密码修改后再操作");
            }
        }
        if (ObjectUtils.isEmpty(uaaUser.getTotp())) {
            logger.warn("账号：{}，校验TOTP时，因未开启两步认证U2F被拒绝。", userDetail.getUsername());
            throw new BusinessException("您未开启两步认证U2F");
        }
        return GoogleAuthenticator.authcode(totp, uaaUser.getTotp());
    }

    @Override
    public void addTotp(UserDetail userDetail, String totp) {
        UaaUserExample example = new UaaUserExample();
        UaaUserExample.Criteria criteria = example.createCriteria();
        criteria.andUsernameEqualTo(userDetail.getUsername());
        UaaUser uaaUser = ListUtils.getOne(uaaUserMapper.selectByExample(example));
        if (uaaUser == null) {
            logger.warn("账号：{}，设置TOTP秘钥时，因账号未找到被拒绝。", userDetail.getUsername());
            throw new BusinessException("未找到当前账户信息，请重试");
        }
        if (!ObjectUtils.isEmpty(uaaUser.getTotp())) {
            logger.warn("账号：{}，设置TOTP秘钥时，因账号已经启用TOTP被拒绝。", userDetail.getUsername());
            throw new BusinessException("当前账户已经启用TOTP，请先关闭后再操作");
        }
        uaaUser.setTotp(totp);
        uaaUserMapper.updateByPrimaryKeySelective(uaaUser);
    }

    @Override
    public void removeTotp(UserDetail userDetail) {
        UaaUserExample example = new UaaUserExample();
        UaaUserExample.Criteria criteria = example.createCriteria();
        criteria.andUsernameEqualTo(userDetail.getUsername());
        UaaUser uaaUser = ListUtils.getOne(uaaUserMapper.selectByExample(example));
        if (uaaUser == null) {
            logger.warn("账号：{}，设置TOTP秘钥时，因账号未找到被拒绝。", userDetail.getUsername());
            throw new BusinessException("未找到当前账户信息，请重试");
        }
        uaaUser.setTotp(null);
        uaaUserMapper.updateByPrimaryKey(uaaUser);
    }

    @Override
    public APIResult sendEmailVerCode(String newEmail) {
        UserDetail userDetail = systemService.currentUserDetail();
        if (userDetail == null) {
            return APIResult.builder()
                    .code(StateCodeEnum.Unauthorized)
                    .message(StateCodeEnum.Unauthorized.getDescribe())
                    .build();
        }
        if (ObjectUtils.isEmpty(newEmail)) {
            return APIResult.builder()
                    .code(StateCodeEnum.Failure)
                    .message("请先填写新的电子邮箱地址")
                    .build();
        }
        newEmail = newEmail.trim().toLowerCase();
        if (!StringUtils.isEmail(newEmail)) {
            return APIResult.builder()
                    .code(StateCodeEnum.Failure)
                    .message("请填写正确的电子邮箱地址")
                    .build();
        }
        if (newEmail.equals(userDetail.getEmail())) {
            return APIResult.builder()
                    .code(StateCodeEnum.Failure)
                    .message("新电子邮箱地址不能与旧邮箱地址一致")
                    .build();
        }
        verificationCodeService.sendVerificationCode(false,
                DateUtils.nextMinutes(10), newEmail, "UPDATE_EMAIL", userDetail, null);
        return APIResult.success();
    }

    @Override
    public APIResult updateEmail(String newEmail, String verCode) {
        UserDetail userDetail = systemService.currentUserDetail();
        if (userDetail == null) {
            return APIResult.builder()
                    .code(StateCodeEnum.Unauthorized)
                    .message(StateCodeEnum.Unauthorized.getDescribe())
                    .build();
        }
        if (ObjectUtils.isEmpty(newEmail)) {
            return APIResult.builder()
                    .code(StateCodeEnum.Failure)
                    .message("请先填写新的电子邮箱地址")
                    .build();
        }
        newEmail = newEmail.trim().toLowerCase();
        if (!StringUtils.isEmail(newEmail)) {
            return APIResult.builder()
                    .code(StateCodeEnum.Failure)
                    .message("请填写正确的电子邮箱地址")
                    .build();
        }
        if (userDetail.getEmail().trim().toLowerCase().equals(newEmail)) {
            return APIResult.builder()
                    .code(StateCodeEnum.Failure)
                    .message("新的电子邮箱地址不能跟旧的电子邮箱地址一样")
                    .build();
        }
        boolean verificationCode = verificationCodeService.verificationCode(verCode, newEmail, "UPDATE_EMAIL");
        if (!verificationCode) {
            return APIResult.builder()
                    .code(StateCodeEnum.Failure)
                    .message("验证码错误或已经过期")
                    .build();
        }
        // 查找新的电子邮箱地址是否被占用
        if (this.getUserDetailByEmail(newEmail).getData() != null) {
            return APIResult.builder()
                    .code(StateCodeEnum.Failure)
                    .message("新的电子邮箱地址已经被占用")
                    .build();
        }
        UaaUserExample example = new UaaUserExample();
        UaaUserExample.Criteria criteria = example.createCriteria();
        criteria.andUsernameEqualTo(userDetail.getUsername());
        UaaUser uaaUser = ListUtils.getOne(uaaUserMapper.selectByExample(example));
        if (uaaUser == null) {
            logger.error("在数据库中未能找到账户信息，UUID：{}，UserName：{}，Email：{}",
                    userDetail.getUuid(), userDetail.getUsername(), userDetail.getEmail());
            return APIResult.builder()
                    .code(StateCodeEnum.Failure)
                    .message("在数据库中未能找到您的账户信息")
                    .build();
        }
        uaaUser.setEmail(newEmail);
        uaaUser.setEmailVerified(true);
        uaaUserMapper.updateByPrimaryKey(uaaUser);
        return APIResult.success();
    }

    @Override
    public APIResult sendPhoneVerCode(String newPhone) {
        UserDetail userDetail = systemService.currentUserDetail();
        if (userDetail == null) {
            return APIResult.builder()
                    .code(StateCodeEnum.Unauthorized)
                    .message(StateCodeEnum.Unauthorized.getDescribe())
                    .build();
        }
        if (ObjectUtils.isEmpty(newPhone)) {
            return APIResult.builder()
                    .code(StateCodeEnum.Failure)
                    .message("手机号不能为空")
                    .build();
        }
        newPhone = newPhone.trim().toLowerCase().replace("+86", "");
        if (!StringUtils.isChinaPhone(newPhone)) {
            return APIResult.builder()
                    .code(StateCodeEnum.Failure)
                    .message("只支持中国大陆的手机号码")
                    .build();
        }
        verificationCodeService.sendVerificationCode(false,
                DateUtils.nextMinutes(10), newPhone, "UPDATE_PHONE", userDetail, null);
        return APIResult.success();
    }

    @Override
    public APIResult updatePhone(String newPhone, String verCode) {
        UserDetail userDetail = systemService.currentUserDetail();
        if (userDetail == null) {
            return APIResult.builder()
                    .code(StateCodeEnum.Unauthorized)
                    .message(StateCodeEnum.Unauthorized.getDescribe())
                    .build();
        }
        if (ObjectUtils.isEmpty(newPhone) || newPhone.isEmpty()) {
            return APIResult.builder()
                    .code(StateCodeEnum.Failure)
                    .message("手机号不能为空")
                    .build();
        }
        newPhone = newPhone.trim().toLowerCase().replace("+86", "");
        if (!StringUtils.isChinaPhone(newPhone)) {
            return APIResult.builder()
                    .code(StateCodeEnum.Failure)
                    .message("只支持中国大陆的手机号码")
                    .build();
        }
        if (!ObjectUtils.isEmpty(userDetail.getPhone())) {
            if (userDetail.getPhone().trim().toLowerCase().equals(newPhone)) {
                return APIResult.builder()
                        .code(StateCodeEnum.Failure)
                        .message("新的手机号码不能跟旧的手机号码一样")
                        .build();
            }
        }
        boolean verificationCode =
                verificationCodeService.verificationCode(verCode, newPhone, "UPDATE_PHONE");
        if (!verificationCode) {
            return APIResult.builder()
                    .code(StateCodeEnum.Failure)
                    .message("验证码错误或已经过期")
                    .build();
        }
        // 查找新的手机号码是否被占用
        if (this.getUserDetailByPhone(newPhone).getData() != null) {
            return APIResult.builder()
                    .code(StateCodeEnum.Failure)
                    .message("新的手机号码已经被占用")
                    .build();
        }
        UaaUserExample example = new UaaUserExample();
        UaaUserExample.Criteria criteria = example.createCriteria();
        criteria.andUsernameEqualTo(userDetail.getUsername());
        UaaUser uaaUser = ListUtils.getOne(uaaUserMapper.selectByExample(example));
        if (uaaUser == null) {
            logger.error("在数据库中未能找到账户信息，UUID：{}，UserName：{}，Email：{}",
                    userDetail.getUuid(), userDetail.getUsername(), userDetail.getEmail());
            return APIResult.builder()
                    .code(StateCodeEnum.Failure)
                    .message("在数据库中未能找到您的账户信息")
                    .build();
        }
        uaaUser.setPhone(newPhone);
        uaaUser.setPhoneVerified(true);
        uaaUserMapper.updateByPrimaryKey(uaaUser);
        return APIResult.success();
    }

    @Override
    public APIResult updateFirstName(String firstName, String lastName) {
        UserDetail userDetail = systemService.currentUserDetail();
        if (userDetail == null) {
            return APIResult.builder()
                    .code(StateCodeEnum.Unauthorized)
                    .message(StateCodeEnum.Unauthorized.getDescribe())
                    .build();
        }
        if (!ObjectUtils.isEmpty(firstName) && firstName.length() > MAX_FIRST_NAME) {
            return APIResult.builder()
                    .code(StateCodeEnum.Failure)
                    .message("姓氏超过系统允许最大长度 " + MAX_FIRST_NAME + " 位。")
                    .build();
        }
        if (!ObjectUtils.isEmpty(lastName) && lastName.length() > MAX_LAST_NAME) {
            return APIResult.builder()
                    .code(StateCodeEnum.Failure)
                    .message("名称超过系统允许最大长度 " + MAX_LAST_NAME + " 位。")
                    .build();
        }
        UaaUserExample example = new UaaUserExample();
        UaaUserExample.Criteria criteria = example.createCriteria();
        criteria.andUsernameEqualTo(userDetail.getUsername());
        UaaUser uaaUser = ListUtils.getOne(uaaUserMapper.selectByExample(example));
        uaaUser.setFirstName(firstName);
        uaaUser.setLastName(lastName);
        uaaUserMapper.updateByPrimaryKey(uaaUser);
        return APIResult.success();
    }

    private UserDetail convert(UaaUser uaaUser) {
        if (uaaUser == null) {
            return null;
        }
        UserDetail userDetail = new UserDetail();
        userDetail.setId(uaaUser.getId() + "");
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
        List<RoleDetail> data = new ArrayList<>();
        if (systemConfig.getEnableSuperTubeUser()
                && uaaUser.getUsername().equals(systemConfig.getSuperTubeUserName())) {
            data = roleService.queryRoleList(false, null, 1, Integer.MAX_VALUE).getData().getData();
        } else {
            data = roleService.queryRoleListByUser(uaaUser.getId(), 1, Integer.MAX_VALUE).getData();
        }
        userDetail.setRoleDetailList(data);
        return userDetail;
    }

    private void checkDuplicateUsername(String username) {
        if (username == null || username.isEmpty()) {
            throw new BusinessException("用户名不能为空");
        }
        // 检查保留用户名
        UaaUserKeepNameExample userKeepNameExample = new UaaUserKeepNameExample();
        userKeepNameExample.createCriteria().andUserNameEqualTo(username.trim().toLowerCase());
        List<UaaUserKeepName> keepNames = uaaUserKeepNameMapper.selectByExample(userKeepNameExample);
        if (keepNames != null && keepNames.size() > 0) {
            throw new BusinessException("用户名已经被占用，请换个用户名试试");
        }
        // 检查用户名重复
        UaaUserExample example = new UaaUserExample();
        example.createCriteria().andUsernameEqualTo(username.trim().toLowerCase());
        UaaUser uaaUser = ListUtils.getOne(uaaUserMapper.selectByExample(example));
        if (uaaUser != null) {
            throw new BusinessException("用户名已经被占用，请换个用户名试试");
        }
    }

    private void checkDuplicateEmail(String email) {
        if (email == null || email.isEmpty()) {
            throw new BusinessException("电子邮箱不能为空");
        }
        // 检查Email重复
        UaaUserExample example = new UaaUserExample();
        example.createCriteria().andEmailEqualTo(email.trim().toLowerCase()).andEmailVerifiedEqualTo(true);
        UaaUser uaaUser = ListUtils.getOne(uaaUserMapper.selectByExample(example));
        if (uaaUser != null) {
            throw new BusinessException("电子邮箱地址已经被注册，您不妨直接登录试试");
        }
    }

    private void checkDuplicatePhone(String phone) {
        // 检查Email重复
        UaaUserExample example = new UaaUserExample();
        example.createCriteria().andEmailEqualTo(phone.trim().toLowerCase()).andEmailVerifiedEqualTo(true);
        UaaUser uaaUser = ListUtils.getOne(uaaUserMapper.selectByExample(example));
        if (uaaUser != null) {
            throw new BusinessException("手机号已经被注册，您不妨直接登录试试");
        }
    }

    private UaaUser convert(UserDetail userDetail) {
        if (userDetail == null) {
            return null;
        }
        UaaUser uaaUser = new UaaUser();
        uaaUser.setId(Long.parseLong(userDetail.getId()));
        uaaUser.setUuid(userDetail.getUuid());
        uaaUser.setUsername(userDetail.getUsername());
        uaaUser.setEmail(userDetail.getEmail());
        uaaUser.setEmailVerified(userDetail.getEmailVerified());
        uaaUser.setPhone(userDetail.getPhone());
        uaaUser.setPhoneVerified(userDetail.getPhoneVerified());
        uaaUser.setRegistrationDate(userDetail.getRegistrationDate());
        uaaUser.setPassword(userDetail.getPassword());
        uaaUser.setTotp(userDetail.getTotp());
        uaaUser.setRegistrationIp(userDetail.getRegistrationIp());
        uaaUser.setTrialErrorTimes(userDetail.getTrialErrorTimes());
        uaaUser.setLockTime(userDetail.getLockTime());
        uaaUser.setSecretLevel(userDetail.getSecretLevel().getLevel());
        uaaUser.setBuiltInUser(userDetail.getBuiltInUser());
        uaaUser.setPasswordExpirationTime(userDetail.getPasswordExpirationTime());
        uaaUser.setLocked(userDetail.getLocked());
        uaaUser.setEnabled(userDetail.getEnabled());
        uaaUser.setLastName(userDetail.getLastName());
        uaaUser.setFirstName(userDetail.getFirstName());
        return uaaUser;
    }

    /**
     * 填充用户拥有的角色列表
     *
     * @param userDetail
     */
    private void fillRoleDetailList(UserDetail userDetail) {
        APIResult<List<RoleDetail>> roleListByUser = roleService.queryRoleListByUser(Long.parseLong(userDetail.getId()), 1, Integer.MAX_VALUE);
        userDetail.setRoleDetailList(
                roleListByUser.getData()
        );
    }
}
