package net.renfei.services;

import net.renfei.domain.AlbumDomain;
import net.renfei.domain.photo.Album;
import net.renfei.model.ListData;

public interface PhotoService {
    ListData<Album> getAllAlbumList(String pages, String rows);

    AlbumDomain getPhotoImgByPhotoId(Long id);
}
