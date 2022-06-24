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
package net.renfei.uaa.api.entity;

import io.swagger.v3.oas.annotations.media.Schema;

import java.io.Serializable;

/**
 * 登录请求对象
 *
 * @author renfei
 */
@Schema(title = "登录请求对象")
public class SignInAo implements Serializable {
    private static final long serialVersionUID = -3316408227872898096L;
    @Schema(description = "用户名（支持邮箱和手机号）")
    private String userName;
    @Schema(description = "密码（加密后传输）")
    private String password;
    @Schema(description = "一次性密码（可选）")
    private String tOtp;
    @Schema(description = "加密密码使用的秘钥UUID编号")
    private String keyUuid;
    @Schema(description = "是否使用验证码登录")
    private Boolean useVerCode;

    public String getUserName() {
        return userName;
    }

    public void setUserName(String userName) {
        this.userName = userName;
    }

    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
    }

    public String gettOtp() {
        return tOtp;
    }

    public void settOtp(String tOtp) {
        this.tOtp = tOtp;
    }

    public String getKeyUuid() {
        return keyUuid;
    }

    public void setKeyUuid(String keyUuid) {
        this.keyUuid = keyUuid;
    }

    public Boolean getUseVerCode() {
        return useVerCode;
    }

    public void setUseVerCode(Boolean useVerCode) {
        this.useVerCode = useVerCode;
    }
}
