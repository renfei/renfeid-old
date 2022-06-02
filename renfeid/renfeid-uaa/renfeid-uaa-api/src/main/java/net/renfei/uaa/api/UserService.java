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
import net.renfei.uaa.api.entity.UserDetail;

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
     * @return
     */
    APIResult<UserDetail> getUserDetailByToken(String token);

    /**
     * 根据 Token 获取用户详情对象
     *
     * @param token Token
     * @param ip    请求方IP
     * @return
     */
    APIResult<UserDetail> getUserDetailByToken(String token, String ip);
}
