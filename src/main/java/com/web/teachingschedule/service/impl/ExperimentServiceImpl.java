package com.web.teachingschedule.service.impl;

import com.web.teachingschedule.dao.ExperimentDAO;
import com.web.teachingschedule.enums.TeachExceptionEnum;
import com.web.teachingschedule.exception.TeachingException;
import com.web.teachingschedule.service.ExperimentService;
import com.web.teachingschedule.utils.ResultVoUtil;
import com.web.teachingschedule.utils.TransformToDTO;
import com.web.teachingschedule.vo.ResultVo;
import com.web.teachingschedule.vo.dto.ExperimentDTO;
import com.web.teachingschedule.vo.entity.Experiment;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.util.StringUtils;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;
import java.util.List;

/**
 * 实验项目接口实现
 * @author q
 */
@Service
@Slf4j
public class ExperimentServiceImpl implements ExperimentService {

    @Autowired
    private ExperimentDAO experimentDAO;

    @Override
    public ResultVo getExList(String courseId) {
        List<Experiment> experimentList = experimentDAO.getExpesByCourseId(courseId);
        List<ExperimentDTO> experimentDTOList = TransformToDTO.exsToexDTOs(experimentList);
        return ResultVoUtil.success(experimentDTOList);
    }

    @Override
    public ResultVo geteNameAndeId(HttpServletRequest request) {
        HttpSession session = request.getSession();
        String courseId = (String) session.getAttribute("courseId");
        System.out.println("session中courseId： "+courseId);
        if (StringUtils.isEmpty(courseId)){
            throw new TeachingException(TeachExceptionEnum.FIRST_CHOOSE_CLASS);
        }
        return getExList(courseId);
    }

}
