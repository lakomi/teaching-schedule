package com.web.teachingschedule.vo.dto;

import lombok.Data;

import java.util.List;

/**
 * 管理员排课视图返回数据信息
 * @author q
 */
@Data
public class RarrangeVo {
    /**
     * 排课编号
     */
    private String ctId;
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
     * 地点
     */
    private String labName;
    /**
     * 课程名称
     */
    private String courseName;
    /**
     * 教师姓名
     */
    private String userName;




}
