package spring.rest.test.demo.config;

import org.springframework.boot.context.properties.ConfigurationProperties;
import org.springframework.context.annotation.Configuration;

import spring.rest.test.demo.models.DatabaseType;

@Configuration
@ConfigurationProperties(prefix = "app")
public class AppConfig {
    private DatabaseType databaseType = DatabaseType.IN_MEMORY;

    public DatabaseType getDatabaseType() {
        return databaseType;
    }

    public void setDatabaseType(DatabaseType databaseType) {
        this.databaseType = databaseType;
    }
}
