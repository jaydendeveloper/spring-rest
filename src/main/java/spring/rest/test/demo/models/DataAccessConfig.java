package spring.rest.test.demo.models;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

import spring.rest.test.demo.interfaces.UserDataAccess;
import spring.rest.test.demo.repositories.InMemoryUserRepository;
import spring.rest.test.demo.repositories.UserRepository;
@Configuration
public class DataAccessConfig {
    @Bean
    public UserDataAccess userDataAccess(
            AppConfig appConfig,
            InMemoryUserRepository inMemoryUserRepository,
            UserRepository userRepository) {

        if (appConfig.getDatabaseType() == DatabaseType.IN_MEMORY) {
            return inMemoryUserRepository;
        }
        return userRepository;
    }
}
