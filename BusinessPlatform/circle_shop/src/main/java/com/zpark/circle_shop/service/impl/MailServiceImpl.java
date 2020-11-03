package com.zpark.circle_shop.service.impl;

import com.zpark.circle_shop.entity.CircleUser;
import com.zpark.circle_shop.service.MailService;
import com.zpark.circle_shop.util.MailUtil;
import com.zpark.circle_shop.util.R;
import com.zpark.circle_shop.util.VerifyUtil;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.redis.core.RedisTemplate;
import org.springframework.mail.javamail.JavaMailSender;
import org.springframework.stereotype.Service;
import org.thymeleaf.TemplateEngine;

import java.util.Map;
import java.util.concurrent.TimeUnit;

/**
 * @author Celery
 */
@Service
public class MailServiceImpl implements MailService {

    @Autowired
    private RedisTemplate<String, Object> redisTemplate;

    @Autowired
    private JavaMailSender sender;

    @Autowired
    private TemplateEngine templateEngine;

    @Override
    public R sendVerify(CircleUser user) {
        //查看是否在2分钟内
        Object redisVerifyCode = redisTemplate.opsForValue().get("userRegisterEmail::" + user.getEmail());
        if (redisVerifyCode != null) {
            return R.error("请不要频繁的发送");
        }
        //生成一个验证码的Map
        Map<String, Object> verifyInfo = VerifyUtil.generateVerifyPic();
        //开辟线程发送邮件
        new Thread(() -> {
            //发送邮件
            MailUtil.sendVerifyHtmlEmail(user.getEmail(), (String) verifyInfo.get("verifyBase64"), sender, templateEngine);
        }).start();
        //将验证码的信息存入redis
        redisTemplate.opsForValue().set("userRegisterEmail::" + user.getEmail(), verifyInfo.get("verifyCode"), 120, TimeUnit.SECONDS);

        return R.ok("发送成功！");
    }

}
