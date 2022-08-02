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
package net.renfei.cms.api.entity;

import io.swagger.v3.oas.annotations.media.Schema;

import java.io.Serializable;

/**
 * 标签
 *
 * @author renfei
 */
@Schema(title = "文章标签对象")
public class Tag implements Serializable {
    private static final long serialVersionUID = -3316408227872898096L;
    @Schema(description = "ID")
    private String id;
    @Schema(description = "用于访问的英文名")
    private String enName;
    @Schema(description = "用于显示的中文名")
    private String zhName;

    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }

    public String getEnName() {
        return enName;
    }

    public void setEnName(String enName) {
        this.enName = enName;
    }

    public String getZhName() {
        return zhName;
    }

    public void setZhName(String zhName) {
        this.zhName = zhName;
    }
}
