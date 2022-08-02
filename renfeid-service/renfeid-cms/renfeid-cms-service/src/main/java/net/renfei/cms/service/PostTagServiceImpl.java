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
package net.renfei.cms.service;

import net.renfei.cms.api.PostTagService;
import net.renfei.cms.api.entity.Tag;
import net.renfei.cms.repositories.CmsPostsTagMapper;
import net.renfei.cms.repositories.CmsTagMapper;
import net.renfei.cms.repositories.entity.CmsPostsTag;
import net.renfei.cms.repositories.entity.CmsPostsTagExample;
import net.renfei.cms.repositories.entity.CmsTag;
import net.renfei.cms.repositories.entity.CmsTagExample;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;

/**
 * 内容标签服务
 *
 * @author renfei
 */
@Service
public class PostTagServiceImpl implements PostTagService {
    private final CmsTagMapper cmsTagMapper;
    private final CmsPostsTagMapper cmsPostsTagMapper;

    public PostTagServiceImpl(CmsTagMapper cmsTagMapper,
                              CmsPostsTagMapper cmsPostsTagMapper) {
        this.cmsTagMapper = cmsTagMapper;
        this.cmsPostsTagMapper = cmsPostsTagMapper;
    }

    @Override
    public List<Tag> queryAllTag() {
        CmsTagExample example = new CmsTagExample();
        List<CmsTag> cmsTags = cmsTagMapper.selectByExample(example);
        List<Tag> tags = new ArrayList<>();
        for (CmsTag cmsTag : cmsTags
        ) {
            Tag tag = new Tag();
            tag.setId(cmsTag.getId() + "");
            tag.setEnName(cmsTag.getEnName());
            tag.setZhName(cmsTag.getZhName());
            tags.add(tag);
        }
        return tags;
    }

    @Override
    public List<Tag> queryTagListByPostId(long postId) {
        CmsPostsTagExample example = new CmsPostsTagExample();
        example.createCriteria().andPostIdEqualTo(postId);
        List<CmsPostsTag> cmsPostsTags = cmsPostsTagMapper.selectByExample(example);
        if (cmsPostsTags.isEmpty()) {
            return null;
        }
        List<Long> ids = new ArrayList<>();
        cmsPostsTags.forEach(cmsPostsTag -> ids.add(cmsPostsTag.getTagId()));
        CmsTagExample cmsTagExample = new CmsTagExample();
        cmsTagExample.createCriteria().andIdIn(ids);
        List<CmsTag> cmsTags = cmsTagMapper.selectByExample(cmsTagExample);
        List<Tag> tags = new ArrayList<>();
        for (CmsTag cmsTag : cmsTags
        ) {
            Tag tag = new Tag();
            tag.setId(cmsTag.getId() + "");
            tag.setEnName(cmsTag.getEnName());
            tag.setZhName(cmsTag.getZhName());
            tags.add(tag);
        }
        return tags;
    }

    @Override
    public Tag createTag(Tag tag) {
        // 检查是否有重名的
        CmsTagExample example = new CmsTagExample();
        example.createCriteria().andEnNameEqualTo(tag.getEnName());
        List<CmsTag> cmsTags = cmsTagMapper.selectByExample(example);
        CmsTag cmsTag = new CmsTag();
        if (cmsTags.isEmpty()) {
            cmsTag.setId(null);
            cmsTag.setEnName(tag.getEnName());
            cmsTag.setZhName(tag.getZhName());
            cmsTagMapper.insertSelective(cmsTag);
        } else {
            cmsTag = cmsTags.get(0);
        }
        tag.setId(cmsTag.getId() + "");
        tag.setEnName(cmsTag.getEnName());
        tag.setZhName(cmsTag.getZhName());
        return tag;
    }

    @Override
    public void cleanPostTagByPostId(long postId) {
        CmsPostsTagExample example = new CmsPostsTagExample();
        example.createCriteria().andPostIdEqualTo(postId);
        cmsPostsTagMapper.deleteByExample(example);
    }

    @Override
    public void addPostTagByPostId(long postId, long tagId) {
        CmsPostsTag cmsPostsTag = new CmsPostsTag();
        cmsPostsTag.setPostId(postId);
        cmsPostsTag.setTagId(tagId);
        cmsPostsTagMapper.insertSelective(cmsPostsTag);
    }
}
