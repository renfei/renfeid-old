package net.renfei.console.controller.account.impl;

import net.renfei.annotation.OperationLog;
import net.renfei.controller.BaseController;
import net.renfei.console.controller.account.AccountManageApi;
import net.renfei.domain.user.User;
import net.renfei.model.APIResult;
import net.renfei.model.ListData;
import net.renfei.model.SecretLevelEnum;
import net.renfei.model.log.OperationTypeEnum;
import net.renfei.model.system.SystemTypeEnum;
import net.renfei.services.AccountService;
import org.springframework.web.bind.annotation.RestController;

/**
 * 用户管理API
 *
 * @author renfei
 */
@RestController
public class AccountManageApiController extends BaseController implements AccountManageApi {
    private final AccountService accountService;

    public AccountManageApiController(AccountService accountService) {
        this.accountService = accountService;
    }

    /**
     * 查询用户信息列表
     *
     * @param userName        用户名
     * @param email           邮箱地址
     * @param phone           电话号码
     * @param stateCode       状态
     * @param secretLevelEnum 保密等级
     * @param pages           页码
     * @param rows            每页行数
     * @return
     */
    @Override
    @OperationLog(module = SystemTypeEnum.ACCOUNT, desc = "查询用户列表", operation = OperationTypeEnum.RETRIEVE)
    public APIResult<ListData<User>> queryAccountList(String userName, String email, String phone, Integer stateCode,
                                                      SecretLevelEnum secretLevelEnum, String pages, String rows) {
        return new APIResult<>(accountService.queryAccountList(userName, email, phone, stateCode, secretLevelEnum, pages, rows));
    }

    /**
     * 给用户定密级
     *
     * @param userName        用户名
     * @param secretLevelEnum 密级
     * @return
     */
    @Override
    public APIResult settingSecretLevel(String userName, SecretLevelEnum secretLevelEnum) {
        accountService.settingAccountSecretLevel(userName, secretLevelEnum);
        return APIResult.success();
    }
}
