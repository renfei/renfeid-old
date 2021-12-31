package net.renfei.controllers.other;

import net.renfei.controllers.BaseController;
import net.renfei.model.HomePageView;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.servlet.ModelAndView;
import org.springframework.web.servlet.view.RedirectView;

/**
 * 赞助
 *
 * @author renfei
 */
@Controller
@RequestMapping("/sponsors")
public class SponsorsController extends BaseController {

    @RequestMapping("")
    public ModelAndView sponsors(ModelAndView mv) {
        HomePageView<String> homePageView = buildPageView(HomePageView.class, "");
        assert SYSTEM_CONFIG != null;
        homePageView.getPageHead().setTitle("赞助 - Sponsors - " + SYSTEM_CONFIG.getSiteName());
        mv.addObject("pageView", homePageView);
        mv.setViewName("sponsors");
        return mv;
    }

    /**
     * 赞助错误地址重定向
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
    public RedirectView getAboutDir() {
        assert SYSTEM_CONFIG != null;
        RedirectView redirectView = new RedirectView(SYSTEM_CONFIG.getSiteDomainName() + "/sponsors");
        redirectView.setStatusCode(HttpStatus.MOVED_PERMANENTLY);
        return redirectView;
    }
}
