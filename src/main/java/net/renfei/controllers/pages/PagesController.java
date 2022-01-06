package net.renfei.controllers.pages;

import lombok.extern.slf4j.Slf4j;
import net.renfei.controllers.BaseController;
import net.renfei.domain.PagesDomain;
import net.renfei.exception.NeedPasswordException;
import net.renfei.exception.NotExistException;
import net.renfei.exception.SecretLevelException;
import net.renfei.model.HomePageView;
import net.renfei.model.SocialSharing;
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
@Slf4j
@Controller
@RequestMapping("/page")
public class PagesController extends BaseController {
    @RequestMapping("{id}")
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
        assert SYSTEM_CONFIG != null;
        assert pagesDomain != null;
        pageView.getPageHead().setTitle(pagesDomain.getPage().getPageTitle() + " - " + SYSTEM_CONFIG.getSiteName());
        SocialSharing socialSharing = new SocialSharing(pagesDomain);
        mv.addObject("pageView", pageView);
        mv.addObject("socialSharing", socialSharing);
        mv.setViewName("pages/page");
        return mv;
    }
}
