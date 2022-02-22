package net.renfei.repositories;

import java.util.List;
import net.renfei.repositories.model.BlogPostmeta;
import net.renfei.repositories.model.BlogPostmetaExample;
import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Param;

@Mapper
public interface BlogPostmetaMapper {
    long countByExample(BlogPostmetaExample example);

    int deleteByExample(BlogPostmetaExample example);

    int deleteByPrimaryKey(Long id);

    int insert(BlogPostmeta record);

    int insertSelective(BlogPostmeta record);

    List<BlogPostmeta> selectByExampleWithBLOBs(BlogPostmetaExample example);

    List<BlogPostmeta> selectByExample(BlogPostmetaExample example);

    BlogPostmeta selectByPrimaryKey(Long id);

    int updateByExampleSelective(@Param("record") BlogPostmeta record, @Param("example") BlogPostmetaExample example);

    int updateByExampleWithBLOBs(@Param("record") BlogPostmeta record, @Param("example") BlogPostmetaExample example);

    int updateByExample(@Param("record") BlogPostmeta record, @Param("example") BlogPostmetaExample example);

    int updateByPrimaryKeySelective(BlogPostmeta record);

    int updateByPrimaryKeyWithBLOBs(BlogPostmeta record);

    int updateByPrimaryKey(BlogPostmeta record);
}