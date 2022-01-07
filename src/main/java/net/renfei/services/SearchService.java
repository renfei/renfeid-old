package net.renfei.services;

import net.renfei.model.ListData;
import net.renfei.model.kitbox.IkAnalyzeVO;
import net.renfei.model.search.SearchItem;
import net.renfei.model.search.TypeEnum;
import net.renfei.repositories.model.HotSearch;
import org.elasticsearch.index.query.QueryBuilder;

import java.io.IOException;
import java.util.Date;
import java.util.List;

/**
 * 搜索服务
 *
 * @author renfei
 */
public interface SearchService {
    void createIndex();
    void deleteIndex();
    ListData<SearchItem> search(String word, String pages, String rows);
    ListData<SearchItem> search(TypeEnum type, String word, String pages, String rows);
    ListData<SearchItem> search(TypeEnum type, Date startDate, Date endDate, String word, String pages, String rows);
    ListData<SearchItem> search(TypeEnum type, Long originalId, String pages, String rows);
    ListData<SearchItem> search(QueryBuilder queryBuilder, String pages, String rows);
    List<IkAnalyzeVO> getIkAnalyzeTerms(String word) throws IOException;
    List<HotSearch> getHotSearchList();
}
