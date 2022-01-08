package net.renfei.discuz.repositories.entity;

public class DiscuzCommonMemberFieldHomeDOWithBLOBs extends DiscuzCommonMemberFieldHomeDO {
    private String spacecss;

    private String blockposition;

    private String recentnote;

    private String spacenote;

    private String privacy;

    private String feedfriend;

    private String acceptemail;

    private String magicgift;

    private String stickblogs;

    public String getSpacecss() {
        return spacecss;
    }

    public void setSpacecss(String spacecss) {
        this.spacecss = spacecss == null ? null : spacecss.trim();
    }

    public String getBlockposition() {
        return blockposition;
    }

    public void setBlockposition(String blockposition) {
        this.blockposition = blockposition == null ? null : blockposition.trim();
    }

    public String getRecentnote() {
        return recentnote;
    }

    public void setRecentnote(String recentnote) {
        this.recentnote = recentnote == null ? null : recentnote.trim();
    }

    public String getSpacenote() {
        return spacenote;
    }

    public void setSpacenote(String spacenote) {
        this.spacenote = spacenote == null ? null : spacenote.trim();
    }

    public String getPrivacy() {
        return privacy;
    }

    public void setPrivacy(String privacy) {
        this.privacy = privacy == null ? null : privacy.trim();
    }

    public String getFeedfriend() {
        return feedfriend;
    }

    public void setFeedfriend(String feedfriend) {
        this.feedfriend = feedfriend == null ? null : feedfriend.trim();
    }

    public String getAcceptemail() {
        return acceptemail;
    }

    public void setAcceptemail(String acceptemail) {
        this.acceptemail = acceptemail == null ? null : acceptemail.trim();
    }

    public String getMagicgift() {
        return magicgift;
    }

    public void setMagicgift(String magicgift) {
        this.magicgift = magicgift == null ? null : magicgift.trim();
    }

    public String getStickblogs() {
        return stickblogs;
    }

    public void setStickblogs(String stickblogs) {
        this.stickblogs = stickblogs == null ? null : stickblogs.trim();
    }
}