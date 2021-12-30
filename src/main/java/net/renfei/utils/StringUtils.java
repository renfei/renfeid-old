package net.renfei.utils;

import net.renfei.exception.BusinessException;
import org.springframework.util.ObjectUtils;

import java.io.UnsupportedEncodingException;
import java.math.BigInteger;
import java.net.InetAddress;
import java.net.URLDecoder;
import java.net.URLEncoder;
import java.net.UnknownHostException;
import java.util.Arrays;
import java.util.Base64;
import java.util.Random;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

/**
 * String工具类
 *
 * @author renfei
 */
public class StringUtils {
    private static final String EMAIL_REGEX = "^([a-zA-Z0-9_\\-\\.]+)@([a-zA-Z0-9_\\-\\.]+)\\.([a-zA-Z]{2,5})$";
    private static final String CHINA_PHONE_ALL = "^(?:\\+?86)?1(?:3\\d{3}|5[^4\\D]\\d{2}|8\\d{3}|7(?:[35-8]\\d{2}|4(?:0\\d|1[0-2]|9\\d))|9[0135-9]\\d{2}|66\\d{2})\\d{6}$";
    private static final String CHINA_PHONE_CHINA_MOBILE = "^(?:\\+?86)?1(?:3(?:4[^9\\D]|[5-9]\\d)|5[^3-6\\D]\\d|8[23478]\\d|[79]8\\d)\\d{7}$";
    private static final String CHINA_PHONE_CHINA_UNICOM = "^(?:\\+?86)?1(?:3[0-2]|[578][56]|66)\\d{8}$";
    private static final String CHINA_PHONE_CHINA_TELECOM = "^(?:\\+?86)?1(?:3(?:3\\d|49)\\d|53\\d{2}|8[019]\\d{2}|7(?:[37]\\d{2}|40[0-5])|9[139]\\d{2})\\d{6}$";
    private static final String CHINA_PHONE_MVNO_ALL = "^(?:\\+?86)?1(?:7[01]|6[257])\\d{8}$";
    private static final String DOMAIN_REGEX = "^(?:[a-z0-9](?:[a-z0-9-]{0,61}[a-z0-9])?\\.)+[a-z0-9][a-z0-9-]{0,61}[a-z0-9]$";
    private static final Pattern LINE_PATTERN = Pattern.compile("_(\\w)");
    private static final Pattern HUMP_PATTERN = Pattern.compile("[A-Z]");

    /**
     * 判断是否是域名格式
     *
     * @param domain
     * @return
     */
    public static boolean isDomain(String domain) {
        if (ObjectUtils.isEmpty(domain)) {
            return false;
        }
        return domain.matches(DOMAIN_REGEX);
    }

    /**
     * 判断是否是邮箱格式
     *
     * @param email
     * @return
     */
    public static boolean isEmail(String email) {
        if (ObjectUtils.isEmpty(email)) {
            return false;
        }
        return email.matches(EMAIL_REGEX);
    }

    /**
     * 判断是否是中国手机号
     *
     * @param phone
     * @return
     */
    public static boolean isChinaPhone(String phone) {
        if (ObjectUtils.isEmpty(phone)) {
            return false;
        }
        return phone.matches(CHINA_PHONE_ALL);
    }

    /**
     * 判断是否是中国移动手机号
     *
     * @param phone
     * @return
     */
    public static boolean isChinaMobilePhone(String phone) {
        if (ObjectUtils.isEmpty(phone)) {
            return false;
        }
        return phone.matches(CHINA_PHONE_CHINA_MOBILE);
    }

    /**
     * 判断是否是中国联通手机号
     *
     * @param phone
     * @return
     */
    public static boolean isChinaUnicomePhone(String phone) {
        if (ObjectUtils.isEmpty(phone)) {
            return false;
        }
        return phone.matches(CHINA_PHONE_CHINA_UNICOM);
    }

    /**
     * 判断是否是中国电信手机号
     *
     * @param phone
     * @return
     */
    public static boolean isChinaTelecomPhone(String phone) {
        if (ObjectUtils.isEmpty(phone)) {
            return false;
        }
        return phone.matches(CHINA_PHONE_CHINA_TELECOM);
    }

