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
package net.renfei.common.core.service.impl;

import net.renfei.common.api.constant.APIResult;
import net.renfei.common.api.constant.enums.StateCodeEnum;
import net.renfei.common.core.config.SystemConfig;
import net.renfei.common.core.entity.SiteFriendlyLinkVo;
import net.renfei.common.core.repositories.CoreSiteFriendlyLinkMapper;
import net.renfei.common.core.repositories.entity.CoreSiteFriendlyLinkExample;
import net.renfei.common.core.repositories.entity.CoreSiteFriendlyLinkWithBLOBs;
import net.renfei.common.core.service.RedisService;
import net.renfei.common.core.service.SiteFriendlyLinkService;
import org.springframework.stereotype.Service;
import org.springframework.util.ObjectUtils;

import java.util.ArrayList;
import java.util.Date;
import java.util.List;

import static net.renfei.common.core.config.RedisConfig.REDIS_KEY_DATABASE;

/**
 * 友情链接服务
 *
 * @author renfei
 */
@Service
public class SiteFriendlyLinkServiceImpl implements SiteFriendlyLinkService {
    private final static String REDIS_KEY = REDIS_KEY_DATABASE + ":friendlylink";
    private final RedisService redisService;
    private final SystemConfig systemConfig;
    private final CoreSiteFriendlyLinkMapper coreSiteFriendlyLinkMapper;

    public SiteFriendlyLinkServiceImpl(RedisService redisService,
                                       SystemConfig systemConfig,
                                       CoreSiteFriendlyLinkMapper coreSiteFriendlyLinkMapper) {
        this.redisService = redisService;
        this.systemConfig = systemConfig;
        this.coreSiteFriendlyLinkMapper = coreSiteFriendlyLinkMapper;
    }

    @Override
    public List<SiteFriendlyLinkVo> queryFriendlyLink() {
        List<SiteFriendlyLinkVo> siteFriendlyLinkVos = null;
        if (systemConfig.getEnableCache()) {
            // 启用了缓存
            if (redisService.hasKey(REDIS_KEY)) {
                Object object = redisService.get(REDIS_KEY);
                if (object instanceof List) {
                    siteFriendlyLinkVos = (List<SiteFriendlyLinkVo>) object;
                }
            }
        }
        if (siteFriendlyLinkVos == null) {
            siteFriendlyLinkVos = new ArrayList<>();
            CoreSiteFriendlyLinkExample example = new CoreSiteFriendlyLinkExample();
            example.setOrderByClause("order_id ASC");
            example.createCriteria()
                    .andIsDeleteEqualTo(false)
                    .andAuditPassEqualTo(true)
                    .andLinkTypeEqualTo(1);
            List<CoreSiteFriendlyLinkWithBLOBs> siteFriendlyLinks = coreSiteFriendlyLinkMapper.selectByExampleWithBLOBs(example);
            if (!siteFriendlyLinks.isEmpty()) {
                for (CoreSiteFriendlyLinkWithBLOBs coreSiteFriendlyLink : siteFriendlyLinks
                ) {
                    SiteFriendlyLinkVo siteFriendlyLinkVo = new SiteFriendlyLinkVo();
                    siteFriendlyLinkVo.setText(coreSiteFriendlyLink.getSitename());
                    siteFriendlyLinkVo.setLink(coreSiteFriendlyLink.getSitelink());
                    siteFriendlyLinkVos.add(siteFriendlyLinkVo);
                }
            }
            if (systemConfig.getEnableCache()) {
                redisService.set(REDIS_KEY, siteFriendlyLinkVos);
            }
        }
        return siteFriendlyLinkVos;
    }

    @Override
    public List<CoreSiteFriendlyLinkWithBLOBs> queryAllFriendlyLink() {
        CoreSiteFriendlyLinkExample example = new CoreSiteFriendlyLinkExample();
        example.setOrderByClause("order_id ASC");
        example.createCriteria().andIsDeleteEqualTo(false);
        return coreSiteFriendlyLinkMapper.selectByExampleWithBLOBs(example);
    }

