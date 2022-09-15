package net.renfei.uaa.repositories.entity;

import java.io.Serializable;
import java.util.Date;

public class UaaMenu implements Serializable {
    private Long id;

    private Long pid;

    private String menuName;

    private String menuType;

    private String permissionExpr;

    private String menuIcon;

    private String menuTarget;

    private String menuClass;

    private String menuTitle;

    private String menuOnclick;

    private String menuOrder;

    private Boolean enable;

    private Date addTime;

    private Date updateTime;

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

    public String getMenuName() {
        return menuName;
    }

    public void setMenuName(String menuName) {
        this.menuName = menuName;
    }

    public String getMenuType() {
        return menuType;
    }

    public void setMenuType(String menuType) {
        this.menuType = menuType;
    }

    public String getPermissionExpr() {
        return permissionExpr;
    }

    public void setPermissionExpr(String permissionExpr) {
        this.permissionExpr = permissionExpr;
    }

    public String getMenuIcon() {
        return menuIcon;
    }

    public void setMenuIcon(String menuIcon) {
        this.menuIcon = menuIcon;
    }

    public String getMenuTarget() {
        return menuTarget;
    }

    public void setMenuTarget(String menuTarget) {
        this.menuTarget = menuTarget;
    }

    public String getMenuClass() {
        return menuClass;
    }

    public void setMenuClass(String menuClass) {
        this.menuClass = menuClass;
    }

    public String getMenuTitle() {
        return menuTitle;
    }

    public void setMenuTitle(String menuTitle) {
        this.menuTitle = menuTitle;
    }

    public String getMenuOnclick() {
        return menuOnclick;
    }

    public void setMenuOnclick(String menuOnclick) {
        this.menuOnclick = menuOnclick;
    }

    public String getMenuOrder() {
        return menuOrder;
    }

    public void setMenuOrder(String menuOrder) {
        this.menuOrder = menuOrder;
    }

    public Boolean getEnable() {
        return enable;
    }

    public void setEnable(Boolean enable) {
        this.enable = enable;
    }

    public Date getAddTime() {
        return addTime;
    }

    public void setAddTime(Date addTime) {
        this.addTime = addTime;
    }

    public Date getUpdateTime() {
        return updateTime;
    }

    public void setUpdateTime(Date updateTime) {
        this.updateTime = updateTime;
    }

    @Override
    public String toString() {
        StringBuilder sb = new StringBuilder();
        sb.append(getClass().getSimpleName());
        sb.append(" [");
        sb.append("Hash = ").append(hashCode());
        sb.append(", id=").append(id);
        sb.append(", pid=").append(pid);
        sb.append(", menuName=").append(menuName);
        sb.append(", menuType=").append(menuType);
        sb.append(", permissionExpr=").append(permissionExpr);
        sb.append(", menuIcon=").append(menuIcon);
        sb.append(", menuTarget=").append(menuTarget);
        sb.append(", menuClass=").append(menuClass);
        sb.append(", menuTitle=").append(menuTitle);
        sb.append(", menuOnclick=").append(menuOnclick);
        sb.append(", menuOrder=").append(menuOrder);
        sb.append(", enable=").append(enable);
        sb.append(", addTime=").append(addTime);
        sb.append(", updateTime=").append(updateTime);
        sb.append(", serialVersionUID=").append(serialVersionUID);
        sb.append("]");
        return sb.toString();
    }
}