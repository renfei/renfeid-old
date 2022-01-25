package net.renfei.services;

import net.renfei.config.SystemConfig;
import net.renfei.application.ApplicationContextUtil;

/**
 * Service 基类
 *
 * @author renfei
 */
public abstract class BaseService {
    protected static final String REDIS_KEY = "renfeid:";
    protected final SystemConfig SYSTEM_CONFIG;

    {
        SYSTEM_CONFIG = (SystemConfig) ApplicationContextUtil.getBean("systemConfig");
    }
}
