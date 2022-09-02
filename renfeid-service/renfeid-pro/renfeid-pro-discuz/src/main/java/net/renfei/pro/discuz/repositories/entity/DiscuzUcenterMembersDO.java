package net.renfei.pro.discuz.repositories.entity;

public class DiscuzUcenterMembersDO {
    private Integer uid;

    private String username;

    private String password;

    private String email;

    private String myid;

    private String myidkey;

    private String regip;

    private Integer regdate;

    private Integer lastloginip;

    private Integer lastlogintime;

    private String salt;

    private String secques;

    public Integer getUid() {
        return uid;
    }

    public void setUid(Integer uid) {
        this.uid = uid;
    }

    public String getUsername() {
        return username;
    }

    public void setUsername(String username) {
        this.username = username == null ? null : username.trim();
    }

    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password == null ? null : password.trim();
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email == null ? null : email.trim();
    }

    public String getMyid() {
        return myid;
    }

    public void setMyid(String myid) {
        this.myid = myid == null ? null : myid.trim();
    }

    public String getMyidkey() {
        return myidkey;
    }

    public void setMyidkey(String myidkey) {
        this.myidkey = myidkey == null ? null : myidkey.trim();
    }

    public String getRegip() {
        return regip;
    }

    public void setRegip(String regip) {
        this.regip = regip == null ? null : regip.trim();
    }

    public Integer getRegdate() {
        return regdate;
    }

    public void setRegdate(Integer regdate) {
        this.regdate = regdate;
    }

    public Integer getLastloginip() {
        return lastloginip;
    }

    public void setLastloginip(Integer lastloginip) {
        this.lastloginip = lastloginip;
    }

    public Integer getLastlogintime() {
        return lastlogintime;
    }

    public void setLastlogintime(Integer lastlogintime) {
        this.lastlogintime = lastlogintime;
    }

    public String getSalt() {
        return salt;
    }

    public void setSalt(String salt) {
        this.salt = salt == null ? null : salt.trim();
    }

    public String getSecques() {
        return secques;
    }

    public void setSecques(String secques) {
        this.secques = secques == null ? null : secques.trim();
    }
}