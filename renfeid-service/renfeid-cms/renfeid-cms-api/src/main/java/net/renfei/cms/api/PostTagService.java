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

import net.renfei.cms.api.entity.Tag;

import java.util.List;

/**
 * 内容标签服务
 *
 * @author renfei
 */
public interface PostTagService {
    /**
     * 查询所有标签
     *
     * @return
     */
    List<Tag> queryAllTag();

    /**
     * 查询内容关联的标签列表
     *
     * @param postId
     * @return
     */
    List<Tag> queryTagListByPostId(long postId);

    /**
     * 创建标签，有则返回无则创建
     *
     * @param tag
     * @return
     */
    Tag createTag(Tag tag);

    /**
     * 清除内容与标签的关系
     *
     * @param postId
     */
    void cleanPostTagByPostId(long postId);

    /**
     * 添加内容与标签的关系
     *
     * @param postId
     * @param tagId
     */
    void addPostTagByPostId(long postId, long tagId);
}
