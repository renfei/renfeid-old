/*
 *   Copyright 2022 RenFei(i@renfei.net)
 *
 * Licensed under the Apache License, Version 2.0 (the "License");
 * you may not use this file except in compliance with the License.
 * You may obtain a copy of the License at
 *
 *   http://www.apache.org/licenses/LICENSE-2.0
 *
 * Unless required by applicable law or agreed to in writing, software
 * distributed under the License is distributed on an "AS IS" BASIS,
 * WITHOUT WARRANTIES OR CONDITIONS OF ANY KIND, either express or implied.
 * See the License for the specific language governing permissions and
 * limitations under the License.
 */
package net.renfei.cms.api.entity;

import net.renfei.cms.api.constant.enums.PostStatusEnum;
import net.renfei.common.api.constant.enums.SecretLevelEnum;

import java.util.Date;

/**
 * @author renfei
 */
public class Post {
    private Long id;
    private Long categoryId;
    private Long postAuthor;
    private Date postDate;
    private PostStatusEnum postStatus;
    private Long postViews;
    private String commentStatus;
    private String postPassword;
    private Date postModified;
    private Long postModifiedUser;
    private Long postParent;
    private Integer versionNumber;
    private Long thumbsUp;
    private Long thumbsDown;
    private Double avgViews;
    private Double avgComment;
    private Double pageRank;
    private SecretLevelEnum secretLevel;
    private Boolean isOriginal;
    private String featuredImage;
    private String postTitle;
    private String postKeyword;
    private String postExcerpt;
    private String postContent;
    private String sourceName;
    private String sourceUrl;

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

    public SecretLevelEnum getSecretLevel() {
        return secretLevel;
    }

    public void setSecretLevel(SecretLevelEnum secretLevel) {
        this.secretLevel = secretLevel;
    }

    public Boolean getOriginal() {
        return isOriginal;
    }

    public void setOriginal(Boolean original) {
        isOriginal = original;
    }

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
}
