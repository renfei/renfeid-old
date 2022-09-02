package net.renfei.pro.discuz.repositories;

import net.renfei.pro.discuz.repositories.entity.DiscuzCommonMemberStatusDO;
import net.renfei.pro.discuz.repositories.entity.DiscuzCommonMemberStatusDOExample;
import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Param;

import java.util.List;

@Mapper
public interface DiscuzCommonMemberStatusDOMapper {
    long countByExample(DiscuzCommonMemberStatusDOExample example);

    int deleteByExample(DiscuzCommonMemberStatusDOExample example);

    int deleteByPrimaryKey(Integer uid);

    int insert(DiscuzCommonMemberStatusDO record);

    int insertSelective(DiscuzCommonMemberStatusDO record);

    List<DiscuzCommonMemberStatusDO> selectByExample(DiscuzCommonMemberStatusDOExample example);

    DiscuzCommonMemberStatusDO selectByPrimaryKey(Integer uid);

    int updateByExampleSelective(@Param("record") DiscuzCommonMemberStatusDO record, @Param("example") DiscuzCommonMemberStatusDOExample example);

    int updateByExample(@Param("record") DiscuzCommonMemberStatusDO record, @Param("example") DiscuzCommonMemberStatusDOExample example);

    int updateByPrimaryKeySelective(DiscuzCommonMemberStatusDO record);

    int updateByPrimaryKey(DiscuzCommonMemberStatusDO record);
}