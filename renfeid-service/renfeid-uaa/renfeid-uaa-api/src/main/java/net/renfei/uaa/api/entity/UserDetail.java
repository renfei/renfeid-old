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
import net.renfei.common.api.constant.enums.SecretLevelEnum;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.userdetails.UserDetails;

import java.io.Serializable;
import java.util.Collection;
import java.util.Date;
import java.util.List;

/**
 * 用户详情
 *
 * @author renfei
 */
@Schema(title = "用户详情")
public class UserDetail implements UserDetails, Serializable {
    private static final long serialVersionUID = -5194970536302876575L;
    @Schema(description = "用户ID")
    private Long id;
    @Schema(description = "UUID编码")
    private String uuid;
    @Schema(description = "用户名")
    private String username;
    @Schema(description = "电子邮箱")
    private String email;
    @Schema(description = "电子邮箱是否经过验证")
    private Boolean emailVerified;
    @Schema(description = "手机号")
    private String phone;
    @Schema(description = "手机号是否经过验证")
    private Boolean phoneVerified;
    @Schema(description = "注册时间")
    private Date registrationDate;
    @Schema(description = "密码（加密后传输）")
    private String password;
    @Schema(description = "一次性密码")
    private String totp;
    @Schema(description = "注册IP地址")
    private String registrationIp;
    @Schema(description = "密码错误次数")
    private Integer trialErrorTimes;
    @Schema(description = "锁定时间")
    private Date lockTime;
    @Schema(description = "密级")
    private SecretLevelEnum secretLevel;
    @Schema(description = "是否是内置用户")
    private Boolean builtInUser;
    @Schema(description = "密码过期时间")
    private Date passwordExpirationTime;
    @Schema(description = "是否被锁定")
    private Boolean locked;
    @Schema(description = "是否启用")
    private Boolean enabled;
    @Schema(description = "姓")
    private String lastName;
    @Schema(description = "名")
    private String firstName;
    @Schema(description = "密码加密时使用的秘钥UUID")
    private String keyUuid;
    @Schema(description = "拥有的角色列表")
    private List<RoleDetail> roleDetailList;

    @Override
    public Collection<? extends GrantedAuthority> getAuthorities() {
        return this.roleDetailList;
    }

    @Override
    public String getPassword() {
        return this.password;
    }

    public void setPassword(String password) {
        this.password = password;
    }

    @Override
    public String getUsername() {
        return this.username;
    }

    public void setUsername(String username) {
        this.username = username;
    }

    @Override
    public boolean isAccountNonExpired() {
        return true;
    }

    @Override
    public boolean isAccountNonLocked() {
        return !locked;
    }

    @Override
    public boolean isCredentialsNonExpired() {
        if (passwordExpirationTime == null) {
            return true;
        }
        return new Date().compareTo(passwordExpirationTime) < 0;
    }

    @Override
    public boolean isEnabled() {
        return enabled;
    }

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

    public String getTotp() {
        return totp;
    }

    public void setTotp(String totp) {
        this.totp = totp;
    }

    public String getRegistrationIp() {
        return registrationIp;
    }

    public void setRegistrationIp(String registrationIp) {
        this.registrationIp = registrationIp;
    }

    public Integer getTrialErrorTimes() {
        return trialErrorTimes;
    }

    public void setTrialErrorTimes(Integer trialErrorTimes) {
        this.trialErrorTimes = trialErrorTimes;
    }

    public Date getLockTime() {
        return lockTime;
    }

    public void setLockTime(Date lockTime) {
        this.lockTime = lockTime;
    }

    public SecretLevelEnum getSecretLevel() {
        return secretLevel;
    }

    public void setSecretLevel(SecretLevelEnum secretLevel) {
        this.secretLevel = secretLevel;
    }

    public Boolean getBuiltInUser() {
        return builtInUser;
    }

    public void setBuiltInUser(Boolean builtInUser) {
        this.builtInUser = builtInUser;
    }

    public Date getPasswordExpirationTime() {
        return passwordExpirationTime;
    }

    public void setPasswordExpirationTime(Date passwordExpirationTime) {
        this.passwordExpirationTime = passwordExpirationTime;
    }

    public Boolean getLocked() {
        return locked;
    }

    public void setLocked(Boolean locked) {
        this.locked = locked;
    }

    public Boolean getEnabled() {
        return enabled;
    }

    public void setEnabled(Boolean enabled) {
        this.enabled = enabled;
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

    public String getKeyUuid() {
        return keyUuid;
    }

    public void setKeyUuid(String keyUuid) {
        this.keyUuid = keyUuid;
    }

    public List<RoleDetail> getRoleDetailList() {
        return roleDetailList;
    }

    public void setRoleDetailList(List<RoleDetail> roleDetailList) {
        this.roleDetailList = roleDetailList;
    }
}
