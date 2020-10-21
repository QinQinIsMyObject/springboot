package com.zpark.springboot04.configuration;

import org.springframework.boot.web.servlet.MultipartConfigFactory;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.util.unit.DataSize;

import javax.servlet.MultipartConfigElement;

/**
 * @author Celery
 */
@Configuration
public class MultipartFileConfig {

    /**
     * 配置上传文件大小
     *
     * @return
     */
    @Bean
    public MultipartConfigElement multipartConfigElement() {
        MultipartConfigFactory factory = new MultipartConfigFactory();
        //单个数据大小 1GB
        factory.setMaxFileSize(DataSize.ofGigabytes(1));
        //总上传数据大小 4GB
        factory.setMaxRequestSize(DataSize.ofGigabytes(4));
        return factory.createMultipartConfig();
    }

}
