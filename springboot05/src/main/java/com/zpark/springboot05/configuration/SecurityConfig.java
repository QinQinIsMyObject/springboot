package com.zpark.springboot05.configuration;

import com.zpark.springboot05.util.R;
import com.zpark.springboot05.util.ResponseUtil;
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
//        super.configure(auth);

        //设置用户名和密码时，有加密的概念
//        auth.inMemoryAuthentication().withUser("sam").password("{noop}123").roles("admin");

//        String password = passwordEncoder().encode("123");
//        auth.inMemoryAuthentication().withUser("sam").password(password).roles("");

        //设置userDetailsService为验证的服务类
        auth.userDetailsService(userDetailsService).passwordEncoder(passwordEncoder());

    }

    @Override
    protected void configure(HttpSecurity http) throws Exception {
//        super.configure(http);

        //关闭csrf防护
        http.csrf().disable();
        //设置登录成功的handler(处理器)
        http.formLogin().successHandler(successHandler());
        //配置未验证状态请求数据所返回的内容
        http.exceptionHandling().authenticationEntryPoint(authenticationEntryPoint());
        //设置登录失败的handler
        http.formLogin().failureHandler(failureHandler());
        //设置登出的返回结果
        http.logout().addLogoutHandler(logoutHandler());
        //配置不需要spring security进行权限验证的资源
        http.authorizeRequests().antMatchers("/ucenter/**", "/admin/hello").permitAll();
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
            //登录成功后输出json
            ResponseUtil.responseJSON(httpServletResponse, R.ok("登录成功！"));
        };
    }

    public AuthenticationEntryPoint authenticationEntryPoint() {
        return (httpServletRequest, httpServletResponse, e) -> {
            ResponseUtil.responseJSON(httpServletResponse, R.error("您还未拥有权限！"));
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
            ResponseUtil.responseJSON(httpServletResponse, R.error("用户名或密码错误！"));
        };
    }

    public LogoutHandler logoutHandler() {
        return (httpServletRequest, httpServletResponse, e) -> {
            ResponseUtil.responseJSON(httpServletResponse, R.ok("登出成功！"));
        };
    }

}
