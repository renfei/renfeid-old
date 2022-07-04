package net.renfei.cms.repositories.entity;

import java.io.Serializable;

public class CmsPostsWithBLOBs extends CmsPosts implements Serializable {
    private String featuredImage;

    private String postContent;

    private static final long serialVersionUID = 1L;

    public String getFeaturedImage() {
        return featuredImage;
    }

    public void setFeaturedImage(String featuredImage) {
        this.featuredImage = featuredImage;
    }

    public String getPostContent() {
        return postContent;
    }

    public void setPostContent(String postContent) {
        this.postContent = postContent;
    }

    @Override
    public String toString() {
        StringBuilder sb = new StringBuilder();
        sb.append(getClass().getSimpleName());
        sb.append(" [");
        sb.append("Hash = ").append(hashCode());
        sb.append(", featuredImage=").append(featuredImage);
        sb.append(", postContent=").append(postContent);
        sb.append(", serialVersionUID=").append(serialVersionUID);
        sb.append("]");
        return sb.toString();
    }
}