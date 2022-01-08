package net.renfei.model.auth;

/**
 * <p>Title: SignInVO</p>
 * <p>Description: </p>
 *
 * @author RenFei(i @ renfei.net)
 */
public class SignInVO {
    private String userName;
    private String password;
    private String tOtp;
    private String keyUuid;
    private Boolean useVerCode;
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

    public String getReCAPTCHAToken() {
        return reCAPTCHAToken;
    }

    public void setReCAPTCHAToken(String reCAPTCHAToken) {
        this.reCAPTCHAToken = reCAPTCHAToken;
    }
}
