package net.renfei.model;

import lombok.*;

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
@NoArgsConstructor(access = AccessLevel.PUBLIC)
@AllArgsConstructor(access = AccessLevel.PRIVATE)
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
    @NoArgsConstructor(access = AccessLevel.PUBLIC)
    @AllArgsConstructor(access = AccessLevel.PRIVATE)
    public static class Item implements Serializable {
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
    @NoArgsConstructor(access = AccessLevel.PUBLIC)
    @AllArgsConstructor(access = AccessLevel.PRIVATE)
    public static class Image implements Serializable {
        private String url;
        private String title;
        private String link;
        private String width;
        private String height;
    }
}
