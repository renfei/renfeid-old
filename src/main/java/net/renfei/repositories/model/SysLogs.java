package net.renfei.repositories.model;

import java.io.Serializable;
import java.util.Date;

public class SysLogs implements Serializable {
    private static final long serialVersionUID = 5315015676769145768L;

    private Long id;

    private String logLevel;

    private String logModule;

    private String logType;

    private String userUuid;

    private String userName;

    private String requMethod;

    private String requUri;

    private String requIp;

    private Date logTime;

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
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

    public String getRequIp() {
        return requIp;
    }

    public void setRequIp(String requIp) {
        this.requIp = requIp;
    }

    public Date getLogTime() {
        return logTime;
    }

    public void setLogTime(Date logTime) {
        this.logTime = logTime;
    }

    @Override
    public String toString() {
        StringBuilder sb = new StringBuilder();
        sb.append(getClass().getSimpleName());
        sb.append(" [");
        sb.append("Hash = ").append(hashCode());
        sb.append(", id=").append(id);
        sb.append(", logLevel=").append(logLevel);
        sb.append(", logModule=").append(logModule);
        sb.append(", logType=").append(logType);
        sb.append(", userUuid=").append(userUuid);
        sb.append(", userName=").append(userName);
        sb.append(", requMethod=").append(requMethod);
        sb.append(", requUri=").append(requUri);
        sb.append(", requIp=").append(requIp);
        sb.append(", logTime=").append(logTime);
        sb.append(", serialVersionUID=").append(serialVersionUID);
        sb.append("]");
        return sb.toString();
    }
}