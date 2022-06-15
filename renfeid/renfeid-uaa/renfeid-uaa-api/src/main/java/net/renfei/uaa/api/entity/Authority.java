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

import java.io.Serializable;

/**
 * 权限
 *
 * @author renfei
 */
@Schema(title = "权限对象")
public class Authority implements Serializable {
    private static final long serialVersionUID = -3316408227872898096L;
    @Schema(description = "权限类型")
    private AuthorityTypeEnum authorityType;
    @Schema(description = "权限详情ID")
    private Long targetId;

    public AuthorityTypeEnum getAuthorityType() {
        return authorityType;
    }

    public void setAuthorityType(AuthorityTypeEnum authorityType) {
        this.authorityType = authorityType;
    }

    public Long getTargetId() {
        return targetId;
    }

    public void setTargetId(Long targetId) {
        this.targetId = targetId;
    }
}
