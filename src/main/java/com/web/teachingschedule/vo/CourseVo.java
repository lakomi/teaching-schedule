package com.web.teachingschedule.vo;

import lombok.Data;

/**
 * 返回的课程信息
 * @author q
 */
@Data
public class CourseVo {
    /**
     * 课程号
     */
    private String courseId;
    /**
     * 课程名
     */
    private String courseName;

    public CourseVo(String courseId, String courseName) {
        this.courseId = courseId;
        this.courseName = courseName;
    }
}
