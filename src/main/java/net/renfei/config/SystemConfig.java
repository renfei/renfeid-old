package net.renfei.config;

import org.springframework.boot.context.properties.ConfigurationProperties;
import org.springframework.context.annotation.Configuration;
import org.springframework.core.annotation.Order;

import java.util.List;

/**
 * 自定义配置
 * 来自 application.yml 配置文件
 *
 * @author renfei
 */
@Order(0)
@Configuration
@ConfigurationProperties(prefix = "system")
public class SystemConfig {
    /**
     * Session 认证模式
     */
    public static final String SESSION_AUTH_MODE = "SESSION";
    /**
     * ReCaptcha 容忍最大阈值
     */
    public static final Float RE_CAPTCHA_MIN_SOURCE = 0.6F;
    /**
     * 允许最大长度用户名
     */
    public static final Integer MAX_USERNAME_LENGTH = 60;
    /**
     * 允许最大评论长度
     */
    public static final Integer MAX_COMMENT_LENGTH = 10240;
    /**
     * 允许最大链接长度
     */
    public static final Integer MAX_LINK_LENGTH = 300;
    private String active;
    private String version;
    private String buildTime;
    private String baseDomainName;
    private String siteDomainName;
    private String staticDomain;
    private boolean enableRedis;
    private String superTubeUserName;
    private String authMode;
    private String globalAesKey;
    private String leafKey;
    private String ownerUserName;
    private String siteName;
    private List<String> footerSmallMenu;
    private PageHead pageHead;
    private PageFooter pageFooter;
    private boolean showFriendlyLink;
    private String totpSecret;
    private Long defaultCacheSeconds;
    private String ip2LocationBinFile;
    private String ip2LocationBinFileV6;
    private Leaf leaf;
    private List<String> authIgnore;
    private ThreadPool threadPool;
    private Aliyun aliyun;
    private WeChat weChat;
    private Baidu baidu;
    private Google google;
    private Jwt jwt;
    private UCenter uCenter;

    public static class PageHead {
        private String author;
        private String copyright;
        private List<String> dnsPrefetch;
        private String fbAppId;
        private String fbPages;
        private String favicon;
        private String appleTouchIcon;
        private List<String> css;

        public String getAuthor() {
            return author;
        }

        public void setAuthor(String author) {
            this.author = author;
        }

        public String getCopyright() {
            return copyright;
        }

        public void setCopyright(String copyright) {
            this.copyright = copyright;
        }

        public List<String> getDnsPrefetch() {
            return dnsPrefetch;
        }

        public void setDnsPrefetch(List<String> dnsPrefetch) {
            this.dnsPrefetch = dnsPrefetch;
        }

        public String getFbAppId() {
            return fbAppId;
        }

        public void setFbAppId(String fbAppId) {
            this.fbAppId = fbAppId;
        }

        public String getFbPages() {
            return fbPages;
        }

        public void setFbPages(String fbPages) {
            this.fbPages = fbPages;
        }

        public String getFavicon() {
            return favicon;
        }

        public void setFavicon(String favicon) {
            this.favicon = favicon;
        }

        public String getAppleTouchIcon() {
            return appleTouchIcon;
        }

        public void setAppleTouchIcon(String appleTouchIcon) {
            this.appleTouchIcon = appleTouchIcon;
        }

        public List<String> getCss() {
            return css;
        }

        public void setCss(List<String> css) {
            this.css = css;
        }
    }

    public static class PageFooter {
        private List<String> jss;

        public List<String> getJss() {
            return jss;
        }

        public void setJss(List<String> jss) {
            this.jss = jss;
        }
    }

    public static class Leaf {
        private Segment segment;
        private Snowflake snowflake;

        public static class Segment {
            private Boolean enable;

            public Boolean getEnable() {
                return enable;
            }

            public void setEnable(Boolean enable) {
                this.enable = enable;
            }
        }

        public static class Snowflake {
            private Boolean enable;
            private Zk zk;
            private Integer port;

            public static class Zk {
                private String address;

