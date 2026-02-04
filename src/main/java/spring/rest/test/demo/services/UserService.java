package spring.rest.test.demo.services;

import java.util.List;
import java.util.Optional;

import org.springframework.stereotype.Service;

import spring.rest.test.demo.exceptions.UserCreationException;
import spring.rest.test.demo.exceptions.UserDeletionException;
import spring.rest.test.demo.exceptions.UserNotFoundException;
import spring.rest.test.demo.interfaces.UserDataAccess;
import spring.rest.test.demo.models.User;

@Service
public class UserService {
    private final UserDataAccess userDataAccess;

     public UserService(UserDataAccess userDataAccess) {
        this.userDataAccess = userDataAccess;
    }

    public List<User> getUsers() {
        return userDataAccess.findAll();
    }

    public User getRandomUser(){
        List<User> users = userDataAccess.findAll();
        if (users.isEmpty()) {
            return null;
        }
        int randomIndex = (int) (Math.random() * users.size());
        return users.get(randomIndex);
    }

    public User getUserById(int id){
        Optional<User> user = userDataAccess.findById(id);

        if (user.isEmpty()) {
            throw new UserNotFoundException();
        }

        return user.get();
    }


    public User createUser(User user) {
        try {
            userDataAccess.save(user);
        } catch (Exception e) {
            throw new UserCreationException("Failed to create user");
        }
        return user;
    }

    public User updateUser(int id, User updatedUser) {
        Optional<User> optionalUser = userDataAccess.findById(id);

        if (optionalUser.isEmpty()) {
            throw new UserNotFoundException();
        }

        User existingUser = optionalUser.get();
        Optional<String> firstNameOpt = Optional.ofNullable(updatedUser.getFirstName());

        firstNameOpt.ifPresent(existingUser::setFirstName);

        Optional<String> lastNameOpt = Optional.ofNullable(updatedUser.getLastName());
        lastNameOpt.ifPresent(existingUser::setLastName);

        Optional<String> emailOpt = Optional.ofNullable(updatedUser.getEmail());
        emailOpt.ifPresent(existingUser::setEmail);

        Optional<String> passwordOpt = Optional.ofNullable(updatedUser.getPassword());
        passwordOpt.ifPresent(existingUser::setPassword);

        userDataAccess.save(existingUser);

        return existingUser;
    }

    public void deleteUser(int id){
        try {
            userDataAccess.deleteById(id);
        } catch (Exception e) {
            throw new UserDeletionException();
        }
    }
}
