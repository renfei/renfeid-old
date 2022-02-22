package net.renfei.model;

import java.io.Serializable;
import java.util.List;

/**
 * <p>Title: FooterMenuVO</p>
 * <p>Description: 页脚菜单</p>
 *
 * @author RenFei(i @ renfei.net)
 */
public class FooterMenuVO implements Serializable {
    private static final long serialVersionUID = -314420603322403668L;
    private String title;
    private List<LinkTree> links;

    public String getTitle() {
        return title;
    }

    public void setTitle(String title) {
        this.title = title;
    }

    public List<LinkTree> getLinks() {
        return links;
    }

    public void setLinks(List<LinkTree> links) {
        this.links = links;
    }
}
