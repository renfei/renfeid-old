package net.renfei.model.system;

import net.renfei.repositories.model.SysRolePermission;

import java.io.Serializable;
import java.util.List;

/**
 * 系统角色VO对象
 *
 * @author renfei
 */
public class SysRoleVO implements Serializable {
    private static final long serialVersionUID = 7119079055670106023L;
    private Long id;
    private String zhName;
    private String enName;
    private Boolean builtInRole;
    private List<SysRolePermission> rolePermissions;

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

    public List<SysRolePermission> getRolePermissions() {
        return rolePermissions;
    }

    public void setRolePermissions(List<SysRolePermission> rolePermissions) {
        this.rolePermissions = rolePermissions;
    }
}
