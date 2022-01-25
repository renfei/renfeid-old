package net.renfei.controllers.other;

import net.renfei.controllers.BaseController;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.servlet.view.RedirectView;

/**
 * 老旧的连接处理
 *
 * @author renfei
 */
@Controller
public class OldUriController extends BaseController {
    /**
     * 分类老地址，301跳转
     *
     * @return
     */
    @RequestMapping("/cat/posts/{enName}")
    public RedirectView catUri(@PathVariable(value = "enName") String enName) {
        assert systemConfig != null;
        RedirectView redirectView = new RedirectView(systemConfig.getSiteDomainName() + "/posts/cat/" + enName);
        redirectView.setStatusCode(HttpStatus.MOVED_PERMANENTLY);
        return redirectView;
    }
}
