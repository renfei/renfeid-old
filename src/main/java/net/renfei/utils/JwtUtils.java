package net.renfei.utils;

import io.jsonwebtoken.Claims;
import io.jsonwebtoken.JwtBuilder;
import io.jsonwebtoken.Jwts;
import io.jsonwebtoken.SignatureAlgorithm;
import net.renfei.config.SystemConfig;
import org.springframework.stereotype.Service;

import javax.crypto.SecretKey;
import javax.crypto.spec.SecretKeySpec;
import java.util.*;

/**
 * JWT 工具类
 *
 * @author RenFei(i @ renfei.net)
 */
@Service
public class JwtUtils {
    private final SystemConfig SYSTEM_CONFIG;

    public JwtUtils(SystemConfig systemConfig) {
        this.SYSTEM_CONFIG = systemConfig;
    }

    /**
     * 签发 JWT
     *
     * @param userId      用户UUID
     * @param authorities 权限列表
     * @param roles       角色列表
     * @param subject     可以是 JSON 数据，尽可能少
     * @return
     */
    public String createJWT(String userId, List<String> roles, List<String> authorities, String subject) {
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
                .setSubject(subject)
                // 签发时间
                .setIssuedAt(now)
                // 签发者
                .setIssuer(SYSTEM_CONFIG.getJwt().getIssuer());

        // payload 中 放入自定义信息
        Map<String, Object> selfMap = new HashMap<>(3);
        selfMap.put("user_id", userId);
        selfMap.put("roles", roles);
        selfMap.put("authorities", authorities);
        claims.putAll(selfMap);
        // header
        JwtBuilder builder = Jwts.builder().setHeader(map)
                // 使用 JSON 实例设置 payload
                .setClaims(claims)
                // 签名算法以及密钥
                .signWith(secretKey, algorithm);
        Date expDate = new Date(System.currentTimeMillis() + SYSTEM_CONFIG.getJwt().getExpiration());
        // 过期时间
        builder.setExpiration(expDate);
        return builder.compact();
    }

    private SecretKey generalKey() {
        byte[] encodedKey = Base64.getDecoder().decode(SYSTEM_CONFIG.getJwt().getSecret());
        return new SecretKeySpec(encodedKey, 0, encodedKey.length, "HmacSHA256");
    }

    /**
     * 解析JWT字符串
     *
     * @param jwt
     * @return
     * @throws Exception
     */
    public Claims parseJWT(String jwt) {
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
        return claims;
    }
}
