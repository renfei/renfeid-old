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
package net.renfei.uaa.service;

import com.github.pagehelper.Page;
import com.github.pagehelper.PageHelper;
import net.renfei.common.api.constant.APIResult;
import net.renfei.common.api.constant.enums.StateCodeEnum;
import net.renfei.common.api.entity.ListData;
import net.renfei.common.api.exception.BusinessException;
import net.renfei.common.core.config.SystemConfig;
import net.renfei.common.core.entity.LogLevelEnum;
import net.renfei.common.core.entity.OperationTypeEnum;
import net.renfei.common.core.entity.SystemTypeEnum;
import net.renfei.common.core.service.SnowflakeService;
import net.renfei.common.core.service.SystemLogService;
import net.renfei.common.core.service.SystemService;
import net.renfei.uaa.api.RoleService;
import net.renfei.uaa.api.entity.Authority;
import net.renfei.uaa.api.entity.AuthorityTypeEnum;
import net.renfei.uaa.api.entity.RoleDetail;
import net.renfei.uaa.api.entity.UserDetail;
import net.renfei.uaa.repositories.UaaAuthorityMapper;
import net.renfei.uaa.repositories.UaaRoleMapper;
import net.renfei.uaa.repositories.UaaUserMapper;
import net.renfei.uaa.repositories.UaaUserRoleMapper;
import net.renfei.uaa.repositories.entity.*;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import javax.servlet.http.HttpServletRequest;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;

/**
 * 角色服务
 *
 * @author renfei
 */
@Service
public class RoleServiceImpl implements RoleService {
    private final SystemConfig systemConfig;
    private final UaaRoleMapper uaaRoleMapper;
    private final SystemService systemService;
    private final SystemLogService systemLogService;
    private final SnowflakeService snowflakeService;
    private final UaaUserMapper uaaUserMapper;
    private final UaaUserRoleMapper uaaUserRoleMapper;
    private final UaaAuthorityMapper uaaAuthorityMapper;

    public RoleServiceImpl(SystemConfig systemConfig,
                           UaaRoleMapper uaaRoleMapper,
                           SystemService systemService,
                           SystemLogService systemLogService,
                           SnowflakeService snowflakeService,
                           UaaUserMapper uaaUserMapper,
                           UaaUserRoleMapper uaaUserRoleMapper,
                           UaaAuthorityMapper uaaAuthorityMapper) {
        this.systemConfig = systemConfig;
        this.uaaRoleMapper = uaaRoleMapper;
        this.systemService = systemService;
        this.systemLogService = systemLogService;
        this.snowflakeService = snowflakeService;
        this.uaaUserMapper = uaaUserMapper;
        this.uaaUserRoleMapper = uaaUserRoleMapper;
        this.uaaAuthorityMapper = uaaAuthorityMapper;
    }

    @Override
    public APIResult<ListData<RoleDetail>> queryRoleList(boolean haveBuiltInRoles, String roleName, int pages, int rows) {
        UaaRoleExample example = new UaaRoleExample();
        UaaRoleExample.Criteria criteria = example.createCriteria();
        if (!haveBuiltInRoles) {
            criteria.andBuiltInRoleEqualTo(false);
        }
        if (roleName != null && !roleName.isEmpty()) {
            criteria.andRoleNameLike("%" + roleName + "%");
        }
        try (Page<UaaRole> page = PageHelper.startPage(pages, rows)) {
            uaaRoleMapper.selectByExampleWithBLOBs(example);
            List<RoleDetail> roleDetails = new ArrayList<>();
            for (UaaRole role : page.getResult()
            ) {
                RoleDetail roleDetail = convert(role);
                // 填充权限列表
                UaaAuthorityExample uaaAuthorityExample = new UaaAuthorityExample();
                uaaAuthorityExample.createCriteria()
                        .andRoleIdEqualTo(role.getId());
                List<UaaAuthority> uaaAuthorities = uaaAuthorityMapper.selectByExample(uaaAuthorityExample);
                if (!uaaAuthorities.isEmpty()) {
                    List<Authority> authorityList = new ArrayList<>();
                    uaaAuthorities.forEach(uaaAuthority -> {
                        Authority authority = new Authority();
                        authority.setAuthorityType(AuthorityTypeEnum.valueOf(uaaAuthority.getAuthorityType()));
                        authority.setTargetId(uaaAuthority.getTargetId());
                        authorityList.add(authority);
                    });
                    roleDetail.setAuthorityList(authorityList);
                }
                roleDetails.add(roleDetail);
            }
            ListData<RoleDetail> listData = new ListData<>();
            listData.setPageNum(page.getPageNum());
            listData.setPageSize(page.getPageSize());
            listData.setStartRow(page.getStartRow());
            listData.setEndRow(page.getEndRow());
            listData.setTotal(page.getTotal());
            listData.setPages(page.getPages());
            listData.setData(roleDetails);
            return new APIResult<>(listData);
        }
    }

