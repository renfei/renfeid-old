package net.renfei.pro.discuz.repositories;

import net.renfei.pro.discuz.repositories.entity.DiscuzForumPostDO;
import net.renfei.pro.discuz.repositories.entity.DiscuzForumPostDOExample;
import net.renfei.pro.discuz.repositories.entity.DiscuzForumPostDOKey;
import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Param;

import java.util.List;

@Mapper
public interface DiscuzForumPostDOMapper {
    long countByExample(DiscuzForumPostDOExample example);

    int deleteByExample(DiscuzForumPostDOExample example);

    int deleteByPrimaryKey(DiscuzForumPostDOKey key);

    int insert(DiscuzForumPostDO record);

    int insertSelective(DiscuzForumPostDO record);

    List<DiscuzForumPostDO> selectByExampleWithBLOBs(DiscuzForumPostDOExample example);

    List<DiscuzForumPostDO> selectByExample(DiscuzForumPostDOExample example);

    DiscuzForumPostDO selectByPrimaryKey(DiscuzForumPostDOKey key);

    int updateByExampleSelective(@Param("record") DiscuzForumPostDO record, @Param("example") DiscuzForumPostDOExample example);

    int updateByExampleWithBLOBs(@Param("record") DiscuzForumPostDO record, @Param("example") DiscuzForumPostDOExample example);

    int updateByExample(@Param("record") DiscuzForumPostDO record, @Param("example") DiscuzForumPostDOExample example);

    int updateByPrimaryKeySelective(DiscuzForumPostDO record);

    int updateByPrimaryKeyWithBLOBs(DiscuzForumPostDO record);

    int updateByPrimaryKey(DiscuzForumPostDO record);
}