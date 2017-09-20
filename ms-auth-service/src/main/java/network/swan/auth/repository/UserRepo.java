package network.swan.auth.repository;

/**
 * Created by guanzhenxing on 2017/9/3.
 */

import network.swan.frame.domain.uc.User;
import org.springframework.data.repository.Repository;

import java.util.Optional;

public interface UserRepo extends Repository<User, Long> {
    Optional<User> findByUsername(String username);


}
