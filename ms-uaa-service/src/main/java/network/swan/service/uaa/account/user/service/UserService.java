package network.swan.service.uaa.account.user.service;

import network.swan.service.uaa.account.user.domain.User;
import network.swan.service.uaa.account.user.repository.UserRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;

import java.util.Optional;

/**
 * Created by guanzhenxing on 2017/11/3.
 */
@Service
public class UserService {


    @Autowired
    UserRepository userRepository;

    public User findByUsername(String username) {
        Optional<User> account = userRepository.findByUsername(username);
        if (account.isPresent()) {
            return account.get();
        } else {
            throw new UsernameNotFoundException(String.format("Username[%s] not found", username));
        }
    }
}
