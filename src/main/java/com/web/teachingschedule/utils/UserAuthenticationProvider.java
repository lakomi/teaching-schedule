package com.web.teachingschedule.utils;

import com.web.teachingschedule.enums.TeachExceptionEnum;
import com.web.teachingschedule.exception.TeachingException;
import com.web.teachingschedule.service.impl.MyUserDetailServiceImpl;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.authentication.AuthenticationProvider;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.AuthenticationException;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Component;
import org.springframework.util.StringUtils;

/**
 * 身份验证  用户名与密码是否正确
 * @author q
 */
@Slf4j
@Component
public class UserAuthenticationProvider implements AuthenticationProvider {
    @Autowired
    private MyUserDetailServiceImpl myUserDetailService;

    @Override
    public Authentication authenticate(Authentication authentication) throws AuthenticationException {
        log.info("进入UserAuthenticationProvider的authenticate方法");

        //获取用户传来用户名和密码
        String userId = authentication.getName();
        String password = authentication.getCredentials().toString();

        log.info("用户传来的用户名和密码为，userId={}，password={}",userId,password);

        //根据用户名获取数据库中的用户名及密码
        UserDetails userDetails = myUserDetailService.loadUserByUsername(userId);

        if (StringUtils.isEmpty(userDetails)){
            throw new TeachingException(TeachExceptionEnum.GET_USERDETAILS_ERROR);
        }

        if(!password.equals(userDetails.getPassword())){
            log.info("密码错误，数据库中密码={}，用户输入密码={}",userDetails.getPassword(),password);
            throw new TeachingException(TeachExceptionEnum.PASSWORD_ERROR);
        }


        Authentication auth = new UsernamePasswordAuthenticationToken(userId,password,userDetails.getAuthorities());

        return auth;
    }

    @Override
    public boolean supports(Class<?> aClass) {
        return false;
    }
}
