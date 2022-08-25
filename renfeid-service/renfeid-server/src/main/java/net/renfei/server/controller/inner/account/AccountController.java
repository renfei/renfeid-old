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
package net.renfei.server.controller.inner.account;

import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.tags.Tag;
import net.renfei.common.api.constant.APIResult;
import net.renfei.common.core.annotation.OperationLog;
import net.renfei.common.core.entity.OperationTypeEnum;
import net.renfei.common.core.entity.SystemTypeEnum;
import net.renfei.server.controller.AbstractController;
import net.renfei.uaa.api.UserService;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

/**
 * 前台账户管理接口
 *
 * @author renfei
 */
@RestController
@RequestMapping("/-/api/account")
@Tag(name = "前台账户管理接口", description = "前台账户管理接口")
public class AccountController extends AbstractController {
    private final UserService userService;

    public AccountController(UserService userService) {
        this.userService = userService;
    }

    @PostMapping("email/verCode")
    @Operation(summary = "修改邮箱，发送验证邮件", tags = {"前台账户管理接口"})
    @OperationLog(module = SystemTypeEnum.ACCOUNT, desc = "修改邮箱，发送验证邮件")
    public APIResult sendEmailVerCode(@RequestParam("newEmail") String newEmail) {
        return userService.sendEmailVerCode(newEmail);
    }

    @PostMapping("email")
    @Operation(summary = "修改邮箱", tags = {"前台账户管理接口"})
    @OperationLog(module = SystemTypeEnum.ACCOUNT, desc = "修改邮箱", operation = OperationTypeEnum.UPDATE)
    public APIResult updateEmail(@RequestParam("newEmail") String newEmail, @RequestParam("verCode") String verCode) {
        return userService.updateEmail(newEmail, verCode);
    }

    @PostMapping("phone/verCode")
    @Operation(summary = "修改手机号，发送短信验证码", tags = {"前台账户管理接口"})
    @OperationLog(module = SystemTypeEnum.ACCOUNT, desc = "修改手机号，发送短信验证码")
    public APIResult sendPhoneVerCode(@RequestParam("newPhone") String newPhone) {
        return userService.sendPhoneVerCode(newPhone);
    }

    @PostMapping("phone")
    @Operation(summary = "修改手机号", tags = {"前台账户管理接口"})
    @OperationLog(module = SystemTypeEnum.ACCOUNT, desc = "前台账户管理接口", operation = OperationTypeEnum.UPDATE)
    public APIResult updatePhone(@RequestParam("newPhone") String newPhone,
                                 @RequestParam("verCode") String verCode) {
        return userService.updatePhone(newPhone, verCode);
    }

    @PostMapping({"firstName", "lastName"})
    @Operation(summary = "修改姓名称呼", tags = {"前台账户管理接口"})
    @OperationLog(module = SystemTypeEnum.ACCOUNT, desc = "修改姓名称呼", operation = OperationTypeEnum.UPDATE)
    public APIResult updateFirstName(@RequestParam(value = "firstName", required = false) String firstName,
                                     @RequestParam(value = "lastName", required = false) String lastName) {
        return userService.updateFirstName(firstName, lastName);
    }
}
