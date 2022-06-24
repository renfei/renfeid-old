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
package net.renfei.server.controller.dash.uaa;

import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.Parameter;
import io.swagger.v3.oas.annotations.tags.Tag;
import net.renfei.common.api.constant.APIResult;
import net.renfei.common.core.annotation.OperationLog;
import net.renfei.common.core.entity.OperationTypeEnum;
import net.renfei.common.core.entity.SystemTypeEnum;
import net.renfei.server.controller.AbstractController;
import net.renfei.uaa.api.RoleService;
import net.renfei.uaa.api.entity.RoleDetail;
import org.springframework.web.bind.annotation.*;

import java.util.List;

/**
 * 角色管理接口
 *
 * @author renfei
 */
@RestController
@RequestMapping("/_/api/uaa")
@Tag(name = "角色管理接口", description = "角色管理接口")
public class RoleController extends AbstractController {
    private final RoleService roleService;

    public RoleController(RoleService roleService) {
        this.roleService = roleService;
    }

    @GetMapping("role")
    @Operation(summary = "查询角色列表", tags = {"角色管理接口"},
            parameters = {
                    @Parameter(name = "roleName", description = "角色名称"),
                    @Parameter(name = "pages", description = "页码"),
                    @Parameter(name = "rows", description = "每页数据量")
            })
    @OperationLog(module = SystemTypeEnum.SYS_ROLE, desc = "查询角色列表")
    public APIResult<List<RoleDetail>> queryRoleList(@RequestParam(value = "roleName", required = false) String roleName,
                                                     @RequestParam(value = "pages", required = false) Integer pages,
                                                     @RequestParam(value = "rows", required = false) Integer rows) {
        return roleService.queryRoleList(false, roleName, pages == null ? 1 : pages, rows == null ? 10 : rows);
    }

    @PostMapping("role")
    @Operation(summary = "创建角色", tags = {"角色管理接口"})
    @OperationLog(module = SystemTypeEnum.SYS_ROLE, desc = "创建角色", operation = OperationTypeEnum.CREATE)
    public APIResult<RoleDetail> createRole(@RequestBody RoleDetail roleDetail) {
        return roleService.createRole(roleDetail, request);
    }

    @PutMapping("role/{id}")
    @Operation(summary = "编辑角色", tags = {"角色管理接口"}, parameters = {
            @Parameter(name = "id", description = "角色ID")
    })
    @OperationLog(module = SystemTypeEnum.SYS_ROLE, desc = "编辑角色", operation = OperationTypeEnum.UPDATE)
    public APIResult<RoleDetail> updateRole(@PathVariable("id") Long roleId, @RequestBody RoleDetail roleDetail) {
        return roleService.updateRole(roleId, roleDetail, request);
    }

    @DeleteMapping("role/{id}")
    @Operation(summary = "删除角色", tags = {"角色管理接口"}, parameters = {
            @Parameter(name = "id", description = "角色ID")
    })
    @OperationLog(module = SystemTypeEnum.SYS_ROLE, desc = "删除角色", operation = OperationTypeEnum.DELETE)
    public APIResult deleteRole(@PathVariable("id") Long roleId) {
        return roleService.deleteRole(roleId, request);
    }
}
