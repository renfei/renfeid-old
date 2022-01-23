package net.renfei.controllers.api;

import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.tags.Tag;
import net.renfei.domain.blog.Post;
import net.renfei.domain.comment.Comment;
import net.renfei.model.APIResult;
import net.renfei.model.system.SystemTypeEnum;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;

/**
 * 前台接口
 *
 * @author renfei
 */
@RequestMapping("/-/api")
@Tag(name = "前台接口", description = "前台接口")
public interface ForegroundApi {

    /**
     * 提交评论接口
     *
     * @param systemTypeEnum 子系统类型
     * @param id             评论目标ID
     * @param comment        评论内容
     * @return
     */
    @PostMapping("comments/{systemTypeEnum}/{id}")
    @Operation(summary = "提交评论接口", tags = {"前台接口"})
    APIResult submitComments(@PathVariable("systemTypeEnum") SystemTypeEnum systemTypeEnum,
                             @PathVariable("id") String id,
                             Comment comment);

    /**
     * 使用密码获取文章详情
     *
     * @param id       文章ID
     * @param password 文章密码
     * @return
     */
    @PostMapping("blog/{id}/byPassword")
    @Operation(summary = "使用密码获取文章详情", tags = {"前台接口"})
    APIResult<Post> getPostInfoByPassword(@PathVariable("id") Long id, @RequestBody String password);

    /**
     * 微博点赞接口
     *
     * @param id 微博ID
     * @return
     */
    @PostMapping("weibo/{id}/thumbsUp")
    @Operation(summary = "微博点赞接口", tags = {"前台接口"})
    APIResult weiboThumbsUp(@PathVariable("id") Long id);

    /**
     * 微博点踩接口
     *
     * @param id 微博ID
     * @return
     */
    @PostMapping("weibo/{id}/thumbsDown")
    @Operation(summary = "微博点踩接口", tags = {"前台接口"})
    APIResult weiboThumbsDown(@PathVariable("id") Long id);

    @PostMapping("kitbox/ShortURL/do")
    @Operation(summary = "新增短网址", description = "新增短网址", tags = "前台接口")
    APIResult addShortUrl(String url);
}
