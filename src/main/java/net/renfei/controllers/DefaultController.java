package net.renfei.controllers;

import net.renfei.annotation.OperationLog;
import net.renfei.model.HomePageView;
import net.renfei.model.ListData;
import net.renfei.model.OGProtocol;
import net.renfei.model.SiteMapXml;
import net.renfei.model.system.BlogVO;
import net.renfei.model.system.SystemTypeEnum;
import net.renfei.services.BlogService;
import net.renfei.services.system.SiteMapService;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.servlet.ModelAndView;
import org.springframework.web.servlet.NoHandlerFoundException;
import org.springframework.web.servlet.view.RedirectView;

import javax.servlet.http.HttpServletResponse;
import java.util.Date;
import java.util.List;

/**
 * 网站首页
 *
 * @author renfei
 */
@Controller
@RequestMapping("/")
public class DefaultController extends BaseController {
    private final BlogService blogService;
    private final SiteMapService siteMapService;

    public DefaultController(BlogService blogService, SiteMapService siteMapService) {
        this.blogService = blogService;
        this.siteMapService = siteMapService;
    }

    /**
     * 网站首页
     *
     * @param mv
     * @return
     */
    @RequestMapping("")
    @OperationLog(module = SystemTypeEnum.HOME, desc = "访问首页")
    public ModelAndView index(ModelAndView mv) {
        ListData<BlogVO> blogDomainListData = blogService.getAllPostList(getSignUser(), false, 1, 15);
        HomePageView<ListData<BlogVO>> homePageView = buildPageView(HomePageView.class, blogDomainListData);
        assert systemConfig != null;
        homePageView.getPageHead().setTitle("任霏 - " + systemConfig.getSiteName());
        homePageView.getPageHead().setDescription("任霏的博客是任霏的个人网站与博客，一个程序员自己写的网站，不仅仅是文章内容，还包括网站程序的代码。 对新鲜事物都十分感兴趣，利用这个站点向大家分享自己的所见所得，同时这个站点也是我的实验室。");
        homePageView.getPageHead().setKeywords("任霏,博客,任霏博客,RenFei,NeilRen,技术,blog");
        homePageView.getPageFooter().setShowFriendlyLink(true);
        homePageView.getPageHead().setOgProtocol(OGProtocol.builder()
                .type("blog")
                .author("任霏")
                .description("任霏的博客是任霏的个人网站与博客，一个程序员自己写的网站，不仅仅是文章内容，还包括网站程序的代码。 对新鲜事物都十分感兴趣，利用这个站点向大家分享自己的所见所得，同时这个站点也是我的实验室。")
                .image("https://cdn.renfei.net/Logo/ogimage.png")
                .locale("zh-CN")
                .releaseDate(new Date())
                .siteName("RenFei.Net")
                .title("任霏 - " + systemConfig.getSiteName())
                .url("https://www.renfei.net")
                .build());
        mv.addObject("pageView", homePageView);
        mv.setViewName("index");
        return mv;
    }

    /**
     * 首页错误地址重定向
     *
     * @return
     */
    @RequestMapping({
            "index.html", "index.htm", "index.xhtml", "index.shtml",
            "index.php", "index.asp", "index.aspx", "index.do",
            "index.jsp", "index.dll", "index.php3", "index.pl",
            "index.cgi",
            "default.html", "default.htm", "default.xhtml", "default.shtml",
            "default.php", "default.asp", "default.aspx", "default.do",
            "default.jsp", "default.dll", "default.php3", "default.pl",
            "default.cgi"
    })
    @OperationLog(module = SystemTypeEnum.HOME, desc = "index.html")
    public RedirectView indexPage() {
        RedirectView redirectView = new RedirectView("/");
        redirectView.setStatusCode(HttpStatus.MOVED_PERMANENTLY);
        return redirectView;
    }

    /**
     * Robots 协议
     *
     * @return
     */
    @ResponseBody
    @RequestMapping(value = "robots.txt", produces = "text/plain")
    @OperationLog(module = SystemTypeEnum.HOME, desc = "robots.txt")
    public String getRobotsTxt() {
        assert systemConfig != null;
        return "#\n" +
                "# robots.txt for RENFEI.NET\n" +
                "# Version: " + systemConfig.getVersion() + "\n" +
                "# Last Build Time: " + systemConfig.getBuildTime() + "\n" +
                "#\n" +
                "\n" +
                "User-agent: *\n" +
                "\n" +
                "Disallow: /_/\n" +
                "\n" +
                "Sitemap: " + systemConfig.getSiteDomainName() + "/sitemap.xml";
    }

    /**
     * Google ADS
     *
     * @return
     * @throws NoHandlerFoundException
     */
    @ResponseBody
    @RequestMapping(value = "ads.txt", produces = "text/plain")
    @OperationLog(module = SystemTypeEnum.HOME, desc = "ads.txt")
    public String getGoogleAds() throws NoHandlerFoundException {
        String ads;
        assert systemConfig != null;
        ads = systemConfig.getGoogle().getAds();
        if (ads == null || ads.length() == 0) {
            noHandlerFoundException();
        }
        return ads;
    }

    /**
     * 站点地图 XML
     *
     * @param mv
     * @param response
     * @return
     */
    @RequestMapping(value = "sitemap.xml")
    @OperationLog(module = SystemTypeEnum.HOME, desc = "sitemap.xml")
    public ModelAndView getSiteMapXml(ModelAndView mv, HttpServletResponse response) {
        List<SiteMapXml> siteMapXmls = siteMapService.getSiteMaps();
        mv.addObject("data", siteMapXmls);
        response.setHeader("content-type", "text/xml;charset=UTF-8");
        response.setContentType("text/xml;charset=UTF-8");
        mv.setViewName("sitemap");
        return mv;
    }

    /**
     * 站点地图 XML
     *
     * @param mv
     * @param response
     * @return
     */
    @RequestMapping(value = "sitemap.xsl")
    @OperationLog(module = SystemTypeEnum.HOME, desc = "sitemap.xsl")
    public ModelAndView getSiteMapXsl(ModelAndView mv, HttpServletResponse response) {
        response.setHeader("content-type", "application/octet-stream;charset=UTF-8");
        response.setContentType("application/octet-stream;charset=UTF-8");
        mv.setViewName("sitemapxsl");
        return mv;
    }

    /**
     * Feed 订阅
     *
     * @param mv
     * @param response
     * @return
     */
    @RequestMapping(value = "feed")
    @OperationLog(module = SystemTypeEnum.HOME, desc = "feed")
    public ModelAndView getFeed(ModelAndView mv, HttpServletResponse response) {
        mv.addObject("feed", siteMapService.getFeed());
        response.setHeader("content-type", "text/xml;charset=UTF-8");
        response.setContentType("text/xml;charset=UTF-8");
        mv.setViewName("feed");
        return mv;
    }
}
