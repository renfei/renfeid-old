package net.renfei.domain.system;

/**
 * 子系统类型
 *
 * @author renfei
 */
public enum SystemTypeEnum {
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
    KITBOX("/kitbox");

    private final String uriPath;

    SystemTypeEnum(String uriPath) {
        this.uriPath = uriPath;
    }

    public String getUriPath() {
        return uriPath;
    }
}
