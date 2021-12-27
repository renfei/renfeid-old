package net.renfei.domain.blog;

import lombok.Builder;
import lombok.Data;
import net.renfei.model.CommentStatusEnum;
import net.renfei.model.PostStatusEnum;
import net.renfei.model.SecretLevelEnum;

import java.util.Date;

/**
 * @author renfei
 */
@Data
@Builder
public class Post {
    private Long id;
    private String title;
    private String keyword;
    private String excerpt;
    private String content;
    private String featuredImage;
    private Boolean isOriginal;
    private String sourceName;
    private String sourceUrl;
    private Date postDate;
    private Long postAuthor;
    private Long categoryId;
    private PostStatusEnum postStatus;
    private Long postViews;
    private CommentStatusEnum commentStatusenum;
    private String postPassword;
    private Date postModified;
    private Long postParent;
    private Long thumbsUp;
    private Long thumbsDown;
    private Double avgViews;
    private Double avgComment;
    private Double pageRank;
    private SecretLevelEnum secretLevelEnum;
}
