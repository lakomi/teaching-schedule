package com.web.teachingschedule.dao;

import com.web.teachingschedule.vo.entity.Course;
import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Param;
import org.springframework.stereotype.Repository;

import java.util.List;

/**
 * 课程表操作
 * @author q
 */
@Repository
@Mapper
public interface CourseDAO {

    /**
     * 根据 课程号 查询课程信息
     * @param courseId
     * @return
     */
    Course getCourseById(@Param("courseId") String courseId);

    /**
     * 根据 课程名 查找课程信息
     * @param courseName
     * @return
     */
    Course getCourseByName(@Param("courseName") String courseName);

    /**
     * 课程列表
     * @return
     */
    List<Course> getCourses();

    /**
     * 添加课程
     * @param course
     * @return
     */
    int addCourse(Course course);

    /**
     * 删除课程
     * @param courseId
     * @return
     */
    int deleteCourse(@Param("courseId") String courseId);

    /**
     * 修改课程信息
     * @param course
     * @return
     */
    int modifyCourse(Course course);
    /**
     * 根据教师名称，查找他的课程
     * @param userName
     * @return
     */
    List<String> getCourseName(@Param("userName") String userName);

}
