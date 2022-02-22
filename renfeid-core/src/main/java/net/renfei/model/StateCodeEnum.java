package net.renfei.model;

/**
 * 响应状态码
 *
 * @author renfei
 */
public enum StateCodeEnum {
    Failure(100, "失败"),
    /**
     * 完成请求
     */
    OK(200, "成功"),
    /**
     * 用于异步时响应，已经接受到请求，正在执行
     */
    Accepted(202, "请求已经被执行，但还未完成"),
    /**
     * 服务器成功处理，但未返回内容
     */
    NoContent(204, "服务器成功处理，但未返回内容"),
    /**
     * 当服务器返回此状态时，客户端应当重新拉取用户信息
     */
    Reset(205, "服务器处理成功，用户终端应重置文档视图"),
    /**
     * 当服务器返回此状态时，客户端应当重新拉取确认各项数据是否变化
     */
    Partial(206, "服务器成功处理了部分任务"),
    /**
     * 当服务器返回此状态时，客户端应当检查所传参数正确性
     */
    BadRequest(400, "客户端请求的语法错误，服务器无法理解"),
    /**
     * 当服务器返回此状态时，客户端应要求用户登录
     */
    Unauthorized(401, "要求用户的身份认证"),
    /**
     * 当服务器返回此状态时，客户端应要求用户输入TOTP码
     */
    NeedTOTP(402, "需要TOTP双因子验证码"),
    /**
     * 操作权限不足，被服务器驳回
     */
    Forbidden(403, "权限不足，服务器理解请求客户端的请求，但是拒绝执行此请求"),
    /**
     * 服务器发生了异常
     */
    Error(500, "服务器发生了错误，服务暂时不可用"),
    /**
     * 服务器维护
     */
    Unavailable(503, "服务器可能正在维护，服务暂时不可用");

    private Integer code;
    private String describe;

    StateCodeEnum(int code, String describe) {
        this.code = code;
        this.describe = describe;
    }

    public Integer getCode() {
        return code;
    }

    public String getDescribe() {
        return describe;
    }
}
