package spring.rest.test.demo.repositories;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

import org.springframework.stereotype.Repository;

import spring.rest.test.demo.interfaces.UserDataAccess;
import spring.rest.test.demo.models.User;

@Repository
public class InMemoryUserRepository implements UserDataAccess {
    
    private List<User> users = new ArrayList<>();


    public InMemoryUserRepository(){
        users.add(new User(1,"John", "Doe", "john.doe@example.com", "password123"));
        users.add(new User(2,"Jane", "Doe", "jane.doe@example.com", "password456"));
        users.add(new User(3,"Jim", "Beam", "jim.beam@example.com", "password789"));
        users.add(new User(4,"Jack", "Daniels", "jack.daniels@example.com", "password101"));
    }

    @Override
    public List<User> findAll() {
        return users;
    }
    
    @Override
    public int countUsers() {
        return users.size();
    }

    public int getNextId() {
        if (users.isEmpty()) {
            return 1;
        }
        return users.get(users.size() - 1).getId() + 1;
    }

    @Override
    public Optional<User> findById(int id) {
        return users.stream().filter(u -> u.getId() == id).findFirst();
    }

    @Override
    public User save(User user) {
        User existing = users.stream().filter(u -> u.getId() == user.getId()).findFirst().orElse(null);
        if (existing != null) {
            users.remove(existing);
        }

        User newUser = user;
        newUser.setId(existing != null ? existing.getId() : getNextId());

        this.users.add(newUser);
        return newUser;
    }

    @Override
    public void deleteById(int id){
        this.users = this.users.stream().filter(u -> u.getId() != id).toList();
    }
}