                public String getAddress() {
                    return address;
                }

                public void setAddress(String address) {
                    this.address = address;
                }
            }

            public Boolean getEnable() {
                return enable;
            }

            public void setEnable(Boolean enable) {
                this.enable = enable;
            }

            public Zk getZk() {
                return zk;
            }

            public void setZk(Zk zk) {
                this.zk = zk;
            }

            public Integer getPort() {
                return port;
            }

            public void setPort(Integer port) {
                this.port = port;
            }
        }

        public Segment getSegment() {
            return segment;
        }

        public void setSegment(Segment segment) {
            this.segment = segment;
        }

        public Snowflake getSnowflake() {
            return snowflake;
        }

        public void setSnowflake(Snowflake snowflake) {
            this.snowflake = snowflake;
        }
    }

    public List<String> getAuthIgnore() {
        return authIgnore;
    }

    public void setAuthIgnore(List<String> authIgnore) {
        this.authIgnore = authIgnore;
    }

    public static class ThreadPool {
        /**
         * 核心线程数
         */
        private int corePoolSize;

        /**
         * 最大线程数
         */
        private int maxPoolSize;

        /**
         * 线程空闲时间
         */
        private int keepAliveSeconds;

        /**
         * 任务队列容量（阻塞队列）
         */
        private int queueCapacity;

        public int getCorePoolSize() {
            return corePoolSize;
        }

        public void setCorePoolSize(int corePoolSize) {
            this.corePoolSize = corePoolSize;
        }

        public int getMaxPoolSize() {
            return maxPoolSize;
        }

        public void setMaxPoolSize(int maxPoolSize) {
            this.maxPoolSize = maxPoolSize;
        }

        public int getKeepAliveSeconds() {
            return keepAliveSeconds;
        }

        public void setKeepAliveSeconds(int keepAliveSeconds) {
            this.keepAliveSeconds = keepAliveSeconds;
        }

        public int getQueueCapacity() {
            return queueCapacity;
        }

        public void setQueueCapacity(int queueCapacity) {
            this.queueCapacity = queueCapacity;
        }
    }

    public static class Aliyun {
        private String accessKeyId;
        private String accessKeySecret;
        private Oss oss;
        private Green green;
        private Sms sms;

        public static class Oss {
            private String endpoint;
            private String bucketName;
            private String downloadBucketName;
            private String downloadHost;

            public String getEndpoint() {
                return endpoint;
            }

            public void setEndpoint(String endpoint) {
                this.endpoint = endpoint;
            }

            public String getBucketName() {
                return bucketName;
            }

            public void setBucketName(String bucketName) {
                this.bucketName = bucketName;
            }

            public String getDownloadBucketName() {
                return downloadBucketName;
            }

            public void setDownloadBucketName(String downloadBucketName) {
                this.downloadBucketName = downloadBucketName;
            }

            public String getDownloadHost() {
                return downloadHost;
            }

            public void setDownloadHost(String downloadHost) {
                this.downloadHost = downloadHost;
            }
        }

        public static class Green {
            private String regionId;

            public String getRegionId() {
                return regionId;
            }

            public void setRegionId(String regionId) {
                this.regionId = regionId;
            }
        }

        public static class Sms {
            private String regionId;
            private String signName;
            private String templateCode;

            public String getRegionId() {
                return regionId;
            }

            public void setRegionId(String regionId) {
                this.regionId = regionId;
            }

            public String getSignName() {
                return signName;
            }

            public void setSignName(String signName) {
                this.signName = signName;
            }

            public String getTemplateCode() {
                return templateCode;
            }

            public void setTemplateCode(String templateCode) {
                this.templateCode = templateCode;
            }
        }

        public String getAccessKeyId() {
            return accessKeyId;
        }

        public void setAccessKeyId(String accessKeyId) {
            this.accessKeyId = accessKeyId;
        }

        public String getAccessKeySecret() {
            return accessKeySecret;
        }

        public void setAccessKeySecret(String accessKeySecret) {
            this.accessKeySecret = accessKeySecret;
        }

