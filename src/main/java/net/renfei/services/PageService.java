package net.renfei.services;

import net.renfei.repositories.model.SysPagesWithBLOBs;

import java.util.List;

/**
 * 页面服务
 *
 * @author renfei
 */
public interface PageService {
    List<SysPagesWithBLOBs> getAllPageNotCache();
}
