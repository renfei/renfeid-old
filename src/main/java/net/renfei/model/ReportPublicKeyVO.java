package net.renfei.model;

import java.io.Serializable;

/**
 * <p>Title: ReportPublicKeyVO</p>
 * <p>Description: </p>
 *
 * @author RenFei(i @ renfei.net)
 */
public class ReportPublicKeyVO implements Serializable {
    private static final long serialVersionUID = -314420603322403668L;
    private String secretKeyId;
    private String publicKey;

    public String getSecretKeyId() {
        return secretKeyId;
    }

    public void setSecretKeyId(String secretKeyId) {
        this.secretKeyId = secretKeyId;
    }

    public String getPublicKey() {
        return publicKey;
    }

    public void setPublicKey(String publicKey) {
        this.publicKey = publicKey;
    }
}
