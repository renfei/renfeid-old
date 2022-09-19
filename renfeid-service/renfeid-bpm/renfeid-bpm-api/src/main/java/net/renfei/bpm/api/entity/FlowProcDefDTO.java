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
package net.renfei.bpm.api.entity;

import io.swagger.v3.oas.annotations.media.Schema;

import java.io.Serializable;
import java.util.Date;

/**
 * 流程定义数据传输对象
 *
 * @author renfei
 */
@Schema(title = "流程定义数据传输对象")
public class FlowProcDefDTO implements Serializable {
    private static final long serialVersionUID = -3316408227872898096L;
    @Schema(description = "流程id")
    private String id;
    @Schema(description = "流程名称")
    private String name;
    @Schema(description = "流程key")
    private String flowKey;
    @Schema(description = "流程分类")
    private String category;
    @Schema(description = "版本")
    private Integer version;
    @Schema(description = "部署ID")
    private String deploymentId;
    @Schema(description = "流程定义状态: 1:激活 , 2:中止")
    private Integer suspensionState;
    @Schema(description = "部署时间")
    private Date deploymentTime;

    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getFlowKey() {
        return flowKey;
    }

    public void setFlowKey(String flowKey) {
        this.flowKey = flowKey;
    }

    public String getCategory() {
        return category;
    }

    public void setCategory(String category) {
        this.category = category;
    }

    public Integer getVersion() {
        return version;
    }

    public void setVersion(Integer version) {
        this.version = version;
    }

    public String getDeploymentId() {
        return deploymentId;
    }

    public void setDeploymentId(String deploymentId) {
        this.deploymentId = deploymentId;
    }

    public Integer getSuspensionState() {
        return suspensionState;
    }

    public void setSuspensionState(Integer suspensionState) {
        this.suspensionState = suspensionState;
    }

    public Date getDeploymentTime() {
        return deploymentTime;
    }

    public void setDeploymentTime(Date deploymentTime) {
        this.deploymentTime = deploymentTime;
    }

    @Override
    public String toString() {
        return "FlowProcDefDTO{" +
                "id='" + id + '\'' +
                ", name='" + name + '\'' +
                ", flowKey='" + flowKey + '\'' +
                ", category='" + category + '\'' +
                ", version=" + version +
                ", deploymentId='" + deploymentId + '\'' +
                ", suspensionState=" + suspensionState +
                ", deploymentTime=" + deploymentTime +
                '}';
    }
}
