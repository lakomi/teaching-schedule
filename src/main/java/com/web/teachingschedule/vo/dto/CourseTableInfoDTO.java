package com.web.teachingschedule.vo.dto;

import lombok.Data;

/**
 * 某一时间段 课表的详情，实验项目等信息
 * 列表
 * @author q
 */
@Data
public class CourseTableInfoDTO {
    /**
     * 课程名称
     */
    private String courseName;
    /**
     * 课程号
     */
    private String courseId;
    /**
     * 专业
     */
    private String classMajor;
    /**
     * 年级
     */
    private String grade;
    /**
     * 教学班号
     */
    private String classId;
    /**
     * 教学班人数
     */
    private int classPeople;
    /**
     * 课程总学时
     */
    private int totalHours;
    /**
     * 课程实验总学时
     */
    private int expeHours;
    /**
     * 实验项目编号
     */
    private String eId;
    /**
     * 实验名称
     */
    private String eName;
    /**
     * 实验类型
     */
    private String eType;
    /**
     * 项目学时
     */
    private int eHours;
    /**
     * 上机人数
     */
    private int ctPeople;
    /**
     * 周次
     */
    private int ctWeek;
    /**
     * 星期几
     */
    private int ctOneday;
    /**
     * 节次
     */
    private int ctPeriod;
    /**
     * 地点 机房名称
     */
    private String labName;
    /**
     * 任课教师姓名
     */
    private String userName;
}
