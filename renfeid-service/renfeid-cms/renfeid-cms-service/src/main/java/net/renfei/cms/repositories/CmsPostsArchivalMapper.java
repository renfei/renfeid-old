package net.renfei.cms.repositories;

import net.renfei.cms.repositories.entity.CmsPostsArchival;
import net.renfei.cms.repositories.entity.CmsPostsArchivalExample;
import net.renfei.cms.repositories.entity.CmsPostsArchivalWithBLOBs;
import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Param;

import java.util.List;

@Mapper
public interface CmsPostsArchivalMapper {
    long countByExample(CmsPostsArchivalExample example);

    int deleteByExample(CmsPostsArchivalExample example);

    int deleteByPrimaryKey(Long id);

    int insert(CmsPostsArchivalWithBLOBs row);

    int insertSelective(CmsPostsArchivalWithBLOBs row);

    List<CmsPostsArchivalWithBLOBs> selectByExampleWithBLOBs(CmsPostsArchivalExample example);

    List<CmsPostsArchival> selectByExample(CmsPostsArchivalExample example);

    CmsPostsArchivalWithBLOBs selectByPrimaryKey(Long id);

    int updateByExampleSelective(@Param("row") CmsPostsArchivalWithBLOBs row, @Param("example") CmsPostsArchivalExample example);

    int updateByExampleWithBLOBs(@Param("row") CmsPostsArchivalWithBLOBs row, @Param("example") CmsPostsArchivalExample example);

    int updateByExample(@Param("row") CmsPostsArchival row, @Param("example") CmsPostsArchivalExample example);

    int updateByPrimaryKeySelective(CmsPostsArchivalWithBLOBs row);

    int updateByPrimaryKeyWithBLOBs(CmsPostsArchivalWithBLOBs row);

    int updateByPrimaryKey(CmsPostsArchival row);
}