package net.renfei.controller.search;

import net.renfei.annotation.OperationLog;
import net.renfei.controller.BaseController;
import net.renfei.model.HomePageView;
import net.renfei.model.ListData;
import net.renfei.model.log.LogLevelEnum;
import net.renfei.model.log.OperationTypeEnum;
import net.renfei.model.search.SearchItem;
import net.renfei.model.search.TypeEnum;
import net.renfei.model.system.SystemTypeEnum;
import net.renfei.services.LogService;
import net.renfei.services.PaginationService;
import net.renfei.services.SearchService;
import org.springframework.stereotype.Controller;
import org.springframework.util.ObjectUtils;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.context.request.RequestContextHolder;
import org.springframework.web.context.request.ServletRequestAttributes;
import org.springframework.web.servlet.ModelAndView;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.text.DecimalFormat;

/**
 * @author renfei
 */
@Controller
@RequestMapping("/search")
public class SearchController extends BaseController {
    private final LogService logService;
    private final SearchService searchService;
    private final PaginationService paginationService;

    public SearchController(LogService logService,
                            SearchService searchService,
                            PaginationService paginationService) {
        this.logService = logService;
        this.searchService = searchService;
        this.paginationService = paginationService;
    }

    @RequestMapping("")
    // 为了跟搜索动作进行区别，访问页面的就不记录了
    // @OperationLog(module = SystemTypeEnum.SEARCH, desc = "访问站内搜索页面")
    public ModelAndView search(ModelAndView mv,
                               @RequestParam(value = "type", required = false) String type,
                               @RequestParam(value = "w", required = false) String query,
                               @RequestParam(value = "p", required = false) String page) {
        assert systemConfig != null;
        HomePageView<ListData<SearchItem>> pageView = buildPageView(HomePageView.class, null);
        if (ObjectUtils.isEmpty(query)) {
            pageView.getPageHead().setTitle("站内搜索 - " + systemConfig.getSiteName());
        } else {
            pageView.getPageHead().setTitle("搜索:" + query + " - " + systemConfig.getSiteName());
            TypeEnum typeEnum = null;
            switch (type.toLowerCase().trim()) {
                case "post":
                    typeEnum = TypeEnum.POSTS;
                    break;
                case "page":
                    typeEnum = TypeEnum.PAGES;
                    break;
                case "video":
                    typeEnum = TypeEnum.VIDEO;
                    break;
                case "photo":
                    typeEnum = TypeEnum.PHOTO;
                    break;
                case "weibo":
                    typeEnum = TypeEnum.WEIBO;
                    break;
                case "kitbox":
                    typeEnum = TypeEnum.KITBOX;
                    break;
                case "discuz":
                    typeEnum = TypeEnum.DISCUZ;
                    break;
                default:
                    type = "all";
                    break;
            }
            Long startTime = System.nanoTime();
            ListData<SearchItem> searchItemListData = searchService.search(typeEnum, query, page, "10");
            pageView.setObject(searchItemListData);
            mv.addObject("query", query);
            mv.addObject("queryTitle", "搜索：" + query);
            mv.addObject("type", typeEnum == null ? "ALL" : typeEnum.toString());
            Long endTime = System.nanoTime();
            double timed = (endTime - startTime) / 1000000000D;
            DecimalFormat df = new DecimalFormat("######0.000000");
            mv.addObject("searchTime", df.format(timed));
            setPagination(paginationService, mv, page, searchItemListData.getTotal(), "/search?type=" + (typeEnum == null ? "ALL" : typeEnum) + "&w=" + query + "&p=");
            ServletRequestAttributes servletRequestAttributes = (ServletRequestAttributes) RequestContextHolder.getRequestAttributes();
            //设置子线程共享
            RequestContextHolder.setRequestAttributes(servletRequestAttributes, true);
            assert servletRequestAttributes != null;
            HttpServletRequest request = servletRequestAttributes.getRequest();
            logService.log(LogLevelEnum.INFO, SystemTypeEnum.SEARCH, OperationTypeEnum.RETRIEVE, query, request);
        }
        mv.addObject("pageView", pageView);
        mv.addObject("hotSearchList", searchService.getHotSearchList());
        mv.setViewName("search");
        return mv;
    }

    @RequestMapping(value = "search.xml")
    @OperationLog(module = SystemTypeEnum.SEARCH, desc = "search.xml")
    public ModelAndView getSearchXml(ModelAndView mv, HttpServletResponse response) {
        assert systemConfig != null;
        mv.addObject("siteName", systemConfig.getSiteName());
        mv.addObject("domain", systemConfig.getSiteDomainName());
        response.setHeader("content-type", "application/xml;charset=UTF-8");
        response.setContentType("application/xml;charset=UTF-8");
        mv.setViewName("searchxml");
        return mv;
    }
}
