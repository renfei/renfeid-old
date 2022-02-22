package net.renfei.model.auth;

/**
 * @author renfei
 */
public class SignInSuccessVO {
    private String accessToken;
    private String ucScript;

    public String getAccessToken() {
        return accessToken;
    }

    public void setAccessToken(String accessToken) {
        this.accessToken = accessToken;
    }

    public String getUcScript() {
        return ucScript;
    }

    public void setUcScript(String ucScript) {
        this.ucScript = ucScript;
    }
}
