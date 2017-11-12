package network.swan.service.uaa.account.permission.domain;

import network.swan.frame.domain.BaseEntity;

/**
 * Created by guanzhenxing on 2017/11/12.
 */
public class Permission extends BaseEntity {

    private String name;
    private String displayName;

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
}
