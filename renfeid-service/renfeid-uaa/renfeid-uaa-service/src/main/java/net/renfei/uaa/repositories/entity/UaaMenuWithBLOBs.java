package net.renfei.uaa.repositories.entity;

import java.io.Serializable;

public class UaaMenuWithBLOBs extends UaaMenu implements Serializable {
    private String menuHref;

    private String menuCss;

    private String extendJson;

    private static final long serialVersionUID = 1L;

    public String getMenuHref() {
        return menuHref;
    }

    public void setMenuHref(String menuHref) {
        this.menuHref = menuHref;
    }

    public String getMenuCss() {
        return menuCss;
    }

    public void setMenuCss(String menuCss) {
        this.menuCss = menuCss;
    }

    public String getExtendJson() {
        return extendJson;
    }

    public void setExtendJson(String extendJson) {
        this.extendJson = extendJson;
    }

    @Override
    public String toString() {
        StringBuilder sb = new StringBuilder();
        sb.append(getClass().getSimpleName());
        sb.append(" [");
        sb.append("Hash = ").append(hashCode());
        sb.append(", menuHref=").append(menuHref);
        sb.append(", menuCss=").append(menuCss);
        sb.append(", extendJson=").append(extendJson);
        sb.append(", serialVersionUID=").append(serialVersionUID);
        sb.append("]");
        return sb.toString();
    }
}