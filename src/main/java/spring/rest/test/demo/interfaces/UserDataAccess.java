package spring.rest.test.demo.interfaces;

import java.util.List;
import java.util.Optional;

import spring.rest.test.demo.models.User;

public interface UserDataAccess {
    List<User> findAll();
    Optional<User> findById(int id);
    User save(User user);
    void deleteById(int id);
}