        public Oss getOss() {
            return oss;
        }

        public void setOss(Oss oss) {
            this.oss = oss;
        }

        public Green getGreen() {
            return green;
        }

        public void setGreen(Green green) {
            this.green = green;
        }

        public Sms getSms() {
            return sms;
        }

        public void setSms(Sms sms) {
            this.sms = sms;
        }
    }

    public static class WeChat {
        private String appId;
        private String appSecret;
        private String token;
        private String encodingAESKey;

        public String getAppId() {
            return appId;
        }

        public void setAppId(String appId) {
            this.appId = appId;
        }

        public String getAppSecret() {
            return appSecret;
        }

        public void setAppSecret(String appSecret) {
            this.appSecret = appSecret;
        }

        public String getToken() {
            return token;
        }

        public void setToken(String token) {
            this.token = token;
        }

        public String getEncodingAESKey() {
            return encodingAESKey;
        }

        public void setEncodingAESKey(String encodingAESKey) {
            this.encodingAESKey = encodingAESKey;
        }
    }

    public static class Baidu {
        private String tongji;

        public String getTongji() {
            return tongji;
        }

        public void setTongji(String tongji) {
            this.tongji = tongji;
        }
    }

    public static class Google {
        private String ads;
        private String analytics;
        private ReCAPTCHA reCAPTCHA;

        public static class ReCAPTCHA {
            private String clientKey;
            private String serverKey;

            public String getClientKey() {
                return clientKey;
            }

            public void setClientKey(String clientKey) {
                this.clientKey = clientKey;
            }

            public String getServerKey() {
                return serverKey;
            }

            public void setServerKey(String serverKey) {
                this.serverKey = serverKey;
            }
        }

        public String getAds() {
            return ads;
        }

        public void setAds(String ads) {
            this.ads = ads;
        }

        public String getAnalytics() {
            return analytics;
        }

        public void setAnalytics(String analytics) {
            this.analytics = analytics;
        }

        public ReCAPTCHA getReCAPTCHA() {
            return reCAPTCHA;
        }

        public void setReCAPTCHA(ReCAPTCHA reCAPTCHA) {
            this.reCAPTCHA = reCAPTCHA;
        }
    }

    public static class Jwt {
        private String secret;
        private String issuer;
        private Long expiration;

        public String getSecret() {
            return secret;
        }

        public void setSecret(String secret) {
            this.secret = secret;
        }

        public String getIssuer() {
            return issuer;
        }

        public void setIssuer(String issuer) {
            this.issuer = issuer;
        }

        public Long getExpiration() {
            return expiration;
        }

        public void setExpiration(Long expiration) {
            this.expiration = expiration;
        }
    }

    public static class UCenter {
        private String api;
        private String key;
        private String appId;
        private String connect;

        public String getApi() {
            return api;
        }

        public void setApi(String api) {
            this.api = api;
        }

        public String getKey() {
            return key;
        }

        public void setKey(String key) {
            this.key = key;
        }

        public String getAppId() {
            return appId;
        }

        public void setAppId(String appId) {
            this.appId = appId;
        }

        public String getConnect() {
            return connect;
        }

        public void setConnect(String connect) {
            this.connect = connect;
        }
    }

    public String getActive() {
        return active;
    }

    public void setActive(String active) {
        this.active = active;
    }

    public String getVersion() {
        return version;
    }

    public void setVersion(String version) {
        this.version = version;
    }

    public String getBuildTime() {
        return buildTime;
    }

    public void setBuildTime(String buildTime) {
        this.buildTime = buildTime;
    }

    public String getBaseDomainName() {
        return baseDomainName;
    }

    public void setBaseDomainName(String baseDomainName) {
        this.baseDomainName = baseDomainName;
    }

    public String getSiteDomainName() {
        return siteDomainName;
    }

    public void setSiteDomainName(String siteDomainName) {
        this.siteDomainName = siteDomainName;
    }

    public String getStaticDomain() {
        return staticDomain;
    }

    public void setStaticDomain(String staticDomain) {
        this.staticDomain = staticDomain;
    }

