package net.renfei.discuz.repositories;

import net.renfei.discuz.repositories.entity.DiscuzCommonMemberCountDO;
import net.renfei.discuz.repositories.entity.DiscuzCommonMemberCountDOExample;
import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Param;

import java.util.List;

@Mapper
public interface DiscuzCommonMemberCountDOMapper {
    long countByExample(DiscuzCommonMemberCountDOExample example);

    int deleteByExample(DiscuzCommonMemberCountDOExample example);

    int deleteByPrimaryKey(Integer uid);

    int insert(DiscuzCommonMemberCountDO record);

    int insertSelective(DiscuzCommonMemberCountDO record);

    List<DiscuzCommonMemberCountDO> selectByExample(DiscuzCommonMemberCountDOExample example);

    DiscuzCommonMemberCountDO selectByPrimaryKey(Integer uid);

    int updateByExampleSelective(@Param("record") DiscuzCommonMemberCountDO record, @Param("example") DiscuzCommonMemberCountDOExample example);

    int updateByExample(@Param("record") DiscuzCommonMemberCountDO record, @Param("example") DiscuzCommonMemberCountDOExample example);

    int updateByPrimaryKeySelective(DiscuzCommonMemberCountDO record);

    int updateByPrimaryKey(DiscuzCommonMemberCountDO record);
}