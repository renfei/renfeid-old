package net.renfei.repositories;

import net.renfei.repositories.model.HotSearch;
import net.renfei.repositories.model.SysLogs;
import net.renfei.repositories.model.SysLogsExample;
import net.renfei.repositories.model.SysLogsWithBLOBs;
import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Param;

import java.util.List;

@Mapper
public interface SysLogsMapper {
    long countByExample(SysLogsExample example);

    int deleteByExample(SysLogsExample example);

    int deleteByPrimaryKey(Long id);

    int insert(SysLogsWithBLOBs record);

    int insertSelective(SysLogsWithBLOBs record);

    List<SysLogsWithBLOBs> selectByExampleWithBLOBs(SysLogsExample example);

    List<SysLogs> selectByExample(SysLogsExample example);

    SysLogsWithBLOBs selectByPrimaryKey(Long id);

    int updateByExampleSelective(@Param("record") SysLogsWithBLOBs record, @Param("example") SysLogsExample example);

    int updateByExampleWithBLOBs(@Param("record") SysLogsWithBLOBs record, @Param("example") SysLogsExample example);

    int updateByExample(@Param("record") SysLogs record, @Param("example") SysLogsExample example);

    int updateByPrimaryKeySelective(SysLogsWithBLOBs record);

    int updateByPrimaryKeyWithBLOBs(SysLogsWithBLOBs record);

    int updateByPrimaryKey(SysLogs record);

    List<HotSearch> selectHotSearchList();
}