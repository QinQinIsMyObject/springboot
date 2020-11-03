package com.zpark.circle_shop.service.impl;

import com.zpark.circle_shop.entity.CircleUser;
import com.zpark.circle_shop.mapper.UserMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.userdetails.User;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;

import javax.servlet.http.HttpSession;
import java.util.ArrayList;

/**
 * @author Celery
 */
@Service("userDetailsService")
public class UserDetailsServiceImpl implements UserDetailsService {

    @Autowired
    private UserMapper userMapper;

    @Autowired
    private HttpSession session;

    @Override
    public UserDetails loadUserByUsername(String username) throws UsernameNotFoundException {
        CircleUser circleUser = userMapper.userLogin(new CircleUser().setUName(username));
        if (circleUser == null) {
            throw new UsernameNotFoundException("该用户不存在!");
        }
        String password = circleUser.getPwd();
        circleUser.setPwd(null);
        //将查到的circleUser存入session
        session.setAttribute("circleUser", circleUser);
        return new User(circleUser.getUName(), password, new ArrayList<>());
    }

}
