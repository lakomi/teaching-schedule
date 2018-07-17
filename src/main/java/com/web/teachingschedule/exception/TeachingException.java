package com.web.teachingschedule.exception;

import com.web.teachingschedule.enums.TeachExceptionEnum;
import lombok.Data;

/**
 * 异常
 * @author q
 */
@Data
public class TeachingException extends RuntimeException {

    private Integer code;

    public TeachingException(String message, Integer code) {
        super(message);
        this.code = code;
    }

    public TeachingException(TeachExceptionEnum teachExceptionEnum) {
        super(teachExceptionEnum.getMessage());
        this.code = teachExceptionEnum.getCode();
    }
}
