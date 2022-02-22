package net.renfei.model.search;

/**
 * <p>Title: TypeEnum</p>
 * <p>Description: </p>
 *
 * @author RenFei
 */
public enum TypeEnum {
    ALL(1L, "ALL", "/"),
    POSTS(1L, "POSTS", "/posts"),
    PAGES(2L, "PAGES", "/page"),
    VIDEO(3L, "VIDEO", "/video"),
    PHOTO(4L, "PHOTO", "/photo"),
    MOVIE(5L, "MOVIE", "/movie"),
    WEIBO(6L, "WEIBO", "/weibo"),
    KITBOX(7L, "KITBOX", "/kitbox"),
    DISCUZ(-1L, "DISCUZ", "https://bbs.renfei.net");
    private Long id;
    private String name;
    private String url;

    TypeEnum(Long id, String name, String url) {
        this.id = id;
        this.name = name;
        this.url = url;
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getUrl() {
        return url;
    }

    public void setUrl(String url) {
        this.url = url;
    }
}
