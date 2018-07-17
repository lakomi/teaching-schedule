package com.web.teachingschedule.service;

import com.web.teachingschedule.vo.ResultVo;

import javax.servlet.http.HttpServletRequest;

/**
 * 实验项目
 * @author q
 */
public interface ExperimentService {
    /**
     * 查询某一课程下的实验项目列表
     * @param courseId
     * @return
     */
    ResultVo getExList(String courseId);

    /**
     * 获取实验项目的名称和id，拼成列表
     * @param request
     * @return
     */
    ResultVo geteNameAndeId(HttpServletRequest request);


}
