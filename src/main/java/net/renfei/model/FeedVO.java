package net.renfei.model;

import java.io.Serializable;
import java.util.Date;
import java.util.List;

/**
 * <p>Title: RssVO</p>
 * <p>Description: </p>
 *
 * @author RenFei(i @ renfei.net)
 */
public class FeedVO implements Serializable {
    private static final long serialVersionUID = 1L;
    private String title;
    private String author;
    private String link;
    private String description;
    private String language;
    private Image image;
    private List<Item> item;

    public static class Item implements Serializable {
        private String title;
        private String author;
        private String link;
        private String description;
        private String guid;
        private String category;
        private String comments;
        private Date pubDate;

        public String getTitle() {
            return title;
        }

        public void setTitle(String title) {
            this.title = title;
        }

        public String getAuthor() {
            return author;
        }

        public void setAuthor(String author) {
            this.author = author;
        }

        public String getLink() {
            return link;
        }

        public void setLink(String link) {
            this.link = link;
        }

        public String getDescription() {
            return description;
        }

        public void setDescription(String description) {
            this.description = description;
        }

        public String getGuid() {
            return guid;
        }

        public void setGuid(String guid) {
            this.guid = guid;
        }

        public String getCategory() {
            return category;
        }

        public void setCategory(String category) {
            this.category = category;
        }

        public String getComments() {
            return comments;
        }

        public void setComments(String comments) {
            this.comments = comments;
        }

        public Date getPubDate() {
            return pubDate;
        }

        public void setPubDate(Date pubDate) {
            this.pubDate = pubDate;
        }
    }

    public static class Image implements Serializable {
        private String url;
        private String title;
        private String link;
        private String width;
        private String height;

        private Image(Builder builder) {
            this.url = builder.url;
            this.title = builder.title;
            this.link = builder.link;
            this.width = builder.width;
            this.height = builder.height;
        }

        public String getUrl() {
            return url;
        }

        public void setUrl(String url) {
            this.url = url;
        }

        public String getTitle() {
            return title;
        }

        public void setTitle(String title) {
            this.title = title;
        }

        public String getLink() {
            return link;
        }

        public void setLink(String link) {
            this.link = link;
        }

        public String getWidth() {
            return width;
        }

        public void setWidth(String width) {
            this.width = width;
        }

        public String getHeight() {
            return height;
        }

        public void setHeight(String height) {
            this.height = height;
        }

        public static Builder builder() {
            return new Builder();
        }

        public static class Builder {
            private String url;
            private String title;
            private String link;
            private String width;
            private String height;

            public Builder url(String url) {
                this.url = url;
                return this;
            }

            public Builder title(String title) {
                this.title = title;
                return this;
            }

            public Builder link(String link) {
                this.link = link;
                return this;
            }

            public Builder width(String width) {
                this.width = width;
                return this;
            }

            public Builder height(String height) {
                this.height = height;
                return this;
            }

            public Image build() {
                return new Image(this);
            }
        }
    }

    public String getTitle() {
        return title;
    }

    public void setTitle(String title) {
        this.title = title;
    }

    public String getAuthor() {
        return author;
    }

    public void setAuthor(String author) {
        this.author = author;
    }

    public String getLink() {
        return link;
    }

    public void setLink(String link) {
        this.link = link;
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    public String getLanguage() {
        return language;
    }

    public void setLanguage(String language) {
        this.language = language;
    }

    public Image getImage() {
        return image;
    }

    public void setImage(Image image) {
        this.image = image;
    }

    public List<Item> getItem() {
        return item;
    }

    public void setItem(List<Item> item) {
        this.item = item;
    }
}
