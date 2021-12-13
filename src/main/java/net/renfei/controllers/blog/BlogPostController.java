package net.renfei.controllers.blog;

import lombok.extern.slf4j.Slf4j;
import net.renfei.controllers.BaseController;
import net.renfei.domain.BlogDomain;
import net.renfei.domain.blog.Post;
import net.renfei.exception.BlogPostNeedPasswordException;
import net.renfei.exception.BlogPostNotExistException;
import net.renfei.exception.SecretLevelException;
import net.renfei.model.APIResult;
import net.renfei.model.SocialSharing;
import net.renfei.model.StateCode;
import net.renfei.model.blog.PostPageView;
import net.renfei.services.BlogService;
import net.renfei.utils.ApplicationContextUtil;
import org.springframework.context.annotation.Lazy;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.servlet.ModelAndView;
import org.springframework.web.servlet.NoHandlerFoundException;
import org.springframework.web.servlet.view.RedirectView;

/**
 * 博客栏目
 *
 * @author renfei
 */
@Lazy
@Slf4j
@Controller
@RequestMapping("/posts")
public class BlogPostController extends BaseController {
    private static final String REDIS_KEY_PAGE_BLOG = "renfeid:page:blog:";
    private final BlogService blogService;

    {
        blogService = (BlogService) ApplicationContextUtil.getBean("blogServiceImpl");
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
        } catch (BlogPostNotExistException e) {
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
        SocialSharing socialSharing = new SocialSharing(blogDomain);
        mv.addObject("pageView", postPageView);
        mv.addObject("socialSharing", socialSharing);
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
        } catch (BlogPostNotExistException e) {
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
    @RequestMapping("{id}/index.html")
    public RedirectView getPostInfoDir(@PathVariable("id") String id) {
        RedirectView redirectView = new RedirectView("/posts/" + id);
        redirectView.setStatusCode(HttpStatus.MOVED_PERMANENTLY);
        return redirectView;
    }
}
