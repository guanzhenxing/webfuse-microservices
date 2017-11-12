package network.swan.service.uaa.account.user.domain;

import network.swan.frame.domain.BaseEntity;
import network.swan.service.uaa.account.permission.domain.Permission;
import network.swan.service.uaa.account.role.domain.Role;
import org.apache.commons.collections.CollectionUtils;
import org.springframework.security.core.CredentialsContainer;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.core.userdetails.UserDetails;

import java.util.Collection;
import java.util.HashSet;

/**
 * Created by guanzhenxing on 2017/11/3.
 */
public class User extends BaseEntity implements UserDetails, CredentialsContainer {

    private String password;
    private String username;
    private boolean accountNonExpired;
    private boolean accountNonLocked;
    private boolean credentialsNonExpired;

    private Collection<Role> roles;
    private Collection<GrantedAuthority> authorities;


    @Override
    public void eraseCredentials() {

    }

    @Override
    public Collection<? extends GrantedAuthority> getAuthorities() {

        if (null != this.authorities) {
            return this.authorities;
        }
        if (null == roles) {
            return new HashSet<>();
        }
        this.authorities = new HashSet<>();
        this.roles.stream().forEach(role -> {
            Collection<Permission> permissions = role.getPermissions();
            if (!CollectionUtils.isEmpty(permissions)) {
                permissions.stream().forEach(permission -> this.authorities.add(new SimpleGrantedAuthority(permission.getName())));
            }
        });
        return this.authorities;
    }

    @Override
    public String getPassword() {
        return this.password;
    }

    @Override
    public String getUsername() {
        return this.username;
    }

    @Override
    public boolean isAccountNonExpired() {
        return true;
    }

    @Override
    public boolean isAccountNonLocked() {
        return true;
    }

    @Override
    public boolean isCredentialsNonExpired() {
        return true;
    }

    @Override
    public boolean isEnabled() {
        return isEnabled();
    }

    public void setPassword(String password) {
        this.password = password;
    }

    public void setUsername(String username) {
        this.username = username;
    }

    public void setAccountNonExpired(boolean accountNonExpired) {
        this.accountNonExpired = accountNonExpired;
    }

    public void setAccountNonLocked(boolean accountNonLocked) {
        this.accountNonLocked = accountNonLocked;
    }

    public void setCredentialsNonExpired(boolean credentialsNonExpired) {
        this.credentialsNonExpired = credentialsNonExpired;
    }

    public Collection<Role> getRoles() {
        return roles;
    }

    public void setRoles(Collection<Role> roles) {
        this.roles = roles;
    }

    public void setAuthorities(Collection<GrantedAuthority> authorities) {
        this.authorities = authorities;
    }

}
