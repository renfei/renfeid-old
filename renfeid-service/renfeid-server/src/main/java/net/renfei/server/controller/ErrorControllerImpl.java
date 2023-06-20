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
package net.renfei.server.controller;

import net.renfei.common.api.constant.APIResult;
import net.renfei.common.api.constant.enums.StateCodeEnum;
import org.springframework.boot.web.servlet.error.ErrorController;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import jakarta.servlet.http.HttpServletResponse;

/**
 * @author renfei
 */
@RestController
public class ErrorControllerImpl extends AbstractController implements ErrorController {
    /**
     * 由于404异常无法被全局异常处理，可通过此种方式进行处理404异常
     *
     * @return
     */
    @RequestMapping("/error")
    public APIResult error(HttpServletResponse response) {
        // 返回异常信息
        return APIResult.builder()
                .code(StateCodeEnum.valueOf(response.getStatus()))
                .message("Error.")
                .build();
    }
}
