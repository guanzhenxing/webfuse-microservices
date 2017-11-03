package network.swan.service.uaa.account.repository;

import network.swan.service.uaa.account.domain.User;
import org.springframework.stereotype.Repository;

import java.util.Optional;

/**
 * Created by guanzhenxing on 2017/11/3.
 */
@Repository
public class UserRepository {
    public Optional<User> findByUsername(String username) {
        return null;
    }
}
