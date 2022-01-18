package net.renfei.controllers.api;

import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.tags.Tag;
import net.renfei.annotation.OperationLog;
import net.renfei.model.APIResult;
import net.renfei.model.account.SaveU2FVO;
import net.renfei.model.account.UpdatePasswordVO;
import net.renfei.model.log.OperationTypeEnum;
import net.renfei.model.system.SystemTypeEnum;
import org.springframework.web.bind.annotation.*;

/**
 * 前台账户管理接口
 *
 * @author renfei
 */
@RequestMapping("/-/api/account")
@Tag(name = "前台接口", description = "前台账户管理接口")
public interface AccountApi {
    @PostMapping("manage/email/verCode")
    @Operation(summary = "修改邮箱，发送验证邮件", tags = {"前台接口"})
    @OperationLog(module = SystemTypeEnum.ACCOUNT, desc = "修改邮箱，发送验证邮件")
    APIResult sendEmailVerCode(@RequestParam("newEmail") String newEmail);

    @PostMapping("manage/email")
    @Operation(summary = "修改邮箱", tags = {"前台接口"})
    @OperationLog(module = SystemTypeEnum.ACCOUNT, desc = "修改邮箱", operation = OperationTypeEnum.UPDATE)
    APIResult updateEmail(@RequestParam("newEmail") String newEmail, @RequestParam("verCode") String verCode);

    @PostMapping("manage/phone/verCode")
    @Operation(summary = "修改手机号，发送短信验证码", tags = {"前台接口"})
    @OperationLog(module = SystemTypeEnum.ACCOUNT, desc = "修改手机号，发送短信验证码")
    APIResult sendPhoneVerCode(@RequestParam("newPhone") String newPhone);

    @PostMapping("manage/phone")
    @Operation(summary = "修改手机号", tags = {"前台接口"})
    @OperationLog(module = SystemTypeEnum.ACCOUNT, desc = "修改手机号", operation = OperationTypeEnum.UPDATE)
    APIResult updatePhone(@RequestParam("newPhone") String newPhone,
                          @RequestParam("verCode") String verCode);

    @PostMapping("manage/password")
    @Operation(summary = "修改密码", tags = {"前台接口"})
    @OperationLog(module = SystemTypeEnum.ACCOUNT, desc = "修改密码", operation = OperationTypeEnum.UPDATE)
    APIResult updatePassword(@RequestBody UpdatePasswordVO updatePasswordVO);

    @PostMapping("manage/u2f")
    @Operation(summary = "修改U2F两步验证", tags = {"前台接口"})
    @OperationLog(module = SystemTypeEnum.ACCOUNT, desc = "修改U2F两步验证", operation = OperationTypeEnum.UPDATE)
    APIResult manageU2FSave(@RequestBody SaveU2FVO saveU2FVO);

    @DeleteMapping("manage/u2f")
    @Operation(summary = "关闭U2F两步验证", tags = {"前台接口"})
    @OperationLog(module = SystemTypeEnum.ACCOUNT, desc = "关闭U2F两步验证", operation = OperationTypeEnum.DELETE)
    APIResult closeU2F(@RequestBody SaveU2FVO saveU2FVO);
}
