package net.renfei.model;

import net.renfei.domain.user.User;

import java.io.Serializable;
import java.util.List;

public class PageHeader implements Serializable {
    private String logoSrc;
    private String logoName;
    private String notice;
    private List<LinkTree> menus;
    private User user;

    public String getLogoSrc() {
        return logoSrc;
    }

    public void setLogoSrc(String logoSrc) {
        this.logoSrc = logoSrc;
    }

    public String getLogoName() {
        return logoName;
    }

    public void setLogoName(String logoName) {
        this.logoName = logoName;
    }

    public String getNotice() {
        return notice;
    }

    public void setNotice(String notice) {
        this.notice = notice;
    }

    public List<LinkTree> getMenus() {
        return menus;
    }

    public void setMenus(List<LinkTree> menus) {
        this.menus = menus;
    }

    public User getUser() {
        return user;
    }

    public void setUser(User user) {
        this.user = user;
    }
}
