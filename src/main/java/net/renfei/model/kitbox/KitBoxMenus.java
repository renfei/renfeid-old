package net.renfei.model.kitbox;

import net.renfei.model.LinkTree;

import java.io.Serializable;
import java.util.List;

/**
 * <p>Title: KitboxMenus</p>
 * <p>Description: 工具箱菜单</p>
 *
 * @author RenFei(i @ renfei.net)
 */
public class KitBoxMenus implements Serializable {
    private String title;
    private String elementId;
    private Boolean isOpen;
    private List<LinkTree> links;

    public KitBoxMenus() {
    }

    private KitBoxMenus(Builder builder) {
        this.title = builder.title;
        this.elementId = builder.elementId;
        this.isOpen = builder.isOpen;
        this.links = builder.links;
    }

    public String getTitle() {
        return title;
    }

    public void setTitle(String title) {
        this.title = title;
    }

    public String getElementId() {
        return elementId;
    }

    public void setElementId(String elementId) {
        this.elementId = elementId;
    }

    public Boolean getOpen() {
        return isOpen;
    }

    public void setOpen(Boolean open) {
        isOpen = open;
    }

    public List<LinkTree> getLinks() {
        return links;
    }

    public void setLinks(List<LinkTree> links) {
        this.links = links;
    }

    public static Builder builder() {
        return new Builder();
    }

    public static class Builder {
        private String title;
        private String elementId;
        private Boolean isOpen;
        private List<LinkTree> links;

        public Builder() {
        }

        public Builder title(String title) {
            this.title = title;
            return this;
        }

        public Builder elementId(String elementId) {
            this.elementId = elementId;
            return this;
        }

        public Builder isOpen(Boolean isOpen) {
            this.isOpen = isOpen;
            return this;
        }

        public Builder links(List<LinkTree> links) {
            this.links = links;
            return this;
        }

        public KitBoxMenus build() {
            return new KitBoxMenus(this);
        }
    }
}
