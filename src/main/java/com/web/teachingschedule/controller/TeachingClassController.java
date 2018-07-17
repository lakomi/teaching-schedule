package com.web.teachingschedule.controller;

import com.web.teachingschedule.service.TeachingClassService;
import com.web.teachingschedule.vo.ResultVo;
import lombok.extern.slf4j.Slf4j;
import org.omg.CORBA.PRIVATE_MEMBER;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/root")
@Slf4j
@CrossOrigin
public class TeachingClassController {
    @Autowired
    private TeachingClassService teachingClassService;

    @GetMapping("/getTClassList")
    public ResultVo getTclassList(){
        return teachingClassService.selectClassList();
    }

    @RequestMapping("/getTClassListByTeacher")
    public ResultVo getTClassListByTeacher(@RequestParam("userName") String userName){
        return teachingClassService.selectClassByTeacher(userName);
    }






}
