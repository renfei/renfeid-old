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

    int insert(UaaAuthority row);

    int insertSelective(UaaAuthority row);

    List<UaaAuthority> selectByExample(UaaAuthorityExample example);

    UaaAuthority selectByPrimaryKey(Long id);

    int updateByExampleSelective(@Param("row") UaaAuthority row, @Param("example") UaaAuthorityExample example);

    int updateByExample(@Param("row") UaaAuthority row, @Param("example") UaaAuthorityExample example);

    int updateByPrimaryKeySelective(UaaAuthority row);

    int updateByPrimaryKey(UaaAuthority row);
}