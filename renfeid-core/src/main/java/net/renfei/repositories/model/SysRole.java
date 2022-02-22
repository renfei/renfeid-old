package net.renfei.repositories.model;

import java.io.Serializable;

public class SysRole implements Serializable {
    private Long id;

    private String zhName;

    private String enName;

    private Boolean builtInRole;

    private static final long serialVersionUID = 1L;

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getZhName() {
        return zhName;
    }

    public void setZhName(String zhName) {
        this.zhName = zhName;
    }

    public String getEnName() {
        return enName;
    }

    public void setEnName(String enName) {
        this.enName = enName;
    }

    public Boolean getBuiltInRole() {
        return builtInRole;
    }

    public void setBuiltInRole(Boolean builtInRole) {
        this.builtInRole = builtInRole;
    }

    @Override
    public String toString() {
        StringBuilder sb = new StringBuilder();
        sb.append(getClass().getSimpleName());
        sb.append(" [");
        sb.append("Hash = ").append(hashCode());
        sb.append(", id=").append(id);
        sb.append(", zhName=").append(zhName);
        sb.append(", enName=").append(enName);
        sb.append(", builtInRole=").append(builtInRole);
        sb.append(", serialVersionUID=").append(serialVersionUID);
        sb.append("]");
        return sb.toString();
    }
}