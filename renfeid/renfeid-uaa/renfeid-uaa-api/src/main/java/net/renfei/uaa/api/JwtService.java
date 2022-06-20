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

import io.jsonwebtoken.Claims;
import net.renfei.common.api.constant.APIResult;

/**
 * JSON Web Token Service
 *
 * @author renfei
 */
public interface JwtService {
    /**
     * 签发 JWT
     *
     * @param userName 用户名
     * @param ip       请求IP地址
     * @return
     */
    APIResult<String> createJWT(String userName, String ip);

    /**
     * 解析JWT字符串
     *
     * @param jwt
     * @return
     * @throws Exception
     */
    APIResult<Claims> parseJWT(String jwt);

    /**
     * 获取用户名
     *
     * @param token
     * @return
     */
    APIResult<String> getUsername(String token);

    /**
     * TOKEN 校验
     *
     * @param token
     * @return
     */
    APIResult validate(String token);

    /**
     * TOKEN 与IP进行绑定校验
     *
     * @param token
     * @param ip
     * @return
     */
    APIResult validate(String token, String ip);
}
