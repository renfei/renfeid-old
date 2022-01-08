package net.renfei.model.auth;

/**
 * @author renfei
 */
public class ReCaptchaVerify {
    private String secret;
    private String response;
    private String remoteip;

    public String getSecret() {
        return secret;
    }

    public void setSecret(String secret) {
        this.secret = secret;
    }

    public String getResponse() {
        return response;
    }

    public void setResponse(String response) {
        this.response = response;
    }

    public String getRemoteip() {
        return remoteip;
    }

    public void setRemoteip(String remoteip) {
        this.remoteip = remoteip;
    }
}
