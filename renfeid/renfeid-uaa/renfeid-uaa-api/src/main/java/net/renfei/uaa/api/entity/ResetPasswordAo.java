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

/**
 * 重置密码请求对象
 *
 * @author renfei
 */
public class ResetPasswordAo {
    private String password;
    private String tOtp;
    private String keyUuid;
    private String verCode;

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

    public String getVerCode() {
        return verCode;
    }

    public void setVerCode(String verCode) {
        this.verCode = verCode;
    }
}
