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

import java.io.Serializable;
import java.util.List;

/**
 * 流程流转记录
 *
 * @author renfei
 */
public class FlowableRecord implements Serializable {
    private static final long serialVersionUID = -3316408227872898096L;
    private String processInstanceId;
    private String deployId;
    private List<FlowTaskVO> flowList;
    private Boolean finished;

    public String getProcessInstanceId() {
        return processInstanceId;
    }

    public void setProcessInstanceId(String processInstanceId) {
        this.processInstanceId = processInstanceId;
    }

    public String getDeployId() {
        return deployId;
    }

    public void setDeployId(String deployId) {
        this.deployId = deployId;
    }

    public List<FlowTaskVO> getFlowList() {
        return flowList;
    }

    public void setFlowList(List<FlowTaskVO> flowList) {
        this.flowList = flowList;
    }

    public Boolean getFinished() {
        return finished;
    }

    public void setFinished(Boolean finished) {
        this.finished = finished;
    }

    @Override
    public String toString() {
        return "FlowableRecord{" +
                "processInstanceId='" + processInstanceId + '\'' +
                ", deployId='" + deployId + '\'' +
                ", flowList=" + flowList +
                ", finished=" + finished +
                '}';
    }
}
