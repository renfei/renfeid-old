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

import com.github.pagehelper.Page;
import com.github.pagehelper.PageHelper;
import net.renfei.cms.api.PostCategoryService;
import net.renfei.cms.api.constant.enums.PostStatusEnum;
import net.renfei.cms.api.entity.PostCategory;
import net.renfei.cms.repositories.CmsCategoryMapper;
import net.renfei.cms.repositories.CmsPostsMapper;
import net.renfei.cms.repositories.entity.CmsCategory;
import net.renfei.cms.repositories.entity.CmsCategoryExample;
import net.renfei.cms.repositories.entity.CmsPostsExample;
import net.renfei.common.api.constant.APIResult;
import net.renfei.common.api.constant.enums.SecretLevelEnum;
import net.renfei.common.api.entity.ListData;
import net.renfei.common.api.exception.BusinessException;
import net.renfei.common.api.exception.OutOfSecretLevelException;
import net.renfei.common.core.config.SystemConfig;
import net.renfei.common.core.service.SnowflakeService;
import net.renfei.common.core.service.SystemService;
import net.renfei.uaa.api.entity.UserDetail;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;

/**
 * 文章分类服务
 *
 * @author renfei
 */
@Service
public class PostCategoryServiceImpl implements PostCategoryService {
    private final SystemConfig systemConfig;
    private final SystemService systemService;
    private final SnowflakeService snowflakeService;
    private final CmsPostsMapper cmsPostsMapper;
    private final CmsCategoryMapper cmsCategoryMapper;

    public PostCategoryServiceImpl(SystemConfig systemConfig,
                                   SystemService systemService,
                                   SnowflakeService snowflakeService,
                                   CmsPostsMapper cmsPostsMapper,
                                   CmsCategoryMapper cmsCategoryMapper) {
        this.systemConfig = systemConfig;
        this.systemService = systemService;
        this.snowflakeService = snowflakeService;
        this.cmsPostsMapper = cmsPostsMapper;
        this.cmsCategoryMapper = cmsCategoryMapper;
    }

    @Override
    public APIResult<ListData<PostCategory>> queryPostCategoryList(String enName, String zhName,
                                                                   SecretLevelEnum secretLevel, int pages, int rows) {
        UserDetail userDetail = systemService.currentUserDetail();
        if (userDetail != null) {
            if (secretLevel != null && SecretLevelEnum.outOfSecretLevel(userDetail.getSecretLevel(), secretLevel)) {
                throw new OutOfSecretLevelException("您查询的数据密级大于账号所属的密级，请求被拒绝");
            } else {
                secretLevel = userDetail.getSecretLevel();
            }
        } else {
            secretLevel = SecretLevelEnum.UNCLASSIFIED;
        }
        CmsCategoryExample example = new CmsCategoryExample();
        CmsCategoryExample.Criteria criteria = example.createCriteria();
        if (enName != null && !enName.isEmpty()) {
            criteria.andEnNameLike("%" + enName + "%");
        }
        if (zhName != null && !zhName.isEmpty()) {
            criteria.andZhNameLike("%" + zhName + "%");
        }
        if (secretLevel != null) {
            criteria.andSecretLevelLessThanOrEqualTo(secretLevel.getLevel());
        }
        ListData<PostCategory> postCategoryListData = new ListData<>();
        try (Page<CmsCategory> page = PageHelper.startPage(pages, rows)) {
            cmsCategoryMapper.selectByExample(example);
            postCategoryListData.setPageNum(page.getPageNum());
            postCategoryListData.setPageSize(page.getPageSize());
            postCategoryListData.setStartRow(page.getStartRow());
            postCategoryListData.setEndRow(page.getEndRow());
            postCategoryListData.setTotal(page.getTotal());
            postCategoryListData.setPages(page.getPages());
            List<PostCategory> postCategoryList = new ArrayList<>();
            for (CmsCategory cmsCategory : page.getResult()
            ) {
                postCategoryList.add(convert(cmsCategory));
            }
            postCategoryListData.setData(postCategoryList);
        }
        return new APIResult<>(postCategoryListData);
    }

    @Override
    public APIResult<PostCategory> createPostCategory(PostCategory postCategory) {
        if (postCategory.getEnName() == null || postCategory.getEnName().isEmpty()) {
            throw new BusinessException("内容分类的EnName不能为空");
        }
        if (postCategory.getZhName() == null || postCategory.getZhName().isEmpty()) {
            throw new BusinessException("内容分类的ZhName不能为空");
        }
        if (postCategory.getSecretLevel() == null) {
            throw new BusinessException("内容分类的密级不能为空");
        }
        if (SecretLevelEnum.outOfSecretLevel(systemConfig.getMaxSecretLevel(), postCategory.getSecretLevel())) {
            throw new OutOfSecretLevelException("内容分类的密级超过系统允许的最大密级，请求被拒绝");
        }
        UserDetail userDetail = systemService.currentUserDetail();
        if (SecretLevelEnum.outOfSecretLevel(userDetail.getSecretLevel(), postCategory.getSecretLevel())) {
            throw new OutOfSecretLevelException("内容分类的密级超过您自身账号的密级，请求被拒绝");
        }
        CmsCategoryExample example = new CmsCategoryExample();
        example.createCriteria().andEnNameEqualTo(postCategory.getEnName());
        if (!cmsCategoryMapper.selectByExample(example).isEmpty()) {
            throw new BusinessException("内容分类的EnName已经存在，不允许重复，请更换一个重试");
        }
        CmsCategory cmsCategory = convert(postCategory);
        cmsCategory.setId(snowflakeService.getId("").getId());
        cmsCategoryMapper.insertSelective(cmsCategory);
        return new APIResult<>(convert(cmsCategory));
    }

