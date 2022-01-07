package net.renfei.domain.photo;

import lombok.Data;

import java.io.Serializable;
import java.util.Date;

@Data
public class Album implements Serializable {
    private Long id;

    private Long categoryId;

    private Date releaseTime;

    private Date addTime;

    private Boolean isDelete;

    private String title;

    private String featuredImage;

    private String describes;
}
