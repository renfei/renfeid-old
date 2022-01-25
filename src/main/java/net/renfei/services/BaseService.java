package net.renfei.services;

import net.renfei.config.SystemConfig;
import org.springframework.beans.factory.annotation.Autowired;

/**
 * Service 基类
 *
 * @author renfei
 */
public abstract class BaseService {
    protected static final String REDIS_KEY = "renfeid:";
    @Autowired
    protected SystemConfig systemConfig;
}
