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

/**
 * @author renfei
 */
public class FlowViewerDTO implements Serializable {
    private static final long serialVersionUID = -3316408227872898096L;
    /**
     * 流程key
     */
    private String key;

    /**
     * 是否完成(已经审批)
     */
    private Boolean completed;

    public String getKey() {
        return key;
    }

    public void setKey(String key) {
        this.key = key;
    }

    public Boolean getCompleted() {
        return completed;
    }

    public void setCompleted(Boolean completed) {
        this.completed = completed;
    }

    @Override
    public String toString() {
        return "FlowViewerDTO{" +
                "key='" + key + '\'' +
                ", completed=" + completed +
                '}';
    }
}
