package net.renfei.discuz.repositories.entity;

public class DiscuzCommonMemberFieldHomeDO {
    private Integer uid;

    private String videophoto;

    private String spacename;

    private String spacedescription;

    private String domain;

    private Integer addsize;

    private Short addfriend;

    private Short menunum;

    private String theme;

    public Integer getUid() {
        return uid;
    }

    public void setUid(Integer uid) {
        this.uid = uid;
    }

    public String getVideophoto() {
        return videophoto;
    }

    public void setVideophoto(String videophoto) {
        this.videophoto = videophoto == null ? null : videophoto.trim();
    }

    public String getSpacename() {
        return spacename;
    }

    public void setSpacename(String spacename) {
        this.spacename = spacename == null ? null : spacename.trim();
    }

    public String getSpacedescription() {
        return spacedescription;
    }

    public void setSpacedescription(String spacedescription) {
        this.spacedescription = spacedescription == null ? null : spacedescription.trim();
    }

    public String getDomain() {
        return domain;
    }

    public void setDomain(String domain) {
        this.domain = domain == null ? null : domain.trim();
    }

    public Integer getAddsize() {
        return addsize;
    }

    public void setAddsize(Integer addsize) {
        this.addsize = addsize;
    }

    public Short getAddfriend() {
        return addfriend;
    }

    public void setAddfriend(Short addfriend) {
        this.addfriend = addfriend;
    }

    public Short getMenunum() {
        return menunum;
    }

    public void setMenunum(Short menunum) {
        this.menunum = menunum;
    }

    public String getTheme() {
        return theme;
    }

    public void setTheme(String theme) {
        this.theme = theme == null ? null : theme.trim();
    }
}