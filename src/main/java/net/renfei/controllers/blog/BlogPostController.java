package net.renfei.controllers.blog;

import lombok.extern.slf4j.Slf4j;
import net.renfei.annotation.OperationLog;
import net.renfei.controllers.BaseController;
import net.renfei.domain.BlogDomain;
import net.renfei.exception.NeedPasswordException;
import net.renfei.exception.NotExistException;
import net.renfei.exception.SecretLevelException;
import net.renfei.model.*;
import net.renfei.model.blog.PostPageView;
import net.renfei.model.system.BlogVO;
import net.renfei.model.system.SystemTypeEnum;
import net.renfei.services.BlogService;
import net.renfei.services.PaginationService;
import net.renfei.utils.NumberUtils;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.servlet.ModelAndView;
import org.springframework.web.servlet.NoHandlerFoundException;
import org.springframework.web.servlet.view.RedirectView;

import java.util.List;

/**
 * 博客栏目
 *
 * @author renfei
 */
@Slf4j
@Controller
@RequestMapping("/posts")
public class BlogPostController extends BaseController {
    private final BlogService blogService;
    private final PaginationService paginationService;

    public BlogPostController(BlogService blogService,
                              PaginationService paginationService) {
        this.blogService = blogService;
        this.paginationService = paginationService;
    }

    @RequestMapping("")
    @OperationLog(module = SystemTypeEnum.BLOG, desc = "访问博客首页")
    public ModelAndView getPostList(ModelAndView mv,
                                    @RequestParam(value = "page", required = false) String page) {
        mv.addObject("catTitle", "全部文档");
        ListData<BlogVO> allPostList = blogService.getAllPostList(getSignUser(), false, NumberUtils.parseInt(page, 1), 15);
        PostPageView<List<BlogDomain>> postPageView = buildPageView(PostPageView.class, allPostList.getData());
        assert SYSTEM_CONFIG != null;
        postPageView.getPageHead().setTitle("任霏的博客文章 - " + SYSTEM_CONFIG.getSiteName());
        mv.addObject("pageView", postPageView);
        mv.addObject("PostSidebar", blogService.buildPostSidebar(getSignUser()));
        setPagination(paginationService, mv, page, allPostList.getTotal(), "/posts?page=");
        mv.setViewName("blog/list");
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
    @OperationLog(module = SystemTypeEnum.BLOG, desc = "访问博客首页Index.html")
    public RedirectView getPostListDir() {
        assert SYSTEM_CONFIG != null;
        RedirectView redirectView = new RedirectView(SYSTEM_CONFIG.getSiteDomainName() + "/posts");
        redirectView.setStatusCode(HttpStatus.MOVED_PERMANENTLY);
        return redirectView;
    }


    /**
     * 博客文章详情页面
     *
     * @param mv ModelAndView
     * @param id 文章ID
     * @return ModelAndView
     * @throws NoHandlerFoundException 404不存在异常
     */
    @GetMapping("{id}")
    @OperationLog(module = SystemTypeEnum.BLOG, desc = "访问博客详情页")
    public ModelAndView getPostInfo(ModelAndView mv, @PathVariable("id") Long id) throws NoHandlerFoundException {
        BlogVO blogVO = null;
        assert blogService != null;
        // 页面没查到缓存，去数据库查询
        try {
            blogVO = blogService.getBlogById(id, getSignUser());
        } catch (NotExistException e) {
            noHandlerFoundException();
        } catch (NeedPasswordException e) {
            // TODO 文章需要密码才能查看
        } catch (SecretLevelException e) {
            // TODO 保密等级无权查看此文章内容
        }
        if (blogVO == null) {
            noHandlerFoundException();
        }
        PostPageView<BlogVO> postPageView = buildPageView(PostPageView.class, blogVO);
        assert blogVO != null;
        SocialSharing socialSharing = new SocialSharing(blogVO);
        mv.addObject("pageView", postPageView);
        mv.addObject("socialSharing", socialSharing);
        mv.addObject("PostSidebar", blogService.buildPostSidebar(getSignUser()));
        assert SYSTEM_CONFIG != null;
        postPageView.getPageHead().setTitle(blogVO.getPost().getTitle() + " - Posts - " + SYSTEM_CONFIG.getSiteName());
        mv.addObject("jsonld", blogService.getJsonld(blogVO));
        postPageView.getPageHead().setOgProtocol(OGProtocol.builder()
                .author(blogVO.getPost().getSourceName())
                .description(blogVO.getPost().getExcerpt())
                .image(blogVO.getPost().getFeaturedImage())
                .locale("zh_CN")
                .releaseDate(blogVO.getPost().getPostDate())
                .siteName("RenFei.Net")
                .title(blogVO.getPost().getTitle())
                .type("article")
                .url(SYSTEM_CONFIG.getSiteDomainName() + "/posts/" + blogVO.getPost().getId())
                .build());
        // TODO 获取文章扩展信息
        mv.setViewName("blog/post");
        blogService.view(blogVO, getSignUser(), null);
        return mv;
    }

    /**
     * 博客文章错误地址重定向
     *
     * @param id 文章ID
     * @return RedirectView
     */
    @RequestMapping({
            "{id}/index.html", "{id}/index.htm", "{id}/index.xhtml", "{id}/index.shtml",
            "{id}/index.php", "{id}/index.asp", "{id}/index.aspx", "{id}/index.do",
            "{id}/index.jsp", "{id}/index.dll", "{id}/index.php3", "{id}/index.pl",
            "{id}/index.cgi",
            "{id}/default.html", "{id}/default.htm", "{id}/default.xhtml", "{id}/default.shtml",
            "{id}/default.php", "{id}/default.asp", "{id}/default.aspx", "{id}/default.do",
            "{id}/default.jsp", "{id}/default.dll", "{id}/default.php3", "{id}/default.pl",
            "{id}/default.cgi"
    })
    @OperationLog(module = SystemTypeEnum.BLOG, desc = "访问博客详情页Index.html")
    public RedirectView getPostInfoDir(@PathVariable("id") String id) {
        assert SYSTEM_CONFIG != null;
        RedirectView redirectView = new RedirectView(SYSTEM_CONFIG.getSiteDomainName() + "/posts/" + id);
        redirectView.setStatusCode(HttpStatus.MOVED_PERMANENTLY);
        return redirectView;
    }
}
