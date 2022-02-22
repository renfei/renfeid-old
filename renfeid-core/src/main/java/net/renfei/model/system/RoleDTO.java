package net.renfei.model.system;

import org.springframework.security.access.ConfigAttribute;

import java.io.Serializable;

/**
 * @author renfei
 */
public class RoleDTO implements ConfigAttribute, Serializable {
    private static final long serialVersionUID = -68998808633462737L;
    private Long id;
    private String zhName;
    private String enName;
    private Boolean builtInRole;

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
    public String getAttribute() {
        if (enName == null) {
            return null;
        } else if (enName.startsWith("ROLE_")) {
            return enName;
        } else {
            return "ROLE_" + enName;
        }
    }
}
