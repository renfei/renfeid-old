package net.renfei.services;

import net.renfei.domain.WeiboDomain;
import net.renfei.domain.weibo.Weibo;
import net.renfei.model.ListData;

/**
 * 微博服务
 *
 * @author renfei
 */
public interface WeiboService {
    /**
     * 获取所有微博列表
     *
     * @param page 页码
     * @param rows 每页容量
     * @return
     */
    ListData<Weibo> getWeiboList(String page, String rows);

    /**
     * 增加微博浏览量
     *
     * @param weiboDomain 微博领域对象
     */
    void view(WeiboDomain weiboDomain);
}
