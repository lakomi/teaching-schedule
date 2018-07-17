package com.web.teachingschedule.handler;

import com.web.teachingschedule.enums.CodeEnum;
import com.web.teachingschedule.exception.TeachingException;
import com.web.teachingschedule.utils.ResultVoUtil;
import com.web.teachingschedule.vo.ResultVo;
import lombok.extern.slf4j.Slf4j;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.ResponseBody;

/**
 * 捕获程序中抛出的特定异常
 * @author q
 */
@Slf4j
@ControllerAdvice
public class TeachingExceptionHandler {

    @ResponseBody
    @ExceptionHandler(TeachingException.class)
    public ResultVo handlerTeachingException(TeachingException e){

        ResultVo resultVo = null;

        resultVo = ResultVoUtil.error(CodeEnum.ERROR.getCode(),e.getMessage());

        return resultVo;
    }


}
