package spring.rest.test.demo.services;

import java.util.List;
import java.util.Optional;

import org.springframework.stereotype.Service;

import spring.rest.test.demo.exceptions.UserCreationException;
import spring.rest.test.demo.exceptions.UserDeletionException;
import spring.rest.test.demo.exceptions.UserNotFoundException;
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

    public User getUserById(int id){
        List<User> users = userRepository.getUsers();

        User user = users.stream().filter(u -> u.getId() == id).findFirst().orElse(null);

        if (user == null) {
            throw new UserNotFoundException();
        }

        return user;
    }

    public int getNextId() {
        return userRepository.getNextId();
    }

    public User createUser(User user) {
        try {
            userRepository.addUser(user);
        } catch (Exception e) {
            throw new UserCreationException("Failed to create user");
        }
        return user;
    }

    public User updateUser(int id, User updatedUser) {
        List<User> users = userRepository.getUsers();

        Optional<User> optionalUser = users.stream().filter(u -> u.getId() == id).findFirst();

        if (optionalUser.isEmpty()) {
            throw new UserNotFoundException();
        }

        User existingUser = optionalUser.get();
        Optional<String> firstNameOpt = Optional.ofNullable(updatedUser.getFirstName());

        firstNameOpt.ifPresent(firstName -> existingUser.setFirstName(firstName));

        Optional<String> lastNameOpt = Optional.ofNullable(updatedUser.getLastName());
        lastNameOpt.ifPresent(existingUser::setLastName);

        Optional<String> emailOpt = Optional.ofNullable(updatedUser.getEmail());
        emailOpt.ifPresent(existingUser::setEmail);

        Optional<String> passwordOpt = Optional.ofNullable(updatedUser.getPassword());
        passwordOpt.ifPresent(existingUser::setPassword);

        return existingUser;
    }

    public void deleteUser(int id){
        try {
            userRepository.deleteUser(id);
        } catch (Exception e) {
            throw new UserDeletionException();
        }
    }
}
