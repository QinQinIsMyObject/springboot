package com.zpark.circle_shop;

import org.mybatis.spring.annotation.MapperScan;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.cache.annotation.EnableCaching;
import org.springframework.security.config.annotation.method.configuration.EnableGlobalMethodSecurity;

/**
 * @author Celery
 */
@EnableGlobalMethodSecurity(prePostEnabled = true, securedEnabled = true)
@EnableCaching
@MapperScan("com.zpark.circle_shop.mapper")
@SpringBootApplication
public class CircleShopApplication {

    public static void main(String[] args) {
        SpringApplication.run(CircleShopApplication.class, args);
    }

}
