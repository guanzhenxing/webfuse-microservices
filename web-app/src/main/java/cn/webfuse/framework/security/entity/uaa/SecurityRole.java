package cn.webfuse.framework.security.entity.uaa;

/**
 * 角色
 */
public class SecurityRole {
    private String code;    //角色编码
    private String name;    //角色名称
    private Boolean status; //状态
    private String description; //角色描述

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

    public Boolean getStatus() {
        return status;
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    public void setStatus(Boolean status) {
        this.status = status;
    }

}
