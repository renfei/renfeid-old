package net.renfei.services.blog;

import lombok.extern.slf4j.Slf4j;
import net.renfei.domain.BlogDomain;
import net.renfei.domain.CommentDomain;
import net.renfei.domain.blog.Category;
import net.renfei.domain.blog.Post;
import net.renfei.domain.comment.Comment;
import net.renfei.domain.system.SysKeywordTag;
import net.renfei.model.system.SystemTypeEnum;
import net.renfei.domain.user.User;
import net.renfei.exception.NeedPasswordException;
import net.renfei.exception.NotExistException;
import net.renfei.exception.SecretLevelException;
import net.renfei.model.LinkTree;
import net.renfei.model.blog.PostSidebarVO;
import net.renfei.services.BaseService;
import net.renfei.services.BlogService;
import net.renfei.services.RedisService;
import net.renfei.utils.ApplicationContextUtil;
import org.springframework.scheduling.annotation.Async;
import org.springframework.stereotype.Service;

import java.text.SimpleDateFormat;
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
     * @throws NotExistException             文章不存在异常
     * @throws NeedPasswordException 文章需要密码异常
     * @throws SecretLevelException          保密等级异常
     */
    @Override
    public BlogDomain getBlogById(Long id, User user) throws NotExistException,
            NeedPasswordException, SecretLevelException {
        return getBlogById(id, user, null, false);
    }

    /**
     * 根据ID获取公开的博客文章
     *
     * @param id       文章ID
     * @param user     当前查看的用户
     * @param password 查看文章的密码
     * @return BlogDomain
     * @throws NotExistException             文章不存在异常
     * @throws NeedPasswordException 文章需要密码异常
     * @throws SecretLevelException          保密等级异常
     */
    @Override
    public BlogDomain getBlogById(Long id, User user, String password) throws NotExistException,
            NeedPasswordException, SecretLevelException {
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
     * @throws NotExistException             文章不存在异常
     * @throws NeedPasswordException 文章需要密码异常
     * @throws SecretLevelException          保密等级异常
     */
    @Override
    public BlogDomain getBlogById(Long id, User user, String password, boolean isAdmin)
            throws NotExistException, NeedPasswordException, SecretLevelException {
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
            List<LinkTree> blogCategoryLinks = new CopyOnWriteArrayList<>();
            List<Category> allCategoryList = BlogDomain.allBlogCategory(user);
            allCategoryList.forEach(blogCategory -> {
                LinkTree link = LinkTree.builder()
                        .href(SYSTEM_CONFIG.getSiteDomainName() + "/cat/posts/" + blogCategory.getEnName())
                        .text(blogCategory.getZhName())
                        .build();
                blogCategoryLinks.add(link);
            });
            // 标签云
            List<LinkTree> allTagList = new CopyOnWriteArrayList<>();
            List<SysKeywordTag> keywordTagList = SysKeywordTag.keywordTagList(SystemTypeEnum.BLOG);
            keywordTagList.forEach(keywordTag -> {
                LinkTree link = LinkTree.builder()
                        .href(SYSTEM_CONFIG.getSiteDomainName() + "/posts/tag/" + keywordTag.getEnName())
                        .text(keywordTag.getZhName() + " (" + keywordTag.getCount() + ")")
                        .build();
                allTagList.add(link);
            });
            // 最新评论
            List<LinkTree> lastCommentList = new CopyOnWriteArrayList<>();
            List<Comment> commentList = CommentDomain.lastCommentTop10(SystemTypeEnum.BLOG);
            if (commentList != null) {
                commentList.forEach(comment -> {
                    LinkTree link = LinkTree.builder()
                            .href(SYSTEM_CONFIG.getSiteDomainName() + "/posts/" + comment.getObjectId() + "#cmt" + comment.getId())
                            .text(comment.getContent())
                            .build();
                    lastCommentList.add(link);
                });
            }
            // 热文排行
            List<LinkTree> hotPostList = new CopyOnWriteArrayList<>();
            List<Post> postList = BlogDomain.hotPostTop10(user);
            postList.forEach(post -> {
                LinkTree link = LinkTree.builder()
                        .href(SYSTEM_CONFIG.getSiteDomainName() + "/posts/" + post.getId())
                        .text(post.getTitle())
                        .build();
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
                                .link(allTagList)
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

    @Override
    public String getJsonld(BlogDomain blogDomain) {
        SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd'T'HH:mm:ss'+08:00'");
        assert SYSTEM_CONFIG != null;
        return getCommonTop() +
                "\"@type\": \"NewsArticle\"," +
                "\"dateModified\":\"" + sdf.format(blogDomain.getPost().getPostDate()) + "\"," +
                "\"datePublished\":\"" + sdf.format(blogDomain.getPost().getPostDate()) + "\"," +
                "\"headline\":\"" + blogDomain.getPost().getTitle().replace("\"", "") + "\"," +
                "\"image\":\"" + (blogDomain.getPost().getFeaturedImage() == null ? "https://cdn.renfei.net/Logo/ogimage.png" : blogDomain.getPost().getFeaturedImage()) + "\"," +
                "\"author\":{" +
                "\"@type\": \"Person\"," +
                "\"name\": \"" + (blogDomain.getPost().getSourceName() == null ? "任霏" : blogDomain.getPost().getSourceName()) + "\"" +
                "}," +
                "\"publisher\":{" +
                "\"@type\": \"Organization\"," +
                "\"name\": \"" + SYSTEM_CONFIG.getSiteName() + "\"," +
                "\"logo\": {" +
                "\"@type\": \"ImageObject\"," +
                "\"url\": \"https://cdn.renfei.net/Logo/logo_112.png\"" +
                "}" +
                "}," +
                "\"description\": \"" + blogDomain.getPost().getExcerpt() + "\"," +
                "\"mainEntityOfPage\": {" +
                "\"@type\":\"WebPage\"," +
                "\"@id\":\"" + SYSTEM_CONFIG.getSiteDomainName() + "/posts/" + blogDomain.getPost().getId() + "\"" +
                "}," +
                "\"speakable\": {" +
                "\"@type\": \"SpeakableSpecification\"," +
                "\"xpath\": [" +
                "\"/html/head/title\"," +
                "\"/html/head/meta[@name='description']/@content\"" +
                "]" +
                "}" +
                "}" +
                "]" +
                "}";
    }

    private String getCommonTop() {
        return "{" +
                "\"@context\": \"http://schema.org/\"," +
                "\"@graph\": [" +
                "{" +
                "\"@type\": \"Organization\"," +
                "\"logo\": \"https://cdn.renfei.net/Logo/logo_112.png\"," +
                "\"url\": \"https://www.renfei.net\"" +
                "}," +
                "{" +
                "\"@type\": \"WebSite\"," +
                "\"url\": \"https://www.renfei.net\"," +
                "\"potentialAction\": {" +
                "\"@type\": \"SearchAction\"," +
                "\"target\": \"https://www.renfei.net/search?type=all&w={search_term_string}\"," +
                "\"query-input\": \"required name=search_term_string\"" +
                "}" +
                "}," +
                "{" +
                "\"@type\": \"Person\"," +
                "\"name\": \"任霏\"," +
                "\"url\": \"https://www.renfei.net\"," +
                "\"sameAs\": [" +
                "\"https://github.com/NeilRen\"," +
                "\"https://www.facebook.com/renfeii\"," +
                "\"https://twitter.com/renfeii\"," +
                "\"https://www.youtube.com/channel/UCPsjiVvFMS7piLgC-RHBWxg\"" +
                "]" +
                "}," +
                "{";
    }
}
