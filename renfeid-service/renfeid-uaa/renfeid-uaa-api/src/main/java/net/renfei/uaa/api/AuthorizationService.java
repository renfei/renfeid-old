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
import net.renfei.common.api.entity.ListData;
import net.renfei.common.api.entity.UserInfo;
import net.renfei.common.api.entity.UserSignInLog;
import net.renfei.uaa.api.entity.UserDetail;
import net.renfei.uaa.api.entity.*;

import javax.servlet.http.HttpServletRequest;

/**
 * 认证服务
 *
 * @author renfei
 */
public interface AuthorizationService {
    /**
     * 向服务器申请服务器公钥
     *
     * @return
     */
    APIResult<SecretKey> requestServerSecretKey();

    /**
     * 上报客户端公钥，并下发AES秘钥
     *
     * @param secretKey 客户端加密后的秘钥
     * @return
     */
    APIResult<SecretKey> settingClientSecretKey(SecretKey secretKey);

    /**
     * 根据秘钥ID解密AES密文
     *
     * @param string 密文
     * @param keyId  秘钥ID
     * @return 明文
     */
    APIResult<String> decryptAesByKeyId(String string, String keyId);

    /**
     * 登录
     *
     * @param signIn 登录请求对象
     * @return 登录响应
     */
    APIResult<SignInVo> signIn(SignInAo signIn, HttpServletRequest request);

    /**
     * 注册
     *
     * @param signUp  注册请求对象
     * @param request 请求对象
     * @return
     */
    APIResult signUp(SignUpAo signUp, HttpServletRequest request);

    /**
     * 登出
     *
     * @param userDetail 用户详情
     * @param request    请求对象
     * @return
     */
    APIResult signOut(UserDetail userDetail, HttpServletRequest request);

    /**
     * 账户激活
     *
     * @param signUpActivation
     */
    void activation(SignUpActivationAo signUpActivation);

    /**
     * 获取当前登录用户的信息
     *
     * @return
     */
    UserInfo requestCurrentUserInfo();

    /**
     * 获取当前登录用户的登录日志记录
     *
     * @param pages
     * @param rows
     * @return
     */
    ListData<UserSignInLog> queryCurrentUserSignInLog(int pages, int rows);

    /**
     * 生成两步认证秘钥
     *
     * @return
     */
    TotpVo generateU2FSecretKey();

    /**
     * 开启两步认证 TOTP
     *
     * @param totpAo
     * @return
     */
    APIResult openU2f(TotpAo totpAo);

    /**
     * 关闭两步认证 TOTP
     *
     * @param totpAo
     * @return
     */
    APIResult closeU2f(TotpAo totpAo);

    /**
     * 修改用户自己的密码
     *
     * @param updatePassword 修改请求对象
     * @return
     */
    APIResult updatePassword(UpdatePasswordAo updatePassword);
}