    @Override
    public APIResult<List<RoleDetail>> queryRoleListByResource(AuthorityTypeEnum authorityTypeEnum, long resourceId) {
        UaaAuthorityExample uaaAuthorityExample = new UaaAuthorityExample();
        uaaAuthorityExample.createCriteria()
                .andAuthorityTypeEqualTo(authorityTypeEnum.toString())
                .andTargetIdEqualTo(resourceId);
        List<UaaAuthority> uaaAuthorities = uaaAuthorityMapper.selectByExample(uaaAuthorityExample);
        if (uaaAuthorities.isEmpty()) {
            return new APIResult<>(new ArrayList<>());
        }
        List<Long> roleIds = new ArrayList<>();
        uaaAuthorities.forEach(uaaAuthority -> roleIds.add(uaaAuthority.getRoleId()));
        UaaRoleExample example = new UaaRoleExample();
        example.createCriteria().andIdIn(roleIds);
        List<UaaRole> roles = uaaRoleMapper.selectByExampleWithBLOBs(example);
        List<RoleDetail> roleDetails = new ArrayList<>();
        for (UaaRole role : roles
        ) {
            roleDetails.add(convert(role));
        }
        return new APIResult<>(roleDetails);
    }

    @Override
    public APIResult<List<RoleDetail>> queryRoleListByUser(long userId, int pages, int rows) {
        UaaUserRoleExample userRoleExample = new UaaUserRoleExample();
        userRoleExample.createCriteria().andAccountIdEqualTo(userId);
        List<UaaUserRole> uaaUserRoles = uaaUserRoleMapper.selectByExample(userRoleExample);
        if (uaaUserRoles.isEmpty()) {
            return new APIResult<>(new ArrayList<>());
        }
        List<Long> roleIds = new ArrayList<>();
        uaaUserRoles.forEach(uaaUserRole -> roleIds.add(uaaUserRole.getRoleId()));
        UaaRoleExample example = new UaaRoleExample();
        example.createCriteria().andIdIn(roleIds);
        try (Page<UaaRole> page = PageHelper.startPage(pages, rows)) {
            uaaRoleMapper.selectByExampleWithBLOBs(example);
            List<RoleDetail> roleDetails = new ArrayList<>();
            for (UaaRole role : page.getResult()
            ) {
                RoleDetail roleDetail = convert(role);
                // 添加角色拥有的权限
                roleDetail.setAuthorityList(this.queryAuthorityListByRole(Long.parseLong(roleDetail.getId())).getData());
                roleDetails.add(roleDetail);
            }
            return new APIResult<>(roleDetails);
        }
    }

    @Override
    @Transactional(rollbackFor = Exception.class)
    public APIResult<List<RoleDetail>> authorizationRoleByUser(long userId, List<RoleDetail> roleDetailList, HttpServletRequest request) {
        UaaUser uaaUser = uaaUserMapper.selectByPrimaryKey(userId);
        if (uaaUser == null) {
            return APIResult.builder()
                    .code(StateCodeEnum.Failure)
                    .message("根据UserID未找到用户信息，请重试")
                    .build();
        }
        UserDetail currentUserDetail = systemService.currentUserDetail();
        if (uaaUser.getBuiltInUser()) {
            systemLogService.save(LogLevelEnum.WARN, SystemTypeEnum.ACCOUNT, OperationTypeEnum.UPDATE,
                    "尝试编辑内置用户的角色，被拒绝。", currentUserDetail.getUuid(), currentUserDetail.getUsername(), request
            );
            return APIResult.builder()
                    .code(StateCodeEnum.Failure)
                    .message("该用户为内置用户，禁止编辑，请求被拒绝")
                    .build();
        }
        // 先删除，再插入
        UaaUserRoleExample example = new UaaUserRoleExample();
        example.createCriteria().andAccountIdEqualTo(userId);
        uaaUserRoleMapper.deleteByExample(example);
        for (RoleDetail roleDetail : roleDetailList
        ) {
            UaaUserRole uaaUserRole = new UaaUserRole();
            uaaUserRole.setRoleId(Long.parseLong(roleDetail.getId()));
            uaaUserRole.setAccountId(userId);
            uaaUserRole.setAddTime(new Date());
            uaaUserRoleMapper.insertSelective(uaaUserRole);
        }
        return this.queryRoleListByUser(userId, 1, Integer.MAX_VALUE);
    }

