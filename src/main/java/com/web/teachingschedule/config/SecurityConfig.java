package com.web.teachingschedule.config;

import com.web.teachingschedule.filter.JWTAuthenticationFilter;
import com.web.teachingschedule.filter.JWTLoginFilter;
import com.web.teachingschedule.service.impl.MyUserDetailServiceImpl;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.http.HttpMethod;
import org.springframework.security.config.annotation.authentication.builders.AuthenticationManagerBuilder;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.builders.WebSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.config.annotation.web.configuration.WebSecurityConfigurerAdapter;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.crypto.password.PasswordEncoder;

/**
 * security 配置
 * 添加jwt filter
 * @author q
 */
@Slf4j
@Configuration
@EnableWebSecurity
public class SecurityConfig extends WebSecurityConfigurerAdapter {

    @Autowired
    private MyUserDetailServiceImpl myUserDetailService;


    /**
     * 处理用户密码加密解密
     * @return
     */
    @Bean
    public PasswordEncoder passwordEncoder(){
        log.info("进入SecurityConfig的passwordEncoder");
        return new BCryptPasswordEncoder();
    }

    /**
     * 配置所有请求的访问控制
     * @param http
     * @throws Exception
     */
    @Override
    protected void configure(HttpSecurity http) throws Exception {

        log.info("进入SecurityConfig的configure(HttpSecurity http)");
        http.csrf().disable()           //关闭csrf
                .authorizeRequests()      //授权配置
                .antMatchers("/user/loginT","/user/loginR","/user/modifyPw").permitAll()
                .antMatchers("/root/*").hasRole("T")
                .anyRequest()          //任何请求
                .authenticated()      //身份认证
                .and()
                .addFilter(new JWTLoginFilter())
                .addFilter(new JWTAuthenticationFilter(authenticationManager()));
    }


    @Override
    protected void configure(AuthenticationManagerBuilder auth) throws Exception {
        log.info("进入SecurityConfig的configure(AuthenticationManagerBuilder auth)");
        //配置自定义的userDetailService
        auth.userDetailsService(myUserDetailService);
    }

    @Override
    public void configure(WebSecurity web) throws Exception {
        super.configure(web);
//        web.ignoring().antMatchers("/home/ubuntu/qiu/front");
    }


}
