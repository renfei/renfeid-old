package net.renfei.model;

import java.io.Serializable;
import java.util.List;

public class LinkTree implements Serializable {
    private String icon;
    private String href;
    private String text;
    private String target;
    private String rel;
    private String style;
    private List<LinkTree> subLink;

    public LinkTree() {
    }

    private LinkTree(Builder builder) {
        this.icon = builder.icon;
        this.href = builder.href;
        this.text = builder.text;
        this.target = builder.target;
        this.rel = builder.rel;
        this.style = builder.style;
        this.subLink = builder.subLink;
    }

    public String getIcon() {
        return icon;
    }

    public void setIcon(String icon) {
        this.icon = icon;
    }

    public String getHref() {
        return href;
    }

    public void setHref(String href) {
        this.href = href;
    }

    public String getText() {
        return text;
    }

    public void setText(String text) {
        this.text = text;
    }

    public String getTarget() {
        return target;
    }

    public void setTarget(String target) {
        this.target = target;
    }

    public String getRel() {
        return rel;
    }

    public void setRel(String rel) {
        this.rel = rel;
    }

    public String getStyle() {
        return style;
    }

    public void setStyle(String style) {
        this.style = style;
    }

    public List<LinkTree> getSubLink() {
        return subLink;
    }

    public void setSubLink(List<LinkTree> subLink) {
        this.subLink = subLink;
    }

    public static LinkTree.Builder builder() {
        return new LinkTree.Builder();
    }

    public static class Builder {
        private String icon;
        private String href;
        private String text;
        private String target;
        private String rel;
        private String style;
        private List<LinkTree> subLink;

        public Builder() {
        }

        public LinkTree.Builder icon(String icon) {
            this.icon = icon;
            return this;
        }

        public LinkTree.Builder href(String href) {
            this.href = href;
            return this;
        }

        public LinkTree.Builder text(String text) {
            this.text = text;
            return this;
        }

        public LinkTree.Builder target(String target) {
            this.target = target;
            return this;
        }

        public LinkTree.Builder rel(String rel) {
            this.rel = rel;
            return this;
        }

        public LinkTree.Builder style(String style) {
            this.style = style;
            return this;
        }

        public LinkTree.Builder subLink(List<LinkTree> subLink) {
            this.subLink = subLink;
            return this;
        }

        public LinkTree build() {
            return new LinkTree(this);
        }
    }
}
