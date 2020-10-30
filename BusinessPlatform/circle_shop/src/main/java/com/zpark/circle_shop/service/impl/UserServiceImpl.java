package com.zpark.circle_shop.service.impl;

import com.zpark.circle_shop.entity.CircleUser;
import com.zpark.circle_shop.mapper.UserMapper;
import com.zpark.circle_shop.service.UserService;
import io.netty.util.internal.StringUtil;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.redis.core.RedisTemplate;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

@Service
public class UserServiceImpl implements UserService {

    @Autowired
    private UserMapper userMapper;

    @Autowired
    private RedisTemplate<String, Object> redisTemplate;

    @Autowired
    private PasswordEncoder encoder;

    @Override
    public boolean exists(CircleUser user) {
        return userMapper.exists(user) != null;
    }

    @Override
    public Integer register(CircleUser user, String verifyCode) {
        //检验验证码是否正确
        String redisVerifyCode = (String) redisTemplate.opsForValue().get("userRegisterEmail::" + user.getEmail());
        if (StringUtil.isNullOrEmpty(redisVerifyCode) || !redisVerifyCode.equalsIgnoreCase(verifyCode)) {
            return 3;
        }
        String uName = user.getUName();
        String email = user.getEmail();
        //检验用户名或邮箱是否存在
        if (!exists(user.setUName(null)) && !exists(user.setUName(uName).setEmail(null))) {
            //重新设置email
            user.setEmail(email);
            //对密码进行加密
            String pwd = encoder.encode(user.getPwd());
            //设置加密后的密码
            user.setPwd(pwd);
            Integer result = userMapper.userRegister(user);
            return result;
        } else {
            //用户名或邮箱
            return 2;
        }
    }

}
