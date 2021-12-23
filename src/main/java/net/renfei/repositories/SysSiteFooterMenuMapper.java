package net.renfei.repositories;

import java.util.List;
import net.renfei.repositories.model.SysSiteFooterMenu;
import net.renfei.repositories.model.SysSiteFooterMenuExample;
import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Param;

@Mapper
public interface SysSiteFooterMenuMapper {
    long countByExample(SysSiteFooterMenuExample example);

    int deleteByExample(SysSiteFooterMenuExample example);

    int insert(SysSiteFooterMenu record);

    int insertSelective(SysSiteFooterMenu record);

    List<SysSiteFooterMenu> selectByExample(SysSiteFooterMenuExample example);

    int updateByExampleSelective(@Param("record") SysSiteFooterMenu record, @Param("example") SysSiteFooterMenuExample example);

    int updateByExample(@Param("record") SysSiteFooterMenu record, @Param("example") SysSiteFooterMenuExample example);
}