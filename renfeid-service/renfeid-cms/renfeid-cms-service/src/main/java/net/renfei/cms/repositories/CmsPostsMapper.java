package net.renfei.cms.repositories;

import net.renfei.cms.repositories.entity.CmsPosts;
import net.renfei.cms.repositories.entity.CmsPostsExample;
import net.renfei.cms.repositories.entity.CmsPostsWithBLOBs;
import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Param;

import java.util.List;

@Mapper
public interface CmsPostsMapper {
    long countByExample(CmsPostsExample example);

    int deleteByExample(CmsPostsExample example);

    int deleteByPrimaryKey(Long id);

    int insert(CmsPostsWithBLOBs row);

    int insertSelective(CmsPostsWithBLOBs row);

    List<CmsPostsWithBLOBs> selectByExampleWithBLOBs(CmsPostsExample example);

    List<CmsPosts> selectByExample(CmsPostsExample example);

    CmsPostsWithBLOBs selectByPrimaryKey(Long id);

    int updateByExampleSelective(@Param("row") CmsPostsWithBLOBs row, @Param("example") CmsPostsExample example);

    int updateByExampleWithBLOBs(@Param("row") CmsPostsWithBLOBs row, @Param("example") CmsPostsExample example);

    int updateByExample(@Param("row") CmsPosts row, @Param("example") CmsPostsExample example);

    int updateByPrimaryKeySelective(CmsPostsWithBLOBs row);

    int updateByPrimaryKeyWithBLOBs(CmsPostsWithBLOBs row);

    int updateByPrimaryKey(CmsPosts row);

    int addViews(Long id);
}