package com.web.teachingschedule.service.impl;

import com.web.teachingschedule.dao.UserDAO;
import com.web.teachingschedule.enums.TeachExceptionEnum;
import com.web.teachingschedule.exception.TeachingException;
import com.web.teachingschedule.vo.UserGrantedAuthority;
import com.web.teachingschedule.vo.entity.UserInfo;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.core.userdetails.User;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Component;
import org.springframework.util.StringUtils;

import java.util.ArrayList;
import java.util.List;

/**
 * 返回用户名和密码及权限
 * @author q
 */
@Slf4j
@Component
public class MyUserDetailServiceImpl implements UserDetailsService {

    @Autowired
    private UserDAO userDAO;

    @Override
    public UserDetails loadUserByUsername(String userId) throws UsernameNotFoundException {
        log.info("进入MyUserDetailService的loadUserByUsername方法");
        //根据用户名查找用户信息
        UserInfo userInfo = userDAO.getUserById(userId);
        if(StringUtils.isEmpty(userInfo)){
            log.info("用户名不存在，userId={}",userId);
            throw new TeachingException(TeachExceptionEnum.USERID_NOT_EXIT);
        }
        log.info("登录用户名：userId={}",userId);
        String password = userInfo.getPassword();

        //GrantedAuthority是security提供的权限类，
        List<UserGrantedAuthority> grantedAuthorityList = new ArrayList<UserGrantedAuthority>();
        //获取该用户角色，并放入list中
        getRoles(userInfo,grantedAuthorityList);


        return new User(userId,password,grantedAuthorityList);
    }

    public void getRoles(UserInfo userInfo,List<UserGrantedAuthority> list){
        for (String role:userInfo.getUserRole().split(",")) {
            //权限如果前缀是ROLE_，security就会认为这是个角色信息，而不是权限，例如ROLE_MENBER就是MENBER角色，CAN_SEND就是CAN_SEND权限
             list.add(new UserGrantedAuthority("ROLE_"+role));
        }
    }


}
