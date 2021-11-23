package net.renfei.services.blog;

import lombok.extern.slf4j.Slf4j;
import net.renfei.domain.BlogDomain;
import net.renfei.domain.user.User;
import net.renfei.exception.BlogPostNeedPasswordException;
import net.renfei.exception.BlogPostNotExistException;
import net.renfei.exception.SecretLevelException;
import net.renfei.model.PostStatus;
import net.renfei.model.SecretLevel;
import net.renfei.services.BaseService;
import net.renfei.services.BlogService;
import net.renfei.services.RedisService;
import org.springframework.context.annotation.Lazy;
import org.springframework.scheduling.annotation.Async;
import org.springframework.stereotype.Service;
import org.springframework.util.ObjectUtils;

/**
 * 博客服务的实现
 *
 * @author renfei
 */
@Lazy
@Slf4j
@Service
public class BlogServiceImpl extends BaseService implements BlogService {
    private static final String REDIS_KEY_BLOG = REDIS_KEY + "blog:";
    private final RedisService redisService;

    public BlogServiceImpl(RedisService redisService) {
        this.redisService = redisService;
    }

    /**
     * 根据ID获取公开的博客文章
     *
     * @param id   文章ID
     * @param user 当前查看的用户
     * @return BlogDomain
     * @throws BlogPostNotExistException     文章不存在异常
     * @throws BlogPostNeedPasswordException 文章需要密码异常
     * @throws SecretLevelException          保密等级异常
     */
    @Override
    public BlogDomain getBlogById(Long id, User user) throws BlogPostNotExistException,
            BlogPostNeedPasswordException, SecretLevelException {
        BlogDomain blogDomain;
        String redisKey = REDIS_KEY_BLOG + "post:" + id;
        if (redisService.hasKey(redisKey)) {
            Object object = redisService.get(redisKey);
            if (object instanceof BlogDomain) {
                return (BlogDomain) object;
            }
        }
        blogDomain = new BlogDomain(id);
        // 判断文章状态和保密等级权限
        if (!PostStatus.PUBLISH.equals(blogDomain.getPost().getPostStatus())) {
            throw new BlogPostNotExistException("文章当前状态不可被阅读。");
        }
        if (!ObjectUtils.isEmpty(blogDomain.getPost().getPostPassword())) {
            throw new BlogPostNeedPasswordException("文章需要密码才能查看。");
        }
        if (user != null) {
            if (user.getSecretLevel().getLevel() < blogDomain.getPost().getSecretLevel().getLevel()) {
                throw new SecretLevelException("您当前的保密等级无权查看此文章内容。");
            }
        } else if (SecretLevel.UNCLASSIFIED.getLevel() < blogDomain.getPost().getSecretLevel().getLevel()) {
            throw new SecretLevelException("当前文章内容受到保密系统保护，请先登陆后查看。");
        }
        return blogDomain;
    }

    /**
     * 增加博客文章的阅读量，交给线程池异步执行
     *
     * @param blogDomain 博文领域对象
     */
    @Override
    @Async
    public void view(BlogDomain blogDomain) {
        blogDomain.view();
    }
}
