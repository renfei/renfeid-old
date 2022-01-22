package net.renfei.services.blog;

import net.renfei.domain.BlogDomain;
import net.renfei.domain.CommentDomain;
import net.renfei.domain.blog.Category;
import net.renfei.domain.blog.Post;
import net.renfei.domain.comment.Comment;
import net.renfei.domain.system.SysKeywordTag;
import net.renfei.domain.user.User;
import net.renfei.exception.NeedPasswordException;
import net.renfei.exception.NotExistException;
import net.renfei.exception.SecretLevelException;
import net.renfei.model.LinkTree;
import net.renfei.model.ListData;
import net.renfei.model.blog.PostSidebarVO;
import net.renfei.model.system.BlogVO;
import net.renfei.model.system.SystemTypeEnum;
import net.renfei.repositories.BlogPostsMapper;
import net.renfei.repositories.model.BlogPostsExample;
import net.renfei.repositories.model.BlogPostsWithBLOBs;
import net.renfei.services.BaseService;
import net.renfei.services.BlogService;
import net.renfei.services.RedisService;
import net.renfei.utils.PageRankUtil;
import net.renfei.utils.StringUtils;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
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
@Service
public class BlogServiceImpl extends BaseService implements BlogService {
    private static final Logger logger = LoggerFactory.getLogger(BlogServiceImpl.class);
    private static final String REDIS_KEY_BLOG = REDIS_KEY + "blog:";
    private static final Double DATE_WEIGHTED = 37.5D;
    private static final Double VIEW_WEIGHTED = 57.5D;
    private static final Double COMMENTHTED = 5D;
    private final RedisService redisService;
    private final BlogPostsMapper blogPostsMapper;

    public BlogServiceImpl(RedisService redisService, BlogPostsMapper blogPostsMapper) {
        this.redisService = redisService;
        this.blogPostsMapper = blogPostsMapper;
    }

    @Override
    public ListData<BlogVO> getAllPostList(User user, boolean isAdmin, int pages, int rows) {
        ListData<BlogVO> blogVOListData = null;
        ListData<BlogDomain> blogDomainListData;
        String redisKey = REDIS_KEY_BLOG + "post:list_" + pages + "_" + rows;
        assert SYSTEM_CONFIG != null;
        if (user == null) {
            // 未登录用户访问，可以用缓存
            if (SYSTEM_CONFIG.isEnableRedis()) {
                // 查询是否曾经缓存过对象，有缓存直接吐出去
                if (redisService.hasKey(redisKey)) {
                    Object object = redisService.get(redisKey);
                    if (object instanceof ListData) {
                        blogVOListData = (ListData<BlogVO>) object;
                    }
                }
            }
            if (blogVOListData == null) {
                blogDomainListData = BlogDomain.allPostList(user, isAdmin, pages, rows);
                blogVOListData = convert(blogDomainListData);
                if (SYSTEM_CONFIG.isEnableRedis()) {
                    redisService.set(redisKey, blogVOListData, SYSTEM_CONFIG.getDefaultCacheSeconds());
                }
            }
        } else {
            // 站内用户直接查最新
            blogDomainListData = BlogDomain.allPostList(user, isAdmin, pages, rows);
            blogVOListData = convert(blogDomainListData);
        }
        return blogVOListData;
    }