    /**
     * 判断是否是中国虚拟运营商手机号
     *
     * @param phone
     * @return
     */
    public static boolean isChinaMvnoPhone(String phone) {
        if (ObjectUtils.isEmpty(phone)) {
            return false;
        }
        return phone.matches(CHINA_PHONE_MVNO_ALL);
    }

    /**
     * 签名，先进行字典排序，再进行SHA1
     *
     * @param arr 参与签名的值
     * @return
     */
    public static String signature(String... arr) {
        if (ObjectUtils.isEmpty(arr)) {
            return null;
        }
        Arrays.sort(arr);
        StringBuffer sb = new StringBuffer();
        //将参数拼接成一个字符串进行sha1加密
        for (String param : arr) {
            sb.append(param);
        }
        return EncryptionUtils.encrypt("SHA1", sb.toString());
    }

    /**
     * 获取指定长度随机字符串
     *
     * @param length 长度
     * @return
     */
    public static String getRandomString(int length) {
        Random random = new Random();
        StringBuffer sb = new StringBuffer();
        for (int i = 0; i < length; i++) {
            int number = random.nextInt(3);
            long result = 0;
            switch (number) {
                case 0:
                    result = Math.round(Math.random() * 25 + 65);
                    sb.append((char) result);
                    break;
                case 1:
                    result = Math.round(Math.random() * 25 + 97);
                    sb.append((char) result);
                    break;
                case 2:
                    sb.append(new Random().nextInt(10));
                    break;
                default:
                    break;
            }
        }
        return sb.toString();
    }

    /**
     * 获取指定位数的随机数字串
     *
     * @param length 长度
     * @return
     */
    public static String getRandomNumber(int length) {
        Random r = new Random();
        StringBuffer rs = new StringBuffer();
        for (int i = 0; i < length; i++) {
            rs.append(r.nextInt(10));
        }
        return rs.toString();
    }

    public static String encodeBase64(byte[] bytes) {
        return Base64.getEncoder().encodeToString(bytes);
    }

    public static byte[] decodeBase64(String str) {
        return Base64.getDecoder().decode(str);
    }

    public static String encodeUTF8StringBase64(String str) {
        String encoded = null;
        try {
            encoded = Base64.getEncoder().encodeToString(str.getBytes("utf-8"));
        } catch (UnsupportedEncodingException e) {
        }
        return encoded;

    }

    public static String decodeUTF8StringBase64(String str) {
        String decoded = null;
        byte[] bytes = Base64.getDecoder().decode(str);
        try {
            decoded = new String(bytes, "utf-8");
        } catch (UnsupportedEncodingException e) {
        }
        return decoded;
    }

    public static String encodeURL(String url) {
        String encoded = null;
        try {
            encoded = URLEncoder.encode(url, "utf-8");
        } catch (UnsupportedEncodingException e) {
        }
        return encoded;
    }


    public static String decodeURL(String url) {
        String decoded = null;
        try {
            decoded = URLDecoder.decode(url, "utf-8");
        } catch (UnsupportedEncodingException e) {
        }
        return decoded;
    }

    public static String delHtmlTags(String htmlStr) {
        //定义script的正则表达式，去除js可以防止注入
        String scriptRegex = "<script[^>]*?>[\\s\\S]*?<\\/script>";
        //定义style的正则表达式，去除style样式，防止css代码过多时只截取到css样式代码
        String styleRegex = "<style[^>]*?>[\\s\\S]*?<\\/style>";
        //定义HTML标签的正则表达式，去除标签，只提取文字内容
        String htmlRegex = "<[^>]+>";
        //定义空格,回车,换行符,制表符
        String spaceRegex = "\\s*|\t|\r|\n";

        // 过滤script标签
        htmlStr = htmlStr.replaceAll(scriptRegex, "");
        // 过滤style标签
        htmlStr = htmlStr.replaceAll(styleRegex, "");
        // 过滤html标签
        htmlStr = htmlStr.replaceAll(htmlRegex, "");
        // 过滤空格等
        htmlStr = htmlStr.replaceAll(spaceRegex, "");
        // 返回文本字符串
        return htmlStr.trim();
    }

