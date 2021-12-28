package net.renfei.controllers.kitbox;

import lombok.extern.slf4j.Slf4j;
import net.renfei.controllers.BaseController;
import net.renfei.model.kitbox.KitboxPageView;
import net.renfei.services.KitBoxService;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.servlet.ModelAndView;
import org.springframework.web.servlet.view.RedirectView;

/**
 * 工具箱栏目
 *
 * @author renfei
 */
@Slf4j
@Controller
@RequestMapping("/kitbox")
public class KitBoxController extends BaseController {
    private final KitBoxService kitBoxService;

    public KitBoxController(KitBoxService kitBoxService) {
        this.kitBoxService = kitBoxService;
    }

    @RequestMapping("")
    public ModelAndView kitbox(ModelAndView mv) {
        assert SYSTEM_CONFIG != null;
        KitboxPageView<Object> pageView = buildPageView(KitboxPageView.class, null);
        pageView.getPageHead().setTitle("开发者工具箱 - " + SYSTEM_CONFIG.getSiteName());
        pageView.getPageHead().setDescription("免费的开发者与站长工具箱小工具，包含网络工具、加解密工具、测试工具等，工欲善其事，必先利其器。");
        pageView.getPageHead().setKeywords("开发,开发者,工具,工具箱");
        mv.addObject("KitBoxMenus", kitBoxService.getKitBoxMenus());
        mv.addObject("pageView", pageView);
        mv.setViewName("kitbox/index");
        return mv;
    }

    /**
     * 博客列表错误地址重定向
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
    public RedirectView getKitBoxDir() {
        assert SYSTEM_CONFIG != null;
        RedirectView redirectView = new RedirectView(SYSTEM_CONFIG.getSiteDomainName() + "/kitbox/");
        redirectView.setStatusCode(HttpStatus.MOVED_PERMANENTLY);
        return redirectView;
    }
}
