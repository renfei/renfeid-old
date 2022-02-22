package net.renfei.repositories;

import java.util.List;
import net.renfei.repositories.model.WechatKeyword;
import net.renfei.repositories.model.WechatKeywordExample;
import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Param;

@Mapper
public interface WechatKeywordMapper {
    long countByExample(WechatKeywordExample example);

    int deleteByExample(WechatKeywordExample example);

    int deleteByPrimaryKey(Long id);

    int insert(WechatKeyword record);

    int insertSelective(WechatKeyword record);

    List<WechatKeyword> selectByExample(WechatKeywordExample example);

    WechatKeyword selectByPrimaryKey(Long id);

    int updateByExampleSelective(@Param("record") WechatKeyword record, @Param("example") WechatKeywordExample example);

    int updateByExample(@Param("record") WechatKeyword record, @Param("example") WechatKeywordExample example);

    int updateByPrimaryKeySelective(WechatKeyword record);

    int updateByPrimaryKey(WechatKeyword record);
}