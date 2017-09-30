package network.swan.auth.service.impl;

import network.swan.auth.models.Account;
import network.swan.auth.service.UserService;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by guanzhenxing on 2017/9/23.
 */
@Service
public class UserServiceImpl implements UserService {
    @Override
    public Account findById(long id) {
        Account account = new Account();
        account.setId(id);
        return account;
    }

    @Override
    public Account findByName(String name) {
        Account account = new Account();
        account.setUsername(name);
        return account;
    }

    @Override
    public void saveUser(Account user) {

    }

    @Override
    public void updateUser(Account user) {

    }

    @Override
    public void deleteUserById(long id) {

    }

    @Override
    public List<Account> findAllUsers() {
        return new ArrayList<>();
    }

    @Override
    public void deleteAllUsers() {

    }

    @Override
    public boolean isUserExist(Account user) {
        return false;
    }
}
