package com.zpark.springboot05;

import org.mybatis.spring.annotation.MapperScan;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.security.config.annotation.method.configuration.EnableGlobalMethodSecurity;

/**
 * @author Celery
 * EnableGlobalMethodSecurity 开启spring security对注解的支持
 */
@EnableGlobalMethodSecurity(prePostEnabled = true)
@MapperScan("com.zpark.springboot05.mapper")
@SpringBootApplication
public class Springboot05Application {

    public static void main(String[] args) {
        SpringApplication.run(Springboot05Application.class, args);
    }

}
