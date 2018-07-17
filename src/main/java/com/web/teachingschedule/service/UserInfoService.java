package com.web.teachingschedule.service;

import com.web.teachingschedule.vo.ResultVo;
import com.web.teachingschedule.vo.dto.LoginDTO;
import com.web.teachingschedule.vo.dto.ModifypwDTO;
import org.springframework.web.bind.annotation.RequestParam;

import javax.servlet.http.HttpServletResponse;
import java.util.List;

/**
 * 用户信息接口
 * @author q
 */
public interface UserInfoService {
    /**
     * 用户登录
     * @param loginDTO
     * @param response
     * @return
     */
    ResultVo loginT(LoginDTO loginDTO, HttpServletResponse response);

    /**
     * 管理员登录
     * @return
     */
    ResultVo loginR(LoginDTO loginDTO,HttpServletResponse response);

    /**
     * 教师修改密码
     * @param modifypwDTO
     * @return
     */
    ResultVo userModifyPw(ModifypwDTO modifypwDTO);

    /**
     * 查询教师列表
     * @return
     */
    ResultVo selectTeachers();

    /**
     * 获取单个教师详情
     * @return
     */
    ResultVo getTeacherInfo();

    /**
     * 管理员添加教师
     * @param userId
     * @param userName
     * @return
     */
    ResultVo rootAddTeacher(String userId,String userName);

    /**
     * 管理员删除单个教师
     * @param userId
     * @return
     */
    ResultVo rootDelTeacher(String userId);

    /**
     * 管理员批量删除教师
     * @param userIds
     * @return
     */
    ResultVo rootDelTeacherList(List<String> userIds);

    /**
     * 管理员重置密码
     * @param userId
     * @return
     */
    ResultVo resetPw(String userId);





}
