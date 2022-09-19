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
import java.util.List;
import java.util.Map;

/**
 * 流程任务数据传输对象
 *
 * @author renfei
 */
@Schema(title = "工作流任务相关--请求参数")
public class FlowTaskAO implements Serializable {
    private static final long serialVersionUID = -3316408227872898096L;
    @Schema(description = "任务Id")
    private String taskId;
    @Schema(description = "用户Id")
    private String userId;
    @Schema(description = "任务意见")
    private String comment;
    @Schema(description = "流程实例Id")
    private String instanceId;
    @Schema(description = "节点")
    private String targetKey;
    @Schema(description = "流程变量信息")
    private Map<String, Object> values;
    @Schema(description = "审批人")
    private String assignee;
    @Schema(description = "候选人")
    private List<String> candidateUsers;
    @Schema(description = "审批组")
    private List<String> candidateGroups;
    @Schema(description = "审批人ID")
    private String assigneeUserId;

    public String getTaskId() {
        return taskId;
    }

    public void setTaskId(String taskId) {
        this.taskId = taskId;
    }

    public String getUserId() {
        return userId;
    }

    public void setUserId(String userId) {
        this.userId = userId;
    }

    public String getComment() {
        return comment;
    }

    public void setComment(String comment) {
        this.comment = comment;
    }

    public String getInstanceId() {
        return instanceId;
    }

    public void setInstanceId(String instanceId) {
        this.instanceId = instanceId;
    }

    public String getTargetKey() {
        return targetKey;
    }

    public void setTargetKey(String targetKey) {
        this.targetKey = targetKey;
    }

    public Map<String, Object> getValues() {
        return values;
    }

    public void setValues(Map<String, Object> values) {
        this.values = values;
    }

    public String getAssignee() {
        return assignee;
    }

    public void setAssignee(String assignee) {
        this.assignee = assignee;
    }

    public List<String> getCandidateUsers() {
        return candidateUsers;
    }

    public void setCandidateUsers(List<String> candidateUsers) {
        this.candidateUsers = candidateUsers;
    }

    public List<String> getCandidateGroups() {
        return candidateGroups;
    }

    public void setCandidateGroups(List<String> candidateGroups) {
        this.candidateGroups = candidateGroups;
    }

    public String getAssigneeUserId() {
        return assigneeUserId;
    }

    public void setAssigneeUserId(String assigneeUserId) {
        this.assigneeUserId = assigneeUserId;
    }

    @Override
    public String toString() {
        return "FlowTaskDTO{" +
                "taskId='" + taskId + '\'' +
                ", userId='" + userId + '\'' +
                ", comment='" + comment + '\'' +
                ", instanceId='" + instanceId + '\'' +
                ", targetKey='" + targetKey + '\'' +
                ", values=" + values +
                ", assignee='" + assignee + '\'' +
                ", candidateUsers=" + candidateUsers +
                ", candidateGroups=" + candidateGroups +
                ", assigneeUserId='" + assigneeUserId + '\'' +
                '}';
    }
}
