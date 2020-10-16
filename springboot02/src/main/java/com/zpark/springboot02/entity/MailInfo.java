package com.zpark.springboot02.entity;

import lombok.Data;

import java.io.Serializable;

/**
 * @author Celery
 */
@Data
public class MailInfo implements Serializable {
    private String to;
    private String title;
    private String content;
}
