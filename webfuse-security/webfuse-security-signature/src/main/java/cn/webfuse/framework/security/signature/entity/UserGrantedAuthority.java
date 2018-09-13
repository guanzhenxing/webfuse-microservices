package cn.webfuse.framework.security.signature.entity;

import org.springframework.security.core.GrantedAuthority;

/**
 * 扩展自GrantAuthority对象
 */
public class UserGrantedAuthority implements GrantedAuthority {

    private String name;    //权限的名称
    private String authority;   //权限代码

    public void setName(String name) {
        this.name = name;
    }

    public String getName() {
        return name;
    }

    public void setAuthority(String authority) {
        this.authority = authority;
    }

    @Override
    public String getAuthority() {
        return authority;
    }

}
