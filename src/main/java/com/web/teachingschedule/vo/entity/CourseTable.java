package com.web.teachingschedule.vo.entity;

import lombok.Data;


/**
 * 排课表
 * @author q
 */
@Data
public class CourseTable {
    /**
     * 排课编号（主键） 15位 自动生成
     */
    private String ctId;
    /**
     * 学期
     */
    private String termName;
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
     * 机房名称
     */
    private String labName;
    /**
     * 教师工号
     */
    private String userId;
    /**
     * 课程id
     */
    private String courseId;
    /**
     * 上机人数
     */
    private int ctPeople;
    /**
     * 教学班号
     */
    private String classId;
    /**
     * 实验项目编号
     */
    private String eId;
}
