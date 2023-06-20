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
package net.renfei.common.core.config;

import net.renfei.common.api.constant.APIResult;
import net.renfei.common.api.constant.enums.StateCodeEnum;
import net.renfei.common.api.exception.*;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.security.access.AccessDeniedException;
import org.springframework.web.HttpRequestMethodNotSupportedException;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.RestControllerAdvice;

import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;

/**
 * 全局异常捕获处理
 *
 * @author renfei
 */
@RestControllerAdvice
public class GlobalExceptionHandler {
    private final static Logger logger = LoggerFactory.getLogger(GlobalExceptionHandler.class);

    @ExceptionHandler(value = BusinessException.class)
    public APIResult businessExceptionError(HttpServletRequest request, BusinessException e) {
        logger.info("Request'{}':{}", request.getRequestURI(), e.getMessage());
        return APIResult.builder()
                .code(StateCodeEnum.Failure)
                .message(e.getMessage())
                .build();
    }

    @ExceptionHandler(value = NotFoundException.class)
    public APIResult notFoundExceptionError(HttpServletRequest request,
                                            NotFoundException e) {
        logger.info("Request'{}':{}", request.getRequestURI(), e.getMessage());
        return APIResult.builder()
                .code(StateCodeEnum.NotFound)
                .message(e.getMessage())
                .build();
    }

    @ExceptionHandler(value = OutOfSecretLevelException.class)
    public APIResult outOfSecretLevelExceptionError(HttpServletRequest request,
                                                    HttpServletResponse response,
                                                    OutOfSecretLevelException e) {
        response.setStatus(403);
        logger.warn("Request'{}':{}", request.getRequestURI(), e.getMessage());
        return APIResult.builder()
                .code(StateCodeEnum.Forbidden)
                .message(e.getMessage())
                .build();
    }

    @ExceptionHandler(value = AccessDeniedException.class)
    public APIResult AccessDeniedExceptionError(HttpServletRequest request,
                                                    HttpServletResponse response,
                                                    OutOfSecretLevelException e) {
        response.setStatus(403);
        logger.warn("Request'{}':{}", request.getRequestURI(), e.getMessage());
        return APIResult.builder()
                .code(StateCodeEnum.Forbidden)
                .message(e.getMessage())
                .build();
    }

    @ExceptionHandler(value = NeedU2FException.class)
    public APIResult needU2FExceptionError(NeedU2FException e) {
        return APIResult.builder()
                .code(StateCodeEnum.NeedTOTP)
                .message(e.getMessage())
                .build();
    }

    @ExceptionHandler(value = HttpRequestMethodNotSupportedException.class)
    public APIResult httpRequestMethodNotSupportedExceptionError(HttpServletRequest request,
                                                                 HttpServletResponse response,
                                                                 HttpRequestMethodNotSupportedException e) {
        response.setStatus(405);
        logger.warn("Request'{}':{}", request.getRequestURI(), e.getMessage());
        return APIResult.builder()
                .code(StateCodeEnum.MethodNotAllowed)
                .message(e.getMessage())
                .build();
    }

    @ExceptionHandler(value = IP2LocationException.class)
    public APIResult ip2LocationExceptionError(HttpServletRequest request,
                                               HttpServletResponse response,
                                               IP2LocationException e) {
        response.setStatus(500);
        logger.error("Request'{}':{}", request.getRequestURI(), e.getMessage(), e);
        return APIResult.builder()
                .code(StateCodeEnum.Error)
                .message(e.getMessage())
                .build();
    }

    @ExceptionHandler(value = RuntimeException.class)
    public APIResult runtimeExceptionError(HttpServletRequest request,
                                           HttpServletResponse response,
                                           RuntimeException e) {
        response.setStatus(500);
        logger.error("Request'{}':{}", request.getRequestURI(), e.getMessage(), e);
        return APIResult.builder()
                .code(StateCodeEnum.Error)
                .message("服务器发生了内部错误，请稍后重试。")
                .build();
    }

    @ExceptionHandler(value = Exception.class)
    public APIResult exceptionError(HttpServletRequest request, HttpServletResponse response, Exception e) {
        response.setStatus(500);
        logger.error("Request'{}':{}", request.getRequestURI(), e.getMessage(), e);
        return APIResult.builder()
                .code(StateCodeEnum.Error)
                .message("服务器发生了内部错误，请稍后重试。")
                .build();
    }
}
