package net.renfei.common.core.repositories.entity;

import java.io.Serializable;

public class CoreSiteFriendlyLinkWithBLOBs extends CoreSiteFriendlyLink implements Serializable {
    private String sitename;

    private String sitelink;

    private String inSiteLink;

    private String contactName;

    private String remarks;

    private static final long serialVersionUID = 1L;

    public String getSitename() {
        return sitename;
    }

    public void setSitename(String sitename) {
        this.sitename = sitename;
    }

    public String getSitelink() {
        return sitelink;
    }

    public void setSitelink(String sitelink) {
        this.sitelink = sitelink;
    }

    public String getInSiteLink() {
        return inSiteLink;
    }

    public void setInSiteLink(String inSiteLink) {
        this.inSiteLink = inSiteLink;
    }

    public String getContactName() {
        return contactName;
    }

    public void setContactName(String contactName) {
        this.contactName = contactName;
    }

    public String getRemarks() {
        return remarks;
    }

    public void setRemarks(String remarks) {
        this.remarks = remarks;
    }

    @Override
    public String toString() {
        StringBuilder sb = new StringBuilder();
        sb.append(getClass().getSimpleName());
        sb.append(" [");
        sb.append("Hash = ").append(hashCode());
        sb.append(", sitename=").append(sitename);
        sb.append(", sitelink=").append(sitelink);
        sb.append(", inSiteLink=").append(inSiteLink);
        sb.append(", contactName=").append(contactName);
        sb.append(", remarks=").append(remarks);
        sb.append(", serialVersionUID=").append(serialVersionUID);
        sb.append("]");
        return sb.toString();
    }
}