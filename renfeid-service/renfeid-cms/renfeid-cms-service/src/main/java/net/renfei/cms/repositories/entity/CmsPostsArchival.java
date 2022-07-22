package net.renfei.cms.repositories.entity;

import java.io.Serializable;
import java.util.Date;

public class CmsPostsArchival implements Serializable {
    private Long id;

    private Long categoryId;

    private String postAuthorUsername;

    private Long postAuthor;

    private Date postDate;

    private String postStatus;

    private Long postViews;

    private String commentStatus;

    private String postPassword;

    private Date postModified;

    private String postModifiedUsername;

    private Long postModifiedUser;

    private Long postParent;

    private Integer versionNumber;

    private Long thumbsUp;

    private Long thumbsDown;

    private Double avgViews;

    private Double avgComment;

    private Double pageRank;

    private Integer secretLevel;

    private Boolean isOriginal;

    private String postTitle;

    private String postKeyword;

    private String postExcerpt;

    private String sourceName;

    private String sourceUrl;

    private static final long serialVersionUID = 1L;

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public Long getCategoryId() {
        return categoryId;
    }

    public void setCategoryId(Long categoryId) {
        this.categoryId = categoryId;
    }

    public String getPostAuthorUsername() {
        return postAuthorUsername;
    }

    public void setPostAuthorUsername(String postAuthorUsername) {
        this.postAuthorUsername = postAuthorUsername;
    }

    public Long getPostAuthor() {
        return postAuthor;
    }

    public void setPostAuthor(Long postAuthor) {
        this.postAuthor = postAuthor;
    }

    public Date getPostDate() {
        return postDate;
    }

    public void setPostDate(Date postDate) {
        this.postDate = postDate;
    }

    public String getPostStatus() {
        return postStatus;
    }

    public void setPostStatus(String postStatus) {
        this.postStatus = postStatus;
    }

    public Long getPostViews() {
        return postViews;
    }

    public void setPostViews(Long postViews) {
        this.postViews = postViews;
    }

    public String getCommentStatus() {
        return commentStatus;
    }

    public void setCommentStatus(String commentStatus) {
        this.commentStatus = commentStatus;
    }

    public String getPostPassword() {
        return postPassword;
    }

    public void setPostPassword(String postPassword) {
        this.postPassword = postPassword;
    }

    public Date getPostModified() {
        return postModified;
    }

    public void setPostModified(Date postModified) {
        this.postModified = postModified;
    }

    public String getPostModifiedUsername() {
        return postModifiedUsername;
    }

    public void setPostModifiedUsername(String postModifiedUsername) {
        this.postModifiedUsername = postModifiedUsername;
    }

    public Long getPostModifiedUser() {
        return postModifiedUser;
    }

    public void setPostModifiedUser(Long postModifiedUser) {
        this.postModifiedUser = postModifiedUser;
    }

    public Long getPostParent() {
        return postParent;
    }

    public void setPostParent(Long postParent) {
        this.postParent = postParent;
    }

    public Integer getVersionNumber() {
        return versionNumber;
    }

    public void setVersionNumber(Integer versionNumber) {
        this.versionNumber = versionNumber;
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

    public Double getAvgViews() {
        return avgViews;
    }

    public void setAvgViews(Double avgViews) {
        this.avgViews = avgViews;
    }

    public Double getAvgComment() {
        return avgComment;
    }

    public void setAvgComment(Double avgComment) {
        this.avgComment = avgComment;
    }

    public Double getPageRank() {
        return pageRank;
    }

    public void setPageRank(Double pageRank) {
        this.pageRank = pageRank;
    }

    public Integer getSecretLevel() {
        return secretLevel;
    }

    public void setSecretLevel(Integer secretLevel) {
        this.secretLevel = secretLevel;
    }

    public Boolean getIsOriginal() {
        return isOriginal;
    }

    public void setIsOriginal(Boolean isOriginal) {
        this.isOriginal = isOriginal;
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
        sb.append(", id=").append(id);
        sb.append(", categoryId=").append(categoryId);
        sb.append(", postAuthorUsername=").append(postAuthorUsername);
        sb.append(", postAuthor=").append(postAuthor);
        sb.append(", postDate=").append(postDate);
        sb.append(", postStatus=").append(postStatus);
        sb.append(", postViews=").append(postViews);
        sb.append(", commentStatus=").append(commentStatus);
        sb.append(", postPassword=").append(postPassword);
        sb.append(", postModified=").append(postModified);
        sb.append(", postModifiedUsername=").append(postModifiedUsername);
        sb.append(", postModifiedUser=").append(postModifiedUser);
        sb.append(", postParent=").append(postParent);
        sb.append(", versionNumber=").append(versionNumber);
        sb.append(", thumbsUp=").append(thumbsUp);
        sb.append(", thumbsDown=").append(thumbsDown);
        sb.append(", avgViews=").append(avgViews);
        sb.append(", avgComment=").append(avgComment);
        sb.append(", pageRank=").append(pageRank);
        sb.append(", secretLevel=").append(secretLevel);
        sb.append(", isOriginal=").append(isOriginal);
        sb.append(", postTitle=").append(postTitle);
        sb.append(", postKeyword=").append(postKeyword);
        sb.append(", postExcerpt=").append(postExcerpt);
        sb.append(", sourceName=").append(sourceName);
        sb.append(", sourceUrl=").append(sourceUrl);
        sb.append(", serialVersionUID=").append(serialVersionUID);
        sb.append("]");
        return sb.toString();
    }
}