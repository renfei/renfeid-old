package net.renfei.utils;

import net.renfei.config.SystemConfig;
import net.renfei.domain.UserDomain;
import net.renfei.model.system.UserDetail;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Service;
import org.springframework.util.ObjectUtils;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;

/**
 * 从请求体中获取用户信息
 *
 * @author renfei
 */
@Service
public class UserDetailUtils {
    private static final Logger logger = LoggerFactory.getLogger(UserDetailUtils.class);
    private static final String HEADER_TOKEN_NAME = "Authorization";
    private static final String HEADER_TOKEN = "Bearer ";
    private final JwtTokenUtils jwtTokenUtil;

    public UserDetailUtils(JwtTokenUtils jwtTokenUtil) {
        this.jwtTokenUtil = jwtTokenUtil;
    }

    /**
     * 从请求体中获取用户信息
     * 优先从Session中获取，如果没有从请求头中Token获取
     *
     * @param request 请求对象
     * @return
     */
    public UserDetail getUserDetail(HttpServletRequest request) {
        final String auth = request.getHeader(HEADER_TOKEN_NAME);
        UserDetail userDetails;
        HttpSession session = request.getSession();
        Object sessionObject = session.getAttribute(SystemConfig.SESSION_KEY);
        if (sessionObject instanceof UserDetail) {
            // 优先从 Session 中取
            userDetails = (UserDetail) sessionObject;
        } else {
            // Session 中没取到，从 Head 中取
            if (ObjectUtils.isEmpty(auth) || !auth.startsWith(HEADER_TOKEN)) {
                // 请求头中没有token
                return null;
            }
            final String token = auth.split(" ")[1].trim();
            if (!jwtTokenUtil.validate(token, request)) {
                // JWT 校验失败
                logger.warn("JwtToken校验失败：{}", token);
                return null;
            }
            userDetails = new UserDetail(new UserDomain(jwtTokenUtil.getUsername(token)));
        }
        return userDetails;
    }
}
