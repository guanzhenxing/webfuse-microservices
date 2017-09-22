package network.swan.auth.models;

import network.swan.frame.domain.uc.User;
import org.apache.commons.collections.CollectionUtils;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.core.userdetails.UserDetails;

import javax.persistence.Entity;
import java.util.ArrayList;
import java.util.Collection;

/**
 * 账户信息
 */
@Entity
public class Account extends User implements UserDetails {


    public Account(User user) {
        this.setUsername(user.getUsername());
        this.setPassword(user.getPassword());
        this.setRoles(user.getRoles());
    }

    @Override
    public Collection<? extends GrantedAuthority> getAuthorities() {

        Collection<GrantedAuthority> authorities = CollectionUtils.EMPTY_COLLECTION;
        this.getRoles().forEach(role -> authorities.add(new SimpleGrantedAuthority(role.getCode())));
        return authorities;
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
        return true;
    }
}
