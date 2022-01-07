package net.renfei.services.system;

import net.renfei.domain.BlogDomain;
import net.renfei.domain.photo.Album;
import net.renfei.domain.weibo.Weibo;
import net.renfei.model.*;
import net.renfei.model.blog.PostSidebarVO;
import net.renfei.model.search.SearchItem;
import net.renfei.services.BaseService;
import net.renfei.services.BlogService;
import net.renfei.services.WeiboService;
import net.renfei.services.photo.PhotoServiceImpl;
import org.springframework.stereotype.Service;
import org.springframework.util.ObjectUtils;

import java.util.ArrayList;
import java.util.Date;
import java.util.List;

/**
 * 站点地图服务
 *
 * @author renfei
 */
@Service
public class SiteMapService extends BaseService {
    private final BlogService blogService;
    private final WeiboService weiboService;
    private final PhotoServiceImpl photoService;
    private final AggregateService aggregateService;

    public SiteMapService(BlogService blogService, WeiboService weiboService, PhotoServiceImpl photoService, AggregateService aggregateService) {
        this.blogService = blogService;
        this.weiboService = weiboService;
        this.photoService = photoService;
        this.aggregateService = aggregateService;
    }

    /**
     * 获取站点地图
     *
     * @return
     */
    public List<SiteMapXml> getSiteMaps() {
        assert SYSTEM_CONFIG != null;
        Date lastBlogDate = new Date();
        ListData<BlogDomain> blogDomainListData = BlogDomain.allPostList(null, false, 1, 1);
        if (!ObjectUtils.isEmpty(blogDomainListData.getData()) && blogDomainListData.getData().size() > 0) {
            lastBlogDate = blogDomainListData.getData().get(0).getPost().getPostDate();
        }
        List<SiteMapXml> siteMapXmls = new ArrayList<>();
        // 首页
        siteMapXmls.add(new SiteMapXml(SYSTEM_CONFIG.getSiteDomainName(), ChangefreqEnum.daily, "1", lastBlogDate));
        // 文章
        siteMapXmls.add(new SiteMapXml(SYSTEM_CONFIG.getSiteDomainName() + "/posts", ChangefreqEnum.daily, "1", lastBlogDate));
        // 微博
        ListData<Weibo> weiboListData = weiboService.getWeiboList("1", "1");
        if (!ObjectUtils.isEmpty(weiboListData.getData()) && weiboListData.getData().size() > 0) {
            siteMapXmls.add(new SiteMapXml(SYSTEM_CONFIG.getSiteDomainName() + "/weibo", ChangefreqEnum.daily, "1", weiboListData.getData().get(0).getReleaseTime()));
        }
        // 相册
        ListData<Album> albumListData = photoService.getAllAlbumList("1", "1");
        if (!ObjectUtils.isEmpty(albumListData.getData()) && albumListData.getData().size() > 0) {
            siteMapXmls.add(new SiteMapXml(SYSTEM_CONFIG.getSiteDomainName() + "/photo", ChangefreqEnum.daily, "1", albumListData.getData().get(0).getReleaseTime()));
        }
        // 工具箱
        siteMapXmls.add(new SiteMapXml(SYSTEM_CONFIG.getSiteDomainName() + "/kitbox", ChangefreqEnum.daily, "1", new Date()));
        List<SearchItem> searchItems = aggregateService.getAllDataBySearchItemSite();
        if (ObjectUtils.isEmpty(searchItems)) {
            return siteMapXmls;
        }
        for (SearchItem item : searchItems
        ) {
            siteMapXmls.add(new SiteMapXml(item.getUrl(), ChangefreqEnum.daily, "0.9", item.getDate()));
        }
        // 博客的分类和标签
        PostSidebarVO postSidebarVO = blogService.buildPostSidebar(null);
        for (PostSidebarVO.PostSidebar postSidebar : postSidebarVO.getPostSidebars()
        ) {
            if ("标签云".equals(postSidebar.getTitle())) {
                for (LinkTree link : postSidebar.getLink()
                ) {
                    siteMapXmls.add(new SiteMapXml(link.getHref(), ChangefreqEnum.daily, "0.8", lastBlogDate));
                }
            }
            if ("文档分类".equals(postSidebar.getTitle())) {
                for (LinkTree link : postSidebar.getLink()
                ) {
                    siteMapXmls.add(new SiteMapXml(link.getHref(), ChangefreqEnum.daily, "0.8", lastBlogDate));
                }
            }
        }
        return siteMapXmls;
    }

    /**
     * Feed 订阅
     *
     * @return
     */
    public FeedVO getFeed() {
        // TODO 待补充
        return null;
    }
}
