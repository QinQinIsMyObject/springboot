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

@Configuration
public class SecurityConfig extends WebSecurityConfigurerAdapter {

    @Autowired
    private UserDetailsService userDetailsService;

    @Override
    protected void configure(AuthenticationManagerBuilder auth) throws Exception {
        //设置用户名和密码的时候，有加密的概念(密码无加密）
        //auth.inMemoryAuthentication().withUser("sam").password("{noop}123").roles("admin");
        //设置主动加密后的密码
        // String password = passwordEncoder().encode("123");
        // auth.inMemoryAuthentication().withUser("sam").password(password).roles("");
        //设置userDetailsService为验证的服务类
        auth.userDetailsService(userDetailsService).passwordEncoder(passwordEncoder());
    }

    @Override
    protected void configure(HttpSecurity http) throws Exception {
        //关闭csrf防护
        http.csrf().disable();
        //设置登录成功上位handler
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
        http.authorizeRequests().antMatchers("/ucenter/**", "/admin/hello").permitAll();
        //配置不同的资源对应不同的权限
//        http.authorizeRequests().antMatchers("/admin/insert").hasAuthority("insert");
//        http.authorizeRequests().antMatchers("/admin/delete").hasAuthority("delete");
//        http.authorizeRequests().antMatchers("/admin/update").hasAuthority("update");
//        http.authorizeRequests().antMatchers("/admin/select").hasAuthority("select");
        //通过角色进行资源的限制
        http.authorizeRequests().antMatchers("/admin/**").hasRole("superAdmin");
        //检验所有的请求
        http.authorizeRequests().anyRequest().authenticated();
    }

    /**
     * 配置加密方式
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
            //登录成功后输出json
            ResponseUtil.responseJson(httpServletResponse, R.ok("登录成功！"));
        };
    }

    public AuthenticationEntryPoint authenticationEntryPoint() {
        return (httpServletRequest, httpServletResponse, e) -> {
            ResponseUtil.responseJson(httpServletResponse, R.error("您还未拥有权限！"));
        };
//        return new AuthenticationEntryPoint() {
//            @Override
//            public void commence(HttpServletRequest httpServletRequest, HttpServletResponse httpServletResponse, AuthenticationException e) throws IOException, ServletException {
////                if (e instanceof UsernameNotFoundException){
////                    ResponseUtil.responseJSON(httpServletResponse, R.error("用户名或密码错误！"));
////                }else if (e instanceof BadCredentialsException) {
////
////                }
////                System.out.println(e.getClass());
//                ResponseUtil.responseJSON(httpServletResponse, R.error("您还未经过验证"));
//            }
//        };
    }

    public AuthenticationFailureHandler failureHandler() {
        return (httpServletRequest, httpServletResponse, e) -> {
            ResponseUtil.responseJson(httpServletResponse, R.error("用户名或密码错误！"));
        };
    }

    public LogoutHandler logoutHandler() {
        return (httpServletRequest, httpServletResponse, authentication) -> {
            ResponseUtil.responseJson(httpServletResponse, R.ok("退出登录"));
        };
    }

    public AccessDeniedHandler accessDeniedHandler() {
        return (httpServletRequest, httpServletResponse, e) -> {
            ResponseUtil.responseJson(httpServletResponse, R.error("您的权限不足！"));
        };

    }
}




