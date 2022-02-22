package net.renfei.utils;

import java.security.MessageDigest;
import java.security.NoSuchAlgorithmException;

/**
 * @author renfei
 */
public class EncryptionUtils {
    private static final char[] HEX_DIGITS = {'0', '1', '2', '3', '4', '5',
            '6', '7', '8', '9', 'a', 'b', 'c', 'd', 'e', 'f'};

    /**
     * 加密
     *
     * @param algorithm 算法名称
     * @param body      加密的字符串
     * @return 加密过后的字符串
     */
    public static String encrypt(String algorithm, String body) {
        if (algorithm == null || body == null) {
            return null;
        }
        try {
            //这是java自带的加密工具
            MessageDigest digest = MessageDigest.getInstance(algorithm);
            digest.update(body.getBytes());
            return getFormattedText(digest.digest());
        } catch (NoSuchAlgorithmException e) {
            e.printStackTrace();
            return null;
        }
    }

    private static String getFormattedText(byte[] bytes) {
        int len = bytes.length;
        StringBuffer buf = new StringBuffer(len * 2);
        // 把密文转换成十六进制的字符串形式
        for (int j = 0; j < len; j++) {
            buf.append(HEX_DIGITS[(bytes[j] >> 4) & 0x0f]);
            buf.append(HEX_DIGITS[bytes[j] & 0x0f]);
        }
        return buf.toString();
    }
}
