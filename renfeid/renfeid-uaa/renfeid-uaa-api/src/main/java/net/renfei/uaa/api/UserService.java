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
import net.renfei.uaa.api.entity.ResetPasswordAo;
import net.renfei.uaa.api.entity.UserDetail;
import net.renfei.uaa.api.entity.SignInAo;
import net.renfei.uaa.api.entity.SignUpAo;

import javax.servlet.http.HttpServletRequest;

/**
 * 用户服务
 *
 * @author renfei
 */
public interface UserService {
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
     * 登陆
     *
     * @param signIn  登陆请求对象
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
     * 禁用用户，登陆状态将被踢出
     *
     * @param userId  用户ID
     * @param enable  是否启用
     * @param request 请求对象
     * @return
     */
    APIResult enableUser(long userId, boolean enable, HttpServletRequest request);

    /**
     * 锁定与解锁用户，等级比禁用低，不会踢出登陆状态
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
}