    @Override
    public ListData<BlogVO> getAllPostListByTagName(SysKeywordTag sysKeywordTag, User user, boolean isAdmin, int pages, int rows) {
        ListData<BlogVO> blogVOListData = null;
        ListData<BlogDomain> blogDomainListData;
        String redisKey = REDIS_KEY_BLOG + "post:tag:" + sysKeywordTag.getEnName() + ":list_" + pages + "_" + rows;
        assert SYSTEM_CONFIG != null;
        if (user == null) {
            // 未登录用户访问，可以用缓存
            if (SYSTEM_CONFIG.isEnableRedis()) {
                // 查询是否曾经缓存过对象，有缓存直接吐出去
                if (redisService.hasKey(redisKey)) {
                    Object object = redisService.get(redisKey);
                    if (object instanceof ListData) {
                        blogVOListData = (ListData<BlogVO>) object;
                    }
                }
            }
            if (blogVOListData == null) {
                blogDomainListData = BlogDomain.allPostListInId(sysKeywordTag.selectObjectIdList(SystemTypeEnum.BLOG), user, isAdmin, pages, rows);
                blogVOListData = convert(blogDomainListData);
                if (SYSTEM_CONFIG.isEnableRedis()) {
                    redisService.set(redisKey, blogVOListData, SYSTEM_CONFIG.getDefaultCacheSeconds());
                }
            }
        } else {
            // 站内用户直接查最新
            blogDomainListData = BlogDomain.allPostListInId(sysKeywordTag.selectObjectIdList(SystemTypeEnum.BLOG), user, isAdmin, pages, rows);
            blogVOListData = convert(blogDomainListData);
        }
        return blogVOListData;
    }

    @Override
    public ListData<BlogVO> getAllPostListByCatName(String catEnName, User user, boolean isAdmin, int pages, int rows) {
        ListData<BlogVO> blogVOListData = null;
        ListData<BlogDomain> blogDomainListData;
        String redisKey = REDIS_KEY_BLOG + "post:cat:" + catEnName + ":list_" + pages + "_" + rows;
        assert SYSTEM_CONFIG != null;
        if (user == null) {
            // 未登录用户访问，可以用缓存
            if (SYSTEM_CONFIG.isEnableRedis()) {
                // 查询是否曾经缓存过对象，有缓存直接吐出去
                if (redisService.hasKey(redisKey)) {
                    Object object = redisService.get(redisKey);
                    if (object instanceof ListData) {
                        blogVOListData = (ListData<BlogVO>) object;
                    }
                }
            }
            if (blogVOListData == null) {
                blogDomainListData = BlogDomain.allPostListByCatName(catEnName, user, isAdmin, pages, rows);
                blogVOListData = convert(blogDomainListData);
                if (SYSTEM_CONFIG.isEnableRedis()) {
                    redisService.set(redisKey, blogVOListData, SYSTEM_CONFIG.getDefaultCacheSeconds());
                }
            }
        } else {
            // 站内用户直接查最新
            blogDomainListData = BlogDomain.allPostListByCatName(catEnName, user, isAdmin, pages, rows);
            blogVOListData = convert(blogDomainListData);
        }
        return blogVOListData;
    }

    /**
     * 根据ID获取公开的博客文章
     *
     * @param id   文章ID
     * @param user 当前查看的用户
     * @return BlogDomain
     * @throws NotExistException     文章不存在异常
     * @throws NeedPasswordException 文章需要密码异常
     * @throws SecretLevelException  保密等级异常
     */
    @Override
    public BlogVO getBlogById(Long id, User user) throws NotExistException,
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
     * @throws NotExistException     文章不存在异常
     * @throws NeedPasswordException 文章需要密码异常
     * @throws SecretLevelException  保密等级异常
     */
    @Override
    public BlogVO getBlogById(Long id, User user, String password) throws NotExistException,
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
     * @throws NotExistException     文章不存在异常
     * @throws NeedPasswordException 文章需要密码异常
     * @throws SecretLevelException  保密等级异常
     */
    @Override
    public BlogVO getBlogById(Long id, User user, String password, boolean isAdmin)
            throws NotExistException, NeedPasswordException, SecretLevelException {
        BlogVO blogVO = null;
        String redisKey = REDIS_KEY_BLOG + "post:" + id;
        assert SYSTEM_CONFIG != null;
        if (SYSTEM_CONFIG.isEnableRedis()) {
            // 查询是否曾经缓存过对象，有缓存直接吐出去
            if (redisService.hasKey(redisKey)) {
                Object object = redisService.get(redisKey);
                if (object instanceof BlogVO) {
                    blogVO = (BlogVO) object;
                }
            }
        }
        if (blogVO == null) {
            BlogDomain blogDomain = new BlogDomain(id, user, password, isAdmin);
            blogVO = convert(blogDomain);
            if (SYSTEM_CONFIG.isEnableRedis()) {
                redisService.set(redisKey, blogVO, SYSTEM_CONFIG.getDefaultCacheSeconds());
            }
        }
        return blogVO;
    }

