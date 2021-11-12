package net.renfei.services;

import net.renfei.config.SystemConfig;
import net.renfei.utils.ApplicationContextUtil;

/**
 * Service 基类
 *
 * @author renfei
 */
public abstract class BaseService {
    protected final SystemConfig SYSTEM_CONFIG;

    {
        SYSTEM_CONFIG = (SystemConfig) ApplicationContextUtil.getBean("systemConfig");
    }
}
