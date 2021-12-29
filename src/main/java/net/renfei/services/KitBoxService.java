package net.renfei.services;

import net.renfei.domain.comment.Comment;
import net.renfei.domain.kitbox.KitBoxTypeEnum;
import net.renfei.model.APIResult;
import net.renfei.model.DnsTypeEnum;
import net.renfei.model.kitbox.KitBoxMenus;

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
}
