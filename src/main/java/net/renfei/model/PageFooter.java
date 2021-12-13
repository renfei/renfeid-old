package net.renfei.model;

import lombok.Data;
import net.renfei.config.SystemConfig;
import net.renfei.utils.ApplicationContextUtil;

import java.util.List;
import java.util.stream.Collectors;

/**
 * Html页面中的Footer页脚部分
 *
 * @author renfei
 */
@Data
public class PageFooter {
    private final SystemConfig SYSTEM_CONFIG;
    private List<FooterMenuLinks> footerMenuLinks;
    private boolean showFriendlyLink;
    private List<LinkTree> friendlyLink;
    private List<LinkTree> smallMenu;
    private String version;
    private String buildTime;
    private List<String> jss;
    private String jsText;

    {
        SYSTEM_CONFIG = (SystemConfig) ApplicationContextUtil.getBean("systemConfig");
    }

    public PageFooter() {
        assert SYSTEM_CONFIG != null;
        this.footerMenuLinks = null;
        this.showFriendlyLink = SYSTEM_CONFIG.isShowFriendlyLink();
        this.friendlyLink = null;
        this.smallMenu = null;
        this.version = SYSTEM_CONFIG.getVersion();
        this.buildTime = SYSTEM_CONFIG.getBuildTime();
        this.jss = SYSTEM_CONFIG.getPageFooter().getJss().stream().map(item -> item + "?ver=" + SYSTEM_CONFIG.getBuildTime()).collect(Collectors.toList());
        this.jss.add("https://www.googletagmanager.com/gtag/js?id=" + SYSTEM_CONFIG.getGoogle().getAnalytics());
        this.jss.add("https://pagead2.googlesyndication.com/pagead/js/adsbygoogle.js");
        // jsText
        this.jsText = "var _hmt = _hmt || [];\n"
                + "(function() {\n"
                + "  var hm = document.createElement(\"script\");\n"
                + "  var analytics_bd = '" + SYSTEM_CONFIG.getBaidu().getTongji() + "';\n"
                // 为了防止爬虫扫描到统计代码的key，将URL地址打碎成数组
                // 原地址：hm.src = https://hm.baidu.com/hm.js?<key>
                + "  hm.src = ['ht','t','ps',':/','/h','m','.','ba','i','d','u.c','o','m/','h','m','.j','s?',analytics_bd].join('');\n"
                + "  var s = document.getElementsByTagName(\"script\")[0]; \n"
                + "  s.parentNode.insertBefore(hm, s);\n"
                + "})();\n";
    }
}
