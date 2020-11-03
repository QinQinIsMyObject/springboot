package com.zpark.circle_shop.entity;

import lombok.Data;
import lombok.experimental.Accessors;

import java.io.Serializable;

/**
 * @author Celery
 */
@Data
@Accessors(chain = true)
public class Cart implements Serializable {

    private Integer cId;
    private Integer uId;
    private Integer gdId;
    private Integer amount;

    private String gId;
    private String pic;
    private String gName;
    private Double price;
    private Double special;

}
