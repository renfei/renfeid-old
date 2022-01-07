package net.renfei.model.system;

import lombok.Data;
import net.renfei.domain.blog.Category;
import net.renfei.domain.blog.Post;
import net.renfei.domain.comment.Comment;
import net.renfei.domain.user.User;

import java.util.List;

/**
 * @author renfei
 */
@Data
public class BlogVO {
    private Post post;
    private User author;
    private Category category;
    private List<Comment> commentList;
}
