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

import net.renfei.common.core.config.SystemConfig;
import net.renfei.uaa.api.entity.RoleDetail;
import net.renfei.uaa.api.entity.UserDetail;
import org.springframework.stereotype.Service;

/**
 * @author renfei
 */
@Service
public class UaaUtilService {
    private final SystemConfig systemConfig;

    public UaaUtilService(SystemConfig systemConfig) {
        this.systemConfig = systemConfig;
    }

    /**
     * 是否是超级管理员
     *
     * @param userDetail
     * @return
     */
    public boolean isSuperTubeUser(UserDetail userDetail) {
        return systemConfig.getEnableSuperTubeUser() && systemConfig.getSuperTubeUserName().equals(userDetail.getUsername());
    }

    /**
     * 是否是安全保密管理员
     *
     * @param userDetail
     * @return
     */
    public boolean isSecuritySuperUser(UserDetail userDetail) {
        for (RoleDetail roleDetail : userDetail.getRoleDetailList()
        ) {
            if (systemConfig.getSecuritySuperRoleName().equals(roleDetail.getRoleName())) {
                return true;
            }
        }
        return false;
    }

    /**
     * 是否是安全审计管理员
     *
     * @param userDetail
     * @return
     */
    public boolean isAuditSuperUser(UserDetail userDetail) {
        for (RoleDetail roleDetail : userDetail.getRoleDetailList()
        ) {
            if (systemConfig.getAuditSuperRoleName().equals(roleDetail.getRoleName())) {
                return true;
            }
        }
        return false;
    }
}
