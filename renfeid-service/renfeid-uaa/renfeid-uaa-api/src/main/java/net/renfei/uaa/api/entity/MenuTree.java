/*
 *   Copyright 2022 RenFei(i@renfei.net)
 *
 * Licensed under the Apache License, Version 2.0 (the "License");
 * you may not use this file except in compliance with the License.
 * You may obtain a copy of the License at
 *
 *   http://www.apache.org/licenses/LICENSE-2.0
 *
 * Unless required by applicable law or agreed to in writing, software
 * distributed under the License is distributed on an "AS IS" BASIS,
 * WITHOUT WARRANTIES OR CONDITIONS OF ANY KIND, either express or implied.
 * See the License for the specific language governing permissions and
 * limitations under the License.
 */
package net.renfei.uaa.api.entity;

import io.swagger.v3.oas.annotations.media.Schema;

import java.io.Serializable;
import java.util.Date;
import java.util.List;

/**
 * 菜单树
 *
 * @author renfei
 */
@Schema(title = "菜单树")
public class MenuTree implements Serializable {
    private static final long serialVersionUID = -3316408227872898096L;
    @Schema(description = "菜单ID")
    private String id;
    @Schema(description = "菜单父级ID")
    private String pid;
    @Schema(description = "菜单名称")
    private String menuName;
    @Schema(description = "菜单图标")
    private String menuIcon;
    @Schema(description = "菜单目标")
    private String menuTarget;
    @Schema(description = "菜单样式类")
    private String menuClass;
    @Schema(description = "菜单标题")
    private String menuTitle;
    @Schema(description = "菜单点击事件")
    private String menuOnclick;
    @Schema(description = "菜单排序")
    private Integer menuOrder;
    @Schema(description = "是否启用")
    private Boolean enable;
    @Schema(description = "添加时间")
    private Date addTime;
    @Schema(description = "更新时间")
    private Date updateTime;
    @Schema(description = "菜单链接")
    private String menuHref;
    @Schema(description = "菜单样式")
    private String menuCss;
    @Schema(description = "菜单扩展预留")
    private String extendJson;
    @Schema(description = "子级菜单")
    private List<MenuTree> child;

    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }

    public String getPid() {
        return pid;
    }

    public void setPid(String pid) {
        this.pid = pid;
    }

    public String getMenuName() {
        return menuName;
    }

    public void setMenuName(String menuName) {
        this.menuName = menuName;
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

    public Integer getMenuOrder() {
        return menuOrder;
    }

    public void setMenuOrder(Integer menuOrder) {
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

    public List<MenuTree> getChild() {
        return child;
    }

    public void setChild(List<MenuTree> child) {
        this.child = child;
    }
}
