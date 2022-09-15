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
package net.renfei.server.security;

import net.renfei.common.core.config.SystemConfig;
import net.renfei.uaa.api.entity.RoleDetail;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.security.access.PermissionEvaluator;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.stereotype.Component;

import java.io.Serializable;
import java.util.Collection;

/**
 * 权限判断
 *
 * @author renfei
 */
@Component
public class PermissionEvaluatorImpl implements PermissionEvaluator {
    private final static Logger logger = LoggerFactory.getLogger(PermissionEvaluatorImpl.class);
    private final SystemConfig systemConfig;

    public PermissionEvaluatorImpl(SystemConfig systemConfig) {
        this.systemConfig = systemConfig;
    }

    @Override
    public boolean hasPermission(Authentication authentication, Object targetDomainObject, Object permission) {
        if (authentication.isAuthenticated()) {
            if (systemConfig.getEnableSuperTubeUser()) {
                Object principal = authentication.getPrincipal();
                if (principal instanceof UserDetails) {
                    UserDetails userDetails = (UserDetails) principal;
                    if (userDetails.getUsername().equals(systemConfig.getSuperTubeUserName())) {
                        return true;
                    }
                }
            }
            Collection<? extends GrantedAuthority> authorities = authentication.getAuthorities();
            for (Object obj : authorities
            ) {
                if (obj instanceof RoleDetail) {
                    RoleDetail roleDetail = (RoleDetail) obj;
                    if (roleDetail.getAuthorityList() != null && !roleDetail.getAuthorityList().isEmpty()) {
                        return roleDetail.getAuthorityList().contains(permission.toString());
                    }
                }
            }
        }
        return false;
    }

    @Override
    public boolean hasPermission(Authentication authentication, Serializable targetId, String targetType, Object permission) {
        return false;
    }
}
