package net.renfei.domain.pages;

import lombok.*;
import net.renfei.model.SecretLevelEnum;
import net.renfei.model.blog.PostStatusEnum;

import java.io.Serializable;
import java.util.Date;

/**
 * @author renfei
 */
@Data
@Builder
@NoArgsConstructor(access = AccessLevel.PUBLIC)
@AllArgsConstructor(access = AccessLevel.PRIVATE)
public class Page implements Serializable {
    private Long id;

    private Long pageAuthor;

    private Date pageDate;

    private PostStatusEnum pageStatus;

    private Long pageViews;

    private String pagePassword;

    private Date pageModified;

    private Long pageParent;

    private Long thumbsUp;

    private Long thumbsDown;

    private SecretLevelEnum secretLevel;

    private String featuredImage;

    private String pageTitle;

    private String pageKeyword;

    private String pageExcerpt;

    private String pageContent;
}
