package com.web.teachingschedule.vo.dto;

import lombok.Data;

import java.util.List;

/**
 * 管理员根据 教师-课程 查询
 * 返回给管理员的信息
 */
@Data
public class BackACDTO {
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
     * 教学班号
     */
    private String classId;

}