    @Override
    public APIResult<List<Authority>> queryAuthorityListByRole(long roleId) {
        UaaAuthorityExample example = new UaaAuthorityExample();
        example.createCriteria().andRoleIdEqualTo(roleId);
        List<UaaAuthority> uaaAuthorities = uaaAuthorityMapper.selectByExample(example);
        List<Authority> authorityList = new ArrayList<>();
        for (UaaAuthority uaaAuthority : uaaAuthorities
        ) {
            authorityList.add(convert(uaaAuthority));
        }
        return new APIResult<>(authorityList);
    }

    @Override
    @Transactional(rollbackFor = Exception.class)
    public APIResult<RoleDetail> createRole(RoleDetail roleDetail, HttpServletRequest request) {
        UaaRole uaaRole = convert(roleDetail);
        if (uaaRole.getRoleName() == null || uaaRole.getRoleName().isEmpty()) {
            return APIResult.builder()
                    .code(StateCodeEnum.Failure)
                    .message("角色名称不能为空")
                    .build();
        }
        UaaRoleExample example = new UaaRoleExample();
        example.createCriteria().andRoleNameEqualTo(uaaRole.getRoleName());
        if (!uaaRoleMapper.selectByExample(example).isEmpty()) {
            return APIResult.builder()
                    .code(StateCodeEnum.Failure)
                    .message("角色名称已经存在，角色名称不能重复")
                    .build();
        }
        uaaRole.setId(snowflakeService.getId("").getId());
        uaaRole.setBuiltInRole(false);
        uaaRole.setAddTime(new Date());
        uaaRole.setUpdateTime(null);
        uaaRoleMapper.insertSelective(uaaRole);
        if (roleDetail.getAuthorityList() != null && !roleDetail.getAuthorityList().isEmpty()) {
            // 插入权限关系表
            updateAuthority(uaaRole.getId(), roleDetail.getAuthorityList(), request);
        }
        // 创建人默认拥有该角色
        UserDetail currentUserDetail = systemService.currentUserDetail();
        UaaUserRole uaaUserRole = new UaaUserRole();
        uaaUserRole.setRoleId(uaaRole.getId());
        uaaUserRole.setAddTime(new Date());
        uaaUserRole.setAccountId(Long.parseLong(currentUserDetail.getId()));
        uaaUserRoleMapper.insertSelective(uaaUserRole);
        return new APIResult<>(convert(uaaRole));
    }

