package com.web.teachingschedule.controller;

import com.web.teachingschedule.service.CourseService;
import com.web.teachingschedule.vo.ResultVo;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

/**
 * 课程操作接口
 * @author q
 */
@RestController
@RequestMapping("/root")
@Slf4j
@CrossOrigin
public class CourseController {

    @Autowired
    private CourseService courseService;

    /**
     * 课程列表 课程号和课程名
     * @return
     */
    @GetMapping("/getCourses")
    public ResultVo getCourseList(){
        return courseService.getCourseList();
    }

    /**
     * 对应某一课程的所有教学班号
     * @param courseId
     * @return
     */
    @PostMapping("/getClass")
    public ResultVo getClassList(@RequestParam("courseId") String courseId){
        return courseService.getClassList(courseId);
    }

    /**
     * 获取课程详情列表
     * @return
     */
    @GetMapping("/getCourseInfoList")
    public ResultVo getCourseInfoList(){
        return courseService.getCourseInfoList();
    }

    /**
     * 获取某个老师的所有对应课程
     * @param userName
     * @return
     */
    @GetMapping("/getteacourse")
    public ResultVo getTeacherCourses(@RequestParam("userName") String userName){
        return courseService.getTeacherCourses(userName);
    }


}
