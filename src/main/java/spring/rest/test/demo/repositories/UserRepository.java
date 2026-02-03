package spring.rest.test.demo.repositories;

import java.util.ArrayList;
import java.util.List;
import org.springframework.stereotype.Repository;
import spring.rest.test.demo.models.User;

@Repository
public class UserRepository {
    
    private List<User> users = new ArrayList<>();


    public UserRepository(){
        users.add(new User("John", "Doe", "john.doe@example.com", "password123"));
        users.add(new User("Jane", "Doe", "jane.doe@example.com", "password456"));
        users.add(new User("Jim", "Beam", "jim.beam@example.com", "password789"));
        users.add(new User("Jack", "Daniels", "jack.daniels@example.com", "password101"));
        users.add(new User("Jill", "Valentine", "jill.valentine@example.com", "password102"));
        users.add(new User("James", "Bond", "james.bond@example.com", "password103"));
        users.add(new User("Jessica", "Jones", "jessica.jones@example.com", "password104"));
    }

    public List<User> getUsers() {
        return users;
    }

    public void setUsers(List<User> users) {
        this.users = users;
    }
}
