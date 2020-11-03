package com.zpark.circle_shop.controller;

import com.zpark.circle_shop.entity.CircleUser;
import com.zpark.circle_shop.service.MailService;
import com.zpark.circle_shop.util.R;
import com.zpark.circle_shop.util.UserInfoUtil;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

/**
 * @author Celery
 */
@RestController
@RequestMapping("/api/mail")
public class MailController {

    @Autowired
    private MailService mailService;

    @PostMapping("/sendVerify")
    public Object sendVerify(@RequestBody CircleUser user) {
        //验证邮件格式
        if (!UserInfoUtil.checkEmail(user.getEmail())) {
            return R.error("邮箱格式不正确！");
        }
        //验证邮箱是否被注册
        return mailService.sendVerify(user);
    }

}
