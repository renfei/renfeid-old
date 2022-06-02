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
package net.renfei.common.core.service.impl;

import net.renfei.common.core.service.SystemService;
import org.springframework.cloud.context.refresh.ContextRefresher;
import org.springframework.stereotype.Service;

/**
 * 系统级服务
 *
 * @author renfei
 */
@Service
public class SystemServiceImpl implements SystemService {
    private final ContextRefresher contextRefresher;

    public SystemServiceImpl(ContextRefresher contextRefresher) {
        this.contextRefresher = contextRefresher;
    }

    /**
     * 刷新系统配置（无需停机更新配置）
     */
    @Override
    public void refreshConfiguration() {
        contextRefresher.refresh();
    }
}
