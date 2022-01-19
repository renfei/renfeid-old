package net.renfei.domain;

import com.github.pagehelper.Page;
import com.github.pagehelper.PageHelper;
import net.renfei.domain.blog.Category;
import net.renfei.domain.blog.Post;
import net.renfei.domain.comment.Comment;
import net.renfei.domain.user.User;
import net.renfei.exception.NeedPasswordException;
import net.renfei.exception.NotExistException;
import net.renfei.exception.SecretLevelException;
import net.renfei.model.CommentStatusEnum;
import net.renfei.model.ListData;
import net.renfei.model.SecretLevelEnum;
import net.renfei.model.blog.PostStatusEnum;
import net.renfei.model.system.SystemTypeEnum;
import net.renfei.repositories.BlogCategoryMapper;
import net.renfei.repositories.BlogPostsMapper;
import net.renfei.repositories.model.BlogCategory;
import net.renfei.repositories.model.BlogCategoryExample;
import net.renfei.repositories.model.BlogPostsExample;
import net.renfei.repositories.model.BlogPostsWithBLOBs;
import net.renfei.utils.ApplicationContextUtil;
import net.renfei.utils.ListUtils;
import net.renfei.utils.SentryUtils;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.util.ObjectUtils;

import java.util.ArrayList;
import java.util.Date;
import java.util.List;
import java.util.concurrent.CopyOnWriteArrayList;

/**
 * 博客领域的聚合根
 * 与博客相关的领域对象都在这里
 *
 * @author renfei
 */
public final class BlogDomain {
    private static final Logger logger = LoggerFactory.getLogger(BlogDomain.class);
    private final Post post;
    private final User author;
    private final Category category;
    private final List<Comment> commentList;
    private final BlogPostsMapper blogPostsMapper;
    private final BlogCategoryMapper categoryMapper;

    {
        blogPostsMapper = (BlogPostsMapper) ApplicationContextUtil.getBean("blogPostsMapper");
        categoryMapper = (BlogCategoryMapper) ApplicationContextUtil.getBean("blogCategoryMapper");

    }

    /**
     * 私有的默认构造，禁止外部直接实例化默认配置
     */
    private BlogDomain() {
        post = null;
        author = null;
        category = null;
        commentList = null;
    }

    /**
     * 加载一篇博客文章
     *
     * @param id       博客文章ID
     * @param user     当前登陆用户
     * @param password 博客文章密码
     * @param isAdmin  是否是管理员管理操作
     * @throws NotExistException 文章不存在异常
     */
    public BlogDomain(Long id, User user, String password, boolean isAdmin)
            throws NotExistException, NeedPasswordException, SecretLevelException {
        if (id == null) {
            throw new NotExistException("博客文章不存在");
        }
        post = initPost(id);
        author = new User(post.getPostAuthor());
        category = initCategory(post.getCategoryId());
        commentList = new CommentDomain(SystemTypeEnum.BLOG, id).getCommentList();
        if (!isAdmin) {
            // 如果不是管理员操作，需要更多限制判断
            // 判断文章状态
            if (!PostStatusEnum.PUBLISH.equals(post.getPostStatus())) {
                throw new NotExistException("文章当前状态不可被阅读。");
            }
            // 判断密码正确性
            if (!ObjectUtils.isEmpty(post.getPostPassword())) {
                if (ObjectUtils.isEmpty(password)) {
                    throw new NeedPasswordException("文章需要密码才能查看。");
                } else {
                    // 判断密码是否正确
                    if (!password.equals(post.getPostPassword())) {
                        throw new NeedPasswordException("文章需要密码才能查看。");
                    }
                }
            }
        }
        // 判断保密等级，管理员也需要判断
        if (user != null) {
            if (user.getSecretLevelEnum().getLevel() < post.getSecretLevelEnum().getLevel()) {
                throw new SecretLevelException("您当前的保密等级无权查看此文章内容。");
            }
        } else if (SecretLevelEnum.UNCLASSIFIED.getLevel() < post.getSecretLevelEnum().getLevel()) {
            throw new SecretLevelException("当前文章内容受到保密系统保护，请先登陆后查看。");
        }
    }

