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

import net.renfei.common.api.constant.APIResult;
import net.renfei.uaa.api.RoleService;
import net.renfei.uaa.api.entity.RoleDetail;
import net.renfei.uaa.repositories.UaaRoleMapper;
import net.renfei.uaa.repositories.entity.UaaRole;
import net.renfei.uaa.repositories.entity.UaaRoleExample;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;

/**
 * 角色服务
 *
 * @author renfei
 */
@Service
public class RoleServiceImpl implements RoleService {
    private final UaaRoleMapper uaaRoleMapper;

    public RoleServiceImpl(UaaRoleMapper uaaRoleMapper) {
        this.uaaRoleMapper = uaaRoleMapper;
    }

    @Override
    public APIResult<List<RoleDetail>> allRoleList(boolean haveBuiltInRoles) {
        UaaRoleExample example = new UaaRoleExample();
        if (!haveBuiltInRoles) {
            example.createCriteria().andBuiltInRoleEqualTo(false);
        }
        List<UaaRole> roles = uaaRoleMapper.selectByExampleWithBLOBs(example);
        List<RoleDetail> roleDetails = new ArrayList<>();
        for (UaaRole role : roles
        ) {
            roleDetails.add(convert(role));
        }
        return new APIResult<>(roleDetails);
    }

    private RoleDetail convert(UaaRole role) {
        if (role == null) {
            return null;
        }
        RoleDetail roleDetail = new RoleDetail();
        roleDetail.setId(role.getId());
        roleDetail.setRoleName(role.getRoleName());
        roleDetail.setRoleDescribe(role.getRoleDescribe());
        roleDetail.setAddTime(role.getAddTime());
        roleDetail.setUpdateTime(role.getUpdateTime());
        roleDetail.setBuiltInRole(role.getBuiltInRole());
        roleDetail.setExtendJson(role.getExtendJson());
        return roleDetail;
    }
}
