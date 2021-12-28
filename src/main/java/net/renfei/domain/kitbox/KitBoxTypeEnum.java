package net.renfei.domain.kitbox;

/**
 * 工具箱类型
 *
 * @author renfei
 */
public enum KitBoxTypeEnum {
    /**
     * IP地址信息查询工具
     */
    NETWORK_IP(1, "IP地址信息查询工具", "IP地址信息查询工具，开放服务接口实现IP信息查询", "/kitbox/ip"),
    /**
     * 域名解析Dig查询工具
     */
    NETWORK_DIGTRACE(2, "域名解析Dig查询工具", "域名解析查询工具，开放服务接口实现dig+trace查询", "/kitbox/digtrace"),
    /**
     * 域名QPS压力测试工具
     */
    NETWORK_DNSQPSE(3, "域名QPS压力测试工具", "域名解析QPS压力测试工具，仅限测试请勿用于发动攻击", "/kitbox/dnsqps"),
    /**
     * 域名Whois查询工具
     */
    NETWORK_WHOIS(4, "域名Whois查询工具", "查询域名是否已经被注册，以及注册域名的详细信息", "/kitbox/whois"),
    /**
     * 公网IP获取工具
     */
    NETWORK_GETMYIP(5, "公网IP获取工具", "公网IP获取工具支持Linux、Windows、API", "/kitbox/getmyip"),
    /**
     * UUID/GUID在线生成工具
     */
    DEVELOP_UUID(6, "UUID/GUID在线生成工具", "在线批量生成 UUID/GUID 工具，支持大小写连词符", "/kitbox/uuid"),
    /**
     * FreeMarker在线测试工具
     */
    DEVELOP_FREEMARKER_TEST(7, "FreeMarker在线测试工具", "FreeMarker在线测试工具，在线测试Ftl文件", "/kitbox/freemarkerTest"),
    /**
     * 下划线驼峰命名互转工具
     */
    DEVELOP_STR_HUMP_LINE_CONVERT(8, "下划线驼峰命名互转工具", "下划线与驼峰命名风格的相互转换工具", "/kitbox/strHumpLineConvert"),
    /**
     * 字节(Byte)单位转换工具
     */
    DEVELOP_BYTE_UNIT_CONVERSION(9, "字节(Byte)单位转换工具", "计算机字节(Byte)单位之间的转换换算工具", "/kitbox/byteUnitConversion"),
    /**
     * UEditor富文本在线编辑器
     */
    DEVELOP_UEDITOR(10, "UEditor富文本在线编辑器", "百度团队的UEditor在线文本编辑器演示与体验", "/kitbox/ueditor"),
    /**
     * IK 在线分词工具与API
     */
    DEVELOP_WORD_IK_ANALYZE(11, "IK 在线分词工具与API", "IK Analyzer 在线分词工具与API开放接口服务", "/kitbox/wordIkAnalyze"),
    /**
     * 端口号注册列表
     */
    DEVELOP_PORT_NUMBER_LIST(12, "端口号注册列表", "计算机 TCP/UDP 端口号注册列表大全", "/kitbox/portNumberList"),
    /**
     * iOS Plist 在线生成
     */
    DEVELOP_PLIST(13, "iOS Plist 在线生成", "苹果 iOS Plist 文件在线生成制作工具", "/kitbox/plist"),
    /**
     * 随机密码生成工具
     */
    ENCRYPTION_RANDOM_PASSWORD(14, "随机密码生成工具", "根据所选的字符及密码长度，随机密码一键生成", "/kitbox/randomPassword"),
    /**
     * MD5加密工具
     */
    ENCRYPTION_MD5(15, "MD5加密工具", "MD5加密,对字符串进行MD5计算得出MD5加密字符串", "/kitbox/md5"),
    /**
     * SHA-1加密工具
     */
    ENCRYPTION_SHA1(16, "SHA-1加密工具", "SHA-1加密,对字符串进行SHA-1计算加密字符串", "/kitbox/sha1"),
    /**
     * SHA-256加密工具
     */
    ENCRYPTION_SHA256(17, "SHA-256加密工具", "SHA-256加密,对字符串进行SHA-256计算加密字符串", "/kitbox/sha256"),
    /**
     * SHA-512加密工具
     */
    ENCRYPTION_SHA512(18, "SHA-512加密工具", "SHA-512加密,对字符串进行SHA-512计算加密字符串", "/kitbox/sha512"),
    /**
     * URL16进制加密
     */
    ENCRYPTION_URL16(19, "URL16进制加密", "URL网址16进制加密工具,对网址进行16进制编码", "/kitbox/url16"),
    /**
     * 二维码生成工具
     */
    OTHER_QRCODE(20, "二维码生成工具", "免费实用的二维码图片在线生成工具", "/kitbox/qrcode"),
    /**
     * 短网址生成工具
     */
    OTHER_SHORT_URL(21, "短网址生成工具", "免费的短网址在线生成服务，缩短网址", "/kitbox/ShortURL"),
    /**
     * 站长推送工具
     */
    OTHER_INDEXING(22, "站长推送工具", "百度/必应/谷歌-站长推送工具", "/kitbox/indexing"),
    /**
     * 其他
     */
    OTHER(-1, "其他", "其他", "/kitbox/");
    private final int id;
    private final String title;
    private final String readme;
    private final String url;

    KitBoxTypeEnum(int id, String title, String readme, String url) {
        this.id = id;
        this.title = title;
        this.readme = readme;
        this.url = url;
    }

    public int getId() {
        return id;
    }

    public String getTitle() {
        return title;
    }

    public String getReadme() {
        return readme;
    }

    public String getUrl() {
        return url;
    }
}
