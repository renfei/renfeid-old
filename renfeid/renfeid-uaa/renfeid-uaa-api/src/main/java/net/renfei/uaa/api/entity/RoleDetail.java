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
package net.renfei.uaa.api.entity;

import io.swagger.v3.oas.annotations.media.Schema;
import org.springframework.security.access.ConfigAttribute;
import org.springframework.security.core.GrantedAuthority;

import java.io.Serializable;
import java.util.Date;
import java.util.List;

/**
 * 角色信息
 *
 * @author renfei
 */
@Schema(title = "角色对象")
public class RoleDetail implements ConfigAttribute, GrantedAuthority, Serializable {
    private static final long serialVersionUID = -5194970536302876575L;
    @Schema(description = "角色ID")
    private Long id;
    @Schema(description = "角色名称")
    private String roleName;
    @Schema(description = "角色描述")
    private String roleDescribe;
    @Schema(description = "添加时间")
    private Date addTime;
    @Schema(description = "更新时间")
    private Date updateTime;
    @Schema(description = "是否是内置角色")
    private Boolean builtInRole;
    @Schema(description = "扩展预留")
    private String extendJson;

    @Schema(description = "拥有的权限列表")
    private List<Authority> authorityList;

    @Override
    public String getAttribute() {
        return roleName;
    }

    @Override
    public String getAuthority() {
        return roleName;
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getRoleName() {
        return roleName;
    }

    public void setRoleName(String roleName) {
        this.roleName = roleName;
    }

    public String getRoleDescribe() {
        return roleDescribe;
    }

    public void setRoleDescribe(String roleDescribe) {
        this.roleDescribe = roleDescribe;
    }

    public Date getAddTime() {
        return addTime;
    }

    public void setAddTime(Date addTime) {
        this.addTime = addTime;
    }

    public Date getUpdateTime() {
        return updateTime;
    }

    public void setUpdateTime(Date updateTime) {
        this.updateTime = updateTime;
    }

    public Boolean getBuiltInRole() {
        return builtInRole;
    }

    public void setBuiltInRole(Boolean builtInRole) {
        this.builtInRole = builtInRole;
    }

    public String getExtendJson() {
        return extendJson;
    }

    public void setExtendJson(String extendJson) {
        this.extendJson = extendJson;
    }

    public List<Authority> getAuthorityList() {
        return authorityList;
    }

    public void setAuthorityList(List<Authority> authorityList) {
        this.authorityList = authorityList;
    }
}
