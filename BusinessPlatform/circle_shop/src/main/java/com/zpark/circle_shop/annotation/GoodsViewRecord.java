package com.zpark.circle_shop.annotation;

import java.lang.annotation.*;

/**
 * @author Celery
 * 商品浏览量记录
 */
@Target({ElementType.METHOD})
@Retention(RetentionPolicy.RUNTIME)
@Documented
@Inherited
public @interface GoodsViewRecord {

}
