package net.renfei.model;

import lombok.Builder;
import lombok.Data;
import net.renfei.utils.StringUtils;
import org.springframework.util.ObjectUtils;

/**
 * API统一响应返回对象
 *
 * @author renfei
 */
@Data
@Builder
public class APIResult<T> {
    private StateCode stateCode;
    /**
     * 状态码
     */
    private Integer code;
    /**
     * 消息
     */
    private String message;
    /**
     * 时间戳
     */
    private Integer timestamp;
    /**
     * 签名，将时间戳+随机字符串字典排序后SHA1
     */
    private String signature;
    /**
     * 随机数
     */
    private String nonce;
    /**
     * 数据负载对象
     */
    private T data;

    /**
     * 私有构造防止直接实例化
     */
    private APIResult() {
        signature();
    }

    public APIResult(T data) {
        this.data = data;
        this.stateCode = StateCode.OK;
        this.code = StateCode.OK.getCode();
        this.message = StateCode.OK.getDescribe();
        signature();
    }

    public APIResult(StateCode stateCode, Integer code, String message,
                     Integer timestamp, String signature, String nonce, T data) {
        this.data = data;
        this.stateCode = stateCode;
        this.code = code;
        this.message = message;
        this.signature = signature;
        this.timestamp = timestamp;
        this.nonce = nonce;
    }

    /**
     * 直接返回成功状态
     *
     * @return APIResult
     */
    public static APIResult success() {
        APIResult apiResult = new APIResult();
        apiResult.stateCode = StateCode.OK;
        apiResult.code = StateCode.OK.getCode();
        apiResult.message = StateCode.OK.getDescribe();
        return apiResult;
    }

    public Integer getCode() {
        return code;
    }

    public String getMessage() {
        if (ObjectUtils.isEmpty(message)) {
            return stateCode.getDescribe();
        }
        return message;
    }

    public Integer getTimestamp() {
        return timestamp;
    }

    public T getData() {
        return data;
    }

    public String getSignature() {
        return signature;
    }

    public String getNonce() {
        return nonce;
    }

    private void setNonce(String nonce) {
        this.nonce = nonce;
    }

    /**
     * 对消息进行签名
     */
    private void signature() {
        this.timestamp = (int) (System.currentTimeMillis() / 1000);
        this.nonce = StringUtils.getRandomString(16);
        this.signature = StringUtils.signature(this.timestamp.toString(), this.nonce);
    }
}
