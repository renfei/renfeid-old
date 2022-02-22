package net.renfei.controller.pages;

import net.renfei.annotation.OperationLog;
import net.renfei.controller.BaseController;
import net.renfei.domain.PagesDomain;
import net.renfei.exception.NeedPasswordException;
import net.renfei.exception.NotExistException;
import net.renfei.exception.SecretLevelException;
import net.renfei.model.HomePageView;
import net.renfei.model.SocialSharing;
import net.renfei.model.system.SystemTypeEnum;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.servlet.ModelAndView;
import org.springframework.web.servlet.NoHandlerFoundException;

/**
 * 页面 Controller
 *
 * @author renfei
 */
@Controller
@RequestMapping("/page")
public class PagesController extends BaseController {
    @RequestMapping("{id}")
    @OperationLog(module = SystemTypeEnum.PAGE, desc = "访问单页面")
    public ModelAndView getPage(@PathVariable("id") Long id, ModelAndView mv) throws NoHandlerFoundException {
        PagesDomain pagesDomain = null;
        try {
            pagesDomain = new PagesDomain(id, getSignUser(), null, false);
        } catch (NotExistException e) {
            noHandlerFoundException();
        } catch (NeedPasswordException e) {
            // TODO 需要密码
        } catch (SecretLevelException e) {
            // TODO 保密等级不足
        }
        HomePageView<PagesDomain> pageView = buildPageView(HomePageView.class, pagesDomain);
        assert systemConfig != null;
        assert pagesDomain != null;
        pageView.getPageHead().setTitle(pagesDomain.getPage().getPageTitle() + " - " + systemConfig.getSiteName());
        SocialSharing socialSharing = new SocialSharing(pagesDomain);
        mv.addObject("pageView", pageView);
        mv.addObject("socialSharing", socialSharing);
        mv.setViewName("pages/page");
        return mv;
    }
}
