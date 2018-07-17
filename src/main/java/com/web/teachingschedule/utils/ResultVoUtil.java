package com.web.teachingschedule.utils;

import com.web.teachingschedule.enums.BackMessageEnum;
import com.web.teachingschedule.enums.CodeEnum;
import com.web.teachingschedule.vo.ResultVo;

public class ResultVoUtil {
    /**
     * 成功返回，携带内容
     */
    public static ResultVo success(Object object) {

        ResultVo resultVo = new ResultVo();
        resultVo.setCode(CodeEnum.SUCCESS.getCode());
        resultVo.setMessage(CodeEnum.SUCCESS.getMessage());
        resultVo.setData(object);
        return resultVo;
    }

    /**
     * 成功返回，携带内容
     */
    public static ResultVo success(String message, Object object) {

        ResultVo resultVo = new ResultVo();
        resultVo.setCode(CodeEnum.SUCCESS.getCode());
        resultVo.setMessage(message);
        resultVo.setData(object);
        return resultVo;
    }

    /**
     * 成功返回，无携带内容
     */
    public static ResultVo success() {

        return success(null);
    }

    /**
     * 返回错误，携带错误信息及错误码
     */
    public static ResultVo error(Integer code, String message) {

        ResultVo resultVo = new ResultVo();
        resultVo.setCode(code);
        resultVo.setMessage(message);
        resultVo.setData(null);
        return resultVo;
    }
}