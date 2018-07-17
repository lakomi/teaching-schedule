package com.web.teachingschedule.vo.dto;

import lombok.Data;

import java.util.List;

/**
 * 教师查询自己课表二维表  返回信息
 * @author q
 */
@Data
public class BackTeacherTableDTO {
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
     * 地点  可能多个机房
     */
    private List<String> labName;
    /**
     * 课程名称
     */
    private String courseName;
    /**
     * 教师姓名
     */
    private String userName;
}
