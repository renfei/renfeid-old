package net.renfei.repositories.model;

import java.io.Serializable;

/**
 * @author renfei
 */
public class SysLogsWithBLOBs extends SysLogs implements Serializable {
    private static final long serialVersionUID = 5315015676769145768L;

    private String logDesc;

    private String requParam;

    private String respParam;

    private String requAgent;

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

    @Override
    public String toString() {
        StringBuilder sb = new StringBuilder();
        sb.append(getClass().getSimpleName());
        sb.append(" [");
        sb.append("Hash = ").append(hashCode());
        sb.append(", logDesc=").append(logDesc);
        sb.append(", requParam=").append(requParam);
        sb.append(", respParam=").append(respParam);
        sb.append(", requAgent=").append(requAgent);
        sb.append(", serialVersionUID=").append(serialVersionUID);
        sb.append("]");
        return sb.toString();
    }
}