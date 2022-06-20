package net.renfei.uaa.repositories;

import net.renfei.uaa.repositories.entity.UaaMenu;
import net.renfei.uaa.repositories.entity.UaaMenuExample;
import net.renfei.uaa.repositories.entity.UaaMenuWithBLOBs;
import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Param;

import java.util.List;

@Mapper
public interface UaaMenuMapper {
    long countByExample(UaaMenuExample example);

    int deleteByExample(UaaMenuExample example);

    int deleteByPrimaryKey(Long id);

    int insert(UaaMenuWithBLOBs record);

    int insertSelective(UaaMenuWithBLOBs record);

    List<UaaMenuWithBLOBs> selectByExampleWithBLOBs(UaaMenuExample example);

    List<UaaMenu> selectByExample(UaaMenuExample example);

    UaaMenuWithBLOBs selectByPrimaryKey(Long id);

    int updateByExampleSelective(@Param("record") UaaMenuWithBLOBs record, @Param("example") UaaMenuExample example);

    int updateByExampleWithBLOBs(@Param("record") UaaMenuWithBLOBs record, @Param("example") UaaMenuExample example);

    int updateByExample(@Param("record") UaaMenu record, @Param("example") UaaMenuExample example);

    int updateByPrimaryKeySelective(UaaMenuWithBLOBs record);

    int updateByPrimaryKeyWithBLOBs(UaaMenuWithBLOBs record);

    int updateByPrimaryKey(UaaMenu record);
}