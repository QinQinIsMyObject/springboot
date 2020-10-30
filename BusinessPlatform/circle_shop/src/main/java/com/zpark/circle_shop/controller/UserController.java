package com.zpark.circle_shop.controller;

import com.zpark.circle_shop.entity.CircleUser;
import com.zpark.circle_shop.service.UserService;
import com.zpark.circle_shop.util.R;
import com.zpark.circle_shop.util.UserInfoUtil;
import io.netty.util.internal.StringUtil;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/api/user")
public class UserController {

    @Autowired
    private UserService userService;

    @GetMapping("/register/exists")
    public Object exists(CircleUser user) {
        if (user.getUName() == null && user.getEmail() == null
                ||
                user.getUName() != null && user.getEmail() != null) {
            return R.error("参数格式不正确！");
        }
        return userService.exists(user) ? R.error("已存在，请更换") : R.ok("恭喜您可以使用！");
    }

    @PostMapping("/register")
    public Object register(CircleUser user, String verifyCode) {
        if (UserInfoUtil.checkEmail(user.getEmail())
                &&
                UserInfoUtil.checkUsername(user.getUName())
                &&
                !StringUtil.isNullOrEmpty(user.getEmail())) {
            Integer result = userService.register(user, verifyCode);
            switch (result) {
                case 0:
                    return R.error("服务器异常！注册失败！");
                case 1:
                    return R.ok("注册成功！");
                case 2:
                    return R.error("用户名或邮箱已存在！");
                case 3:
                    return R.error("验证码错误！");
                default:
                    return R.error("未知错误！");
            }
        } else {
            return R.error("用户名、密码或邮箱格式不正确！");
        }
    }

}
