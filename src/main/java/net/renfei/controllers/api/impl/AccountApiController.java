package net.renfei.controllers.api.impl;

import net.renfei.controllers.BaseController;
import net.renfei.controllers.api.AccountApi;
import net.renfei.domain.user.User;
import net.renfei.exception.BusinessException;
import net.renfei.model.APIResult;
import net.renfei.model.StateCodeEnum;
import net.renfei.model.account.SaveU2FVO;
import net.renfei.model.account.UpdatePasswordVO;
import net.renfei.repositories.model.SysAccount;
import net.renfei.repositories.model.SysVerificationCode;
import net.renfei.services.AccountService;
import net.renfei.services.EmailService;
import net.renfei.services.SysService;
import net.renfei.services.VerificationCodeService;
import net.renfei.utils.DateUtils;
import net.renfei.utils.GoogleAuthenticator;
import net.renfei.utils.PasswordUtils;
import net.renfei.utils.StringUtils;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.BeanUtils;
import org.springframework.util.ObjectUtils;
import org.springframework.web.bind.annotation.RestController;

/**
 * 前台账户管理接口
 *
 * @author renfei
 */
@RestController
public class AccountApiController extends BaseController implements AccountApi {
    private static final Logger logger = LoggerFactory.getLogger(AccountApiController.class);
    private final SysService sysService;
    private final EmailService emailService;
    private final AccountService accountService;
    private final VerificationCodeService verificationCodeService;

    public AccountApiController(SysService sysService,
                                EmailService emailService,
                                AccountService accountService,
                                VerificationCodeService verificationCodeService) {
        this.sysService = sysService;
        this.emailService = emailService;
        this.accountService = accountService;
        this.verificationCodeService = verificationCodeService;
    }

    @Override
    public APIResult sendEmailVerCode(String newEmail) {
        if (getSignUser() == null) {
            return APIResult.builder()
                    .code(StateCodeEnum.Failure)
                    .message("请先登录")
                    .build();
        }
        if (ObjectUtils.isEmpty(newEmail) || newEmail.isEmpty()) {
            return APIResult.builder()
                    .code(StateCodeEnum.Failure)
                    .message("邮箱不能为空")
                    .build();
        }
        newEmail = newEmail.trim().toLowerCase();
        if (!StringUtils.isEmail(newEmail)) {
            return APIResult.builder()
                    .code(StateCodeEnum.Failure)
                    .message("请填写正确的电子邮箱地址")
                    .build();
        }
        SysAccount account = new SysAccount();
        BeanUtils.copyProperties(getSignUser(), account);
        verificationCodeService.sendVerificationCode(false,
                DateUtils.nextMinutes(10), newEmail, "UPDATE_EMAIL", account, null);
        return APIResult.success();
    }

    @Override
    public APIResult updateEmail(String newEmail, String verCode) {
        User user = getSignUser();
        if (user == null) {
            return APIResult.builder()
                    .code(StateCodeEnum.Failure)
                    .message("请先登录")
                    .build();
        }
        if (ObjectUtils.isEmpty(newEmail) || newEmail.isEmpty()) {
            return APIResult.builder()
                    .code(StateCodeEnum.Failure)
                    .message("邮箱不能为空")
                    .build();
        }
        newEmail = newEmail.trim().toLowerCase();
        if (!StringUtils.isEmail(newEmail)) {
            return APIResult.builder()
                    .code(StateCodeEnum.Failure)
                    .message("请填写正确的电子邮箱地址")
                    .build();
        }
        if (user.getEmail().trim().toLowerCase().equals(newEmail)) {
            return APIResult.builder()
                    .code(StateCodeEnum.Failure)
                    .message("新的电子邮箱地址不能跟旧的电子邮箱地址一样")
                    .build();
        }
        SysVerificationCode verificationCode =
                verificationCodeService.verificationCode(verCode, newEmail, "UPDATE_EMAIL");
        if (verificationCode == null) {
            return APIResult.builder()
                    .code(StateCodeEnum.Failure)
                    .message("验证码错误或已经过期")
                    .build();
        }
        // 查找新的电子邮箱地址是否被占用
        if (accountService.getAccountByEmail(newEmail) != null) {
            return APIResult.builder()
                    .code(StateCodeEnum.Failure)
                    .message("新的电子邮箱地址已经被占用")
                    .build();
        }

        SysAccount account = accountService.getAccountByUser(user);
        if (account == null) {
            logger.error("在数据库中未能找到账户信息，UUID：{}，UserName：{}，Email：{}",
                    user.getUuid(), user.getUserName(), user.getEmail());
            return APIResult.builder()
                    .code(StateCodeEnum.Failure)
                    .message("在数据库中未能找到您的账户信息")
                    .build();
        }
        account.setEmail(newEmail);
        accountService.updateAccount(account);
        user.setEmail(newEmail);
        updateSignUser(user);
        return APIResult.success();
    }

