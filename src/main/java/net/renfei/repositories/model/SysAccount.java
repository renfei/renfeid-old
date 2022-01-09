package net.renfei.repositories.model;

import java.io.Serializable;
import java.util.Date;

public class SysAccount implements Serializable {
    private Long id;

    private String uuid;

    private String userName;

    private String email;

    private String phone;

    private Date registrationDate;

    private String password;

    private String totp;

    private String registrationIp;

    private Integer trialErrorTimes;

    private Date lockTime;

    private Integer stateCode;

    private String lastName;

    private String firstName;

    private Integer secretLevel;

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

    public String getUserName() {
        return userName;
    }

    public void setUserName(String userName) {
        this.userName = userName;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public String getPhone() {
        return phone;
    }

    public void setPhone(String phone) {
        this.phone = phone;
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

    public Integer getStateCode() {
        return stateCode;
    }

    public void setStateCode(Integer stateCode) {
        this.stateCode = stateCode;
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

    public Integer getSecretLevel() {
        return secretLevel;
    }

    public void setSecretLevel(Integer secretLevel) {
        this.secretLevel = secretLevel;
    }

    @Override
    public String toString() {
        StringBuilder sb = new StringBuilder();
        sb.append(getClass().getSimpleName());
        sb.append(" [");
        sb.append("Hash = ").append(hashCode());
        sb.append(", id=").append(id);
        sb.append(", uuid=").append(uuid);
        sb.append(", userName=").append(userName);
        sb.append(", email=").append(email);
        sb.append(", phone=").append(phone);
        sb.append(", registrationDate=").append(registrationDate);
        sb.append(", password=").append(password);
        sb.append(", totp=").append(totp);
        sb.append(", registrationIp=").append(registrationIp);
        sb.append(", trialErrorTimes=").append(trialErrorTimes);
        sb.append(", lockTime=").append(lockTime);
        sb.append(", stateCode=").append(stateCode);
        sb.append(", lastName=").append(lastName);
        sb.append(", firstName=").append(firstName);
        sb.append(", secretLevel=").append(secretLevel);
        sb.append(", serialVersionUID=").append(serialVersionUID);
        sb.append("]");
        return sb.toString();
    }
}