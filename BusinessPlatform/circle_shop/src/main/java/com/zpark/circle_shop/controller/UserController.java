package com.zpark.circle_shop.controller;

import com.zpark.circle_shop.entity.CircleUser;
import com.zpark.circle_shop.service.UserService;
import com.zpark.circle_shop.util.R;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
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

}
