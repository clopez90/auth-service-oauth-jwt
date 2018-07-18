package config;

import org.springframework.context.annotation.ComponentScan;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.Profile;
import org.springframework.data.jpa.repository.config.EnableJpaRepositories;


@Profile("test")
@Configuration
@ComponentScan(basePackages = "com.clopeza.api.login.service")
@EnableJpaRepositories(basePackages = "com.clopeza.api.login.model.repository")
public class TestConfiguration {


}
