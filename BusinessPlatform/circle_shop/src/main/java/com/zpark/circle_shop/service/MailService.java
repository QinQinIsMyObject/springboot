package com.zpark.circle_shop.service;

import com.zpark.circle_shop.entity.CircleUser;
import com.zpark.circle_shop.util.R;

/**
 * @author Celery
 */
public interface MailService {

    R sendVerify(CircleUser user);

}
