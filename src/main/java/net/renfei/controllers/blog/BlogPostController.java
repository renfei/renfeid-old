package net.renfei.controllers.blog;

import lombok.extern.slf4j.Slf4j;
import net.renfei.controllers.BaseController;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.servlet.ModelAndView;
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

    @RequestMapping("{id}")
    public ModelAndView getPostInfo(ModelAndView mv, @PathVariable("id") String id) {
        mv.setViewName("index");
        return mv;
    }

    @RequestMapping("{id}/index.html")
    public RedirectView getPostInfoDir(@PathVariable("id") String id) {
        RedirectView redirectView = new RedirectView("/posts/" + id);
        redirectView.setStatusCode(HttpStatus.MOVED_PERMANENTLY);
        return redirectView;
    }
}
