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
import net.renfei.bpm.api.FlowTaskService;
import net.renfei.bpm.api.entity.*;
import net.renfei.common.api.constant.APIResult;
import net.renfei.common.api.entity.ListData;
import net.renfei.common.core.annotation.OperationLog;
import net.renfei.common.core.entity.OperationTypeEnum;
import net.renfei.common.core.entity.SystemTypeEnum;
import net.renfei.server.controller.AbstractController;
import org.flowable.bpmn.model.UserTask;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.*;

import javax.imageio.ImageIO;
import javax.servlet.http.HttpServletResponse;
import java.awt.image.BufferedImage;
import java.io.IOException;
import java.io.InputStream;
import java.io.OutputStream;
import java.util.List;
import java.util.Map;

/**
 * 工作流流程任务管理
 *
 * @author renfei
 */
@RestController
@RequestMapping("/_/api/flow/task")
@Tag(name = "工作流流程任务管理", description = "工作流流程任务管理")
public class FlowTaskDashController extends AbstractController {
    private final FlowTaskService flowTaskService;

    public FlowTaskDashController(FlowTaskService flowTaskService) {
        this.flowTaskService = flowTaskService;
    }

    @GetMapping("todoList")
    @PreAuthorize("isAuthenticated()")
    @Operation(summary = "查询我的待办流程", tags = {"工作流流程任务管理"},
            parameters = {
                    @Parameter(name = "pages", description = "页码"),
                    @Parameter(name = "rows", description = "每页数据量")
            })
    @OperationLog(module = SystemTypeEnum.FLOWABLE, desc = "查询我的待办流程")
    public APIResult<ListData<FlowTaskVO>> todoList(@RequestParam(value = "pages", required = false, defaultValue = "1") Integer pages,
                                                    @RequestParam(value = "rows", required = false, defaultValue = "10") Integer rows) {
        return new APIResult<>(flowTaskService.queryTodoList(pages, rows));
    }

    @GetMapping("finishedList")
    @PreAuthorize("isAuthenticated()")
    @Operation(summary = "查询我的已办流程", tags = {"工作流流程任务管理"},
            parameters = {
                    @Parameter(name = "pages", description = "页码"),
                    @Parameter(name = "rows", description = "每页数据量")
            })
    @OperationLog(module = SystemTypeEnum.FLOWABLE, desc = "查询我的已办流程")
    public APIResult<ListData<FlowTaskVO>> finishedList(@RequestParam(value = "pages", required = false, defaultValue = "1") Integer pages,
                                                        @RequestParam(value = "rows", required = false, defaultValue = "10") Integer rows) {
        return new APIResult<>(flowTaskService.queryFinishedList(pages, rows));
    }

    @GetMapping("myProcess")
    @PreAuthorize("isAuthenticated()")
    @Operation(summary = "查询我发起的流程", tags = {"工作流流程任务管理"},
            parameters = {
                    @Parameter(name = "pages", description = "页码"),
                    @Parameter(name = "rows", description = "每页数据量")
            })
    @OperationLog(module = SystemTypeEnum.FLOWABLE, desc = "查询我发起的流程")
    public APIResult<ListData<FlowTaskVO>> myProcess(@RequestParam(value = "pages", required = false, defaultValue = "1") Integer pages,
                                                     @RequestParam(value = "rows", required = false, defaultValue = "10") Integer rows) {
        return new APIResult<>(flowTaskService.queryMyProcess(pages, rows));
    }

    @PostMapping("stopProcess")
    @PreAuthorize("hasPermission('','flow:task:stop')")
    @Operation(summary = "取消流程", tags = {"工作流流程任务管理"})
    @OperationLog(module = SystemTypeEnum.FLOWABLE, desc = "取消流程", operation = OperationTypeEnum.UPDATE)
    public APIResult<?> stopProcess(@RequestBody FlowTaskAO flowTaskAo) {
        flowTaskService.stopProcess(flowTaskAo);
        return APIResult.success();
    }

    @PostMapping("revokeProcess")
    @PreAuthorize("isAuthenticated()")
    @Operation(summary = "撤回流程", tags = {"工作流流程任务管理"})
    @OperationLog(module = SystemTypeEnum.FLOWABLE, desc = "撤回流程", operation = OperationTypeEnum.UPDATE)
    public APIResult<?> revokeProcess(@RequestBody FlowTaskAO flowTaskAo) {
        flowTaskService.revokeProcess(flowTaskAo);
        return APIResult.success();
    }

