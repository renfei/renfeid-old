package net.renfei.uaa.repositories;

import net.renfei.uaa.repositories.entity.UaaSystemApi;
import net.renfei.uaa.repositories.entity.UaaSystemApiExample;
import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Param;

import java.util.List;

@Mapper
public interface UaaSystemApiMapper {
    long countByExample(UaaSystemApiExample example);

    int deleteByExample(UaaSystemApiExample example);

    int deleteByPrimaryKey(Long id);

    int insert(UaaSystemApi record);

    int insertSelective(UaaSystemApi record);

    List<UaaSystemApi> selectByExample(UaaSystemApiExample example);

    UaaSystemApi selectByPrimaryKey(Long id);

    int updateByExampleSelective(@Param("record") UaaSystemApi record, @Param("example") UaaSystemApiExample example);

    int updateByExample(@Param("record") UaaSystemApi record, @Param("example") UaaSystemApiExample example);

    int updateByPrimaryKeySelective(UaaSystemApi record);

    int updateByPrimaryKey(UaaSystemApi record);
}