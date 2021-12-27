package net.renfei.domain.weibo;

import lombok.Builder;
import lombok.Data;

import java.util.Date;

@Data
@Builder
public class Weibo {
    private Long id;
    private String content;
    private Date releaseTime;
    private Long views;
    private Long thumbsUp;
    private Long thumbsDown;
    private String image;
}
