package net.renfei.repositories;

import java.util.List;
import net.renfei.repositories.model.SysKeywordObject;
import net.renfei.repositories.model.SysKeywordObjectExample;
import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Param;

@Mapper
public interface SysKeywordObjectMapper {
    long countByExample(SysKeywordObjectExample example);

    int deleteByExample(SysKeywordObjectExample example);

    int deleteByPrimaryKey(Long id);

    int insert(SysKeywordObject record);

    int insertSelective(SysKeywordObject record);

    List<SysKeywordObject> selectByExample(SysKeywordObjectExample example);

    SysKeywordObject selectByPrimaryKey(Long id);

    int updateByExampleSelective(@Param("record") SysKeywordObject record, @Param("example") SysKeywordObjectExample example);

    int updateByExample(@Param("record") SysKeywordObject record, @Param("example") SysKeywordObjectExample example);

    int updateByPrimaryKeySelective(SysKeywordObject record);

    int updateByPrimaryKey(SysKeywordObject record);
}