package net.renfei.utils;

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
