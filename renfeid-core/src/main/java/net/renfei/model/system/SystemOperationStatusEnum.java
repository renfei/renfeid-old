package net.renfei.model.system;

/**
 * 系统运行状态
 *
 * @author renfei
 */
public enum SystemOperationStatusEnum {
    /**
     * 完全开放，所有功能可以正常使用
     */
    OPENED,
    /**
     * 降级运行，只接受 GET/HEAD 请求（后台接口和登陆除外）
     */
    DEGRADED,
    /**
     * 关闭状态，所有请求均被拦截（后台接口和登陆除外）
     */
    CLOSED
}
