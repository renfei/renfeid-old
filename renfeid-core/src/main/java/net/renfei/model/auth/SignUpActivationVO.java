package net.renfei.model.auth;

/**
 * 账户激活请求对象
 */
public class SignUpActivationVO {
    private String emailOrPhone;
    private String code;
    private String reCAPTCHAToken;

    public String getEmailOrPhone() {
        return emailOrPhone;
    }

    public void setEmailOrPhone(String emailOrPhone) {
        this.emailOrPhone = emailOrPhone;
    }

    public String getCode() {
        return code;
    }

    public void setCode(String code) {
        this.code = code;
    }

    public String getReCAPTCHAToken() {
        return reCAPTCHAToken;
    }

    public void setReCAPTCHAToken(String reCAPTCHAToken) {
        this.reCAPTCHAToken = reCAPTCHAToken;
    }
}
