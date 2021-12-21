package net.renfei.controllers._api.foreground.impl;

import net.renfei.controllers.BaseController;
import net.renfei.controllers._api.foreground.ForegroundApi;
import net.renfei.domain.BlogDomain;
import net.renfei.domain.blog.Post;
import net.renfei.exception.BlogPostNeedPasswordException;
import net.renfei.exception.NotExistException;
import net.renfei.exception.SecretLevelException;
import net.renfei.model.APIResult;
import net.renfei.model.StateCode;
import net.renfei.services.BlogService;
import org.springframework.web.bind.annotation.RestController;

/**
 * 前台接口
 *
 * @author renfei
 */
@RestController
public class ForegroundApiController extends BaseController implements ForegroundApi {
    private final BlogService blogService;

    public ForegroundApiController(BlogService blogService) {
        this.blogService = blogService;
    }

    /**
     * 使用密码获取文章详情
     *
     * @param id       文章ID
     * @param password 文章密码
     * @return
     */
    @Override
    public APIResult<Post> getPostInfoByPassword(Long id, String password) {
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
}
