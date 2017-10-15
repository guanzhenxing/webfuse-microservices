package swan.demo.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RestController;
import swan.demo.dao.UserRepository;
import swan.demo.entity.User;

import java.util.List;

@RestController
public class UserController {

    @Autowired
    private UserRepository userRepository;

    @GetMapping("/users")
    public List<User> listUser() {
        return userRepository.findAll();
    }


    @GetMapping("/users/{id}")
    public User findById(@PathVariable Long id) {
        return this.userRepository.findOne(id);
    }

}
