package com.zpark.springboot02.service;

import com.zpark.springboot02.entity.MailInfo;
import com.zpark.springboot02.util.R;

/**
 * @author Celery
 */
public interface MailService {

    /**
     * @param mailInfo
     * @return
     */
    R sendEmail(MailInfo mailInfo);

    /**
     * @param mailInfo
     * @return
     */
    R sendVerifyCode(MailInfo mailInfo);

}
