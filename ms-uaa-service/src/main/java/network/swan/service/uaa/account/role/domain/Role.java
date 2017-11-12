package network.swan.service.uaa.account.role.domain;

import network.swan.frame.domain.BaseEntity;
import network.swan.service.uaa.account.permission.domain.Permission;

import java.util.Collection;

/**
 * Created by guanzhenxing on 2017/11/3.
 */
public class Role extends BaseEntity {

    private String name;
    private String displayName;
    private Collection<Permission> permissions;


    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getDisplayName() {
        return displayName;
    }

    public void setDisplayName(String displayName) {
        this.displayName = displayName;
    }

    public Collection<Permission> getPermissions() {
        return permissions;
    }

    public void setPermissions(Collection<Permission> permissions) {
        this.permissions = permissions;
    }
}
