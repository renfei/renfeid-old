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
package net.renfei.server.controller.dash.core;

import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.Parameter;
import io.swagger.v3.oas.annotations.tags.Tag;
import net.renfei.common.api.constant.APIResult;
import net.renfei.common.core.annotation.OperationLog;
import net.renfei.common.core.entity.EnvironmentInfo;
import net.renfei.common.core.entity.OperationTypeEnum;
import net.renfei.common.core.entity.SystemTypeEnum;
import net.renfei.common.core.entity.UploadObjectVo;
import net.renfei.common.core.service.SystemService;
import net.renfei.server.controller.AbstractController;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.multipart.MultipartFile;

/**
 * 系统相关接口
 *
 * @author renfei
 */
@RestController
@RequestMapping("/_/api/core/system")
@Tag(name = "系统相关接口", description = "系统相关接口")
public class SystemController extends AbstractController {
    private final SystemService systemService;

    public SystemController(SystemService systemService) {
        this.systemService = systemService;
    }

    @GetMapping("refreshConfiguration")
    @Operation(summary = "刷新系统配置", tags = {"系统相关接口"})
    @OperationLog(module = SystemTypeEnum.SYSTEM, desc = "刷新系统配置", operation = OperationTypeEnum.UPDATE)
    public APIResult refreshConfiguration() {
        systemService.refreshConfiguration();
        return APIResult.success();
    }

    @GetMapping("environment")
    @Operation(summary = "获取运行环境信息", tags = {"系统相关接口"})
    @OperationLog(module = SystemTypeEnum.SYSTEM, desc = "获取运行环境信息", operation = OperationTypeEnum.RETRIEVE)
    public APIResult<EnvironmentInfo> getEnvironmentInfo() {
        return new APIResult<>(new EnvironmentInfo());
    }

    @PostMapping("upload")
    @Operation(summary = "上传文件", tags = {"系统相关接口"},
            description = "此接口上传的文件将立即公开访问",
            parameters = {
                    @Parameter(name = "file", description = "文件")
            })
    @OperationLog(module = SystemTypeEnum.SYSTEM, desc = "上传文件", operation = OperationTypeEnum.CREATE)
    public APIResult<UploadObjectVo> uploadObject(MultipartFile file) {
        return new APIResult<>(systemService.uploadObject(file));
    }
}
