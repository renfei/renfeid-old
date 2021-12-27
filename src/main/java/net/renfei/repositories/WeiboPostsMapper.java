package net.renfei.repositories;

import java.util.List;
import net.renfei.repositories.model.WeiboPosts;
import net.renfei.repositories.model.WeiboPostsExample;
import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Param;

@Mapper
public interface WeiboPostsMapper {
    long countByExample(WeiboPostsExample example);

    int deleteByExample(WeiboPostsExample example);

    int insert(WeiboPosts record);

    int insertSelective(WeiboPosts record);

    List<WeiboPosts> selectByExample(WeiboPostsExample example);

    int updateByExampleSelective(@Param("record") WeiboPosts record, @Param("example") WeiboPostsExample example);

    int updateByExample(@Param("record") WeiboPosts record, @Param("example") WeiboPostsExample example);
}