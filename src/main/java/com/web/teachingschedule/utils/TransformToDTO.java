package com.web.teachingschedule.utils;

import com.web.teachingschedule.dao.CourseDAO;
import com.web.teachingschedule.dao.UserDAO;
import com.web.teachingschedule.service.impl.ArrangeCourseServiceImpl;
import com.web.teachingschedule.vo.CourseVo;
import com.web.teachingschedule.vo.dto.BackTeacherTableDTO;
import com.web.teachingschedule.vo.dto.ExperimentDTO;
import com.web.teachingschedule.vo.entity.*;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

/**
 * 将entity中的某些实体转化为对应的DTO
 * @author q
 */
@Slf4j
public class TransformToDTO {
    /**
     * 将 course 转换成 courseVo
     * @param courseList
     * @return
     */
    public static List<CourseVo> coursesTocourseVos(List<Course> courseList){
        List<CourseVo> courseVoList = new ArrayList<>();
        for (Course course : courseList){
            CourseVo courseVo = new CourseVo(course.getCourseId(),course.getCourseName());
            courseVoList.add(courseVo);
        }
        System.out.println("转换之后： "+courseVoList.toString());
        return courseVoList;
    }

    /**
     * 提取出 classId 并放入 list 中
     * @param teachingClassList
     * @return
     */
    public static List<String> getClassIds(List<TeachingClass> teachingClassList){
        List<String> classIdList = new ArrayList<>();
        for (TeachingClass teachingClass : teachingClassList){
            classIdList.add(teachingClass.getClassId());
        }
        System.out.println("转换之后： "+classIdList.toString());
        return classIdList;
    }

    /**
     * 返回实验项目信息的转换
     * @param experimentList
     * @return
     */
    public static List<ExperimentDTO> exsToexDTOs(List<Experiment> experimentList){
        List<ExperimentDTO> experimentDTOList = new ArrayList<>();
        for (Experiment experiment :  experimentList){
            ExperimentDTO experimentDTO = new ExperimentDTO();
            experimentDTO.setEId(experiment.getEId());
            experimentDTO.setEName(experiment.getEName());
            experimentDTO.setEHours(experiment.getEHours());
            experimentDTO.setEType(experiment.getEType());
            experimentDTOList.add(experimentDTO);
        }
        System.out.println("转换之后： "+experimentDTOList.toString());
        return experimentDTOList;
    }



}
