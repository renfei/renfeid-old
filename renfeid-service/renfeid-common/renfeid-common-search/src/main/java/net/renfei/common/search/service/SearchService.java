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
package net.renfei.common.search.service;

import net.renfei.common.api.entity.ListData;
import net.renfei.common.search.entity.SearchItem;
import net.renfei.common.search.entity.TypeEnum;
import org.elasticsearch.index.query.QueryBuilder;

import java.util.Date;

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
}
