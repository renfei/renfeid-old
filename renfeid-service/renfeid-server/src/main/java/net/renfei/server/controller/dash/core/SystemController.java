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
import net.renfei.common.api.constant.enums.SystemSettingEnum;
import net.renfei.common.api.entity.ListData;
import net.renfei.common.core.annotation.OperationLog;
import net.renfei.common.core.entity.*;
import net.renfei.common.core.service.QuartzService;
import net.renfei.common.core.service.SystemLogService;
import net.renfei.common.core.service.SystemService;
import net.renfei.server.controller.AbstractController;
import net.renfei.server.entity.SystemSettingVo;
import net.renfei.uaa.api.UserService;
import net.renfei.uaa.api.entity.UserDetail;
import net.renfei.uaa.service.UaaUtilService;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;

import java.util.ArrayList;
import java.util.Date;
import java.util.List;

/**
 * 系统相关接口
 *
 * @author renfei
 */
@RestController
@RequestMapping("/_/api/core/system")
@Tag(name = "系统相关接口", description = "系统相关接口")
public class SystemController extends AbstractController {
    private final UserService userService;
    private final SystemService systemService;
    private final QuartzService quartzService;
    private final UaaUtilService uaaUtilService;
    private final SystemLogService systemLogService;

    public SystemController(UserService userService,
                            SystemService systemService,
                            QuartzService quartzService,
                            UaaUtilService uaaUtilService,
                            SystemLogService systemLogService) {
        this.userService = userService;
        this.systemService = systemService;
        this.quartzService = quartzService;
        this.uaaUtilService = uaaUtilService;
        this.systemLogService = systemLogService;
    }

    @GetMapping("refreshConfiguration")
    @PreAuthorize("hasPermission('','core:system:config')")
    @Operation(summary = "刷新系统配置", tags = {"系统相关接口"})
    @OperationLog(module = SystemTypeEnum.SYSTEM, desc = "刷新系统配置", operation = OperationTypeEnum.UPDATE)
    public APIResult refreshConfiguration() {
        systemService.refreshConfiguration();
        return APIResult.success();
    }

    @GetMapping("environment")
    @PreAuthorize("hasPermission('','core:environment:query')")
    @Operation(summary = "获取运行环境信息", tags = {"系统相关接口"})
    @OperationLog(module = SystemTypeEnum.SYSTEM, desc = "获取运行环境信息", operation = OperationTypeEnum.RETRIEVE)
    public APIResult<EnvironmentInfo> getEnvironmentInfo() {
        return new APIResult<>(new EnvironmentInfo());
    }

    @PostMapping("upload")
    @PreAuthorize("hasPermission('','core:file:upload')")
    @Operation(summary = "上传文件", tags = {"系统相关接口"},
            description = "此接口上传的文件将立即公开访问",
            parameters = {
                    @Parameter(name = "file", description = "文件")
            })
    @OperationLog(module = SystemTypeEnum.SYSTEM, desc = "上传文件", operation = OperationTypeEnum.CREATE)
    public APIResult<UploadObjectVo> uploadObject(MultipartFile file) {
        return new APIResult<>(systemService.uploadObject(file));
    }

