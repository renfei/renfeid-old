package net.renfei.uaa.repositories;

import net.renfei.uaa.repositories.entity.UaaAuthority;
import net.renfei.uaa.repositories.entity.UaaAuthorityExample;
import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Param;

import java.util.List;

@Mapper
public interface UaaAuthorityMapper {
    long countByExample(UaaAuthorityExample example);

    int deleteByExample(UaaAuthorityExample example);

    int deleteByPrimaryKey(Long id);

    int insert(UaaAuthority record);

    int insertSelective(UaaAuthority record);

    List<UaaAuthority> selectByExample(UaaAuthorityExample example);

    UaaAuthority selectByPrimaryKey(Long id);

    int updateByExampleSelective(@Param("record") UaaAuthority record, @Param("example") UaaAuthorityExample example);

    int updateByExample(@Param("record") UaaAuthority record, @Param("example") UaaAuthorityExample example);

    int updateByPrimaryKeySelective(UaaAuthority record);

    int updateByPrimaryKey(UaaAuthority record);
}