    @GetMapping("record")
    @PreAuthorize("hasPermission('','flow:task:record')")
    @Operation(summary = "查询流程历史流转记录", tags = {"工作流流程任务管理"},
            parameters = {
                    @Parameter(name = "procInsId", description = "流程实例Id"),
                    @Parameter(name = "deployId", description = "暂时没用")
            })
    @OperationLog(module = SystemTypeEnum.FLOWABLE, desc = "查询流程历史流转记录")
    public APIResult<FlowableRecord> flowRecord(@RequestParam("procInsId") String procInsId,
                                                @RequestParam("deployId") String deployId) {
        return new APIResult<>(flowTaskService.flowRecord(procInsId, deployId));
    }

    @GetMapping("processVariables/{taskId}")
    @PreAuthorize("hasPermission('','flow:task:variables')")
    @Operation(summary = "查询流程变量", tags = {"工作流流程任务管理"},
            parameters = {
                    @Parameter(name = "taskId", description = "流程任务Id")
            })
    @OperationLog(module = SystemTypeEnum.FLOWABLE, desc = "查询流程变量")
    public APIResult<Map<String, Object>> getProcessVariables(@PathVariable(value = "taskId") String taskId) {
        return new APIResult<>(flowTaskService.getProcessVariables(taskId));
    }

    @PostMapping("complete")
    @PreAuthorize("hasPermission('','flow:task:complete')")
    @Operation(summary = "审批流程任务", tags = {"工作流流程任务管理"})
    @OperationLog(module = SystemTypeEnum.FLOWABLE, desc = "审批流程任务", operation = OperationTypeEnum.UPDATE)
    public APIResult<?> complete(@RequestBody FlowTaskAO flowTaskAo) {
        flowTaskService.complete(flowTaskAo);
        return APIResult.success();
    }

    @PostMapping("reject")
    @PreAuthorize("hasPermission('','flow:task:reject')")
    @Operation(summary = "驳回流程任务", tags = {"工作流流程任务管理"})
    @OperationLog(module = SystemTypeEnum.FLOWABLE, desc = "驳回流程任务", operation = OperationTypeEnum.UPDATE)
    public APIResult<?> reject(@RequestBody FlowTaskAO flowTaskAo) {
        flowTaskService.reject(flowTaskAo);
        return APIResult.success();
    }

    @PostMapping("fallback")
    @PreAuthorize("hasPermission('','flow:task:fallback')")
    @Operation(summary = "退回流程任务", tags = {"工作流流程任务管理"})
    @OperationLog(module = SystemTypeEnum.FLOWABLE, desc = "退回流程任务", operation = OperationTypeEnum.UPDATE)
    public APIResult<?> fallback(@RequestBody FlowTaskAO flowTaskAo) {
        flowTaskService.fallback(flowTaskAo);
        return APIResult.success();
    }

    @GetMapping("fallback")
    @PreAuthorize("hasPermission('','flow:task:fallback')")
    @Operation(summary = "获取所有可回退的节点", tags = {"工作流流程任务管理"})
    @OperationLog(module = SystemTypeEnum.FLOWABLE, desc = "获取所有可回退的节点")
    public APIResult<List<UserTask>> queryCanFallbackTaskList(@RequestBody FlowTaskAO flowTaskAo) {
        return new APIResult<>(flowTaskService.queryCanFallbackTaskList(flowTaskAo));
    }

    @DeleteMapping("")
    @PreAuthorize("hasPermission('','flow:task:delete')")
    @Operation(summary = "删除流程任务", tags = {"工作流流程任务管理"})
    @OperationLog(module = SystemTypeEnum.FLOWABLE, desc = "删除流程任务", operation = OperationTypeEnum.DELETE)
    public APIResult<?> deleteTask(@RequestBody FlowTaskAO flowTaskAo) {
        flowTaskService.deleteTask(flowTaskAo);
        return APIResult.success();
    }

