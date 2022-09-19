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
package net.renfei.server.controller.dash.bpm;

import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.Parameter;
import io.swagger.v3.oas.annotations.tags.Tag;
import net.renfei.bpm.api.FlowInstanceService;
import net.renfei.common.api.constant.APIResult;
import net.renfei.common.core.annotation.OperationLog;
import net.renfei.common.core.entity.OperationTypeEnum;
import net.renfei.common.core.entity.SystemTypeEnum;
import net.renfei.server.controller.AbstractController;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.*;

/**
 * 工作流流程实例管理
 *
 * @author renfei
 */
@RestController
@RequestMapping("/_/api/flow/instance")
@Tag(name = "工作流流程实例管理", description = "工作流流程实例管理")
public class FlowInstanceDashController extends AbstractController {
    private final FlowInstanceService flowInstanceService;

    public FlowInstanceDashController(FlowInstanceService flowInstanceService) {
        this.flowInstanceService = flowInstanceService;
    }

    @PutMapping("activate/{instanceId}")
    @PreAuthorize("hasPermission('','flow:instance:activate')")
    @Operation(summary = "激活或挂起流程实例", tags = {"工作流流程实例管理"},
            parameters = {
                    @Parameter(name = "instanceId", description = "流程实例ID"),
                    @Parameter(name = "activate", description = "激活或挂起")
            })
    @OperationLog(module = SystemTypeEnum.FLOWABLE, desc = "激活或挂起流程实例", operation = OperationTypeEnum.UPDATE)
    public APIResult<?> activateOrSuspend(@PathVariable("instanceId") String instanceId,
                                          @RequestParam("activate") boolean activate) {
        flowInstanceService.activateOrSuspend(activate, instanceId);
        return APIResult.success();
    }

    @DeleteMapping("{instanceId}")
    @PreAuthorize("hasPermission('','flow:instance:delete')")
    @Operation(summary = "删除流程实例", tags = {"工作流流程实例管理"},
            parameters = {
                    @Parameter(name = "instanceId", description = "流程实例ID"),
                    @Parameter(name = "deleteReason", description = "删除原因")
            })
    @OperationLog(module = SystemTypeEnum.FLOWABLE, desc = "删除流程实例", operation = OperationTypeEnum.DELETE)
    public APIResult<?> delete(@PathVariable("instanceId") String instanceId,
                               @RequestParam(required = false) String deleteReason) {
        flowInstanceService.delete(instanceId, deleteReason);
        return APIResult.success();
    }
}
