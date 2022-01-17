package net.renfei.services;

import net.renfei.services.leaf.common.Result;

/**
 * 美团开源的 Leaf 全局发号服务
 *
 * @author renfei
 */
public interface LeafService {
    /**
     * 发号服务
     *
     * @return
     */
    Result getId();

    /**
     * 发号服务
     *
     * @param key
     * @return
     */
    Result getId(String key);
}