    @Override
    @Transactional(rollbackFor = Exception.class)
    public APIResult<RoleDetail> updateRole(long roleId, RoleDetail roleDetail, HttpServletRequest request) {
        UaaRole oldUaaRole = uaaRoleMapper.selectByPrimaryKey(roleId);
        if (oldUaaRole == null) {
            return APIResult.builder()
                    .code(StateCodeEnum.Failure)
                    .message("根据角色ID未找到角色信息，请重试")
                    .build();
        }
        if (oldUaaRole.getBuiltInRole()) {
            UserDetail currentUserDetail = systemService.currentUserDetail();
            systemLogService.save(LogLevelEnum.WARN, SystemTypeEnum.ACCOUNT, OperationTypeEnum.UPDATE,
                    "尝试编辑内置角色，被拒绝。", currentUserDetail.getUuid(), currentUserDetail.getUsername(), request
            );
            return APIResult.builder()
                    .code(StateCodeEnum.Failure)
                    .message("内置角色禁止编辑，请求被拒绝")
                    .build();
        }
        boolean haveRole = false;
        UserDetail currentUserDetail = systemService.currentUserDetail();
        for (RoleDetail currentRoleDetail : currentUserDetail.getRoleDetailList()
        ) {
            if (Long.parseLong(currentRoleDetail.getId()) == roleId) {
                haveRole = true;
                break;
            }
        }
        if (!systemConfig.getSuperTubeUserName().equals(currentUserDetail.getUsername()) && !haveRole) {
            // 不是超管也不拥有这个角色
            systemLogService.save(LogLevelEnum.WARN, SystemTypeEnum.ACCOUNT, OperationTypeEnum.UPDATE,
                    "尝试编辑未拥有的角色，被拒绝。", currentUserDetail.getUuid(), currentUserDetail.getUsername(), request
            );
            return APIResult.builder()
                    .code(StateCodeEnum.Failure)
                    .message("您未拥有该角色，无权编辑，请求被拒绝")
                    .build();
        }
        if (roleDetail.getRoleName() == null || roleDetail.getRoleName().isEmpty()) {
            return APIResult.builder()
                    .code(StateCodeEnum.Failure)
                    .message("角色名称不能为空")
                    .build();
        }
        if (!oldUaaRole.getRoleName().equals(roleDetail.getRoleName())) {
            // 角色名称发生改变，检查重复
            UaaRoleExample example = new UaaRoleExample();
            example.createCriteria().andRoleNameEqualTo(roleDetail.getRoleName());
            if (!uaaRoleMapper.selectByExample(example).isEmpty()) {
                return APIResult.builder()
                        .code(StateCodeEnum.Failure)
                        .message("角色名称已经存在，角色名称不能重复")
                        .build();
            }
        }
        // 只能修改这几项
        oldUaaRole.setRoleName(roleDetail.getRoleName());
        oldUaaRole.setRoleDescribe(roleDetail.getRoleDescribe());
        oldUaaRole.setExtendJson(roleDetail.getExtendJson());
        oldUaaRole.setUpdateTime(new Date());
        uaaRoleMapper.updateByPrimaryKeyWithBLOBs(oldUaaRole);
        // 插入权限关系表
        updateAuthority(oldUaaRole.getId(), roleDetail.getAuthorityList(), request);
        return new APIResult<>(convert(oldUaaRole));
    }

    @Override
    @Transactional(rollbackFor = Exception.class)
    public APIResult deleteRole(long roleId, HttpServletRequest request) {
        UaaRole oldUaaRole = uaaRoleMapper.selectByPrimaryKey(roleId);
        if (oldUaaRole == null) {
            return APIResult.builder()
                    .code(StateCodeEnum.Failure)
                    .message("根据角色ID未找到角色信息，请重试")
                    .build();
        }
        if (oldUaaRole.getBuiltInRole()) {
            UserDetail currentUserDetail = systemService.currentUserDetail();
            systemLogService.save(LogLevelEnum.WARN, SystemTypeEnum.ACCOUNT, OperationTypeEnum.DELETE,
                    "尝试删除内置角色，被拒绝。", currentUserDetail.getUuid(), currentUserDetail.getUsername(), request
            );
            return APIResult.builder()
                    .code(StateCodeEnum.Failure)
                    .message("内置角色禁止删除，请求被拒绝")
                    .build();
        }
        boolean haveRole = false;
        UserDetail currentUserDetail = systemService.currentUserDetail();
        for (RoleDetail roleDetail : currentUserDetail.getRoleDetailList()
        ) {
            if (Long.parseLong(roleDetail.getId()) == roleId) {
                haveRole = true;
                break;
            }
        }
        if (!systemConfig.getSuperTubeUserName().equals(currentUserDetail.getUsername()) && !haveRole) {
            // 不是超管也不拥有这个角色
            systemLogService.save(LogLevelEnum.WARN, SystemTypeEnum.ACCOUNT, OperationTypeEnum.UPDATE,
                    "尝试删除未拥有的角色，被拒绝。", currentUserDetail.getUuid(), currentUserDetail.getUsername(), request
            );
            return APIResult.builder()
                    .code(StateCodeEnum.Failure)
                    .message("您未拥有该角色，无权编辑，请求被拒绝")
                    .build();
        }
        uaaRoleMapper.deleteByPrimaryKey(roleId);
        UaaAuthorityExample authorityExample = new UaaAuthorityExample();
        authorityExample.createCriteria().andRoleIdEqualTo(roleId);
        uaaAuthorityMapper.deleteByExample(authorityExample);
        UaaUserRoleExample userRoleExample = new UaaUserRoleExample();
        userRoleExample.createCriteria().andRoleIdEqualTo(roleId);
        uaaUserRoleMapper.deleteByExample(userRoleExample);
        return APIResult.success();
    }

