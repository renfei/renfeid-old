package net.renfei.model.account;

/**
 * <p>Title: UpdatePasswordVO</p>
 * <p>Description: </p>
 *
 * @author RenFei(i @ renfei.net)
 */
public class UpdatePasswordVO {
    private String oldPwd;
    private String newPwd;
    private String keyId;

    public String getOldPwd() {
        return oldPwd;
    }

    public void setOldPwd(String oldPwd) {
        this.oldPwd = oldPwd;
    }

    public String getNewPwd() {
        return newPwd;
    }

    public void setNewPwd(String newPwd) {
        this.newPwd = newPwd;
    }

    public String getKeyId() {
        return keyId;
    }

    public void setKeyId(String keyId) {
        this.keyId = keyId;
    }
}
