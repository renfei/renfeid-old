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
package net.renfei.server.entity;

import io.swagger.v3.oas.annotations.media.Schema;

import java.io.Serializable;

/**
 * 评论请求对象
 *
 * @author renfei
 */
@Schema(title = "评论请求对象")
public class CommentAo implements Serializable {
    private static final long serialVersionUID = -314420603322403668L;
    @Schema(description = "评论给评论的ID")
    private String parentId;
    @Schema(description = "作者昵称")
    private String author;
    @Schema(description = "作者邮箱")
    private String authorEmail;
    @Schema(description = "作者链接")
    private String authorUrl;
    @Schema(description = "评论内容")
    private String content;

    public String getParentId() {
        return parentId;
    }

    public void setParentId(String parentId) {
        this.parentId = parentId;
    }

    public String getAuthor() {
        return author;
    }

    public void setAuthor(String author) {
        this.author = author;
    }

    public String getAuthorEmail() {
        return authorEmail;
    }

    public void setAuthorEmail(String authorEmail) {
        this.authorEmail = authorEmail;
    }

    public String getAuthorUrl() {
        return authorUrl;
    }

    public void setAuthorUrl(String authorUrl) {
        this.authorUrl = authorUrl;
    }

    public String getContent() {
        return content;
    }

    public void setContent(String content) {
        this.content = content;
    }
}
