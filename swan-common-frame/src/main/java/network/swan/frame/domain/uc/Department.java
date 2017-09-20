package network.swan.frame.domain.uc;

import network.swan.core.lang.domain.Entity;

/**
 * 部门
 */
public class Department extends Entity {

    private int id;
    private String code;
    private String name;
    private Organization organization;  //部门属于某个组织

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getCode() {
        return code;
    }

    public void setCode(String code) {
        this.code = code;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public Organization getOrganization() {
        return organization;
    }

    public void setOrganization(Organization organization) {
        this.organization = organization;
    }
}
