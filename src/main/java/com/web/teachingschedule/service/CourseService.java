package com.web.teachingschedule.service;

import com.web.teachingschedule.vo.ResultVo;

/**
 * 课程接口
 * @author q
 */
public interface CourseService {
    /**
     * 获取课程名和课程号列表
     * @return
     */
    ResultVo getCourseList();
    /**
     * 获取对应课程的教学班
     * @param courseId
     * @return
     */
    ResultVo getClassList(String courseId);
    /**
     * 获取课程详情列表
     * @return
     */
    ResultVo getCourseInfoList();

    /**
     * 获取某一个教师的所有课程
     * @param userName
     * @return
     */
    ResultVo getTeacherCourses(String userName);





}
