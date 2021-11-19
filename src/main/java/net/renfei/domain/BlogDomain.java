package net.renfei.domain;

import net.renfei.domain.comment.Comment;
import net.renfei.domain.system.SystemTypeEnum;

import java.util.Date;
import java.util.List;

/**
 * 博客领域的聚合根
 * 与博客相关的领域对象都在这里
 *
 * @author renfei
 */
public final class BlogDomain {
    private final List<Comment> commentList;

    public BlogDomain(Long id) {
        commentList = new CommentDomain(SystemTypeEnum.BLOG, id).getCommentList();
    }
}
