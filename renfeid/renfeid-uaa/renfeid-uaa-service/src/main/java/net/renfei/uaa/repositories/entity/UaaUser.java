package net.renfei.uaa.repositories.entity;

import java.io.Serializable;
import java.util.Date;

public class UaaUser implements Serializable {
    private Long id;

    private String uuid;

    private String username;

    private String email;

    private Boolean emailVerified;

    private String phone;

    private Boolean phoneVerified;

    private Date registrationDate;

    private String password;

    private String totp;

    private String registrationIp;

    private Integer trialErrorTimes;

    private Date lockTime;

    private Integer secretLevel;

    private Boolean builtInUser;

    private Date passwordExpirationTime;

    private Boolean locked;

    private Boolean enabled;

    private String lastName;

    private String firstName;

    private static final long serialVersionUID = 1L;

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

    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
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

    public Integer getSecretLevel() {
        return secretLevel;
    }

    public void setSecretLevel(Integer secretLevel) {
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

    @Override
    public String toString() {
        StringBuilder sb = new StringBuilder();
        sb.append(getClass().getSimpleName());
        sb.append(" [");
        sb.append("Hash = ").append(hashCode());
        sb.append(", id=").append(id);
        sb.append(", uuid=").append(uuid);
        sb.append(", username=").append(username);
        sb.append(", email=").append(email);
        sb.append(", emailVerified=").append(emailVerified);
        sb.append(", phone=").append(phone);
        sb.append(", phoneVerified=").append(phoneVerified);
        sb.append(", registrationDate=").append(registrationDate);
        sb.append(", password=").append(password);
        sb.append(", totp=").append(totp);
        sb.append(", registrationIp=").append(registrationIp);
        sb.append(", trialErrorTimes=").append(trialErrorTimes);
        sb.append(", lockTime=").append(lockTime);
        sb.append(", secretLevel=").append(secretLevel);
        sb.append(", builtInUser=").append(builtInUser);
        sb.append(", passwordExpirationTime=").append(passwordExpirationTime);
        sb.append(", locked=").append(locked);
        sb.append(", enabled=").append(enabled);
        sb.append(", lastName=").append(lastName);
        sb.append(", firstName=").append(firstName);
        sb.append(", serialVersionUID=").append(serialVersionUID);
        sb.append("]");
        return sb.toString();
    }
}