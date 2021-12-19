package net.renfei.services;

import net.renfei.domain.BlogDomain;
import net.renfei.domain.user.User;
import net.renfei.exception.BlogPostNeedPasswordException;
import net.renfei.exception.NotExistException;
import net.renfei.exception.SecretLevelException;
import net.renfei.model.blog.PostSidebarVO;

/**
 * 博客服务
 *
 * @author renfei
 */
public interface BlogService {
    /**
     * 根据ID获取公开的博客文章
     *
     * @param id   文章ID
     * @param user 当前查看的用户
     * @return BlogDomain
     * @throws NotExistException     文章不存在异常
     * @throws BlogPostNeedPasswordException 文章需要密码异常
     * @throws SecretLevelException          保密等级异常
     */
    BlogDomain getBlogById(Long id, User user) throws NotExistException,
            BlogPostNeedPasswordException, SecretLevelException;

    /**
     * 根据ID获取公开的博客文章
     *
     * @param id       文章ID
     * @param user     当前查看的用户
     * @param password 查看文章的密码
     * @return BlogDomain
     * @throws NotExistException     文章不存在异常
     * @throws BlogPostNeedPasswordException 文章需要密码异常
     * @throws SecretLevelException          保密等级异常
     */
    BlogDomain getBlogById(Long id, User user, String password) throws NotExistException,
            BlogPostNeedPasswordException, SecretLevelException;

    /**
     * 根据ID、密码获取公开的博客文章
     *
     * @param id       文章ID
     * @param user     当前查看的用户
     * @param password 查看文章的密码
     * @param isAdmin  是否是管理员操作
     * @return BlogDomain
     * @throws NotExistException     文章不存在异常
     * @throws BlogPostNeedPasswordException 文章需要密码异常
     * @throws SecretLevelException          保密等级异常
     */
    BlogDomain getBlogById(Long id, User user, String password, boolean isAdmin)
            throws NotExistException, BlogPostNeedPasswordException, SecretLevelException;

    /**
     * 增加博客文章的阅读量，交给线程池异步执行
     *
     * @param blogDomain 博文领域对象
     */
    void view(BlogDomain blogDomain);

    /**
     * 构建博客侧边栏内容
     *
     * @return
     */
    PostSidebarVO buildPostSidebar(User user);
}
