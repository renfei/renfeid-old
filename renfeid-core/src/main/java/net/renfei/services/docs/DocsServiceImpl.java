package net.renfei.services.docs;

import net.renfei.model.LinkTree;
import net.renfei.model.kitbox.KitBoxMenus;
import net.renfei.repositories.DocsOnlineDocumentsMapper;
import net.renfei.repositories.model.DocsOnlineDocuments;
import net.renfei.repositories.model.DocsOnlineDocumentsExample;
import net.renfei.services.BaseService;
import net.renfei.services.DocsService;
import net.renfei.utils.ListUtils;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;
import java.util.Map;

/**
 * 在线文档栏目
 * 由于在线文档功能有限，不使用 Domain 模型类
 *
 * @author renfei
 */
@Service
public class DocsServiceImpl extends BaseService implements DocsService {
    private final DocsOnlineDocumentsMapper docsOnlineDocumentsMapper;

    public DocsServiceImpl(DocsOnlineDocumentsMapper docsOnlineDocumentsMapper) {
        this.docsOnlineDocumentsMapper = docsOnlineDocumentsMapper;
    }

    @Override
    public List<KitBoxMenus> getDocs() {
        List<KitBoxMenus> kitBoxMenus = new ArrayList<>();
        List<Map<String, Object>> category = docsOnlineDocumentsMapper.selectCategory();
        for (Map<String, Object> map : category
        ) {
            KitBoxMenus menus = KitBoxMenus.builder()
                    .title(map.get("category").toString())
                    .build();
            DocsOnlineDocumentsExample example = new DocsOnlineDocumentsExample();
            example.setOrderByClause("version DESC");
            example.createCriteria()
                    .andCategoryEqualTo(map.get("category").toString());
            List<DocsOnlineDocuments> onlineDocumentsDOS = docsOnlineDocumentsMapper.selectByExample(example);
            List<LinkTree> links = new ArrayList<>();
            for (DocsOnlineDocuments onlineDocuments : onlineDocumentsDOS
            ) {
                links.add(LinkTree.builder()
                        .href("/docs/" + onlineDocuments.getCategory() + "/" + onlineDocuments.getTitle() + "/" + onlineDocuments.getVersion() + "/" + onlineDocuments.getLang())
                        .text(onlineDocuments.getTitle() + " " + onlineDocuments.getVersion() + " (" + onlineDocuments.getLang() + ")")
                        .rel(onlineDocuments.getDescribe())
                        .build());
            }
            menus.setLinks(links);
            kitBoxMenus.add(menus);
        }
        return kitBoxMenus;
    }

    @Override
    public DocsOnlineDocuments getEmbed(String category, String title, String version, String lang) {
        DocsOnlineDocumentsExample example = new DocsOnlineDocumentsExample();
        example.createCriteria()
                .andCategoryEqualTo(category)
                .andTitleEqualTo(title)
                .andVersionEqualTo(version)
                .andLangEqualTo(lang);
        return ListUtils.getOne(docsOnlineDocumentsMapper.selectByExample(example));
    }
}
