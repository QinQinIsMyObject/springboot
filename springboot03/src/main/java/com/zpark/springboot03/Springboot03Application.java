package com.zpark.springboot03;

import org.mybatis.spring.annotation.MapperScan;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.transaction.annotation.EnableTransactionManagement;

/**
 * @author Celery
 * EnableTransactionManagement   开启事物管理机制
 */
@MapperScan(basePackages = "com.zpark.springboot03.mapper")
@SpringBootApplication
@EnableTransactionManagement
public class Springboot03Application {

    public static void main(String[] args) {
        SpringApplication.run(Springboot03Application.class, args);
    }

}
