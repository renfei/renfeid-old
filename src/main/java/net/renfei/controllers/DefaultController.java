package net.renfei.controllers;

import net.renfei.model.HomePageView;
import org.springframework.context.annotation.Lazy;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.servlet.ModelAndView;

/**
 * @author renfei
 */
@Lazy
@Controller
public class DefaultController extends BaseController {

    @RequestMapping("/")
    public ModelAndView index(ModelAndView mv) {
        HomePageView<String> homePageView = buildPageView(HomePageView.class, "");
        mv.addObject("pageView", homePageView);
        mv.setViewName("index");
        return mv;
    }
}
