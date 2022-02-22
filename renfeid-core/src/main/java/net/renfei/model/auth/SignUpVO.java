package net.renfei.model.auth;

/**
 * <p>Title: SignUpVO</p>
 * <p>Description: </p>
 *
 * @author RenFei(i @ renfei.net)
 */
public class SignUpVO {
    private String userName;
    private String password;
    private String email;
    private String keyUuid;
    private String reCAPTCHAToken;

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

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public String getKeyUuid() {
        return keyUuid;
    }

    public void setKeyUuid(String keyUuid) {
        this.keyUuid = keyUuid;
    }

    public String getReCAPTCHAToken() {
        return reCAPTCHAToken;
    }

    public void setReCAPTCHAToken(String reCAPTCHAToken) {
        this.reCAPTCHAToken = reCAPTCHAToken;
    }
}
