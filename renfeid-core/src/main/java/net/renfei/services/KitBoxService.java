package net.renfei.services;

import net.renfei.domain.comment.Comment;
import net.renfei.domain.kitbox.KitBoxTypeEnum;
import net.renfei.domain.user.User;
import net.renfei.model.APIResult;
import net.renfei.model.DnsTypeEnum;
import net.renfei.model.kitbox.IcpQueryVo;
import net.renfei.model.kitbox.KitBoxMenus;
import net.renfei.repositories.model.KitboxDneyesRecordLog;
import net.renfei.repositories.model.KitboxShortUrl;

import javax.servlet.http.HttpServletRequest;
import java.util.List;

/**
 * 工具箱服务
 *
 * @author renfei
 */
public interface KitBoxService {
    /**
     * 获取工具箱左侧菜单
     *
     * @return
     */
    List<KitBoxMenus> getKitBoxMenus();

    /**
     * 获取工具箱左侧菜单，包含展开状态
     *
     * @param key 应当展开的菜单ID
     * @return
     */
    List<KitBoxMenus> getKitBoxMenus(String key);

    /**
     * 获取工具的评论
     *
     * @param kitBoxTypeEnum 菜单类型
     * @return
     */
    List<Comment> getCommentList(KitBoxTypeEnum kitBoxTypeEnum);

    APIResult<String> execDigTrace(String domain, DnsTypeEnum dnsTypeEnum);

    APIResult<String> execWhois(String domain);

    /**
     * 获取ShortUrl对象
     *
     * @param shortUrl 短网址
     * @return
     */
    KitboxShortUrl getShortUrl(String shortUrl);

    /**
     * 添加短网址
     *
     * @param url
     * @param user
     * @return
     */
    KitboxShortUrl createShortUrl(String url, User user);

    void updateShortUrl(KitboxShortUrl shortUrl);

    IcpQueryVo.IcpInfo queryIcpInfo(String domain, Boolean refresh);

    /**
     * 生成一个 DNeyeS 子域名
     *
     * @param user    登陆用户
     * @param request 请求对象
     * @return
     */
    String generateDneyesSubdomain(User user, HttpServletRequest request);

    /**
     * 查询 DNeyeS 子域名解析记录
     *
     * @param subdomain 子域名
     * @param user      登陆用户
     * @return
     */
    List<KitboxDneyesRecordLog> queryDneyesRecordLog(String subdomain, User user);
}
