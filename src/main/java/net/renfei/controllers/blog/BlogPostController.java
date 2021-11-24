package net.renfei.controllers.blog;

import lombok.extern.slf4j.Slf4j;
import net.renfei.controllers.BaseController;
import net.renfei.domain.BlogDomain;
import net.renfei.exception.BlogPostNeedPasswordException;
import net.renfei.exception.BlogPostNotExistException;
import net.renfei.exception.SecretLevelException;
import net.renfei.model.blog.PostPageView;
import net.renfei.services.BlogService;
import net.renfei.services.RedisService;
import net.renfei.utils.ApplicationContextUtil;
import org.springframework.context.annotation.Lazy;
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
@Lazy
@Slf4j
@Controller
@RequestMapping("/posts")
public class BlogPostController extends BaseController {
    private static final String REDIS_KEY_PAGE_BLOG = "renfeid:page:blog:";
    private final BlogService blogService;
    private RedisService redisService;

    {
        blogService = (BlogService) ApplicationContextUtil.getBean("blogServiceImpl");
        if (SYSTEM_CONFIG.isEnableRedis()) {
            redisService = (RedisService) ApplicationContextUtil.getBean("redisServiceImpl");
        }
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
        PostPageView postPageView = null;
        BlogDomain blogDomain = null;
        String redisKey = REDIS_KEY_PAGE_BLOG + id;
        assert SYSTEM_CONFIG != null;
        if (SYSTEM_CONFIG.isEnableRedis()){
            // 查询是否曾经缓存过对象，有缓存直接吐出去
            if (redisService.hasKey(redisKey)) {
                Object object = redisService.get(redisKey);
                if (object instanceof PostPageView) {
                    postPageView = (PostPageView) object;
                }
            }
        }
        if (postPageView == null) {
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
            postPageView = buildPageView(PostPageView.class, blogDomain);
            if (SYSTEM_CONFIG.isEnableRedis()){
                redisService.set(redisKey, postPageView, SYSTEM_CONFIG.getDefaultCacheSeconds());
            }
        } else {
            blogDomain = (BlogDomain) postPageView.getObject();
        }
        mv.addObject("postPageView", postPageView);
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
