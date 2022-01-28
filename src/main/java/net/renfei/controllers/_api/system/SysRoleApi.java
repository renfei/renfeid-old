package net.renfei.controllers._api.system;

import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.tags.Tag;
import net.renfei.model.APIResult;
import net.renfei.model.ListData;
import net.renfei.repositories.model.SysRole;
import org.springframework.web.bind.annotation.*;

/**
 * 系统角色与权限接口
 *
 * @author renfei
 */
@RequestMapping("/_/api")
@Tag(name = "系统角色与权限接口", description = "系统角色与权限接口")
public interface SysRoleApi {
    @GetMapping("sys/role")
    @Operation(summary = "获取系统角色列表", tags = {"系统接口列表"},
            description = "只能获取到登陆用户自己拥有的角色，超管和安全管理员可以获取所有角色")
    APIResult<ListData<SysRole>> getSysRoleList(@RequestParam(value = "pages", required = false) String pages,
                                                @RequestParam(value = "rows", required = false) String rows);

    @PostMapping("sys/role")
    @Operation(summary = "添加系统角色", tags = {"系统接口列表"}, description = "只有超管和安全管理员可以添加角色")
    APIResult addSysRole(@RequestBody SysRole sysRole);

    @PutMapping("sys/role/{id}")
    @Operation(summary = "修改系统角色", tags = {"系统接口列表"}, description = "只有超管和安全管理员可以修改系统角色")
    APIResult updateSysRole(@PathVariable("id") Long id, @RequestBody SysRole sysRole);

    @DeleteMapping("sys/role/{id}")
    @Operation(summary = "删除系统角色", tags = {"系统接口列表"}, description = "只有超管和安全管理员可以删除系统角色")
    APIResult deleteSysRole(@PathVariable("id") Long id);
}
