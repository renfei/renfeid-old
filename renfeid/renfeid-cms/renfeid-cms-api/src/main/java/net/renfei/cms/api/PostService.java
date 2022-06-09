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
package net.renfei.cms.api;

import net.renfei.cms.api.constant.enums.PostStatusEnum;
import net.renfei.cms.api.entity.Post;
import net.renfei.common.api.constant.APIResult;
import net.renfei.common.api.entity.ListData;

import java.util.Date;

/**
 * 文章服务
 *
 * @author renfei
 */
public interface PostService {
    /**
     * 查询文章列表
     *
     * @param categoryId 分类ID
     * @param postStatus 文章状态
     * @param startDate  开始时间
     * @param endDate    结束时间
     * @param pages      页码
     * @param rows       每页行数
     * @return
     */
    APIResult<ListData<Post>> queryPostList(Long categoryId, PostStatusEnum postStatus,
                                            Date startDate, Date endDate, int pages, int rows);
}
