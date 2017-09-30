package network.swan.auth.service;

import network.swan.auth.models.Account;

import java.util.List;

/**
 * Created by guanzhenxing on 2017/9/23.
 */
public interface UserService {
    Account findById(long id);

    Account findByName(String name);

    void saveUser(Account user);

    void updateUser(Account user);

    void deleteUserById(long id);

    List<Account> findAllUsers();

    void deleteAllUsers();

    public boolean isUserExist(Account user);
}
