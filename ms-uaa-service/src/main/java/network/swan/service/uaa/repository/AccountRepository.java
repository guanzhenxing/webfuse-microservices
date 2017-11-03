package network.swan.service.uaa.repository;

import network.swan.service.uaa.domain.Account;
import org.springframework.stereotype.Repository;

import java.util.Optional;

/**
 * Created by guanzhenxing on 2017/11/3.
 */
@Repository
public class AccountRepository {

    public Optional<Account> findByUsername(String username) {
        return null;
    }
}