    @GetMapping("log")
    @PreAuthorize("hasPermission('','core:log:query')")
    @Operation(summary = "系统审计日志", tags = {"系统相关接口"},
            description = "系统审计日志",
            parameters = {
                    @Parameter(name = "startDate", description = "日志起始时间"),
                    @Parameter(name = "endDate", description = "日志结束时间"),
                    @Parameter(name = "logLevel", description = "日志等级"),
                    @Parameter(name = "systemType", description = "功能模块"),
                    @Parameter(name = "operationType", description = "操作类型"),
                    @Parameter(name = "reqUri", description = "操作的资源地址"),
                    @Parameter(name = "username", description = "操作者用户名"),
                    @Parameter(name = "reqIp", description = "操作者IP"),
                    @Parameter(name = "pages", description = "页码"),
                    @Parameter(name = "rows", description = "每页数据量")
            })
    @OperationLog(module = SystemTypeEnum.SYS_LOGS, desc = "查看系统审计日志", operation = OperationTypeEnum.RETRIEVE)
    public APIResult<ListData<SystemLogEntity>> querySystemLog(
            @RequestParam(value = "startDate", required = false) Date startDate,
            @RequestParam(value = "endDate", required = false) Date endDate,
            @RequestParam(value = "logLevel", required = false) LogLevelEnum logLevel,
            @RequestParam(value = "systemType", required = false) SystemTypeEnum systemType,
            @RequestParam(value = "operationType", required = false) OperationTypeEnum operationType,
            @RequestParam(value = "reqUri", required = false) String reqUri,
            @RequestParam(value = "username", required = false) String username,
            @RequestParam(value = "reqIp", required = false) String reqIp,
            @RequestParam(value = "pages", required = false, defaultValue = "1") int pages,
            @RequestParam(value = "rows", required = false, defaultValue = "10") int rows) {
        UserDetail userDetail = systemService.currentUserDetail();
        // 需要从日志中排除的用户名，三员保密要求
        List<String> excludeUsername = null;
        // 仅查看这些用户的日志
        List<String> inUsername = null;
        if (!uaaUtilService.isSuperTubeUser(userDetail)) {
            // 不是超管，那么需要屏蔽一些用户
            if (uaaUtilService.isSystemSuperUser(userDetail)) {
                // 系统管理员，看不见安全保密管理员、安全审计管理员的日志
                excludeUsername = new ArrayList<>();
                List<UserDetail> securitySupers = userService.queryUserListByRoleName(systemConfig.getSecuritySuperRoleName());
                if (!securitySupers.isEmpty()) {
                    List<String> finalExcludeUsername = new ArrayList<>();
                    securitySupers.forEach(securitySuper -> finalExcludeUsername.add(securitySuper.getUsername()));
                    excludeUsername.addAll(finalExcludeUsername);
                }
                List<UserDetail> auditSupers = userService.queryUserListByRoleName(systemConfig.getAuditSuperRoleName());
                if (!auditSupers.isEmpty()) {
                    List<String> finalExcludeUsername = new ArrayList<>();
                    auditSupers.forEach(auditSuper -> finalExcludeUsername.add(auditSuper.getUsername()));
                    excludeUsername.addAll(finalExcludeUsername);
                }
            } else if (uaaUtilService.isSecuritySuperUser(userDetail)) {
                // 安全保密管理员，看不见系统管理员的日志
                excludeUsername = new ArrayList<>();
                List<UserDetail> systemSupers = userService.queryUserListByRoleName(systemConfig.getSystemSuperRoleName());
                if (!systemSupers.isEmpty()) {
                    List<String> finalExcludeUsername = new ArrayList<>();
                    systemSupers.forEach(securitySuper -> finalExcludeUsername.add(securitySuper.getUsername()));
                    excludeUsername.addAll(finalExcludeUsername);
                }
            } else if (uaaUtilService.isAuditSuperUser(userDetail)) {
                // 安全审计管理员，仅能进行查询分析系统管理员和安全管理员的操作日志
                inUsername = new ArrayList<>();
                List<UserDetail> systemSupers = userService.queryUserListByRoleName(systemConfig.getSystemSuperRoleName());
                if (!systemSupers.isEmpty()) {
                    List<String> finalExcludeUsername = new ArrayList<>();
                    systemSupers.forEach(securitySuper -> finalExcludeUsername.add(securitySuper.getUsername()));
                    inUsername.addAll(finalExcludeUsername);
                }
                List<UserDetail> securitySupers = userService.queryUserListByRoleName(systemConfig.getSecuritySuperRoleName());
                if (!securitySupers.isEmpty()) {
                    List<String> finalExcludeUsername = new ArrayList<>();
                    securitySupers.forEach(securitySuper -> finalExcludeUsername.add(securitySuper.getUsername()));
                    inUsername.addAll(finalExcludeUsername);
                }
            } else {
                // 啥也不是，屏蔽全部三员账号
                excludeUsername = new ArrayList<>();
                List<UserDetail> systemSupers = userService.queryUserListByRoleName(systemConfig.getSystemSuperRoleName());
                if (!systemSupers.isEmpty()) {
                    List<String> finalExcludeUsername = new ArrayList<>();
                    systemSupers.forEach(securitySuper -> finalExcludeUsername.add(securitySuper.getUsername()));
                    excludeUsername.addAll(finalExcludeUsername);
                }
                List<UserDetail> securitySupers = userService.queryUserListByRoleName(systemConfig.getSecuritySuperRoleName());
                if (!securitySupers.isEmpty()) {
                    List<String> finalExcludeUsername = new ArrayList<>();
                    securitySupers.forEach(securitySuper -> finalExcludeUsername.add(securitySuper.getUsername()));
                    excludeUsername.addAll(finalExcludeUsername);
                }
                List<UserDetail> auditSupers = userService.queryUserListByRoleName(systemConfig.getAuditSuperRoleName());
                if (!auditSupers.isEmpty()) {
                    List<String> finalExcludeUsername = new ArrayList<>();
                    auditSupers.forEach(auditSuper -> finalExcludeUsername.add(auditSuper.getUsername()));
                    excludeUsername.addAll(finalExcludeUsername);
                }
            }
        }
        return new APIResult<>(systemLogService.querySystemLog(startDate, endDate, logLevel,
                systemType, operationType, reqUri, username, reqIp, excludeUsername, inUsername, pages, rows));
    }

