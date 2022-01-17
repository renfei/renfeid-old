package net.renfei.controllers.api.impl;

import net.renfei.controllers.BaseController;
import net.renfei.controllers.api.ForegroundApi;
import net.renfei.domain.CommentDomain;
import net.renfei.domain.WeiboDomain;
import net.renfei.domain.blog.Post;
import net.renfei.domain.comment.Comment;
import net.renfei.model.kitbox.ShortUrlVO;
import net.renfei.model.system.BlogVO;
import net.renfei.model.system.SystemTypeEnum;
import net.renfei.exception.NeedPasswordException;
import net.renfei.exception.NotExistException;
import net.renfei.exception.SecretLevelException;
import net.renfei.model.APIResult;
import net.renfei.model.StateCodeEnum;
import net.renfei.repositories.model.KitboxShortUrl;
import net.renfei.services.BlogService;
import net.renfei.services.CommentService;
import net.renfei.services.KitBoxService;
import net.renfei.utils.IpUtils;
import net.renfei.utils.SentryUtils;
import org.springframework.beans.BeanUtils;
import org.springframework.util.ObjectUtils;
import org.springframework.web.bind.annotation.RestController;

import static net.renfei.config.SystemConfig.*;

/**
 * 前台接口
 *
 * @author renfei
 */
@RestController
public class ForegroundApiController extends BaseController implements ForegroundApi {
    private final BlogService blogService;
    private final KitBoxService kitBoxService;
    private final CommentService commentService;

    public ForegroundApiController(BlogService blogService,
                                   KitBoxService kitBoxService,
                                   CommentService commentService) {
        this.blogService = blogService;
        this.kitBoxService = kitBoxService;
        this.commentService = commentService;
    }

    /**
     * 提交评论接口
     *
     * @param systemTypeEnum 子系统类型
     * @param id             评论目标ID
     * @param comment        评论内容
     * @return
     */
    @Override
    public APIResult submitComments(SystemTypeEnum systemTypeEnum, String id, Comment comment) {
        // TODO 检查全局评论开关
        // TODO 检查被评论的对象是否允许评论
        if (comment.getContent() != null && comment.getContent().length() >= MAX_COMMENT_LENGTH) {
            return APIResult.builder()
                    .code(StateCodeEnum.Failure)
                    .message("评论内容长度超过系统允许最大值：" + MAX_COMMENT_LENGTH)
                    .build();
        }
        if (comment.getEmail() != null && comment.getEmail().length() >= MAX_USERNAME_LENGTH) {
            return APIResult.builder()
                    .code(StateCodeEnum.Failure)
                    .message("邮箱地址长度超过系统允许最大值：" + MAX_USERNAME_LENGTH)
                    .build();
        }
        if (comment.getAuthor() != null && comment.getAuthor().length() >= MAX_USERNAME_LENGTH) {
            return APIResult.builder()
                    .code(StateCodeEnum.Failure)
                    .message("作者昵称长度超过系统允许最大值：" + MAX_USERNAME_LENGTH)
                    .build();
        }
        if (comment.getLink() != null && comment.getLink().length() >= MAX_LINK_LENGTH) {
            return APIResult.builder()
                    .code(StateCodeEnum.Failure)
                    .message("网站链接长度超过系统允许最大值：" + MAX_LINK_LENGTH)
                    .build();
        }
        Long objId;
        try {
            objId = Long.parseLong(id);
        } catch (Exception e) {
            return APIResult.builder()
                    .code(StateCodeEnum.Failure)
                    .message("Failure of parameter conversion")
                    .build();
        }
        comment.setObjectId(objId);
        comment.setIp(IpUtils.getIpAddress(request));
        APIResult<CommentDomain> apiResult = commentService.submit(systemTypeEnum, comment, getSignUser());
        // 发送通知
        commentService.sendNotify(systemTypeEnum, comment, getSignUser());
        return apiResult;
    }

    /**
     * 使用密码获取文章详情
     *
     * @param id       文章ID
     * @param password 文章密码
     * @return
     */
    @Override
    public APIResult<Post> getPostInfoByPassword(Long id, String password) {
        BlogVO blogVO;
        assert blogService != null;
        // 页面没查到缓存，去数据库查询
        try {
            blogVO = blogService.getBlogById(id, getSignUser(), password);
        } catch (NotExistException e) {
            return APIResult.builder()
                    .code(StateCodeEnum.Failure)
                    .message("文章不存在。")
                    .build();
        } catch (NeedPasswordException e) {
            return APIResult.builder()
                    .code(StateCodeEnum.Forbidden)
                    .message("文章密码不正确。")
                    .build();
        } catch (SecretLevelException e) {
            return APIResult.builder()
                    .code(StateCodeEnum.Forbidden)
                    .message("根据您的保密等级，您无权查看此内容。")
                    .build();
        }
        blogService.view(blogVO, getSignUser(), password);
        return new APIResult<>(blogVO.getPost());
    }

    @Override
    public APIResult weiboThumbsUp(Long id) {
        try {
            WeiboDomain weiboDomain = new WeiboDomain(id);
            weiboDomain.thumbsUp();
        } catch (NotExistException e) {
            SentryUtils.captureException(e);
        }
        return APIResult.success();
    }

    @Override
    public APIResult weiboThumbsDown(Long id) {
        try {
            WeiboDomain weiboDomain = new WeiboDomain(id);
            weiboDomain.thumbsDown();
        } catch (NotExistException e) {
            SentryUtils.captureException(e);
        }
        return APIResult.success();
    }

    @Override
    public APIResult addShortUrl(String url) {
        if (ObjectUtils.isEmpty(url)) {
            return APIResult.builder()
                    .code(StateCodeEnum.Failure)
                    .message("Url不合法")
                    .build();
        }
        if (url.startsWith("http://") || url.startsWith("https://")) {
            KitboxShortUrl shortUrl = kitBoxService.createShortUrl(url, getSignUser());
            if (shortUrl != null) {
                ShortUrlVO shortUrlVO = new ShortUrlVO();
                BeanUtils.copyProperties(shortUrl, shortUrlVO);
                shortUrlVO.setShortUrl(ShortUrlVO.BASE_DOMAIN + shortUrl.getShortUrl());
                return new APIResult(shortUrlVO);
            } else {
                return APIResult.builder()
                        .code(StateCodeEnum.Error)
                        .message("内部服务错误")
                        .build();
            }
        } else {
            return APIResult.builder()
                    .code(StateCodeEnum.Failure)
                    .message("Url不合法，必须以 http 或 https 开头")
                    .build();
        }
    }
}
