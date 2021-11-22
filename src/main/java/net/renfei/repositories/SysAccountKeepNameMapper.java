package net.renfei.repositories;

import java.util.List;
import net.renfei.repositories.model.SysAccountKeepName;
import net.renfei.repositories.model.SysAccountKeepNameExample;
import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Param;

@Mapper
public interface SysAccountKeepNameMapper {
    long countByExample(SysAccountKeepNameExample example);

    int deleteByExample(SysAccountKeepNameExample example);

    int deleteByPrimaryKey(Long id);

    int insert(SysAccountKeepName record);

    int insertSelective(SysAccountKeepName record);

    List<SysAccountKeepName> selectByExample(SysAccountKeepNameExample example);

    SysAccountKeepName selectByPrimaryKey(Long id);

    int updateByExampleSelective(@Param("record") SysAccountKeepName record, @Param("example") SysAccountKeepNameExample example);

    int updateByExample(@Param("record") SysAccountKeepName record, @Param("example") SysAccountKeepNameExample example);

    int updateByPrimaryKeySelective(SysAccountKeepName record);

    int updateByPrimaryKey(SysAccountKeepName record);
}