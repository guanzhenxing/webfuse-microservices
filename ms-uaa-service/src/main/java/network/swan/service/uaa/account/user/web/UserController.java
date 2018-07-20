package network.swan.service.uaa.account.user.web;

import network.swan.service.uaa.account.user.domain.User;
import network.swan.service.uaa.account.user.service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RestController;

/**
 * Created by guanzhenxing on 2017/11/21.
 */
@RestController
public class UserController {

    @Autowired
    UserService userService;

    @GetMapping("/v0.1/uaa/users/{userId}")
    public User findById(@PathVariable("userId") String userId) {
        return userService.selectById(userId);
    }

}
