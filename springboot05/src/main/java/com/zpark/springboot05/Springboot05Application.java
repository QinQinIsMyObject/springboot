package com.zpark.springboot05;

import org.mybatis.spring.annotation.MapperScan;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

@MapperScan("com.zpark.springboot05.mapper")
@SpringBootApplication
public class Springboot05Application {

    public static void main(String[] args) {
        SpringApplication.run(Springboot05Application.class, args);
    }

}
