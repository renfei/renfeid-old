package net.renfei.security.handler;

import com.fasterxml.jackson.databind.ObjectMapper;
import net.renfei.model.APIResult;
import net.renfei.model.StateCodeEnum;
import org.springframework.security.access.AccessDeniedException;
import org.springframework.security.web.access.AccessDeniedHandler;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.io.PrintWriter;

/**
 * 拒签（403响应）处理器
 * 此处我们可以自定义403响应的内容,让他返回我们的错误逻辑提示
 *
 * @author renfei
 */
public class AccessDeniedHandlerImpl implements AccessDeniedHandler {
    @Override
    public void handle(HttpServletRequest httpServletRequest, HttpServletResponse httpServletResponse,
                       AccessDeniedException e) throws IOException {
        httpServletResponse.setStatus(HttpServletResponse.SC_FORBIDDEN);
        httpServletResponse.setContentType("application/json;charset=UTF-8");
        PrintWriter out = httpServletResponse.getWriter();
        APIResult apiResult = APIResult.builder()
                .code(StateCodeEnum.Forbidden)
                .message(StateCodeEnum.Forbidden.getDescribe())
                .build();
        out.write(new ObjectMapper().writeValueAsString(apiResult));
        out.flush();
        out.close();
    }
}
