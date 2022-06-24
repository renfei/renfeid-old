package net.renfei.uaa.repositories;

import net.renfei.uaa.repositories.entity.UaaSecretKey;
import net.renfei.uaa.repositories.entity.UaaSecretKeyExample;
import net.renfei.uaa.repositories.entity.UaaSecretKeyWithBLOBs;
import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Param;

import java.util.List;

@Mapper
public interface UaaSecretKeyMapper {
    long countByExample(UaaSecretKeyExample example);

    int deleteByExample(UaaSecretKeyExample example);

    int deleteByPrimaryKey(Long id);

    int insert(UaaSecretKeyWithBLOBs record);

    int insertSelective(UaaSecretKeyWithBLOBs record);

    List<UaaSecretKeyWithBLOBs> selectByExampleWithBLOBs(UaaSecretKeyExample example);

    List<UaaSecretKey> selectByExample(UaaSecretKeyExample example);

    UaaSecretKeyWithBLOBs selectByPrimaryKey(Long id);

    int updateByExampleSelective(@Param("record") UaaSecretKeyWithBLOBs record, @Param("example") UaaSecretKeyExample example);

    int updateByExampleWithBLOBs(@Param("record") UaaSecretKeyWithBLOBs record, @Param("example") UaaSecretKeyExample example);

    int updateByExample(@Param("record") UaaSecretKey record, @Param("example") UaaSecretKeyExample example);

    int updateByPrimaryKeySelective(UaaSecretKeyWithBLOBs record);

    int updateByPrimaryKeyWithBLOBs(UaaSecretKeyWithBLOBs record);

    int updateByPrimaryKey(UaaSecretKey record);
}