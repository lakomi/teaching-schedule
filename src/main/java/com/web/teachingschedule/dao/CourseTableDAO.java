package com.web.teachingschedule.dao;

import com.web.teachingschedule.vo.dto.ArrcourseDTO;
import com.web.teachingschedule.vo.dto.TimeDTO;
import com.web.teachingschedule.vo.entity.CourseTable;
import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Param;
import org.springframework.security.core.parameters.P;
import org.springframework.stereotype.Repository;

import java.util.List;

/**
 * 排课表
 * @author q
 */
@Repository
@Mapper
public interface CourseTableDAO {
    /**
     * 得到所有课表
     * @return
     */
    List<CourseTable> getTables();

    /**
     * 查询 某学期 某一教学班 某一周的课表
     * @param classId
     * @return
     */
    List<CourseTable> getTablesByClassId(@Param("classId") String classId,@Param("termName") String termName,@Param("ctWeek") int ctWeek);

    /**
     * 查询某一学期的排课列表
     * @param termName
     * @return
     */
    List<CourseTable> getTablesByTermAndWeekAndLab(@Param("termName") String termName,@Param("ctWeek") int ctWeek,@Param("labName") String labName);
    /**
     * 查询某教师的排课列表
     * @param ctWeek
     * @return
     */
    List<CourseTable> getTablesByTeacher(String userId);
    /**
     * 查询某一机房的排课列表
     * @param labName
     * @return
     */
    List<CourseTable> getTablesByLab(@Param("labName") String labName);
    /**
     * 查询某一教师的排课列表
     * @param userId
     * @return
     */
    List<CourseTable> getTablesByTeacherAndTerm(@Param("userId") String userId,@Param("termName") String termName,@Param("ctWeek") int ctWeek);
    /**
     * 查询某一教师某一课程的排课列表
     * @param userId
     * @param courseId
     * @return
     */
    List<CourseTable> getTablesByTeaAndCou(@Param("userId") String userId,@Param("courseId") String courseId,@Param("ctWeek") int ctWeek,@Param("termName") String termName);


    /**
     * 查询某一教师某一课程的排课列表
     * @param userId
     * @return
     */
    List<CourseTable> getTablesByTeaAndClass(@Param("userId") String userId,@Param("classId") String classId,@Param("ctWeek") int ctWeek,@Param("termName") String termName);

    /**
     * 排课
     * @param courseTable
     * @return
     */
    int addTable(CourseTable courseTable);
    /**
     * 取消排课
     * @param ctId
     * @return
     */
    int deleteTable(@Param("ctId") String ctId);
    /**
     * 锁定某一时间，该机房是否有课程
     * @param timeDTO
     * @return
     */
    CourseTable getTableByTime(TimeDTO timeDTO);





}
