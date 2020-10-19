package com.zpark.springboot03.service;

import com.zpark.springboot03.entity.Ucenter;
import com.zpark.springboot03.util.R;

/**
 * @author Celery
 */
public interface UcenterService {
    R sendVerifyCode(Ucenter ucenter);
}
