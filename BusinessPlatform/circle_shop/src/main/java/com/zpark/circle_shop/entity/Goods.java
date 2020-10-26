package com.zpark.circle_shop.entity;

import lombok.Data;

import java.io.Serializable;
import java.util.Date;

/**
 * @author Celery
 * 商品类
 */
@Data
public class Goods implements Serializable {

    private Integer gId;
    private Integer cateId;
    private String gName;
    private String keywords;
    private String brand;
    private Integer state;
    private Date createTime;
    private Date updateTime;
    private String pic;

    /**
     * 商品的最低价格
     */
    private Double price;

    /**
     * 月销售量
     */
    private Integer sales;

}
