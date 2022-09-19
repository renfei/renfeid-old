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
import net.renfei.bpm.api.FlowDefinitionService;
import net.renfei.bpm.api.entity.FlowProcDefDTO;
import net.renfei.bpm.api.entity.FlowSaveXmlAO;
import net.renfei.common.api.constant.APIResult;
import net.renfei.common.api.constant.enums.StateCodeEnum;
import net.renfei.common.api.entity.ListData;
import net.renfei.common.core.annotation.OperationLog;
import net.renfei.common.core.entity.OperationTypeEnum;
import net.renfei.common.core.entity.SystemTypeEnum;
import net.renfei.server.controller.AbstractController;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;

import javax.imageio.ImageIO;
import javax.servlet.http.HttpServletResponse;
import java.awt.image.BufferedImage;
import java.io.ByteArrayInputStream;
import java.io.IOException;
import java.io.InputStream;
import java.io.OutputStream;
import java.nio.charset.StandardCharsets;
import java.util.Map;

/**
 * 工作流程定义管理
 *
 * @author renfei
 */
@RestController
@RequestMapping("/_/api/flow/definition")
@Tag(name = "工作流程定义管理", description = "工作流程定义管理")
public class FlowDefinitionDashController extends AbstractController {
    private final static Logger logger = LoggerFactory.getLogger(FlowDefinitionDashController.class);
    private final FlowDefinitionService flowDefinitionService;

    public FlowDefinitionDashController(FlowDefinitionService flowDefinitionService) {
        this.flowDefinitionService = flowDefinitionService;
    }

    @GetMapping("")
    @PreAuthorize("hasPermission('','flow:definition:query')")
    @Operation(summary = "查询工作流程定义", tags = {"工作流流程定义管理"},
            parameters = {
                    @Parameter(name = "name", description = "流程名称"),
                    @Parameter(name = "pages", description = "页码"),
                    @Parameter(name = "rows", description = "每页数据量")
            })
    @OperationLog(module = SystemTypeEnum.FLOWABLE, desc = "查询工作流程定义")
    public APIResult<ListData<FlowProcDefDTO>> queryFlowableDefinition(@RequestParam(value = "name", required = false) String name,
                                                                       @RequestParam(value = "pages", required = false, defaultValue = "1") int pages,
                                                                       @RequestParam(value = "rows", required = false, defaultValue = "10") int rows) {
        return new APIResult<>(flowDefinitionService.queryFlowableDefinition(name, pages, rows));
    }

    @PostMapping("import")
    @PreAuthorize("hasPermission('','flow:definition:save')")
    @Operation(summary = "导入流程文件", tags = {"工作流流程定义管理"})
    @OperationLog(module = SystemTypeEnum.FLOWABLE, desc = "导入流程文件", operation = OperationTypeEnum.CREATE)
    public APIResult<?> importFile(@RequestParam(value = "name", required = false) String name,
                                   @RequestParam(value = "category", required = false) String category,
                                   MultipartFile file) {
        try (InputStream in = file.getInputStream()) {
            flowDefinitionService.importFile(name, category, in);
        } catch (Exception e) {
            logger.error("导入失败:", e);
            return APIResult.builder()
                    .code(StateCodeEnum.Failure)
                    .message(e.getMessage())
                    .build();
        }
        return APIResult.success();
    }

    @GetMapping("readXml/{deployId}")
    @PreAuthorize("hasPermission('','flow:definition:read')")
    @Operation(summary = "读取xml文件", tags = {"工作流流程定义管理"})
    @OperationLog(module = SystemTypeEnum.FLOWABLE, desc = "读取xml文件")
    public APIResult<String> readXml(@PathVariable(value = "deployId") String deployId) {
        try {
            return new APIResult<>(flowDefinitionService.readXml(deployId));
        } catch (IOException e) {
            logger.error("加载xml文件异常:", e);
            return APIResult.builder()
                    .code(StateCodeEnum.Failure)
                    .message("加载xml文件异常")
                    .build();
        }
    }

    @GetMapping("readImage/{deployId}")
    @PreAuthorize("hasPermission('','flow:definition:read')")
    @Operation(summary = "读取图片文件", tags = {"工作流流程定义管理"})
    @OperationLog(module = SystemTypeEnum.FLOWABLE, desc = "读取图片文件")
    public void readImage(@PathVariable(value = "deployId") String deployId, HttpServletResponse response) {
        try (OutputStream os = response.getOutputStream()) {
            BufferedImage image = ImageIO.read(flowDefinitionService.readImage(deployId));
            response.setContentType("image/png");
            if (image != null) {
                ImageIO.write(image, "png", os);
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    @PostMapping("save")
    @PreAuthorize("hasPermission('','flow:definition:save')")
    @Operation(summary = "保存流程设计器内的xml文件", tags = {"工作流流程定义管理"})
    @OperationLog(module = SystemTypeEnum.FLOWABLE, desc = "保存流程设计器内的xml文件", operation = OperationTypeEnum.CREATE)
    public APIResult<?> save(@RequestBody FlowSaveXmlAO ao) {
        try (InputStream in = new ByteArrayInputStream(ao.getXml().getBytes(StandardCharsets.UTF_8))) {
            flowDefinitionService.importFile(ao.getName(), ao.getCategory(), in);
            return APIResult.success();
        } catch (Exception e) {
            logger.error("导入失败:", e);
            return APIResult.builder()
                    .code(StateCodeEnum.Failure)
                    .message("导入失败")
                    .build();
        }
    }

    @PostMapping("start/{procDefId}")
    @PreAuthorize("hasPermission('','flow:definition:start')")
    @Operation(summary = "根据流程定义id启动流程实例", tags = {"工作流流程实例管理"},
            parameters = {
                    @Parameter(name = "procDefId", description = "流程定义id"),
                    @Parameter(name = "variables", description = "变量集合,json对象")
            })
    @OperationLog(module = SystemTypeEnum.FLOWABLE, desc = "根据流程定义id启动流程实例", operation = OperationTypeEnum.CREATE)
    public APIResult<?> start(@PathVariable("procDefId") String procDefId,
                              @RequestBody Map<String, Object> variables) {
        flowDefinitionService.startProcessInstanceById(procDefId, variables);
        return APIResult.success();
    }

    @PutMapping("activate/{deployId}")
    @PreAuthorize("hasPermission('','flow:definition:activate')")
    @Operation(summary = "激活或挂起流程定义", tags = {"工作流流程定义管理"},
            parameters = {
                    @Parameter(name = "deployId", description = "部署ID"),
                    @Parameter(name = "activate", description = "激活或挂起")
            })
    @OperationLog(module = SystemTypeEnum.FLOWABLE, desc = "激活或挂起流程定义", operation = OperationTypeEnum.UPDATE)
    public APIResult<?> activateOrSuspend(@PathVariable("deployId") String deployId,
                                          @RequestParam("activate") boolean activate) {
        flowDefinitionService.activateOrSuspend(activate, deployId);
        return APIResult.success();
    }

    @DeleteMapping("{deployId}")
    @PreAuthorize("hasPermission('','flow:definition:delete')")
    @Operation(summary = "删除流程定义", tags = {"工作流流程定义管理"},
            parameters = {
                    @Parameter(name = "deployId", description = "部署ID")
            })
    @OperationLog(module = SystemTypeEnum.FLOWABLE, desc = "删除流程定义", operation = OperationTypeEnum.DELETE)
    public APIResult<?> delete(@PathVariable("deployId") String deployId) {
        flowDefinitionService.delete(deployId);
        return APIResult.success();
    }
}
