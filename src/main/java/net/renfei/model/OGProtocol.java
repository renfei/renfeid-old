package net.renfei.model;

import java.io.Serializable;
import java.util.Date;

/**
 * @author renfei
 */
public class OGProtocol implements Serializable {
    private String title;
    private String description;
    private String type;
    private String image;
    private String url;
    private Date releaseDate;
    private String author;
    private String locale;
    private String siteName;

    private OGProtocol(Builder builder) {
        this.title = builder.title;
        this.description = builder.description;
        this.type = builder.type;
        this.image = builder.image;
        this.url = builder.url;
        this.releaseDate = builder.releaseDate;
        this.author = builder.author;
        this.locale = builder.locale;
        this.siteName = builder.siteName;
    }

    public String getImage() {
        return image == null ? "https://cdn.renfei.net/Logo/ogimage.png" : image;
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

    public String getType() {
        return type;
    }

    public void setType(String type) {
        this.type = type;
    }

    public void setImage(String image) {
        this.image = image;
    }

    public String getUrl() {
        return url;
    }

    public void setUrl(String url) {
        this.url = url;
    }

    public Date getReleaseDate() {
        return releaseDate;
    }

    public void setReleaseDate(Date releaseDate) {
        this.releaseDate = releaseDate;
    }

    public String getAuthor() {
        return author;
    }

    public void setAuthor(String author) {
        this.author = author;
    }

    public String getLocale() {
        return locale;
    }

    public void setLocale(String locale) {
        this.locale = locale;
    }

    public String getSiteName() {
        return siteName;
    }

    public void setSiteName(String siteName) {
        this.siteName = siteName;
    }

    public static Builder builder() {
        return new Builder();
    }

    public static class Builder {
        private String title;
        private String description;
        private String type;
        private String image;
        private String url;
        private Date releaseDate;
        private String author;
        private String locale;
        private String siteName;

        public Builder title(String title) {
            this.title = title;
            return this;
        }

        public Builder description(String description) {
            this.description = description;
            return this;
        }

        public Builder type(String type) {
            this.type = type;
            return this;
        }

        public Builder image(String image) {
            this.image = image;
            return this;
        }

        public Builder url(String url) {
            this.url = url;
            return this;
        }

        public Builder releaseDate(Date releaseDate) {
            this.releaseDate = releaseDate;
            return this;
        }

        public Builder author(String author) {
            this.author = author;
            return this;
        }

        public Builder locale(String locale) {
            this.locale = locale;
            return this;
        }

        public Builder siteName(String siteName) {
            this.siteName = siteName;
            return this;
        }

        public OGProtocol build() {
            return new OGProtocol(this);
        }
    }
}
