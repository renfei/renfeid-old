package net.renfei.services;

import net.renfei.domain.ip2location.IPResult;
import net.renfei.exception.IP2LocationException;

/**
 * IP2Location 服务
 *
 * @author renfei
 */
public interface IP2LocationService {
    IPResult ipQuery(String ip) throws IP2LocationException;
}
