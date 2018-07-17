package com.web.teachingschedule.enums;

import lombok.Getter;

/**
 * 错误信息枚举
 * @author q
 */
@Getter
public enum TeachExceptionEnum {
    /**
     * token过期 需要重新登录
     */
    REPEAT_LOGIN(2,"请重新登录"),
    /**
     * token为空 先登录
     */
    PLEASE_LOGIN_FIRST(16,"请先登录"),
    /**
     * 用户名不存在
     */
    USERID_NOT_EXIT(1,"用户名不存在"),
    /**
     * UserAuthenticationProvider 中提取用户详情出错
     */
    GET_USERDETAILS_ERROR(3,"登录中获取用户详情出错"),
    /**
     * 密码错误
     */
    PASSWORD_ERROR(9,"密码错误"),
    /**
     * 修改密码中，原密码错误
     */
    OLD_PASSWORD_ERROR(3,"原密码错误"),
    /**
     * 服务器内部错误，数据库操作出错
     */
    SQL_ERROR(4,"增山查改操作数据库出错"),
    /**
     * 选择学期失败
     */
    CHOOSE_TERM_ERROR(5,"选择学期失败"),
    /**
     * 选择某课程的某教学班
     */
    CHOOSE_CLASS_ERROR(6,"选择教学班失败"),
    /**
     * 从session中得到的term为空，提示先选学期
     */
    FIRST_CHOOSE_TERM(7,"请先选择某一学期"),
    /**
     * 课程冲突
     */
    COURSE_CLASH(8,"该时间段已有课程，请另选时间段"),
    /**
     * session中取出的课程号和教学班号为空
     */
    FIRST_CHOOSE_CLASS(9,"请先选择课程及教学班"),
    /**
     * 批量排课中有冲突
     */
    COURSE_IS_CLASH(10,"有课程冲突，请到单次排课页面自行排课"),
    /**
     * 修改机房信息处，没有传入机房名称
     */
    LABROOM_NAME_NOT_NULL(11,"机房名称不能为空"),

    ;

    private Integer code;

    private String message;

    TeachExceptionEnum(Integer code, String message) {
        this.code = code;
        this.message = message;
    }

}
