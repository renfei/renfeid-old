package net.renfei.controllers._api.system;

import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.tags.Tag;
import net.renfei.model.APIResult;
import net.renfei.model.ListData;
import net.renfei.model.system.SysApi;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;

/**
 * 系统接口列表
 * 用于给角色分配权限使用
 *
 * @author renfei
 */
@RequestMapping("/_/api")
@Tag(name = "系统接口列表", description = "系统接口列表，用于给角色分配权限使用")
public interface SysApiListApi {
    @GetMapping("sys/api")
    @Operation(summary = "获取系统接口列表", tags = {"系统接口列表"}, description = "此接口只能获取用户拥有的接口权限，超管和安全管理员可以获取全部接口")
    APIResult<ListData<SysApi>> getSysApiList(@RequestParam(value = "pages", required = false) String pages,
                                              @RequestParam(value = "rows", required = false) String rows);
}
