package com.web.teachingschedule.filter;

import com.web.teachingschedule.enums.TeachExceptionEnum;
import com.web.teachingschedule.vo.UserGrantedAuthority;
import io.jsonwebtoken.Claims;
import io.jsonwebtoken.Jwts;
import lombok.extern.slf4j.Slf4j;
import org.springframework.http.HttpStatus;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.web.authentication.www.BasicAuthenticationFilter;
import org.springframework.util.StringUtils;

import javax.servlet.FilterChain;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;

/**
 * token的校验
 * 该类继承自BasicAuthenticationFilter，在doFilterInternal方法中，
 * 从http头的Authorization 项读取token数据，然后用Jwts包提供的方法校验token的合法性。
 * 如果校验通过，就认为这是一个取得授权的合法请求
 * @author q
 */
@Slf4j
public class JWTAuthenticationFilter extends BasicAuthenticationFilter {
    /**
     *  私钥
     */
    private String key = "spring-security-@Jwt!&Secret^#";

    /**
     * token的开头
     */
    private String tokenHead = "Bearer ";

    /**
     * token头部
     */
    private String tokenHeader = "Authorization";

    public JWTAuthenticationFilter(AuthenticationManager authenticationManager) {
        super(authenticationManager);
    }

    @Override
    protected void doFilterInternal(HttpServletRequest request,
                                    HttpServletResponse response,
                                    FilterChain chain) throws IOException, ServletException {

        log.info("进入JWTAuthenticationFilter的doFilterInternal方法。校验token");
        System.out.println("请求方式: "+ request.getMethod());

        if (request.getMethod().equals("OPTIONS")){
            response.setHeader("Access-Control-Allow-Origin", "*");
            response.setHeader("Access-Control-Allow-Methods", "POST, GET, OPTIONS, DELETE");
            response.addHeader("Access-Control-Allow-Credentials", "true");
            response.addHeader("Access-Control-Allow-Headers", "Origin, X-Requested-With, Content-Type, Accept,Authorization");
            response.addHeader("Access-Control-Expose-Headers", "Origin, X-Requested-With, Content-Type, Accept,Authorization");
            return;
        }

        //取得token内容
        String token = request.getHeader(tokenHeader);

        log.info("从头部获取的token={}",token);

        if(StringUtils.isEmpty(token)||!token.startsWith(tokenHead)){
            chain.doFilter(request,response);
            return;
        }
        UsernamePasswordAuthenticationToken authenticationToken = getAuthentication(token);
        SecurityContextHolder.getContext().setAuthentication(authenticationToken);
        chain.doFilter(request,response);

    }

    public UsernamePasswordAuthenticationToken getAuthentication(String responseToken){
        log.info("进入JWTAuthenticationFilter的getAuthentication校验token");
        Claims claims;
        //userId 用户名
        String userId = null;
        //GrantedAuthority是security提供的权限类
        List<UserGrantedAuthority> roles = new ArrayList<UserGrantedAuthority>();
        List<UserGrantedAuthority> userGrantedAuthorityList = new ArrayList<UserGrantedAuthority>();
        String token = responseToken.split("\\s+")[1];

        log.info("检验中分离出来的token={}",token);

        if (!StringUtils.isEmpty(token)){
            try {
                //解析token
                claims = Jwts.parser()
                        .setSigningKey(key)
                        .parseClaimsJws(token)
                        .getBody();
                userId =  (String) claims.get("name");
                roles = (List<UserGrantedAuthority>) claims.get("role");
                log.info("userId: "+userId);
                log.info("roles: "+roles);
            }catch (Exception e) {
                e.printStackTrace();
                //token过期，重新登录
                userId = TeachExceptionEnum.REPEAT_LOGIN.getMessage();
            }
        }else{
            //token为空，先登录
            userId = TeachExceptionEnum.PLEASE_LOGIN_FIRST.getMessage();
        }
        log.info("userId={}",userId);

        //从token中获取到角色，添加到 GrantedAuthorityList 中以便后边查验
        String[] tempRoles = roles.toString().split(",");
        for(int i=0;i<tempRoles.length;i++){
            String tempRole = org.apache.commons.lang.StringUtils.substringBetween(tempRoles[i],"=","}");
            userGrantedAuthorityList.add(new UserGrantedAuthority(tempRole));
        }

        log.info("权限角色："+userGrantedAuthorityList.toString());
        return new UsernamePasswordAuthenticationToken(userId,null,userGrantedAuthorityList);


    }




}
