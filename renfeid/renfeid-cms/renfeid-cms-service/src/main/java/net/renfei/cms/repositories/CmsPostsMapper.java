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

    int insert(CmsPostsWithBLOBs record);

    int insertSelective(CmsPostsWithBLOBs record);

    List<CmsPostsWithBLOBs> selectByExampleWithBLOBs(CmsPostsExample example);

    List<CmsPosts> selectByExample(CmsPostsExample example);

    CmsPostsWithBLOBs selectByPrimaryKey(Long id);

    int updateByExampleSelective(@Param("record") CmsPostsWithBLOBs record, @Param("example") CmsPostsExample example);

    int updateByExampleWithBLOBs(@Param("record") CmsPostsWithBLOBs record, @Param("example") CmsPostsExample example);

    int updateByExample(@Param("record") CmsPosts record, @Param("example") CmsPostsExample example);

    int updateByPrimaryKeySelective(CmsPostsWithBLOBs record);

    int updateByPrimaryKeyWithBLOBs(CmsPostsWithBLOBs record);

    int updateByPrimaryKey(CmsPosts record);
}