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
package net.renfei.common.core.annotation;

import net.renfei.common.core.entity.LogLevelEnum;
import net.renfei.common.core.entity.OperationTypeEnum;
import net.renfei.common.core.entity.SystemTypeEnum;

import java.lang.annotation.*;

/**
 * 操作日志记录注解
 *
 * @author renfei
 */
@Documented
@Target(ElementType.METHOD)
@Retention(RetentionPolicy.RUNTIME)
public @interface OperationLog {
    /**
     * 日志等级
     *
     * @return
     */
    LogLevelEnum leve() default LogLevelEnum.INFO;

    /**
     * 子系统类型
     *
     * @return
     */
    SystemTypeEnum module();

    /**
     * 操作类型
     *
     * @return
     */
    OperationTypeEnum operation() default OperationTypeEnum.RETRIEVE;

    /**
     * 详细信息
     *
     * @return
     */
    String desc() default "";
}