    /**
     * 将字符串形式的ip地址转换为BigInteger
     *
     * @param ipInString 字符串形式的ip地址
     * @return 整数形式的ip地址
     */
    public static BigInteger stringToBigInt(String ipInString) {
        ipInString = ipInString.replace(" ", "");
        byte[] bytes;
        if (ipInString.contains(":")) {
            bytes = ipv6ToBytes(ipInString);
        } else {
            bytes = ipv4ToBytes(ipInString);
        }
        return new BigInteger(bytes);
    }

    /**
     * 将整数形式的ip地址转换为字符串形式
     *
     * @param ipInBigInt 整数形式的ip地址
     * @return 字符串形式的ip地址
     */
    public static String bigIntToString(BigInteger ipInBigInt) {
        byte[] bytes = ipInBigInt.toByteArray();
        byte[] unsignedBytes = Arrays.copyOfRange(bytes, 1, bytes.length);
        if (bytes.length == 4 || bytes.length == 16) {
            unsignedBytes = bytes;
        }
        // 去除符号位
        try {
            String ip = InetAddress.getByAddress(unsignedBytes).toString();
            return ip.substring(ip.indexOf('/') + 1).trim();
        } catch (UnknownHostException e) {
            throw new BusinessException(e.getMessage());
        }
    }

    /**
     * ipv6地址转有符号byte[17]
     */
    private static byte[] ipv6ToBytes(String ipv6) {
        byte[] ret = new byte[17];
        ret[0] = 0;
        int ib = 16;
        // ipv4混合模式标记
        boolean comFlag = false;
        if (ipv6.startsWith(":")) {
            ipv6 = ipv6.substring(1);
        }
        String groups[] = ipv6.split(":");
        // 反向扫描
        for (int ig = groups.length - 1; ig > -1; ig--) {
            if (groups[ig].contains(".")) {
                // 出现ipv4混合模式
                byte[] temp = ipv4ToBytes(groups[ig]);
                ret[ib--] = temp[4];
                ret[ib--] = temp[3];
                ret[ib--] = temp[2];
                ret[ib--] = temp[1];
                comFlag = true;
            } else if ("".equals(groups[ig])) {
                // 出现零长度压缩,计算缺少的组数
                int zlg = 9 - (groups.length + (comFlag ? 1 : 0));
                // 将这些组置0
                while (zlg-- > 0) {
                    ret[ib--] = 0;
                    ret[ib--] = 0;
                }
            } else {
                int temp = Integer.parseInt(groups[ig], 16);
                ret[ib--] = (byte) temp;
                ret[ib--] = (byte) (temp >> 8);
            }
        }
        return ret;
    }

    /**
     * ipv4地址转有符号byte[5]
     */
    private static byte[] ipv4ToBytes(String ipv4) {
        byte[] ret = new byte[5];
        ret[0] = 0;
        // 先找到IP地址字符串中.的位置
        int position1 = ipv4.indexOf(".");
        int position2 = ipv4.indexOf(".", position1 + 1);
        int position3 = ipv4.indexOf(".", position2 + 1);
        // 将每个.之间的字符串转换成整型
        ret[1] = (byte) Integer.parseInt(ipv4.substring(0, position1));
        ret[2] = (byte) Integer.parseInt(ipv4.substring(position1 + 1,
                position2));
        ret[3] = (byte) Integer.parseInt(ipv4.substring(position2 + 1,
                position3));
        ret[4] = (byte) Integer.parseInt(ipv4.substring(position3 + 1));
        return ret;
    }

    /**
     * 下划线转驼峰
     *
     * @param str
     * @return
     */
    public static String lineToHump(String str) {
        str = str.toLowerCase();
        Matcher matcher = LINE_PATTERN.matcher(str);
        StringBuffer sb = new StringBuffer();
        while (matcher.find()) {
            matcher.appendReplacement(sb, matcher.group(1).toUpperCase());
        }
        matcher.appendTail(sb);
        return sb.toString();
    }

    /**
     * 驼峰转下划线
     *
     * @param str
     * @return
     */
    public static String humpToLine(String str) {
        Matcher matcher = HUMP_PATTERN.matcher(str);
        StringBuffer sb = new StringBuffer();
        while (matcher.find()) {
            matcher.appendReplacement(sb, "_" + matcher.group(0).toLowerCase());
        }
        matcher.appendTail(sb);
        return sb.toString();
    }
}
