package net.renfei.repositories;

import java.util.List;
import net.renfei.repositories.model.SysPages;
import net.renfei.repositories.model.SysPagesExample;
import net.renfei.repositories.model.SysPagesWithBLOBs;
import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Param;

@Mapper
public interface SysPagesMapper {
    long countByExample(SysPagesExample example);

    int deleteByExample(SysPagesExample example);

    int deleteByPrimaryKey(Long id);

    int insert(SysPagesWithBLOBs record);

    int insertSelective(SysPagesWithBLOBs record);

    List<SysPagesWithBLOBs> selectByExampleWithBLOBs(SysPagesExample example);

    List<SysPages> selectByExample(SysPagesExample example);

    SysPagesWithBLOBs selectByPrimaryKey(Long id);

    int updateByExampleSelective(@Param("record") SysPagesWithBLOBs record, @Param("example") SysPagesExample example);

    int updateByExampleWithBLOBs(@Param("record") SysPagesWithBLOBs record, @Param("example") SysPagesExample example);

    int updateByExample(@Param("record") SysPages record, @Param("example") SysPagesExample example);

    int updateByPrimaryKeySelective(SysPagesWithBLOBs record);

    int updateByPrimaryKeyWithBLOBs(SysPagesWithBLOBs record);

    int updateByPrimaryKey(SysPages record);
}