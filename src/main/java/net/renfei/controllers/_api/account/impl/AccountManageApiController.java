package net.renfei.controllers._api.account.impl;

import net.renfei.annotation.OperationLog;
import net.renfei.controllers.BaseController;
import net.renfei.controllers._api.account.AccountManageApi;
import net.renfei.domain.UserDomain;
import net.renfei.domain.user.User;
import net.renfei.model.APIResult;
import net.renfei.model.ListData;
import net.renfei.model.SecretLevelEnum;
import net.renfei.model.log.OperationTypeEnum;
import net.renfei.model.system.SystemTypeEnum;
import org.springframework.web.bind.annotation.RestController;

/**
 * 用户管理API
 *
 * @author renfei
 */
@RestController
public class AccountManageApiController extends BaseController implements AccountManageApi {

    @Override
    @OperationLog(module = SystemTypeEnum.ACCOUNT, desc = "查询用户列表", operation = OperationTypeEnum.RETRIEVE)
    public APIResult<ListData<User>> queryAccountList(String userName, String email, String phone, Integer stateCode,
                                                      SecretLevelEnum secretLevelEnum, String pages, String rows) {
        return new APIResult<>(UserDomain.queryUserList(userName, email, phone, stateCode, secretLevelEnum, pages, rows));
    }
}
