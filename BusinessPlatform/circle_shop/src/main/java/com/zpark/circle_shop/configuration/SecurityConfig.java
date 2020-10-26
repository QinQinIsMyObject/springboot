package com.zpark.circle_shop.configuration;

import com.zpark.circle_shop.util.R;
import com.zpark.circle_shop.util.ResponseUtil;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.config.annotation.authentication.builders.AuthenticationManagerBuilder;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.WebSecurityConfigurerAdapter;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.security.web.AuthenticationEntryPoint;
import org.springframework.security.web.access.AccessDeniedHandler;
import org.springframework.security.web.authentication.AuthenticationFailureHandler;
import org.springframework.security.web.authentication.AuthenticationSuccessHandler;
import org.springframework.security.web.authentication.logout.LogoutHandler;

/**
 * @author Celery
 */
@Configuration
public class SecurityConfig extends WebSecurityConfigurerAdapter {

    @Autowired
    private UserDetailsService userDetailsService;

    @Override
    protected void configure(AuthenticationManagerBuilder auth) throws Exception {
        auth.userDetailsService(userDetailsService).passwordEncoder(passwordEncoder());
    }

    @Override
    protected void configure(HttpSecurity http) throws Exception {
        //关闭csrf防护
        http.csrf().disable();
        //设置登录成功的handler
        http.formLogin().successHandler(successHandler());
        //配置未验证状态请求数据所返回的内容
        http.exceptionHandling().authenticationEntryPoint(authenticationEntryPoint());
        //设置登录失败的handler
        http.formLogin().failureHandler(failureHandler());
        //设置登出的handler
        http.logout().addLogoutHandler(logoutHandler());
        //配置权限不足的handler
        http.exceptionHandling().accessDeniedHandler(accessDeniedHandler());
        //配置不需要spring security进行权限验证的资源
        http.authorizeRequests().antMatchers("/api/goods/**").permitAll();
        //检验所有的请求
        http.authorizeRequests().anyRequest().authenticated();
    }

    /**
     * 配置密码的加密类
     *
     * @return
     */
    @Bean
    public PasswordEncoder passwordEncoder() {
        return new BCryptPasswordEncoder();
    }

    @Bean
    public AuthenticationSuccessHandler successHandler() {
        return (httpServletRequest, httpServletResponse, authentication) -> {
            ResponseUtil.responseJson(httpServletResponse, R.ok("登录成功！"));
        };
    }

    public AuthenticationEntryPoint authenticationEntryPoint() {
        return (httpServletRequest, httpServletResponse, e) -> {
            ResponseUtil.responseJson(httpServletResponse, R.error("您还未登录！"));
        };
    }

    public AuthenticationFailureHandler failureHandler() {
        return (httpServletRequest, httpServletResponse, e) -> {
            ResponseUtil.responseJson(httpServletResponse, R.error("用户名或密码错误！"));
        };
    }

    public LogoutHandler logoutHandler() {
        return (httpServletRequest, httpServletResponse, authentication) -> {
            ResponseUtil.responseJson(httpServletResponse, R.ok("登出成功！"));
        };
    }

    public AccessDeniedHandler accessDeniedHandler() {
        return (httpServletRequest, httpServletResponse, e) -> {
            ResponseUtil.responseJson(httpServletResponse, R.error("您的权限不足！"));
        };
    }

}
