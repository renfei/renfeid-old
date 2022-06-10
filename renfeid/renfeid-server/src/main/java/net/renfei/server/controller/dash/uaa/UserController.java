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
package net.renfei.server.controller.dash.uaa;

import net.renfei.common.api.constant.APIResult;
import net.renfei.common.api.constant.enums.SecretLevelEnum;
import net.renfei.common.api.constant.enums.StateCodeEnum;
import net.renfei.common.api.entity.ListData;
import net.renfei.common.api.utils.StringUtils;
import net.renfei.common.core.annotation.OperationLog;
import net.renfei.common.core.entity.SystemTypeEnum;
import net.renfei.server.controller.AbstractController;
import net.renfei.uaa.api.AuthorizationService;
import net.renfei.uaa.api.UserService;
import net.renfei.uaa.api.entity.UserDetail;
import org.springframework.util.ObjectUtils;
import org.springframework.web.bind.annotation.*;

import static net.renfei.common.core.config.SystemConfig.MAX_USERNAME_LENGTH;

/**
 * @author renfei
 */
@RestController
@RequestMapping("/_/api/uaa")
public class UserController extends AbstractController {
    private final UserService userService;
    private final AuthorizationService authorizationService;

    public UserController(UserService userService,
                          AuthorizationService authorizationService) {
        this.userService = userService;
        this.authorizationService = authorizationService;
    }

    @GetMapping("user")
    @OperationLog(module = SystemTypeEnum.ACCOUNT, desc = "查询用户账户列表")
    public APIResult<ListData<UserDetail>>
    queryUserList(@RequestParam(value = "username", required = false) String username,
                  @RequestParam(value = "email", required = false) String email,
                  @RequestParam(value = "phone", required = false) String phone,
                  @RequestParam(value = "ip", required = false) String ip,
                  @RequestParam(value = "secretLevel", required = false) SecretLevelEnum secretLevel,
                  @RequestParam(value = "locked", required = false) Boolean locked,
                  @RequestParam(value = "enabled", required = false) Boolean enabled,
                  @RequestParam(value = "pages", required = false) Integer pages,
                  @RequestParam(value = "rows", required = false) Integer rows) {
        return userService.queryUserList(username, email, phone, ip, secretLevel, locked, enabled, pages, rows);
    }

    @PostMapping("user")
    public APIResult<UserDetail> createUser(@RequestBody UserDetail userDetail) {
        if (userDetail.getUsername().trim().toLowerCase().length() >= MAX_USERNAME_LENGTH) {
            return APIResult.builder().code(StateCodeEnum.Failure).message("用户名长度超过系统允许的最大值：" + MAX_USERNAME_LENGTH).build();
        }
        if (userDetail.getEmail().length() >= MAX_USERNAME_LENGTH) {
            return APIResult.builder().code(StateCodeEnum.Failure).message("邮箱地址长度超过系统允许最大值：" + MAX_USERNAME_LENGTH).build();
        }
        if (ObjectUtils.isEmpty(userDetail.getUsername().trim())) {
            return APIResult.builder().code(StateCodeEnum.Failure).message("用户名不能为空").build();
        }
        if (userDetail.getUsername().trim().getBytes().length < 4) {
            return APIResult.builder().code(StateCodeEnum.Failure).message("用户名长度过短，请重起一个名字吧").build();
        }
        if (ObjectUtils.isEmpty(userDetail.getEmail().trim())) {
            return APIResult.builder().code(StateCodeEnum.Failure).message("电子邮箱不能为空").build();
        }
        if (StringUtils.isEmail(userDetail.getUsername().trim())) {
            return APIResult.builder().code(StateCodeEnum.Failure).message("不能使用电子邮件地址作为用户名").build();
        }
        if (StringUtils.isChinaPhone(userDetail.getUsername().trim())) {
            return APIResult.builder().code(StateCodeEnum.Failure).message("不能使用手机号码作为用户名").build();
        }
        if (StringUtils.isDomain(userDetail.getUsername().trim())) {
            return APIResult.builder().code(StateCodeEnum.Failure).message("用户名格式不正确，请换个用户名试试").build();
        }
        if (ObjectUtils.isEmpty(userDetail.getPassword())) {
            return APIResult.builder().code(StateCodeEnum.Failure).message("密码不能为空").build();
        }
        if (!StringUtils.isEmail(userDetail.getEmail().trim())) {
            return APIResult.builder().code(StateCodeEnum.Failure).message("您填写的电子邮箱地址格式不正确").build();
        }
        userDetail.setPassword(authorizationService.decryptAesByKeyId(
                userDetail.getPassword(), userDetail.getKeyUuid()
        ).getData());
        return userService.createUser(userDetail, request);
    }
}
