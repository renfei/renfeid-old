package net.renfei.services;

import net.renfei.domain.CommentDomain;
import net.renfei.domain.comment.Comment;
import net.renfei.model.system.SystemTypeEnum;
import net.renfei.domain.user.User;
import net.renfei.model.APIResult;

/**
 * @author renfei
 */
public interface CommentService {
    APIResult<CommentDomain> submit(SystemTypeEnum systemTypeEnum, Comment comment, User user);
    void sendNotify(SystemTypeEnum systemTypeEnum, Comment comment, User user);
}
