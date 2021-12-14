package net.renfei.domain;

import lombok.Getter;
import lombok.extern.slf4j.Slf4j;
import net.renfei.config.SystemConfig;
import net.renfei.domain.comment.Comment;
import net.renfei.domain.system.SystemTypeEnum;
import net.renfei.domain.user.User;
import net.renfei.exception.BusinessException;
import net.renfei.exception.IP2LocationException;
import net.renfei.repositories.SysCommentsMapper;
import net.renfei.repositories.model.SysCommentsExample;
import net.renfei.repositories.model.SysCommentsWithBLOBs;
import net.renfei.services.IP2LocationService;
import net.renfei.services.LeafService;
import net.renfei.utils.ApplicationContextUtil;
import net.renfei.utils.SentryUtils;
import org.springframework.util.ObjectUtils;

import java.util.Date;
import java.util.List;
import java.util.concurrent.CopyOnWriteArrayList;

/**
 * 评论领域
 *
 * @author renfei
 */
@Slf4j
public final class CommentDomain {
    private final LeafService leafService;
    private final SystemConfig systemConfig;
    private final SysCommentsMapper commentsMapper;
    private final IP2LocationService ip2LocationService;
    @Getter
    private List<Comment> commentList;

    {
        leafService = (LeafService) ApplicationContextUtil.getBean("leafServiceImpl");
        systemConfig = (SystemConfig) ApplicationContextUtil.getBean("systemConfig");
        commentsMapper = (SysCommentsMapper) ApplicationContextUtil.getBean("sysCommentsMapper");
        ip2LocationService = (IP2LocationService) ApplicationContextUtil.getBean("IP2LocationServiceImpl");
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
    CommentDomain(SystemTypeEnum systemType, Long objectId, Comment comment, User user) {
        commenting(systemType, objectId, comment, user);
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

    /**
     * 评论某个对象
     *
     * @param systemType 子系统类型
     * @param objectId   被评论对象的ID
     * @param comment    评论上下文对象
     * @param user       登录用户
     */
    private void commenting(SystemTypeEnum systemType, Long objectId, Comment comment, User user) {
        if (objectId == null) {
            throw new BusinessException("objectId 不能为空");
        }
        if (ObjectUtils.isEmpty(comment.getContent())) {
            throw new BusinessException("评论内容不能为空");
        }
        if (user == null) {
            if (ObjectUtils.isEmpty(comment.getAuthor())) {
                throw new BusinessException("评论昵称不能为空");
            }
            if (ObjectUtils.isEmpty(comment.getEmail())) {
                throw new BusinessException("电子邮箱不能为空");
            }
        }
        SysCommentsWithBLOBs sysComment = convert(systemType, objectId, comment);
        commentsMapper.insertSelective(sysComment);
    }

    private Comment convert(SysCommentsWithBLOBs sysComment) {
        User user = null;
        if (sysComment.getAuthorId() != null) {
            user = new User(sysComment.getAuthorId());
        }
        Comment comment = Comment.builder()
                .id(sysComment.getId())
                .reply(sysComment.getParentId())
                .author(sysComment.getAuthor())
                .email(sysComment.getAuthorEmail())
                .link(sysComment.getAuthorUrl())
                .content(sysComment.getContent())
                .datetime(sysComment.getAddtime())
                .address(sysComment.getAuthorAddress())
                .isOwner(false)
                .ip(sysComment.getAuthorIp())
                .user(user)
                .build();
        if (user != null) {
            comment.setAuthor(user.getUserName());
            comment.setEmail(user.getEmail());
            comment.setLink(user.getWebSite());
            if (systemConfig.getOwnerUserName().equals(user.getUserName())) {
                comment.setIsOwner(true);
            }
        }
        return comment;
    }

    private SysCommentsWithBLOBs convert(SystemTypeEnum systemType, Long objectId, Comment comment) {
        User user = comment.getUser();
        SysCommentsWithBLOBs sysComment = new SysCommentsWithBLOBs();
        sysComment.setId(leafService.getId().getId());
        sysComment.setSysType(systemType.name());
        sysComment.setObjectId(objectId);
        sysComment.setAddtime(new Date());
        sysComment.setIsDelete(false);
        sysComment.setParentId(comment.getReply());
        sysComment.setAuthorIp(comment.getIp());
        try {
            sysComment.setAuthorAddress(ip2LocationService.ipQueryAddress(comment.getIp()));
        } catch (IP2LocationException e) {
            log.error(e.getMessage(), e);
            SentryUtils.captureException(e);
            sysComment.setAuthorAddress("-, -, -");
        }
        sysComment.setContent(comment.getContent());
        if (user != null) {
            sysComment.setAuthorId(user.getId());
            if (systemConfig.getOwnerUserName().equals(user.getUserName())) {
                sysComment.setIsOwner(true);
            }
            sysComment.setAuthor(user.getUserName());
            sysComment.setAuthorEmail(user.getEmail());
        } else {
            sysComment.setAuthorId(null);
            sysComment.setIsOwner(false);
            sysComment.setAuthor(comment.getAuthor());
            sysComment.setAuthorEmail(comment.getEmail());
        }
        return sysComment;
    }
}
