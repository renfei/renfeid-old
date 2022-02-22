package net.renfei.domain.photo;

import java.io.Serializable;

public class AlbumImg implements Serializable {
    private static final long serialVersionUID = -314420603322403668L;

    private Long id;

    private Long photoId;

    private String uri;

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public Long getPhotoId() {
        return photoId;
    }

    public void setPhotoId(Long photoId) {
        this.photoId = photoId;
    }

    public String getUri() {
        return uri;
    }

    public void setUri(String uri) {
        this.uri = uri;
    }
}
