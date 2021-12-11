package net.renfei.repositories;

import java.util.List;
import net.renfei.repositories.model.SysSiteMenu;
import net.renfei.repositories.model.SysSiteMenuExample;
import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Param;

@Mapper
public interface SysSiteMenuMapper {
    long countByExample(SysSiteMenuExample example);

    int deleteByExample(SysSiteMenuExample example);

    int insert(SysSiteMenu record);

    int insertSelective(SysSiteMenu record);

    List<SysSiteMenu> selectByExample(SysSiteMenuExample example);

    int updateByExampleSelective(@Param("record") SysSiteMenu record, @Param("example") SysSiteMenuExample example);

    int updateByExample(@Param("record") SysSiteMenu record, @Param("example") SysSiteMenuExample example);
}