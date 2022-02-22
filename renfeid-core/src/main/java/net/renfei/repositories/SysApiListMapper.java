package net.renfei.repositories;

import net.renfei.repositories.model.SysApiList;
import net.renfei.repositories.model.SysApiListExample;
import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Param;

import java.util.List;

@Mapper
public interface SysApiListMapper {
    long countByExample(SysApiListExample example);

    int deleteByExample(SysApiListExample example);

    int deleteByPrimaryKey(Long id);

    int insert(SysApiList record);

    int insertSelective(SysApiList record);

    List<SysApiList> selectByExample(SysApiListExample example);

    SysApiList selectByPrimaryKey(Long id);

    int updateByExampleSelective(@Param("record") SysApiList record, @Param("example") SysApiListExample example);

    int updateByExample(@Param("record") SysApiList record, @Param("example") SysApiListExample example);

    int updateByPrimaryKeySelective(SysApiList record);

    int updateByPrimaryKey(SysApiList record);
}