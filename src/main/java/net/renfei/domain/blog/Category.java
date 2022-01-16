package net.renfei.domain.blog;

import net.renfei.model.SecretLevelEnum;

import java.io.Serializable;

/**
 * @author renfei
 */
public class Category implements Serializable {
    private Long id;
    private String enName;
    private String zhName;
    private SecretLevelEnum secretLevelEnum;

    public Category() {
    }

    private Category(Builder builder) {
        this.id = builder.id;
        this.enName = builder.enName;
        this.zhName = builder.zhName;
        this.secretLevelEnum = builder.secretLevelEnum;
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getEnName() {
        return enName;
    }

    public void setEnName(String enName) {
        this.enName = enName;
    }

    public String getZhName() {
        return zhName;
    }

    public void setZhName(String zhName) {
        this.zhName = zhName;
    }

    public SecretLevelEnum getSecretLevelEnum() {
        return secretLevelEnum;
    }

    public void setSecretLevelEnum(SecretLevelEnum secretLevelEnum) {
        this.secretLevelEnum = secretLevelEnum;
    }

    public static Builder builder() {
        return new Builder();
    }

    public static class Builder {
        private Long id;
        private String enName;
        private String zhName;
        private SecretLevelEnum secretLevelEnum;

        public Builder id(Long id) {
            this.id = id;
            return this;
        }

        public Builder enName(String enName) {
            this.enName = enName;
            return this;
        }

        public Builder zhName(String zhName) {
            this.zhName = zhName;
            return this;
        }

        public Builder secretLevelEnum(SecretLevelEnum secretLevelEnum) {
            this.secretLevelEnum = secretLevelEnum;
            return this;
        }

        public Category build() {
            return new Category(this);
        }
    }
}
