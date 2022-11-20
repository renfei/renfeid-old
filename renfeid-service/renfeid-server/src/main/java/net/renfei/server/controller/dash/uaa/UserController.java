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

import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.Parameter;
import io.swagger.v3.oas.annotations.tags.Tag;
import net.renfei.common.api.constant.APIResult;
import net.renfei.common.api.constant.enums.SecretLevelEnum;
import net.renfei.common.api.constant.enums.StateCodeEnum;
import net.renfei.common.api.entity.ListData;
import net.renfei.common.api.exception.BusinessException;
import net.renfei.common.api.utils.StringUtils;
import net.renfei.common.core.annotation.OperationLog;
import net.renfei.common.core.entity.OperationTypeEnum;
import net.renfei.common.core.entity.SystemTypeEnum;
import net.renfei.server.controller.AbstractController;
import net.renfei.uaa.api.AuthorizationService;
import net.renfei.uaa.api.UserService;
import net.renfei.uaa.api.entity.ResetPasswordAo;
import net.renfei.uaa.api.entity.RoleDetail;
import net.renfei.uaa.api.entity.UserDetail;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.util.ObjectUtils;
import org.springframework.web.bind.annotation.*;

import java.util.List;

import static net.renfei.common.core.config.SystemConfig.MAX_USERNAME_LENGTH;

/**
 * 用户管理接口
 *
 * @author renfei
 */
@RestController
@RequestMapping("/_/api/uaa")
@Tag(name = "用户管理接口", description = "用户管理接口")
public class UserController extends AbstractController {
    private final UserService userService;
    private final AuthorizationService authorizationService;

    public UserController(UserService userService,
                          AuthorizationService authorizationService) {
        this.userService = userService;
        this.authorizationService = authorizationService;
    }

    @GetMapping("user")
    @PreAuthorize("hasPermission('','uaa:user:query')")
    @Operation(summary = "查询用户账户列表", tags = {"用户管理接口"},
            parameters = {
                    @Parameter(name = "username", description = "用户名"),
                    @Parameter(name = "email", description = "电子邮箱"),
                    @Parameter(name = "phone", description = "手机号"),
                    @Parameter(name = "ip", description = "注册时IP地址"),
                    @Parameter(name = "secretLevel", description = "密级"),
                    @Parameter(name = "locked", description = "是否锁定"),
                    @Parameter(name = "enabled", description = "是否启用"),
                    @Parameter(name = "pages", description = "页码"),
                    @Parameter(name = "rows", description = "每页数据量")
            })
    @OperationLog(module = SystemTypeEnum.ACCOUNT, desc = "查询用户账户列表")
    public APIResult<ListData<UserDetail>>
    queryUserList(@RequestParam(value = "username", required = false) String username,
                  @RequestParam(value = "email", required = false) String email,
                  @RequestParam(value = "phone", required = false) String phone,
                  @RequestParam(value = "ip", required = false) String ip,
                  @RequestParam(value = "secretLevel", required = false) SecretLevelEnum secretLevel,
                  @RequestParam(value = "locked", required = false) Boolean locked,
                  @RequestParam(value = "enabled", required = false) Boolean enabled,
                  @RequestParam(value = "pages", required = false, defaultValue = "1") Integer pages,
                  @RequestParam(value = "rows", required = false, defaultValue = "10") Integer rows) {
        return userService.queryUserList(username, email, phone, ip, secretLevel, locked, enabled, pages, rows);
    }

    @PostMapping("user")
    @PreAuthorize("hasPermission('','uaa:user:create')")
    @Operation(summary = "创建用户", tags = {"用户管理接口"})
    @OperationLog(module = SystemTypeEnum.ACCOUNT, desc = "创建用户", operation = OperationTypeEnum.CREATE)
    public APIResult<UserDetail> createUser(@RequestBody UserDetail userDetail) {
        if (userDetail.getUsername().trim().toLowerCase().length() >= MAX_USERNAME_LENGTH) {
            throw new BusinessException("用户名长度超过系统允许的最大值：" + MAX_USERNAME_LENGTH);
        }
        if (userDetail.getEmail().length() >= MAX_USERNAME_LENGTH) {
            throw new BusinessException("邮箱地址长度超过系统允许最大值：" + MAX_USERNAME_LENGTH);
        }
        if (ObjectUtils.isEmpty(userDetail.getUsername().trim())) {
            throw new BusinessException("用户名不能为空");
        }
        if (userDetail.getUsername().trim().getBytes().length < 4) {
            throw new BusinessException("用户名长度过短，请重起一个名字吧");
        }
        if (ObjectUtils.isEmpty(userDetail.getEmail().trim())) {
            throw new BusinessException("电子邮箱不能为空");
        }
        if (StringUtils.isEmail(userDetail.getUsername().trim())) {
            throw new BusinessException("不能使用电子邮件地址作为用户名");
        }
        if (StringUtils.isChinaPhone(userDetail.getUsername().trim())) {
            throw new BusinessException("不能使用手机号码作为用户名");
        }
        if (StringUtils.isDomain(userDetail.getUsername().trim())) {
            throw new BusinessException("用户名格式不正确，请换个用户名试试");
        }
        if (ObjectUtils.isEmpty(userDetail.getPassword())) {
            throw new BusinessException("密码不能为空");
        }
        if (!StringUtils.isEmail(userDetail.getEmail().trim())) {
            throw new BusinessException("您填写的电子邮箱地址格式不正确");
        }
        userDetail.setPassword(authorizationService.decryptAesByKeyId(
                userDetail.getPassword(), userDetail.getKeyUuid()
        ).getData());
        return userService.createUser(userDetail, request);
    }

