package com.web.teachingschedule.dao;

import com.web.teachingschedule.vo.entity.TeachingClass;
import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Param;
import org.springframework.stereotype.Repository;

import java.util.List;

/**
 * 教学班
 * @author q
 */
@Repository
@Mapper
public interface TeachingClassDAO {
    /**
     * 根据课程号，教学班号固定一个某课程的某教学班
     * @param courseId
     * @param classId
     * @return
     */
    TeachingClass getClassByCourseAndClass(@Param("courseId") String courseId,@Param("classId") String classId);
    /**
     * 教学班级列表
     * @return
     */
    List<TeachingClass> getClasses();

    /**
     * 同一课程的教学班级
     * @param courseId
     * @return
     */
    List<TeachingClass> getClassesByCourseId(@Param("courseId") String courseId);

    /**
     * 同一老师的教学班级
     * @param userId
     * @return
     */
    List<TeachingClass> getClassesByUserId(@Param("userId") String userId);

    /**
     * 查询某一教学班
     * @param classId
     * @return
     */
    TeachingClass getClassByClassId(@Param("classId") String classId);

    /**
     * 增加
     * @param teachingClass
     * @return
     */
    int addClass(TeachingClass teachingClass);

    /**
     * 修改
     * @param teachingClass
     * @return
     */
    int modifyClass(TeachingClass teachingClass);

    /**
     * 根据 classId 删除
     * @param classId
     * @return
     */
    int deleteClassByClassId(@Param("classId") String classId);

    /**
     * 根据 courseId 删除
     * @param courseId
     * @return
     */
    int deleteClassByCourseId(@Param("courseId") String courseId);

    /**
     * 根据 userId 删除
     * @param userId
     * @return
     */
    int deleteClassByUserId(@Param("userId") String userId);

}
