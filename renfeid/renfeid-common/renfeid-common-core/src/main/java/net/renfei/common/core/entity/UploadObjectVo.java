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
package net.renfei.common.core.entity;

import io.swagger.v3.oas.annotations.media.Schema;
import lombok.Data;

/**
 * 上传响应对象
 *
 * @author renfei
 */
@Data
@Schema(title = "上传响应对象")
public class UploadObjectVo {
    @Schema(description = "对象地址")
    private String location;
    @Schema(description = "原始文件名")
    private String alt;
}
