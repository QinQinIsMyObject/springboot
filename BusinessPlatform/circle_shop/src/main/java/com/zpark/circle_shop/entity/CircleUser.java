package com.zpark.circle_shop.entity;

import lombok.Data;
import lombok.experimental.Accessors;

import java.io.Serializable;
import java.util.Date;

/**
 * @author Celery
 */
@Data
@Accessors(chain = true)
public class CircleUser implements Serializable {

    private Integer id;
    private String uName;
    private String pwd;
    private String email;
    private String avatar;
    private Date birthday;
    private Integer credit;
    private Integer points;
    private Integer state;
    private Date createTime;
    private Date updateTime;

}
