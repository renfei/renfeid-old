package net.renfei.common.core.repositories;

import net.renfei.common.core.repositories.entity.CoreSiteFriendlyLink;
import net.renfei.common.core.repositories.entity.CoreSiteFriendlyLinkExample;
import net.renfei.common.core.repositories.entity.CoreSiteFriendlyLinkWithBLOBs;
import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Param;

import java.util.List;

@Mapper
public interface CoreSiteFriendlyLinkMapper {
    long countByExample(CoreSiteFriendlyLinkExample example);

    int deleteByExample(CoreSiteFriendlyLinkExample example);

    int deleteByPrimaryKey(Long id);

    int insert(CoreSiteFriendlyLinkWithBLOBs row);

    int insertSelective(CoreSiteFriendlyLinkWithBLOBs row);

    List<CoreSiteFriendlyLinkWithBLOBs> selectByExampleWithBLOBs(CoreSiteFriendlyLinkExample example);

    List<CoreSiteFriendlyLink> selectByExample(CoreSiteFriendlyLinkExample example);

    CoreSiteFriendlyLinkWithBLOBs selectByPrimaryKey(Long id);

    int updateByExampleSelective(@Param("row") CoreSiteFriendlyLinkWithBLOBs row, @Param("example") CoreSiteFriendlyLinkExample example);

    int updateByExampleWithBLOBs(@Param("row") CoreSiteFriendlyLinkWithBLOBs row, @Param("example") CoreSiteFriendlyLinkExample example);

    int updateByExample(@Param("row") CoreSiteFriendlyLink row, @Param("example") CoreSiteFriendlyLinkExample example);

    int updateByPrimaryKeySelective(CoreSiteFriendlyLinkWithBLOBs row);

    int updateByPrimaryKeyWithBLOBs(CoreSiteFriendlyLinkWithBLOBs row);

    int updateByPrimaryKey(CoreSiteFriendlyLink row);
}