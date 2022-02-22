package net.renfei.controller.account;

import net.renfei.annotation.OperationLog;
import net.renfei.controller.BaseController;
import net.renfei.discuz.model.DiscuzInfo;
import net.renfei.discuz.service.DiscuzService;
import net.renfei.domain.user.User;
import net.renfei.model.account.AccountPageView;
import net.renfei.model.system.SystemTypeEnum;
import net.renfei.utils.GoogleAuthenticator;
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
    @OperationLog(module = SystemTypeEnum.ACCOUNT, desc = "访问账户管理页面")
    public ModelAndView manage(ModelAndView mv) {
        assert systemConfig != null;
        ModelAndView modelAndView = checkSigned();
        if (modelAndView != null) {
            return modelAndView;
        }
        DiscuzInfo discuzInfo = discuzService.getDiscuzInfo(getSignUser().getUserName());
        AccountPageView<User> pageView = buildPageView(AccountPageView.class, getSignUser());
        pageView.setDiscuzInfo(discuzInfo);
        pageView.getPageHead().setTitle("管理您的账户 - " + systemConfig.getSiteName());
        mv.addObject("pageView", pageView);
        mv.setViewName("account/manage");
        return mv;
    }

    @GetMapping("manage/email")
    @OperationLog(module = SystemTypeEnum.ACCOUNT, desc = "访问账户邮箱管理页面")
    public ModelAndView manageEmail(ModelAndView mv) {
        assert systemConfig != null;
        ModelAndView modelAndView = checkSigned();
        if (modelAndView != null) {
            return modelAndView;
        }
        AccountPageView<User> pageView = buildPageView(AccountPageView.class, getSignUser());
        mv.addObject("pageView", pageView);
        pageView.getPageHead().setTitle("管理您的电子邮箱地址 - " + systemConfig.getSiteName());
        mv.setViewName("account/email");
        return mv;
    }

    @GetMapping("manage/phone")
    @OperationLog(module = SystemTypeEnum.ACCOUNT, desc = "访问账户手机管理页面")
    public ModelAndView managePhone(ModelAndView mv) {
        assert systemConfig != null;
        ModelAndView modelAndView = checkSigned();
        if (modelAndView != null) {
            return modelAndView;
        }
        AccountPageView<User> pageView = buildPageView(AccountPageView.class, getSignUser());
        mv.addObject("pageView", pageView);
        pageView.getPageHead().setTitle("管理您的手机号码 - " + systemConfig.getSiteName());
        mv.setViewName("account/phone");
        return mv;
    }

    @GetMapping("manage/password")
    @OperationLog(module = SystemTypeEnum.ACCOUNT, desc = "访问账户密码管理页面")
    public ModelAndView managePassword(ModelAndView mv) {
        assert systemConfig != null;
        ModelAndView modelAndView = checkSigned();
        if (modelAndView != null) {
            return modelAndView;
        }
        AccountPageView<String> pageView = buildPageView(AccountPageView.class, null);
        mv.addObject("pageView", pageView);
        pageView.getPageHead().setTitle("管理您的密码 - " + systemConfig.getSiteName());
        mv.setViewName("account/password");
        return mv;
    }

    @GetMapping("manage/u2f")
    @OperationLog(module = SystemTypeEnum.ACCOUNT, desc = "访问账户U2F管理页面")
    public ModelAndView manageU2F(ModelAndView mv) {
        assert systemConfig != null;
        ModelAndView modelAndView = checkSigned();
        if (modelAndView != null) {
            return modelAndView;
        }
        User user = getSignUser();
        AccountPageView<User> pageView = buildPageView(AccountPageView.class, user);
        pageView.getPageHead().setTitle("管理您的两步认证(U2F) - " + systemConfig.getSiteName());
        mv.addObject("pageView", pageView);
        String secretKey = GoogleAuthenticator.generateSecretKey(systemConfig.getTotpSecret());
        mv.addObject("secretKey", secretKey);
        mv.addObject("totpString", GoogleAuthenticator.genTotpString("RENFEI.NET", user.getUserName(), secretKey));
        mv.setViewName("account/u2f");
        return mv;
    }

    @GetMapping({"manage/firstName", "manage/lastName"})
    @OperationLog(module = SystemTypeEnum.ACCOUNT, desc = "访问账户称呼管理页面")
    public ModelAndView manageFirstName(ModelAndView mv) {
        assert systemConfig != null;
        ModelAndView modelAndView = checkSigned();
        if (modelAndView != null) {
            return modelAndView;
        }
        User user = getSignUser();
        AccountPageView<User> pageView = buildPageView(AccountPageView.class, user);
        pageView.getPageHead().setTitle("管理您的姓名称呼 - " + systemConfig.getSiteName());
        mv.addObject("pageView", pageView);
        mv.setViewName("account/firstName");
        return mv;
    }
}
