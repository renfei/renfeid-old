package net.renfei.common.core.repositories;

import net.renfei.common.core.entity.HotSearch;
import net.renfei.common.core.repositories.entity.CoreLogs;
import net.renfei.common.core.repositories.entity.CoreLogsExample;
import net.renfei.common.core.repositories.entity.CoreLogsWithBLOBs;
import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Param;

import java.util.List;

@Mapper
public interface CoreLogsMapper {
    long countByExample(CoreLogsExample example);

    int deleteByExample(CoreLogsExample example);

    int deleteByPrimaryKey(Long id);

    int insert(CoreLogsWithBLOBs record);

    int insertSelective(CoreLogsWithBLOBs record);

    List<CoreLogsWithBLOBs> selectByExampleWithBLOBs(CoreLogsExample example);

    List<CoreLogs> selectByExample(CoreLogsExample example);

    CoreLogsWithBLOBs selectByPrimaryKey(Long id);

    int updateByExampleSelective(@Param("record") CoreLogsWithBLOBs record, @Param("example") CoreLogsExample example);

    int updateByExampleWithBLOBs(@Param("record") CoreLogsWithBLOBs record, @Param("example") CoreLogsExample example);

    int updateByExample(@Param("record") CoreLogs record, @Param("example") CoreLogsExample example);

    int updateByPrimaryKeySelective(CoreLogsWithBLOBs record);

    int updateByPrimaryKeyWithBLOBs(CoreLogsWithBLOBs record);

    int updateByPrimaryKey(CoreLogs record);

    List<HotSearch> selectHotSearchList();
}