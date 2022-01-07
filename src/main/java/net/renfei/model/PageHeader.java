package net.renfei.model;

import lombok.Data;
import net.renfei.config.SystemConfig;
import net.renfei.domain.user.User;
import net.renfei.repositories.SysSiteMenuMapper;
import net.renfei.repositories.model.SysSiteMenu;
import net.renfei.repositories.model.SysSiteMenuExample;
import net.renfei.utils.ApplicationContextUtil;

import java.io.Serializable;
import java.util.List;
import java.util.concurrent.CopyOnWriteArrayList;

@Data
public class PageHeader implements Serializable {
    private String logoSrc;
    private String logoName;
    private String notice;
    private List<LinkTree> menus;
    private User user;
    private final SystemConfig SYSTEM_CONFIG;
    private final SysSiteMenuMapper sysSiteMenuMapper;

    {
        SYSTEM_CONFIG = (SystemConfig) ApplicationContextUtil.getBean("systemConfig");
        sysSiteMenuMapper = (SysSiteMenuMapper) ApplicationContextUtil.getBean("sysSiteMenuMapper");
    }

    public PageHeader(String notice) {
        this.notice = notice;
        SysSiteMenuExample example = new SysSiteMenuExample();
        example.setOrderByClause("order_number");
        example.createCriteria()
                .andPidIsNull();
        assert sysSiteMenuMapper != null;
        this.menus = convert(sysSiteMenuMapper.selectByExample(example));
    }

    private List<LinkTree> convert(List<SysSiteMenu> sysSiteMenus) {
        List<LinkTree> menus = new CopyOnWriteArrayList<>();
        for (SysSiteMenu sysSiteMenu : sysSiteMenus
        ) {
            LinkTree linkSubTree = LinkTree.builder()
                    .href(sysSiteMenu.getMenuLink())
                    .text(sysSiteMenu.getMenuText())
                    .target(sysSiteMenu.getIsNewWin() ? "_blank" : "_self")
                    .build();
            linkSubTree.setSubLink(setSubLink(sysSiteMenu.getId()));
            menus.add(linkSubTree);
        }
        return menus;
    }

    private List<LinkTree> setSubLink(Long pid) {
        SysSiteMenuExample example = new SysSiteMenuExample();
        example.setOrderByClause("order_number");
        example.createCriteria()
                .andPidEqualTo(pid);
        assert sysSiteMenuMapper != null;
        List<SysSiteMenu> sysSiteMenus = sysSiteMenuMapper.selectByExample(example);
        if (sysSiteMenus == null || sysSiteMenus.size() < 1) {
            return null;
        }
        return convert(sysSiteMenus);
    }
}
