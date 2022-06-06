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

import com.sankuai.inf.leaf.IDGen;
import com.sankuai.inf.leaf.common.Result;
import com.sankuai.inf.leaf.snowflake.SnowflakeIDGenImpl;
import net.renfei.common.core.config.SystemConfig;
import net.renfei.common.core.service.SnowflakeService;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Service;

/**
 * 分布式ID发号器
 *
 * @author renfei
 */
@Service
public class SnowflakeServiceImpl implements SnowflakeService {
    private final static Logger logger = LoggerFactory.getLogger(SnowflakeServiceImpl.class);
    private final IDGen idGen;

    public SnowflakeServiceImpl(SystemConfig systemConfig) {
        idGen = new SnowflakeIDGenImpl(systemConfig.getLeaf().getZk(), systemConfig.getLeaf().getPort());
        if (idGen.init()) {
            logger.info("Snowflake Service Init Successfully");
        } else {
            logger.error("Snowflake Service Init Fail");
            throw new RuntimeException("Snowflake Service Init Fail");
        }
    }

    @Override
    public Result getId(String key) {
        return idGen.get(key);
    }
}
