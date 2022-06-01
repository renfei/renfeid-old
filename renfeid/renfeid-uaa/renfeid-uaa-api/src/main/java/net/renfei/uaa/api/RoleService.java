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
package net.renfei.uaa.api;

import net.renfei.common.api.constant.APIResult;
import net.renfei.uaa.api.entity.RoleDetail;

import java.util.List;

/**
 * 角色服务
 *
 * @author renfei
 */
public interface RoleService {
    /**
     * 获取全部角色列表
     *
     * @param haveBuiltInRoles 是否包含内置角色
     * @return
     */
    APIResult<List<RoleDetail>> allRoleList(boolean haveBuiltInRoles);
}
