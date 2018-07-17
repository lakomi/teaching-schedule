package com.web.teachingschedule.dao;

import com.web.teachingschedule.vo.entity.Experiment;
import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Param;
import org.springframework.stereotype.Repository;

import java.util.List;

/**
 * 实验表
 * @author q
 */
@Repository
@Mapper
public interface ExperimentDAO {

    /**
     * 实验列表
     * @return
     */
    List<Experiment> getExpes();

    /**
     * 查询一个课程的实验项目列表
     * @param courseId
     * @return
     */
    List<Experiment> getExpesByCourseId(@Param("courseId") String courseId);

    /**
     * 获取实验项目
     * @param eId
     * @return
     */
    Experiment getExpeByeId(@Param("eId") String eId);

    /**
     * 添加
     * @param experiment
     * @return
     */
    int addExpe(Experiment experiment);

    /**
     * 修改
     * @param experiment
     * @return
     */
    int modifyExpe(Experiment experiment);

    /**
     * 删除
     * @param eId
     * @return
     */
    int deleteExpe(@Param("eId") String eId);










}
