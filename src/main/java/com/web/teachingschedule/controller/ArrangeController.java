package com.web.teachingschedule.controller;

import com.web.teachingschedule.enums.CodeEnum;
import com.web.teachingschedule.service.ArrangeCourseService;
import com.web.teachingschedule.utils.ResultVoUtil;
import com.web.teachingschedule.vo.ResultVo;
import com.web.teachingschedule.vo.dto.ArrcourseDTO;
import com.web.teachingschedule.vo.dto.ArrlotcourseDTO;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.util.StringUtils;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.*;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.validation.Valid;

/**
 * 排课
 * @author q
 */
@RestController
@RequestMapping("/root")
@Slf4j
@CrossOrigin
public class ArrangeController {
    @Autowired
    private ArrangeCourseService arrangeCourseService;

    /**
     * 选择学期
     * @param termName
     * @param request
     * @return
     */
    @PostMapping("/chooseTerm")
    public ResultVo chooseTerm(@RequestParam("termName") String termName, HttpServletRequest request, HttpServletResponse response){
        return arrangeCourseService.chooseTerm(termName, request);
    }

    /**
     * 选择课程，教学班
     * @param courseId
     * @param classId
     * @param request
     * @return
     */
    @PostMapping("/chooseClass")
    public ResultVo chooseClass(@RequestParam("courseId") String courseId,@RequestParam("classId") String classId,HttpServletRequest request){
        return arrangeCourseService.chooseTeachingClass(courseId, classId, request);
    }

    /**
     * 取消排课
     * @param ctId
     * @return
     */
    @PostMapping("/cancel")
    public ResultVo cancelCourse(@RequestParam("ctId") String ctId){
        return arrangeCourseService.cancelArrangeCourse(ctId);
    }

    /**
     * 排课
     * @param arrcourseDTO
     * @param bindingResult
     * @param request
     * @return
     */
    @PostMapping("/arrange")
    public ResultVo arrangeCourse(@Valid ArrcourseDTO arrcourseDTO, BindingResult bindingResult, HttpServletRequest request){
        if (bindingResult.hasErrors()){
            log.info("/arrange @Valid 注解 验证错误");
            System.out.println(bindingResult.getFieldError().getDefaultMessage());
            return ResultVoUtil.error(CodeEnum.ERROR.getCode(),bindingResult.getFieldError().getDefaultMessage());
        }
        return arrangeCourseService.arrangeCourse(arrcourseDTO);
    }

    /**
     * 批量排课
     * @return
     */
    @PostMapping("/arrangeLot")
    public ResultVo arrangeLotCourse(@Valid ArrlotcourseDTO arrlotcourseDTO, BindingResult bindingResult, HttpServletRequest request){
        if (bindingResult.hasErrors()){
            log.info("/arrange @Valid 注解 验证错误");
            System.out.println(bindingResult.getFieldError().getDefaultMessage());
            return ResultVoUtil.error(CodeEnum.ERROR.getCode(),bindingResult.getFieldError().getDefaultMessage());
        }
        return arrangeCourseService.arrangeLotCourses(arrlotcourseDTO);
    }

    /**
     * 管理员按 老师 查询课表
     * 管理员按 老师-课程 查询课表
     * @return
     */
    @PostMapping("/getTCtables")
    public ResultVo getTCtables(@RequestParam("userName") String userName,@RequestParam("courseName") String courseName, @RequestParam("ctWeek") int ctWeek,HttpServletRequest request){
        if (StringUtils.isEmpty(courseName)){
            log.info("courseName 为空");
            return arrangeCourseService.getTtables(userName,request,ctWeek);
        }else {
            log.info("courseName 不为空");
            return arrangeCourseService.getTCtables(userName, courseName, ctWeek, request);
        }
    }

    /**
     * 管理员排课视图
     * @param labName
     * @param ctWeek
     * @return
     */
    @PostMapping("/getarrangevo")
    public ResultVo getArrangeVo(@RequestParam("labName") String labName,@RequestParam("ctWeek") int ctWeek,String termName){
        return arrangeCourseService.getArrangeVo(labName, ctWeek, termName);
    }
    /**
     * 教师名，教学班号查询
     */
    @PostMapping("/getTClassTable")
    public ResultVo getTClassTable(@RequestParam("userName") String userName,@RequestParam("classId") String classId,@RequestParam("termName") String termName,@RequestParam("ctWeek") int  ctWeek){
        System.out.println("userName: "+userName+",classId: "+classId+",termName: "+termName+",ctWeek: "+ctWeek);
        return arrangeCourseService.getClassTable(classId, userName, ctWeek, termName);

    }











}
