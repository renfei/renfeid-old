package net.renfei.controllers.blog;

import lombok.extern.slf4j.Slf4j;
import net.renfei.controllers.BaseController;
import net.renfei.domain.BlogDomain;
import net.renfei.domain.blog.Post;
import net.renfei.exception.BlogPostNeedPasswordException;
import net.renfei.exception.NotExistException;
import net.renfei.exception.SecretLevelException;
import net.renfei.model.*;
import net.renfei.model.blog.PostPageView;
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
    public ModelAndView getPostList(ModelAndView mv,
                                    @RequestParam(value = "page", required = false) String page) {
        mv.addObject("catTitle", "全部文档");
        ListData<BlogDomain> blogDomainListData = BlogDomain.allPostList(getSignUser(), false, NumberUtils.parseInt(page, 1), 15);
        PostPageView<List<BlogDomain>> postPageView = buildPageView(PostPageView.class, blogDomainListData.getData());
        assert SYSTEM_CONFIG != null;
        postPageView.getPageHead().setTitle("任霏的博客文章 - " + SYSTEM_CONFIG.getSiteName());
        mv.addObject("pageView", postPageView);
        mv.addObject("PostSidebar", blogService.buildPostSidebar(getSignUser()));
        setPagination(paginationService, mv, page, blogDomainListData.getTotal(), "/posts?page=");
        mv.setViewName("blog/list");
        return mv;
    }

    /**
     * 博客列表错误地址重定向
     *
     * @return
     */
    @RequestMapping({"index.html", "index.htm", "index.xhtml", "index.shtml"})
    public RedirectView getPostListDir() {
        RedirectView redirectView = new RedirectView("/posts/");
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
    public ModelAndView getPostInfo(ModelAndView mv, @PathVariable("id") Long id) throws NoHandlerFoundException {
        BlogDomain blogDomain = null;
        assert blogService != null;
        // 页面没查到缓存，去数据库查询
        try {
            blogDomain = blogService.getBlogById(id, getSignUser());
        } catch (NotExistException e) {
            noHandlerFoundException();
        } catch (BlogPostNeedPasswordException e) {
            // TODO 文章需要密码才能查看
        } catch (SecretLevelException e) {
            // TODO 保密等级无权查看此文章内容
        }
        if (blogDomain == null) {
            noHandlerFoundException();
        }
        PostPageView<BlogDomain> postPageView = buildPageView(PostPageView.class, blogDomain);
        assert blogDomain != null;
        SocialSharing socialSharing = new SocialSharing(blogDomain);
        mv.addObject("pageView", postPageView);
        mv.addObject("socialSharing", socialSharing);
        mv.addObject("PostSidebar", blogService.buildPostSidebar(getSignUser()));
        assert SYSTEM_CONFIG != null;
        postPageView.getPageHead().setTitle(blogDomain.getPost().getTitle() + " - Posts - " + SYSTEM_CONFIG.getSiteName());
        mv.addObject("jsonld", blogService.getJsonld(blogDomain));
        postPageView.getPageHead().setOgProtocol(OGProtocol.builder()
                .author(blogDomain.getPost().getSourceName())
                .description(blogDomain.getPost().getExcerpt())
                .image(blogDomain.getPost().getFeaturedImage())
                .locale("zh_CN")
                .releaseDate(blogDomain.getPost().getPostDate())
                .siteName("RenFei.Net")
                .title(blogDomain.getPost().getTitle())
                .type("article")
                .url(SYSTEM_CONFIG.getSiteDomainName() + "/posts/" + blogDomain.getPost().getId())
                .build());
        // TODO 获取文章扩展信息
        mv.setViewName("blog/post");
        blogService.view(blogDomain);
        return mv;
    }

    /**
     * 使用密码请求博客文章详情
     *
     * @param id       文章ID
     * @param password 文章密码
     * @return
     */
    @ResponseBody
    @PostMapping("{id}")
    public APIResult<Post> getPostInfo(@PathVariable("id") Long id, @RequestBody String password) {
        BlogDomain blogDomain;
        assert blogService != null;
        // 页面没查到缓存，去数据库查询
        try {
            blogDomain = blogService.getBlogById(id, getSignUser(), password);
        } catch (NotExistException e) {
            return APIResult.builder()
                    .code(StateCode.Failure)
                    .message("文章不存在。")
                    .build();
        } catch (BlogPostNeedPasswordException e) {
            return APIResult.builder()
                    .code(StateCode.Forbidden)
                    .message("文章密码不正确。")
                    .build();
        } catch (SecretLevelException e) {
            return APIResult.builder()
                    .code(StateCode.Forbidden)
                    .message("根据您的保密等级，您无权查看此内容。")
                    .build();
        }
        blogService.view(blogDomain);
        return new APIResult<>(blogDomain.getPost());
    }

    /**
     * 博客文章错误地址重定向
     *
     * @param id 文章ID
     * @return RedirectView
     */
    @RequestMapping({"{id}/index.html", "{id}/index.htm", "{id}/index.xhtml", "{id}/index.shtml"})
    public RedirectView getPostInfoDir(@PathVariable("id") String id) {
        RedirectView redirectView = new RedirectView("/posts/" + id);
        redirectView.setStatusCode(HttpStatus.MOVED_PERMANENTLY);
        return redirectView;
    }
}
