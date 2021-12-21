package net.renfei.services;

import net.renfei.model.FeedVO;
import net.renfei.model.SiteMapXml;
import net.renfei.model.system.RegionVO;

import java.util.List;

/**
 * 系统基础服务
 *
 * @author renfei
 */
public interface SysService {
    /**
     * 获取行政代码
     *
     * @param regionCode 行政代码
     * @return 下一级行政代码列表
     */
    List<RegionVO> getRegion(String regionCode);

    /**
     * 获取站点地图
     *
     * @return
     */
    List<SiteMapXml> getSiteMaps();

    /**
     * Feed 订阅
     *
     * @return
     */
    FeedVO getFeed();
}
