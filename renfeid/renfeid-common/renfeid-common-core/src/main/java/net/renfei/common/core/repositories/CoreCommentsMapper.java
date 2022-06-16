package net.renfei.common.core.repositories;

import net.renfei.common.core.repositories.entity.CoreComments;
import net.renfei.common.core.repositories.entity.CoreCommentsExample;
import net.renfei.common.core.repositories.entity.CoreCommentsWithBLOBs;
import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Param;

import java.util.List;

@Mapper
public interface CoreCommentsMapper {
    long countByExample(CoreCommentsExample example);

    int deleteByExample(CoreCommentsExample example);

    int deleteByPrimaryKey(Long id);

    int insert(CoreCommentsWithBLOBs record);

    int insertSelective(CoreCommentsWithBLOBs record);

    List<CoreCommentsWithBLOBs> selectByExampleWithBLOBs(CoreCommentsExample example);

    List<CoreComments> selectByExample(CoreCommentsExample example);

    CoreCommentsWithBLOBs selectByPrimaryKey(Long id);

    int updateByExampleSelective(@Param("record") CoreCommentsWithBLOBs record, @Param("example") CoreCommentsExample example);

    int updateByExampleWithBLOBs(@Param("record") CoreCommentsWithBLOBs record, @Param("example") CoreCommentsExample example);

    int updateByExample(@Param("record") CoreComments record, @Param("example") CoreCommentsExample example);

    int updateByPrimaryKeySelective(CoreCommentsWithBLOBs record);

    int updateByPrimaryKeyWithBLOBs(CoreCommentsWithBLOBs record);

    int updateByPrimaryKey(CoreComments record);
}