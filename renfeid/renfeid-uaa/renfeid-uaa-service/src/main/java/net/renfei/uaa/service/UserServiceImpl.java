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
package net.renfei.uaa.service;

import net.renfei.common.api.constant.APIResult;
import net.renfei.common.api.utils.ListUtils;
import net.renfei.uaa.api.JwtService;
import net.renfei.uaa.api.UserService;
import net.renfei.uaa.api.entity.UserDetail;
import net.renfei.uaa.repositories.UaaUserMapper;
import net.renfei.uaa.repositories.entity.UaaUser;
import net.renfei.uaa.repositories.entity.UaaUserExample;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Service;

/**
 * 用户服务
 *
 * @author renfei
 */
@Service
public class UserServiceImpl implements UserService {
    private final static Logger logger = LoggerFactory.getLogger(UserServiceImpl.class);
    private final JwtService jwtService;
    private final UaaUserMapper uaaUserMapper;

    public UserServiceImpl(JwtService jwtService,
                           UaaUserMapper uaaUserMapper) {
        this.jwtService = jwtService;
        this.uaaUserMapper = uaaUserMapper;
    }

    @Override
    public APIResult<UserDetail> getUserDetailByToken(String token) {
        if (jwtService.validate(token).getCode() != 200) {
            // JWT 校验失败
            logger.warn("Token校验失败：{}", token);
            throw new RuntimeException("Token校验失败。");
        }
        String username = jwtService.getUsername(token).getData();
        logger.info(username);
        // 根据 username 获取 UserDetail
        UaaUserExample example = new UaaUserExample();
        example.createCriteria()
                .andUsernameEqualTo(username);
        UaaUser uaaUser = ListUtils.getOne(uaaUserMapper.selectByExample(example));
        if (uaaUser == null) {
            logger.error("根据用户名：{}，未找到用户信息", username);
            throw new RuntimeException("未找到用户信息。");
        }
        return new APIResult<>(convert(uaaUser));
    }

    @Override
    public APIResult<UserDetail> getUserDetailByToken(String token, String ip) {
        if (jwtService.validate(token, ip).getCode() != 200) {
            // JWT 校验失败
            logger.warn("Token校验失败：{}", token);
            throw new RuntimeException("Token校验失败。");
        }
        String username = jwtService.getUsername(token).getData();
        logger.info(username);
        // 根据 username 获取 UserDetail
        UaaUserExample example = new UaaUserExample();
        example.createCriteria()
                .andUsernameEqualTo(username);
        UaaUser uaaUser = ListUtils.getOne(uaaUserMapper.selectByExample(example));
        if (uaaUser == null) {
            logger.error("根据用户名：{}，未找到用户信息", username);
            throw new RuntimeException("未找到用户信息。");
        }
        return new APIResult<>(convert(uaaUser));
    }

    private UserDetail convert(UaaUser uaaUser) {
        if (uaaUser == null) {
            return null;
        }
        UserDetail userDetail = new UserDetail();
        userDetail.setId(uaaUser.getId());
        userDetail.setUuid(uaaUser.getUuid());
        userDetail.setUsername(uaaUser.getUsername());
        userDetail.setEmail(uaaUser.getEmail());
        userDetail.setEmailVerified(uaaUser.getEmailVerified());
        userDetail.setPhone(uaaUser.getPhone());
        userDetail.setPhoneVerified(uaaUser.getPhoneVerified());
        userDetail.setRegistrationDate(uaaUser.getRegistrationDate());
        userDetail.setPassword(uaaUser.getPassword());
        userDetail.setTotp(uaaUser.getTotp());
        userDetail.setRegistrationIp(uaaUser.getRegistrationIp());
        userDetail.setTrialErrorTimes(uaaUser.getTrialErrorTimes());
        userDetail.setLockTime(uaaUser.getLockTime());
        userDetail.setSecretLevel(uaaUser.getSecretLevel());
        userDetail.setBuiltInUser(uaaUser.getBuiltInUser());
        userDetail.setPasswordExpirationTime(uaaUser.getPasswordExpirationTime());
        userDetail.setLocked(uaaUser.getLocked());
        userDetail.setEnabled(uaaUser.getEnabled());
        userDetail.setLastName(uaaUser.getLastName());
        userDetail.setFirstName(uaaUser.getFirstName());
        return userDetail;
    }
}
