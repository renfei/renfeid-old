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
import net.renfei.common.api.constant.enums.SecretLevelEnum;

import java.io.Serializable;
import java.util.Date;
import java.util.List;

/**
 * 用户信息对象
 *
 * @author renfei
 */
@Schema(title = "用户信息对象")
public class UserInfo implements Serializable {
    private static final long serialVersionUID = -3316408227872898096L;
    @Schema(description = "ID")
    private Long id;
    @Schema(description = "唯一编号")
    private String uuid;
    @Schema(description = "用户名")
    private String username;
    @Schema(description = "电子邮箱")
    private String email;
    @Schema(description = "电子邮箱是否通过验证")
    private Boolean emailVerified;
    @Schema(description = "手机号")
    private String phone;
    @Schema(description = "手机号是否通过验证")
    private Boolean phoneVerified;
    @Schema(description = "账户注册时间")
    private Date registrationDate;
    @Schema(description = "账户注册IP地址")
    private String registrationIp;
    @Schema(description = "保密等级")
    private SecretLevelEnum secretLevel;
    @Schema(description = "姓")
    private String lastName;
    @Schema(description = "名")
    private String firstName;
    @Schema(description = "两步认证启用状态")
    private Boolean u2fEnable;

    @Schema(description = "权限标识列表")
    private List<String> authorityList;

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getUuid() {
        return uuid;
    }

    public void setUuid(String uuid) {
        this.uuid = uuid;
    }

    public String getUsername() {
        return username;
    }

    public void setUsername(String username) {
        this.username = username;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public Boolean getEmailVerified() {
        return emailVerified;
    }

    public void setEmailVerified(Boolean emailVerified) {
        this.emailVerified = emailVerified;
    }

    public String getPhone() {
        return phone;
    }

    public void setPhone(String phone) {
        this.phone = phone;
    }

    public Boolean getPhoneVerified() {
        return phoneVerified;
    }

    public void setPhoneVerified(Boolean phoneVerified) {
        this.phoneVerified = phoneVerified;
    }

    public Date getRegistrationDate() {
        return registrationDate;
    }

    public void setRegistrationDate(Date registrationDate) {
        this.registrationDate = registrationDate;
    }

    public String getRegistrationIp() {
        return registrationIp;
    }

    public void setRegistrationIp(String registrationIp) {
        this.registrationIp = registrationIp;
    }

    public SecretLevelEnum getSecretLevel() {
        return secretLevel;
    }

    public void setSecretLevel(SecretLevelEnum secretLevel) {
        this.secretLevel = secretLevel;
    }

    public String getLastName() {
        return lastName;
    }

    public void setLastName(String lastName) {
        this.lastName = lastName;
    }

    public String getFirstName() {
        return firstName;
    }

    public void setFirstName(String firstName) {
        this.firstName = firstName;
    }

    public Boolean getU2fEnable() {
        return u2fEnable;
    }

    public void setU2fEnable(Boolean u2fEnable) {
        this.u2fEnable = u2fEnable;
    }

    public List<String> getAuthorityList() {
        return authorityList;
    }

    public void setAuthorityList(List<String> authorityList) {
        this.authorityList = authorityList;
    }
}