    /**
     * 博文增加浏览量
     */
    public void view() {
        BlogPostsExample example = new BlogPostsExample();
        example.createCriteria().andIdEqualTo(post.getId());
        BlogPostsWithBLOBs blogPost = ListUtils.getOne(blogPostsMapper.selectByExampleWithBLOBs(example));
        blogPost.setPostViews(blogPost.getPostViews() + 1);
        blogPostsMapper.updateByExampleWithBLOBs(blogPost, example);
    }

    /**
     * 获取全部文章列表
     *
     * @param user    登录用户
     * @param isAdmin 是否是管理员操作
     * @param pages   页码
     * @param rows    每页容量
     * @return
     */
    public static ListData<BlogDomain> allPostList(User user, boolean isAdmin, int pages, int rows) {
        BlogDomain blogDomain = new BlogDomain();
        return blogDomain.getAllPostList(user, isAdmin, pages, rows);
    }

    /**
     * 获取全部文章列表
     *
     * @param ids     ID列表
     * @param user    登录用户
     * @param isAdmin 是否是管理员操作
     * @param pages   页码
     * @param rows    每页容量
     * @return
     */
    public static ListData<BlogDomain> allPostListInId(List<Long> ids, User user,
                                                       boolean isAdmin, int pages, int rows) {
        BlogDomain blogDomain = new BlogDomain();
        return blogDomain.getAllPostListInId(ids, user, isAdmin, pages, rows);
    }

    /**
     * 获取全部文章列表
     *
     * @param catEnName 分类名称
     * @param user      登录用户
     * @param isAdmin   是否是管理员操作
     * @param pages     页码
     * @param rows      每页容量
     * @return
     */
    public static ListData<BlogDomain> allPostListByCatName(String catEnName, User user,
                                                            boolean isAdmin, int pages, int rows) {
        BlogDomain blogDomain = new BlogDomain();
        return blogDomain.getAllPostListByCatName(catEnName, user, isAdmin, pages, rows);
    }

    /**
     * 获取全部文章列表
     *
     * @param user    登录用户
     * @param isAdmin 是否是管理员操作
     * @param pages   页码
     * @param rows    每页容量
     * @return
     */
    public ListData<BlogDomain> getAllPostList(User user, boolean isAdmin, int pages, int rows) {
        BlogPostsExample example = new BlogPostsExample();
        example.setOrderByClause("post_date DESC");
        BlogPostsExample.Criteria criteria = example.createCriteria();
        if (user != null) {
            // 登录用户，判断保密等级
            criteria.andSecretLevelLessThanOrEqualTo(user.getSecretLevelEnum().getLevel());
            if (isAdmin) {
                // 管理员，除了被删除和修订版本，其他都显示
                criteria
                        .andPostStatusNotEqualTo(PostStatusEnum.DELETED.toString())
                        .andPostStatusNotEqualTo(PostStatusEnum.REVISION.toString());
            } else {
                // 其他用户，只能查看已经发布的内容
                criteria
                        .andPostDateLessThanOrEqualTo(new Date())
                        .andPostStatusEqualTo(PostStatusEnum.PUBLISH.toString());
            }
        } else {
            // 未登录用户，只能查看非密内容、已经发布的内容
            criteria
                    .andSecretLevelLessThanOrEqualTo(SecretLevelEnum.UNCLASSIFIED.getLevel())
                    .andPostDateLessThanOrEqualTo(new Date())
                    .andPostStatusEqualTo(PostStatusEnum.PUBLISH.toString());
        }
        Page<BlogPostsWithBLOBs> page = PageHelper.startPage(pages, rows);
        blogPostsMapper.selectByExampleWithBLOBs(example);
        ListData<BlogDomain> blogDomainListData = new ListData<>(page);
        List<BlogDomain> data = new CopyOnWriteArrayList<>();
        for (BlogPostsWithBLOBs blogPostsWithBLOBs : page.getResult()
        ) {
            BlogDomain blogDomain;
            try {
                blogDomain = new BlogDomain(blogPostsWithBLOBs.getId(), user, blogPostsWithBLOBs.getPostPassword(), isAdmin);
            } catch (NotExistException | SecretLevelException | NeedPasswordException exception) {
                SentryUtils.captureException(exception);
                continue;
            }
            if (user == null || !isAdmin) {
                // 未登录或者不是管理员，需要密码的文章隐藏内容和概述
                if (!ObjectUtils.isEmpty(blogPostsWithBLOBs.getPostPassword())) {
                    blogDomain.getPost().setContent("");
                    blogDomain.getPost().setExcerpt("");
                }
            }
            data.add(blogDomain);
        }
        blogDomainListData.setData(data);
        return blogDomainListData;
    }

