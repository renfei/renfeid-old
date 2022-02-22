package net.renfei.utils;

import org.apache.commons.codec.binary.Base32;
import org.apache.commons.codec.binary.Base64;

import javax.crypto.Mac;
import javax.crypto.spec.SecretKeySpec;
import java.security.InvalidKeyException;
import java.security.NoSuchAlgorithmException;
import java.security.SecureRandom;

/**
 * 谷歌身份验证器（TOTP）
 *
 * @author RenFei
 */
public class GoogleAuthenticator {
    private static final int SECRET_SIZE = 10;
    private static final String RANDOM_NUMBER_ALGORITHM = "SHA1PRNG";
    /**
     * default 3 - max 17 (from google docs)最多可偏移的时间
     */
    private static int windowSize = 5;

    public static void setWindowSize(int s) {
        if (s >= 1 && s <= 17) {
            windowSize = s;
        }
    }

    /**
     * 验证身份验证码是否正确
     *
     * @param codes       输入的身份验证码
     * @param savedSecret 密钥
     * @return
     */
    public static Boolean authcode(String codes, String savedSecret) {
        long code = 0;
        try {
            code = Long.parseLong(codes);
        } catch (Exception e) {
            return false;
        }
        long t = System.currentTimeMillis();
        return checkCode(savedSecret, code, t);
    }

    /**
     * 获取TOTP串
     *
     * @param serverName 服务商名称
     * @param userName   用户名称
     * @param secret     用户密钥
     * @return TOTP串
     */
    public static String genTotpString(String serverName, String userName, String secret) {
        String format = "otpauth://totp/%s:%s?secret=%s&issuer=RenFei.Net";
        return String.format(format, serverName, userName, secret);
    }

    /**
     * 生成用户秘钥
     *
     * @param serverSeed 服务器给出的种子因素
     * @return
     * @throws NoSuchAlgorithmException
     */
    public static String generateSecretKey(String serverSeed) {
        SecureRandom sr = null;
        try {
            sr = SecureRandom.getInstance(RANDOM_NUMBER_ALGORITHM);
            sr.setSeed(Base64.decodeBase64(serverSeed));
            byte[] buffer = sr.generateSeed(SECRET_SIZE);
            Base32 codec = new Base32();
            byte[] bEncodedKey = codec.encode(buffer);
            return new String(bEncodedKey);
        } catch (Exception e) {
            return "";
        }
    }

    private static boolean checkCode(String secret, long code, long timeMsec) {
        Base32 codec = new Base32();
        byte[] decodedKey = codec.decode(secret);
        long t = (timeMsec / 1000L) / 30L;
        for (int i = -windowSize; i <= windowSize; ++i) {
            long hash;
            try {
                hash = verifyCode(decodedKey, t + i);
            } catch (Exception e) {
                throw new RuntimeException(e.getMessage());
            }
            if (hash == code) {
                return true;
            }
        }
        return false;
    }

    private static int verifyCode(byte[] key, long t)
            throws NoSuchAlgorithmException, InvalidKeyException {
        byte[] data = new byte[8];
        long value = t;
        for (int i = 8; i-- > 0; value >>>= 8) {
            data[i] = (byte) value;
        }
        SecretKeySpec signKey = new SecretKeySpec(key, "HmacSHA1");
        Mac mac = Mac.getInstance("HmacSHA1");
        mac.init(signKey);
        byte[] hash = mac.doFinal(data);
        int offset = hash[20 - 1] & 0xF;
        long truncatedHash = 0;
        for (int i = 0; i < 4; ++i) {
            truncatedHash <<= 8;
            truncatedHash |= (hash[offset + i] & 0xFF);
        }
        truncatedHash &= 0x7FFFFFFF;
        truncatedHash %= 1000000;
        return (int) truncatedHash;
    }
}
