package net.renfei.filter;

import net.renfei.config.SystemConfig;
import net.renfei.domain.UserDomain;
import net.renfei.domain.user.User;
import net.renfei.model.system.UserDetail;
import net.renfei.utils.JwtTokenUtils;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.web.authentication.WebAuthenticationDetailsSource;
import org.springframework.stereotype.Component;
import org.springframework.util.ObjectUtils;
import org.springframework.web.filter.OncePerRequestFilter;

import javax.servlet.FilterChain;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
import java.io.IOException;

import static net.renfei.controllers.BaseController.SESSION_KEY;

/**
 * 用户身份认证过滤器
 *
 * @author renfei
 */
@Component
public class AuthorizationFilter extends OncePerRequestFilter {
    private final SystemConfig systemConfig;
    private final JwtTokenUtils jwtTokenUtil;
    private static final String HEADER_TOKEN_NAME = "Authorization";
    private static final String HEADER_TOKEN = "Bearer ";

    public AuthorizationFilter(SystemConfig systemConfig,
                               JwtTokenUtils jwtTokenUtil) {
        this.systemConfig = systemConfig;
        this.jwtTokenUtil = jwtTokenUtil;
    }

    @Override
    protected void doFilterInternal(HttpServletRequest request,
                                    HttpServletResponse response,
                                    FilterChain filterChain) throws ServletException, IOException {
        final String auth = request.getHeader(HEADER_TOKEN_NAME);
        UserDetail userDetails;
        if ("SESSION".equals(systemConfig.getAuthMode())) {
            HttpSession session = request.getSession();
            Object sessionObject = session.getAttribute(SESSION_KEY);
            if (sessionObject instanceof User) {
                userDetails = new UserDetail((User) sessionObject);
            } else {
                filterChain.doFilter(request, response);
                return;
            }
        } else {
            if (ObjectUtils.isEmpty(auth) || !auth.startsWith(HEADER_TOKEN)) {
                filterChain.doFilter(request, response);
                return;
            }
            final String token = auth.split(" ")[1].trim();
            if (!jwtTokenUtil.validate(token, request)) {
                filterChain.doFilter(request, response);
                return;
            }
            userDetails = new UserDetail(new UserDomain(jwtTokenUtil.getUsername(token)));
        }
        UsernamePasswordAuthenticationToken
                authentication = new UsernamePasswordAuthenticationToken(
                userDetails, null,
                userDetails.getAuthorities()
        );

        authentication.setDetails(
                new WebAuthenticationDetailsSource().buildDetails(request)
        );

        SecurityContextHolder.getContext().setAuthentication(authentication);
        filterChain.doFilter(request, response);
    }
}
