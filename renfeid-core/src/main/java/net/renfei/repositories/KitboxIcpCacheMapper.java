package net.renfei.repositories;

import net.renfei.repositories.model.KitboxIcpCache;
import net.renfei.repositories.model.KitboxIcpCacheExample;
import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Param;

import java.util.List;

@Mapper
public interface KitboxIcpCacheMapper {
    long countByExample(KitboxIcpCacheExample example);

    int deleteByExample(KitboxIcpCacheExample example);

    int deleteByPrimaryKey(Long id);

    int insert(KitboxIcpCache record);

    int insertSelective(KitboxIcpCache record);

    List<KitboxIcpCache> selectByExample(KitboxIcpCacheExample example);

    KitboxIcpCache selectByPrimaryKey(Long id);

    int updateByExampleSelective(@Param("record") KitboxIcpCache record, @Param("example") KitboxIcpCacheExample example);

    int updateByExample(@Param("record") KitboxIcpCache record, @Param("example") KitboxIcpCacheExample example);

    int updateByPrimaryKeySelective(KitboxIcpCache record);

    int updateByPrimaryKey(KitboxIcpCache record);
}