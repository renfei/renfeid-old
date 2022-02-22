package net.renfei.console.controller.system;

import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.tags.Tag;
import net.renfei.model.APIResult;
import net.renfei.model.system.SystemOperationStatusEnum;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;

/**
 * 系统设置接口
 *
 * @author renfei
 */
@Tag(name = "系统设置接口", description = "系统设置接口")
@RequestMapping("/_/api")
public interface SysSettingApi {
    /**
     * 获取系统运行状态
     *
     * @return
     */
    @GetMapping("sys/SystemOperationStatus")
    @Operation(summary = "获取系统运行状态设置", tags = {"系统设置接口"}, description = "获取系统运行状态设置")
    APIResult<SystemOperationStatusEnum> querySystemOperationStatus();

    /**
     * 设置系统运行状态
     *
     * @param systemOperationStatusEnum 状态枚举
     * @return
     */
    @PutMapping("sys/SystemOperationStatus")
    @Operation(summary = "设置系统运行状态", tags = {"系统设置接口"}, description = "设置系统运行状态")
    APIResult settingSystemOperationStatus(@RequestParam("setting") SystemOperationStatusEnum systemOperationStatusEnum);
}
