package net.renfei.repositories.model;

import java.io.Serializable;

public class SysSiteFooterMenu implements Serializable {
    private Long id;

    private Long pid;

    private String menuText;

    private String menuLink;

    private Boolean isNewWin;

    private Boolean isEnable;

    private Integer orderNumber;

    private static final long serialVersionUID = 1L;

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public Long getPid() {
        return pid;
    }

    public void setPid(Long pid) {
        this.pid = pid;
    }

    public String getMenuText() {
        return menuText;
    }

    public void setMenuText(String menuText) {
        this.menuText = menuText;
    }

    public String getMenuLink() {
        return menuLink;
    }

    public void setMenuLink(String menuLink) {
        this.menuLink = menuLink;
    }

    public Boolean getIsNewWin() {
        return isNewWin;
    }

    public void setIsNewWin(Boolean isNewWin) {
        this.isNewWin = isNewWin;
    }

    public Boolean getIsEnable() {
        return isEnable;
    }

    public void setIsEnable(Boolean isEnable) {
        this.isEnable = isEnable;
    }

    public Integer getOrderNumber() {
        return orderNumber;
    }

    public void setOrderNumber(Integer orderNumber) {
        this.orderNumber = orderNumber;
    }

    @Override
    public String toString() {
        StringBuilder sb = new StringBuilder();
        sb.append(getClass().getSimpleName());
        sb.append(" [");
        sb.append("Hash = ").append(hashCode());
        sb.append(", id=").append(id);
        sb.append(", pid=").append(pid);
        sb.append(", menuText=").append(menuText);
        sb.append(", menuLink=").append(menuLink);
        sb.append(", isNewWin=").append(isNewWin);
        sb.append(", isEnable=").append(isEnable);
        sb.append(", orderNumber=").append(orderNumber);
        sb.append(", serialVersionUID=").append(serialVersionUID);
        sb.append("]");
        return sb.toString();
    }
}