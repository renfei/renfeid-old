package net.renfei.model.log;

import java.util.Date;

/**
 * @author renfei
 */
public class SysLogDTO {
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

    private String logDesc;

    private String requParam;

    private String respParam;

    private String requAgent;

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

    public String getLogDesc() {
        return logDesc;
    }

    public void setLogDesc(String logDesc) {
        this.logDesc = logDesc;
    }

    public String getRequParam() {
        return requParam;
    }

    public void setRequParam(String requParam) {
        this.requParam = requParam;
    }

    public String getRespParam() {
        return respParam;
    }

    public void setRespParam(String respParam) {
        this.respParam = respParam;
    }

    public String getRequAgent() {
        return requAgent;
    }

    public void setRequAgent(String requAgent) {
        this.requAgent = requAgent;
    }
}
