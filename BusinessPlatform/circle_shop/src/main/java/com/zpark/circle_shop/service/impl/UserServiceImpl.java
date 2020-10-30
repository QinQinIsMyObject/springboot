package com.zpark.circle_shop.service.impl;

import com.zpark.circle_shop.entity.CircleUser;
import com.zpark.circle_shop.mapper.UserMapper;
import com.zpark.circle_shop.service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class UserServiceImpl implements UserService {

    @Autowired
    private UserMapper userMapper;

    @Override
    public boolean exists(CircleUser user) {
        return userMapper.exists(user) != null;
    }

}
