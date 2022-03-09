package net.renfei.repositories;

import net.renfei.repositories.model.KitboxDneyesRecordLog;
import net.renfei.repositories.model.KitboxDneyesRecordLogExample;
import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Param;

import java.util.List;

@Mapper
public interface KitboxDneyesRecordLogMapper {
    long countByExample(KitboxDneyesRecordLogExample example);

    int deleteByExample(KitboxDneyesRecordLogExample example);

    int deleteByPrimaryKey(Long id);

    int insert(KitboxDneyesRecordLog record);

    int insertSelective(KitboxDneyesRecordLog record);

    List<KitboxDneyesRecordLog> selectByExampleWithBLOBs(KitboxDneyesRecordLogExample example);

    List<KitboxDneyesRecordLog> selectByExample(KitboxDneyesRecordLogExample example);

    KitboxDneyesRecordLog selectByPrimaryKey(Long id);

    int updateByExampleSelective(@Param("record") KitboxDneyesRecordLog record, @Param("example") KitboxDneyesRecordLogExample example);

    int updateByExampleWithBLOBs(@Param("record") KitboxDneyesRecordLog record, @Param("example") KitboxDneyesRecordLogExample example);

    int updateByExample(@Param("record") KitboxDneyesRecordLog record, @Param("example") KitboxDneyesRecordLogExample example);

    int updateByPrimaryKeySelective(KitboxDneyesRecordLog record);

    int updateByPrimaryKeyWithBLOBs(KitboxDneyesRecordLog record);

    int updateByPrimaryKey(KitboxDneyesRecordLog record);
}