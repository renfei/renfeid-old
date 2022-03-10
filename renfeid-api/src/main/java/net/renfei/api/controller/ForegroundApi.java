package net.renfei.api.controller;

import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.tags.Tag;
import net.renfei.domain.blog.Post;
import net.renfei.domain.comment.Comment;
import net.renfei.model.APIResult;
import net.renfei.model.system.SystemTypeEnum;
import net.renfei.repositories.model.KitboxDneyesRecordLog;
import org.springframework.web.bind.annotation.*;

import java.util.List;

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

    // 暂时移除DNeyeS工具，国内云主机商不允许开放 53端口，需要境外部署
    // @PostMapping("kitbox/dneyes/subdomain")
    // @Operation(summary = "创建 DNeyeS Subdomain 子域名", description = "创建 DNeyeS Subdomain 子域名", tags = "前台接口")
    APIResult<String> generateDneyesSubdomain();

    // 暂时移除DNeyeS工具，国内云主机商不允许开放 53端口，需要境外部署
    // @PostMapping("kitbox/dneyes/{subdomain}")
    // @Operation(summary = "查询 DNeyeS Subdomain 子域名解析记录", description = "查询 DNeyeS Subdomain 子域名解析记录", tags = "前台接口")
    APIResult<List<KitboxDneyesRecordLog>> queryDneyesRecordLog(@PathVariable("subdomain") String subdomain);
}
