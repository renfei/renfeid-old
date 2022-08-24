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
package net.renfei.common.api.entity;

import io.swagger.v3.oas.annotations.media.Schema;

import java.io.Serializable;
import java.util.Date;

/**
 * 用户登录日志对象
 *
 * @author renfei
 */
@Schema(title = "用户登录日志对象")
public class UserSignInLog implements Serializable {
    private static final long serialVersionUID = -3316408227872898096L;
    private Date logTime;
    private String userUuid;
    private String userName;
    private String requIp;
    private String requAgent;
    private String address;

    public Date getLogTime() {
        return logTime;
    }

    public void setLogTime(Date logTime) {
        this.logTime = logTime;
    }

    public String getUserUuid() {
        return userUuid;
    }

    public void setUserUuid(String userUuid) {
        this.userUuid = userUuid;
    }

    public String getUserName() {
        return userName;
    }

    public void setUserName(String userName) {
        this.userName = userName;
    }

    public String getRequIp() {
        return requIp;
    }

    public void setRequIp(String requIp) {
        this.requIp = requIp;
    }

    public String getRequAgent() {
        return requAgent;
    }

    public void setRequAgent(String requAgent) {
        this.requAgent = requAgent;
    }

    public String getAddress() {
        return address;
    }

    public void setAddress(String address) {
        this.address = address;
    }
}
