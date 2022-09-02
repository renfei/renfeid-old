package net.renfei.pro.discuz.repositories;

import net.renfei.pro.discuz.repositories.entity.DiscuzCommonMemberFieldHomeDO;
import net.renfei.pro.discuz.repositories.entity.DiscuzCommonMemberFieldHomeDOExample;
import net.renfei.pro.discuz.repositories.entity.DiscuzCommonMemberFieldHomeDOWithBLOBs;
import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Param;

import java.util.List;

@Mapper
public interface DiscuzCommonMemberFieldHomeDOMapper {
    long countByExample(DiscuzCommonMemberFieldHomeDOExample example);

    int deleteByExample(DiscuzCommonMemberFieldHomeDOExample example);

    int deleteByPrimaryKey(Integer uid);

    int insert(DiscuzCommonMemberFieldHomeDOWithBLOBs record);

    int insertSelective(DiscuzCommonMemberFieldHomeDOWithBLOBs record);

    List<DiscuzCommonMemberFieldHomeDOWithBLOBs> selectByExampleWithBLOBs(DiscuzCommonMemberFieldHomeDOExample example);

    List<DiscuzCommonMemberFieldHomeDO> selectByExample(DiscuzCommonMemberFieldHomeDOExample example);

    DiscuzCommonMemberFieldHomeDOWithBLOBs selectByPrimaryKey(Integer uid);

    int updateByExampleSelective(@Param("record") DiscuzCommonMemberFieldHomeDOWithBLOBs record, @Param("example") DiscuzCommonMemberFieldHomeDOExample example);

    int updateByExampleWithBLOBs(@Param("record") DiscuzCommonMemberFieldHomeDOWithBLOBs record, @Param("example") DiscuzCommonMemberFieldHomeDOExample example);

    int updateByExample(@Param("record") DiscuzCommonMemberFieldHomeDO record, @Param("example") DiscuzCommonMemberFieldHomeDOExample example);

    int updateByPrimaryKeySelective(DiscuzCommonMemberFieldHomeDOWithBLOBs record);

    int updateByPrimaryKeyWithBLOBs(DiscuzCommonMemberFieldHomeDOWithBLOBs record);

    int updateByPrimaryKey(DiscuzCommonMemberFieldHomeDO record);
}