package net.renfei.discuz.repositories;

import net.renfei.discuz.repositories.entity.DiscuzCommonMemberDO;
import net.renfei.discuz.repositories.entity.DiscuzCommonMemberDOExample;
import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Param;

import java.util.List;

@Mapper
public interface DiscuzCommonMemberDOMapper {
    long countByExample(DiscuzCommonMemberDOExample example);

    int deleteByExample(DiscuzCommonMemberDOExample example);

    int deleteByPrimaryKey(Integer uid);

    int insert(DiscuzCommonMemberDO record);

    int insertSelective(DiscuzCommonMemberDO record);

    List<DiscuzCommonMemberDO> selectByExample(DiscuzCommonMemberDOExample example);

    DiscuzCommonMemberDO selectByPrimaryKey(Integer uid);

    int updateByExampleSelective(@Param("record") DiscuzCommonMemberDO record, @Param("example") DiscuzCommonMemberDOExample example);

    int updateByExample(@Param("record") DiscuzCommonMemberDO record, @Param("example") DiscuzCommonMemberDOExample example);

    int updateByPrimaryKeySelective(DiscuzCommonMemberDO record);

    int updateByPrimaryKey(DiscuzCommonMemberDO record);
}