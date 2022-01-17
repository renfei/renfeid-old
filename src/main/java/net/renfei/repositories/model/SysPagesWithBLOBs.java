package net.renfei.repositories.model;

import java.io.Serializable;

public class SysPagesWithBLOBs extends SysPages implements Serializable {
    private String featuredImage;

    private String pageTitle;

    private String pageKeyword;

    private String pageExcerpt;

    private String pageContent;

    private static final long serialVersionUID = -314420603322403668L;

    public String getFeaturedImage() {
        return featuredImage;
    }

    public void setFeaturedImage(String featuredImage) {
        this.featuredImage = featuredImage;
    }

    public String getPageTitle() {
        return pageTitle;
    }

    public void setPageTitle(String pageTitle) {
        this.pageTitle = pageTitle;
    }

    public String getPageKeyword() {
        return pageKeyword;
    }

    public void setPageKeyword(String pageKeyword) {
        this.pageKeyword = pageKeyword;
    }

    public String getPageExcerpt() {
        return pageExcerpt;
    }

    public void setPageExcerpt(String pageExcerpt) {
        this.pageExcerpt = pageExcerpt;
    }

    public String getPageContent() {
        return pageContent;
    }

    public void setPageContent(String pageContent) {
        this.pageContent = pageContent;
    }

    @Override
    public String toString() {
        StringBuilder sb = new StringBuilder();
        sb.append(getClass().getSimpleName());
        sb.append(" [");
        sb.append("Hash = ").append(hashCode());
        sb.append(", featuredImage=").append(featuredImage);
        sb.append(", pageTitle=").append(pageTitle);
        sb.append(", pageKeyword=").append(pageKeyword);
        sb.append(", pageExcerpt=").append(pageExcerpt);
        sb.append(", pageContent=").append(pageContent);
        sb.append(", serialVersionUID=").append(serialVersionUID);
        sb.append("]");
        return sb.toString();
    }
}