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

    int insert(UaaMenuWithBLOBs row);

    int insertSelective(UaaMenuWithBLOBs row);

    List<UaaMenuWithBLOBs> selectByExampleWithBLOBs(UaaMenuExample example);

    List<UaaMenu> selectByExample(UaaMenuExample example);

    UaaMenuWithBLOBs selectByPrimaryKey(Long id);

    int updateByExampleSelective(@Param("row") UaaMenuWithBLOBs row, @Param("example") UaaMenuExample example);

    int updateByExampleWithBLOBs(@Param("row") UaaMenuWithBLOBs row, @Param("example") UaaMenuExample example);

    int updateByExample(@Param("row") UaaMenu row, @Param("example") UaaMenuExample example);

    int updateByPrimaryKeySelective(UaaMenuWithBLOBs row);

    int updateByPrimaryKeyWithBLOBs(UaaMenuWithBLOBs row);

    int updateByPrimaryKey(UaaMenu row);
}