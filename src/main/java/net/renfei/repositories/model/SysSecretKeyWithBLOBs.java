package net.renfei.repositories.model;

import java.io.Serializable;

public class SysSecretKeyWithBLOBs extends SysSecretKey implements Serializable {
    private String publicKey;

    private String privateKey;

    private static final long serialVersionUID = 1L;

    public String getPublicKey() {
        return publicKey;
    }

    public void setPublicKey(String publicKey) {
        this.publicKey = publicKey;
    }

    public String getPrivateKey() {
        return privateKey;
    }

    public void setPrivateKey(String privateKey) {
        this.privateKey = privateKey;
    }

    @Override
    public String toString() {
        StringBuilder sb = new StringBuilder();
        sb.append(getClass().getSimpleName());
        sb.append(" [");
        sb.append("Hash = ").append(hashCode());
        sb.append(", publicKey=").append(publicKey);
        sb.append(", privateKey=").append(privateKey);
        sb.append(", serialVersionUID=").append(serialVersionUID);
        sb.append("]");
        return sb.toString();
    }
}