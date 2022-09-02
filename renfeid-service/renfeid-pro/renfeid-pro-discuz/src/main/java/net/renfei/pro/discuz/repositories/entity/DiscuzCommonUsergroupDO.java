package net.renfei.pro.discuz.repositories.entity;

public class DiscuzCommonUsergroupDO {
    private Short groupid;

    private Byte radminid;

    private String type;

    private String system;

    private String grouptitle;

    private Integer creditshigher;

    private Integer creditslower;

    private Byte stars;

    private String color;

    private String icon;

    private Boolean allowvisit;

    private Boolean allowsendpm;

    private Boolean allowinvite;

    private Boolean allowmailinvite;

    private Byte maxinvitenum;

    private Short inviteprice;

    private Short maxinviteday;

    public Short getGroupid() {
        return groupid;
    }

    public void setGroupid(Short groupid) {
        this.groupid = groupid;
    }

    public Byte getRadminid() {
        return radminid;
    }

    public void setRadminid(Byte radminid) {
        this.radminid = radminid;
    }

    public String getType() {
        return type;
    }

    public void setType(String type) {
        this.type = type == null ? null : type.trim();
    }

    public String getSystem() {
        return system;
    }

    public void setSystem(String system) {
        this.system = system == null ? null : system.trim();
    }

    public String getGrouptitle() {
        return grouptitle;
    }

    public void setGrouptitle(String grouptitle) {
        this.grouptitle = grouptitle == null ? null : grouptitle.trim();
    }

    public Integer getCreditshigher() {
        return creditshigher;
    }

    public void setCreditshigher(Integer creditshigher) {
        this.creditshigher = creditshigher;
    }

    public Integer getCreditslower() {
        return creditslower;
    }

    public void setCreditslower(Integer creditslower) {
        this.creditslower = creditslower;
    }

    public Byte getStars() {
        return stars;
    }

    public void setStars(Byte stars) {
        this.stars = stars;
    }

    public String getColor() {
        return color;
    }

    public void setColor(String color) {
        this.color = color == null ? null : color.trim();
    }

    public String getIcon() {
        return icon;
    }

    public void setIcon(String icon) {
        this.icon = icon == null ? null : icon.trim();
    }

    public Boolean getAllowvisit() {
        return allowvisit;
    }

    public void setAllowvisit(Boolean allowvisit) {
        this.allowvisit = allowvisit;
    }

    public Boolean getAllowsendpm() {
        return allowsendpm;
    }

    public void setAllowsendpm(Boolean allowsendpm) {
        this.allowsendpm = allowsendpm;
    }

    public Boolean getAllowinvite() {
        return allowinvite;
    }

    public void setAllowinvite(Boolean allowinvite) {
        this.allowinvite = allowinvite;
    }

    public Boolean getAllowmailinvite() {
        return allowmailinvite;
    }

    public void setAllowmailinvite(Boolean allowmailinvite) {
        this.allowmailinvite = allowmailinvite;
    }

    public Byte getMaxinvitenum() {
        return maxinvitenum;
    }

    public void setMaxinvitenum(Byte maxinvitenum) {
        this.maxinvitenum = maxinvitenum;
    }

    public Short getInviteprice() {
        return inviteprice;
    }

    public void setInviteprice(Short inviteprice) {
        this.inviteprice = inviteprice;
    }

    public Short getMaxinviteday() {
        return maxinviteday;
    }

    public void setMaxinviteday(Short maxinviteday) {
        this.maxinviteday = maxinviteday;
    }
}