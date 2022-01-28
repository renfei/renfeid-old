package net.renfei.model.system;

import java.util.List;

/**
 * ant-design-pro 菜单对象
 * 此对象结构是根据：
 * https://github.com/ant-design/ant-design-pro-layout/blob/56590a06434c3d0e77dbddcd2bc60827c9866706/src/typings.ts#L18
 *
 * @author renfei
 */
public class MenuDataItemVo {
    private Long id;
    private List<String> authority;
    private List<MenuDataItemVo> children;
    private Boolean hideChildrenInMenu;
    private Boolean hideInMenu;
    private String icon;
    private String locale;
    private String name;
    private String path;

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public List<String> getAuthority() {
        return authority;
    }

    public void setAuthority(List<String> authority) {
        this.authority = authority;
    }

    public List<MenuDataItemVo> getChildren() {
        return children;
    }

    public void setChildren(List<MenuDataItemVo> children) {
        this.children = children;
    }

    public Boolean getHideChildrenInMenu() {
        return hideChildrenInMenu;
    }

    public void setHideChildrenInMenu(Boolean hideChildrenInMenu) {
        this.hideChildrenInMenu = hideChildrenInMenu;
    }

    public Boolean getHideInMenu() {
        return hideInMenu;
    }

    public void setHideInMenu(Boolean hideInMenu) {
        this.hideInMenu = hideInMenu;
    }

    public String getIcon() {
        return icon;
    }

    public void setIcon(String icon) {
        this.icon = icon;
    }

    public String getLocale() {
        return locale;
    }

    public void setLocale(String locale) {
        this.locale = locale;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getPath() {
        return path;
    }

    public void setPath(String path) {
        this.path = path;
    }
}
