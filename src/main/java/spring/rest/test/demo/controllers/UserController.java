package spring.rest.test.demo.controllers;

import java.util.List;

import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;
import spring.rest.test.demo.models.User;
import spring.rest.test.demo.services.UserService;

@RestController
public class UserController {
    
    private final UserService userService;

    public UserController(UserService userService) {
        this.userService = userService;
    }

    @GetMapping("/random-user")
    public User getRandomUser(){
        return userService.getRandomUser();
    }

    @GetMapping("/users")
    public List<User> getAllUsers() {
        return userService.getUsers();
    }
}
