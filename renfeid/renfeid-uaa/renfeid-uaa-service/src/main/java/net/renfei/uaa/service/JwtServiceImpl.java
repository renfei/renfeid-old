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

import io.jsonwebtoken.Claims;
import io.jsonwebtoken.JwtBuilder;
import io.jsonwebtoken.Jwts;
import io.jsonwebtoken.SignatureAlgorithm;
import net.renfei.common.api.constant.APIResult;
import net.renfei.common.api.constant.enums.StateCodeEnum;
import net.renfei.common.core.config.SystemConfig;
import net.renfei.uaa.api.JwtService;
import org.springframework.stereotype.Service;

import javax.crypto.SecretKey;
import javax.crypto.spec.SecretKeySpec;
import java.util.*;

/**
 * JSON Web Token Service
 *
 * @author renfei
 */
@Service
public class JwtServiceImpl implements JwtService {
    private final SystemConfig SYSTEM_CONFIG;

    public JwtServiceImpl(SystemConfig systemConfig) {
        SYSTEM_CONFIG = systemConfig;
    }

    /**
     * 签发 JWT
     *
     * @param userName 用户名
     * @param ip       请求IP地址
     * @return
     */
    @Override
    public APIResult<String> createJWT(String userName, String ip) {
        SignatureAlgorithm algorithm = SignatureAlgorithm.HS256;
        long nowMillis = System.currentTimeMillis();
        Date now = new Date(nowMillis);

        // header Map
        Map<String, Object> map = new HashMap<>();
        map.put("alg", "HS256");
        map.put("typ", "JWT");

        SecretKey secretKey = generalKey();

        Claims claims = Jwts.claims();
        // jti (JWT ID)，防止jwt被重新发送
        claims.setId(UUID.randomUUID().toString())
                // 主题
                .setSubject(userName)
                // 受众，跟IP绑定
                .setAudience(ip)
                // 签发时间
                .setIssuedAt(now)
                // 签发者
                .setIssuer(SYSTEM_CONFIG.getJwt().getIssuer());

        JwtBuilder builder = Jwts.builder().setHeader(map)
                // 使用 JSON 实例设置 payload
                .setClaims(claims)
                // 签名算法以及密钥
                .signWith(secretKey, algorithm);
        Date expDate = new Date(System.currentTimeMillis() + SYSTEM_CONFIG.getJwt().getExpiration());
        // 过期时间
        builder.setExpiration(expDate);
        return new APIResult<>(builder.compact());
    }

    /**
     * 解析JWT字符串
     *
     * @param jwt
     * @return
     * @throws Exception
     */
    @Override
    public APIResult<Claims> parseJWT(String jwt) {
        Claims claims = null;
        try {
            SecretKey secretKey = generalKey();
            claims = Jwts.parserBuilder()
                    .setSigningKey(secretKey)
                    .build()
                    .parseClaimsJws(jwt)
                    .getBody();
        } catch (Exception ignored) {
        }
        return new APIResult<>(claims);
    }

    /**
     * 获取用户名
     *
     * @param token
     * @return
     */
    @Override
    public APIResult<String> getUsername(String token) {
        Claims claims = parseJWT(token).getData();
        if (claims == null) {
            return APIResult.builder()
                    .code(StateCodeEnum.Failure)
                    .message(StateCodeEnum.Failure.getDescribe())
                    .build();
        } else {
            return new APIResult<>(claims.getSubject());
        }
    }

    /**
     * TOKEN 校验
     *
     * @param token
     * @return
     */
    @Override
    public APIResult validate(String token) {
        if (this.parseJWT(token) == null) {
            return APIResult.builder()
                    .code(StateCodeEnum.Failure)
                    .message(StateCodeEnum.Failure.getDescribe())
                    .build();
        }
        return APIResult.success();
    }

    /**
     * TOKEN 与IP进行绑定校验
     *
     * @param token
     * @param ip
     * @return
     */
    @Override
    public APIResult validate(String token, String ip) {
        if (ip == null || ip.isEmpty()) {
            return APIResult.builder()
                    .code(StateCodeEnum.Failure)
                    .message(StateCodeEnum.Failure.getDescribe())
                    .build();
        }
        Claims claims = this.parseJWT(token).getData();
        if (this.parseJWT(token) == null) {
            return APIResult.builder()
                    .code(StateCodeEnum.Failure)
                    .message(StateCodeEnum.Failure.getDescribe())
                    .build();
        }
        if (!ip.equals(claims.getAudience())) {
            return APIResult.builder()
                    .code(StateCodeEnum.Failure)
                    .message(StateCodeEnum.Failure.getDescribe())
                    .build();
        }
        return APIResult.success();
    }

    private SecretKey generalKey() {
        byte[] encodedKey = Base64.getDecoder().decode(SYSTEM_CONFIG.getJwt().getSecret());
        return new SecretKeySpec(encodedKey, 0, encodedKey.length, "HmacSHA256");
    }
}
