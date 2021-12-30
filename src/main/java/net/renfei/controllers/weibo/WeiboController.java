package net.renfei.controllers.weibo;

import lombok.extern.slf4j.Slf4j;
import net.renfei.controllers.BaseController;
import net.renfei.domain.WeiboDomain;
import net.renfei.domain.weibo.Weibo;
import net.renfei.exception.NotExistException;
import net.renfei.model.ListData;
import net.renfei.model.OGProtocol;
import net.renfei.model.weibo.WeiboPageView;
import net.renfei.services.PaginationService;
import net.renfei.services.WeiboService;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.servlet.ModelAndView;
import org.springframework.web.servlet.NoHandlerFoundException;
import org.springframework.web.servlet.view.RedirectView;

/**
 * 微博栏目
 *
 * @author renfei
 */
@Slf4j
@Controller
@RequestMapping("/weibo")
public class WeiboController extends BaseController {
    private final WeiboService weiboService;
    private final PaginationService paginationService;

    public WeiboController(WeiboService weiboService, PaginationService paginationService) {
        this.weiboService = weiboService;
        this.paginationService = paginationService;
    }

    @RequestMapping("")
    public ModelAndView getWeiboList(@RequestParam(value = "page", required = false) String page,
                                     ModelAndView mv) {
        WeiboPageView<ListData<Weibo>> weiboPageView = buildPageView(WeiboPageView.class, weiboService.getWeiboList(page, "10"));
        setPagination(paginationService, mv, page, weiboPageView.getObject().getTotal(), "/weibo?page=");
        assert SYSTEM_CONFIG != null;
        weiboPageView.getPageHead().setTitle("任霏的微博客 - Weibo - " + SYSTEM_CONFIG.getSiteName());
        weiboPageView.getPageHead().setKeywords("任霏,RenFei,NeilRen,个人,博客,blog,开发,技术,posts");
        weiboPageView.getPageHead().setDescription("任霏的的微型博客和微型网志。");
        mv.addObject("pageView", weiboPageView);
        mv.setViewName("weibo/index");
        return mv;
    }

    /**
     * 微博列表错误地址重定向
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
    public RedirectView getWeiboListDir() {
        assert SYSTEM_CONFIG != null;
        RedirectView redirectView = new RedirectView(SYSTEM_CONFIG.getSiteDomainName() + "/weibo");
        redirectView.setStatusCode(HttpStatus.MOVED_PERMANENTLY);
        return redirectView;
    }

    @RequestMapping("{id}")
    public ModelAndView getWeiboById(@PathVariable("id") String id, ModelAndView mv) throws NoHandlerFoundException {
        WeiboPageView<WeiboDomain> weiboPageView = null;
        try {
            Long longId = Long.parseLong(id);
            weiboPageView = buildPageView(WeiboPageView.class, new WeiboDomain(longId));
        } catch (NotExistException e) {
            noHandlerFoundException();
        }
        assert weiboPageView != null;
        assert SYSTEM_CONFIG != null;
        weiboPageView.getPageHead().setOgProtocol(OGProtocol.builder()
                .author("任霏")
                .description(weiboPageView.getObject().getWeibo().getContent())
                .image(weiboPageView.getObject().getWeibo() == null ? null : weiboPageView.getObject().getWeibo().getImage())
                .locale("zh_CN")
                .releaseDate(weiboPageView.getObject().getWeibo().getReleaseTime())
                .siteName("RenFei.Net")
                .title(getTitle(weiboPageView.getObject().getWeibo().getContent()) + " - Weibo")
                .type("article")
                .url(SYSTEM_CONFIG.getSiteDomainName() + "/posts/" + weiboPageView.getObject().getWeibo().getId())
                .build());
        weiboPageView.getPageHead().setTitle(getTitle(weiboPageView.getObject().getWeibo().getContent()) + " - 任霏的微博客");
        mv.addObject("pageView", weiboPageView);
        mv.setViewName("weibo/weibo");
        weiboService.view(weiboPageView.getObject());
        return mv;
    }

    /**
     * 微博文章错误地址重定向
     *
     * @param id 文章ID
     * @return RedirectView
     */
    @RequestMapping({
            "{id}/index.html", "{id}/index.htm", "{id}/index.xhtml", "{id}/index.shtml",
            "{id}/index.php", "{id}/index.asp", "{id}/index.aspx", "{id}/index.do",
            "{id}/index.jsp", "{id}/index.dll", "{id}/index.php3", "{id}/index.pl",
            "{id}/index.cgi",
            "{id}/default.html", "{id}/default.htm", "{id}/default.xhtml", "{id}/default.shtml",
            "{id}/default.php", "{id}/default.asp", "{id}/default.aspx", "{id}/default.do",
            "{id}/default.jsp", "{id}/default.dll", "{id}/default.php3", "{id}/default.pl",
            "{id}/default.cgi"
    })
    public RedirectView getWeiboInfoDir(@PathVariable("id") String id) {
        assert SYSTEM_CONFIG != null;
        RedirectView redirectView = new RedirectView(SYSTEM_CONFIG.getSiteDomainName() + "/weibo/" + id);
        redirectView.setStatusCode(HttpStatus.MOVED_PERMANENTLY);
        return redirectView;
    }

    private String getTitle(String content) {
        if (content.length() < 100) {
            return content;
        } else {
            return content.substring(0, 100);
        }
    }
}
