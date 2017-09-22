package network.swan.auth.repository;

/**
 * Created by guanzhenxing on 2017/9/3.
 */

import network.swan.auth.models.Account;
import org.springframework.data.repository.Repository;

import java.util.Optional;


public interface AccountRepo extends Repository<Account, Long> {
    Optional<Account> findByUsername(String username);
    Account save(Account account);
    int deleteAccountById(Long id);

}
