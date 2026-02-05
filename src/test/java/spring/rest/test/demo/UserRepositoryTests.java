package spring.rest.test.demo;

import java.util.List;

import static org.junit.jupiter.api.Assertions.assertFalse;
import static org.junit.jupiter.api.Assertions.assertNotNull;
import org.junit.jupiter.api.Test;
import org.springframework.boot.test.context.SpringBootTest;

import spring.rest.test.demo.interfaces.UserDataAccess;
import spring.rest.test.demo.models.User;
import spring.rest.test.demo.repositories.InMemoryUserRepository;

@SpringBootTest
class UserRepositoryTests {

	@Test
	void shouldContainUsers() {
		UserDataAccess userDataAccess = new InMemoryUserRepository();

		List<User> users = userDataAccess.findAll();

		assertNotNull(users);
		assertFalse(users.isEmpty());
	}

}
