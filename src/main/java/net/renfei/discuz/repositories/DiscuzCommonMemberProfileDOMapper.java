package net.renfei.discuz.repositories;

import net.renfei.discuz.repositories.entity.DiscuzCommonMemberProfileDO;
import net.renfei.discuz.repositories.entity.DiscuzCommonMemberProfileDOExample;
import net.renfei.discuz.repositories.entity.DiscuzCommonMemberProfileDOWithBLOBs;
import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Param;

import java.util.List;

@Mapper
public interface DiscuzCommonMemberProfileDOMapper {
    long countByExample(DiscuzCommonMemberProfileDOExample example);

    int deleteByExample(DiscuzCommonMemberProfileDOExample example);

    int deleteByPrimaryKey(Integer uid);

    int insert(DiscuzCommonMemberProfileDOWithBLOBs record);

    int insertSelective(DiscuzCommonMemberProfileDOWithBLOBs record);

    List<DiscuzCommonMemberProfileDOWithBLOBs> selectByExampleWithBLOBs(DiscuzCommonMemberProfileDOExample example);

    List<DiscuzCommonMemberProfileDO> selectByExample(DiscuzCommonMemberProfileDOExample example);

    DiscuzCommonMemberProfileDOWithBLOBs selectByPrimaryKey(Integer uid);

    int updateByExampleSelective(@Param("record") DiscuzCommonMemberProfileDOWithBLOBs record, @Param("example") DiscuzCommonMemberProfileDOExample example);

    int updateByExampleWithBLOBs(@Param("record") DiscuzCommonMemberProfileDOWithBLOBs record, @Param("example") DiscuzCommonMemberProfileDOExample example);

    int updateByExample(@Param("record") DiscuzCommonMemberProfileDO record, @Param("example") DiscuzCommonMemberProfileDOExample example);

    int updateByPrimaryKeySelective(DiscuzCommonMemberProfileDOWithBLOBs record);

    int updateByPrimaryKeyWithBLOBs(DiscuzCommonMemberProfileDOWithBLOBs record);

    int updateByPrimaryKey(DiscuzCommonMemberProfileDO record);
}