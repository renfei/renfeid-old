package net.renfei.repositories.model;

import java.io.Serializable;

public class BlogPostsWithBLOBs extends BlogPosts implements Serializable {
    private String featuredImage;

    private String postTitle;

    private String postKeyword;

    private String postExcerpt;

    private String postContent;

    private String sourceName;

    private String sourceUrl;

    private static final long serialVersionUID = -314420603322403668L;

    public String getFeaturedImage() {
        return featuredImage;
    }

    public void setFeaturedImage(String featuredImage) {
        this.featuredImage = featuredImage;
    }

    public String getPostTitle() {
        return postTitle;
    }

    public void setPostTitle(String postTitle) {
        this.postTitle = postTitle;
    }

    public String getPostKeyword() {
        return postKeyword;
    }

    public void setPostKeyword(String postKeyword) {
        this.postKeyword = postKeyword;
    }

    public String getPostExcerpt() {
        return postExcerpt;
    }

    public void setPostExcerpt(String postExcerpt) {
        this.postExcerpt = postExcerpt;
    }

    public String getPostContent() {
        return postContent;
    }

    public void setPostContent(String postContent) {
        this.postContent = postContent;
    }

    public String getSourceName() {
        return sourceName;
    }

    public void setSourceName(String sourceName) {
        this.sourceName = sourceName;
    }

    public String getSourceUrl() {
        return sourceUrl;
    }

    public void setSourceUrl(String sourceUrl) {
        this.sourceUrl = sourceUrl;
    }

    @Override
    public String toString() {
        StringBuilder sb = new StringBuilder();
        sb.append(getClass().getSimpleName());
        sb.append(" [");
        sb.append("Hash = ").append(hashCode());
        sb.append(", featuredImage=").append(featuredImage);
        sb.append(", postTitle=").append(postTitle);
        sb.append(", postKeyword=").append(postKeyword);
        sb.append(", postExcerpt=").append(postExcerpt);
        sb.append(", postContent=").append(postContent);
        sb.append(", sourceName=").append(sourceName);
        sb.append(", sourceUrl=").append(sourceUrl);
        sb.append(", serialVersionUID=").append(serialVersionUID);
        sb.append("]");
        return sb.toString();
    }
}