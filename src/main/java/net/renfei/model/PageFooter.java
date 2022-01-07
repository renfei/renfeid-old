package net.renfei.model;

import lombok.*;
import net.renfei.config.SystemConfig;
import net.renfei.repositories.SysSiteFooterMenuMapper;
import net.renfei.repositories.model.SysSiteFooterMenu;
import net.renfei.repositories.model.SysSiteFooterMenuExample;
import net.renfei.repositories.model.SysSiteMenu;
import net.renfei.repositories.model.SysSiteMenuExample;
import net.renfei.services.SysService;
import net.renfei.utils.ApplicationContextUtil;

import java.io.Serializable;
import java.util.List;
import java.util.concurrent.CopyOnWriteArrayList;
import java.util.stream.Collectors;

/**
 * Html页面中的Footer页脚部分
 *
 * @author renfei
 */
@Data
@Builder
@NoArgsConstructor(access = AccessLevel.PUBLIC)
@AllArgsConstructor(access = AccessLevel.PRIVATE)
public class PageFooter implements Serializable {
    private List<FooterMenuLinks> footerMenuLinks;
    private boolean showFriendlyLink;
    private List<LinkTree> friendlyLink;
    private List<LinkTree> smallMenu;
    private String version;
    private String buildTime;
    private List<String> jss;
    private String jsText;
}
