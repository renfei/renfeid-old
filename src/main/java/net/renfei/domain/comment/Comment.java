package net.renfei.domain.comment;

import lombok.Data;
import net.renfei.domain.user.User;

import java.util.Date;
import java.util.List;

/**
 * 评论域实体类
 *
 * @author renfei
 */
@Data
public final class Comment {
    private Long id;
    private Long reply;
    private String author;
    private String email;
    private String link;
    private String content;
    private Date datetime;
    private String address;
    private Boolean isOwner;
    private User user;
    private List<Comment> child;
}
