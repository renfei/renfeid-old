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
}
