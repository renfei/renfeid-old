package net.renfei.cms.repositories;

import net.renfei.cms.repositories.entity.CmsPostsTag;
import net.renfei.cms.repositories.entity.CmsPostsTagExample;
import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Param;

import java.util.List;

@Mapper
public interface CmsPostsTagMapper {
    long countByExample(CmsPostsTagExample example);

    int deleteByExample(CmsPostsTagExample example);

    int deleteByPrimaryKey(Long id);

    int insert(CmsPostsTag row);

    int insertSelective(CmsPostsTag row);

    List<CmsPostsTag> selectByExample(CmsPostsTagExample example);

    CmsPostsTag selectByPrimaryKey(Long id);

    int updateByExampleSelective(@Param("row") CmsPostsTag row, @Param("example") CmsPostsTagExample example);

    int updateByExample(@Param("row") CmsPostsTag row, @Param("example") CmsPostsTagExample example);

    int updateByPrimaryKeySelective(CmsPostsTag row);

    int updateByPrimaryKey(CmsPostsTag row);
}