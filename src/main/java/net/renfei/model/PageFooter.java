package net.renfei.model;

import lombok.Data;

import java.util.List;

/**
 * @author renfei
 */
@Data
public class PageFooter {
    private List<FooterMenuLinks> footerMenuLinks;
    private boolean showFriendlyLink;
    private List<LinkTree> friendlyLink;
    private List<LinkTree> smallMenu;
    private String version;
    private String buildTime;
    private List<String> jss;
    private String jsText;
}
