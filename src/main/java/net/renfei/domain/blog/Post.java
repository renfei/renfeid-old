package net.renfei.domain.blog;

import lombok.Builder;
import lombok.Data;
import net.renfei.model.CommentStatus;
import net.renfei.model.PostStatus;
import net.renfei.model.SecretLevel;

import java.util.Date;

/**
 * @author renfei
 */
@Data
@Builder
public class Post {
    private Long id;
    private Date postDate;
    private Long postAuthor;
    private Long categoryId;
    private PostStatus postStatus;
    private Long postViews;
    private CommentStatus commentStatus;
    private String postPassword;
    private Date postModified;
    private Long postParent;
    private Long thumbsUp;
    private Long thumbsDown;
    private Double avgViews;
    private Double avgComment;
    private Double pageRank;
    private SecretLevel secretLevel;
}
