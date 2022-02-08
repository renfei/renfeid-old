package net.renfei.repositories.model;

import java.io.Serializable;

public class KitboxIcpCache implements Serializable {
    private Long id;

    private String contentTypeName;

    private String domain;

    private Long domainId;

    private String leaderName;

    private String limitAccess;

    private Long mainId;

    private String mainLicence;

    private String natureName;

    private Long serviceId;

    private String serviceLicence;

    private String unitName;

    private String updateRecordTime;

    private String cacheTime;

    private static final long serialVersionUID = 1L;

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getContentTypeName() {
        return contentTypeName;
    }

    public void setContentTypeName(String contentTypeName) {
        this.contentTypeName = contentTypeName;
    }

    public String getDomain() {
        return domain;
    }

    public void setDomain(String domain) {
        this.domain = domain;
    }

    public Long getDomainId() {
        return domainId;
    }

    public void setDomainId(Long domainId) {
        this.domainId = domainId;
    }

    public String getLeaderName() {
        return leaderName;
    }

    public void setLeaderName(String leaderName) {
        this.leaderName = leaderName;
    }

    public String getLimitAccess() {
        return limitAccess;
    }

    public void setLimitAccess(String limitAccess) {
        this.limitAccess = limitAccess;
    }

    public Long getMainId() {
        return mainId;
    }

    public void setMainId(Long mainId) {
        this.mainId = mainId;
    }

    public String getMainLicence() {
        return mainLicence;
    }

    public void setMainLicence(String mainLicence) {
        this.mainLicence = mainLicence;
    }

    public String getNatureName() {
        return natureName;
    }

    public void setNatureName(String natureName) {
        this.natureName = natureName;
    }

    public Long getServiceId() {
        return serviceId;
    }

    public void setServiceId(Long serviceId) {
        this.serviceId = serviceId;
    }

    public String getServiceLicence() {
        return serviceLicence;
    }

    public void setServiceLicence(String serviceLicence) {
        this.serviceLicence = serviceLicence;
    }

    public String getUnitName() {
        return unitName;
    }

    public void setUnitName(String unitName) {
        this.unitName = unitName;
    }

    public String getUpdateRecordTime() {
        return updateRecordTime;
    }

    public void setUpdateRecordTime(String updateRecordTime) {
        this.updateRecordTime = updateRecordTime;
    }

    public String getCacheTime() {
        return cacheTime;
    }

    public void setCacheTime(String cacheTime) {
        this.cacheTime = cacheTime;
    }

    @Override
    public String toString() {
        StringBuilder sb = new StringBuilder();
        sb.append(getClass().getSimpleName());
        sb.append(" [");
        sb.append("Hash = ").append(hashCode());
        sb.append(", id=").append(id);
        sb.append(", contentTypeName=").append(contentTypeName);
        sb.append(", domain=").append(domain);
        sb.append(", domainId=").append(domainId);
        sb.append(", leaderName=").append(leaderName);
        sb.append(", limitAccess=").append(limitAccess);
        sb.append(", mainId=").append(mainId);
        sb.append(", mainLicence=").append(mainLicence);
        sb.append(", natureName=").append(natureName);
        sb.append(", serviceId=").append(serviceId);
        sb.append(", serviceLicence=").append(serviceLicence);
        sb.append(", unitName=").append(unitName);
        sb.append(", updateRecordTime=").append(updateRecordTime);
        sb.append(", cacheTime=").append(cacheTime);
        sb.append(", serialVersionUID=").append(serialVersionUID);
        sb.append("]");
        return sb.toString();
    }
}