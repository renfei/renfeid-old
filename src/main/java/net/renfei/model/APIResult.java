package net.renfei.model;

import net.renfei.utils.StringUtils;
import org.springframework.util.ObjectUtils;

/**
 * API统一响应返回对象
 *
 * @author renfei
 */
public final class APIResult<T> {
    private StateCodeEnum stateCodeEnum;
    private Integer code;
    private String message;
    private Integer timestamp;
    private String signature;
    private String nonce;
    private T data;

    private APIResult() {
        this.signature();
    }

    public APIResult(T data) {
        this.data = data;
        this.stateCodeEnum = StateCodeEnum.OK;
        this.code = StateCodeEnum.OK.getCode();
        this.message = StateCodeEnum.OK.getDescribe();
        this.signature();
    }

    private APIResult(APIResult.Builder<T> builder) {
        this.code = builder.code;
        this.message = builder.message;
        this.data = builder.data;
        this.stateCodeEnum = builder.stateCodeEnum;
        this.signature();
    }

    public static APIResult.Builder builder() {
        return new APIResult.Builder();
    }

    public static APIResult success() {
        APIResult apiResult = new APIResult();
        apiResult.stateCodeEnum = StateCodeEnum.OK;
        apiResult.code = StateCodeEnum.OK.getCode();
        apiResult.message = StateCodeEnum.OK.getDescribe();
        return apiResult;
    }

    public Integer getCode() {
        return this.code;
    }

    public String getMessage() {
        return ObjectUtils.isEmpty(this.message) ? this.stateCodeEnum.getDescribe() : this.message;
    }

    public Integer getTimestamp() {
        return this.timestamp;
    }

    public T getData() {
        return this.data;
    }

    public String getSignature() {
        return this.signature;
    }

    public String getNonce() {
        return this.nonce;
    }

    private void setNonce(String nonce) {
        this.nonce = nonce;
    }

    private void signature() {
        this.timestamp = (int)(System.currentTimeMillis() / 1000L);
        this.nonce = StringUtils.getRandomString(16);
        this.signature = StringUtils.signature(new String[]{this.timestamp.toString(), this.nonce});
    }

    public String toString() {
        return "APIResult{stateCode=" + (this.stateCodeEnum == null ? "null" : this.stateCodeEnum.toString()) + ", code=" + (this.code == null ? "null" : this.code) + ", message='" + (this.message == null ? "null" : this.message) + '\'' + ", timestamp=" + (this.timestamp == null ? "null" : this.timestamp) + ", signature='" + (this.signature == null ? "null" : this.signature) + '\'' + ", nonce='" + (this.nonce == null ? "null" : this.nonce) + '\'' + ", data=" + (this.data == null ? "null" : this.data.toString()) + '}';
    }

    public static class Builder<T> {
        private StateCodeEnum stateCodeEnum;
        private Integer code;
        private String message;
        private T data;

        public Builder() {
        }

        public APIResult.Builder code(StateCodeEnum stateCodeEnum) {
            this.stateCodeEnum = stateCodeEnum;
            this.code = stateCodeEnum.getCode();
            return this;
        }

        public APIResult.Builder message(String message) {
            this.message = message;
            return this;
        }

        public APIResult.Builder data(T data) {
            this.data = data;
            return this;
        }

        public APIResult<T> build() {
            return new APIResult(this);
        }
    }
}
