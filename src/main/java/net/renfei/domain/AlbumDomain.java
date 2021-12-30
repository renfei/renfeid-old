package net.renfei.domain;

import com.github.pagehelper.Page;
import com.github.pagehelper.PageHelper;
import lombok.Getter;
import net.renfei.domain.blog.Category;
import net.renfei.domain.photo.Album;
import net.renfei.domain.photo.AlbumImg;
import net.renfei.model.ListData;
import net.renfei.repositories.PhotoAlbumImgMapper;
import net.renfei.repositories.PhotoAlbumMapper;
import net.renfei.repositories.model.PhotoAlbumExample;
import net.renfei.repositories.model.PhotoAlbumImg;
import net.renfei.repositories.model.PhotoAlbumImgExample;
import net.renfei.repositories.model.PhotoAlbumWithBLOBs;
import net.renfei.utils.ApplicationContextUtil;
import net.renfei.utils.ListUtils;
import org.springframework.beans.BeanUtils;

import java.util.Date;
import java.util.List;
import java.util.concurrent.CopyOnWriteArrayList;

public final class AlbumDomain {
    @Getter
    private final Album album;
    @Getter
    private final List<AlbumImg> albumImgList;
    private final PhotoAlbumMapper photoAlbumMapper;
    private final PhotoAlbumImgMapper photoAlbumImgMapper;

    {
        photoAlbumMapper = (PhotoAlbumMapper) ApplicationContextUtil.getBean("photoAlbumMapper");
        photoAlbumImgMapper = (PhotoAlbumImgMapper) ApplicationContextUtil.getBean("photoAlbumImgMapper");
    }

    private AlbumDomain() {
        album = null;
        albumImgList = null;
    }

    public AlbumDomain(Long id) {
        album = initAlbum(id);
        if (album == null) {
            albumImgList = new CopyOnWriteArrayList<>();
        } else {
            albumImgList = initAlbumImgList(id);
        }
    }

    public static ListData<Album> allAlbumList(int pages, int rows) {
        AlbumDomain albumDomain = new AlbumDomain();
        return albumDomain.getAllAlbumList(pages, rows);
    }

    private Album initAlbum(Long id) {
        PhotoAlbumExample example = new PhotoAlbumExample();
        example
                .createCriteria()
                .andIdEqualTo(id)
                .andIsDeleteEqualTo(false)
                .andReleaseTimeLessThanOrEqualTo(new Date());
        return convert(ListUtils.getOne(photoAlbumMapper.selectByExampleWithBLOBs(example)));
    }

    private List<AlbumImg> initAlbumImgList(Long albumId) {
        PhotoAlbumImgExample example = new PhotoAlbumImgExample();
        example
                .createCriteria()
                .andPhotoIdEqualTo(albumId);
        List<AlbumImg> albumImgList = new CopyOnWriteArrayList<>();
        for (PhotoAlbumImg photoAlbumImg : photoAlbumImgMapper.selectByExampleWithBLOBs(example)
        ) {
            albumImgList.add(convert(photoAlbumImg));
        }
        return albumImgList;
    }

    private ListData<Album> getAllAlbumList(int pages, int rows) {
        assert photoAlbumMapper != null;
        PhotoAlbumExample example = new PhotoAlbumExample();
        example.setOrderByClause("release_time DESC");
        example
                .createCriteria()
                .andIsDeleteEqualTo(false)
                .andReleaseTimeLessThanOrEqualTo(new Date());
        Page<PhotoAlbumWithBLOBs> page = PageHelper.startPage(pages, rows);
        photoAlbumMapper.selectByExampleWithBLOBs(example);
        ListData<Album> albumListData = new ListData<>(page);
        List<Album> albumList = new CopyOnWriteArrayList<>();
        for (PhotoAlbumWithBLOBs photoAlbumWithBLOBs : page.getResult()
        ) {
            albumList.add(convert(photoAlbumWithBLOBs));
        }
        albumListData.setData(albumList);
        return albumListData;
    }

    private Album convert(PhotoAlbumWithBLOBs photoAlbums) {
        if (photoAlbums == null) {
            return null;
        }
        Album album = new Album();
        BeanUtils.copyProperties(photoAlbums, album);
        return album;
    }

    private AlbumImg convert(PhotoAlbumImg photoAlbumImg) {
        if (photoAlbumImg == null) {
            return null;
        }
        AlbumImg album = new AlbumImg();
        BeanUtils.copyProperties(photoAlbumImg, album);
        return album;
    }
}