    /**
     * 增加博客文章的阅读量，交给线程池异步执行
     *
     * @param blogVO 博文领域对象
     */
    @Override
    @Async
    public void view(BlogVO blogVO, User user, String password) {
        BlogDomain blogDomain = null;
        try {
            blogDomain = new BlogDomain(blogVO.getPost().getId(), user, password, false);
            blogDomain.view();
        } catch (NotExistException e) {
            e.printStackTrace();
        } catch (NeedPasswordException e) {
            e.printStackTrace();
        } catch (SecretLevelException e) {
            e.printStackTrace();
        }
    }

    @Override
    public List<Post> getRelated(Post post, User user) {
        List<Post> posts = null;
        String redisKey = REDIS_KEY_BLOG + "post:related:" + post.getId();
        assert SYSTEM_CONFIG != null;
        if (user == null) {
            // 未登录用户访问，可以用缓存
            if (SYSTEM_CONFIG.isEnableRedis()) {
                // 查询是否曾经缓存过对象，有缓存直接吐出去
                if (redisService.hasKey(redisKey)) {
                    Object object = redisService.get(redisKey);
                    if (object instanceof ListData) {
                        posts = (List<Post>) object;
                    }
                }
            }
        }
        if (posts == null) {
            List<Long> ids = SysKeywordTag.cognateIdList(SystemTypeEnum.BLOG, post.getId());
            if (ids.size() == 0) {
                return null;
            }
            ListData<BlogDomain> blogDomainListData = BlogDomain.allPostListInId(ids, user, false, 1, 10);
            if (blogDomainListData.getData() == null || blogDomainListData.getData().size() < 1) {
                return new ArrayList<>();
            }
            posts = new ArrayList<>();
            for (BlogDomain blogDomain : blogDomainListData.getData()
            ) {
                posts.add(blogDomain.getPost());
            }
            if (SYSTEM_CONFIG.isEnableRedis()) {
                redisService.set(redisKey, posts, SYSTEM_CONFIG.getDefaultCacheSeconds());
            }
        }
        return posts;
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
                        .href(SYSTEM_CONFIG.getSiteDomainName() + "/posts/cat/" + blogCategory.getEnName())
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
                            .text(StringUtils.substring(comment.getContent(), 38, "..."))
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
            List<PostSidebarVO.PostSidebar> postSidebars = new ArrayList<>();
            PostSidebarVO.PostSidebar catSidebar = new PostSidebarVO.PostSidebar();
            PostSidebarVO.PostSidebar tagSidebar = new PostSidebarVO.PostSidebar();
            PostSidebarVO.PostSidebar commentSidebar = new PostSidebarVO.PostSidebar();
            PostSidebarVO.PostSidebar hotSidebar = new PostSidebarVO.PostSidebar();
            catSidebar.setTitle("文档分类");
            catSidebar.setLink(blogCategoryLinks);
            tagSidebar.setTitle("标签云");
            tagSidebar.setLink(allTagList);
            commentSidebar.setTitle("最新留言");
            commentSidebar.setLink(lastCommentList);
            hotSidebar.setTitle("热文排行");
            hotSidebar.setLink(hotPostList);
            postSidebars.add(catSidebar);
            postSidebars.add(tagSidebar);
            postSidebars.add(commentSidebar);
            postSidebars.add(hotSidebar);
            postSidebarVO = new PostSidebarVO();
            postSidebarVO.setPostSidebars(postSidebars);
            if (SYSTEM_CONFIG.isEnableRedis()) {
                redisService.set(redisKey, postSidebarVO, SYSTEM_CONFIG.getDefaultCacheSeconds());
            }
        }
        return postSidebarVO;
    }

    @Override
    public String getJsonld(BlogVO blogVO) {
        SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd'T'HH:mm:ss'+08:00'");
        assert SYSTEM_CONFIG != null;
        return getCommonTop() +
                "\"@type\": \"NewsArticle\"," +
                "\"dateModified\":\"" + sdf.format(blogVO.getPost().getPostDate()) + "\"," +
                "\"datePublished\":\"" + sdf.format(blogVO.getPost().getPostDate()) + "\"," +
                "\"headline\":\"" + blogVO.getPost().getTitle().replace("\"", "") + "\"," +
                "\"image\":\"" + (blogVO.getPost().getFeaturedImage() == null ? "https://cdn.renfei.net/Logo/ogimage.png" : blogVO.getPost().getFeaturedImage()) + "\"," +
                "\"author\":{" +
                "\"@type\": \"Person\"," +
                "\"name\": \"" + (blogVO.getPost().getSourceName() == null ? "任霏" : blogVO.getPost().getSourceName()) + "\"" +
                "}," +
                "\"publisher\":{" +
                "\"@type\": \"Organization\"," +
                "\"name\": \"" + SYSTEM_CONFIG.getSiteName() + "\"," +
                "\"logo\": {" +
                "\"@type\": \"ImageObject\"," +
                "\"url\": \"https://cdn.renfei.net/Logo/logo_112.png\"" +
                "}" +
                "}," +
                "\"description\": \"" + blogVO.getPost().getExcerpt() + "\"," +
                "\"mainEntityOfPage\": {" +
                "\"@type\":\"WebPage\"," +
                "\"@id\":\"" + SYSTEM_CONFIG.getSiteDomainName() + "/posts/" + blogVO.getPost().getId() + "\"" +
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

    @Override
    public void updatePageRank() {
        BlogPostsExample example = new BlogPostsExample();
        example.createCriteria();
        List<BlogPostsWithBLOBs> blogPosts = blogPostsMapper.selectByExampleWithBLOBs(example);
        for (BlogPostsWithBLOBs blogPost : blogPosts
        ) {
            setPageRank(blogPost);
            long id = blogPost.getId();
            example = new BlogPostsExample();
            example.createCriteria()
                    .andIdEqualTo(id);
            blogPost.setId(null);
            blogPostsMapper.updateByExampleSelective(blogPost, example);
        }
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

    private ListData<BlogVO> convert(ListData<BlogDomain> blogDomainListData) {
        ListData<BlogVO> blogVOListData = new ListData<>();
        blogVOListData.setTotal(blogDomainListData.getTotal());
        blogVOListData.setEndRow(blogDomainListData.getEndRow());
        blogVOListData.setPages(blogDomainListData.getPages());
        blogVOListData.setTotal(blogDomainListData.getTotal());
        blogVOListData.setPageNum(blogDomainListData.getPageNum());
        blogVOListData.setStartRow(blogDomainListData.getStartRow());
        List<BlogVO> blogVOS = new CopyOnWriteArrayList<>();
        blogDomainListData.getData().forEach(blogDomain -> blogVOS.add(convert(blogDomain)));
        blogVOListData.setData(blogVOS);
        return blogVOListData;
    }

    private BlogVO convert(BlogDomain blogDomain) {
        BlogVO blogVO = new BlogVO();
        blogVO.setPost(blogDomain.getPost());
        blogVO.setAuthor(blogDomain.getAuthor());
        blogVO.setCategory(blogDomain.getCategory());
        blogVO.setCommentList(blogDomain.getCommentList());
        return blogVO;
    }

    private void setPageRank(BlogPostsWithBLOBs postsDO) {
        PageRankUtil pageRankUtil = new PageRankUtil();
        postsDO.setPageRank(pageRankUtil.getPageRank(
                postsDO.getPostDate(),
                postsDO.getPostViews(),
                0L,
                DATE_WEIGHTED, VIEW_WEIGHTED, COMMENTHTED
        ));
        postsDO.setAvgViews(pageRankUtil.getAvgViews(
                postsDO.getPostDate(),
                postsDO.getPostViews()
        ));
        postsDO.setAvgComment(pageRankUtil.getAvgComments(
                postsDO.getPostDate(),
                0L
        ));
    }
}
