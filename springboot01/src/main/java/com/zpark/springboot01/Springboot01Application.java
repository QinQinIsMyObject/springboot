package com.zpark.springboot01;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

/**
 * @author Celery
 * 这个类是当前项目的启动类
 * 1、将tomcat集成在springboot中，可以将项目打成.jar包，只要有Java环境就可以运行；
 * 2、约定大于配置；
 * 3、启动器：如果需要使用spring boot去整合其他框架或工具，只需要在依赖中增加对应的启动器即可；
 * 4、自动装配；
 */
@SpringBootApplication
public class Springboot01Application {

    public static void main(String[] args) {
        SpringApplication.run(Springboot01Application.class, args);
    }

}
