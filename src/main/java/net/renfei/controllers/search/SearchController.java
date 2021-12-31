package net.renfei.controllers.search;

import net.renfei.controllers.BaseController;
import net.renfei.services.SearchService;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.servlet.ModelAndView;

import javax.servlet.http.HttpServletResponse;

/**
 * @author renfei
 */
@Controller
@RequestMapping("/search")
public class SearchController extends BaseController {
    private final SearchService searchService;

    public SearchController(SearchService searchService) {
        this.searchService = searchService;
    }

    @RequestMapping(value = "search.xml")
    public ModelAndView getSearchXml(ModelAndView mv, HttpServletResponse response) {
        assert SYSTEM_CONFIG != null;
        mv.addObject("siteName", SYSTEM_CONFIG.getSiteName());
        mv.addObject("domain", SYSTEM_CONFIG.getSiteDomainName());
        response.setHeader("content-type", "application/xml;charset=UTF-8");
        response.setContentType("application/xml;charset=UTF-8");
        mv.setViewName("searchxml");
        return mv;
    }
}
