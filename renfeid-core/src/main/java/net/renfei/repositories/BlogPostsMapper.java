package net.renfei.repositories;

import java.util.List;
import net.renfei.repositories.model.BlogPosts;
import net.renfei.repositories.model.BlogPostsExample;
import net.renfei.repositories.model.BlogPostsWithBLOBs;
import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Param;

@Mapper
public interface BlogPostsMapper {
    long countByExample(BlogPostsExample example);

    int deleteByExample(BlogPostsExample example);

    int insert(BlogPostsWithBLOBs record);

    int insertSelective(BlogPostsWithBLOBs record);

    List<BlogPostsWithBLOBs> selectByExampleWithBLOBs(BlogPostsExample example);

    List<BlogPosts> selectByExample(BlogPostsExample example);

    int updateByExampleSelective(@Param("record") BlogPostsWithBLOBs record, @Param("example") BlogPostsExample example);

    int updateByExampleWithBLOBs(@Param("record") BlogPostsWithBLOBs record, @Param("example") BlogPostsExample example);

    int updateByExample(@Param("record") BlogPosts record, @Param("example") BlogPostsExample example);
}