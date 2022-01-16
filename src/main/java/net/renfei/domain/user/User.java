package net.renfei.domain.user;

import net.renfei.model.SecretLevelEnum;

import java.io.Serializable;

/**
 * 用户域实体类
 *
 * @author renfei
 */
public final class User implements Serializable {
    private Long id;
    private String userName;
    private String uuid;
    private String email;
    private String webSite;
    private String ucScript;
    private SecretLevelEnum secretLevelEnum;

    public User() {
    }

    public User(Long id) {
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
}
