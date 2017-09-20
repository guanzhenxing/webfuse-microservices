package network.swan.auth.service;

import network.swan.auth.models.Account;
import network.swan.auth.repository.UserRepo;
import network.swan.frame.domain.uc.User;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

import java.util.Optional;

/**
 * Created by guanzhenxing on 2017/9/3.
 */
@Service
public class AccountService implements UserDetailsService {


    @Autowired
    private UserRepo userRepo;

    @Autowired
    private PasswordEncoder passwordEncoder;

    @Override
    public UserDetails loadUserByUsername(String username) throws UsernameNotFoundException {
        return findAccountByUsername(username);
    }

    public Account findAccountByUsername(String username) throws UsernameNotFoundException {
        Optional<User> user = userRepo.findByUsername(username);
        if (user.isPresent()) {
            return new Account(user.get());
        } else {
            throw new UsernameNotFoundException(String.format("Username[%s] not found", username));
        }
    }

//    public Account registerUser(Account account) {
//        account.setPassword(passwordEncoder.encode(account.getPassword()));
//        account.grantAuthority(Role.ROLE_USER);
//        return accountRepo.save(account);
//    }
//
//    @Transactional // To successfully remove the date @Transactional annotation must be added
//    public boolean removeAuthenticatedAccount() throws UsernameNotFoundException {
//        String username = SecurityContextHolder.getContext().getAuthentication().getName();
//        Account acct = findAccountByUsername(username);
//        int del = accountRepo.deleteAccountById(acct.getId());
//        return del > 0;
//    }


}
