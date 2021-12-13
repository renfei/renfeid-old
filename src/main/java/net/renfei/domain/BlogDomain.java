package net.renfei.domain;

import lombok.Getter;
import net.renfei.domain.blog.Category;
import net.renfei.domain.blog.Post;
import net.renfei.domain.comment.Comment;
import net.renfei.domain.system.SystemTypeEnum;
import net.renfei.domain.user.User;
import net.renfei.exception.BlogPostNotExistException;
import net.renfei.model.CommentStatus;
import net.renfei.model.PostStatus;
import net.renfei.model.SecretLevel;
import net.renfei.repositories.BlogCategoryMapper;
import net.renfei.repositories.BlogPostsMapper;
import net.renfei.repositories.model.BlogCategory;
import net.renfei.repositories.model.BlogPostsExample;
import net.renfei.repositories.model.BlogPostsWithBLOBs;
import net.renfei.utils.ApplicationContextUtil;
import net.renfei.utils.ListUtils;

import java.util.List;

/**
 * 博客领域的聚合根
 * 与博客相关的领域对象都在这里
 *
 * @author renfei
 */
public final class BlogDomain {
    @Getter
    private final Post post;
    @Getter
    private final User author;
    @Getter
    private final Category category;
    @Getter
    private final List<Comment> commentList;
    private final BlogPostsMapper blogPostsMapper;
    private final BlogCategoryMapper categoryMapper;

    {
        blogPostsMapper = (BlogPostsMapper) ApplicationContextUtil.getBean("blogPostsMapper");
        categoryMapper = (BlogCategoryMapper) ApplicationContextUtil.getBean("blogCategoryMapper");
    }

    /**
     * 加载一篇博客文章
     *
     * @param id 博客文章ID
     * @throws BlogPostNotExistException 文章不存在异常
     */
    public BlogDomain(Long id) throws BlogPostNotExistException {
        if (id == null) {
            throw new BlogPostNotExistException("博客文章不存在");
        }
        post = initPost(id);
        author = new User(post.getPostAuthor());
        category = initCategory(post.getCategoryId());
        commentList = new CommentDomain(SystemTypeEnum.BLOG, id).getCommentList();
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

    private Post initPost(Long id) throws BlogPostNotExistException {
        BlogPostsExample example = new BlogPostsExample();
        example.createCriteria().andIdEqualTo(id);
        BlogPostsWithBLOBs blogPost = ListUtils.getOne(blogPostsMapper.selectByExampleWithBLOBs(example));
        if (blogPost == null || PostStatus.DELETED.toString().equals(blogPost.getPostStatus())) {
            throw new BlogPostNotExistException("博客文章不存在");
        }
        return Post.builder()
                .id(id)
                .title(blogPost.getPostTitle())
                .keyword(blogPost.getPostKeyword())
                .excerpt(blogPost.getPostExcerpt())
                .content(blogPost.getPostContent())
                .featuredImage(blogPost.getFeaturedImage())
                .isOriginal(blogPost.getIsOriginal())
                .sourceName(blogPost.getSourceName())
                .sourceUrl(blogPost.getSourceUrl())
                .postDate(blogPost.getPostDate())
                .postAuthor(blogPost.getPostAuthor())
                .categoryId(blogPost.getCategoryId())
                .postStatus(PostStatus.valueOf(blogPost.getPostStatus()))
                .commentStatus(CommentStatus.valueOf(blogPost.getCommentStatus()))
                .postPassword(blogPost.getPostPassword())
                .postModified(blogPost.getPostModified())
                .postParent(blogPost.getPostParent())
                .thumbsUp(blogPost.getThumbsUp())
                .thumbsDown(blogPost.getThumbsDown())
                .avgViews(blogPost.getAvgViews())
                .avgComment(blogPost.getAvgComment())
                .pageRank(blogPost.getPageRank())
                .secretLevel(SecretLevel.valueOf(blogPost.getSecretLevel()))
                .build();
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
                .secretLevel(SecretLevel.valueOf(blogCategory.getSecretLevel()))
                .build();
    }
}
