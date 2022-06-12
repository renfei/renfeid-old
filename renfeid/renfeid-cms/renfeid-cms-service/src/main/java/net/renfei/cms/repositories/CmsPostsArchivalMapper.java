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

    int insert(CmsPostsArchivalWithBLOBs record);

    int insertSelective(CmsPostsArchivalWithBLOBs record);

    List<CmsPostsArchivalWithBLOBs> selectByExampleWithBLOBs(CmsPostsArchivalExample example);

    List<CmsPostsArchival> selectByExample(CmsPostsArchivalExample example);

    CmsPostsArchivalWithBLOBs selectByPrimaryKey(Long id);

    int updateByExampleSelective(@Param("record") CmsPostsArchivalWithBLOBs record, @Param("example") CmsPostsArchivalExample example);

    int updateByExampleWithBLOBs(@Param("record") CmsPostsArchivalWithBLOBs record, @Param("example") CmsPostsArchivalExample example);

    int updateByExample(@Param("record") CmsPostsArchival record, @Param("example") CmsPostsArchivalExample example);

    int updateByPrimaryKeySelective(CmsPostsArchivalWithBLOBs record);

    int updateByPrimaryKeyWithBLOBs(CmsPostsArchivalWithBLOBs record);

    int updateByPrimaryKey(CmsPostsArchival record);
}