    @Override
    public APIResult<PostCategory> updatePostCategory(long categoryId, PostCategory postCategory) {
        if (postCategory.getEnName() == null || postCategory.getEnName().isEmpty()) {
            throw new BusinessException("内容分类的EnName不能为空");
        }
        if (postCategory.getZhName() == null || postCategory.getZhName().isEmpty()) {
            throw new BusinessException("内容分类的ZhName不能为空");
        }
        if (postCategory.getSecretLevel() == null) {
            throw new BusinessException("内容分类的密级不能为空");
        }
        CmsCategory oldCmsCategory = cmsCategoryMapper.selectByPrimaryKey(categoryId);
        if (oldCmsCategory == null) {
            throw new BusinessException("根据内容分类ID未找到内容分类数据，请查证");
        }
        UserDetail userDetail = systemService.currentUserDetail();
        if (SecretLevelEnum.outOfSecretLevel(userDetail.getSecretLevel(), SecretLevelEnum.valueOf(oldCmsCategory.getSecretLevel()))) {
            throw new OutOfSecretLevelException("内容分类的密级超过您自身账号的密级，请求被拒绝");
        }
        if (!oldCmsCategory.getEnName().equals(postCategory.getEnName())) {
            // EnName 发生了改变，检查重复情况
            CmsCategoryExample example = new CmsCategoryExample();
            example.createCriteria().andEnNameEqualTo(postCategory.getEnName());
            if (!cmsCategoryMapper.selectByExample(example).isEmpty()) {
                throw new BusinessException("内容分类的EnName已经存在，不允许重复，请更换一个重试");
            }
        }
        if (!oldCmsCategory.getSecretLevel().equals(postCategory.getSecretLevel().getLevel())) {
            // 密级发生了变化
            if (postCategory.getSecretLevel().getLevel() > oldCmsCategory.getSecretLevel()) {
                // 密级升级，检查是否越级
                if (SecretLevelEnum.outOfSecretLevel(systemConfig.getMaxSecretLevel(), postCategory.getSecretLevel())) {
                    throw new OutOfSecretLevelException("内容分类的密级超过系统允许的最大密级，请求被拒绝");
                }
                if (SecretLevelEnum.outOfSecretLevel(userDetail.getSecretLevel(), postCategory.getSecretLevel())) {
                    throw new OutOfSecretLevelException("内容分类的密级超过您自身账号的密级，请求被拒绝");
                }
            } else {
                // 密级降级，检查分类下的文章是否存在越级
                CmsPostsExample example = new CmsPostsExample();
                example.createCriteria()
                        .andCategoryIdEqualTo(categoryId)
                        .andPostStatusNotEqualTo(PostStatusEnum.DELETED.toString())
                        .andSecretLevelGreaterThan(postCategory.getSecretLevel().getLevel());
                if (!cmsPostsMapper.selectByExample(example).isEmpty()) {
                    throw new OutOfSecretLevelException("内容分类下存在文章密级超过修改目标密级的情况，请先移除不符合密级要求的内容，请求被拒绝");
                }
            }
        }
        oldCmsCategory.setEnName(postCategory.getEnName());
        oldCmsCategory.setZhName(postCategory.getZhName());
        oldCmsCategory.setSecretLevel(postCategory.getSecretLevel().getLevel());
        cmsCategoryMapper.updateByPrimaryKey(oldCmsCategory);
        return new APIResult<>(convert(oldCmsCategory));
    }

    @Override
    public APIResult deletePostCategory(long categoryId) {
        CmsCategory oldCmsCategory = cmsCategoryMapper.selectByPrimaryKey(categoryId);
        if (oldCmsCategory == null) {
            throw new BusinessException("根据内容分类ID未找到内容分类数据，请查证");
        }
        UserDetail userDetail = systemService.currentUserDetail();
        if (SecretLevelEnum.outOfSecretLevel(userDetail.getSecretLevel(), SecretLevelEnum.valueOf(oldCmsCategory.getSecretLevel()))) {
            throw new OutOfSecretLevelException("内容分类的密级超过您自身账号的密级，请求被拒绝");
        }
        CmsPostsExample example = new CmsPostsExample();
        example.createCriteria()
                .andCategoryIdEqualTo(categoryId)
                .andPostStatusNotEqualTo(PostStatusEnum.DELETED.toString());
        if (!cmsPostsMapper.selectByExample(example).isEmpty()) {
            throw new BusinessException("内容分类下存在未删除的文章，请先移除分类下的内容，请求被拒绝");
        }
        cmsCategoryMapper.deleteByPrimaryKey(categoryId);
        return APIResult.success();
    }

    private PostCategory convert(CmsCategory cmsCategory) {
        PostCategory postCategory = new PostCategory();
        postCategory.setId(cmsCategory.getId());
        postCategory.setEnName(cmsCategory.getEnName());
        postCategory.setZhName(cmsCategory.getZhName());
        postCategory.setSecretLevel(SecretLevelEnum.valueOf(cmsCategory.getSecretLevel()));
        return postCategory;
    }

    private CmsCategory convert(PostCategory postCategory) {
        CmsCategory cmsCategory = new CmsCategory();
        cmsCategory.setId(postCategory.getId());
        cmsCategory.setEnName(postCategory.getEnName());
        cmsCategory.setZhName(postCategory.getZhName());
        cmsCategory.setSecretLevel(postCategory.getSecretLevel().getLevel());
        return cmsCategory;
    }
}
