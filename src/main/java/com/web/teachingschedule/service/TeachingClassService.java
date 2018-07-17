package com.web.teachingschedule.service;

import com.web.teachingschedule.vo.ResultVo;

/**
 * 教学班操作
 * @author q
 */
public interface TeachingClassService {

    /**
     * 查询所有教学班
     * @return
     */
    ResultVo selectClassList();
    /**
     * 查询教师对应的教学班
     * @param userName
     * @return
     */
    ResultVo selectClassByTeacher(String userName);


}
