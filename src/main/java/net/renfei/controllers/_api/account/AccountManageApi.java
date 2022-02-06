package net.renfei.controllers._api.account;

import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.tags.Tag;
import net.renfei.domain.user.User;
import net.renfei.model.APIResult;
import net.renfei.model.ListData;
import net.renfei.model.SecretLevelEnum;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;

/**
 * 用户管理API
 *
 * @author renfei
 */
@Tag(name = "账户管理接口", description = "账户管理接口")
@RequestMapping("/_/api")
public interface AccountManageApi {
    /**
     * 查询账户列表
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
    @GetMapping("account")
    @Operation(summary = "获取账户列表", tags = {"账户管理接口"}, description = "获取账户列表")
    APIResult<ListData<User>> queryAccountList(@RequestParam(value = "userName", required = false) String userName,
                                               @RequestParam(value = "email", required = false) String email,
                                               @RequestParam(value = "phone", required = false) String phone,
                                               @RequestParam(value = "stateCode", required = false) Integer stateCode,
                                               @RequestParam(value = "secretLevel", required = false) SecretLevelEnum secretLevelEnum,
                                               @RequestParam(value = "pages", required = false) String pages,
                                               @RequestParam(value = "rows", required = false) String rows);
}
