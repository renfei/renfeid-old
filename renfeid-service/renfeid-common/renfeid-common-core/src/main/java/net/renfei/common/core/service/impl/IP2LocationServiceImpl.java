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

import net.renfei.common.api.exception.BusinessException;
import net.renfei.common.api.exception.IP2LocationException;
import net.renfei.common.core.config.SystemConfig;
import net.renfei.common.core.service.IP2LocationService;
import net.renfei.ip2location.IP2Location;
import net.renfei.ip2location.IPResult;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.core.io.ClassPathResource;
import org.springframework.stereotype.Service;

import java.io.IOException;

/**
 * IP地理位置服务
 *
 * @author renfei
 */
@Service
public class IP2LocationServiceImpl implements IP2LocationService {
    private static final Logger logger = LoggerFactory.getLogger(IP2LocationServiceImpl.class);
    private final IP2Location location;
    private final IP2Location locationV6;
    private static final String CLASS_PATH_RESOURCE = "classpath";

    public IP2LocationServiceImpl(SystemConfig systemConfig) {
        location = new IP2Location();
        locationV6 = new IP2Location();
        try {
            if (systemConfig.getIp2LocationBinFile().startsWith(CLASS_PATH_RESOURCE)) {
                location.Open(new ClassPathResource(
                        systemConfig.getIp2LocationBinFile().split(CLASS_PATH_RESOURCE)[1]).getFile().getPath(), true);
            } else {
                location.Open(systemConfig.getIp2LocationBinFile(), true);
            }
            if (systemConfig.getIp2LocationBinFileV6().startsWith(CLASS_PATH_RESOURCE)) {
                locationV6.Open(new ClassPathResource(
                        systemConfig.getIp2LocationBinFileV6().split(CLASS_PATH_RESOURCE)[1]).getFile().getPath(), true);
            } else {
                locationV6.Open(systemConfig.getIp2LocationBinFileV6(), true);
            }
        } catch (IOException e) {
            logger.error(e.getMessage(), e);
        }
    }
    @Override
    public String ipQueryAddress(String ip) {
        IPResult result = this.ipQuery(ip);
        return result.getCity() + ", " + result.getRegion() + ", " + result.getCountryLong();
    }

    @Override
    public IPResult ipQuery(String ip) {
        IPResult rec;
        try {
            rec = location.IPQuery(ip);
        } catch (IOException e) {
            logger.error(e.getMessage(), e);
            throw new IP2LocationException("IP2Location 服务暂时不可用。");
        }
        assert rec != null;
        if ("OK".equals(rec.getStatus())) {
            return rec;
        } else if ("EMPTY_IP_ADDRESS".equals(rec.getStatus())) {
            throw new BusinessException("IP地址不能为空。");
        } else if ("INVALID_IP_ADDRESS".equals(rec.getStatus())) {
            throw new BusinessException("无效的IP地址。");
        } else if ("MISSING_FILE".equals(rec.getStatus())) {
            logger.error("Invalid database path.");
            throw new IP2LocationException("IP2Location 服务暂时不可用。");
        } else if ("IPV6_NOT_SUPPORTED".equals(rec.getStatus())) {
            return ipQueryIpV6(ip);
        } else {
            throw new IP2LocationException("IP2Location 服务暂时不可用。" + rec.getStatus());
        }
    }

    private IPResult ipQueryIpV6(String ip) {
        IPResult rec;
        try {
            rec = locationV6.IPQuery(ip);
        } catch (IOException e) {
            logger.error(e.getMessage(), e);
            throw new IP2LocationException("IP2Location 服务暂时不可用。");
        }
        assert rec != null;
        if ("OK".equals(rec.getStatus())) {
            return rec;
        } else if ("EMPTY_IP_ADDRESS".equals(rec.getStatus())) {
            throw new BusinessException("IP地址不能为空。");
        } else if ("INVALID_IP_ADDRESS".equals(rec.getStatus())) {
            throw new BusinessException("无效的IP地址。");
        } else if ("MISSING_FILE".equals(rec.getStatus())) {
            logger.error("Invalid database path.");
            throw new IP2LocationException("IP2Location 服务暂时不可用。");
        } else if ("IPV6_NOT_SUPPORTED".equals(rec.getStatus())) {
            logger.error("This BIN does not contain IPv6 data.");
            throw new IP2LocationException("IP2Location 服务暂时不支持IPv6。");
        } else {
            throw new IP2LocationException("IP2Location 服务暂时不可用。" + rec.getStatus());
        }
    }
}
