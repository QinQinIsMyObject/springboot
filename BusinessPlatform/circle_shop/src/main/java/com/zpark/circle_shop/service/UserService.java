package com.zpark.circle_shop.service;

import com.zpark.circle_shop.entity.CircleUser;

/**
 * @author Celery
 */
public interface UserService {

    boolean exists(CircleUser user);

    Integer register(CircleUser user, String verifyCode);

}
