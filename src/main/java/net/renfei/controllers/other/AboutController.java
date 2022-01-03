package net.renfei.controllers.other;

import net.renfei.annotation.OperationLog;
import net.renfei.controllers.BaseController;
import net.renfei.model.HomePageView;
import net.renfei.model.system.SystemTypeEnum;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.servlet.ModelAndView;
import org.springframework.web.servlet.view.RedirectView;

/**
 * 关于
 *
 * @author renfei
 */
@Controller
@RequestMapping("/about")
public class AboutController extends BaseController {

    @RequestMapping("")
    @OperationLog(module = SystemTypeEnum.HOME, desc = "访问关于页面")
    public ModelAndView getAbout(ModelAndView mv) {
        HomePageView<String> homePageView = buildPageView(HomePageView.class, "");
        assert SYSTEM_CONFIG != null;
        homePageView.getPageHead().setTitle("关于任霏 - " + SYSTEM_CONFIG.getSiteName());
        homePageView.getPageHead().setDescription("任霏，软件开发工程师。一只90后程序猿，大学是 C#.NET 专业毕业，后自学 Java 进行技术转型。你现在看到的这个网站就是我从后端到前端完整的手写的。");
        homePageView.getPageHead().setKeywords("任霏,renfei,关于");
        mv.addObject("pageView", homePageView);
        mv.setViewName("about");
        return mv;
    }

    /**
     * 关于错误地址重定向
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
    @OperationLog(module = SystemTypeEnum.HOME, desc = "访问关于页面Index.html")
    public RedirectView getAboutDir() {
        assert SYSTEM_CONFIG != null;
        RedirectView redirectView = new RedirectView(SYSTEM_CONFIG.getSiteDomainName() + "/about");
        redirectView.setStatusCode(HttpStatus.MOVED_PERMANENTLY);
        return redirectView;
    }
}