    public APIResult createFriendlyLink(CoreSiteFriendlyLinkWithBLOBs friendlyLink) {
        if (ObjectUtils.isEmpty(friendlyLink.getSitename())) {
            return APIResult.builder()
                    .code(StateCodeEnum.Failure)
                    .message("网站名称不能为空")
                    .build();
        }
        if (ObjectUtils.isEmpty(friendlyLink.getSitelink())) {
            return APIResult.builder()
                    .code(StateCodeEnum.Failure)
                    .message("网站链接不能为空")
                    .build();
        }
        if (ObjectUtils.isEmpty(friendlyLink.getAuditPass())) {
            friendlyLink.setAuditPass(true);
        }
        if (ObjectUtils.isEmpty(friendlyLink.getLinkType())) {
            friendlyLink.setLinkType(1);
        }
        if (ObjectUtils.isEmpty(friendlyLink.getOrderId())) {
            friendlyLink.setOrderId(0);
        }
        friendlyLink.setId(null);
        friendlyLink.setAddtime(new Date());
        friendlyLink.setIsDelete(false);
        coreSiteFriendlyLinkMapper.insertSelective(friendlyLink);
        if (systemConfig.getEnableCache()) {
            // 启用了缓存，清空缓存
            redisService.del(REDIS_KEY);
        }
        return APIResult.success();
    }

    @Override
    public APIResult updateFriendlyLink(CoreSiteFriendlyLinkWithBLOBs friendlyLink) {
        if (ObjectUtils.isEmpty(friendlyLink.getId())) {
            return APIResult.builder()
                    .code(StateCodeEnum.Failure)
                    .message("数据ID不能为空")
                    .build();
        }
        CoreSiteFriendlyLinkWithBLOBs old = coreSiteFriendlyLinkMapper.selectByPrimaryKey(friendlyLink.getId());
        if (old != null) {
            if (ObjectUtils.isEmpty(friendlyLink.getSitename())) {
                return APIResult.builder()
                        .code(StateCodeEnum.Failure)
                        .message("网站名称不能为空")
                        .build();
            }
            if (ObjectUtils.isEmpty(friendlyLink.getSitelink())) {
                return APIResult.builder()
                        .code(StateCodeEnum.Failure)
                        .message("网站链接不能为空")
                        .build();
            }
            if (ObjectUtils.isEmpty(friendlyLink.getAuditPass())) {
                friendlyLink.setAuditPass(true);
            }
            if (ObjectUtils.isEmpty(friendlyLink.getLinkType())) {
                friendlyLink.setLinkType(1);
            }
            if (ObjectUtils.isEmpty(friendlyLink.getOrderId())) {
                friendlyLink.setOrderId(0);
            }
            old.setSitename(friendlyLink.getSitename());
            old.setSitelink(friendlyLink.getSitelink());
            old.setAuditPass(friendlyLink.getAuditPass());
            old.setAddtime(friendlyLink.getAddtime());
            old.setLinkType(friendlyLink.getLinkType());
            old.setInSiteLink(friendlyLink.getInSiteLink());
            old.setContactName(friendlyLink.getContactName());
            old.setContactEmail(friendlyLink.getContactEmail());
            old.setContactQq(friendlyLink.getContactQq());
            old.setRemarks(friendlyLink.getRemarks());
            old.setOrderId(friendlyLink.getOrderId());
            coreSiteFriendlyLinkMapper.updateByPrimaryKeyWithBLOBs(old);
        }
        if (systemConfig.getEnableCache()) {
            // 启用了缓存，清空缓存
            redisService.del(REDIS_KEY);
        }
        return APIResult.success();
    }

    @Override
    public APIResult deleteFriendlyLink(long id) {
        CoreSiteFriendlyLinkWithBLOBs old = coreSiteFriendlyLinkMapper.selectByPrimaryKey(id);
        if (old != null) {
            old.setIsDelete(true);
            coreSiteFriendlyLinkMapper.updateByPrimaryKeyWithBLOBs(old);
        }
        if (systemConfig.getEnableCache()) {
            // 启用了缓存，清空缓存
            redisService.del(REDIS_KEY);
        }
        return APIResult.success();
    }
}
