package com.zpark.circle_shop.entity;

import lombok.Data;

import java.io.Serializable;

/**
 * @author Celery
 * 商品细节信息类
 */
@Data
public class GoodsDetail implements Serializable {

    private Integer gdId;
    private Integer gId;
    private String model;
    private String color;
    private Double price;
    private Integer amount;
    private String pic;
    private Double special;

}
