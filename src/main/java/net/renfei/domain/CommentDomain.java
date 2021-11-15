package net.renfei.domain;

import lombok.Getter;
import net.renfei.domain.comment.Comment;
import net.renfei.domain.system.SystemTypeEnum;
import net.renfei.repositories.SysCommentsMapper;
import net.renfei.repositories.model.SysCommentsExample;
import net.renfei.repositories.model.SysCommentsWithBLOBs;
import net.renfei.utils.ApplicationContextUtil;
import org.springframework.util.ObjectUtils;

import java.util.List;
import java.util.concurrent.CopyOnWriteArrayList;

/**
 * 评论领域
 *
 * @author renfei
 */
public final class CommentDomain {
    private final SysCommentsMapper commentsMapper;
    @Getter
    private List<Comment> commentList;

    {
        commentsMapper = (SysCommentsMapper) ApplicationContextUtil.getBean("sysCommentsMapper");
    }

    /**
     * 禁止直接实例化
     */
    private CommentDomain() {
    }

    /**
     * 实例化
     *
     * @param systemType 子系统类型
     * @param objectId   被评论对象的ID
     */
    CommentDomain(SystemTypeEnum systemType, Long objectId) {
        buildCommentList(systemType, objectId);
    }

    /**
     * 实例化
     * 添加评论并返回新的评论列表树
     *
     * @param systemType 子系统类型
     * @param objectId   被评论对象的ID
     * @param comment    评论上下文内容
     */
    CommentDomain(SystemTypeEnum systemType, Long objectId, Comment comment) {
        buildCommentList(systemType, objectId);
    }

    /**
     * 构建评论列表树
     *
     * @param systemType 子系统类型
     * @param objectId   被评论对象的ID
     */
    private void buildCommentList(SystemTypeEnum systemType, Long objectId) {
        this.commentList = buildCommentList(systemType, objectId, null);
    }

    /**
     * 递归构建评论列表树
     *
     * @param systemType 子系统类型
     * @param objectId   被评论对象的ID
     * @param reply      被评论父对象
     * @return 评论列表树
     */
    private List<Comment> buildCommentList(SystemTypeEnum systemType, Long objectId, Comment reply) {
        SysCommentsExample example = new SysCommentsExample();
        SysCommentsExample.Criteria criteria = example.createCriteria();
        criteria.andSysTypeEqualTo(systemType.name())
                .andObjectIdEqualTo(objectId);
        if (reply == null) {
            criteria.andParentIdIsNull();
        } else {
            criteria.andParentIdEqualTo(reply.getId());
        }
        List<SysCommentsWithBLOBs> sysComments = commentsMapper.selectByExampleWithBLOBs(example);
        if (!ObjectUtils.isEmpty(sysComments)) {
            List<Comment> commentList = new CopyOnWriteArrayList<>();
            for (SysCommentsWithBLOBs sysComment : sysComments
            ) {
                Comment comment = convert(sysComment);
                assert comment != null;
                comment.setChild(buildCommentList(systemType, objectId, comment));
                commentList.add(comment);
            }
            return commentList;
        }
        return null;
    }

    private Comment convert(SysCommentsWithBLOBs sysComment) {
        // TODO
        return null;
    }
}
