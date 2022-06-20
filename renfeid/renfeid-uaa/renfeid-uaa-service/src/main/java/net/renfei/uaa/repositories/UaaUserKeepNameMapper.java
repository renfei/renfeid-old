package net.renfei.uaa.repositories;

import net.renfei.uaa.repositories.entity.UaaUserKeepName;
import net.renfei.uaa.repositories.entity.UaaUserKeepNameExample;
import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Param;

import java.util.List;

@Mapper
public interface UaaUserKeepNameMapper {
    long countByExample(UaaUserKeepNameExample example);

    int deleteByExample(UaaUserKeepNameExample example);

    int deleteByPrimaryKey(Long id);

    int insert(UaaUserKeepName record);

    int insertSelective(UaaUserKeepName record);

    List<UaaUserKeepName> selectByExample(UaaUserKeepNameExample example);

    UaaUserKeepName selectByPrimaryKey(Long id);

    int updateByExampleSelective(@Param("record") UaaUserKeepName record, @Param("example") UaaUserKeepNameExample example);

    int updateByExample(@Param("record") UaaUserKeepName record, @Param("example") UaaUserKeepNameExample example);

    int updateByPrimaryKeySelective(UaaUserKeepName record);

    int updateByPrimaryKey(UaaUserKeepName record);
}