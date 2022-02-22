package net.renfei.filter;

import net.renfei.model.APIResult;
import net.renfei.model.StateCodeEnum;
import net.renfei.model.system.SystemOperationStatusEnum;
import net.renfei.services.SysService;
import net.renfei.utils.JacksonUtil;
import org.springframework.stereotype.Component;

import javax.servlet.*;
import javax.servlet.http.HttpServletRequest;
import java.io.IOException;
import java.io.PrintWriter;

/**
 * 系统运行状态过滤器
 *
 * @author renfei
 */
@Component
public class SystemOperationStatusFilter implements Filter {
    private static final String GET_METHOD = "get";
    private static final String HEAD_METHOD = "head";
    private final SysService sysService;

    public SystemOperationStatusFilter(SysService sysService) {
        this.sysService = sysService;
    }

    @Override
    public void doFilter(ServletRequest servletRequest, ServletResponse servletResponse, FilterChain filterChain) throws IOException, ServletException {
        SystemOperationStatusEnum systemOperationStatus = sysService.querySystemOperationStatus();
        if (SystemOperationStatusEnum.OPENED.equals(systemOperationStatus)) {
            //系统开放状态，不做拦截，全部跳过
            filterChain.doFilter(servletRequest, servletResponse);
            return;
        }
        HttpServletRequest request = (HttpServletRequest) servletRequest;
        String requestMethod = request.getMethod().toLowerCase();
        String url = request.getServletPath().toLowerCase();
        if (url.startsWith("/_/api/") || "/-/api/auth/signIn".equals(url)) {
            // 登陆接口和后台管理接口，不做拦截
            filterChain.doFilter(servletRequest, servletResponse);
            return;
        }
        if (SystemOperationStatusEnum.CLOSED.equals(systemOperationStatus)) {
            // 关闭状态，所有请求均被拦截
            sendResult(APIResult.builder()
                    .code(StateCodeEnum.Forbidden)
                    .message("当前系统已关闭，不允许任何请求操作。")
                    .build(), servletResponse);
            return;
        }
        if (SystemOperationStatusEnum.DEGRADED.equals(systemOperationStatus)) {
            // 降级运行，只接受 GET/HEAD 请求
            if (!GET_METHOD.equals(requestMethod)
                    && !HEAD_METHOD.equals(requestMethod)) {
                //只接受 GET/HEAD 请求
                sendResult(APIResult.builder()
                        .code(StateCodeEnum.Forbidden)
                        .message("当前系统运行在降级模式下，只允许查看操作（GET/HEAD），不允许其他请求操作。")
                        .build(), servletResponse);
                return;
            }
        }
        filterChain.doFilter(servletRequest, servletResponse);
    }

    private void sendResult(APIResult apiResult, ServletResponse response) {
        response.setCharacterEncoding("UTF-8");
        response.setContentType("application/json;charset=utf-8");
        PrintWriter out;
        try {
            out = response.getWriter();
            out.println(JacksonUtil.obj2String(apiResult));
            out.flush();
            out.close();
        } catch (IOException e) {
            e.printStackTrace();
        }
    }
}
