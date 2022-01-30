package net.renfei.domain.user;

import net.renfei.model.SecretLevelEnum;
import net.renfei.repositories.model.SysAccount;

import java.io.Serializable;
import java.util.Date;

/**
 * 用户域实体类
 *
 * @author renfei
 */
public class User implements Serializable {
    private static final long serialVersionUID = -314420603322403668L;
    private Long id;
    private String userName;
    private String uuid;
    private String email;
    private String phone;
    private String lastName;
    private String firstName;
    private String webSite;
    private String ucScript;
    private SecretLevelEnum secretLevelEnum;
    private String totp;
    private Date registrationDate;

    public User() {
    }

    public User(Long id) {
    }

    public User(SysAccount account) {
        this.id = account.getId();
        this.userName = account.getUserName();
        this.uuid = account.getUuid();
        this.email = account.getEmail();
        this.phone = account.getPhone();
        this.lastName = account.getLastName();
        this.firstName = account.getFirstName();
        this.webSite = "";
        this.ucScript = "";
        this.secretLevelEnum = SecretLevelEnum.valueOf(account.getSecretLevel());
        this.totp = account.getTotp();
        this.registrationDate = account.getRegistrationDate();
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getUserName() {
        return userName;
    }

    public void setUserName(String userName) {
        this.userName = userName;
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

    public String getPhone() {
        return phone;
    }

    public void setPhone(String phone) {
        this.phone = phone;
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

    public String getWebSite() {
        return webSite;
    }

    public void setWebSite(String webSite) {
        this.webSite = webSite;
    }

    public String getUcScript() {
        return ucScript;
    }

    public void setUcScript(String ucScript) {
        this.ucScript = ucScript;
    }

    public SecretLevelEnum getSecretLevelEnum() {
        return secretLevelEnum;
    }

    public void setSecretLevelEnum(SecretLevelEnum secretLevelEnum) {
        this.secretLevelEnum = secretLevelEnum;
    }

    public String getTotp() {
        return totp;
    }

    public void setTotp(String totp) {
        this.totp = totp;
    }

    public Date getRegistrationDate() {
        return registrationDate;
    }

    public void setRegistrationDate(Date registrationDate) {
        this.registrationDate = registrationDate;
    }

    @Override
    public String toString() {
        return "User{" +
                "id=" + id +
                ", userName='" + userName + '\'' +
                ", uuid='" + uuid + '\'' +
                ", email='" + email + '\'' +
                ", phone='" + phone + '\'' +
                ", lastName='" + lastName + '\'' +
                ", firstName='" + firstName + '\'' +
                ", webSite='" + webSite + '\'' +
                ", ucScript='" + ucScript + '\'' +
                ", secretLevelEnum=" + secretLevelEnum +
                ", totp='" + totp + '\'' +
                ", registrationDate=" + registrationDate +
                '}';
    }
}
