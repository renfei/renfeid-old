package net.renfei.model.system;

/**
 * 子系统类型
 *
 * @author renfei
 */
public enum SystemTypeEnum {
    /**
     * 首页
     */
    HOME("/"),
    /**
     * API开放接口
     */
    API("/api"),
    /**
     * 账号模块
     */
    ACCOUNT(""),
    /**
     * 认证模块
     */
    AUTH(""),
    /**
     * 评论系统
     */
    COMMENT(""),
    /**
     * 博客类
     */
    BLOG("/posts"),
    /**
     * 相册类
     */
    ALBUM("/photo"),
    /**
     * 微博类
     */
    WEIBO("/weibo"),
    /**
     * 工具箱类
     */
    KITBOX("/kitbox"),
    /**
     * 在线文档
     */
    DOCS("/docs"),
    /**
     * 站内搜索
     */
    SEARCH("/search");

    private final String uriPath;

    SystemTypeEnum(String uriPath) {
        this.uriPath = uriPath;
    }

    public String getUriPath() {
        return uriPath;
    }
}
