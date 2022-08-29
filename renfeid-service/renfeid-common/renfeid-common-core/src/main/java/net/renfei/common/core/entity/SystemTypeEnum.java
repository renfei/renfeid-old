package net.renfei.common.core.entity;

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
    POSTS("/posts"),
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
    SEARCH("/search"),
    /**
     * 单页模块
     */
    PAGE("/page"),
    /**
     * 微信公众号接口
     */
    WECHAT_MP(""),
    /**
     * 系统角色与权限模块
     */
    SYS_ROLE(""),
    /**
     * 系统菜单模块
     */
    SYS_MENU(""),
    /**
     * 系统模块
     */
    SYSTEM(""),
    /**
     * 系统设置模块
     */
    SYS_SETTING(""),
    /**
     * 系统定时任务模块
     */
    SYS_QUARTZ(""),
    /**
     * 系统日志
     */
    SYS_LOGS("");

    private final String uriPath;

    SystemTypeEnum(String uriPath) {
        this.uriPath = uriPath;
    }

    public String getUriPath() {
        return uriPath;
    }
}
