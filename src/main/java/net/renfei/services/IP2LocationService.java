package net.renfei.services;

import net.renfei.exception.IP2LocationException;
import net.renfei.ip2location.IPResult;

/**
 * IP2Location 服务
 *
 * @author renfei
 */
public interface IP2LocationService {
    String ipQueryAddress(String ip) throws IP2LocationException;
    IPResult ipQuery(String ip) throws IP2LocationException;
}
