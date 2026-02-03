package spring.rest.test.demo.services;

import java.util.List;
import org.springframework.stereotype.Service;
import spring.rest.test.demo.models.User;
import spring.rest.test.demo.repositories.UserRepository;

@Service
public class UserService {
    private final UserRepository userRepository;

    public UserService(UserRepository userRepository) {
        this.userRepository = userRepository;
    }

    public List<User> getUsers() {
        return userRepository.getUsers();
    }

    public User getRandomUser(){
        List<User> users = userRepository.getUsers();
        if (users.isEmpty()) {
            return null;
        }
        int randomIndex = (int) (Math.random() * users.size());
        return users.get(randomIndex);
    }
}
