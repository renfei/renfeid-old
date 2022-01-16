package net.renfei.model;

import java.io.Serializable;
import java.util.List;

public class FooterMenuLinks implements Serializable {
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
