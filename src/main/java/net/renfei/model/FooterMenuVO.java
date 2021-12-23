package net.renfei.model;

import lombok.Data;

import java.util.List;

/**
 * <p>Title: FooterMenuVO</p>
 * <p>Description: 页脚菜单</p>
 *
 * @author RenFei(i @ renfei.net)
 */
@Data
public class FooterMenuVO {
    private String title;
    private List<LinkTree> links;
}
