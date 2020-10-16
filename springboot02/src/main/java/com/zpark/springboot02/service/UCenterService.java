package com.zpark.springboot02.service;

import com.zpark.springboot02.util.R;

/**
 * @author Celery
 */
public interface UCenterService {

    /**
     * @param pageNum
     * @return
     */
    R findUCenterInPage(Integer pageNum);
}
