package net.renfei.domain.photo;

import lombok.Data;

import java.util.Date;

@Data
public class Album {
    private Long id;

    private Long categoryId;

    private Date releaseTime;

    private Date addTime;

    private Boolean isDelete;

    private String title;

    private String featuredImage;

    private String describes;
}
