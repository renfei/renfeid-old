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

import net.renfei.common.api.entity.ListData;
import net.renfei.common.api.utils.NumberUtils;
import net.renfei.common.search.entity.SearchItem;
import net.renfei.common.search.entity.TypeEnum;
import net.renfei.common.search.repository.SearchRepository;
import net.renfei.common.search.service.SearchService;
import org.elasticsearch.index.query.BoolQueryBuilder;
import org.elasticsearch.index.query.QueryBuilder;
import org.elasticsearch.index.query.QueryBuilders;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.elasticsearch.core.ElasticsearchRestTemplate;
import org.springframework.data.elasticsearch.core.IndexOperations;
import org.springframework.data.elasticsearch.core.SearchHits;
import org.springframework.data.elasticsearch.core.mapping.IndexCoordinates;
import org.springframework.data.elasticsearch.core.query.NativeSearchQuery;
import org.springframework.data.elasticsearch.core.query.NativeSearchQueryBuilder;
import org.springframework.stereotype.Service;
import org.springframework.util.ObjectUtils;

import java.util.ArrayList;
import java.util.Date;
import java.util.List;

import static net.renfei.common.search.entity.SearchItem.INDEX_COORDINATES;

/**
 * 搜索服务
 *
 * @author renfei
 */
@Service
public class SearchServiceImpl implements SearchService {
    private final SearchRepository searchRepository;
    private final ElasticsearchRestTemplate elasticsearchRestTemplate;

    public SearchServiceImpl(SearchRepository searchRepository,
                             ElasticsearchRestTemplate elasticsearchRestTemplate) {
        this.searchRepository = searchRepository;
        this.elasticsearchRestTemplate = elasticsearchRestTemplate;
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
        BoolQueryBuilder queryBuilder = QueryBuilders.boolQuery();
        if (!ObjectUtils.isEmpty(word)) {
            QueryBuilder wordBuilder = QueryBuilders.boolQuery()
                    .should(QueryBuilders.matchQuery("title", word))
                    .should(QueryBuilders.matchQuery("content", word));
            queryBuilder.must(wordBuilder);
        }
        if (type != null) {
            queryBuilder.must(QueryBuilders.termQuery("type", type.getName()));
        }
        if (startDate != null) {
            queryBuilder.must(QueryBuilders.rangeQuery("date").gt(startDate));
        }
        if (endDate != null) {
            queryBuilder.must(QueryBuilders.rangeQuery("date").lt(startDate));
        }
        return search(queryBuilder, pages, rows);
    }

    @Override
    public ListData<SearchItem> search(TypeEnum type, Long originalId, String pages, String rows) {
        BoolQueryBuilder queryBuilder = QueryBuilders.boolQuery()
                .must(QueryBuilders.termQuery("type", type.getName()))
                .must(QueryBuilders.termQuery("originalId", originalId));
        return search(queryBuilder, pages, rows);
    }

    @Override
    public ListData<SearchItem> search(QueryBuilder queryBuilder, String pages, String rows) {
        createIndex();
        int page = NumberUtils.parseInt(pages, 1) - 1,
                size = NumberUtils.parseInt(rows, 10);
        if (page < 0) {
            page = 0;
        }
        if (size < 0) {
            size = 10;
        }
        // 关键词高亮的色值
        NativeSearchQuery query = new NativeSearchQueryBuilder()
                .withQuery(queryBuilder)
                //添加分页  注意页码是从0开始的
                //pageable的实现类PageRequest的静态方法of
                //要排序就增加参数3 Sort.Direction.ASC升  Sort.Direction.DESC降
                .withPageable(PageRequest.of(page, size))
                //排序整体
                //根据字段排序fieldSort("字段名")   .order(SortOrder.ASC/DESC)
//                .withSort(SortBuilders.fieldSort("date").order(SortOrder.DESC))
                .build();
        //elasticsearchRestTemplate.search方法参数一,本机查询的构造,参数二index的类,可选参数三再次声明库名(可以多个)
        SearchHits<SearchItem> search = elasticsearchRestTemplate.search(query, SearchItem.class, IndexCoordinates.of(INDEX_COORDINATES));
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
    public void createIndex() {
        /*
        由于我的博客数据量小，更新频率低，所以采取每天删掉索引重建的方式来同步数据
        省去了丢失的全量同步，新增的增量同步，直接每天凌晨全量导入
         */
        // TODO 设置索引信息(绑定实体类)  返回IndexOperations
    }

    @Override
    public void deleteIndex() {
        IndexOperations indexOperations = elasticsearchRestTemplate.indexOps(SearchItem.class);
        if (indexOperations.exists()) {
            // 已经存在，删除
            indexOperations.delete();
        }
    }
}
