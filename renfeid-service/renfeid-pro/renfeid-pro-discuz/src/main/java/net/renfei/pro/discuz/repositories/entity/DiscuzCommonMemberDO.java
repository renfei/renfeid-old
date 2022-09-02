package net.renfei.pro.discuz.repositories.entity;

public class DiscuzCommonMemberDO {
    private Integer uid;

    private String email;

    private String username;

    private String password;

    private Boolean status;

    private Integer emailstatus;

    private Boolean avatarstatus;

    private Boolean videophotostatus;

    private Boolean adminid;

    private Short groupid;

    private Integer groupexpiry;

    private String extgroupids;

    private Integer regdate;

    private Integer credits;

    private Boolean notifysound;

    private String timeoffset;

    private Short newpm;

    private Short newprompt;

    private Boolean accessmasks;

    private Boolean allowadmincp;

    private Boolean onlyacceptfriendpm;

    private Boolean conisbind;

    private Boolean freeze;

    public Integer getUid() {
        return uid;
    }

    public void setUid(Integer uid) {
        this.uid = uid;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email == null ? null : email.trim();
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

    public Boolean getStatus() {
        return status;
    }

    public void setStatus(Boolean status) {
        this.status = status;
    }

    public Integer getEmailstatus() {
        return emailstatus;
    }

    public void setEmailstatus(Integer emailstatus) {
        this.emailstatus = emailstatus;
    }

    public Boolean getAvatarstatus() {
        return avatarstatus;
    }

    public void setAvatarstatus(Boolean avatarstatus) {
        this.avatarstatus = avatarstatus;
    }

    public Boolean getVideophotostatus() {
        return videophotostatus;
    }

    public void setVideophotostatus(Boolean videophotostatus) {
        this.videophotostatus = videophotostatus;
    }

    public Boolean getAdminid() {
        return adminid;
    }

    public void setAdminid(Boolean adminid) {
        this.adminid = adminid;
    }

    public Short getGroupid() {
        return groupid;
    }

    public void setGroupid(Short groupid) {
        this.groupid = groupid;
    }

    public Integer getGroupexpiry() {
        return groupexpiry;
    }

    public void setGroupexpiry(Integer groupexpiry) {
        this.groupexpiry = groupexpiry;
    }

    public String getExtgroupids() {
        return extgroupids;
    }

    public void setExtgroupids(String extgroupids) {
        this.extgroupids = extgroupids == null ? null : extgroupids.trim();
    }

    public Integer getRegdate() {
        return regdate;
    }

    public void setRegdate(Integer regdate) {
        this.regdate = regdate;
    }

    public Integer getCredits() {
        return credits;
    }

    public void setCredits(Integer credits) {
        this.credits = credits;
    }

    public Boolean getNotifysound() {
        return notifysound;
    }

    public void setNotifysound(Boolean notifysound) {
        this.notifysound = notifysound;
    }

    public String getTimeoffset() {
        return timeoffset;
    }

    public void setTimeoffset(String timeoffset) {
        this.timeoffset = timeoffset == null ? null : timeoffset.trim();
    }

    public Short getNewpm() {
        return newpm;
    }

    public void setNewpm(Short newpm) {
        this.newpm = newpm;
    }

    public Short getNewprompt() {
        return newprompt;
    }

    public void setNewprompt(Short newprompt) {
        this.newprompt = newprompt;
    }

    public Boolean getAccessmasks() {
        return accessmasks;
    }

    public void setAccessmasks(Boolean accessmasks) {
        this.accessmasks = accessmasks;
    }

    public Boolean getAllowadmincp() {
        return allowadmincp;
    }

    public void setAllowadmincp(Boolean allowadmincp) {
        this.allowadmincp = allowadmincp;
    }

    public Boolean getOnlyacceptfriendpm() {
        return onlyacceptfriendpm;
    }

    public void setOnlyacceptfriendpm(Boolean onlyacceptfriendpm) {
        this.onlyacceptfriendpm = onlyacceptfriendpm;
    }

    public Boolean getConisbind() {
        return conisbind;
    }

    public void setConisbind(Boolean conisbind) {
        this.conisbind = conisbind;
    }

    public Boolean getFreeze() {
        return freeze;
    }

    public void setFreeze(Boolean freeze) {
        this.freeze = freeze;
    }
}