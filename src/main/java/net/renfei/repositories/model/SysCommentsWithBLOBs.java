package net.renfei.repositories.model;

import java.io.Serializable;

public class SysCommentsWithBLOBs extends SysComments implements Serializable {
    private String author;

    private String authorEmail;

    private String authorUrl;

    private String authorIp;

    private String authorAddress;

    private String content;

    private static final long serialVersionUID = -314420603322403668L;

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

    public String getAuthorIp() {
        return authorIp;
    }

    public void setAuthorIp(String authorIp) {
        this.authorIp = authorIp;
    }

    public String getAuthorAddress() {
        return authorAddress;
    }

    public void setAuthorAddress(String authorAddress) {
        this.authorAddress = authorAddress;
    }

    public String getContent() {
        return content;
    }

    public void setContent(String content) {
        this.content = content;
    }

    @Override
    public String toString() {
        StringBuilder sb = new StringBuilder();
        sb.append(getClass().getSimpleName());
        sb.append(" [");
        sb.append("Hash = ").append(hashCode());
        sb.append(", author=").append(author);
        sb.append(", authorEmail=").append(authorEmail);
        sb.append(", authorUrl=").append(authorUrl);
        sb.append(", authorIp=").append(authorIp);
        sb.append(", authorAddress=").append(authorAddress);
        sb.append(", content=").append(content);
        sb.append(", serialVersionUID=").append(serialVersionUID);
        sb.append("]");
        return sb.toString();
    }
}