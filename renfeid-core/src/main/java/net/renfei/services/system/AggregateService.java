package net.renfei.services.system;

import net.renfei.discuz.repositories.entity.DiscuzForumPostDO;
import net.renfei.discuz.service.DiscuzService;
import net.renfei.domain.BlogDomain;
import net.renfei.domain.photo.Album;
import net.renfei.domain.weibo.Weibo;
import net.renfei.model.LinkTree;
import net.renfei.model.ListData;
import net.renfei.model.kitbox.KitBoxMenus;
import net.renfei.model.search.SearchItem;
import net.renfei.model.search.TypeEnum;
import net.renfei.repositories.model.SysPagesWithBLOBs;
import net.renfei.services.BaseService;
import net.renfei.services.KitBoxService;
import net.renfei.services.PageService;
import net.renfei.services.WeiboService;
import net.renfei.services.photo.PhotoServiceImpl;
import net.renfei.utils.StringUtils;
import org.springframework.stereotype.Service;
import org.springframework.util.ObjectUtils;

import java.util.ArrayList;
import java.util.Date;
import java.util.List;
import java.util.UUID;

/**
 * 聚合服务 所有数据
 *
 * @author renfei
 */
@Service
public class AggregateService extends BaseService {
    private final PageService pageService;
    private final WeiboService weiboService;
    private final KitBoxService kitBoxService;
    private final DiscuzService discuzService;
    private final PhotoServiceImpl photoService;

    public AggregateService(PageService pageService,
                            WeiboService weiboService,
                            KitBoxService kitBoxService,
                            DiscuzService discuzService,
                            PhotoServiceImpl photoService) {
        this.pageService = pageService;
        this.weiboService = weiboService;
        this.kitBoxService = kitBoxService;
        this.discuzService = discuzService;
        this.photoService = photoService;
    }

