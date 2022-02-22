package net.renfei.repositories;

import java.util.List;
import net.renfei.repositories.model.WeiboPostmeta;
import net.renfei.repositories.model.WeiboPostmetaExample;
import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Param;

@Mapper
public interface WeiboPostmetaMapper {
    long countByExample(WeiboPostmetaExample example);

    int deleteByExample(WeiboPostmetaExample example);

    int insert(WeiboPostmeta record);

    int insertSelective(WeiboPostmeta record);

    List<WeiboPostmeta> selectByExampleWithBLOBs(WeiboPostmetaExample example);

    List<WeiboPostmeta> selectByExample(WeiboPostmetaExample example);

    int updateByExampleSelective(@Param("record") WeiboPostmeta record, @Param("example") WeiboPostmetaExample example);

    int updateByExampleWithBLOBs(@Param("record") WeiboPostmeta record, @Param("example") WeiboPostmetaExample example);

    int updateByExample(@Param("record") WeiboPostmeta record, @Param("example") WeiboPostmetaExample example);
}