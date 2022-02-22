package net.renfei.controller.blog;

import net.renfei.annotation.OperationLog;
import net.renfei.controller.BaseController;
import net.renfei.domain.BlogDomain;
import net.renfei.domain.blog.Category;
import net.renfei.domain.system.SysKeywordTag;
import net.renfei.exception.NeedPasswordException;
import net.renfei.exception.NotExistException;
import net.renfei.exception.SecretLevelException;
import net.renfei.model.ListData;
import net.renfei.model.OGProtocol;
import net.renfei.model.SocialSharing;
import net.renfei.model.blog.PostPageView;
import net.renfei.model.system.BlogVO;
import net.renfei.model.system.SystemTypeEnum;
import net.renfei.services.BlogService;
import net.renfei.services.PaginationService;
import net.renfei.utils.NumberUtils;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.servlet.ModelAndView;
import org.springframework.web.servlet.NoHandlerFoundException;
import org.springframework.web.servlet.view.RedirectView;

import java.util.List;

/**
 * 博客栏目
 *
 * @author renfei
 */
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
        assert systemConfig != null;
        postPageView.getPageHead().setTitle("任霏的博客文章 - " + systemConfig.getSiteName());
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
        assert systemConfig != null;
        RedirectView redirectView = new RedirectView(systemConfig.getSiteDomainName() + "/posts");
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
        mv.addObject("related", blogService.getRelated(blogVO.getPost(), getSignUser()));
        assert systemConfig != null;
        postPageView.getPageHead().setTitle(blogVO.getPost().getTitle() + " - Posts - " + systemConfig.getSiteName());
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
                .url(systemConfig.getSiteDomainName() + "/posts/" + blogVO.getPost().getId())
                .build());
        // TODO 获取文章扩展信息
        if (blogVO.getPost().getContent().contains("code class=")) {
            //检测到有代码显示，需要增加代码高亮插件
            mv.addObject("HighlightJS", 1);
        }
        mv.setViewName("blog/post");
        blogService.view(blogVO, getSignUser(), null);
        return mv;
    }

    /**
     * 根据标签获取所有文章列表
     *
     * @param page 页码
     * @return
     */
    @RequestMapping("tag/{enName}")
    @OperationLog(module = SystemTypeEnum.BLOG, desc = "根据标签获取所有文章列表页面")
    public ModelAndView getAllPostsListByTag(@RequestParam(value = "page", required = false) String page,
                                             @PathVariable("enName") String enName,
                                             ModelAndView mv) throws NoHandlerFoundException {
        SysKeywordTag sysKeywordTag = null;
        try {
            sysKeywordTag = new SysKeywordTag(enName);
        } catch (NotExistException e) {
            noHandlerFoundException();
        }
        assert sysKeywordTag != null;
        mv.addObject("catTitle", "标签：" + sysKeywordTag.getZhName());
        ListData<BlogVO> allPostList = blogService.getAllPostListByTagName(sysKeywordTag, getSignUser(), false, NumberUtils.parseInt(page, 1), 15);
        PostPageView<List<BlogDomain>> postPageView = buildPageView(PostPageView.class, allPostList.getData());
        assert systemConfig != null;
        postPageView.getPageHead().setTitle("标签：" + sysKeywordTag.getZhName() + " - 博客文章标签分类 - " + systemConfig.getSiteName());
        postPageView.getPageHead().setKeywords(sysKeywordTag.getZhName() + ",博客,blog,开发,技术,posts");
        postPageView.getPageHead().setDescription("博客文章标签分类：" + sysKeywordTag.getZhName() + "。共同类型的文章在这里聚合等待您的查阅。");
        mv.addObject("pageView", postPageView);
        mv.addObject("PostSidebar", blogService.buildPostSidebar(getSignUser()));
        setPagination(paginationService, mv, page, allPostList.getTotal(), "/posts/tag/" + sysKeywordTag.getEnName() + "?page=");
        mv.setViewName("blog/list");
        return mv;
    }

    /**
     * 根据分类获取所有文章列表
     *
     * @param page 页码
     * @return
     */
    @RequestMapping("cat/{enName}")
    @OperationLog(module = SystemTypeEnum.BLOG, desc = "根据分类获取所有文章列表页面")
    public ModelAndView getAllPostsListByCat(@RequestParam(value = "page", required = false) String page,
                                             @PathVariable("enName") String enName,
                                             ModelAndView mv) throws NoHandlerFoundException {
        ListData<BlogVO> allPostList = blogService.getAllPostListByCatName(enName, getSignUser(), false, NumberUtils.parseInt(page, 1), 15);
        if (allPostList.getTotal() == 0) {
            noHandlerFoundException();
        }
        Category category = allPostList.getData().get(0).getCategory();
        PostPageView<List<BlogDomain>> postPageView = buildPageView(PostPageView.class, allPostList.getData());
        assert systemConfig != null;
        mv.addObject("catTitle", "文章分类：" + category.getZhName());
        postPageView.getPageHead().setTitle("分类：" + category.getZhName() + " - 博客文章分类 - " + systemConfig.getSiteName());
        postPageView.getPageHead().setKeywords(category.getZhName() + ",博客,blog,开发,技术,posts");
        postPageView.getPageHead().setDescription("博客文章分类：" + category.getZhName() + "。共同类型的文章在这里聚合等待您的查阅。");
        mv.addObject("pageView", postPageView);
        mv.addObject("PostSidebar", blogService.buildPostSidebar(getSignUser()));
        setPagination(paginationService, mv, page, allPostList.getTotal(), "/posts/cat/" + enName + "?page=");
        mv.setViewName("blog/list");
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
        assert systemConfig != null;
        RedirectView redirectView = new RedirectView(systemConfig.getSiteDomainName() + "/posts/" + id);
        redirectView.setStatusCode(HttpStatus.MOVED_PERMANENTLY);
        return redirectView;
    }
}
