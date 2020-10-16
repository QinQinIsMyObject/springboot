package com.zpark.springboot02.entity;

import lombok.Data;

import java.io.Serializable;

/**
 * @author Celery
 */
@Data
public class UCenter implements Serializable {
    private Integer uid;
    private String uname;
    private Integer age;
}
