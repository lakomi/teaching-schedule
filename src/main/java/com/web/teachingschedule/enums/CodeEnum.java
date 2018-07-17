package com.web.teachingschedule.enums;

import lombok.Getter;

/**
 * 返回码枚举
 * @author q
 */
@Getter
public enum CodeEnum {
    /**
     * 失败
     */
    SUCCESS(0,"成功"),
    /**
     * 成功
     */
    ERROR(1,"失败"),

    ;
    private Integer code;

    private String message;

    CodeEnum(Integer code, String message) {
        this.code = code;
        this.message = message;
    }
}

