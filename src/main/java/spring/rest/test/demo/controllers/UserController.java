package spring.rest.test.demo.controllers;

import java.util.List;
import java.util.Map;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

import jakarta.validation.Valid;
import spring.rest.test.demo.models.CreateUserRequest;
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

    @GetMapping("users/{id}")
    public User getUserById(@PathVariable int id){
        return userService.getUserById(id);
    }
    
    @GetMapping("users/count")
    public Map<String, Integer> countUsers() {
        return Map.of("count", userService.countUsers());
    }

    @PostMapping("/users")
    public ResponseEntity<User> createUser(@RequestBody @Valid CreateUserRequest payload) {
        String firstName = payload.getFirstName();
        String lastName = payload.getLastName();
        String email = payload.getEmail();
        String password = payload.getPassword();
        
        User user = new User(0, firstName, lastName, email, password);

        userService.createUser(user);
        return ResponseEntity.status(HttpStatus.CREATED).body(user);
    }

    @PutMapping("/users/{id}")
    public ResponseEntity<User> updateUser(@PathVariable int id, @RequestBody @Valid CreateUserRequest payload) {
        String firstName = payload.getFirstName();
        String lastName = payload.getLastName();
        String email = payload.getEmail();
        String password = payload.getPassword();

        User updatedUser = new User(id, firstName, lastName, email, password);

        userService.updateUser(id, updatedUser);
        return ResponseEntity.status(HttpStatus.OK).body(updatedUser);
    }

    @DeleteMapping("/users/{id}")
    public ResponseEntity<?> deleteUser(@PathVariable int id){

        userService.deleteUser(id);

        return ResponseEntity.status(HttpStatus.NO_CONTENT).build();
    }
}
