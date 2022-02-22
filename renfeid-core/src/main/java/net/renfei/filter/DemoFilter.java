package net.renfei.filter;

import net.renfei.config.SystemConfig;
import net.renfei.model.APIResult;
import net.renfei.model.StateCodeEnum;
import net.renfei.utils.JacksonUtil;
import org.springframework.stereotype.Component;

import javax.servlet.*;
import javax.servlet.http.HttpServletRequest;
import java.io.IOException;
import java.io.PrintWriter;
import java.util.ArrayList;
import java.util.List;

/**
 * 演示模式过滤器
 *
 * @author renfei
 */
@Component
public class DemoFilter implements Filter {
    private final SystemConfig systemConfig;
    private static final String DEMO_ACTIVE = "demo";
    private static final String POST_METHOD = "post";
    private static final String PUT_METHOD = "put";
    private static final String DELETE_METHOD = "delete";
    private static final List<String> IGNORE_LIST = new ArrayList<String>() {{
        // 登陆注册等接口允许提交
        this.add("/-/api/auth/signIn");
        this.add("/-/api/auth/signUp");
    }};

    public DemoFilter(SystemConfig systemConfig) {
        this.systemConfig = systemConfig;
    }

    @Override
    public void init(FilterConfig filterConfig) throws ServletException {
        Filter.super.init(filterConfig);
    }

    @Override
    public void doFilter(ServletRequest servletRequest, ServletResponse servletResponse, FilterChain filterChain) throws IOException, ServletException {
        if (DEMO_ACTIVE.equals(systemConfig.getActive())) {
            HttpServletRequest request = (HttpServletRequest) servletRequest;
            String requestMethod = request.getMethod().toLowerCase();
            // 演示模式下，只允许 GET、OPTIONS 请求，其他操作进行拦截
            if (POST_METHOD.equals(requestMethod)
                    || PUT_METHOD.equals(requestMethod)
                    || DELETE_METHOD.equals(requestMethod)) {
                // 查询忽略列表，有些接口允许操作，例如登陆
                String url = request.getServletPath();
                if (!IGNORE_LIST.contains(url)) {
                    sendResult(APIResult.builder()
                            .code(StateCodeEnum.Forbidden)
                            .message("当前系统运行在演示模式下，只允许查看操作，不允许新增、修改与删除操作。")
                            .build(), servletResponse);
                    return;
                }
            }
        }
        filterChain.doFilter(servletRequest, servletResponse);
    }

    @Override
    public void destroy() {
        Filter.super.destroy();
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
