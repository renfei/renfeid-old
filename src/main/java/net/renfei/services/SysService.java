package net.renfei.services;

import com.aliyun.oss.ServiceException;
import net.renfei.model.FeedVO;
import net.renfei.model.LinkTree;
import net.renfei.model.ReportPublicKeyVO;
import net.renfei.model.SiteMapXml;
import net.renfei.model.system.RegionVO;

import java.io.IOException;
import java.util.List;
import java.util.Map;

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
     * 获取网站友情链接列表
     *
     * @return
     */
    List<LinkTree> getSysSiteFriendlyLinkList();

    /**
     * 【危险】在服务器主机上执行命令
     *
     * @param cmd 命令
     * @return
     * @throws IOException
     */
    String execCmd(String cmd) throws IOException;

    /**
     * 【危险】在服务器主机上执行命令
     *
     * @param cmd 命令
     * @return
     * @throws IOException
     */
    String execCmd(String[] cmd) throws IOException;

    Map<Integer, String> secretKey();

    Map<String, String> setSecretKey(ReportPublicKeyVO reportPublicKeyVO) throws ServiceException;
}
