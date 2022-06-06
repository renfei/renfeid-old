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
import net.renfei.common.api.exception.BusinessException;
import net.renfei.common.api.utils.ListUtils;
import net.renfei.common.api.utils.RSAUtils;
import net.renfei.common.api.utils.StringUtils;
import net.renfei.uaa.api.AuthorizationService;
import net.renfei.uaa.api.entity.SecretKey;
import net.renfei.uaa.api.entity.SignInAo;
import net.renfei.uaa.api.entity.SignInVo;
import net.renfei.uaa.repositories.UaaSecretKeyMapper;
import net.renfei.uaa.repositories.entity.UaaSecretKeyExample;
import net.renfei.uaa.repositories.entity.UaaSecretKeyWithBLOBs;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Service;
import org.springframework.util.ObjectUtils;

import java.util.Date;
import java.util.Map;
import java.util.UUID;

/**
 * 认证服务
 *
 * @author renfei
 */
@Service
public class AuthorizationServiceImpl implements AuthorizationService {
    private final static Logger logger = LoggerFactory.getLogger(AuthorizationServiceImpl.class);
    private final UaaSecretKeyMapper uaaSecretKeyMapper;

    public AuthorizationServiceImpl(UaaSecretKeyMapper uaaSecretKeyMapper) {
        this.uaaSecretKeyMapper = uaaSecretKeyMapper;
    }

    /**
     * 向服务器申请服务器公钥
     *
     * @return
     */
    @Override
    public APIResult<SecretKey> requestServerSecretKey() {
        Map<Integer, String> map = RSAUtils.genKeyPair(2048);
        if (ObjectUtils.isEmpty(map)) {
            logger.error("生成服务器公钥失败。");
            throw new RuntimeException("生成服务器公钥失败。");
        }
        String uuid = UUID.randomUUID().toString();
        SecretKey secretKey = new SecretKey();
        secretKey.setUuid(uuid);
        secretKey.setPublicKey(map.get(0));
        // 私钥不外传
        secretKey.setPrivateKey(null);
        // 保存数据库
        UaaSecretKeyWithBLOBs uaaSecretKey = new UaaSecretKeyWithBLOBs();
        uaaSecretKey.setPublicKey(map.get(0));
        uaaSecretKey.setPrivateKey(map.get(1));
        uaaSecretKey.setUuid(uuid);
        uaaSecretKey.setCreateTime(new Date());
        uaaSecretKeyMapper.insertSelective(uaaSecretKey);
        return new APIResult<>(secretKey);
    }

    /**
     * 上报客户端公钥，并下发AES秘钥
     *
     * @param secretKey 客户端加密后的秘钥
     * @return
     */
    @Override
    public APIResult<SecretKey> settingClientSecretKey(SecretKey secretKey) {
        if (secretKey == null || secretKey.getUuid() == null || secretKey.getUuid().isEmpty()) {
            throw new BusinessException("服务器端秘钥UUID错误，请查证后重试。");
        }
        UaaSecretKeyExample example = new UaaSecretKeyExample();
        example.createCriteria().andUuidEqualTo(secretKey.getUuid());
        UaaSecretKeyWithBLOBs uaaSecretKey = ListUtils.getOne(uaaSecretKeyMapper.selectByExampleWithBLOBs(example));
        if (uaaSecretKey == null) {
            throw new BusinessException("服务器端秘钥UUID错误，请查证后重试。");
        }
        String clientKey;
        try {
            clientKey = RSAUtils.decrypt(secretKey.getPublicKey(), secretKey.getPrivateKey());
        } catch (Exception ex) {
            logger.error(ex.getMessage());
            throw new BusinessException("客户端公钥解密失败。");
        }
        String aes = StringUtils.getRandomString(16);
        String aesEnc;
        try {
            aesEnc = RSAUtils.encrypt(aes, clientKey.replaceAll("\n", ""));
        } catch (Exception ex) {
            logger.error(ex.getMessage());
            throw new RuntimeException("服务器内部错误，使用RSA客户端公钥加密失败。");
        }
        //保存AES
        String uuid = UUID.randomUUID().toString();
        UaaSecretKeyWithBLOBs clientPrivateKey = new UaaSecretKeyWithBLOBs();
        clientPrivateKey.setUuid(uuid);
        clientPrivateKey.setCreateTime(new Date());
        clientPrivateKey.setPrivateKey(aes);
        uaaSecretKeyMapper.insertSelective(clientPrivateKey);
        SecretKey responseSecretKey = new SecretKey();
        responseSecretKey.setUuid(uuid);
        responseSecretKey.setPublicKey(aesEnc);
        responseSecretKey.setPrivateKey(aesEnc);
        return new APIResult<>(responseSecretKey);
    }

    /**
     * 登录
     *
     * @param signIn 登录请求对象
     * @return 登录响应
     */
    @Override
    public APIResult<SignInVo> signIn(SignInAo signIn) {
        // TODO
        return null;
    }
}