    public boolean isEnableRedis() {
        return enableRedis;
    }

    public void setEnableRedis(boolean enableRedis) {
        this.enableRedis = enableRedis;
    }

    public String getSuperTubeUserName() {
        return superTubeUserName;
    }

    public void setSuperTubeUserName(String superTubeUserName) {
        this.superTubeUserName = superTubeUserName;
    }

    public String getAuthMode() {
        return authMode;
    }

    public void setAuthMode(String authMode) {
        this.authMode = authMode;
    }

    public String getGlobalAesKey() {
        return globalAesKey;
    }

    public void setGlobalAesKey(String globalAesKey) {
        this.globalAesKey = globalAesKey;
    }

    public String getLeafKey() {
        return leafKey;
    }

    public void setLeafKey(String leafKey) {
        this.leafKey = leafKey;
    }

    public String getOwnerUserName() {
        return ownerUserName;
    }

    public void setOwnerUserName(String ownerUserName) {
        this.ownerUserName = ownerUserName;
    }

    public String getSiteName() {
        return siteName;
    }

    public void setSiteName(String siteName) {
        this.siteName = siteName;
    }

    public List<String> getFooterSmallMenu() {
        return footerSmallMenu;
    }

    public void setFooterSmallMenu(List<String> footerSmallMenu) {
        this.footerSmallMenu = footerSmallMenu;
    }

    public PageHead getPageHead() {
        return pageHead;
    }

    public void setPageHead(PageHead pageHead) {
        this.pageHead = pageHead;
    }

    public PageFooter getPageFooter() {
        return pageFooter;
    }

    public void setPageFooter(PageFooter pageFooter) {
        this.pageFooter = pageFooter;
    }

    public boolean isShowFriendlyLink() {
        return showFriendlyLink;
    }

    public void setShowFriendlyLink(boolean showFriendlyLink) {
        this.showFriendlyLink = showFriendlyLink;
    }

    public String getTotpSecret() {
        return totpSecret;
    }

    public void setTotpSecret(String totpSecret) {
        this.totpSecret = totpSecret;
    }

    public Long getDefaultCacheSeconds() {
        return defaultCacheSeconds;
    }

    public void setDefaultCacheSeconds(Long defaultCacheSeconds) {
        this.defaultCacheSeconds = defaultCacheSeconds;
    }

    public String getIp2LocationBinFile() {
        return ip2LocationBinFile;
    }

    public void setIp2LocationBinFile(String ip2LocationBinFile) {
        this.ip2LocationBinFile = ip2LocationBinFile;
    }

    public String getIp2LocationBinFileV6() {
        return ip2LocationBinFileV6;
    }

    public void setIp2LocationBinFileV6(String ip2LocationBinFileV6) {
        this.ip2LocationBinFileV6 = ip2LocationBinFileV6;
    }

    public Leaf getLeaf() {
        return leaf;
    }

    public void setLeaf(Leaf leaf) {
        this.leaf = leaf;
    }

    public ThreadPool getThreadPool() {
        return threadPool;
    }

    public void setThreadPool(ThreadPool threadPool) {
        this.threadPool = threadPool;
    }

    public Aliyun getAliyun() {
        return aliyun;
    }

    public void setAliyun(Aliyun aliyun) {
        this.aliyun = aliyun;
    }

    public WeChat getWeChat() {
        return weChat;
    }

    public void setWeChat(WeChat weChat) {
        this.weChat = weChat;
    }

    public Baidu getBaidu() {
        return baidu;
    }

    public void setBaidu(Baidu baidu) {
        this.baidu = baidu;
    }

    public Google getGoogle() {
        return google;
    }

    public void setGoogle(Google google) {
        this.google = google;
    }

    public Jwt getJwt() {
        return jwt;
    }

    public void setJwt(Jwt jwt) {
        this.jwt = jwt;
    }

    public UCenter getuCenter() {
        return uCenter;
    }

    public void setuCenter(UCenter uCenter) {
        this.uCenter = uCenter;
    }
}
