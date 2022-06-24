package net.renfei.common.core.repositories.entity;

import java.io.Serializable;

public class CoreLogsWithBLOBs extends CoreLogs implements Serializable {
    private String logDesc;

    private String requParam;

    private String respParam;

    private String requAgent;

    private static final long serialVersionUID = 1L;

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