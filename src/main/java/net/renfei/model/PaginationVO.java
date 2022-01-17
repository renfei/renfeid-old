package net.renfei.model;

import java.io.Serializable;

/**
 * @author renfei
 */
public class PaginationVO implements Serializable {
    private static final long serialVersionUID = 2827784018263113088L;
    private String link;
    private String page;
    private boolean active;

    public String getLink() {
        return link;
    }

    public void setLink(String link) {
        this.link = link;
    }

    public String getPage() {
        return page;
    }

    public void setPage(String page) {
        this.page = page;
    }

    public boolean isActive() {
        return active;
    }

    public void setActive(boolean active) {
        this.active = active;
    }
}
