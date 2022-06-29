package net.renfei.uaa.repositories;

import net.renfei.uaa.repositories.entity.UaaUserRole;
import net.renfei.uaa.repositories.entity.UaaUserRoleExample;
import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Param;

import java.util.List;

@Mapper
public interface UaaUserRoleMapper {
    long countByExample(UaaUserRoleExample example);

    int deleteByExample(UaaUserRoleExample example);

    int deleteByPrimaryKey(Long id);

    int insert(UaaUserRole record);

    int insertSelective(UaaUserRole record);

    List<UaaUserRole> selectByExample(UaaUserRoleExample example);

    UaaUserRole selectByPrimaryKey(Long id);

    int updateByExampleSelective(@Param("record") UaaUserRole record, @Param("example") UaaUserRoleExample example);

    int updateByExample(@Param("record") UaaUserRole record, @Param("example") UaaUserRoleExample example);

    int updateByPrimaryKeySelective(UaaUserRole record);

    int updateByPrimaryKey(UaaUserRole record);
}