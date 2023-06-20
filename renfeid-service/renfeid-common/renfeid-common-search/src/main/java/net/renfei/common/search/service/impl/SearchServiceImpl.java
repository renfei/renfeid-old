/*
 *   Copyright 2022 RenFei(i@renfei.net)
 *
 * Licensed under the Apache License, Version 2.0 (the "License");
 * you may not use this file except in compliance with the License.
 * You may obtain a copy of the License at
 *
 *   http://www.apache.org/licenses/LICENSE-2.0
 *
 * Unless required by applicable law or agreed to in writing, software
 * distributed under the License is distributed on an "AS IS" BASIS,
 * WITHOUT WARRANTIES OR CONDITIONS OF ANY KIND, either express or implied.
 * See the License for the specific language governing permissions and
 * limitations under the License.
 */
package net.renfei.common.search.service.impl;

import co.elastic.clients.elasticsearch._types.query_dsl.Query;
import co.elastic.clients.elasticsearch._types.query_dsl.QueryBuilders;
import co.elastic.clients.json.JsonData;
import net.renfei.common.api.entity.ListData;
import net.renfei.common.api.utils.NumberUtils;
import net.renfei.common.search.entity.SearchItem;
import net.renfei.common.search.entity.TypeEnum;
import net.renfei.common.search.repository.SearchRepository;
import net.renfei.common.search.service.SearchService;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.elasticsearch.client.elc.ElasticsearchTemplate;
import org.springframework.data.elasticsearch.client.elc.NativeQuery;
import org.springframework.data.elasticsearch.client.elc.NativeQueryBuilder;
import org.springframework.data.elasticsearch.core.IndexOperations;
import org.springframework.data.elasticsearch.core.SearchHits;
import org.springframework.data.elasticsearch.core.document.Document;
import org.springframework.data.elasticsearch.core.mapping.IndexCoordinates;
import org.springframework.stereotype.Service;
import org.springframework.util.ObjectUtils;

import java.util.ArrayList;
import java.util.Date;
import java.util.List;

import static co.elastic.clients.elasticsearch._types.query_dsl.QueryBuilders.match;
import static net.renfei.common.search.entity.SearchItem.INDEX_COORDINATES;

/**
 * 搜索服务
 *
 * @author renfei
 */
@Service
public class SearchServiceImpl implements SearchService {
    private final SearchRepository searchRepository;
    private final ElasticsearchTemplate elasticsearchTemplate;

    public SearchServiceImpl(SearchRepository searchRepository,
                             ElasticsearchTemplate elasticsearchTemplate) {
        this.searchRepository = searchRepository;
        this.elasticsearchTemplate = elasticsearchTemplate;
    }

    /**
     * 搜索
     *
     * @param word  搜索词
     * @param pages 页码
     * @param rows  每页容量
     * @return
     */
    @Override
    public ListData<SearchItem> search(String word, String pages, String rows) {
        return search(null, null, null, word, pages, rows);
    }

    /**
     * 搜索
     *
     * @param type  类型
     * @param word  搜索词
     * @param pages 页码
     * @param rows  每页容量
     * @return
     */
    @Override
    public ListData<SearchItem> search(TypeEnum type, String word, String pages, String rows) {
        return search(type, null, null, word, pages, rows);
    }

    /**
     * 搜索
     *
     * @param type      类型
     * @param startDate 开始时间
     * @param endDate   结束时间
     * @param word      搜索词
     * @param pages     页码
     * @param rows      每页容量
     * @return
     */
    @Override
    public ListData<SearchItem> search(TypeEnum type, Date startDate, Date endDate, String word, String pages, String rows) {
        NativeQueryBuilder build = NativeQuery.builder();
        if (!ObjectUtils.isEmpty(word)) {
            Query bool = QueryBuilders.bool(builder -> builder.must(
                    match(queryAuthor -> queryAuthor.field("title").query(word)),
                    match(queryTitle -> queryTitle.field("content").query(word))
            ));
            build.withQuery(bool);
        }
        if (type != null) {
            if (!TypeEnum.ALL.equals(type)) {
                Query query = QueryBuilders.term(builder -> builder.field("type").value(type.getName()));
                build.withQuery(query);
            }
        }
        if (startDate != null) {
            Query query = QueryBuilders.range(builder -> builder.field("date").gte(JsonData.of(startDate)));
            build.withQuery(query);
        }
        if (endDate != null) {
            Query query = QueryBuilders.range(builder -> builder.field("date").lte(JsonData.of(endDate)));
            build.withQuery(query);
        }
        return search(build, pages, rows);
    }

    @Override
    public ListData<SearchItem> search(TypeEnum type, Long originalId, String pages, String rows) {
        Query bool = QueryBuilders.bool(builder -> builder.must(
                match(queryAuthor -> queryAuthor.field("type").query(type.getName())),
                match(queryTitle -> queryTitle.field("originalId").query(originalId))
        ));
        return search(NativeQuery.builder().withQuery(bool), pages, rows);
    }

    @Override
    public ListData<SearchItem> search(NativeQueryBuilder queryBuilder, String pages, String rows) {
        int page = NumberUtils.parseInt(pages, 1) - 1,
                size = NumberUtils.parseInt(rows, 10);
        if (page < 0) {
            page = 0;
        }
        if (size < 0) {
            size = 10;
        }
        NativeQuery query = queryBuilder.withPageable(PageRequest.of(page, size)).build();
        SearchHits<SearchItem> search = elasticsearchTemplate.search(query, SearchItem.class, IndexCoordinates.of(INDEX_COORDINATES));
        ListData<SearchItem> searchItemListData = new ListData<>();
        searchItemListData.setTotal(search.getTotalHits());
        List<SearchItem> searchItems = new ArrayList<>();
        search.forEach(searchItemSearchHit -> searchItems.add(searchItemSearchHit.getContent()));
        searchItemListData.setData(searchItems);
        return searchItemListData;
    }

    /**
     * 创建索引库，如果不存在触发全量同步
     */
    @Override
    public void createIndex(List<SearchItem> searchItemAll) {
        /*
        由于我的博客数据量小，更新频率低，所以采取每天删掉索引重建的方式来同步数据
        省去了丢失的全量同步，新增的增量同步，直接每天凌晨全量导入
         */
        IndexOperations indexOperations = elasticsearchTemplate.indexOps(SearchItem.class);
        if (!indexOperations.exists()) {
            // 不存在索引库，创建索引库
            indexOperations.create();
            //Creates the index mapping for the entity this IndexOperations is bound to.
            //为该IndexOperations绑定到的实体创建索引映射。  有一个为给定类创建索引的重载,需要类的字节码文件
            Document mapping = indexOperations.createMapping();
            //writes a mapping to the index  将刚刚通过类创建的映射写入索引
            indexOperations.putMapping(mapping);
            this.searchRepository.saveAll(searchItemAll);
        }
    }

    @Override
    public void deleteIndex() {
        IndexOperations indexOperations = elasticsearchTemplate.indexOps(SearchItem.class);
        if (indexOperations.exists()) {
            // 已经存在，删除
            indexOperations.delete();
        }
    }
}
