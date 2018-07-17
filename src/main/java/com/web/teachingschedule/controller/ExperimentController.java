package com.web.teachingschedule.controller;

import com.web.teachingschedule.service.ExperimentService;
import com.web.teachingschedule.vo.ResultVo;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import javax.servlet.http.HttpServletRequest;

/**
 * 实验项目
 * @author q
 */
@RestController
@Slf4j
@RequestMapping("/root")
@CrossOrigin
public class ExperimentController {

    @Autowired
    private ExperimentService experimentService;

    /**
     * 查看某一课程的实验项目详情
     * @param courseId
     * @return
     */
    @PostMapping("/getExsinfo")
    public ResultVo getExsinfo(@RequestParam("courseId") String courseId){
        return experimentService.getExList(courseId);
    }

    /**
     * 获取实验项目的名称和id，拼成列表
     * @return
     */
    @GetMapping("/getExslist")
    public ResultVo getExslist(HttpServletRequest request){
        return experimentService.geteNameAndeId(request);
    }






}
