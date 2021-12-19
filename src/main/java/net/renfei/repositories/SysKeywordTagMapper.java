package net.renfei.repositories;

import java.util.List;
import java.util.Map;

import net.renfei.repositories.model.SysKeywordTag;
import net.renfei.repositories.model.SysKeywordTagExample;
import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Param;
import org.apache.ibatis.annotations.Select;

@Mapper
public interface SysKeywordTagMapper {
    long countByExample(SysKeywordTagExample example);

    int deleteByExample(SysKeywordTagExample example);

    int deleteByPrimaryKey(Long id);

    int insert(SysKeywordTag record);

    int insertSelective(SysKeywordTag record);

    List<SysKeywordTag> selectByExample(SysKeywordTagExample example);

    SysKeywordTag selectByPrimaryKey(Long id);

    int updateByExampleSelective(@Param("record") SysKeywordTag record, @Param("example") SysKeywordTagExample example);

    int updateByExample(@Param("record") SysKeywordTag record, @Param("example") SysKeywordTagExample example);

    int updateByPrimaryKeySelective(SysKeywordTag record);

    int updateByPrimaryKey(SysKeywordTag record);

    @Select("SELECT" +
            "    renfeid.sys_keyword_tag.*," +
            "    COUNT( renfeid.sys_keyword_object.tag_id ) AS CNT " +
            "FROM" +
            "    renfeid.sys_keyword_tag" +
            "    LEFT JOIN renfeid.sys_keyword_object ON renfeid.sys_keyword_tag.id = renfeid.sys_keyword_object.tag_id " +
            "WHERE" +
            "    renfeid.sys_keyword_object.object_type = #{systemTypeEnum,jdbcType=VARCHAR} " +
            "GROUP BY" +
            "    renfeid.sys_keyword_object.object_type")
    List<Map<String, Object>> getTagAndCount(@Param("systemTypeEnum") String systemTypeEnum);
}