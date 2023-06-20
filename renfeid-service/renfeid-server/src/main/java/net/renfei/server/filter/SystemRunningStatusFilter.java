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
package net.renfei.server.filter;

import net.renfei.common.api.constant.APIResult;
import net.renfei.common.api.constant.enums.StateCodeEnum;
import net.renfei.common.api.constant.enums.SystemStatusEnum;
import net.renfei.common.core.service.SystemService;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.core.annotation.Order;
import org.springframework.stereotype.Component;

import jakarta.servlet.*;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

/**
 * 系统运行模式检查过滤器
 *
 * @author renfei
 */
@Order(1)
@Component
public class SystemRunningStatusFilter extends AbstractFilter implements Filter {
    private final static Logger logger = LoggerFactory.getLogger(SystemRunningStatusFilter.class);
    private final static List<String> ALLOWED_REQUEST_METHODS = new ArrayList<String>() {{
        this.add("OPTIONS");
        this.add("HEAD");
        this.add("GET");
    }};
    private final SystemService systemService;

    public SystemRunningStatusFilter(SystemService systemService) {
        this.systemService = systemService;
    }

    @Override
    public void doFilter(ServletRequest servletRequest, ServletResponse servletResponse, FilterChain filterChain) throws ServletException, IOException {
        if (servletRequest instanceof HttpServletRequest && servletResponse instanceof HttpServletResponse) {
            HttpServletRequest httpRequest = (HttpServletRequest) servletRequest;
            HttpServletResponse httpResponse = (HttpServletResponse) servletResponse;
            SystemStatusEnum systemStatusEnum = systemService.querySystemRunningStatus();
            String method = httpRequest.getMethod().toUpperCase();
            switch (systemStatusEnum) {
                case READONLY:
                    if (!ALLOWED_REQUEST_METHODS.contains(method)) {
                        APIResult result = APIResult.builder()
                                .code(StateCodeEnum.MethodNotAllowed)
                                .message("当前系统状态为半关闭[只读状态]，请联系系统管理员，启用完整系统。")
                                .build();
                        responseResult(logger, httpResponse, result);
                        return;
                    }
                    break;
                case CLOSED:
                    APIResult result = APIResult.builder()
                            .code(StateCodeEnum.SystemClosed)
                            .message("当前系统状态为关闭状态，请联系系统管理员，启用系统。")
                            .build();
                    responseResult(logger, httpResponse, result);
                    return;
                case OPENED:
                default:
                    break;
            }
        }
        filterChain.doFilter(servletRequest, servletResponse);
    }
}
