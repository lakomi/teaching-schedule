package com.web.teachingschedule.vo.entity;

import lombok.Data;

/**
 * 班级实体
 * @author q
 */
@Data
public class TeachingClass {
    /**
     * 教学班级编号（主键） 4位
     */
    private String classId;
    /**
     * 教学班 专业
     */
    private String classMajor;
    /**
     * 教学班人数
     */
    private int classPeople = -1;
    /**
     * 教师Id
     */
    private String userId;
    /**
     * 课程id
     */
    private String courseId;
    /**
     * 年级
     */
    private String grade;

}
