package net.renfei.repositories;

import java.util.List;
import net.renfei.repositories.model.PhotoAlbum;
import net.renfei.repositories.model.PhotoAlbumExample;
import net.renfei.repositories.model.PhotoAlbumWithBLOBs;
import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Param;

@Mapper
public interface PhotoAlbumMapper {
    long countByExample(PhotoAlbumExample example);

    int deleteByExample(PhotoAlbumExample example);

    int insert(PhotoAlbumWithBLOBs record);

    int insertSelective(PhotoAlbumWithBLOBs record);

    List<PhotoAlbumWithBLOBs> selectByExampleWithBLOBs(PhotoAlbumExample example);

    List<PhotoAlbum> selectByExample(PhotoAlbumExample example);

    int updateByExampleSelective(@Param("record") PhotoAlbumWithBLOBs record, @Param("example") PhotoAlbumExample example);

    int updateByExampleWithBLOBs(@Param("record") PhotoAlbumWithBLOBs record, @Param("example") PhotoAlbumExample example);

    int updateByExample(@Param("record") PhotoAlbum record, @Param("example") PhotoAlbumExample example);
}