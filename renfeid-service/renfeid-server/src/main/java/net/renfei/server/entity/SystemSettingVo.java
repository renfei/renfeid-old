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
package net.renfei.server.entity;

import net.renfei.common.api.constant.enums.SystemSettingEnum;

import java.io.Serializable;
import java.util.List;

/**
 * 系统设置对象
 *
 * @author renfei
 */
public class SystemSettingVo implements Serializable {
    private static final long serialVersionUID = -3316408227872898096L;
    private SystemSettingEnum setting;
    private List<String> values;

    public SystemSettingEnum getSetting() {
        return setting;
    }

    public void setSetting(SystemSettingEnum setting) {
        this.setting = setting;
    }

    public List<String> getValues() {
        return values;
    }

    public void setValues(List<String> values) {
        this.values = values;
    }
}
