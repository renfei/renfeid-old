package net.renfei.model.kitbox;

import java.util.List;

/**
 * @author renfei
 */
public class IcpQueryVo {
    private Integer code;
    private String msg;
    private Params params;
    private Boolean success;

    public Integer getCode() {
        return code;
    }

    public void setCode(Integer code) {
        this.code = code;
    }

    public String getMsg() {
        return msg;
    }

    public void setMsg(String msg) {
        this.msg = msg;
    }

    public Params getParams() {
        return params;
    }

    public void setParams(Params params) {
        this.params = params;
    }

    public Boolean getSuccess() {
        return success;
    }

    public void setSuccess(Boolean success) {
        this.success = success;
    }

    public static class Params {
        List<IcpInfo> list;

        public List<IcpInfo> getList() {
            return list;
        }

        public void setList(List<IcpInfo> list) {
            this.list = list;
        }
    }

    public static class IcpInfo {
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
            return "IcpInfo{" +
                    "contentTypeName='" + contentTypeName + '\'' +
                    ", domain='" + domain + '\'' +
                    ", domainId=" + domainId +
                    ", leaderName='" + leaderName + '\'' +
                    ", limitAccess='" + limitAccess + '\'' +
                    ", mainId=" + mainId +
                    ", mainLicence='" + mainLicence + '\'' +
                    ", natureName='" + natureName + '\'' +
                    ", serviceId=" + serviceId +
                    ", serviceLicence='" + serviceLicence + '\'' +
                    ", unitName='" + unitName + '\'' +
                    ", updateRecordTime='" + updateRecordTime + '\'' +
                    ", cacheTime='" + cacheTime + '\'' +
                    '}';
        }
    }
}
