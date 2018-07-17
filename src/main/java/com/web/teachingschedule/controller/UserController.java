package com.web.teachingschedule.controller;

import com.web.teachingschedule.service.UserInfoService;
import com.web.teachingschedule.vo.ResultVo;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;

/**
 * 管理员访问接口
 * @author q
 */
@RestController
@RequestMapping("/root")
@Slf4j
@CrossOrigin
public class UserController {
    @Autowired
    private UserInfoService userInfoService;

    /**
     * 管理员获取教师列表
     * @return
     */
    @GetMapping("/getTeachers")
    public ResultVo getTeachers(){
        return userInfoService.selectTeachers();
    }

    /**
     * 管理员添加教师
     * @param userId
     * @param userName
     * @return
     */
    @PostMapping("/addTeacher")
    public ResultVo add(@RequestParam("userId") String userId,
                        @RequestParam("userName") String userName){
        return userInfoService.rootAddTeacher(userId, userName);
    }

    /**
     * 删除教师
     * @param userId
     * @return
     */
    @PostMapping("/deltea")
    public ResultVo deleteTeacher(@RequestParam("userId") String userId){
        return userInfoService.rootDelTeacher(userId);
    }

    /**
     * 删除批量教师
     * @param userIds
     * @return
     */
    @PostMapping("/deltealist")
    public ResultVo deleteLotTeacher(@RequestBody List<String> userIds){
        return userInfoService.rootDelTeacherList(userIds);
    }

    /**
     * 重置密码
     * @param userId
     * @return
     */
    @PostMapping("/reset")
    public ResultVo resetPassword(@RequestParam("userId") String userId){
        return userInfoService.resetPw(userId);
    }




}
