package net.renfei.repositories;

import java.util.List;
import net.renfei.repositories.model.SysSiteFriendlyLink;
import net.renfei.repositories.model.SysSiteFriendlyLinkExample;
import net.renfei.repositories.model.SysSiteFriendlyLinkWithBLOBs;
import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Param;

@Mapper
public interface SysSiteFriendlyLinkMapper {
    long countByExample(SysSiteFriendlyLinkExample example);

    int deleteByExample(SysSiteFriendlyLinkExample example);

    int deleteByPrimaryKey(Long id);

    int insert(SysSiteFriendlyLinkWithBLOBs record);

    int insertSelective(SysSiteFriendlyLinkWithBLOBs record);

    List<SysSiteFriendlyLinkWithBLOBs> selectByExampleWithBLOBs(SysSiteFriendlyLinkExample example);

    List<SysSiteFriendlyLink> selectByExample(SysSiteFriendlyLinkExample example);

    SysSiteFriendlyLinkWithBLOBs selectByPrimaryKey(Long id);

    int updateByExampleSelective(@Param("record") SysSiteFriendlyLinkWithBLOBs record, @Param("example") SysSiteFriendlyLinkExample example);

    int updateByExampleWithBLOBs(@Param("record") SysSiteFriendlyLinkWithBLOBs record, @Param("example") SysSiteFriendlyLinkExample example);

    int updateByExample(@Param("record") SysSiteFriendlyLink record, @Param("example") SysSiteFriendlyLinkExample example);

    int updateByPrimaryKeySelective(SysSiteFriendlyLinkWithBLOBs record);

    int updateByPrimaryKeyWithBLOBs(SysSiteFriendlyLinkWithBLOBs record);

    int updateByPrimaryKey(SysSiteFriendlyLink record);
}