    @Override
    public APIResult sendPhoneVerCode(String newPhone) {
        User user = getSignUser();
        if (user == null) {
            return APIResult.builder()
                    .code(StateCodeEnum.Failure)
                    .message("请先登录")
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
        SysAccount account = new SysAccount();
        BeanUtils.copyProperties(getSignUser(), account);
        verificationCodeService.sendVerificationCode(false,
                DateUtils.nextMinutes(10), newPhone, "UPDATE_PHONE", account, null);
        return APIResult.success();
    }

    @Override
    public APIResult updatePhone(String newPhone, String verCode) {
        User user = getSignUser();
        if (user == null) {
            return APIResult.builder()
                    .code(StateCodeEnum.Failure)
                    .message("请先登录")
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
        if (!ObjectUtils.isEmpty(user.getPhone()) && !user.getPhone().isEmpty()) {
            if (user.getPhone().trim().toLowerCase().equals(newPhone)) {
                return APIResult.builder()
                        .code(StateCodeEnum.Failure)
                        .message("新的手机号码不能跟旧的手机号码一样")
                        .build();
            }
        }
        SysVerificationCode verificationCode =
                verificationCodeService.verificationCode(verCode, newPhone, "UPDATE_PHONE");
        if (verificationCode == null) {
            return APIResult.builder()
                    .code(StateCodeEnum.Failure)
                    .message("验证码错误或已经过期")
                    .build();
        }
        // 查找新的手机号码是否被占用
        if (accountService.getAccountByPhone(newPhone) != null) {
            return APIResult.builder()
                    .code(StateCodeEnum.Failure)
                    .message("新的电子邮箱地址已经被占用")
                    .build();
        }

        SysAccount account = accountService.getAccountByUser(user);
        if (account == null) {
            logger.error("在数据库中未能找到账户信息，UUID：{}，UserName：{}，Email：{}",
                    user.getUuid(), user.getUserName(), user.getEmail());
            return APIResult.builder()
                    .code(StateCodeEnum.Failure)
                    .message("在数据库中未能找到您的账户信息")
                    .build();
        }
        account.setPhone(newPhone);
        if (account.getStateCode() == 2) {
            account.setStateCode(3);
        }
        accountService.updateAccount(account);
        user.setPhone(newPhone);
        updateSignUser(user);
        return APIResult.success();
    }

    @Override
    public APIResult updatePassword(UpdatePasswordVO updatePasswordVO) {
        User user = getSignUser();
        if (user == null) {
            return APIResult.builder()
                    .code(StateCodeEnum.Failure)
                    .message("请先登录")
                    .build();
        }
        try {
            updatePasswordVO.setOldPwd(sysService.decrypt(updatePasswordVO.getOldPwd(), updatePasswordVO.getKeyId()));
            updatePasswordVO.setNewPwd(sysService.decrypt(updatePasswordVO.getNewPwd(), updatePasswordVO.getKeyId()));
            SysAccount account = accountService.getAccountByUser(user);
            accountService.updatePassword(account, updatePasswordVO);
            return APIResult.success();
        } catch (BusinessException | PasswordUtils.CannotPerformOperationException businessException) {
            return APIResult.builder().code(StateCodeEnum.Failure).message(businessException.getMessage()).build();
        }
    }

    @Override
    public APIResult manageU2FSave(SaveU2FVO saveU2FVO) {
        User user = getSignUser();
        if (user == null) {
            return APIResult.builder()
                    .code(StateCodeEnum.Failure)
                    .message("请先登录")
                    .build();
        }
        if (ObjectUtils.isEmpty(user.getTotp()) || user.getTotp().isEmpty()) {
            saveU2FVO.setPwd(sysService.decrypt(saveU2FVO.getPwd(), saveU2FVO.getKeyId()));
            saveU2FVO.setSecretKey(sysService.decrypt(saveU2FVO.getSecretKey(), saveU2FVO.getKeyId()));
            SysAccount account = accountService.getAccountByUser(user);
            if (PasswordUtils.verifyPassword(saveU2FVO.getPwd(), account.getPassword())) {
                if (GoogleAuthenticator.authcode(saveU2FVO.getTotp(), saveU2FVO.getSecretKey())) {
                    account.setTotp(saveU2FVO.getSecretKey());
                    accountService.updateAccount(account);
                    user.setTotp(saveU2FVO.getSecretKey());
                    updateSignUser(user);
                    emailService.send(
                            account.getEmail(),
                            account.getUserName(),
                            "操作通知：您开启了U2F两步认证",
                            "您开启了U2F两步认证，您的账户安全性固若金汤。如果不是您本人操作，请立即登陆修改密码并联系我们，您的账户可能被盗用。"
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
                        .message("密码不正确")
                        .build();
            }
        } else {
            return APIResult.builder()
                    .code(StateCodeEnum.Failure)
                    .message("两步认证U2F已经是开启状态，无需再次开启")
                    .build();
        }
    }

    @Override
    public APIResult closeU2F(SaveU2FVO saveU2FVO) {
        User user = getSignUser();
        if (user == null) {
            return APIResult.builder()
                    .code(StateCodeEnum.Failure)
                    .message("请先登录")
                    .build();
        }
        if (ObjectUtils.isEmpty(user.getTotp()) || user.getTotp().isEmpty()) {
            return APIResult.builder()
                    .code(StateCodeEnum.Failure)
                    .message("两步认证U2F已经是关闭状态，无需再次关闭")
                    .build();
        }
        saveU2FVO.setPwd(sysService.decrypt(saveU2FVO.getPwd(), saveU2FVO.getKeyId()));
        SysAccount account = accountService.getAccountByUser(user);
        if (PasswordUtils.verifyPassword(saveU2FVO.getPwd(), account.getPassword())) {
            if (GoogleAuthenticator.authcode(saveU2FVO.getTotp(), account.getTotp())) {
                account.setTotp(null);
                accountService.updateAccountAll(account);
                user.setTotp(null);
                updateSignUser(user);
                emailService.send(
                        account.getEmail(),
                        account.getUserName(),
                        "操作通知：您关闭了U2F两步认证",
                        "您关闭了U2F两步认证，如果不是您本人操作，请立即登陆修改密码并联系我们，您的账户可能被盗用。"
                );
                return APIResult.success();
            } else {
                return APIResult.builder()
                        .code(StateCodeEnum.Failure)
                        .message("您输入的两步认证码校验失败，请重试")
                        .build();
            }
        } else {
            return APIResult.builder()
                    .code(StateCodeEnum.Failure)
                    .message("密码不正确")
                    .build();
        }
    }
}