    @GetMapping("crontab")
    @PreAuthorize("hasPermission('','core:crontab:query')")
    @Operation(summary = "查看系统定时任务列表", tags = {"系统相关接口"}, description = "创建系统定时任务")
    @OperationLog(module = SystemTypeEnum.SYS_QUARTZ, desc = "查看系统定时任务列表", operation = OperationTypeEnum.RETRIEVE)
    public APIResult<List<CronJobVo>> queryJobList() {
        return quartzService.queryJobList();
    }

    @PostMapping("crontab/{jobGroup}/{jobName}")
    @PreAuthorize("hasPermission('','core:crontab:create')")
    @Operation(summary = "创建系统定时任务", tags = {"系统相关接口"},
            description = "创建系统定时任务",
            parameters = {
                    @Parameter(name = "jobName", description = "任务名称"),
                    @Parameter(name = "jobGroup", description = "任务组名")
            })
    @OperationLog(module = SystemTypeEnum.SYS_QUARTZ, desc = "创建系统定时任务", operation = OperationTypeEnum.CREATE)
    public APIResult createJob(@PathVariable("jobName") String jobName,
                               @PathVariable("jobGroup") String jobGroup,
                               @RequestBody CronJobAo cronJobAo) {
        return quartzService.createJob(jobName, jobGroup, cronJobAo);
    }

    @PutMapping("crontab/{jobGroup}/{jobName}/reschedule")
    @PreAuthorize("hasPermission('','core:crontab:reschedule')")
    @Operation(summary = "重排定时任务", tags = {"系统相关接口"},
            description = "重排定时任务",
            parameters = {
                    @Parameter(name = "jobName", description = "任务名称"),
                    @Parameter(name = "jobGroup", description = "任务组名"),
                    @Parameter(name = "cron", description = "定时表达式")
            })
    @OperationLog(module = SystemTypeEnum.SYS_QUARTZ, desc = "重排定时任务", operation = OperationTypeEnum.UPDATE)
    public APIResult rescheduleJob(@PathVariable("jobName") String jobName,
                                   @PathVariable("jobGroup") String jobGroup,
                                   @RequestBody String cron) {
        return quartzService.rescheduleJob(jobName, jobGroup, cron);
    }

