package spring.rest.test.demo.repositories;
import org.springframework.data.jpa.repository.JpaRepository;

import spring.rest.test.demo.interfaces.UserDataAccess;
import spring.rest.test.demo.models.User;

public interface UserRepository extends JpaRepository<User, Integer>, UserDataAccess {
    @Override
    public default int countUsers() {
        return (int) count();
    }
}
