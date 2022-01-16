package net.renfei.domain.blog;

import net.renfei.model.CommentStatusEnum;
import net.renfei.model.blog.PostStatusEnum;
import net.renfei.model.SecretLevelEnum;

import java.io.Serializable;
import java.util.Date;

/**
 * @author renfei
 */
public class Post implements Serializable {
    private Long id;
    private String title;
    private String keyword;
    private String excerpt;
    private String content;
    private String featuredImage;
    private Boolean isOriginal;
    private String sourceName;
    private String sourceUrl;
    private Date postDate;
    private Long postAuthor;
    private Long categoryId;
    private PostStatusEnum postStatus;
    private Long postViews;
    private CommentStatusEnum commentStatusenum;
    private String postPassword;
    private Date postModified;
    private Long postParent;
    private Long thumbsUp;
    private Long thumbsDown;
    private Double avgViews;
    private Double avgComment;
    private Double pageRank;
    private SecretLevelEnum secretLevelEnum;

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getTitle() {
        return title;
    }

    public void setTitle(String title) {
        this.title = title;
    }

    public String getKeyword() {
        return keyword;
    }

    public void setKeyword(String keyword) {
        this.keyword = keyword;
    }

    public String getExcerpt() {
        return excerpt;
    }

    public void setExcerpt(String excerpt) {
        this.excerpt = excerpt;
    }

    public String getContent() {
        return content;
    }

    public void setContent(String content) {
        this.content = content;
    }

    public String getFeaturedImage() {
        return featuredImage;
    }

    public void setFeaturedImage(String featuredImage) {
        this.featuredImage = featuredImage;
    }

    public Boolean getOriginal() {
        return isOriginal;
    }

    public void setOriginal(Boolean original) {
        isOriginal = original;
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

    public Date getPostDate() {
        return postDate;
    }

    public void setPostDate(Date postDate) {
        this.postDate = postDate;
    }

    public Long getPostAuthor() {
        return postAuthor;
    }

    public void setPostAuthor(Long postAuthor) {
        this.postAuthor = postAuthor;
    }

    public Long getCategoryId() {
        return categoryId;
    }

    public void setCategoryId(Long categoryId) {
        this.categoryId = categoryId;
    }

    public PostStatusEnum getPostStatus() {
        return postStatus;
    }

    public void setPostStatus(PostStatusEnum postStatus) {
        this.postStatus = postStatus;
    }

    public Long getPostViews() {
        return postViews;
    }

    public void setPostViews(Long postViews) {
        this.postViews = postViews;
    }

    public CommentStatusEnum getCommentStatusenum() {
        return commentStatusenum;
    }

    public void setCommentStatusenum(CommentStatusEnum commentStatusenum) {
        this.commentStatusenum = commentStatusenum;
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

    public Long getPostParent() {
        return postParent;
    }

    public void setPostParent(Long postParent) {
        this.postParent = postParent;
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

    public SecretLevelEnum getSecretLevelEnum() {
        return secretLevelEnum;
    }

    public void setSecretLevelEnum(SecretLevelEnum secretLevelEnum) {
        this.secretLevelEnum = secretLevelEnum;
    }
}
