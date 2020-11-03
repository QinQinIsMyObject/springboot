package com.zpark.circle_shop.entity;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import lombok.experimental.Accessors;

import java.io.Serializable;
import java.util.Date;

/**
 * @author Celery
 */
@Data
@Accessors(chain = true)
@AllArgsConstructor
@NoArgsConstructor
public class Order implements Serializable {

    private Integer oId;
    private String oNum;
    private String oName;
    private Integer uId;
    private Integer gdId;
    private Double price;
    private Integer amount;
    private Double totalPrice;
    private Integer state;
    private String details;
    private Date createTime;
    private Date updateTime;

}
