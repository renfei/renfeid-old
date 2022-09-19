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
public class FlowSaveXmlAO implements Serializable {
    private static final long serialVersionUID = -3316408227872898096L;
    /**
     * 流程名称
     */
    private String name;
    /**
     * 流程分类
     */
    private String category;
    /**
     * xml 文件
     */
    private String xml;

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getCategory() {
        return category;
    }

    public void setCategory(String category) {
        this.category = category;
    }

    public String getXml() {
        return xml;
    }

    public void setXml(String xml) {
        this.xml = xml;
    }

    @Override
    public String toString() {
        return "FlowSaveXmlAO{" +
                "name='" + name + '\'' +
                ", category='" + category + '\'' +
                ", xml='" + xml + '\'' +
                '}';
    }
}