    private void updateAuthority(long roleId, List<Authority> authorityList, HttpServletRequest request) {
        if (authorityList != null) {
            UserDetail currentUserDetail = systemService.currentUserDetail();
            for (Authority authority : authorityList
            ) {
                if (authority.getAuthorityType() == null) {
                    throw new BusinessException("角色的权限列表中AuthorityType不能为空");
                }
                if (authority.getTargetId() == null) {
                    throw new BusinessException("角色的权限列表中TargetId不能为空");
                }
                for (RoleDetail currentRoleDetail : currentUserDetail.getRoleDetailList()
                ) {
                    if (!currentRoleDetail.getAuthorityList().contains(authority)) {
                        systemLogService.save(LogLevelEnum.WARN, SystemTypeEnum.ACCOUNT, OperationTypeEnum.UPDATE,
                                String.format("尝试给角色分配自己未拥有的资源，被拒绝。资源类型[%s]ID[%s]。",
                                        authority.getAuthorityType(),
                                        authority.getTargetId()),
                                currentUserDetail.getUuid(), currentUserDetail.getUsername(), request
                        );
                        throw new BusinessException(
                                String.format("您未拥有资源类型[%s]ID[%s]的权限，无法分配给其他用户，请求被拒绝",
                                        authority.getAuthorityType(),
                                        authority.getTargetId())
                        );
                    }
                }
            }
        }
        // 先删除，再插入
        UaaAuthorityExample example = new UaaAuthorityExample();
        example.createCriteria().andRoleIdEqualTo(roleId);
        uaaAuthorityMapper.deleteByExample(example);
        if (authorityList != null) {
            for (Authority authority : authorityList
            ) {
                UaaAuthority uaaAuthority = new UaaAuthority();
                uaaAuthority.setAuthorityType(authority.getAuthorityType().toString());
                uaaAuthority.setRoleId(roleId);
                uaaAuthority.setTargetId(authority.getTargetId());
                uaaAuthority.setAddTime(new Date());
                uaaAuthorityMapper.insertSelective(uaaAuthority);
            }
        }
    }

    private RoleDetail convert(UaaRole role) {
        if (role == null) {
            return null;
        }
        RoleDetail roleDetail = new RoleDetail();
        roleDetail.setId(role.getId() + "");
        roleDetail.setRoleName(role.getRoleName());
        roleDetail.setRoleDescribe(role.getRoleDescribe());
        roleDetail.setAddTime(role.getAddTime());
        roleDetail.setUpdateTime(role.getUpdateTime());
        roleDetail.setBuiltInRole(role.getBuiltInRole());
        roleDetail.setExtendJson(role.getExtendJson());
        return roleDetail;
    }

    private UaaRole convert(RoleDetail roleDetail) {
        if (roleDetail == null) {
            return null;
        }
        UaaRole role = new UaaRole();
        role.setId(roleDetail.getId() == null ? null : Long.parseLong(roleDetail.getId()));
        role.setRoleName(roleDetail.getRoleName());
        role.setRoleDescribe(roleDetail.getRoleDescribe());
        role.setAddTime(roleDetail.getAddTime());
        role.setUpdateTime(roleDetail.getUpdateTime());
        role.setBuiltInRole(roleDetail.getBuiltInRole());
        role.setExtendJson(roleDetail.getExtendJson());
        return role;
    }

    private Authority convert(UaaAuthority uaaAuthority) {
        if (uaaAuthority == null) {
            return null;
        }
        Authority authority = new Authority();
        authority.setAuthorityType(AuthorityTypeEnum.valueOf(uaaAuthority.getAuthorityType()));
        authority.setTargetId(uaaAuthority.getTargetId());
        return authority;
    }
}
