package net.renfei.common.core.repositories;

import net.renfei.common.core.repositories.entity.CoreSystemSetting;
import net.renfei.common.core.repositories.entity.CoreSystemSettingExample;
import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Param;

import java.util.List;

@Mapper
public interface CoreSystemSettingMapper {
    long countByExample(CoreSystemSettingExample example);

    int deleteByExample(CoreSystemSettingExample example);

    int deleteByPrimaryKey(Long id);

    int insert(CoreSystemSetting record);

    int insertSelective(CoreSystemSetting record);

    List<CoreSystemSetting> selectByExample(CoreSystemSettingExample example);

    CoreSystemSetting selectByPrimaryKey(Long id);

    int updateByExampleSelective(@Param("record") CoreSystemSetting record, @Param("example") CoreSystemSettingExample example);

    int updateByExample(@Param("record") CoreSystemSetting record, @Param("example") CoreSystemSettingExample example);

    int updateByPrimaryKeySelective(CoreSystemSetting record);

    int updateByPrimaryKey(CoreSystemSetting record);
}