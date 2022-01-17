package net.renfei.repositories.model;

import java.io.Serializable;
import java.util.Date;

public class KitboxShortUrl implements Serializable {
    private Long id;

    private String shortUrl;

    private String url;

    private Date addTime;

    private Long views;

    private Integer stateCode;

    private Long addUser;

    private static final long serialVersionUID = -314420603322403668L;

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getShortUrl() {
        return shortUrl;
    }

    public void setShortUrl(String shortUrl) {
        this.shortUrl = shortUrl;
    }

    public String getUrl() {
        return url;
    }

    public void setUrl(String url) {
        this.url = url;
    }

    public Date getAddTime() {
        return addTime;
    }

    public void setAddTime(Date addTime) {
        this.addTime = addTime;
    }

    public Long getViews() {
        return views;
    }

    public void setViews(Long views) {
        this.views = views;
    }

    public Integer getStateCode() {
        return stateCode;
    }

    public void setStateCode(Integer stateCode) {
        this.stateCode = stateCode;
    }

    public Long getAddUser() {
        return addUser;
    }

    public void setAddUser(Long addUser) {
        this.addUser = addUser;
    }

    @Override
    public String toString() {
        StringBuilder sb = new StringBuilder();
        sb.append(getClass().getSimpleName());
        sb.append(" [");
        sb.append("Hash = ").append(hashCode());
        sb.append(", id=").append(id);
        sb.append(", shortUrl=").append(shortUrl);
        sb.append(", url=").append(url);
        sb.append(", addTime=").append(addTime);
        sb.append(", views=").append(views);
        sb.append(", stateCode=").append(stateCode);
        sb.append(", addUser=").append(addUser);
        sb.append(", serialVersionUID=").append(serialVersionUID);
        sb.append("]");
        return sb.toString();
    }
}