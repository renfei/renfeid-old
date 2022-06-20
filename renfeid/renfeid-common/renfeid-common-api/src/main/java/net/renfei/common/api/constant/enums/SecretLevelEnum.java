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
 * 保密等级
 * 绝密不能使用这套系统，所以没有绝密等级
 *
 * @author renfei
 */
public enum SecretLevelEnum {
    /**
     * 非密
     */
    UNCLASSIFIED(0),
    /**
     * 内部
     */
    INTERNAL(1),
    /**
     * 秘密
     */
    SECRET(2),
    /**
     * 机密
     */
    CONFIDENTIAL(3);

    private final int LEVEL;

    SecretLevelEnum(int level) {
        this.LEVEL = level;
    }

    public int getLevel() {
        return LEVEL;
    }

    public static SecretLevelEnum valueOf(int level) {
        switch (level) {
            case 2:
                return SECRET;
            case 3:
                return CONFIDENTIAL;
            default:
                return UNCLASSIFIED;
        }
    }

    /**
     * 是否超越保密等级
     *
     * @param source 当前用户持有的保密等级
     * @param target 需要符合的保密等级
     * @return
     */
    public static boolean outOfSecretLevel(SecretLevelEnum source, SecretLevelEnum target) {
        return target.getLevel() > source.getLevel();
    }
}
