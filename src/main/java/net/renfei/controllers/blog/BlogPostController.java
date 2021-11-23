package net.renfei.controllers.blog;

import lombok.extern.slf4j.Slf4j;
import net.renfei.controllers.BaseController;
import net.renfei.domain.BlogDomain;
import net.renfei.exception.BlogPostNeedPasswordException;
import net.renfei.exception.BlogPostNotExistException;
import net.renfei.exception.SecretLevelException;
import net.renfei.services.BlogService;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.servlet.ModelAndView;
import org.springframework.web.servlet.NoHandlerFoundException;
import org.springframework.web.servlet.view.RedirectView;

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

    public BlogPostController(BlogService blogService) {
        this.blogService = blogService;
    }

    /**
     * 博客文章详情页面
     *
     * @param mv ModelAndView
     * @param id 文章ID
     * @return ModelAndView
     * @throws NoHandlerFoundException 404不存在异常
     */
    @RequestMapping("{id}")
    public ModelAndView getPostInfo(ModelAndView mv, @PathVariable("id") Long id) throws NoHandlerFoundException {
        BlogDomain blogDomain = null;
        try {
            blogDomain = blogService.getBlogById(id, getSignUser());
        } catch (BlogPostNotExistException e) {
            noHandlerFoundException();
        } catch (BlogPostNeedPasswordException e) {
            // TODO 文章需要密码才能查看
        } catch (SecretLevelException e) {
            // TODO 保密等级无权查看此文章内容
        }
        mv.addObject("blogDomain", blogDomain);
        mv.setViewName("blog/post");
        blogService.view(blogDomain);
        return mv;
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
