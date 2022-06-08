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
import net.renfei.common.core.config.RedisConfig;
import net.renfei.common.core.config.SystemConfig;
import net.renfei.common.core.entity.UserDetail;
import net.renfei.common.core.service.RedisService;
import net.renfei.common.core.utils.AESUtils;
import net.renfei.common.core.utils.IpUtils;
import net.renfei.proprietary.discuz.service.DiscuzService;
import net.renfei.uaa.api.AuthorizationService;
import net.renfei.uaa.api.JwtService;
import net.renfei.uaa.api.UserService;
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

import javax.servlet.http.HttpServletRequest;
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
    public final static String REDIS_TOKEN_KEY = RedisConfig.REDIS_KEY_DATABASE + ":token:";
    private final static Logger logger = LoggerFactory.getLogger(AuthorizationServiceImpl.class);
    private final JwtService jwtService;
    private final UserService userService;
    private final SystemConfig systemConfig;
    private final RedisService redisService;
    private final DiscuzService discuzService;
    private final UaaSecretKeyMapper uaaSecretKeyMapper;

    public AuthorizationServiceImpl(JwtService jwtService,
                                    UserService userService,
                                    SystemConfig systemConfig,
                                    RedisService redisService,
                                    DiscuzService discuzService,
                                    UaaSecretKeyMapper uaaSecretKeyMapper) {
        this.jwtService = jwtService;
        this.userService = userService;
        this.systemConfig = systemConfig;
        this.redisService = redisService;
        this.discuzService = discuzService;
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
     * 根据秘钥ID解密AES密文
     *
     * @param string 密文
     * @param keyId  秘钥ID
     * @return 明文
     */
    @Override
    public APIResult<String> decryptAesByKeyId(String string, String keyId) {
        UaaSecretKeyExample example = new UaaSecretKeyExample();
        example.createCriteria().andUuidEqualTo(keyId);
        UaaSecretKeyWithBLOBs uaaSecretKey = ListUtils.getOne(uaaSecretKeyMapper.selectByExampleWithBLOBs(example));
        if (uaaSecretKey == null) {
            throw new BusinessException("AESKeyId不存在");
        }
        try {
            string = AESUtils.decrypt(string, uaaSecretKey.getPrivateKey());
        } catch (Exception ex) {
            logger.error(ex.getMessage(), ex);
            throw new BusinessException("加密密文解密失败");
        }
        return new APIResult<>(string);
    }

    /**
     * 登录
     *
     * @param signIn 登录请求对象
     * @return 登录响应
     */
    @Override
    public APIResult<SignInVo> signIn(SignInAo signIn, HttpServletRequest request) {
        signIn.setUserName(signIn.getUserName().trim().toLowerCase());
        signIn.setPassword(this.decryptAesByKeyId(
                signIn.getPassword(), signIn.getKeyUuid()
        ).getData());
        UserDetail userDetail = userService.signIn(signIn, request).getData();
        String token = jwtService.createJWT(userDetail.getUsername(), IpUtils.getIpAddress(request)).getData();
        // 将此用户的其他token从redis中删除
        redisService.del(REDIS_TOKEN_KEY + userDetail.getUsername());
        redisService.set(REDIS_TOKEN_KEY + userDetail.getUsername(), token);
        redisService.expire(REDIS_TOKEN_KEY + userDetail.getUsername(), systemConfig.getJwt().getExpiration() / 1000);
        SignInVo signInVo = new SignInVo();
        signInVo.setAccessToken(token);
        if (systemConfig.getUCenter().getEnable()) {
            signInVo.setUcScript(discuzService.uCenterSynLogin(userDetail.getUsername()));
        }
        return new APIResult<>(signInVo);
    }
}
