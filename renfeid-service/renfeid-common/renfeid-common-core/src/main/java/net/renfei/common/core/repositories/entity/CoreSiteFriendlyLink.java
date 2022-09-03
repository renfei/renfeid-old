package net.renfei.common.core.repositories.entity;

import java.io.Serializable;
import java.util.Date;

public class CoreSiteFriendlyLink implements Serializable {
    private Long id;

    private Boolean isDelete;

    private Date addtime;

    private Boolean auditPass;

    private Integer linkType;

    private String contactEmail;

    private String contactQq;

    private Integer orderId;

    private static final long serialVersionUID = 1L;

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public Boolean getIsDelete() {
        return isDelete;
    }

    public void setIsDelete(Boolean isDelete) {
        this.isDelete = isDelete;
    }

    public Date getAddtime() {
        return addtime;
    }

    public void setAddtime(Date addtime) {
        this.addtime = addtime;
    }

    public Boolean getAuditPass() {
        return auditPass;
    }

    public void setAuditPass(Boolean auditPass) {
        this.auditPass = auditPass;
    }

    public Integer getLinkType() {
        return linkType;
    }

    public void setLinkType(Integer linkType) {
        this.linkType = linkType;
    }

    public String getContactEmail() {
        return contactEmail;
    }

    public void setContactEmail(String contactEmail) {
        this.contactEmail = contactEmail;
    }

    public String getContactQq() {
        return contactQq;
    }

    public void setContactQq(String contactQq) {
        this.contactQq = contactQq;
    }

    public Integer getOrderId() {
        return orderId;
    }

    public void setOrderId(Integer orderId) {
        this.orderId = orderId;
    }

    @Override
    public String toString() {
        StringBuilder sb = new StringBuilder();
        sb.append(getClass().getSimpleName());
        sb.append(" [");
        sb.append("Hash = ").append(hashCode());
        sb.append(", id=").append(id);
        sb.append(", isDelete=").append(isDelete);
        sb.append(", addtime=").append(addtime);
        sb.append(", auditPass=").append(auditPass);
        sb.append(", linkType=").append(linkType);
        sb.append(", contactEmail=").append(contactEmail);
        sb.append(", contactQq=").append(contactQq);
        sb.append(", orderId=").append(orderId);
        sb.append(", serialVersionUID=").append(serialVersionUID);
        sb.append("]");
        return sb.toString();
    }
}