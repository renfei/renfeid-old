package net.renfei.controllers;

import net.renfei.domain.BlogDomain;
import net.renfei.model.HomePageView;
import net.renfei.model.ListData;
import net.renfei.model.OGProtocol;
import net.renfei.model.SiteMapXml;
import net.renfei.services.SysService;
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
    private final SysService sysService;

    public DefaultController(SysService sysService) {
        this.sysService = sysService;
    }

    /**
     * 网站首页
     *
     * @param mv
     * @return
     */
    @RequestMapping("")
    public ModelAndView index(ModelAndView mv) {
        ListData<BlogDomain> blogDomainListData = BlogDomain.allPostList(getSignUser(), false, 1, 15);
        HomePageView<ListData<BlogDomain>> homePageView = buildPageView(HomePageView.class, blogDomainListData);
        assert SYSTEM_CONFIG != null;
        homePageView.getPageHead().setTitle("任霏 - " + SYSTEM_CONFIG.getSiteName());
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
                .title("任霏 - " + SYSTEM_CONFIG.getSiteName())
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
    public String getRobotsTxt() {
        assert SYSTEM_CONFIG != null;
        return "#\n" +
                "# robots.txt for RENFEI.NET\n" +
                "# Version: " + SYSTEM_CONFIG.getVersion() + "\n" +
                "# Last Build Time: " + SYSTEM_CONFIG.getBuildTime() + "\n" +
                "#\n" +
                "\n" +
                "User-agent: *\n" +
                "\n" +
                "Disallow: /private/\n" +
                "\n" +
                "Sitemap: " + SYSTEM_CONFIG.getSiteDomainName() + "/sitemap.xml";
    }

    /**
     * Google ADS
     *
     * @return
     * @throws NoHandlerFoundException
     */
    @ResponseBody
    @RequestMapping(value = "ads.txt", produces = "text/plain")
    public String getGoogleAds() throws NoHandlerFoundException {
        String ads;
        assert SYSTEM_CONFIG != null;
        ads = SYSTEM_CONFIG.getGoogle().getAds();
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
    public ModelAndView getSiteMapXml(ModelAndView mv, HttpServletResponse response) {
        List<SiteMapXml> siteMapXmls = sysService.getSiteMaps();
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
    public ModelAndView getFeed(ModelAndView mv, HttpServletResponse response) {
        mv.addObject("feed", sysService.getFeed());
        response.setHeader("content-type", "text/xml;charset=UTF-8");
        response.setContentType("text/xml;charset=UTF-8");
        mv.setViewName("feed");
        return mv;
    }
}
