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
package net.renfei.common.core.service;

/**
 * 手机短信服务
 *
 * @author renfei
 */
public interface SMSService {
    /**
     * 发送短信
     *
     * @param phoneNumber 手机号
     * @param content     内容
     */
    void send(String phoneNumber, String content);

    /**
     * 发送短信
     *
     * @param templateCode 模板号
     * @param phoneNumber  手机号
     * @param content      内容
     */
    void send(String templateCode, String phoneNumber, String content);
}
