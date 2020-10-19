package com.zpark.springboot03.entity;

import lombok.Data;

import java.io.Serializable;

/**
 * @author Celery
 */
@Data
public class Goods implements Serializable {
    private Integer gid;
    private String gname;
    private Double gprice;
    private Integer gnum;
}
