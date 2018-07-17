package com.web.teachingschedule.dao;


import com.web.teachingschedule.vo.Teachers;
import com.web.teachingschedule.vo.entity.UserInfo;
import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Param;
import org.springframework.stereotype.Repository;

import java.util.List;

/**
 * 用户表操作
 * @author q
 */
@Repository
@Mapper
public interface UserDAO {

    /**
     * 返回教师列表
     * @return
     */
    List<Teachers> getTeachersByRole();

    /**
     * 根据userId 查询用户
     * @param userId
     * @return
     */
    UserInfo getUserById(@Param("userId") String userId);

    /**
     * 添加教师
     * @param userInfo
     * @return
     */
    int addUser(UserInfo userInfo);

    /**
     * 根据 userId 删除教师
     * @param userId
     * @return
     */
    int deleteUser(@Param("userId") String userId);

    /**
     * 批量删除教师
     * @param userIds
     * @return
     */
    int deleteLotUser(@Param("userIds") List<String> userIds);

    /**
     * 单个重置密码
     * @return
     */
    int resetPw(@Param("userId") String userId);

    /**
     * 批量重置密码
     * @param userIds
     * @return
     */
    int resetLotPw(@Param("userIds") List<String> userIds);

    /**
     * 修改密码
     * @param userId
     * @param newPassword
     * @return
     */
    int modifyPw(@Param("userId") String userId, @Param("newPassword") String newPassword);

    /**
     * 根据名称查找老师
     * @param userName
     * @return
     */
    UserInfo getUserByName(@Param("userName") String userName);



}
