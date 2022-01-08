package net.renfei.discuz.repositories;

import net.renfei.discuz.repositories.entity.DiscuzCommonMemberFieldHomeDO;
import net.renfei.discuz.repositories.entity.DiscuzCommonMemberFieldHomeDOExample;
import net.renfei.discuz.repositories.entity.DiscuzCommonMemberFieldHomeDOWithBLOBs;
import org.apache.ibatis.annotations.Param;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
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