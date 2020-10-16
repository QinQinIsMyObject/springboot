package com.zpark.springboot02.service.impl;

import com.zpark.springboot02.entity.MailInfo;
import com.zpark.springboot02.service.MailService;
import com.zpark.springboot02.util.MailUtil;
import com.zpark.springboot02.util.R;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.mail.javamail.JavaMailSender;
import org.springframework.stereotype.Service;

/**
 * @author Celery
 */
@Service
public class MailServiceImpl implements MailService {

    @Autowired
    private JavaMailSender sender;

    @Override
    public R sendEmail(MailInfo mailInfo) {
        //调用工具类
        MailUtil.sendSimpleEmail(mailInfo.getTo(), mailInfo.getTitle(), mailInfo.getContent(), sender);
        return R.ok("发送成功！");
    }

    /**
     * 验证码
     *
     * @param mailInfo
     * @return
     */
    @Override
    public R sendVerifyCode(MailInfo mailInfo) {
        //使用Redis存储验证码
        //需要生成token
        //需要多线程操作（异步代码）帮助我们发送邮件
        return null;
    }

}
