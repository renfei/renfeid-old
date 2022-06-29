package net.renfei.uaa.repositories;

import net.renfei.uaa.repositories.entity.UaaRole;
import net.renfei.uaa.repositories.entity.UaaRoleExample;
import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Param;

import java.util.List;

@Mapper
public interface UaaRoleMapper {
    long countByExample(UaaRoleExample example);

    int deleteByExample(UaaRoleExample example);

    int deleteByPrimaryKey(Long id);

    int insert(UaaRole record);

    int insertSelective(UaaRole record);

    List<UaaRole> selectByExampleWithBLOBs(UaaRoleExample example);

    List<UaaRole> selectByExample(UaaRoleExample example);

    UaaRole selectByPrimaryKey(Long id);

    int updateByExampleSelective(@Param("record") UaaRole record, @Param("example") UaaRoleExample example);

    int updateByExampleWithBLOBs(@Param("record") UaaRole record, @Param("example") UaaRoleExample example);

    int updateByExample(@Param("record") UaaRole record, @Param("example") UaaRoleExample example);

    int updateByPrimaryKeySelective(UaaRole record);

    int updateByPrimaryKeyWithBLOBs(UaaRole record);

    int updateByPrimaryKey(UaaRole record);
}