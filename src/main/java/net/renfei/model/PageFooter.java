package net.renfei.model;

import java.io.Serializable;
import java.util.List;

/**
 * Html页面中的Footer页脚部分
 *
 * @author renfei
 */
public class PageFooter implements Serializable {
    private static final long serialVersionUID = -314420603322403668L;
    private List<FooterMenuLinks> footerMenuLinks;
    private boolean showFriendlyLink;
    private List<LinkTree> friendlyLink;
    private List<LinkTree> smallMenu;
    private String version;
    private String buildTime;
    private List<String> jss;
    private String jsText;

    public List<FooterMenuLinks> getFooterMenuLinks() {
        return footerMenuLinks;
    }

    public void setFooterMenuLinks(List<FooterMenuLinks> footerMenuLinks) {
        this.footerMenuLinks = footerMenuLinks;
    }

    public boolean isShowFriendlyLink() {
        return showFriendlyLink;
    }

    public void setShowFriendlyLink(boolean showFriendlyLink) {
        this.showFriendlyLink = showFriendlyLink;
    }

    public List<LinkTree> getFriendlyLink() {
        return friendlyLink;
    }

    public void setFriendlyLink(List<LinkTree> friendlyLink) {
        this.friendlyLink = friendlyLink;
    }

    public List<LinkTree> getSmallMenu() {
        return smallMenu;
    }

    public void setSmallMenu(List<LinkTree> smallMenu) {
        this.smallMenu = smallMenu;
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

    public List<String> getJss() {
        return jss;
    }

    public void setJss(List<String> jss) {
        this.jss = jss;
    }

    public String getJsText() {
        return jsText;
    }

    public void setJsText(String jsText) {
        this.jsText = jsText;
    }
}
