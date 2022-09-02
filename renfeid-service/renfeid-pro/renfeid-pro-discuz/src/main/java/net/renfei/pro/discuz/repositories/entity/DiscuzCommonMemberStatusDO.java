package net.renfei.pro.discuz.repositories.entity;

public class DiscuzCommonMemberStatusDO {
    private Integer uid;

    private String regip;

    private String lastip;

    private Short port;

    private Integer lastvisit;

    private Integer lastactivity;

    private Integer lastpost;

    private Integer lastsendmail;

    private Integer invisible;

    private Short buyercredit;

    private Short sellercredit;

    private Integer favtimes;

    private Integer sharetimes;

    private Byte profileprogress;

    public Integer getUid() {
        return uid;
    }

    public void setUid(Integer uid) {
        this.uid = uid;
    }

    public String getRegip() {
        return regip;
    }

    public void setRegip(String regip) {
        this.regip = regip == null ? null : regip.trim();
    }

    public String getLastip() {
        return lastip;
    }

    public void setLastip(String lastip) {
        this.lastip = lastip == null ? null : lastip.trim();
    }

    public Short getPort() {
        return port;
    }

    public void setPort(Short port) {
        this.port = port;
    }

    public Integer getLastvisit() {
        return lastvisit;
    }

    public void setLastvisit(Integer lastvisit) {
        this.lastvisit = lastvisit;
    }

    public Integer getLastactivity() {
        return lastactivity;
    }

    public void setLastactivity(Integer lastactivity) {
        this.lastactivity = lastactivity;
    }

    public Integer getLastpost() {
        return lastpost;
    }

    public void setLastpost(Integer lastpost) {
        this.lastpost = lastpost;
    }

    public Integer getLastsendmail() {
        return lastsendmail;
    }

    public void setLastsendmail(Integer lastsendmail) {
        this.lastsendmail = lastsendmail;
    }

    public Integer getInvisible() {
        return invisible;
    }

    public void setInvisible(Integer invisible) {
        this.invisible = invisible;
    }

    public Short getBuyercredit() {
        return buyercredit;
    }

    public void setBuyercredit(Short buyercredit) {
        this.buyercredit = buyercredit;
    }

    public Short getSellercredit() {
        return sellercredit;
    }

    public void setSellercredit(Short sellercredit) {
        this.sellercredit = sellercredit;
    }

    public Integer getFavtimes() {
        return favtimes;
    }

    public void setFavtimes(Integer favtimes) {
        this.favtimes = favtimes;
    }

    public Integer getSharetimes() {
        return sharetimes;
    }

    public void setSharetimes(Integer sharetimes) {
        this.sharetimes = sharetimes;
    }

    public Byte getProfileprogress() {
        return profileprogress;
    }

    public void setProfileprogress(Byte profileprogress) {
        this.profileprogress = profileprogress;
    }
}