    @PostMapping("claim")
    @PreAuthorize("hasPermission('','flow:task:claim')")
    @Operation(summary = "认领流程任务", tags = {"工作流流程任务管理"})
    @OperationLog(module = SystemTypeEnum.FLOWABLE, desc = "认领流程任务", operation = OperationTypeEnum.UPDATE)
    public APIResult<?> claim(@RequestBody FlowTaskAO flowTaskAo) {
        flowTaskService.claim(flowTaskAo);
        return APIResult.success();
    }

    @PostMapping("unClaim")
    @PreAuthorize("hasPermission('','flow:task:claim')")
    @Operation(summary = "取消认领流程任务", tags = {"工作流流程任务管理"})
    @OperationLog(module = SystemTypeEnum.FLOWABLE, desc = "取消认领流程任务", operation = OperationTypeEnum.UPDATE)
    public APIResult<?> unClaim(@RequestBody FlowTaskAO flowTaskAo) {
        flowTaskService.unClaim(flowTaskAo);
        return APIResult.success();
    }

    @PostMapping("delegate")
    @PreAuthorize("hasPermission('','flow:task:delegate')")
    @Operation(summary = "委派流程任务", tags = {"工作流流程任务管理"})
    @OperationLog(module = SystemTypeEnum.FLOWABLE, desc = "委派流程任务", operation = OperationTypeEnum.UPDATE)
    public APIResult<?> delegateTask(@RequestBody FlowTaskAO flowTaskAo) {
        flowTaskService.delegateTask(flowTaskAo);
        return APIResult.success();
    }

    @PostMapping("assign")
    @PreAuthorize("hasPermission('','flow:task:assign')")
    @Operation(summary = "转办流程任务", tags = {"工作流流程任务管理"})
    @OperationLog(module = SystemTypeEnum.FLOWABLE, desc = "转办流程任务", operation = OperationTypeEnum.UPDATE)
    public APIResult<?> assignTask(@RequestBody FlowTaskAO flowTaskAo) {
        flowTaskService.assignTask(flowTaskAo);
        return APIResult.success();
    }

    @PostMapping("nextFlowNode")
    @PreAuthorize("hasPermission('','flow:task:next')")
    @Operation(summary = "获取下一节点流程任务", tags = {"工作流流程任务管理"})
    @OperationLog(module = SystemTypeEnum.FLOWABLE, desc = "获取下一节点流程任务")
    public APIResult<FlowNextDTO> nextFlowNode(@RequestBody FlowTaskAO flowTaskAo) {
        return new APIResult<>(flowTaskService.queryNextFlowNode(flowTaskAo));
    }

    @GetMapping("diagram/{processId}")
    @PreAuthorize("isAuthenticated()")
    @Operation(summary = "生成流程图", tags = {"工作流流程任务管理"})
    @OperationLog(module = SystemTypeEnum.FLOWABLE, desc = "生成流程图")
    public void genProcessDiagram(HttpServletResponse response,
                                  @PathVariable("processId") String processId) {
        InputStream inputStream = flowTaskService.diagram(processId);
        OutputStream os = null;
        BufferedImage image = null;
        try {
            image = ImageIO.read(inputStream);
            response.setContentType("image/png");
            os = response.getOutputStream();
            if (image != null) {
                ImageIO.write(image, "png", os);
            }
        } catch (Exception e) {
            e.printStackTrace();
        } finally {
            try {
                if (os != null) {
                    os.flush();
                    os.close();
                }
            } catch (IOException e) {
                e.printStackTrace();
            }
        }
    }

    @GetMapping("flowViewer/{procInsId}/{executionId}")
    @PreAuthorize("isAuthenticated()")
    @Operation(summary = "获取流程执行过程", tags = {"工作流流程任务管理"},
            parameters = {
                    @Parameter(name = "procInsId", description = "流程实例编号"),
                    @Parameter(name = "executionId", description = "历史流程实例")
            })
    @OperationLog(module = SystemTypeEnum.FLOWABLE, desc = "获取流程执行过程")
    public APIResult<List<FlowViewerDTO>> getFlowViewer(@PathVariable("procInsId") String procInsId,
                                                        @PathVariable("executionId") String executionId) {
        return new APIResult<>(flowTaskService.getFlowViewer(procInsId, executionId));
    }
}
