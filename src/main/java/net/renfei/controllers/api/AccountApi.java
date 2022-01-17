package net.renfei.controllers.api;

import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.tags.Tag;
import net.renfei.annotation.OperationLog;
import net.renfei.model.APIResult;
import net.renfei.model.system.SystemTypeEnum;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;

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
    APIResult updateEmail(@RequestParam("newEmail") String newEmail, @RequestParam("verCode") String verCode);
}
