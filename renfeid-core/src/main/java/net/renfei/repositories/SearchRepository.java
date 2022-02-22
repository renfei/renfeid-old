package net.renfei.repositories;

import net.renfei.model.search.SearchItem;
import org.springframework.data.elasticsearch.repository.ElasticsearchRepository;

/**
 * @author renfei
 */
public interface SearchRepository extends ElasticsearchRepository<SearchItem, String> {
}
