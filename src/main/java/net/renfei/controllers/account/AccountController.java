package net.renfei.controllers.account;

import net.renfei.controllers.BaseController;
import net.renfei.domain.user.User;
import net.renfei.model.account.AccountPageView;
import net.renfei.model.discuz.DiscuzInfo;
import net.renfei.services.DiscuzService;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.servlet.ModelAndView;

/**
 * 账户管理
 *
 * @author renfei
 */
@Controller
@RequestMapping("/account")
public class AccountController extends BaseController {
    private final DiscuzService discuzService;

    public AccountController(DiscuzService discuzService) {
        this.discuzService = discuzService;
    }

    @GetMapping("")
    public ModelAndView account() {
        return new ModelAndView("redirect:/account/manage");
    }

    @GetMapping("manage")
    public ModelAndView manage(ModelAndView mv) {
        assert SYSTEM_CONFIG != null;
        ModelAndView modelAndView = checkSigned();
        if (modelAndView != null) {
            return modelAndView;
        }
        DiscuzInfo discuzInfo = discuzService.getDiscuzInfo(getSignUser().getUserName());
        AccountPageView<User> pageView = buildPageView(AccountPageView.class, getSignUser());
        pageView.setDiscuzInfo(discuzInfo);
        pageView.getPageHead().setTitle("管理您的账户 - " + SYSTEM_CONFIG.getSiteName());
        mv.addObject("pageView", pageView);
        mv.setViewName("account/manage");
        return mv;
    }

    @GetMapping("manage/email")
    public ModelAndView manageEmail(ModelAndView mv) {
        assert SYSTEM_CONFIG != null;
        ModelAndView modelAndView = checkSigned();
        if (modelAndView != null) {
            return modelAndView;
        }
        AccountPageView<User> pageView = buildPageView(AccountPageView.class, getSignUser());
        mv.addObject("pageView", pageView);
        pageView.getPageHead().setTitle("管理您的电子邮箱地址 - " + SYSTEM_CONFIG.getSiteName());
        mv.setViewName("account/email");
        return mv;
    }

    @GetMapping("manage/phone")
    public ModelAndView managePhone(ModelAndView mv) {
        assert SYSTEM_CONFIG != null;
        ModelAndView modelAndView = checkSigned();
        if (modelAndView != null) {
            return modelAndView;
        }
        AccountPageView<User> pageView = buildPageView(AccountPageView.class, getSignUser());
        mv.addObject("pageView", pageView);
        pageView.getPageHead().setTitle("管理您的手机号码 - " + SYSTEM_CONFIG.getSiteName());
        mv.setViewName("account/phone");
        return mv;
    }
}
