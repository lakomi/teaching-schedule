package com.web.teachingschedule.vo.dto;

import lombok.Data;


/**
 * 检查排课是否冲突
 * 查询数据库的时间信息
 * @author q
 */
@Data
public class TimeDTO {

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
     * 哪一个机房 地点
     */
    private String labName;
    /**
     * 学期
     */
    private String termName;


}
