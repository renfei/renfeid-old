package net.renfei.cms.repositories;

import net.renfei.cms.repositories.entity.CmsTag;
import net.renfei.cms.repositories.entity.CmsTagExample;
import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Param;

import java.util.List;

@Mapper
public interface CmsTagMapper {
    long countByExample(CmsTagExample example);

    int deleteByExample(CmsTagExample example);

    int deleteByPrimaryKey(Long id);

    int insert(CmsTag row);

    int insertSelective(CmsTag row);

    List<CmsTag> selectByExample(CmsTagExample example);

    CmsTag selectByPrimaryKey(Long id);

    int updateByExampleSelective(@Param("row") CmsTag row, @Param("example") CmsTagExample example);

    int updateByExample(@Param("row") CmsTag row, @Param("example") CmsTagExample example);

    int updateByPrimaryKeySelective(CmsTag row);

    int updateByPrimaryKey(CmsTag row);
}