package net.renfei.discuz.repositories;

import net.renfei.discuz.repositories.entity.DiscuzCommonMemberStatusDO;
import net.renfei.discuz.repositories.entity.DiscuzCommonMemberStatusDOExample;
import org.apache.ibatis.annotations.Param;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
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