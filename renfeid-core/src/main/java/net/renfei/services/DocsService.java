package net.renfei.services;

import net.renfei.model.kitbox.KitBoxMenus;
import net.renfei.repositories.model.DocsOnlineDocuments;

import java.util.List;

/**
 * 在线文档栏目
 *
 * @author renfei
 */
public interface DocsService {
    List<KitBoxMenus> getDocs();
    DocsOnlineDocuments getEmbed(String category, String title, String version, String lang);
}
