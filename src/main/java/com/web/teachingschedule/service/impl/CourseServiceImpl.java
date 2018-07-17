package com.web.teachingschedule.service.impl;

import com.web.teachingschedule.dao.CourseDAO;
import com.web.teachingschedule.dao.TeachingClassDAO;
import com.web.teachingschedule.dao.UserDAO;
import com.web.teachingschedule.service.CourseService;
import com.web.teachingschedule.utils.ResultVoUtil;
import com.web.teachingschedule.utils.TransformToDTO;
import com.web.teachingschedule.vo.CourseVo;
import com.web.teachingschedule.vo.ResultVo;
import com.web.teachingschedule.vo.entity.Course;
import com.web.teachingschedule.vo.entity.TeachingClass;
import com.web.teachingschedule.vo.entity.UserInfo;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

/**
 * 课程接口实现
 * @author q
 */
@Service
@Slf4j
public class CourseServiceImpl implements CourseService {

    @Autowired
    private CourseDAO courseDAO;
    @Autowired
    private TeachingClassDAO teachingClassDAO;

    @Override
    public ResultVo getCourseList() {
        List<Course> courseList = courseDAO.getCourses();
        List<CourseVo> courseVoList = TransformToDTO.coursesTocourseVos(courseList);
        return ResultVoUtil.success(courseVoList);
    }

    @Override
    public ResultVo getClassList(String courseId) {
        List<TeachingClass> teachingClassList = teachingClassDAO.getClassesByCourseId(courseId);
        List<String> classIdList = TransformToDTO.getClassIds(teachingClassList);
        return ResultVoUtil.success(classIdList);
    }

    @Override
    public ResultVo getCourseInfoList() {
        List<Course> courseList = courseDAO.getCourses();
        return ResultVoUtil.success(courseList);
    }

    @Override
    public ResultVo getTeacherCourses(String userName) {
        List<String> courseList = courseDAO.getCourseName(userName);
        return ResultVoUtil.success(courseList);
    }


}
