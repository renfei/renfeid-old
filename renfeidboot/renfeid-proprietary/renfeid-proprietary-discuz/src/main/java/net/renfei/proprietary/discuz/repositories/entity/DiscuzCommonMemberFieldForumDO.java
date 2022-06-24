package net.renfei.proprietary.discuz.repositories.entity;

public class DiscuzCommonMemberFieldForumDO {
    private Integer uid;

    private Byte publishfeed;

    private Boolean customshow;

    private String customstatus;

    private String authstr;

    private String attentiongroup;

    public Integer getUid() {
        return uid;
    }

    public void setUid(Integer uid) {
        this.uid = uid;
    }

    public Byte getPublishfeed() {
        return publishfeed;
    }

    public void setPublishfeed(Byte publishfeed) {
        this.publishfeed = publishfeed;
    }

    public Boolean getCustomshow() {
        return customshow;
    }

    public void setCustomshow(Boolean customshow) {
        this.customshow = customshow;
    }

    public String getCustomstatus() {
        return customstatus;
    }

    public void setCustomstatus(String customstatus) {
        this.customstatus = customstatus == null ? null : customstatus.trim();
    }

    public String getAuthstr() {
        return authstr;
    }

    public void setAuthstr(String authstr) {
        this.authstr = authstr == null ? null : authstr.trim();
    }

    public String getAttentiongroup() {
        return attentiongroup;
    }

    public void setAttentiongroup(String attentiongroup) {
        this.attentiongroup = attentiongroup == null ? null : attentiongroup.trim();
    }
}