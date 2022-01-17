package net.renfei.repositories.model;

import java.io.Serializable;

public class WechatKeyword implements Serializable {
    private Long id;

    private String keyWord;

    private String keyType;

    private String title;

    private String description;

    private String picUrl;

    private String url;

    private String mediaId;

    private static final long serialVersionUID = -314420603322403668L;

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getKeyWord() {
        return keyWord;
    }

    public void setKeyWord(String keyWord) {
        this.keyWord = keyWord;
    }

    public String getKeyType() {
        return keyType;
    }

    public void setKeyType(String keyType) {
        this.keyType = keyType;
    }

    public String getTitle() {
        return title;
    }

    public void setTitle(String title) {
        this.title = title;
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    public String getPicUrl() {
        return picUrl;
    }

    public void setPicUrl(String picUrl) {
        this.picUrl = picUrl;
    }

    public String getUrl() {
        return url;
    }

    public void setUrl(String url) {
        this.url = url;
    }

    public String getMediaId() {
        return mediaId;
    }

    public void setMediaId(String mediaId) {
        this.mediaId = mediaId;
    }

    @Override
    public String toString() {
        StringBuilder sb = new StringBuilder();
        sb.append(getClass().getSimpleName());
        sb.append(" [");
        sb.append("Hash = ").append(hashCode());
        sb.append(", id=").append(id);
        sb.append(", keyWord=").append(keyWord);
        sb.append(", keyType=").append(keyType);
        sb.append(", title=").append(title);
        sb.append(", description=").append(description);
        sb.append(", picUrl=").append(picUrl);
        sb.append(", url=").append(url);
        sb.append(", mediaId=").append(mediaId);
        sb.append(", serialVersionUID=").append(serialVersionUID);
        sb.append("]");
        return sb.toString();
    }
}