    /**
     * 获取全部文章列表
     *
     * @param ids     ID列表
     * @param user    登录用户
     * @param isAdmin 是否是管理员操作
     * @param pages   页码
     * @param rows    每页容量
     * @return
     */
    public ListData<BlogDomain> getAllPostListInId(List<Long> ids, User user, boolean isAdmin, int pages, int rows) {
        BlogPostsExample example = new BlogPostsExample();
        example.setOrderByClause("post_date DESC");
        BlogPostsExample.Criteria criteria = example.createCriteria();
        criteria.andIdIn(ids);
        if (user != null) {
            // 登录用户，判断保密等级
            criteria.andSecretLevelLessThanOrEqualTo(user.getSecretLevelEnum().getLevel());
            if (isAdmin) {
                // 管理员，除了被删除和修订版本，其他都显示
                criteria
                        .andPostStatusNotEqualTo(PostStatusEnum.DELETED.toString())
                        .andPostStatusNotEqualTo(PostStatusEnum.REVISION.toString());
            } else {
                // 其他用户，只能查看已经发布的内容
                criteria
                        .andPostDateLessThanOrEqualTo(new Date())
                        .andPostStatusEqualTo(PostStatusEnum.PUBLISH.toString());
            }
        } else {
            // 未登录用户，只能查看非密内容、已经发布的内容
            criteria
                    .andSecretLevelLessThanOrEqualTo(SecretLevelEnum.UNCLASSIFIED.getLevel())
                    .andPostDateLessThanOrEqualTo(new Date())
                    .andPostStatusEqualTo(PostStatusEnum.PUBLISH.toString());
        }
        Page<BlogPostsWithBLOBs> page = PageHelper.startPage(pages, rows);
        blogPostsMapper.selectByExampleWithBLOBs(example);
        ListData<BlogDomain> blogDomainListData = new ListData<>(page);
        List<BlogDomain> data = new CopyOnWriteArrayList<>();
        for (BlogPostsWithBLOBs blogPostsWithBLOBs : page.getResult()
        ) {
            BlogDomain blogDomain;
            try {
                blogDomain = new BlogDomain(blogPostsWithBLOBs.getId(), user, blogPostsWithBLOBs.getPostPassword(), isAdmin);
            } catch (NotExistException | SecretLevelException | NeedPasswordException exception) {
                SentryUtils.captureException(exception);
                continue;
            }
            if (user == null || !isAdmin) {
                // 未登录或者不是管理员，需要密码的文章隐藏内容和概述
                if (!ObjectUtils.isEmpty(blogPostsWithBLOBs.getPostPassword())) {
                    blogDomain.getPost().setContent("");
                    blogDomain.getPost().setExcerpt("");
                }
            }
            data.add(blogDomain);
        }
        blogDomainListData.setData(data);
        return blogDomainListData;
    }

