package com.zpark.springboot03.service.impl;

import com.zpark.springboot03.entity.Ucenter;
import com.zpark.springboot03.service.UcenterService;
import com.zpark.springboot03.util.MailUtil;
import com.zpark.springboot03.util.R;
import com.zpark.springboot03.util.VerifyUtil;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.redis.core.RedisTemplate;
import org.springframework.mail.javamail.JavaMailSender;
import org.springframework.stereotype.Service;
import org.springframework.util.StringUtils;
import org.thymeleaf.TemplateEngine;

import java.util.concurrent.TimeUnit;

/**
 * @author Celery
 */
@Service
public class UcenterServiceImpl implements UcenterService {

    @Autowired
    private JavaMailSender javaMailSender;

    @Autowired
    private RedisTemplate<String, Object> redisTemplate;

    @Autowired
    private TemplateEngine templateEngine;

    @Override
    public R sendVerifyCode(Ucenter ucenter) {
        //从redis中取出数据
        String redisVerifyCode = (String) redisTemplate.opsForValue().get(ucenter.getEmail());

        //如果有数据，说明还没有过120s，就不能继续发送验证码
        if (!StringUtils.isEmpty(redisVerifyCode)) {
            return R.error("请不要频繁发送验证码！");
        }

        //得到验证码
        String verifyCode = VerifyUtil.generateVerifyCode(6);

        //发送简单邮件
        String content = "欢迎注册！你的验证码是：" + verifyCode + "。打死都不要不告诉别人";
        //准备异步代码（开辟新线程），必须要有匿名内部类才可使用箭头函数
        new Thread(() -> {
//            MailUtil.sendSimpleEmail(ucenter.getEmail(), "验证码", content, javaMailSender);
            MailUtil.sendVerifyEmail(ucenter.getEmail(), verifyCode, javaMailSender, templateEngine);
        }).start();

        //将验证码存入redis
        redisTemplate.opsForValue().set(ucenter.getEmail(), verifyCode);
        //设置到期时间
        redisTemplate.expire(ucenter.getEmail(), 120, TimeUnit.SECONDS);
        return R.ok("发送成功！");
    }
}
