package net.renfei.repositories;

import net.renfei.repositories.model.KitboxDneyesRecord;
import net.renfei.repositories.model.KitboxDneyesRecordExample;
import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Param;

import java.util.List;

@Mapper
public interface KitboxDneyesRecordMapper {
    long countByExample(KitboxDneyesRecordExample example);

    int deleteByExample(KitboxDneyesRecordExample example);

    int deleteByPrimaryKey(Long id);

    int insert(KitboxDneyesRecord record);

    int insertSelective(KitboxDneyesRecord record);

    List<KitboxDneyesRecord> selectByExample(KitboxDneyesRecordExample example);

    KitboxDneyesRecord selectByPrimaryKey(Long id);

    int updateByExampleSelective(@Param("record") KitboxDneyesRecord record, @Param("example") KitboxDneyesRecordExample example);

    int updateByExample(@Param("record") KitboxDneyesRecord record, @Param("example") KitboxDneyesRecordExample example);

    int updateByPrimaryKeySelective(KitboxDneyesRecord record);

    int updateByPrimaryKey(KitboxDneyesRecord record);
}