    /**
     * 获取全部文章列表
     *
     * @param catEnName 分类名称
     * @param user      登录用户
     * @param isAdmin   是否是管理员操作
     * @param pages     页码
     * @param rows      每页容量
     * @return
     */
    public ListData<BlogDomain> getAllPostListByCatName(String catEnName, User user, boolean isAdmin, int pages, int rows) {
        BlogCategoryExample categoryExample = new BlogCategoryExample();
        BlogCategoryExample.Criteria catCriteria = categoryExample.createCriteria().andEnNameEqualTo(catEnName);
        BlogPostsExample example = new BlogPostsExample();
        example.setOrderByClause("post_date DESC");
        BlogPostsExample.Criteria criteria = example.createCriteria();
        if (user != null) {
            // 登录用户，判断保密等级
            criteria.andSecretLevelLessThanOrEqualTo(user.getSecretLevelEnum().getLevel());
            catCriteria.andSecretLevelLessThanOrEqualTo(user.getSecretLevelEnum().getLevel());
            if (isAdmin) {
                // 管理员，除了被删除和修订版本，其他都显示
                criteria
                        .andPostStatusNotEqualTo(PostStatusEnum.DELETED.toString())
                        .andPostStatusNotEqualTo(PostStatusEnum.REVISION.toString());
            } else {
                // 其他用户，只能查看已经发布的内容
                criteria
                        .andPostDateLessThanOrEqualTo(new Date())
                        .andPostStatusEqualTo(PostStatusEnum.PUBLISH.toString());
            }
        } else {
            // 未登录用户，只能查看非密内容、已经发布的内容
            criteria
                    .andSecretLevelLessThanOrEqualTo(SecretLevelEnum.UNCLASSIFIED.getLevel())
                    .andPostDateLessThanOrEqualTo(new Date())
                    .andPostStatusEqualTo(PostStatusEnum.PUBLISH.toString());
            catCriteria.andSecretLevelLessThanOrEqualTo(SecretLevelEnum.UNCLASSIFIED.getLevel());
        }
        BlogCategory category = ListUtils.getOne(categoryMapper.selectByExample(categoryExample));
        ListData<BlogDomain> blogDomainListData = new ListData<>();
        if (category == null) {
            blogDomainListData.setTotal(0);
            blogDomainListData.setPages(pages);
            blogDomainListData.setPageSize(rows);
            blogDomainListData.setData(new ArrayList<>());
            return blogDomainListData;
        }
        criteria.andCategoryIdEqualTo(category.getId());
        Page<BlogPostsWithBLOBs> page = PageHelper.startPage(pages, rows);
        blogPostsMapper.selectByExampleWithBLOBs(example);
        blogDomainListData = new ListData<>(page);
        List<BlogDomain> data = new CopyOnWriteArrayList<>();
        for (BlogPostsWithBLOBs blogPostsWithBLOBs : page.getResult()
        ) {
            BlogDomain blogDomain;
            try {
                blogDomain = new BlogDomain(blogPostsWithBLOBs.getId(), user, blogPostsWithBLOBs.getPostPassword(), isAdmin);
            } catch (NotExistException | SecretLevelException | NeedPasswordException exception) {
                SentryUtils.captureException(exception);
                continue;
            }
            if (user == null || !isAdmin) {
                // 未登录或者不是管理员，需要密码的文章隐藏内容和概述
                if (!ObjectUtils.isEmpty(blogPostsWithBLOBs.getPostPassword())) {
                    blogDomain.getPost().setContent("");
                    blogDomain.getPost().setExcerpt("");
                }
            }
            data.add(blogDomain);
        }
        blogDomainListData.setData(data);
        return blogDomainListData;
    }

    /**
     * 获取博客领域下所有分类
     *
     * @param user 当前登陆用户
     * @return
     */
    public static List<Category> allBlogCategory(User user) {
        BlogDomain blogDomain = new BlogDomain();
        return blogDomain.getAllBlogCategory(user);
    }

    /**
     * 获取博客领域下所有分类
     *
     * @param user 当前登陆用户
     * @return
     */
    private List<Category> getAllBlogCategory(User user) {
        BlogCategoryExample example = new BlogCategoryExample();
        BlogCategoryExample.Criteria criteria = example.createCriteria();
        if (user != null) {
            // 保密等级判断
            criteria.andSecretLevelLessThanOrEqualTo(user.getSecretLevelEnum().getLevel());
        } else {
            criteria.andSecretLevelEqualTo(SecretLevelEnum.UNCLASSIFIED.getLevel());
        }
        List<BlogCategory> blogCategories = categoryMapper.selectByExample(example);
        List<Category> categories = new CopyOnWriteArrayList<>();
        blogCategories.forEach(blogCategory -> categories.add(initCategory(blogCategory.getId())));
        return categories;
    }

