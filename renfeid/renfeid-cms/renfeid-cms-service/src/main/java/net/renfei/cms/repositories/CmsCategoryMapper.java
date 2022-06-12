package net.renfei.cms.repositories;

import net.renfei.cms.repositories.entity.CmsCategory;
import net.renfei.cms.repositories.entity.CmsCategoryExample;
import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Param;

import java.util.List;

@Mapper
public interface CmsCategoryMapper {
    long countByExample(CmsCategoryExample example);

    int deleteByExample(CmsCategoryExample example);

    int deleteByPrimaryKey(Long id);

    int insert(CmsCategory record);

    int insertSelective(CmsCategory record);

    List<CmsCategory> selectByExample(CmsCategoryExample example);

    CmsCategory selectByPrimaryKey(Long id);

    int updateByExampleSelective(@Param("record") CmsCategory record, @Param("example") CmsCategoryExample example);

    int updateByExample(@Param("record") CmsCategory record, @Param("example") CmsCategoryExample example);

    int updateByPrimaryKeySelective(CmsCategory record);

    int updateByPrimaryKey(CmsCategory record);
}