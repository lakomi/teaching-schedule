package com.web.teachingschedule.service.impl;

import com.web.teachingschedule.dao.TeachingClassDAO;
import com.web.teachingschedule.dao.UserDAO;
import com.web.teachingschedule.service.TeachingClassService;
import com.web.teachingschedule.service.UserInfoService;
import com.web.teachingschedule.utils.ResultVoUtil;
import com.web.teachingschedule.vo.ResultVo;
import com.web.teachingschedule.vo.entity.TeachingClass;
import com.web.teachingschedule.vo.entity.UserInfo;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.annotation.AuthenticationPrincipal;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;


@Service
@Slf4j
public class TeachingClassServiceImpl implements TeachingClassService {
    @Autowired
    private TeachingClassDAO teachingClassDAO;
    @Autowired
    private UserDAO userDAO;
    @Override
    public ResultVo selectClassList() {
        List<TeachingClass> teachingClassList = teachingClassDAO.getClasses();
        return ResultVoUtil.success(teachingClassList);
    }

    @Override
    public ResultVo selectClassByTeacher(String userName) {

        UserInfo userInfo = userDAO.getUserByName(userName);
        List<TeachingClass> teachingClassList = teachingClassDAO.getClassesByUserId(userInfo.getUserId());
        List<String> classIds = new ArrayList<>();
         for (TeachingClass teachingClass:teachingClassList){
            classIds.add(teachingClass.getClassId());

        }
        return ResultVoUtil.success(classIds);
    }
}