    /**
     * 最热文章Top10
     *
     * @param user 当前登陆用户
     * @return
     */
    public static List<Post> hotPostTop10(User user) {
        BlogDomain blogDomain = new BlogDomain();
        return blogDomain.getHotPostTop10(user);
    }

    /**
     * 最热文章Top10
     *
     * @param user 当前登陆用户
     * @return
     */
    private List<Post> getHotPostTop10(User user) {
        BlogPostsExample example = new BlogPostsExample();
        BlogPostsExample.Criteria criteria = example.createCriteria();
        if (user != null) {
            // 保密等级判断
            criteria.andSecretLevelLessThanOrEqualTo(user.getSecretLevelEnum().getLevel());
        } else {
            criteria.andSecretLevelEqualTo(SecretLevelEnum.UNCLASSIFIED.getLevel());
        }
        criteria
                .andPostStatusEqualTo(PostStatusEnum.PUBLISH.toString())
                .andPostDateLessThanOrEqualTo(new Date());
        example.setOrderByClause("avg_views DESC,post_date DESC");
        PageHelper.startPage(1, 10);
        List<BlogPostsWithBLOBs> blogPostList = blogPostsMapper.selectByExampleWithBLOBs(example);
        List<Post> postList = new CopyOnWriteArrayList<>();
        blogPostList.forEach(blogPost -> postList.add(convert(blogPost)));
        return postList;
    }

    private Post initPost(Long id) throws NotExistException {
        BlogPostsExample example = new BlogPostsExample();
        example.createCriteria().andIdEqualTo(id);
        BlogPostsWithBLOBs blogPost = ListUtils.getOne(blogPostsMapper.selectByExampleWithBLOBs(example));
        if (blogPost == null || PostStatusEnum.DELETED.toString().equals(blogPost.getPostStatus())) {
            throw new NotExistException("博客文章不存在");
        }
        return convert(blogPost);
    }

    private Category initCategory(Long categoryId) {
        BlogCategory blogCategory = categoryMapper.selectByPrimaryKey(categoryId);
        if (blogCategory == null) {
            return null;
        }
        return Category.builder()
                .id(blogCategory.getId())
                .enName(blogCategory.getEnName())
                .zhName(blogCategory.getZhName())
                .secretLevelEnum(SecretLevelEnum.valueOf(blogCategory.getSecretLevel()))
                .build();
    }

    private Post convert(BlogPostsWithBLOBs blogPost) {
        Post post = new Post();
        post.setId(blogPost.getId());
        post.setTitle(blogPost.getPostTitle());
        post.setKeyword(blogPost.getPostKeyword());
        post.setExcerpt(blogPost.getPostExcerpt());
        post.setContent(blogPost.getPostContent());
        post.setFeaturedImage(blogPost.getFeaturedImage());
        post.setOriginal(blogPost.getIsOriginal());
        post.setSourceName(blogPost.getSourceName());
        post.setSourceUrl(blogPost.getSourceUrl());
        post.setPostDate(blogPost.getPostDate());
        post.setPostAuthor(blogPost.getPostAuthor());
        post.setCategoryId(blogPost.getCategoryId());
        post.setPostStatus(PostStatusEnum.valueOf(blogPost.getPostStatus()));
        post.setCommentStatusenum(CommentStatusEnum.valueOf(blogPost.getCommentStatus()));
        post.setPostPassword(blogPost.getPostPassword());
        post.setPostModified(blogPost.getPostModified());
        post.setPostParent(blogPost.getPostParent());
        post.setThumbsUp(blogPost.getThumbsUp());
        post.setThumbsDown(blogPost.getThumbsDown());
        post.setAvgViews(blogPost.getAvgViews());
        post.setAvgComment(blogPost.getAvgComment());
        post.setPageRank(blogPost.getPageRank());
        post.setSecretLevelEnum(SecretLevelEnum.valueOf(blogPost.getSecretLevel()));
        return post;
    }

    public Post getPost() {
        return post;
    }

    public User getAuthor() {
        return author;
    }

    public Category getCategory() {
        return category;
    }

    public List<Comment> getCommentList() {
        return commentList;
    }
}
