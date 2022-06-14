/*
 *   Copyright 2022 RenFei(i@renfei.net)
 *
 * Licensed under the Apache License, Version 2.0 (the "License");
 * you may not use this file except in compliance with the License.
 * You may obtain a copy of the License at
 *
 *   http://www.apache.org/licenses/LICENSE-2.0
 *
 * Unless required by applicable law or agreed to in writing, software
 * distributed under the License is distributed on an "AS IS" BASIS,
 * WITHOUT WARRANTIES OR CONDITIONS OF ANY KIND, either express or implied.
 * See the License for the specific language governing permissions and
 * limitations under the License.
 */
package net.renfei.common.api.constant.enums;

/**
 * 状态码
 *
 * @author renfei
 */
public enum StateCodeEnum {
    /**
     * 执行失败
     */
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
     * 所请求的资源未修改
     */
    NotModified(304, "所请求的资源未修改"),
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
     * 访问的资源不存在
     */
    NotFound(404, "访问的资源不存在"),
    /**
     * 客户端请求中的方法被禁止
     */
    MethodNotAllowed(405, "客户端请求中的方法被禁止"),
    /**
     * 由于之前的某个请求发生的错误，导致当前请求失败
     */
    FailedDependency(424, "由于之前的某个请求发生的错误，导致当前请求失败"),
    /**
     * 服务器发生了异常
     */
    Error(500, "服务器发生了错误，服务暂时不可用"),
    /**
     * 服务器维护
     */
    Unavailable(503, "服务器可能正在维护，服务暂时不可用"),
    NeedPostPassword(1403, "内容受到密码保护，需要输入密码访问");

    private final Integer code;
    private final String describe;

    StateCodeEnum(int code, String describe) {
        this.code = code;
        this.describe = describe;
    }

    public static StateCodeEnum valueOf(int value) {
        for (StateCodeEnum stateCode : StateCodeEnum.values()
        ) {
            if (stateCode.getCode() == value) {
                return stateCode;
            }
        }
        return Failure;
    }

    public Integer getCode() {
        return code;
    }

    public String getDescribe() {
        return describe;
    }
}
