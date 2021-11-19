package net.renfei.repositories;

import java.util.List;
import net.renfei.repositories.model.SysKeywordTag;
import net.renfei.repositories.model.SysKeywordTagExample;
import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Param;

@Mapper
public interface SysKeywordTagMapper {
    long countByExample(SysKeywordTagExample example);

    int deleteByExample(SysKeywordTagExample example);

    int deleteByPrimaryKey(Long id);

    int insert(SysKeywordTag record);

    int insertSelective(SysKeywordTag record);

    List<SysKeywordTag> selectByExample(SysKeywordTagExample example);

    SysKeywordTag selectByPrimaryKey(Long id);

    int updateByExampleSelective(@Param("record") SysKeywordTag record, @Param("example") SysKeywordTagExample example);

    int updateByExample(@Param("record") SysKeywordTag record, @Param("example") SysKeywordTagExample example);

    int updateByPrimaryKeySelective(SysKeywordTag record);

    int updateByPrimaryKey(SysKeywordTag record);
}