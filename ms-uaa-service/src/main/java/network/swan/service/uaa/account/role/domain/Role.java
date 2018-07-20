package network.swan.service.uaa.account.role.domain;

import network.swan.frame.domain.BaseEntity;
import network.swan.service.uaa.account.permission.domain.Permission;

import java.util.List;

/**
 * 角色
 * Created by guanzhenxing on 2017/11/3.
 */
public class Role extends BaseEntity {

    private String name;
    private String displayName;
    private List<Permission> permissions;   //权限列表

    public String getName() {
        return name;
    }
    public void setName(String name) {
        this.name = name == null ? null : name.trim();
    }

    public String getDisplayName() {
        return displayName;
    }
    public void setDisplayName(String displayName) {
        this.displayName = displayName == null ? null : displayName.trim();
    }

    public List<Permission> getPermissions() {
        return permissions;
    }
    public void setPermissions(List<Permission> permissions) {
        this.permissions = permissions;
    }
}
