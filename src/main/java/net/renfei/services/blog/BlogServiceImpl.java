package net.renfei.services.blog;

import lombok.extern.slf4j.Slf4j;
import net.renfei.domain.BlogDomain;
import net.renfei.domain.CommentDomain;
import net.renfei.domain.blog.Category;
import net.renfei.domain.blog.Post;
import net.renfei.domain.comment.Comment;
import net.renfei.domain.system.SystemTypeEnum;
import net.renfei.domain.user.User;
import net.renfei.exception.BlogPostNeedPasswordException;
import net.renfei.exception.BlogPostNotExistException;
import net.renfei.exception.SecretLevelException;
import net.renfei.model.LinkVO;
import net.renfei.model.PostStatus;
import net.renfei.model.SecretLevel;
import net.renfei.model.blog.PostSidebarVO;
import net.renfei.repositories.model.BlogCategory;
import net.renfei.services.BaseService;
import net.renfei.services.BlogService;
import net.renfei.services.RedisService;
import net.renfei.utils.ApplicationContextUtil;
import net.renfei.utils.PasswordUtils;
import org.springframework.scheduling.annotation.Async;
import org.springframework.stereotype.Service;
import org.springframework.util.ObjectUtils;

import java.util.ArrayList;
import java.util.List;
import java.util.concurrent.CopyOnWriteArrayList;

/**
 * 博客服务的实现
 *
 * @author renfei
 */
@Slf4j
@Service
public class BlogServiceImpl extends BaseService implements BlogService {
    private static final String REDIS_KEY_BLOG = REDIS_KEY + "blog:";
    private RedisService redisService;

    {
        if (SYSTEM_CONFIG.isEnableRedis()) {
            redisService = (RedisService) ApplicationContextUtil.getBean("redisServiceImpl");
        }
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
        return getBlogById(id, user, null, false);
    }

    /**
     * 根据ID获取公开的博客文章
     *
     * @param id       文章ID
     * @param user     当前查看的用户
     * @param password 查看文章的密码
     * @return BlogDomain
     * @throws BlogPostNotExistException     文章不存在异常
     * @throws BlogPostNeedPasswordException 文章需要密码异常
     * @throws SecretLevelException          保密等级异常
     */
    @Override
    public BlogDomain getBlogById(Long id, User user, String password) throws BlogPostNotExistException,
            BlogPostNeedPasswordException, SecretLevelException {
        return getBlogById(id, user, password, false);
    }

    /**
     * 根据ID、密码获取公开的博客文章
     *
     * @param id       文章ID
     * @param user     当前查看的用户
     * @param password 查看文章的密码
     * @param isAdmin  是否是管理员操作
     * @return BlogDomain
     * @throws BlogPostNotExistException     文章不存在异常
     * @throws BlogPostNeedPasswordException 文章需要密码异常
     * @throws SecretLevelException          保密等级异常
     */
    @Override
    public BlogDomain getBlogById(Long id, User user, String password, boolean isAdmin)
            throws BlogPostNotExistException, BlogPostNeedPasswordException, SecretLevelException {
        BlogDomain blogDomain = null;
        String redisKey = REDIS_KEY_BLOG + "post:" + id;
        assert SYSTEM_CONFIG != null;
        if (SYSTEM_CONFIG.isEnableRedis()) {
            // 查询是否曾经缓存过对象，有缓存直接吐出去
            if (redisService.hasKey(redisKey)) {
                Object object = redisService.get(redisKey);
                if (object instanceof BlogDomain) {
                    blogDomain = (BlogDomain) object;
                }
            }
        }
        if (blogDomain == null) {
            blogDomain = new BlogDomain(id, user, password, isAdmin);
            if (SYSTEM_CONFIG.isEnableRedis()) {
                redisService.set(redisKey, blogDomain, SYSTEM_CONFIG.getDefaultCacheSeconds());
            }
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

    /**
     * 构建博客侧边栏内容
     *
     * @return
     */
    @Override
    public PostSidebarVO buildPostSidebar(User user) {
        PostSidebarVO postSidebarVO = null;
        String redisKey = REDIS_KEY_BLOG + "post:sidebar";
        assert SYSTEM_CONFIG != null;
        if (SYSTEM_CONFIG.isEnableRedis()) {
            // 查询是否曾经缓存过对象，有缓存直接吐出去
            if (redisService.hasKey(redisKey)) {
                Object object = redisService.get(redisKey);
                if (object instanceof PostSidebarVO) {
                    postSidebarVO = (PostSidebarVO) object;
                }
            }
        }
        if (postSidebarVO == null) {
            // 缓存未命中，从数据库中组织数据
            // 博客分类们
            List<LinkVO> blogCategoryLinks = new CopyOnWriteArrayList<>();
            List<Category> allCategoryList = BlogDomain.allBlogCategory(user);
            allCategoryList.forEach(blogCategory -> {
                LinkVO link = new LinkVO();
                link.setHref(SYSTEM_CONFIG.getSiteDomainName() + "/cat/posts/" + blogCategory.getEnName());
                link.setText(blogCategory.getZhName());
                blogCategoryLinks.add(link);
            });
            // 最新评论
            List<LinkVO> lastCommentList = new CopyOnWriteArrayList<>();
            CommentDomain commentDomain = new CommentDomain();
            List<Comment> commentList = commentDomain.lastCommentTop10(SystemTypeEnum.BLOG);
            if (commentList != null) {
                commentList.forEach(comment -> {
                    LinkVO link = new LinkVO();
                    link.setHref(SYSTEM_CONFIG.getSiteDomainName() + "/posts/" + comment.getObjectId() + "#cmt" + comment.getId());
                    link.setText(comment.getContent());
                    lastCommentList.add(link);
                });
            }
            // 热文排行
            List<LinkVO> hotPostList = new CopyOnWriteArrayList<>();
            List<Post> postList = BlogDomain.hotPostTop10(user);
            postList.forEach(post -> {
                LinkVO link = new LinkVO();
                link.setHref(SYSTEM_CONFIG.getSiteDomainName() + "/posts/" + post.getId());
                link.setText(post.getTitle());
                hotPostList.add(link);
            });
            postSidebarVO = PostSidebarVO.builder()
                    .postSidebars(new ArrayList<PostSidebarVO.PostSidebar>() {{
                        this.add(PostSidebarVO.PostSidebar.builder()
                                .title("文档分类")
                                .link(blogCategoryLinks)
                                .build());
                        this.add(PostSidebarVO.PostSidebar.builder()
                                .title("标签云")
                                .link(null)
                                .build());
                        this.add(PostSidebarVO.PostSidebar.builder()
                                .title("最新留言")
                                .link(lastCommentList)
                                .build());
                        this.add(PostSidebarVO.PostSidebar.builder()
                                .title("热文排行")
                                .link(hotPostList)
                                .build());
                    }})
                    .build();
            if (SYSTEM_CONFIG.isEnableRedis()) {
                redisService.set(redisKey, postSidebarVO, SYSTEM_CONFIG.getDefaultCacheSeconds());
            }
        }
        return postSidebarVO;
    }
}
