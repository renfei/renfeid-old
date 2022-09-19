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
 * 工作流任务相关--返回参数
 *
 * @author renfei
 */
@Schema(title = "工作流任务相关--返回参数")
public class FlowTaskVO implements Serializable {
    private static final long serialVersionUID = -3316408227872898096L;
    @Schema(description = "任务编号")
    private String taskId;
    @Schema(description = "任务执行编号")
    private String executionId;
    @Schema(description = "任务名称")
    private String taskName;
    @Schema(description = "任务Key")
    private String taskDefKey;
    @Schema(description = "任务执行人Id")
    private Long assigneeId;
    @Schema(description = "部门名称")
    private String deptName;
    @Schema(description = "流程发起人部门名称")
    private String startDeptName;
    @Schema(description = "任务执行人名称")
    private String assigneeName;
    @Schema(description = "流程发起人Id")
    private String startUserId;
    @Schema(description = "流程发起人名称")
    private String startUserName;
    @Schema(description = "流程类型")
    private String category;
    @Schema(description = "流程变量信息")
    private Object procVars;
    @Schema(description = "局部变量信息")
    private Object taskLocalVars;
    @Schema(description = "流程部署编号")
    private String deployId;
    @Schema(description = "流程ID")
    private String procDefId;
    @Schema(description = "流程key")
    private String procDefKey;
    @Schema(description = "流程定义名称")
    private String procDefName;
    @Schema(description = "流程定义内置使用版本")
    private int procDefVersion;
    @Schema(description = "流程实例ID")
    private String procInsId;
    @Schema(description = "历史流程实例ID")
    private String hisProcInsId;
    @Schema(description = "任务耗时")
    private String duration;
    @Schema(description = "任务意见")
    private FlowCommentDTO comment;
    @Schema(description = "候选执行人")
    private String candidate;
    @Schema(description = "任务创建时间")
    private Date createTime;
    @Schema(description = "任务完成时间")
    private Date finishTime;

    public String getTaskId() {
        return taskId;
    }

    public void setTaskId(String taskId) {
        this.taskId = taskId;
    }

    public String getExecutionId() {
        return executionId;
    }

    public void setExecutionId(String executionId) {
        this.executionId = executionId;
    }

    public String getTaskName() {
        return taskName;
    }

    public void setTaskName(String taskName) {
        this.taskName = taskName;
    }

    public String getTaskDefKey() {
        return taskDefKey;
    }

    public void setTaskDefKey(String taskDefKey) {
        this.taskDefKey = taskDefKey;
    }

    public Long getAssigneeId() {
        return assigneeId;
    }

    public void setAssigneeId(Long assigneeId) {
        this.assigneeId = assigneeId;
    }

    public String getDeptName() {
        return deptName;
    }

    public void setDeptName(String deptName) {
        this.deptName = deptName;
    }

    public String getStartDeptName() {
        return startDeptName;
    }

    public void setStartDeptName(String startDeptName) {
        this.startDeptName = startDeptName;
    }

    public String getAssigneeName() {
        return assigneeName;
    }

    public void setAssigneeName(String assigneeName) {
        this.assigneeName = assigneeName;
    }

    public String getStartUserId() {
        return startUserId;
    }

    public void setStartUserId(String startUserId) {
        this.startUserId = startUserId;
    }

    public String getStartUserName() {
        return startUserName;
    }

    public void setStartUserName(String startUserName) {
        this.startUserName = startUserName;
    }

    public String getCategory() {
        return category;
    }

    public void setCategory(String category) {
        this.category = category;
    }

    public Object getProcVars() {
        return procVars;
    }

    public void setProcVars(Object procVars) {
        this.procVars = procVars;
    }

    public Object getTaskLocalVars() {
        return taskLocalVars;
    }

    public void setTaskLocalVars(Object taskLocalVars) {
        this.taskLocalVars = taskLocalVars;
    }

    public String getDeployId() {
        return deployId;
    }

    public void setDeployId(String deployId) {
        this.deployId = deployId;
    }

    public String getProcDefId() {
        return procDefId;
    }

    public void setProcDefId(String procDefId) {
        this.procDefId = procDefId;
    }

    public String getProcDefKey() {
        return procDefKey;
    }

    public void setProcDefKey(String procDefKey) {
        this.procDefKey = procDefKey;
    }

    public String getProcDefName() {
        return procDefName;
    }

    public void setProcDefName(String procDefName) {
        this.procDefName = procDefName;
    }

    public int getProcDefVersion() {
        return procDefVersion;
    }

    public void setProcDefVersion(int procDefVersion) {
        this.procDefVersion = procDefVersion;
    }

    public String getProcInsId() {
        return procInsId;
    }

    public void setProcInsId(String procInsId) {
        this.procInsId = procInsId;
    }

    public String getHisProcInsId() {
        return hisProcInsId;
    }

    public void setHisProcInsId(String hisProcInsId) {
        this.hisProcInsId = hisProcInsId;
    }

    public String getDuration() {
        return duration;
    }

    public void setDuration(String duration) {
        this.duration = duration;
    }

    public FlowCommentDTO getComment() {
        return comment;
    }

    public void setComment(FlowCommentDTO comment) {
        this.comment = comment;
    }

    public String getCandidate() {
        return candidate;
    }

    public void setCandidate(String candidate) {
        this.candidate = candidate;
    }

    public Date getCreateTime() {
        return createTime;
    }

    public void setCreateTime(Date createTime) {
        this.createTime = createTime;
    }

    public Date getFinishTime() {
        return finishTime;
    }

    public void setFinishTime(Date finishTime) {
        this.finishTime = finishTime;
    }

    @Override
    public String toString() {
        return "FlowTaskVO{" +
                "taskId='" + taskId + '\'' +
                ", executionId='" + executionId + '\'' +
                ", taskName='" + taskName + '\'' +
                ", taskDefKey='" + taskDefKey + '\'' +
                ", assigneeId=" + assigneeId +
                ", deptName='" + deptName + '\'' +
                ", startDeptName='" + startDeptName + '\'' +
                ", assigneeName='" + assigneeName + '\'' +
                ", startUserId='" + startUserId + '\'' +
                ", startUserName='" + startUserName + '\'' +
                ", category='" + category + '\'' +
                ", procVars=" + procVars +
                ", taskLocalVars=" + taskLocalVars +
                ", deployId='" + deployId + '\'' +
                ", procDefId='" + procDefId + '\'' +
                ", procDefKey='" + procDefKey + '\'' +
                ", procDefName='" + procDefName + '\'' +
                ", procDefVersion=" + procDefVersion +
                ", procInsId='" + procInsId + '\'' +
                ", hisProcInsId='" + hisProcInsId + '\'' +
                ", duration='" + duration + '\'' +
                ", comment=" + comment +
                ", candidate='" + candidate + '\'' +
                ", createTime=" + createTime +
                ", finishTime=" + finishTime +
                '}';
    }
}
