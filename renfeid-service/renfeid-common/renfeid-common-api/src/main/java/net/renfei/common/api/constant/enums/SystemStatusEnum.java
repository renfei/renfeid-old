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
package net.renfei.common.api.constant.enums;

/**
 * 系统运行状态
 *
 * @author renfei
 */
public enum SystemStatusEnum {
    /**
     * 完全开放状态
     */
    OPENED,
    /**
     * 只读状态，只允许以下请求方法：OPTIONS、HEAD、GET
     */
    READONLY,
    /**
     * 关闭状态，只返回闭站页面
     */
    CLOSED
}
