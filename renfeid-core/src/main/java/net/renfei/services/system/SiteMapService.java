package net.renfei.services.system;

import net.renfei.domain.BlogDomain;
import net.renfei.domain.blog.Category;
import net.renfei.domain.blog.Post;
import net.renfei.domain.photo.Album;
import net.renfei.domain.weibo.Weibo;
import net.renfei.model.*;
import net.renfei.model.blog.PostSidebarVO;
import net.renfei.model.search.SearchItem;
import net.renfei.services.BaseService;
import net.renfei.services.BlogService;
import net.renfei.services.RedisService;
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
    private final RedisService redisService;
    private final PhotoServiceImpl photoService;
    private final AggregateService aggregateService;

    public SiteMapService(BlogService blogService,
                          WeiboService weiboService,
                          RedisService redisService,
                          PhotoServiceImpl photoService,
                          AggregateService aggregateService) {
        this.blogService = blogService;
        this.weiboService = weiboService;
        this.redisService = redisService;
        this.photoService = photoService;
        this.aggregateService = aggregateService;
    }

    /**
     * 获取站点地图
     *
     * @return
     */
    public List<SiteMapXml> getSiteMaps() {
        List<SiteMapXml> siteMapXmls = null;
        assert systemConfig != null;
        String redisKey = REDIS_KEY + "sitemap";
        if (systemConfig.isEnableRedis()) {
            // 查询是否曾经缓存过对象，有缓存直接吐出去
            if (redisService.hasKey(redisKey)) {
                Object object = redisService.get(redisKey);
                if (object instanceof List) {
                    siteMapXmls = (List<SiteMapXml>) object;
                }
            }
        }
        if (siteMapXmls == null) {
            siteMapXmls = new ArrayList<>();
            Date lastBlogDate = new Date();
            ListData<BlogDomain> blogDomainListData = BlogDomain.allPostList(null, false, 1, 1);
            if (!ObjectUtils.isEmpty(blogDomainListData.getData()) && blogDomainListData.getData().size() > 0) {
                lastBlogDate = blogDomainListData.getData().get(0).getPost().getPostDate();
            }
            // 首页
            siteMapXmls.add(new SiteMapXml(systemConfig.getSiteDomainName(), ChangefreqEnum.daily, "1", lastBlogDate));
            // 文章
            siteMapXmls.add(new SiteMapXml(systemConfig.getSiteDomainName() + "/posts", ChangefreqEnum.daily, "1", lastBlogDate));
            // 微博
            ListData<Weibo> weiboListData = weiboService.getWeiboList("1", "1");
            if (!ObjectUtils.isEmpty(weiboListData.getData()) && weiboListData.getData().size() > 0) {
                siteMapXmls.add(new SiteMapXml(systemConfig.getSiteDomainName() + "/weibo", ChangefreqEnum.daily, "1", weiboListData.getData().get(0).getReleaseTime()));
            }
            // 相册
            ListData<Album> albumListData = photoService.getAllAlbumList("1", "1");
            if (!ObjectUtils.isEmpty(albumListData.getData()) && albumListData.getData().size() > 0) {
                siteMapXmls.add(new SiteMapXml(systemConfig.getSiteDomainName() + "/photo", ChangefreqEnum.daily, "1", albumListData.getData().get(0).getReleaseTime()));
            }
            // 工具箱
            siteMapXmls.add(new SiteMapXml(systemConfig.getSiteDomainName() + "/kitbox", ChangefreqEnum.daily, "1", new Date()));
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
        }
        if (systemConfig.isEnableRedis()) {
            redisService.set(redisKey, siteMapXmls, systemConfig.getDefaultCacheSeconds());
        }
        return siteMapXmls;
    }

    /**
     * Feed 订阅
     *
     * @return
     */
    public FeedVO getFeed() {
        FeedVO feedVO = new FeedVO();
        assert systemConfig != null;
        feedVO.setTitle(systemConfig.getSiteName());
        feedVO.setAuthor("i@renfei.net (RenFei)");
        feedVO.setLink(systemConfig.getSiteDomainName());
        feedVO.setDescription("任霏博客是任霏的个人网站与博客，一个程序员自己写的网站，不仅仅是文章内容，还包括网站程序的代码。 对新鲜事物都十分感兴趣，利用这个站点向大家分享自己的所见所得，同时这个站点也是我的实验室。");
        feedVO.setLanguage("zh-CN");
        feedVO.setImage(FeedVO.Image.builder()
                .link(systemConfig.getSiteDomainName())
                .height("32")
                .title(systemConfig.getSiteName())
                .url("https://cdn.renfei.net/Logo/RF_white.svg")
                .width("32")
                .build());
        List<FeedVO.Item> items = new ArrayList<>();
        ListData<BlogDomain> blogDomainListData = BlogDomain.allPostList(null, false, 1, 10);
        if (blogDomainListData.getData() != null && blogDomainListData.getData().size() > 0) {
            for (BlogDomain blogDomain : blogDomainListData.getData()
            ) {
                Post post = blogDomain.getPost();
                Category category = blogDomain.getCategory();
                FeedVO.Item item = new FeedVO.Item();
                item.setTitle(post.getTitle());
                item.setAuthor(post.getSourceName());
                item.setLink(systemConfig.getSiteDomainName() + "/posts/" + post.getId());
                item.setGuid(systemConfig.getSiteDomainName() + "/posts/" + post.getId());
                item.setDescription(post.getExcerpt());
                item.setCategory(category.getZhName());
                item.setPubDate(post.getPostDate());
                items.add(item);
            }
        }
        feedVO.setItem(items);
        return feedVO;
    }
}