package spring.rest.test.demo.repositories;

import java.util.ArrayList;
import java.util.List;

import org.springframework.stereotype.Repository;

import spring.rest.test.demo.models.User;

@Repository
public class UserRepository {
    
    private List<User> users = new ArrayList<>();


    public UserRepository(){
        users.add(new User(1,"John", "Doe", "john.doe@example.com", "password123"));
        users.add(new User(2,"Jane", "Doe", "jane.doe@example.com", "password456"));
        users.add(new User(3,"Jim", "Beam", "jim.beam@example.com", "password789"));
        users.add(new User(4,"Jack", "Daniels", "jack.daniels@example.com", "password101"));
    }

    public List<User> getUsers() {
        return users;
    }

    public int getNextId() {
        if (users.isEmpty()) {
            return 1;
        }
        return users.get(users.size() - 1).getId() + 1;
    }

    public void setUsers(List<User> users) {
        this.users = users;
    }

    public void addUser(User user) {
        this.users.add(user);
    }

    public void deleteUser(int id){
        this.users = this.users.stream().filter(u -> u.getId() != id).toList();
    }
}
