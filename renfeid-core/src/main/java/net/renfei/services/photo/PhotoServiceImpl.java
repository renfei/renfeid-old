package net.renfei.services.photo;

import net.renfei.domain.AlbumDomain;
import net.renfei.domain.photo.Album;
import net.renfei.model.ListData;
import net.renfei.services.BaseService;
import net.renfei.services.PhotoService;
import net.renfei.utils.NumberUtils;
import org.springframework.stereotype.Service;

@Service
public class PhotoServiceImpl extends BaseService implements PhotoService {
    @Override
    public ListData<Album> getAllAlbumList(String pages, String rows) {
        return AlbumDomain.allAlbumList(NumberUtils.parseInt(pages, 1), NumberUtils.parseInt(rows, 10));
    }

    @Override
    public AlbumDomain getPhotoImgByPhotoId(Long id) {
        return new AlbumDomain(id);
    }
}
