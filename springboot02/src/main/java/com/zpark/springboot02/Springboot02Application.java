package com.zpark.springboot02;

import org.mybatis.spring.annotation.MapperScan;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

/**
 * @author Celery
 * MapperScan：Mapper扫描
 */
@MapperScan(basePackages = "com.zpark.springboot02.mapper")
@SpringBootApplication
public class Springboot02Application {

    public static void main(String[] args) {
        SpringApplication.run(Springboot02Application.class, args);
    }

}
