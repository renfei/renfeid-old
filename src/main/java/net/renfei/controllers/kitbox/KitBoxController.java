package net.renfei.controllers.kitbox;

import lombok.extern.slf4j.Slf4j;
import net.renfei.controllers.BaseController;
import net.renfei.domain.comment.Comment;
import net.renfei.domain.kitbox.KitBoxTypeEnum;
import net.renfei.exception.IP2LocationException;
import net.renfei.ip2location.IPResult;
import net.renfei.model.kitbox.KitboxPageView;
import net.renfei.services.IP2LocationService;
import net.renfei.services.KitBoxService;
import net.renfei.utils.IpUtils;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.servlet.ModelAndView;
import org.springframework.web.servlet.view.RedirectView;

import java.util.ArrayList;
import java.util.List;

import static net.renfei.services.kitbox.KitBoxServiceImpl.*;

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
    private final IP2LocationService ip2LocationService;

    public KitBoxController(KitBoxService kitBoxService, IP2LocationService ip2LocationService) {
        this.kitBoxService = kitBoxService;
        this.ip2LocationService = ip2LocationService;
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

    /**
     * IP地址信息查询工具
     *
     * @param mv
     * @return
     */
    @RequestMapping("ip")
    public ModelAndView ip(ModelAndView mv) {
        assert SYSTEM_CONFIG != null;
        String ip = IpUtils.getIpAddress(request);
        IPResult ipInfoDTO = null;
        try {
            ipInfoDTO = ip2LocationService.ipQuery(ip);
        } catch (IP2LocationException e) {
            e.printStackTrace();
        }
        mv.addObject("myip", ip);
        KitboxPageView<IPResult> pageView = buildPageView(KitboxPageView.class, ipInfoDTO);
        pageView.getPageHead().setTitle(KitBoxTypeEnum.NETWORK_IP.getTitle() + "开发者工具箱 - " + SYSTEM_CONFIG.getSiteName());
        pageView.getPageHead().setDescription("免费的开发者与站长工具箱小工具，包含网络工具、加解密工具、测试工具等，工欲善其事，必先利其器。");
        pageView.getPageHead().setKeywords("IP,地址,信息,查询,工具,地理,位置");
        mv.setViewName("kitbox/ipinfo");
        mv.addObject("pageView", pageView);
        setKitBoxMenus(mv, NETWORK_TOOL);
        List<Comment> commentList = kitBoxService.getCommentList(KitBoxTypeEnum.NETWORK_IP);
        mv.addObject("commentList", commentList == null ? new ArrayList<>() : commentList);
        mv.addObject("kitBoxId", KitBoxTypeEnum.NETWORK_IP.getId());
        return mv;
    }

    @RequestMapping({"freemarkerTest", "FtlTest"})
    public ModelAndView freemarkerTest(ModelAndView mv) {
        assert SYSTEM_CONFIG != null;
        KitboxPageView<String> pageView = buildPageView(KitboxPageView.class, "");
        pageView.getPageHead().setTitle(KitBoxTypeEnum.DEVELOP_FREEMARKER_TEST.getTitle() + "开发者工具箱 - " + SYSTEM_CONFIG.getSiteName());
        pageView.getPageHead().setDescription("FreeMarker(FTL)在线测试工具");
        pageView.getPageHead().setKeywords("FreeMarker,ftl,在线,测试,工具");
        mv.setViewName("kitbox/freemarkerTest");
        mv.addObject("pageView", pageView);
        setKitBoxMenus(mv, DEVELOPMENT_TOOL);
        List<Comment> commentList = kitBoxService.getCommentList(KitBoxTypeEnum.DEVELOP_FREEMARKER_TEST);
        mv.addObject("commentList", commentList == null ? new ArrayList<>() : commentList);
        mv.addObject("kitBoxId", KitBoxTypeEnum.DEVELOP_FREEMARKER_TEST.getId());
        return mv;
    }

    private void setKitBoxMenus(ModelAndView mv, String key) {
        mv.addObject("KitBoxMenus", kitBoxService.getKitBoxMenus(key));
    }
}
