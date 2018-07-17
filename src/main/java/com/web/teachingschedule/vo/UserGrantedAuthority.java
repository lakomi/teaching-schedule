package com.web.teachingschedule.vo;

import lombok.Data;
import org.springframework.security.core.GrantedAuthority;

/**
 * 继承 security 提供的权限类
 * @author q
 */
@Data
public class UserGrantedAuthority implements GrantedAuthority {

    private String authority;

    public UserGrantedAuthority(String auth) {
        this.authority = auth;
    }
    @Override
    public String getAuthority() {
        return authority;
    }
}
