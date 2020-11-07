package com.zpark.circle_shop.annotation;

import java.lang.annotation.*;

/**
 * @author Celery
 */
@Target({ElementType.METHOD})
@Retention(RetentionPolicy.RUNTIME)
@Documented
@Inherited
public @interface UserRegisterRecord {

}
