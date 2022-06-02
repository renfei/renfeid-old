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
import net.renfei.common.api.exception.BusinessException;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.RestControllerAdvice;

import javax.servlet.http.HttpServletRequest;

/**
 * 全局异常捕获处理
 *
 * @author renfei
 */
@RestControllerAdvice
public class GlobalExceptionHandler {
    private final static Logger logger = LoggerFactory.getLogger(GlobalExceptionHandler.class);

    @ExceptionHandler(value = BusinessException.class)
    public APIResult businessExceptionError(HttpServletRequest req, BusinessException e) {
        logger.warn("Message: {} \n Request: {}", e.getMessage(), req, e);
        return APIResult.builder()
                .code(StateCodeEnum.Failure)
                .message(e.getMessage())
                .build();
    }

    @ExceptionHandler(value = RuntimeException.class)
    public APIResult runtimeExceptionError(HttpServletRequest req, RuntimeException e) {
        logger.error("Request: {}", req, e);
        return APIResult.builder()
                .code(StateCodeEnum.Error)
                .message("服务器发生了内部错误，请稍后重试。")
                .build();
    }

    @ExceptionHandler(value = Exception.class)
    public APIResult exceptionError(HttpServletRequest req, Exception e) {
        logger.error("Request: {}", req, e);
        return APIResult.builder()
                .code(StateCodeEnum.Error)
                .message("服务器发生了内部错误，请稍后重试。")
                .build();
    }
}
