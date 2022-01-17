package net.renfei.repositories;

import java.util.List;
import net.renfei.repositories.model.KitboxShortUrl;
import net.renfei.repositories.model.KitboxShortUrlExample;
import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Param;

@Mapper
public interface KitboxShortUrlMapper {
    long countByExample(KitboxShortUrlExample example);

    int deleteByExample(KitboxShortUrlExample example);

    int deleteByPrimaryKey(Long id);

    int insert(KitboxShortUrl record);

    int insertSelective(KitboxShortUrl record);

    List<KitboxShortUrl> selectByExample(KitboxShortUrlExample example);

    KitboxShortUrl selectByPrimaryKey(Long id);

    int updateByExampleSelective(@Param("record") KitboxShortUrl record, @Param("example") KitboxShortUrlExample example);

    int updateByExample(@Param("record") KitboxShortUrl record, @Param("example") KitboxShortUrlExample example);

    int updateByPrimaryKeySelective(KitboxShortUrl record);

    int updateByPrimaryKey(KitboxShortUrl record);
}