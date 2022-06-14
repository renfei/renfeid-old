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

import net.renfei.cms.api.entity.PostCategory;
import net.renfei.common.api.constant.APIResult;
import net.renfei.common.api.constant.enums.SecretLevelEnum;
import net.renfei.common.api.entity.ListData;

/**
 * 文章分类服务
 *
 * @author renfei
 */
public interface PostCategoryService {
    /**
     * 查询内容分类列表
     *
     * @param enName      英文名
     * @param zhName      中文名
     * @param secretLevel 密级
     * @param pages       页码
     * @param rows        每页容量
     * @return
     */
    APIResult<ListData<PostCategory>> queryPostCategoryList(String enName, String zhName,
                                                            SecretLevelEnum secretLevel, int pages, int rows);

    /**
     * 创建内容分类
     *
     * @param postCategory 内容分类
     * @return
     */
    APIResult<PostCategory> createPostCategory(PostCategory postCategory);

    /**
     * 修改内容分类
     *
     * @param categoryId   内容分类ID
     * @param postCategory 内容分类
     * @return
     */
    APIResult<PostCategory> updatePostCategory(long categoryId, PostCategory postCategory);

    /**
     * 删除内容分类
     *
     * @param categoryId 内容分类ID
     * @return
     */
    APIResult deletePostCategory(long categoryId);
}
