package net.renfei.utils;

import io.jsonwebtoken.Claims;
import io.jsonwebtoken.JwtBuilder;
import io.jsonwebtoken.Jwts;
import io.jsonwebtoken.SignatureAlgorithm;
import net.renfei.config.SystemConfig;
import org.springframework.stereotype.Service;

import javax.crypto.SecretKey;
import javax.crypto.spec.SecretKeySpec;
import javax.servlet.http.HttpServletRequest;
import java.util.*;

/**
 * JWT 工具类
 *
 * @author RenFei(i @ renfei.net)
 */
@Service
public class JwtTokenUtils {
    private final SystemConfig SYSTEM_CONFIG;

    public JwtTokenUtils(SystemConfig systemConfig) {
        this.SYSTEM_CONFIG = systemConfig;
    }

    /**
     * 签发 JWT
     *
     * @param userName 用户名
     * @param request  请求对象
     * @return
     */
    public String createJWT(String userName, HttpServletRequest request) {
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
                .setAudience(IpUtils.getIpAddress(request))
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
        return builder.compact();
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

    /**
     * 获取用户名
     *
     * @param token
     * @return
     */
    public String getUsername(String token) {
        Claims claims = parseJWT(token);
        return claims == null ? null : claims.getSubject();
    }

    /**
     * TOKEN 与IP进行绑定校验
     *
     * @param token
     * @param request
     * @return
     */
    public boolean validate(String token, HttpServletRequest request) {
        Claims claims = this.parseJWT(token);
        if (this.parseJWT(token) == null) {
            return false;
        }
        return IpUtils.getIpAddress(request).equals(claims.getAudience());
    }

    private SecretKey generalKey() {
        byte[] encodedKey = Base64.getDecoder().decode(SYSTEM_CONFIG.getJwt().getSecret());
        return new SecretKeySpec(encodedKey, 0, encodedKey.length, "HmacSHA256");
    }
}
