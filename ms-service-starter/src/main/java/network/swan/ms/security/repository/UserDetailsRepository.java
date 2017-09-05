package network.swan.ms.security.repository;

import network.swan.ms.security.module.User;

public interface UserDetailsRepository {


    User findByName(String username);
}
