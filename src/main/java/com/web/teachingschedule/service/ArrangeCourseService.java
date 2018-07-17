package com.web.teachingschedule.service;

import com.web.teachingschedule.vo.ResultVo;
import com.web.teachingschedule.vo.dto.ArrcourseDTO;
import com.web.teachingschedule.vo.dto.ArrlotcourseDTO;


import javax.servlet.http.HttpServletRequest;

/**
 * 排课接口
 * @author q
 */
public interface ArrangeCourseService {
    /**
     * 选择学期
     * @param termName
     * @param request
     * @return
     */
    ResultVo chooseTerm(String termName, HttpServletRequest request);

    /**
     * 选择教学班
     * @param courseId
     * @param classId
     * @param request
     * @return
     */
    ResultVo chooseTeachingClass(String courseId,String classId,HttpServletRequest request);
    /**
     * 取消排课
     * @param ctId
     * @return
     */
    ResultVo cancelArrangeCourse(String ctId);

    /**
     * 排课
     * @param arrcourseDTO
     * @return
     */
    ResultVo arrangeCourse(ArrcourseDTO arrcourseDTO);

    /**
     * 批量排课
     * @return
     */
    ResultVo arrangeLotCourses(ArrlotcourseDTO arrlotcourseDTO);

    /**
     * 查询某一周排课表
     * @param ctWeek
     * @return
     */
    ResultVo getTablesByWeek();

    /**
     * 管理员按 老师 查询课表
     * @return
     */
    ResultVo getTtables(String userName,HttpServletRequest request,int ctWeek);

    /**
     * 管理员按 老师-课程 查询课表
     * @return
     */
    ResultVo getTCtables(String userName,String courseName,int ctWeek,HttpServletRequest request);

    /**
     * 管理员按教学班查询课表信息 某一周，某一学期
     * @return
     */
    ResultVo getClassTable(String classId,String userName,int ctWeek,String termName);

    /**
     * 管理员排课视图
     * @param labName
     * @param ctWeek
     * @return
     */
    ResultVo getArrangeVo(String labName,int ctWeek,String termName);














}
