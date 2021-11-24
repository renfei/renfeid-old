package net.renfei.model;

import lombok.Data;
import net.renfei.config.SystemConfig;
import net.renfei.utils.ApplicationContextUtil;

import java.util.List;

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
        this.jss = SYSTEM_CONFIG.getPageFooter().getJss();
        this.jsText = "";
    }
}