    /**
     * 获取全部数据（本站）
     *
     * @return
     */
    public List<SearchItem> getAllDataBySearchItemSite() {
        assert systemConfig != null;
        List<SearchItem> searchItemAll = new ArrayList<>();
        // == Post >>>>
        ListData<BlogDomain> blogDomainListData = BlogDomain.allPostList(null, false, 1, Integer.MAX_VALUE);
        if (!ObjectUtils.isEmpty(blogDomainListData.getData()) && blogDomainListData.getData().size() > 0) {
            for (BlogDomain blog : blogDomainListData.getData()
            ) {
                SearchItem searchItem = new SearchItem();
                searchItem.setUuid(UUID.randomUUID().toString().toUpperCase());
                searchItem.setType(TypeEnum.POSTS.getName());
                searchItem.setTitle(blog.getPost().getTitle());
                searchItem.setContent(StringUtils.delHtmlTags(blog.getPost().getContent()));
                searchItem.setImage(getImgUrl(blog.getPost().getFeaturedImage()));
                searchItem.setUrl(systemConfig.getSiteDomainName() + TypeEnum.POSTS.getUrl() + "/" + blog.getPost().getId());
                searchItem.setOriginalId(blog.getPost().getId());
                searchItem.setDate(blog.getPost().getPostDate());
                searchItemAll.add(searchItem);
            }
        }
        // == Posts <<<< Pages >>>>
        List<SysPagesWithBLOBs> pageAll = pageService.getAllPageNotCache();
        if (!ObjectUtils.isEmpty(pageAll)) {
            for (SysPagesWithBLOBs page : pageAll
            ) {
                SearchItem searchItem = new SearchItem();
                searchItem.setUuid(UUID.randomUUID().toString().toUpperCase());
                searchItem.setType(TypeEnum.PAGES.getName());
                searchItem.setTitle(page.getPageTitle());
                searchItem.setContent(StringUtils.delHtmlTags(page.getPageContent()));
                searchItem.setImage(getImgUrl(page.getFeaturedImage()));
                searchItem.setUrl(systemConfig.getSiteDomainName() + TypeEnum.PAGES.getUrl() + "/" + page.getId());
                searchItem.setOriginalId(page.getId());
                searchItem.setDate(page.getPageDate());
                searchItemAll.add(searchItem);
            }
        }
        // == Pages <<<< Video >>>>
        // == Video <<<< Photo >>>>
        ListData<Album> albumListData = photoService.getAllAlbumList("1", "999999999");
        if (!ObjectUtils.isEmpty(albumListData.getData()) && albumListData.getData().size() > 0) {
            for (Album album : albumListData.getData()
            ) {
                SearchItem searchItem = new SearchItem();
                searchItem.setUuid(UUID.randomUUID().toString().toUpperCase());
                searchItem.setType(TypeEnum.PHOTO.getName());
                searchItem.setTitle(album.getTitle());
                searchItem.setContent(StringUtils.delHtmlTags(album.getDescribes()));
                searchItem.setImage(getImgUrl(album.getFeaturedImage()));
                searchItem.setUrl(systemConfig.getSiteDomainName() + TypeEnum.PHOTO.getUrl() + "/" + album.getId());
                searchItem.setOriginalId(album.getId());
                searchItem.setDate(album.getReleaseTime());
                searchItemAll.add(searchItem);
            }
        }
        // == Photo <<<< Weibo >>>>
        ListData<Weibo> weiboListData = weiboService.getWeiboList("1", "999999999");
        if (!ObjectUtils.isEmpty(weiboListData.getData()) && weiboListData.getData().size() > 0) {
            for (Weibo weibo : weiboListData.getData()
            ) {
                SearchItem searchItem = new SearchItem();
                searchItem.setUuid(UUID.randomUUID().toString().toUpperCase());
                searchItem.setType(TypeEnum.WEIBO.getName());
                searchItem.setTitle(StringUtils.delHtmlTags(weibo.getContent()));
                searchItem.setContent(StringUtils.delHtmlTags(weibo.getContent()));
                searchItem.setImage(getImgUrl(""));
                searchItem.setUrl(systemConfig.getSiteDomainName() + TypeEnum.WEIBO.getUrl() + "/" + weibo.getId());
                searchItem.setOriginalId(weibo.getId());
                searchItem.setDate(weibo.getReleaseTime());
                searchItemAll.add(searchItem);
            }
        }
        // == Weibo <<<< KitBox >>>>
        List<KitBoxMenus> kitBoxs = kitBoxService.getKitBoxMenus();
        if (!ObjectUtils.isEmpty(kitBoxs)) {
            for (KitBoxMenus kitBox : kitBoxs
            ) {
                if (!ObjectUtils.isEmpty(kitBox.getLinks())) {
                    for (LinkTree link : kitBox.getLinks()
                    ) {
                        SearchItem searchItem = new SearchItem();
                        searchItem.setUuid(UUID.randomUUID().toString().toUpperCase());
                        searchItem.setType(TypeEnum.KITBOX.getName());
                        searchItem.setTitle(link.getText());
                        searchItem.setContent(link.getRel());
                        searchItem.setImage(getImgUrl(""));
                        searchItem.setUrl(link.getHref());
                        searchItem.setOriginalId(0L);
                        searchItem.setDate(new Date());
                        searchItemAll.add(searchItem);
                    }
                }
            }
        }
        return searchItemAll;
    }

    /**
     * 获取全部数据（包含子站点）
     *
     * @return
     */
    public List<SearchItem> getAllDataBySearchItem() {
        List<SearchItem> searchItemAll = getAllDataBySearchItemSite();
        if (searchItemAll == null) {
            searchItemAll = new ArrayList<>();
        }
        // == Discuz >>>>
        List<DiscuzForumPostDO> discuzForumPostList = discuzService.getAllPost();
        if (!ObjectUtils.isEmpty(discuzForumPostList)) {
            for (DiscuzForumPostDO post : discuzForumPostList
            ) {
                SearchItem searchItem = new SearchItem();
                searchItem.setUuid(UUID.randomUUID().toString().toUpperCase());
                searchItem.setType(TypeEnum.DISCUZ.getName());
                searchItem.setTitle(post.getSubject());
                searchItem.setContent(StringUtils.delHtmlTags(post.getMessage()));
                searchItem.setImage(getImgUrl(null));
                searchItem.setUrl(TypeEnum.DISCUZ.getUrl() + "/thread-" + post.getTid() + "-1-1.html");
                searchItem.setOriginalId(Long.valueOf(post.getTid()));
                searchItem.setDate(new Date((long) post.getDateline() * 1000));
                searchItemAll.add(searchItem);
            }
        }
        // == Discuz <<<<
        return searchItemAll;
    }

    private String getImgUrl(String url) {
        if (ObjectUtils.isEmpty(url)) {
            return "https://cdn.renfei.net/images/default_posts.jpg";
        }
        return url;
    }
}
