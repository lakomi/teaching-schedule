package com.web.teachingschedule.service.impl;

import com.web.teachingschedule.dao.CourseDAO;
import com.web.teachingschedule.dao.UserDAO;
import com.web.teachingschedule.enums.BackMessageEnum;
import com.web.teachingschedule.enums.TeachExceptionEnum;
import com.web.teachingschedule.exception.TeachingException;
import com.web.teachingschedule.filter.JWTLoginFilter;
import com.web.teachingschedule.service.UserInfoService;
import com.web.teachingschedule.utils.AuthenticationUtil;
import com.web.teachingschedule.utils.ResultVoUtil;
import com.web.teachingschedule.utils.UserAuthenticationProvider;
import com.web.teachingschedule.vo.CourseVo;
import com.web.teachingschedule.vo.ResultVo;
import com.web.teachingschedule.vo.Teachers;
import com.web.teachingschedule.vo.dto.LoginDTO;
import com.web.teachingschedule.vo.dto.ModifypwDTO;
import com.web.teachingschedule.vo.entity.Course;
import com.web.teachingschedule.vo.entity.UserInfo;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.Authentication;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.util.StringUtils;

import javax.servlet.http.HttpServletResponse;
import java.util.ArrayList;
import java.util.List;

/**
 * 用户信息接口实现
 * @author q
 */
@Slf4j
@Service
public class UserInfoServiceImpl implements UserInfoService {

    @Autowired
    private UserDAO userDAO;

    @Autowired
    private UserAuthenticationProvider userAuthenticationProvider;

    private JWTLoginFilter jwtLoginFilter = new JWTLoginFilter();

    @Override
    public ResultVo loginT(LoginDTO loginDTO, HttpServletResponse response) {
        log.info("进入UserBasicInfoServiceImpl的userLogin");
        //解析请求,从request中取出authentication
        Authentication authentication = jwtLoginFilter.attemptAuthentication(loginDTO);
        //验证用户名和密码
        Authentication resultAuthentication = userAuthenticationProvider.authenticate(authentication);
        //验证通过，生成token，放入response
        ResultVo resultVo = jwtLoginFilter.successfulAuthentication(response, resultAuthentication);
        return resultVo;
    }

    @Override
    public ResultVo loginR(LoginDTO loginDTO, HttpServletResponse response) {
        log.info("进入UserBasicInfoServiceImpl的userLogin");
        //解析请求,从request中取出authentication
        Authentication authentication = jwtLoginFilter.attemptAuthentication(loginDTO);
        //验证用户名和密码
        Authentication resultAuthentication = userAuthenticationProvider.authenticate(authentication);
        UserInfo userInfo = userDAO.getUserById(loginDTO.getUserId());
        if (!userInfo.getUserRole().contains("A")){
            throw new TeachingException(TeachExceptionEnum.USERID_NOT_EXIT);
        }
        //验证通过，生成token，放入response
        ResultVo resultVo = jwtLoginFilter.successfulAuthentication(response, resultAuthentication);
        return resultVo;
    }

    @Override
    public ResultVo userModifyPw(ModifypwDTO modifypwDTO) {

        UserInfo userInfo = userDAO.getUserById(modifypwDTO.getUserId());
        //用户名不存在
        if (StringUtils.isEmpty(userInfo)){
            throw new TeachingException(TeachExceptionEnum.USERID_NOT_EXIT);
        }
        //验证原密码
        if (!modifypwDTO.getOldPassword().equals(userInfo.getPassword())){
            throw new TeachingException(TeachExceptionEnum.OLD_PASSWORD_ERROR);
        }
        int i = userDAO.modifyPw(userInfo.getUserId(),modifypwDTO.getNewPassword());
        //数据库修改操作出问题
        if (i != 1){
            throw new TeachingException(TeachExceptionEnum.SQL_ERROR);
        }

        return ResultVoUtil.success(BackMessageEnum.MODIFY_SUCCESS.getMessage());
    }

    @Override
    public ResultVo selectTeachers() {
        List<Teachers> teachersList = userDAO.getTeachersByRole();
        return ResultVoUtil.success(teachersList);
    }

    @Override
    public ResultVo getTeacherInfo() {
        String userId = AuthenticationUtil.getAuthUserId();
        log.info("token中的userId: "+userId);
        Teachers teachers = new Teachers();
        UserInfo userInfo = userDAO.getUserById(userId);
        teachers.setTeacherId(userInfo.getUserId());
        teachers.setTeacherName(userInfo.getUserName());
        return ResultVoUtil.success(teachers);
    }

    @Override
    public ResultVo rootAddTeacher(String userId, String userName) {

        UserInfo userInfo = new UserInfo();
        userInfo.setUserId(userId);
        userInfo.setUserName(userName);
        userInfo.setPassword("123456");
        userInfo.setUserRole("T");
        int i = userDAO.addUser(userInfo);
        if (i != 1){
            throw new TeachingException(TeachExceptionEnum.SQL_ERROR);
        }
        return ResultVoUtil.success(BackMessageEnum.ADD_SUCCESS.getMessage());
    }

    @Override
    public ResultVo rootDelTeacher(String userId) {

        int i = userDAO.deleteUser(userId);
        if (i != 1){
            throw new TeachingException(TeachExceptionEnum.SQL_ERROR);
        }
        return ResultVoUtil.success(BackMessageEnum.DEL_SUCCESS.getMessage());
    }

    @Transactional
    @Override
    public ResultVo rootDelTeacherList(List<String> userIds) {
        int i = -1;
        for (String userId:userIds){
            System.out.println("正在删除userId= " + userId);
            i = userDAO.deleteUser(userId);
            if (i != 1){
                System.out.println("i=" + i);
                throw new TeachingException(TeachExceptionEnum.SQL_ERROR);
            }
        }
        return ResultVoUtil.success(BackMessageEnum.DEL_SUCCESS.getMessage());
    }

    @Override
    public ResultVo resetPw(String userId) {
        int i = userDAO.resetPw(userId);
        if (i != 1){
            throw new TeachingException(TeachExceptionEnum.SQL_ERROR);
        }
        return ResultVoUtil.success(BackMessageEnum.RESET_SUCCESS.getMessage());
    }




}
