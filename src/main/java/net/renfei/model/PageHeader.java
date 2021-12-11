package net.renfei.model;

import lombok.Data;
import net.renfei.domain.user.User;

import java.util.List;

@Data
public class PageHeader {
    private String logoSrc;
    private String logoName;
    private String notice;
    private List<LinkTree> menus;
    private User user;
}
