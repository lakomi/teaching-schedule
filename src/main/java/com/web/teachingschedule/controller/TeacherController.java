package com.web.teachingschedule.controller;

import com.web.teachingschedule.enums.CodeEnum;
import com.web.teachingschedule.service.ArrangeCourseService;
import com.web.teachingschedule.service.UserInfoService;
import com.web.teachingschedule.utils.ResultVoUtil;
import com.web.teachingschedule.vo.ResultVo;
import com.web.teachingschedule.vo.dto.LoginDTO;
import com.web.teachingschedule.vo.dto.ModifypwDTO;
import javafx.geometry.Pos;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.*;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.validation.Valid;

/**
 * 教师访问接口
 * @author q
 */
@Slf4j
@RestController
@RequestMapping("/user")
@CrossOrigin
public class TeacherController {
    @Autowired
    private UserInfoService userInfoService;
    @Autowired
    private ArrangeCourseService arrangeCourseService;

    @GetMapping("/hi")
    public ResultVo hi(){
        return ResultVoUtil.success("hello");
    }
    /**
     * 登录
     * @param loginDTO
     * @param bindingResult
     * @param response
     * @return
     */
    @PostMapping("/loginT")
    public ResultVo loginT(@Valid LoginDTO loginDTO, BindingResult bindingResult, HttpServletRequest request,HttpServletResponse response){
        response.setHeader("Access-Control-Allow-Origin", "*");
        response.setHeader("Access-Control-Allow-Methods", "POST, GET, OPTIONS, DELETE");
        response.addHeader("Access-Control-Allow-Credentials", "true");
        response.addHeader("Access-Control-Allow-Headers", "Origin, X-Requested-With, Content-Type, Accept,Authorization");
        response.addHeader("Access-Control-Expose-Headers", "Origin, X-Requested-With, Content-Type, Accept,Authorization");
        if (bindingResult.hasErrors()){
            log.info("/login @Valid 注解 验证错误");
            System.out.println(bindingResult.getFieldError().getDefaultMessage());
            return ResultVoUtil.error(CodeEnum.ERROR.getCode(),bindingResult.getFieldError().getDefaultMessage());
        }
        return userInfoService.loginT(loginDTO,response);
    }

    @PostMapping("/loginR")
    public ResultVo loginR(@Valid LoginDTO loginDTO, BindingResult bindingResult,HttpServletRequest request,HttpServletResponse response){
        response.setHeader("Access-Control-Allow-Origin","*");
        response.setHeader("Access-Control-Allow-Methods", "POST, GET, OPTIONS, DELETE");
        response.addHeader("Access-Control-Allow-Credentials", "true");
        response.addHeader("Access-Control-Allow-Headers", "Origin, X-Requested-With, Content-Type, Accept,Authorization");
        response.addHeader("Access-Control-Expose-Headers", "Origin, X-Requested-With, Content-Type, Accept,Authorization");

        if (bindingResult.hasErrors()){
            log.info("/login @Valid 注解 验证错误");
            System.out.println(bindingResult.getFieldError().getDefaultMessage());
            return ResultVoUtil.error(CodeEnum.ERROR.getCode(),bindingResult.getFieldError().getDefaultMessage());
        }
        return userInfoService.loginR(loginDTO,response);
    }

    /**
     * 修改密码
     * @param modifypwDTO
     * @param bindingResult
     * @return
     */
    @PostMapping("/modifyPw")
    public ResultVo modifyPw(@Valid ModifypwDTO modifypwDTO,BindingResult bindingResult){
        if (bindingResult.hasErrors()){
            log.info("/modifyPw @Valid 注解 验证错误");
            System.out.println(bindingResult.getFieldError().getDefaultMessage());
            return ResultVoUtil.error(CodeEnum.ERROR.getCode(),bindingResult.getFieldError().getDefaultMessage());
        }
        return userInfoService.userModifyPw(modifypwDTO);

    }

    /**
     * 获取单个教师详情(教师个人信息)
     * @return
     */
    @GetMapping("/getInfo")
    public ResultVo getTeacherInfo(){
        return userInfoService.getTeacherInfo();

    }

    /**
     * 教师获取自己的课表
     * @return
     */
    @RequestMapping("/getCourseTable")
    public ResultVo getCourseTable(){
        return arrangeCourseService.getTablesByWeek();
    }


}
