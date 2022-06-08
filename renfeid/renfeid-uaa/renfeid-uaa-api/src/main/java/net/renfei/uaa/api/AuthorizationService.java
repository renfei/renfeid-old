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
import net.renfei.uaa.api.entity.SecretKey;
import net.renfei.uaa.api.entity.SignInAo;
import net.renfei.uaa.api.entity.SignInVo;

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
}
