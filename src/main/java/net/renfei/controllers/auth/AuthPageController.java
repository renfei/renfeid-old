package net.renfei.controllers.auth;

import lombok.extern.slf4j.Slf4j;
import net.renfei.annotation.OperationLog;
import net.renfei.controllers.BaseController;
import net.renfei.domain.user.User;
import net.renfei.model.HomePageView;
import net.renfei.model.system.SystemTypeEnum;
import net.renfei.services.AccountService;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.stereotype.Controller;
import org.springframework.util.ObjectUtils;
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
    private final AccountService accountService;

    public AuthPageController(AccountService accountService) {
        this.accountService = accountService;
    }

    /**
     * 登陆界面
     */
    @GetMapping("signIn")
    @OperationLog(module = SystemTypeEnum.ACCOUNT, desc = "访问登陆页")
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

    /**
     * 注册界面
     */
    @GetMapping("signUp")
    @OperationLog(module = SystemTypeEnum.ACCOUNT, desc = "访问注册页")
    public ModelAndView signUpPage(ModelAndView mv) {
        if (getSignUser() != null) {
            return new ModelAndView("redirect:/");
        }
        HomePageView<User> pageView = buildPageView(HomePageView.class, getSignUser());
        assert SYSTEM_CONFIG != null;
        pageView.getPageHead().setTitle("创建您的账户 - " + SYSTEM_CONFIG.getSiteName());
        mv.addObject("pageView", pageView);
        mv.addObject("ReCAPTCHA_Client_Key", SYSTEM_CONFIG.getGoogle().getReCAPTCHA().getClientKey());
        mv.addObject("pageView", pageView);
        mv.setViewName("auth/signUp");
        return mv;
    }

    /**
     * 注册完成界面
     */
    @GetMapping("signUp/success")
    public ModelAndView signUpSuccessPage(ModelAndView mv) {
        HomePageView<String> pageView = buildPageView(HomePageView.class, null);
        assert SYSTEM_CONFIG != null;
        pageView.getPageHead().setTitle("您已成功创建了账户 - " + SYSTEM_CONFIG.getSiteName());
        mv.addObject("pageView", pageView);
        mv.setViewName("auth/signUpSuccess");
        return mv;
    }

    /**
     * 登出界面
     */
    @GetMapping("signOut")
    public ModelAndView signOut(ModelAndView mv,
                                @RequestParam(value = "callback", required = false) String callback) {
        assert SYSTEM_CONFIG != null;
        User user = getSignUser();
        String script = "";
        if (user != null) {
            script = accountService.signOut(user);
        }
        request.getSession().removeAttribute(SESSION_KEY);
        SecurityContextHolder.clearContext();
        String callBack = getCallBack(callback);
        if (ObjectUtils.isEmpty(callBack) || "".equals(callBack)) {
            callBack = SYSTEM_CONFIG.getSiteDomainName();
        }
        if (!callBack.startsWith("http") && !callBack.startsWith("https")) {
            callBack = "http://" + callBack;
        }
        HomePageView<String> pageView = buildPageView(HomePageView.class, callBack);
        pageView.getPageHead().setTitle("您已安全登出我们的系统 - " + SYSTEM_CONFIG.getSiteName());
        mv.addObject("script", script);
        mv.addObject("pageView", pageView);
        mv.setViewName("auth/signOut");
        return mv;
    }
}
