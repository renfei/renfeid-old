package net.renfei.controllers.error;

import net.renfei.controllers.BaseController;
import net.renfei.model.HomePageView;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.servlet.ModelAndView;

/**
 * 错误页
 *
 * @author renfei
 */
@Controller
@RequestMapping("/error")
public class ErrorController extends BaseController {
    @RequestMapping("401")
    public ModelAndView error401(ModelAndView mv) {
        HomePageView<String> pageView = buildPageView(HomePageView.class, "");
        assert SYSTEM_CONFIG != null;
        pageView.getPageHead().setTitle("Error 401 (Unauthorized)!! - " + SYSTEM_CONFIG.getSiteName());
        pageView.getPageHead().setDescription("Error 401 (Unauthorized)!!");
        pageView.getPageHead().setKeywords("error,401,unauthorized");
        mv.addObject("pageView", pageView);
        mv.setViewName("error/401");
        mv.setStatus(HttpStatus.UNAUTHORIZED);
        return mv;
    }

    @RequestMapping("403")
    public ModelAndView error403(ModelAndView mv) {
        HomePageView<String> pageView = buildPageView(HomePageView.class, "");
        assert SYSTEM_CONFIG != null;
        pageView.getPageHead().setTitle("Error 403 (Forbidden)!! - " + SYSTEM_CONFIG.getSiteName());
        pageView.getPageHead().setDescription("Error 403 (Forbidden)!!");
        pageView.getPageHead().setKeywords("error,403,forbidden");
        mv.addObject("pageView", pageView);
        mv.setViewName("error/403");
        mv.setStatus(HttpStatus.FORBIDDEN);
        return mv;
    }

    @RequestMapping("404")
    public ModelAndView error404(ModelAndView mv) {
        HomePageView<String> pageView = buildPageView(HomePageView.class, "");
        assert SYSTEM_CONFIG != null;
        pageView.getPageHead().setTitle("Error 404 (Not Found)!! - " + SYSTEM_CONFIG.getSiteName());
        pageView.getPageHead().setDescription("Error 404 (Not Found)!!");
        pageView.getPageHead().setKeywords("error,404,not found");
        mv.addObject("pageView", pageView);
        mv.setViewName("error/404");
        mv.setStatus(HttpStatus.NOT_FOUND);
        return mv;
    }

    @RequestMapping("405")
    public ModelAndView error405(ModelAndView mv) {
        HomePageView<String> pageView = buildPageView(HomePageView.class, "");
        assert SYSTEM_CONFIG != null;
        pageView.getPageHead().setTitle("Error 405 (Method Not Allowed)!! - " + SYSTEM_CONFIG.getSiteName());
        pageView.getPageHead().setDescription("Error 405 (Method Not Allowed)!!");
        pageView.getPageHead().setKeywords("error,405,method not allowed");
        mv.addObject("pageView", pageView);
        mv.setViewName("error/405");
        mv.setStatus(HttpStatus.METHOD_NOT_ALLOWED);
        return mv;
    }

    @RequestMapping("500")
    public ModelAndView error500(ModelAndView mv) {
        HomePageView<String> pageView = buildPageView(HomePageView.class, "");
        assert SYSTEM_CONFIG != null;
        pageView.getPageHead().setTitle("Error 500 (Internal Server Error)!! - " + SYSTEM_CONFIG.getSiteName());
        pageView.getPageHead().setDescription("Error 500 (Internal Server Error)!!");
        pageView.getPageHead().setKeywords("error,500,Internal Server Error");
        mv.addObject("pageView", pageView);
        mv.setViewName("error/500");
        mv.setStatus(HttpStatus.INTERNAL_SERVER_ERROR);
        return mv;
    }
}
