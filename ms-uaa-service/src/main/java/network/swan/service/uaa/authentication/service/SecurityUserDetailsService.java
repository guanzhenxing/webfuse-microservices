package network.swan.service.uaa.authentication.service;

import network.swan.service.uaa.account.user.domain.User;
import network.swan.service.uaa.account.user.service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;

/**
 * 权限认证时候的用户查询
 * Created by guanzhenxing on 2017/11/3.
 */
@Service
public class SecurityUserDetailsService implements UserDetailsService {

    @Autowired
    private UserService userService;

    @Override
    public UserDetails loadUserByUsername(String username) throws UsernameNotFoundException {
        User account = userService.findByUsername(username);
        return new org.springframework.security.core.userdetails.User(account.getUsername(), account.getPassword(), account.getAuthorities());
    }

}
