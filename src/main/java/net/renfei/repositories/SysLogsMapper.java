package net.renfei.repositories;

import java.util.List;
import net.renfei.repositories.model.SysLogs;
import net.renfei.repositories.model.SysLogsExample;
import net.renfei.repositories.model.SysLogsWithBLOBs;
import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Param;

@Mapper
public interface SysLogsMapper {
    long countByExample(SysLogsExample example);

    int insert(SysLogsWithBLOBs record);

    int insertSelective(SysLogsWithBLOBs record);

    List<SysLogsWithBLOBs> selectByExampleWithBLOBs(SysLogsExample example);

    List<SysLogs> selectByExample(SysLogsExample example);

    SysLogsWithBLOBs selectByPrimaryKey(Long id);
}