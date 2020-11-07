package com.zpark.circle_shop.entity;

import lombok.Data;
import lombok.experimental.Accessors;

import java.util.Date;

@Data
@Accessors(chain = true)
public class UserRecord {

    private Integer urId;
    private Integer visits;
    private Integer regists;
    private Date recordDate;

}
