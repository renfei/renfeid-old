package net.renfei.model;

import lombok.Builder;
import lombok.Data;

import java.io.Serializable;
import java.util.Date;
import java.util.List;

/**
 * <p>Title: RssVO</p>
 * <p>Description: </p>
 *
 * @author RenFei(i @ renfei.net)
 */
@Data
public class FeedVO implements Serializable {
    private static final long serialVersionUID = 1L;
    private String title;
    private String author;
    private String link;
    private String description;
    private String language;
    private Image image;
    private List<Item> item;

    @Data
    public static class Item{
        private String title;
        private String author;
        private String link;
        private String description;
        private String guid;
        private String category;
        private String comments;
        private Date pubDate;
    }

    @Data
    @Builder
    public static class Image{
        private String url;
        private String title;
        private String link;
        private String width;
        private String height;
    }
}
