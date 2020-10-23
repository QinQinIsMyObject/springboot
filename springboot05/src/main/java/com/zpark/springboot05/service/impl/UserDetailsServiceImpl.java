package com.zpark.springboot05.service.impl;

import com.zpark.springboot05.entity.Admin;
import com.zpark.springboot05.mapper.AdminMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.authority.AuthorityUtils;
import org.springframework.security.core.userdetails.User;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

import java.util.List;

/**
 * @author Celery
 */
@Service("userDetailsService")
public class UserDetailsServiceImpl implements UserDetailsService {
    @Autowired
    private PasswordEncoder passwordEncoder;

    @Autowired
    private AdminMapper adminMapper;

    @Override
    public UserDetails loadUserByUsername(String username) throws UsernameNotFoundException {
//        System.out.println(username);
        Admin admin = adminMapper.findAdminByName(username);
        List<GrantedAuthority> auths = AuthorityUtils.commaSeparatedStringToAuthorityList("modify,delete");
//        if (!"sam".equals(username)) {
//            throw new UsernameNotFoundException("用户名不存在！");
//        }
        if (admin == null) {
            throw new UsernameNotFoundException("用户名不存在！");
        }
//        return new User("sam", passwordEncoder.encode("123"), auths);
        return new User(admin.getName(), passwordEncoder.encode(admin.getPassword()), auths);
    }
}
