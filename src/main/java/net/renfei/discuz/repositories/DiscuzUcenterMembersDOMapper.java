package net.renfei.discuz.repositories;

import net.renfei.discuz.repositories.entity.DiscuzUcenterMembersDO;
import net.renfei.discuz.repositories.entity.DiscuzUcenterMembersDOExample;
import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Param;

import java.util.List;

@Mapper
public interface DiscuzUcenterMembersDOMapper {
    long countByExample(DiscuzUcenterMembersDOExample example);

    int deleteByExample(DiscuzUcenterMembersDOExample example);

    int deleteByPrimaryKey(Integer uid);

    int insert(DiscuzUcenterMembersDO record);

    int insertSelective(DiscuzUcenterMembersDO record);

    List<DiscuzUcenterMembersDO> selectByExample(DiscuzUcenterMembersDOExample example);

    DiscuzUcenterMembersDO selectByPrimaryKey(Integer uid);

    int updateByExampleSelective(@Param("record") DiscuzUcenterMembersDO record, @Param("example") DiscuzUcenterMembersDOExample example);

    int updateByExample(@Param("record") DiscuzUcenterMembersDO record, @Param("example") DiscuzUcenterMembersDOExample example);

    int updateByPrimaryKeySelective(DiscuzUcenterMembersDO record);

    int updateByPrimaryKey(DiscuzUcenterMembersDO record);
}