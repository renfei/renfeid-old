package net.renfei.uaa.repositories;

import net.renfei.uaa.repositories.entity.UaaUser;
import net.renfei.uaa.repositories.entity.UaaUserExample;
import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Param;

import java.util.List;

@Mapper
public interface UaaUserMapper {
    long countByExample(UaaUserExample example);

    int deleteByExample(UaaUserExample example);

    int deleteByPrimaryKey(Long id);

    int insert(UaaUser record);

    int insertSelective(UaaUser record);

    List<UaaUser> selectByExample(UaaUserExample example);

    UaaUser selectByPrimaryKey(Long id);

    int updateByExampleSelective(@Param("record") UaaUser record, @Param("example") UaaUserExample example);

    int updateByExample(@Param("record") UaaUser record, @Param("example") UaaUserExample example);

    int updateByPrimaryKeySelective(UaaUser record);

    int updateByPrimaryKey(UaaUser record);
}