package net.renfei.controllers.docs;

import net.renfei.annotation.OperationLog;
import net.renfei.controllers.BaseController;
import net.renfei.model.docs.DocsPageView;
import net.renfei.model.kitbox.KitBoxMenus;
import net.renfei.model.system.SystemTypeEnum;
import net.renfei.repositories.model.DocsOnlineDocuments;
import net.renfei.services.DocsService;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.servlet.ModelAndView;
import org.springframework.web.servlet.NoHandlerFoundException;
import org.springframework.web.servlet.view.RedirectView;

import java.util.List;

/**
 * 在线文档栏目
 *
 * @author renfei
 */
@Controller
@RequestMapping("/docs")
public class DocsController extends BaseController {
    private final DocsService docsService;

    public DocsController(DocsService docsService) {
        this.docsService = docsService;
    }

    @RequestMapping("")
    @OperationLog(module = SystemTypeEnum.DOCS, desc = "访问在线文档首页")
    public ModelAndView getDocs(ModelAndView mv) {
        DocsPageView<List<KitBoxMenus>> pageView = buildPageView(DocsPageView.class, docsService.getDocs());
        assert systemConfig != null;
        pageView.getPageHead().setTitle("在线文档 - " + systemConfig.getSiteName());
        pageView.getPageHead().setDescription("提供在线开发文档与手册服务，部分中文文档基于 Google Translation 机器翻译，请结合原版阅读。");
        pageView.getPageHead().setKeywords("开发,文档,手册,在线");
        mv.addObject("pageView", pageView);
        mv.setViewName("docs/index");
        return mv;
    }

    /**
     * 在线文档栏目列表错误地址重定向
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
    @OperationLog(module = SystemTypeEnum.DOCS, desc = "访问在线文档首页Index.html")
    public RedirectView getPostListDir() {
        assert systemConfig != null;
        RedirectView redirectView = new RedirectView(systemConfig.getSiteDomainName() + "/docs");
        redirectView.setStatusCode(HttpStatus.MOVED_PERMANENTLY);
        return redirectView;
    }

    @RequestMapping("{category}/{title}/{version}/{lang}")
    @OperationLog(module = SystemTypeEnum.DOCS, desc = "访问在线文档详情页")
    public ModelAndView getEmbed(ModelAndView mv,
                                 @PathVariable("category") String category,
                                 @PathVariable("title") String title,
                                 @PathVariable("version") String version,
                                 @PathVariable("lang") String lang) throws NoHandlerFoundException {
        DocsOnlineDocuments docsOnlineDocuments = docsService.getEmbed(category, title, version, lang);
        if (docsOnlineDocuments == null) {
            noHandlerFoundException();
        }
        assert docsOnlineDocuments != null;
        assert systemConfig != null;
        DocsPageView<DocsOnlineDocuments> pageView = buildPageView(DocsPageView.class, docsOnlineDocuments);
        pageView.getPageHead().setTitle(docsOnlineDocuments.getTitle() + " (" + docsOnlineDocuments.getVersion() + ") - 在线文档 - " + systemConfig.getSiteName());
        pageView.getPageHead().setDescription("提供在线开发文档与手册服务，部分中文文档基于 Google Translation 机器翻译，请结合原版阅读。");
        pageView.getPageHead().setKeywords("开发,文档,手册,在线");
        mv.addObject("pageView", pageView);
        mv.setViewName("docs/embed");
        return mv;
    }
}
