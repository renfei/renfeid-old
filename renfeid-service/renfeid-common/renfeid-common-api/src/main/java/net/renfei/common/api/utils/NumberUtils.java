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
package net.renfei.common.api.utils;

/**
 * 数字工具类
 *
 * @author renfei
 */
public class NumberUtils {
    public static int parseInt(String str, int defaultValue) {
        if (str != null && str.length() != 0) {
            try {
                return Integer.parseInt(str);
            } catch (NumberFormatException var3) {
                return defaultValue;
            }
        } else {
            return defaultValue;
        }
    }

    public static float parseFloat(String str, float defaultValue) {
        if (str != null && str.length() != 0) {
            try {
                return Float.parseFloat(str);
            } catch (NumberFormatException var3) {
                return defaultValue;
            }
        } else {
            return defaultValue;
        }
    }

    public static double parseDouble(String str, double defaultValue) {
        if (str != null && str.length() != 0) {
            try {
                return Double.parseDouble(str);
            } catch (NumberFormatException var4) {
                return defaultValue;
            }
        } else {
            return defaultValue;
        }
    }
}
