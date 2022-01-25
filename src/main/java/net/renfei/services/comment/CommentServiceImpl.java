package net.renfei.services.comment;

import net.renfei.domain.BlogDomain;
import net.renfei.domain.CommentDomain;
import net.renfei.domain.comment.Comment;
import net.renfei.model.system.SystemTypeEnum;
import net.renfei.domain.user.User;
import net.renfei.exception.BusinessException;
import net.renfei.model.APIResult;
import net.renfei.services.BaseService;
import net.renfei.services.CommentService;
import net.renfei.services.EmailService;
import net.renfei.services.LeafService;
import net.renfei.services.aliyun.AliyunGreen;
import net.renfei.utils.JacksonUtil;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.scheduling.annotation.Async;
import org.springframework.stereotype.Service;

import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;

/**
 * @author renfei
 */
@Service
public class CommentServiceImpl extends BaseService implements CommentService {
    private static final Logger logger = LoggerFactory.getLogger(CommentServiceImpl.class);
    private final LeafService leafService;
    private final EmailService emailService;
    private final AliyunGreen aliyunGreen;

    public CommentServiceImpl(LeafService leafService, EmailService emailService, AliyunGreen aliyunGreen) {
        this.leafService = leafService;
        this.emailService = emailService;
        this.aliyunGreen = aliyunGreen;
    }

    @Override
    public APIResult<CommentDomain> submit(SystemTypeEnum systemTypeEnum, Comment comment, User user) {
        // 检查评论内容，包括昵称和内容
        if (aliyunGreen.textScan(comment.getAuthor())
                && aliyunGreen.textScan(comment.getContent())) {
            comment.setDatetime(new Date());
            comment.setId(leafService.getId().getId());
            CommentDomain commentDomain = new CommentDomain(systemTypeEnum, comment.getObjectId(), comment, user);
            return new APIResult<>(commentDomain);
        } else {
            logger.warn("评论未能通过机器自动审查。内容为：{}", JacksonUtil.obj2String(comment));
            throw new BusinessException("您提交的内容可能包含不健康或不和谐的内容，未能通过机器自动审查，请重试。");
        }
    }

    @Async
    @Override
    public void sendNotify(SystemTypeEnum systemTypeEnum, Comment comment, User user) {
        String sent = "", sentName = "";
        Comment parentComment = null;
        if (comment.getReply() == null || comment.getReply() == 0) {
            sent = "i@renfei.net";
            sentName = "RenFei";
        } else {
            parentComment = CommentDomain.commentById(comment.getReply());
            if (parentComment != null) {
                sent = parentComment.getEmail();
                sentName = parentComment.getAuthor();
            }

        }
        List<String> stringList = new ArrayList<>();
        stringList.add("很高兴能与你取得联系。您的评论留言收到了回复：");
        stringList.add(comment.getContent());
        stringList.add("----");
        assert systemConfig != null;
        String link = systemConfig.getSiteDomainName();
        link += systemTypeEnum.getUriPath() + "/" + comment.getObjectId();
        switch (systemTypeEnum) {
            case BLOG:
                BlogDomain blogDomain;
                try {
                    blogDomain = new BlogDomain(comment.getObjectId(), user, null, false);
                    stringList.add("回顾：<a href=\"" + link + "\">" + blogDomain.getPost().getTitle() + "</a>");
                } catch (Exception ignored) {
                }
                break;
            default:
                return;
        }
        if (parentComment != null) {
            SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd");
            stringList.add("在 " + sdf.format(parentComment.getDatetime()) + " 时您写到：");
            stringList.add(parentComment.getContent());
        }
        stringList.add("Visit Topic to respond.");
        emailService.send(sent, sentName, "您在[RENFEI.NET]的评论留言有新的回复消息", stringList);
    }
}
