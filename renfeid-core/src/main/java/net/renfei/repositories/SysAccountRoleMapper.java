package net.renfei.repositories;

import net.renfei.repositories.model.SysAccountRole;
import net.renfei.repositories.model.SysAccountRoleExample;
import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Param;

import java.util.List;

@Mapper
public interface SysAccountRoleMapper {
    long countByExample(SysAccountRoleExample example);

    int deleteByExample(SysAccountRoleExample example);

    int deleteByPrimaryKey(Long id);

    int insert(SysAccountRole record);

    int insertSelective(SysAccountRole record);

    List<SysAccountRole> selectByExample(SysAccountRoleExample example);

    SysAccountRole selectByPrimaryKey(Long id);

    int updateByExampleSelective(@Param("record") SysAccountRole record, @Param("example") SysAccountRoleExample example);

    int updateByExample(@Param("record") SysAccountRole record, @Param("example") SysAccountRoleExample example);

    int updateByPrimaryKeySelective(SysAccountRole record);

    int updateByPrimaryKey(SysAccountRole record);
}