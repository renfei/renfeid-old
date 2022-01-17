package net.renfei.services;

import net.renfei.discuz.repositories.entity.DiscuzForumPostDO;
import net.renfei.model.discuz.DiscuzInfo;

import java.util.List;

/**
 * @author renfei
 */
public interface DiscuzService {
    DiscuzInfo getDiscuzInfo(String userName);
    List<DiscuzForumPostDO> getAllPost();
}
