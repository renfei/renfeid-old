package net.renfei.domain.pages;

import net.renfei.model.SecretLevelEnum;
import net.renfei.model.blog.PostStatusEnum;

import java.io.Serializable;
import java.util.Date;

/**
 * @author renfei
 */
public class Page implements Serializable {
    private Long id;

    private Long pageAuthor;

    private Date pageDate;

    private PostStatusEnum pageStatus;

    private Long pageViews;

    private String pagePassword;

    private Date pageModified;

    private Long pageParent;

    private Long thumbsUp;

    private Long thumbsDown;

    private SecretLevelEnum secretLevel;

    private String featuredImage;

    private String pageTitle;

    private String pageKeyword;

    private String pageExcerpt;

    private String pageContent;

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

    public PostStatusEnum getPageStatus() {
        return pageStatus;
    }

    public void setPageStatus(PostStatusEnum pageStatus) {
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

    public SecretLevelEnum getSecretLevel() {
        return secretLevel;
    }

    public void setSecretLevel(SecretLevelEnum secretLevel) {
        this.secretLevel = secretLevel;
    }

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
}
