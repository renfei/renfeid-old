package net.renfei.domain.photo;

import lombok.Data;

@Data
public class AlbumImg {
    private Long id;

    private Long photoId;

    private String uri;
}
