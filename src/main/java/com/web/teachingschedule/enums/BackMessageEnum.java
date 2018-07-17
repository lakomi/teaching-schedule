package com.web.teachingschedule.enums;

import lombok.Getter;

/**
 * 正常信息返回枚举
 * @author q
 */
@Getter
public enum BackMessageEnum {
    /**
     * 登录成功
     */
    LOGIN_SUCCESS(1,"登录成功"),
    /**
     * 修改信息成功
     */
    MODIFY_SUCCESS(2,"修改成功"),
    /**
     * 管理员添加成功
     */
    ADD_SUCCESS(3,"添加成功"),
    /**
     * 管理员删除成功
     */
    DEL_SUCCESS(4,"删除成功"),
    /**
     * 选择学期，教学班等
     */
    CHOOSE_SUCCESS(5,"选择成功"),
    /**
     * 取消排课成功
     */
    CANCEL_SUCCESS(6,"已取消"),
    /**
     * 重置密码
     */
    RESET_SUCCESS(7,"重置成功"),

    ;
    private Integer code;

    private String message;

    BackMessageEnum(Integer code, String message) {
        this.code = code;
        this.message = message;
    }
}
