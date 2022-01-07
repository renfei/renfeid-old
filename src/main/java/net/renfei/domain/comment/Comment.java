package net.renfei.domain.comment;

import lombok.*;
import lombok.experimental.Tolerate;
import net.renfei.domain.user.User;

import java.io.Serializable;
import java.util.Date;
import java.util.List;

/**
 * 评论域实体类
 *
 * @author renfei
 */
@Data
@Builder
@NoArgsConstructor(access = AccessLevel.PUBLIC)
@AllArgsConstructor(access = AccessLevel.PRIVATE)
public final class Comment implements Serializable {
    private Long id;
    private Long objectId;
    private Long reply;
    private String author;
    private String email;
    private String link;
    private String content;
    private Date datetime;
    private String address;
    private String ip;
    private Boolean isOwner;
    private User user;
    private List<Comment> child;
}
