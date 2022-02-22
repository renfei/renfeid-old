package net.renfei.discuz.service;

import net.renfei.discuz.model.DiscuzInfo;
import net.renfei.discuz.repositories.entity.DiscuzForumPostDO;

import java.util.List;

/**
 * @author renfei
 */
public interface DiscuzService {
    DiscuzInfo getDiscuzInfo(String userName);
    List<DiscuzForumPostDO> getAllPost();
}
