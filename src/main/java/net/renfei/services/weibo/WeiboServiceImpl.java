package net.renfei.services.weibo;

import net.renfei.domain.WeiboDomain;
import net.renfei.domain.weibo.Weibo;
import net.renfei.model.ListData;
import net.renfei.services.BaseService;
import net.renfei.services.WeiboService;
import net.renfei.utils.NumberUtils;
import org.springframework.scheduling.annotation.Async;
import org.springframework.stereotype.Service;

/**
 * 微博服务
 *
 * @author renfei
 */
@Service
public class WeiboServiceImpl extends BaseService implements WeiboService {
    /**
     * 获取所有微博列表
     *
     * @param page 页码
     * @param rows 每页容量
     * @return
     */
    @Override
    public ListData<Weibo> getWeiboList(String page, String rows) {
        return WeiboDomain.allWeiboList(NumberUtils.parseInt(page, 1), NumberUtils.parseInt(rows, 10));
    }

    /**
     * 增加微博浏览量
     *
     * @param weiboDomain 微博领域对象
     */
    @Async
    @Override
    public void view(WeiboDomain weiboDomain) {
        weiboDomain.view();
    }
}
