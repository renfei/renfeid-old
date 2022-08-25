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
package net.renfei.uaa.api;

import net.renfei.common.api.constant.APIResult;
import net.renfei.common.api.constant.enums.SecretLevelEnum;
import net.renfei.common.api.entity.ListData;
import net.renfei.uaa.api.entity.*;

import javax.servlet.http.HttpServletRequest;
import java.util.List;

/**
 * 用户服务
 *
 * @author renfei
 */
public interface UserService {
    int MAX_FIRST_NAME = 10;
    int MAX_LAST_NAME = 15;

    /**
     * 根据 Token 获取用户详情对象
     *
     * @param token Token
     * @return 用户详情对象
     */
    APIResult<UserDetail> getUserDetailByToken(String token);

    /**
     * 根据 Token 获取用户详情对象
     *
     * @param token Token
     * @param ip    请求方IP
     * @return 用户详情对象
     */
    APIResult<UserDetail> getUserDetailByToken(String token, String ip);

    /**
     * 根据 email 获取用户详情对象
     * 无论是否被禁用或锁定都会被查询出来
     *
     * @param email email
     * @return 用户详情对象
     */
    APIResult<UserDetail> getUserDetailByEmail(String email);

    /**
     * 根据 phone 获取用户详情对象
     * 无论是否被禁用或锁定都会被查询出来
     *
     * @param phone phone
     * @return 用户详情对象
     */
    APIResult<UserDetail> getUserDetailByPhone(String phone);

    /**
     * 登录
     *
     * @param signIn  登录请求对象
     * @param request 请求对象
     * @return 用户详情对象
     */
    APIResult<UserDetail> signIn(SignInAo signIn, HttpServletRequest request);

    /**
     * 注册
     *
     * @param signUp  注册请求对象
     * @param request 请求对象
     * @return
     */
    APIResult signUp(SignUpAo signUp, HttpServletRequest request);

    /**
     * 激活账户（邮箱或手机）
     *
     * @param emailOrPhone 账户邮箱或手机号
     */
    void activation(String emailOrPhone);

    /**
     * 查询用户列表
     *
     * @param username    用户名
     * @param email       邮箱
     * @param phone       手机号
     * @param ip          注册IP地址
     * @param secretLevel 保密等级
     * @param locked      是否被锁定
     * @param enabled     是否启用
     * @param pages       页码
     * @param rows        每页容量
     * @return 用户列表
     */
    APIResult<ListData<UserDetail>> queryUserList(String username, String email, String phone, String ip,
                                                  SecretLevelEnum secretLevel, Boolean locked, Boolean enabled,
                                                  int pages, int rows);

    /**
     * 添加用户（只添加用户，用户的定密、启用由安全保密员操作）
     *
     * @param userDetail 用户详情
     * @param request    请求对象
     * @return
     */
    APIResult<UserDetail> createUser(UserDetail userDetail, HttpServletRequest request);

    /**
     * 更新用户资料
     *
     * @param userId     用户ID
     * @param userDetail 用户资料详情
     * @param request    请求对象
     * @return
     */
    APIResult<UserDetail> updateUser(long userId, UserDetail userDetail, HttpServletRequest request);

    /**
     * 给用户定密
     *
     * @param userId      用户ID
     * @param secretLevel 密级
     * @return
     */
    APIResult determineUserSecretLevel(long userId, SecretLevelEnum secretLevel, HttpServletRequest request);

    /**
     * 禁用用户，登录状态将被踢出
     *
     * @param userId  用户ID
     * @param enable  是否启用
     * @param request 请求对象
     * @return
     */
    APIResult enableUser(long userId, boolean enable, HttpServletRequest request);

    /**
     * 锁定与解锁用户，等级比禁用低，不会踢出登录状态
     *
     * @param userId 用户ID
     * @param lock   是否锁定
     * @return
     */
    APIResult lockUser(long userId, boolean lock);

    /**
     * 重置密码
     *
     * @param userId        用户ID
     * @param resetPassword 重置密码请求对象
     * @return
     */
    APIResult resetPassword(long userId, ResetPasswordAo resetPassword);

    /**
     * 给用户授予角色
     *
     * @param userId         用户ID
     * @param roleDetailList 角色列表
     * @param request        请求对象
     * @return
     */
    APIResult<List<RoleDetail>> authorizationRoleByUser(long userId, List<RoleDetail> roleDetailList, HttpServletRequest request);

    List<UserDetail> queryUserListByRoleName(String roleName);

    /**
     * 验证密码正确性
     *
     * @param userDetail 用户
     * @param password   明文密码
     * @return
     */
    boolean verifyPassword(UserDetail userDetail, String password);

    /**
     * 修改密码
     *
     * @param userDetail 用户
     * @param oldPwd     明文旧密码
     * @param newPwd     明文新密码
     * @return
     */
    void updatePassword(UserDetail userDetail, String oldPwd, String newPwd);

    /**
     * 验证TOTP正确性
     *
     * @param userDetail 用户
     * @param totp       totp
     * @return
     */
    boolean verifyTotp(UserDetail userDetail, String totp);

    /**
     * 设置用户 Totp 秘钥
     *
     * @param userDetail 用户
     * @param totp       秘钥
     * @return
     */
    void addTotp(UserDetail userDetail, String totp);

    /**
     * 移除用户 TOTP 秘钥
     *
     * @param userDetail 用户
     */
    void removeTotp(UserDetail userDetail);

    /**
     * 给邮箱发送验证码
     *
     * @param newEmail 新邮箱
     * @return
     */
    APIResult sendEmailVerCode(String newEmail);

    /**
     * 更新邮箱地址
     *
     * @param newEmail 新邮箱
     * @param verCode  验证码
     * @return
     */
    APIResult updateEmail(String newEmail, String verCode);

    /**
     * 给手机发送验证码
     *
     * @param newPhone 新手机
     * @return
     */
    APIResult sendPhoneVerCode(String newPhone);

    /**
     * 更新手机号
     *
     * @param newPhone 新手机号
     * @param verCode  验证码
     * @return
     */
    APIResult updatePhone(String newPhone, String verCode);

    /**
     * 更新姓名称呼
     *
     * @param firstName 名字
     * @param lastName  姓氏
     * @return
     */
    APIResult updateFirstName(String firstName, String lastName);
}
