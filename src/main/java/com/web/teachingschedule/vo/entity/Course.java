package com.web.teachingschedule.vo.entity;

import lombok.Data;

/**
 * 课程实体
 * @author q
 */
@Data
public class Course {
    /**
     * 课程号（主键）10位
     */
    private String courseId;
    /**
     * 课程名称
     */
    private String courseName;
    /**
     * 课程总学时
     */
    private int totalHours = -1;
    /**
     * 课程实验总学时
     */
    private int expeHours = -1;

}
