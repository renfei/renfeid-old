package net.renfei.services.ip2location;

import io.sentry.Sentry;
import lombok.extern.slf4j.Slf4j;
import net.renfei.domain.ip2location.IP2Location;
import net.renfei.domain.ip2location.IPResult;
import net.renfei.exception.IP2LocationException;
import net.renfei.services.BaseService;
import net.renfei.services.IP2LocationService;
import org.springframework.context.annotation.Lazy;
import org.springframework.core.io.ClassPathResource;
import org.springframework.stereotype.Service;

import java.io.IOException;

/**
 * IP2Location 服务
 *
 * @author renfei
 */
@Lazy
@Slf4j
@Service
public class IP2LocationServiceImpl extends BaseService implements IP2LocationService {
    private final IP2Location location;
    private final IP2Location locationV6;
    private static final String CLASS_PATH_RESOURCE = "classpath:/";

    {
        location = new IP2Location();
        locationV6 = new IP2Location();
        try {
            if (SYSTEM_CONFIG.getIp2LocationBinFile().startsWith(CLASS_PATH_RESOURCE)) {
                location.Open(new ClassPathResource(
                        SYSTEM_CONFIG.getIp2LocationBinFile().split(CLASS_PATH_RESOURCE)[1]).getFile().getPath(), true);
            } else {
                location.Open(SYSTEM_CONFIG.getIp2LocationBinFile(), true);
            }
            if (SYSTEM_CONFIG.getIp2LocationBinFileV6().startsWith(CLASS_PATH_RESOURCE)) {
                locationV6.Open(new ClassPathResource(
                        SYSTEM_CONFIG.getIp2LocationBinFileV6().split(CLASS_PATH_RESOURCE)[1]).getFile().getPath(), true);
            } else {
                locationV6.Open(SYSTEM_CONFIG.getIp2LocationBinFileV6(), true);
            }
        } catch (IOException e) {
            log.error(e.getMessage(), e);
            Sentry.captureException(e);
        }
    }

    @Override
    public String ipQueryAddress(String ip) throws IP2LocationException {
        IPResult result = this.ipQuery(ip);
        return result.getCity() + ", " + result.getRegion() + ", " + result.getCountryLong();
    }

    @Override
    public IPResult ipQuery(String ip) throws IP2LocationException {
        IPResult rec;
        try {
            rec = location.IPQuery(ip);
        } catch (IOException e) {
            log.error(e.getMessage(), e);
            Sentry.captureException(e);
            throw new IP2LocationException("IP2Location 服务暂时不可用。");
        }
        assert rec != null;
        if ("OK".equals(rec.getStatus())) {
            return rec;
        } else if ("EMPTY_IP_ADDRESS".equals(rec.getStatus())) {
            throw new IP2LocationException("IP地址不能为空。");
        } else if ("INVALID_IP_ADDRESS".equals(rec.getStatus())) {
            throw new IP2LocationException("无效的IP地址。");
        } else if ("MISSING_FILE".equals(rec.getStatus())) {
            log.error("Invalid database path.");
            throw new IP2LocationException("IP2Location 服务暂时不可用。");
        } else if ("IPV6_NOT_SUPPORTED".equals(rec.getStatus())) {
            return ipQueryIpV6(ip);
        } else {
            throw new IP2LocationException("IP2Location 服务暂时不可用。" + rec.getStatus());
        }
    }

    private IPResult ipQueryIpV6(String ip) throws IP2LocationException {
        IPResult rec;
        try {
            rec = locationV6.IPQuery(ip);
        } catch (IOException e) {
            log.error(e.getMessage(), e);
            Sentry.captureException(e);
            throw new IP2LocationException("IP2Location 服务暂时不可用。");
        }
        assert rec != null;
        if ("OK".equals(rec.getStatus())) {
            return rec;
        } else if ("EMPTY_IP_ADDRESS".equals(rec.getStatus())) {
            throw new IP2LocationException("IP地址不能为空。");
        } else if ("INVALID_IP_ADDRESS".equals(rec.getStatus())) {
            throw new IP2LocationException("无效的IP地址。");
        } else if ("MISSING_FILE".equals(rec.getStatus())) {
            log.error("Invalid database path.");
            throw new IP2LocationException("IP2Location 服务暂时不可用。");
        } else if ("IPV6_NOT_SUPPORTED".equals(rec.getStatus())) {
            log.error("This BIN does not contain IPv6 data.");
            throw new IP2LocationException("IP2Location 服务暂时不支持IPv6。");
        } else {
            throw new IP2LocationException("IP2Location 服务暂时不可用。" + rec.getStatus());
        }
    }
}
