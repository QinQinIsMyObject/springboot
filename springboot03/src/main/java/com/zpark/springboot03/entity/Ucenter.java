package com.zpark.springboot03.entity;

import lombok.Data;

import java.io.Serializable;

/**
 * @author Celery
 */
@Data
public class Ucenter implements Serializable {
    private Integer uid;
    private String email;
    private String password;
}
