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
package net.renfei.common.core.service;

import net.renfei.uaa.api.entity.UserDetail;
import org.springframework.context.ApplicationContextAware;

import javax.servlet.http.HttpServletRequest;
import java.io.IOException;

/**
 * 系统级服务
 *
 * @author renfei
 */
public interface SystemService extends ApplicationContextAware {
    /**
     * 【危险】在主机上执行命令
     *
     * @param command 命令
     * @return
     * @throws IOException
     */
    String execCommand(String... command) throws IOException;

    /**
     * 刷新系统配置（无需停机更新配置）
     */
    void refreshConfiguration();

    /**
     * 【危险】关闭系统，主动退出
     */
    void shutdownSystem(HttpServletRequest request);

    /**
     * 获取当前登陆的用户详情
     *
     * @return
     */
    UserDetail currentUserDetail();
}
