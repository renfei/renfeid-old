package net.renfei.repositories;

import java.util.List;
import net.renfei.repositories.model.SysSecretKey;
import net.renfei.repositories.model.SysSecretKeyExample;
import net.renfei.repositories.model.SysSecretKeyWithBLOBs;
import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Param;

@Mapper
public interface SysSecretKeyMapper {
    long countByExample(SysSecretKeyExample example);

    int deleteByExample(SysSecretKeyExample example);

    int deleteByPrimaryKey(Long id);

    int insert(SysSecretKeyWithBLOBs record);

    int insertSelective(SysSecretKeyWithBLOBs record);

    List<SysSecretKeyWithBLOBs> selectByExampleWithBLOBs(SysSecretKeyExample example);

    List<SysSecretKey> selectByExample(SysSecretKeyExample example);

    SysSecretKeyWithBLOBs selectByPrimaryKey(Long id);

    int updateByExampleSelective(@Param("record") SysSecretKeyWithBLOBs record, @Param("example") SysSecretKeyExample example);

    int updateByExampleWithBLOBs(@Param("record") SysSecretKeyWithBLOBs record, @Param("example") SysSecretKeyExample example);

    int updateByExample(@Param("record") SysSecretKey record, @Param("example") SysSecretKeyExample example);

    int updateByPrimaryKeySelective(SysSecretKeyWithBLOBs record);

    int updateByPrimaryKeyWithBLOBs(SysSecretKeyWithBLOBs record);

    int updateByPrimaryKey(SysSecretKey record);
}