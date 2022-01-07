package net.renfei.model;

import lombok.Data;
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
public class PageFooter implements Serializable {
    private final SystemConfig SYSTEM_CONFIG;
    private List<FooterMenuLinks> footerMenuLinks;
    private boolean showFriendlyLink;
    private List<LinkTree> friendlyLink;
    private List<LinkTree> smallMenu;
    private String version;
    private String buildTime;
    private List<String> jss;
    private String jsText;
    private final SysService sysService;
    private final SysSiteFooterMenuMapper siteFooterMenuMapper;

    {
        SYSTEM_CONFIG = (SystemConfig) ApplicationContextUtil.getBean("systemConfig");
        sysService = (SysService) ApplicationContextUtil.getBean("sysServiceImpl");
        siteFooterMenuMapper = (SysSiteFooterMenuMapper) ApplicationContextUtil.getBean("sysSiteFooterMenuMapper");
    }

    public PageFooter() {
        assert SYSTEM_CONFIG != null;
        assert sysService != null;
        assert siteFooterMenuMapper != null;
        this.showFriendlyLink = SYSTEM_CONFIG.isShowFriendlyLink();
        this.friendlyLink = sysService.getSysSiteFriendlyLinkList();
        this.version = SYSTEM_CONFIG.getVersion();
        this.buildTime = SYSTEM_CONFIG.getBuildTime();
        SysSiteFooterMenuExample example = new SysSiteFooterMenuExample();
        example.setOrderByClause("order_number");
        example.createCriteria()
                .andPidIsNull();
        this.footerMenuLinks = convert(siteFooterMenuMapper.selectByExample(example));
        this.smallMenu = smallMenuList();
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

    private List<LinkTree> smallMenuList() {
        List<LinkTree> menus = new CopyOnWriteArrayList<>();
        String split = "@";
        // 前端是居右，这里需要倒序输出
        for (int i = SYSTEM_CONFIG.getFooterSmallMenu().size() - 1; i >= 0; i--) {
            LinkTree linkTree = LinkTree.builder()
                    .href(SYSTEM_CONFIG.getFooterSmallMenu().get(i).split(split)[1])
                    .text(SYSTEM_CONFIG.getFooterSmallMenu().get(i).split(split)[0])
                    .target("_blank")
                    .build();
            menus.add(linkTree);
        }
        return menus;
    }

    private List<FooterMenuLinks> convert(List<SysSiteFooterMenu> sysSiteMenus) {
        List<FooterMenuLinks> menus = new CopyOnWriteArrayList<>();
        for (SysSiteFooterMenu sysSiteMenu : sysSiteMenus
        ) {
            FooterMenuLinks linkSubTree = FooterMenuLinks.builder()
                    .title(sysSiteMenu.getMenuText())
                    .links(setSubLink(sysSiteMenu.getId()))
                    .build();
            menus.add(linkSubTree);
        }
        return menus;
    }

    private List<LinkTree> setSubLink(Long pid) {
        SysSiteFooterMenuExample example = new SysSiteFooterMenuExample();
        example.setOrderByClause("order_number");
        example.createCriteria()
                .andPidEqualTo(pid);
        assert siteFooterMenuMapper != null;
        List<SysSiteFooterMenu> sysSiteMenus = siteFooterMenuMapper.selectByExample(example);
        if (sysSiteMenus == null || sysSiteMenus.size() < 1) {
            return null;
        }
        List<LinkTree> menus = new CopyOnWriteArrayList<>();
        for (SysSiteFooterMenu sysSiteMenu : sysSiteMenus
        ) {
            LinkTree linkSubTree = LinkTree.builder()
                    .href(sysSiteMenu.getMenuLink())
                    .text(sysSiteMenu.getMenuText())
                    .target(sysSiteMenu.getIsNewWin() ? "_blank" : "_self")
                    .build();
            menus.add(linkSubTree);
        }
        return menus;
    }
}
