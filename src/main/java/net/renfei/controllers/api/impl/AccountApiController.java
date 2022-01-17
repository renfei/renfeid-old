package net.renfei.controllers.api.impl;

import net.renfei.controllers.BaseController;
import net.renfei.controllers.api.AccountApi;
import net.renfei.domain.user.User;
import net.renfei.model.APIResult;
import net.renfei.model.StateCodeEnum;
import net.renfei.repositories.model.SysAccount;
import net.renfei.repositories.model.SysVerificationCode;
import net.renfei.services.AccountService;
import net.renfei.services.VerificationCodeService;
import net.renfei.utils.DateUtils;
import net.renfei.utils.StringUtils;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.BeanUtils;
import org.springframework.web.bind.annotation.RestController;

/**
 * 前台账户管理接口
 *
 * @author renfei
 */
@RestController
public class AccountApiController extends BaseController implements AccountApi {
    private static final Logger logger = LoggerFactory.getLogger(AccountApiController.class);
    private final AccountService accountService;
    private final VerificationCodeService verificationCodeService;

    public AccountApiController(AccountService accountService,
                                VerificationCodeService verificationCodeService) {
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
}
