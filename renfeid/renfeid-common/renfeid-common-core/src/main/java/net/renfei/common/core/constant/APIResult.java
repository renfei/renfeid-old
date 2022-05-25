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
package net.renfei.common.core.constant;

import net.renfei.common.core.constant.enums.StateCodeEnum;
import net.renfei.common.core.utils.StringUtils;

/**
 * @author renfei
 */
public class APIResult<T> {
    private StateCodeEnum stateCode;
    private Integer code;
    private String message;
    private Integer timestamp;
    private String signature;
    private String nonce;
    private T data;

    private APIResult() {
        this.signature();
    }

    private APIResult(Builder<T> builder) {
        this.code = builder.code;
        this.message = builder.message;
        this.data = builder.data;
        this.stateCode = builder.stateCode;
        this.signature();
    }

    public static Builder builder() {
        return new Builder();
    }

    public static APIResult success() {
        APIResult apiResult = new APIResult();
        apiResult.stateCode = StateCodeEnum.OK;
        apiResult.code = StateCodeEnum.OK.getCode();
        apiResult.message = StateCodeEnum.OK.getDescribe();
        return apiResult;
    }

    public Integer getCode() {
        return this.code;
    }

    public String getMessage() {
        return this.message == null || "".equals(this.message) ? this.stateCode.getDescribe() : this.message;
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
        this.timestamp = (int) (System.currentTimeMillis() / 1000L);
        this.nonce = StringUtils.getRandomString(16);
        this.signature = StringUtils.signature(this.timestamp.toString(), this.nonce);
    }

    public static class Builder<T> {
        private StateCodeEnum stateCode;
        private Integer code;
        private String message;
        private T data;

        public Builder() {
        }

        public Builder code(StateCodeEnum stateCode) {
            this.stateCode = stateCode;
            this.code = stateCode.getCode();
            return this;
        }

        public Builder message(String message) {
            this.message = message;
            return this;
        }

        public Builder data(T data) {
            this.data = data;
            return this;
        }

        public APIResult<T> build() {
            return new APIResult(this);
        }
    }
}
