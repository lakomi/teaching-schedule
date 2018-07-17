package com.web.teachingschedule.vo.entity;

import lombok.Data;

/**
 * 用户实体（包括教师、管理员）
 * @author q
 */
@Data
public class UserInfo {
    /**
     * 教师工号(主键) 12位
     */
    private String userId;
    /**
     * 教师姓名
     */
    private String userName;
    /**
     * 密码
     */
    private String password;
    /**
     * 用户角色（教师/管理员）
     */
    private String userRole;


}
