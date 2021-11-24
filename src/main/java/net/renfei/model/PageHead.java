package net.renfei.model;

import lombok.Data;
import net.renfei.config.SystemConfig;
import net.renfei.utils.ApplicationContextUtil;

import java.util.List;

/**
 * Html页面中的Head头部分
 *
 * @author renfei
 */
@Data
public class PageHead {
    private final SystemConfig SYSTEM_CONFIG;
    private String title;
    private String description;
    private String keywords;
    private String author;
    private String copyright;
    private List<String> dnsPrefetch;
    private OGProtocol ogProtocol;
    private String fbAppId;
    private String fbPages;
    private String favicon;
    private String appleTouchIcon;
    private List<String> css;
    private String cssText;
    private String jsText;

    {
        SYSTEM_CONFIG = (SystemConfig) ApplicationContextUtil.getBean("systemConfig");
    }

    public PageHead() {
        assert SYSTEM_CONFIG != null;
        this.title = "";
        this.description = "";
        this.keywords = "";
        this.author = SYSTEM_CONFIG.getPageHead().getAuthor();
        this.copyright = SYSTEM_CONFIG.getPageHead().getCopyright();
        this.dnsPrefetch = SYSTEM_CONFIG.getPageHead().getDnsPrefetch();
        this.ogProtocol = null;
        this.favicon = SYSTEM_CONFIG.getPageHead().getFavicon();
        this.fbAppId = SYSTEM_CONFIG.getPageHead().getFbAppId();
        this.fbPages = SYSTEM_CONFIG.getPageHead().getFbPages();
        this.appleTouchIcon = SYSTEM_CONFIG.getPageHead().getAppleTouchIcon();
        this.css = SYSTEM_CONFIG.getPageHead().getCss();
        this.cssText = "";
        this.jsText = "";
    }
}
