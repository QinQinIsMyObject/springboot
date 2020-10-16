package com.zpark.springboot01.entity;

import lombok.Data;

import java.io.Serializable;

/**
 * @author Celery
 */
@Data
public class TestObject implements Serializable {
    private Integer a;
    private String b;
}