    @PutMapping("crontab/{jobGroup}/{jobName}/pause")
    @PreAuthorize("hasPermission('','core:crontab:pause')")
    @Operation(summary = "暂停定时任务", tags = {"系统相关接口"},
            description = "暂停定时任务",
            parameters = {
                    @Parameter(name = "jobName", description = "任务名称"),
                    @Parameter(name = "jobGroup", description = "任务组名")
            })
    @OperationLog(module = SystemTypeEnum.SYS_QUARTZ, desc = "暂停定时任务", operation = OperationTypeEnum.UPDATE)
    public APIResult pauseJob(@PathVariable("jobName") String jobName,
                              @PathVariable("jobGroup") String jobGroup) {
        return quartzService.pauseJob(jobName, jobGroup);
    }

    @PutMapping("crontab/{jobGroup}/{jobName}/resume")
    @PreAuthorize("hasPermission('','core:crontab:resume')")
    @Operation(summary = "恢复定时任务", tags = {"系统相关接口"},
            description = "恢复定时任务",
            parameters = {
                    @Parameter(name = "jobName", description = "任务名称"),
                    @Parameter(name = "jobGroup", description = "任务组名")
            })
    @OperationLog(module = SystemTypeEnum.SYS_QUARTZ, desc = "恢复定时任务", operation = OperationTypeEnum.UPDATE)
    public APIResult resumeJob(@PathVariable("jobName") String jobName,
                               @PathVariable("jobGroup") String jobGroup) {
        return quartzService.resumeJob(jobName, jobGroup);
    }

    @DeleteMapping("crontab/{jobGroup}/{jobName}")
    @PreAuthorize("hasPermission('','core:crontab:delete')")
    @Operation(summary = "删除定时任务", tags = {"系统相关接口"},
            description = "删除定时任务",
            parameters = {
                    @Parameter(name = "jobName", description = "任务名称"),
                    @Parameter(name = "jobGroup", description = "任务组名")
            })
    @OperationLog(module = SystemTypeEnum.SYS_QUARTZ, desc = "删除定时任务", operation = OperationTypeEnum.DELETE)
    public APIResult deleteJob(@PathVariable("jobName") String jobName,
                               @PathVariable("jobGroup") String jobGroup) {
        return quartzService.deleteJob(jobName, jobGroup);
    }

    @GetMapping("setting/{setting}")
    @PreAuthorize("hasPermission('','core:setting:query')")
    @Operation(summary = "查询系统全局设置", tags = {"系统相关接口"},
            description = "查询系统全局设置",
            parameters = {@Parameter(name = "setting", description = "系统设置KEY")})
    @OperationLog(module = SystemTypeEnum.SYS_SETTING, desc = "查询系统全局设置")
    public APIResult<SystemSettingVo> querySystemSetting(@PathVariable("setting") SystemSettingEnum setting) {
        SystemSettingVo systemSettingVo = new SystemSettingVo();
        systemSettingVo.setSetting(setting);
        systemSettingVo.setValues(systemService.querySystemSetting(setting));
        return new APIResult<>(systemSettingVo);
    }

    @PutMapping("setting/{setting}")
    @PreAuthorize("hasPermission('','core:setting:update')")
    @Operation(summary = "修改系统全局设置", tags = {"系统相关接口"},
            description = "修改系统全局设置",
            parameters = {@Parameter(name = "setting", description = "系统设置KEY")})
    @OperationLog(module = SystemTypeEnum.SYS_SETTING, desc = "修改系统全局设置",operation = OperationTypeEnum.UPDATE)
    public APIResult<String> updateSystemSetting(@PathVariable("setting") SystemSettingEnum setting,
                                                         @RequestBody SystemSettingVo systemSetting) {
        systemService.updateSystemSetting(setting,systemSetting.getValues());
        return new APIResult<>("");
    }
}
