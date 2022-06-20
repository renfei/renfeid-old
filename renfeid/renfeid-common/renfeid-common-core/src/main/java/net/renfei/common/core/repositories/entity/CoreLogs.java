package net.renfei.common.core.repositories.entity;

import java.io.Serializable;
import java.util.Date;

public class CoreLogs implements Serializable {
    private Long id;

    private Date logTime;

    private String logLevel;

    private String logModule;

    private String logType;

    private String requMethod;

    private String requUri;

    private String requReferrer;

    private String userUuid;

    private String userName;

    private String requIp;

    private static final long serialVersionUID = 1L;

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public Date getLogTime() {
        return logTime;
    }

    public void setLogTime(Date logTime) {
        this.logTime = logTime;
    }

    public String getLogLevel() {
        return logLevel;
    }

    public void setLogLevel(String logLevel) {
        this.logLevel = logLevel;
    }

    public String getLogModule() {
        return logModule;
    }

    public void setLogModule(String logModule) {
        this.logModule = logModule;
    }

    public String getLogType() {
        return logType;
    }

    public void setLogType(String logType) {
        this.logType = logType;
    }

    public String getRequMethod() {
        return requMethod;
    }

    public void setRequMethod(String requMethod) {
        this.requMethod = requMethod;
    }

    public String getRequUri() {
        return requUri;
    }

    public void setRequUri(String requUri) {
        this.requUri = requUri;
    }

    public String getRequReferrer() {
        return requReferrer;
    }

    public void setRequReferrer(String requReferrer) {
        this.requReferrer = requReferrer;
    }

    public String getUserUuid() {
        return userUuid;
    }

    public void setUserUuid(String userUuid) {
        this.userUuid = userUuid;
    }

    public String getUserName() {
        return userName;
    }

    public void setUserName(String userName) {
        this.userName = userName;
    }

    public String getRequIp() {
        return requIp;
    }

    public void setRequIp(String requIp) {
        this.requIp = requIp;
    }

    @Override
    public String toString() {
        StringBuilder sb = new StringBuilder();
        sb.append(getClass().getSimpleName());
        sb.append(" [");
        sb.append("Hash = ").append(hashCode());
        sb.append(", id=").append(id);
        sb.append(", logTime=").append(logTime);
        sb.append(", logLevel=").append(logLevel);
        sb.append(", logModule=").append(logModule);
        sb.append(", logType=").append(logType);
        sb.append(", requMethod=").append(requMethod);
        sb.append(", requUri=").append(requUri);
        sb.append(", requReferrer=").append(requReferrer);
        sb.append(", userUuid=").append(userUuid);
        sb.append(", userName=").append(userName);
        sb.append(", requIp=").append(requIp);
        sb.append(", serialVersionUID=").append(serialVersionUID);
        sb.append("]");
        return sb.toString();
    }
}