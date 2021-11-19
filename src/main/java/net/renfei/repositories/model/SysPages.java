package net.renfei.repositories.model;

import java.io.Serializable;
import java.util.Date;

public class SysPages implements Serializable {
    private Long id;

    private Long pageAuthor;

    private Date pageDate;

    private String pageStatus;

    private Long pageViews;

    private String pagePassword;

    private Date pageModified;

    private Long pageParent;

    private Long thumbsUp;

    private Long thumbsDown;

    private String secretLevel;

    private static final long serialVersionUID = 1L;

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public Long getPageAuthor() {
        return pageAuthor;
    }

    public void setPageAuthor(Long pageAuthor) {
        this.pageAuthor = pageAuthor;
    }

    public Date getPageDate() {
        return pageDate;
    }

    public void setPageDate(Date pageDate) {
        this.pageDate = pageDate;
    }

    public String getPageStatus() {
        return pageStatus;
    }

    public void setPageStatus(String pageStatus) {
        this.pageStatus = pageStatus;
    }

    public Long getPageViews() {
        return pageViews;
    }

    public void setPageViews(Long pageViews) {
        this.pageViews = pageViews;
    }

    public String getPagePassword() {
        return pagePassword;
    }

    public void setPagePassword(String pagePassword) {
        this.pagePassword = pagePassword;
    }

    public Date getPageModified() {
        return pageModified;
    }

    public void setPageModified(Date pageModified) {
        this.pageModified = pageModified;
    }

    public Long getPageParent() {
        return pageParent;
    }

    public void setPageParent(Long pageParent) {
        this.pageParent = pageParent;
    }

    public Long getThumbsUp() {
        return thumbsUp;
    }

    public void setThumbsUp(Long thumbsUp) {
        this.thumbsUp = thumbsUp;
    }

    public Long getThumbsDown() {
        return thumbsDown;
    }

    public void setThumbsDown(Long thumbsDown) {
        this.thumbsDown = thumbsDown;
    }

    public String getSecretLevel() {
        return secretLevel;
    }

    public void setSecretLevel(String secretLevel) {
        this.secretLevel = secretLevel;
    }

    @Override
    public String toString() {
        StringBuilder sb = new StringBuilder();
        sb.append(getClass().getSimpleName());
        sb.append(" [");
        sb.append("Hash = ").append(hashCode());
        sb.append(", id=").append(id);
        sb.append(", pageAuthor=").append(pageAuthor);
        sb.append(", pageDate=").append(pageDate);
        sb.append(", pageStatus=").append(pageStatus);
        sb.append(", pageViews=").append(pageViews);
        sb.append(", pagePassword=").append(pagePassword);
        sb.append(", pageModified=").append(pageModified);
        sb.append(", pageParent=").append(pageParent);
        sb.append(", thumbsUp=").append(thumbsUp);
        sb.append(", thumbsDown=").append(thumbsDown);
        sb.append(", secretLevel=").append(secretLevel);
        sb.append(", serialVersionUID=").append(serialVersionUID);
        sb.append("]");
        return sb.toString();
    }
}