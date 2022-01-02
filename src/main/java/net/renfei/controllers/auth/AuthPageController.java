package net.renfei.controllers.auth;

import lombok.extern.slf4j.Slf4j;
import net.renfei.controllers.BaseController;
import net.renfei.domain.user.User;
import net.renfei.model.HomePageView;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.servlet.ModelAndView;

/**
 * 认证相关的页面Controller
 *
 * @author renfei
 */
@Slf4j
@Controller
@RequestMapping("/auth")
public class AuthPageController extends BaseController {
    /**
     * 登陆界面
     */
    @GetMapping("signIn")
    public ModelAndView signInPage(ModelAndView mv,
                                   @RequestParam(value = "callback", required = false) String callback) {
        HomePageView<User> pageView = buildPageView(HomePageView.class, getSignUser());
        assert SYSTEM_CONFIG != null;
        pageView.getPageHead().setTitle("登录 - " + SYSTEM_CONFIG.getSiteName());
        mv.addObject("pageView", pageView);
        mv.addObject("callback", getCallBack(callback));
        mv.addObject("ReCAPTCHA_Client_Key", SYSTEM_CONFIG.getGoogle().getReCAPTCHA().getClientKey());
        mv.addObject("title", "登录 - " + SYSTEM_CONFIG.getSiteName());
        mv.setViewName("auth/signIn");
        return mv;
    }
}
