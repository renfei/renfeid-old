package net.renfei.repositories;

import java.util.List;
import net.renfei.repositories.model.SysComments;
import net.renfei.repositories.model.SysCommentsExample;
import net.renfei.repositories.model.SysCommentsWithBLOBs;
import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Param;

@Mapper
public interface SysCommentsMapper {
    long countByExample(SysCommentsExample example);

    int deleteByExample(SysCommentsExample example);

    int deleteByPrimaryKey(Long id);

    int insert(SysCommentsWithBLOBs record);

    int insertSelective(SysCommentsWithBLOBs record);

    List<SysCommentsWithBLOBs> selectByExampleWithBLOBs(SysCommentsExample example);

    List<SysComments> selectByExample(SysCommentsExample example);

    SysCommentsWithBLOBs selectByPrimaryKey(Long id);

    int updateByExampleSelective(@Param("record") SysCommentsWithBLOBs record, @Param("example") SysCommentsExample example);

    int updateByExampleWithBLOBs(@Param("record") SysCommentsWithBLOBs record, @Param("example") SysCommentsExample example);

    int updateByExample(@Param("record") SysComments record, @Param("example") SysCommentsExample example);

    int updateByPrimaryKeySelective(SysCommentsWithBLOBs record);

    int updateByPrimaryKeyWithBLOBs(SysCommentsWithBLOBs record);

    int updateByPrimaryKey(SysComments record);
}