package com.zpark.circle_shop.entity;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import lombok.experimental.Accessors;

import java.util.Date;

@Data
@Accessors(chain = true)
@AllArgsConstructor
@NoArgsConstructor
public class GoodsRecord {

    private Integer grId;
    private Integer gId;
    private Integer views;
    private Integer deals;
    private Date recordDate;

}