    @PutMapping("user/{id}")
    @PreAuthorize("hasPermission('','uaa:user:update')")
    @Operation(summary = "修改用户资料", tags = {"用户管理接口"}, parameters = {
            @Parameter(name = "id", description = "用户ID")
    }, description = "此接口只能更新基础资料，修改密码、更改密级等有专门的接口")
    @OperationLog(module = SystemTypeEnum.ACCOUNT, desc = "修改用户资料", operation = OperationTypeEnum.UPDATE)
    public APIResult<UserDetail> updateUser(@PathVariable("id") long userId, @RequestBody UserDetail userDetail) {
        return userService.updateUser(userId, userDetail, request);
    }

    @PutMapping("user/{id}/secret-level/{secretLevel}")
    @PreAuthorize("hasPermission('','uaa:user:secretlevel')")
    @Operation(summary = "给用户定密", tags = {"用户管理接口"}, parameters = {
            @Parameter(name = "id", description = "用户ID"),
            @Parameter(name = "secretLevel", description = "密级")
    })
    @OperationLog(module = SystemTypeEnum.ACCOUNT, desc = "给用户定密", operation = OperationTypeEnum.UPDATE)
    public APIResult determineUserSecretLevel(@PathVariable("id") long userId,
                                              @PathVariable("secretLevel") SecretLevelEnum secretLevel) {
        return userService.determineUserSecretLevel(userId, secretLevel);
    }

    @PutMapping("user/{id}/enable/{enable}")
    @PreAuthorize("hasPermission('','uaa:user:enable')")
    @Operation(summary = "用户启用或禁用", tags = {"用户管理接口"}, parameters = {
            @Parameter(name = "id", description = "用户ID"),
            @Parameter(name = "enable", description = "启用或禁用")
    })
    @OperationLog(module = SystemTypeEnum.ACCOUNT, desc = "用户启用或禁用", operation = OperationTypeEnum.UPDATE)
    public APIResult enableUser(@PathVariable("id") long userId, @PathVariable("enable") boolean enable) {
        return userService.enableUser(userId, enable);
    }

    @PutMapping("user/{id}/reset-password")
    @PreAuthorize("hasPermission('','uaa:user:resetpassword')")
    @Operation(summary = "重置用户密码", tags = {"用户管理接口"}, parameters = {
            @Parameter(name = "id", description = "用户ID")
    })
    @OperationLog(module = SystemTypeEnum.ACCOUNT, desc = "重置用户密码", operation = OperationTypeEnum.UPDATE)
    public APIResult resetPassword(@PathVariable("id") long userId, @RequestBody ResetPasswordAo resetPassword) {
        resetPassword.setPassword(authorizationService.decryptAesByKeyId(
                resetPassword.getPassword(), resetPassword.getKeyUuid()
        ).getData());
        return userService.resetPassword(userId, resetPassword);
    }

    @DeleteMapping("user/{id}")
    @PreAuthorize("hasPermission('','uaa:user:delete')")
    @Operation(summary = "删除用户", tags = {"用户管理接口"}, parameters = {
            @Parameter(name = "id", description = "用户ID")
    }, description = "由于需要可审计，避免毁尸灭迹，用户暂时不支持删除操作")
    public APIResult deleteUser(@PathVariable("id") long userId) {
        // 由于需要可审计，避免毁尸灭迹，用户暂时不支持删除操作
        return APIResult.builder()
                .code(StateCodeEnum.Failure)
                .message("由于需要确保系统可审计可追踪，暂不支持用户删除操作。")
                .build();
    }

    @PutMapping("user/{id}/role")
    @PreAuthorize("hasPermission('','uaa:user:role')")
    @Operation(summary = "编辑用户角色", tags = {"用户管理接口"}, parameters = {
            @Parameter(name = "id", description = "用户ID")
    })
    @OperationLog(module = SystemTypeEnum.ACCOUNT, desc = "编辑用户角色", operation = OperationTypeEnum.UPDATE)
    public APIResult<List<RoleDetail>> authorizationRoleByUser(@PathVariable("id") long userId,
                                                               @RequestBody List<RoleDetail> roleDetailList) {
        return userService.authorizationRoleByUser(userId, roleDetailList, request);
    }
}
