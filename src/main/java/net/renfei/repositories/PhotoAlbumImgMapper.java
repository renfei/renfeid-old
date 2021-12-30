package net.renfei.repositories;

import java.util.List;
import net.renfei.repositories.model.PhotoAlbumImg;
import net.renfei.repositories.model.PhotoAlbumImgExample;
import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Param;

@Mapper
public interface PhotoAlbumImgMapper {
    long countByExample(PhotoAlbumImgExample example);

    int deleteByExample(PhotoAlbumImgExample example);

    int insert(PhotoAlbumImg record);

    int insertSelective(PhotoAlbumImg record);

    List<PhotoAlbumImg> selectByExampleWithBLOBs(PhotoAlbumImgExample example);

    List<PhotoAlbumImg> selectByExample(PhotoAlbumImgExample example);

    int updateByExampleSelective(@Param("record") PhotoAlbumImg record, @Param("example") PhotoAlbumImgExample example);

    int updateByExampleWithBLOBs(@Param("record") PhotoAlbumImg record, @Param("example") PhotoAlbumImgExample example);

    int updateByExample(@Param("record") PhotoAlbumImg record, @Param("example") PhotoAlbumImgExample example);
}