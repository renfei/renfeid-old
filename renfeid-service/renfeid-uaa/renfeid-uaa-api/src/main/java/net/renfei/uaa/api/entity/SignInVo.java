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
 * 登录响应对象
 *
 * @author renfei
 */
@Schema(title = "登录响应对象")
public class SignInVo implements Serializable {
    private static final long serialVersionUID = -3316408227872898096L;
    @Schema(description = "Token令牌")
    private String accessToken;
    @Schema(description = "UCenter的登录脚本")
    private String ucScript;

    public String getAccessToken() {
        return accessToken;
    }

    public void setAccessToken(String accessToken) {
        this.accessToken = accessToken;
    }

    public String getUcScript() {
        return ucScript;
    }

    public void setUcScript(String ucScript) {
        this.ucScript = ucScript;
    }
}
