package net.renfei.domain.photo;

import lombok.Data;

import java.io.Serializable;

@Data
public class AlbumImg implements Serializable {
    private Long id;

    private Long photoId;

    private String uri;
}
