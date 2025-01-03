package ua.gym.config;

import org.springframework.context.annotation.ComponentScan;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.PropertySource;

@Configuration
@ComponentScan(basePackages = "ua.gym")
@PropertySource("classpath:application.properties")
public class SpringConfig {

}
