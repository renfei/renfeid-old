package net.renfei.domain;

import com.github.pagehelper.Page;
import com.github.pagehelper.PageHelper;
import net.renfei.config.SystemConfig;
import net.renfei.domain.comment.Comment;
import net.renfei.model.system.SystemTypeEnum;
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
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.util.ObjectUtils;

import java.util.Date;
import java.util.List;
import java.util.concurrent.CopyOnWriteArrayList;

/**
 * 评论领域
 *
 * @author renfei
 */
public final class CommentDomain {
    private static final Logger logger = LoggerFactory.getLogger(CommentDomain.class);
    private final LeafService leafService;
    private final SystemConfig systemConfig;
    private final SysCommentsMapper commentsMapper;
    private final IP2LocationService ip2LocationService;
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
    public CommentDomain() {
    }

    /**
     * 实例化
     *
     * @param systemType 子系统类型
     * @param objectId   被评论对象的ID
     */
    public CommentDomain(SystemTypeEnum systemType, Long objectId) {
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
    public CommentDomain(SystemTypeEnum systemType, Long objectId, Comment comment, User user) {
        commenting(systemType, objectId, comment, user);
        buildCommentList(systemType, objectId);
    }

    public static Comment commentById(long id) {
        CommentDomain commentDomain = new CommentDomain();
        return commentDomain.getCommentById(id);
    }

    private Comment getCommentById(Long id) {
        return convert(commentsMapper.selectByPrimaryKey(id));
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
     * 最新的10个评论
     *
     * @param systemType 子系统类型
     * @return
     */
    public static List<Comment> lastCommentTop10(SystemTypeEnum systemType) {
        CommentDomain commentDomain = new CommentDomain();
        return commentDomain.getLastCommentTop10(systemType);
    }

    /**
     * 最新的10个评论
     *
     * @param systemType 子系统类型
     * @return
     */
    private List<Comment> getLastCommentTop10(SystemTypeEnum systemType) {
        SysCommentsExample example = new SysCommentsExample();
        example.setOrderByClause("addtime DESC");
        example.createCriteria()
                .andSysTypeEqualTo(systemType.toString())
                .andIsDeleteEqualTo(false);
        Page<SysCommentsWithBLOBs> page = PageHelper.startPage(1, 10);
        commentsMapper.selectByExampleWithBLOBs(example);
        List<SysCommentsWithBLOBs> sysComments = page.getResult();
        if (!ObjectUtils.isEmpty(sysComments)) {
            List<Comment> commentList = new CopyOnWriteArrayList<>();
            for (SysCommentsWithBLOBs sysComment : sysComments
            ) {
                Comment comment = convert(sysComment);
                commentList.add(comment);
            }
            return commentList;
        }
        return null;
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
        example.setOrderByClause("addtime DESC");
        SysCommentsExample.Criteria criteria = example.createCriteria();
        criteria.andIsDeleteEqualTo(false)
                .andSysTypeEqualTo(systemType.name())
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
        } else {
            comment.setUser(user);
        }
        SysCommentsWithBLOBs sysComment = convert(systemType, objectId, comment);
        commentsMapper.insertSelective(sysComment);
    }

    private Comment convert(SysCommentsWithBLOBs sysComment) {
        User user = null;
        if (sysComment.getAuthorId() != null) {
            user = new User(sysComment.getAuthorId());
        }
        Comment comment = new Comment();
        comment.setId(sysComment.getId());
        comment.setObjectId(sysComment.getObjectId());
        comment.setReply(sysComment.getParentId());
        comment.setAuthor(sysComment.getAuthor());
        comment.setEmail(sysComment.getAuthorEmail());
        comment.setLink(sysComment.getAuthorUrl());
        comment.setContent(sysComment.getContent());
        comment.setDatetime(sysComment.getAddtime());
        comment.setAddress(sysComment.getAuthorAddress());
        comment.setOwner(false);
        comment.setIp(sysComment.getAuthorIp());
        comment.setUser(user);
        if (user != null) {
            comment.setAuthor(user.getUserName());
            comment.setEmail(user.getEmail());
            comment.setLink(user.getWebSite());
            if (systemConfig.getOwnerUserName().equals(user.getUserName())) {
                comment.setOwner(true);
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
            logger.error(e.getMessage(), e);
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

    public List<Comment> getCommentList() {
        return commentList;
    }
}
