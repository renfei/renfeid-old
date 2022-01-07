package net.renfei.domain.weibo;

import lombok.*;

import java.io.Serializable;
import java.util.Date;

@Data
@Builder
@NoArgsConstructor(access = AccessLevel.PUBLIC)
@AllArgsConstructor(access = AccessLevel.PRIVATE)
public class Weibo implements Serializable {
    private Long id;
    private String content;
    private Date releaseTime;
    private Long views;
    private Long thumbsUp;
    private Long thumbsDown;
    private String image;
}
