package com.web.teachingschedule.vo.dto;

import javafx.scene.chart.ValueAxis;
import lombok.Data;

import javax.validation.constraints.Max;
import javax.validation.constraints.Min;
import javax.validation.constraints.NotBlank;

/**
 * 排课需传的数据
 * @author q
 */
@Data
public class ArrcourseDTO {
    /**
     * 周次
     */
    @Min(value = 1,message = "请输入正确的周数")
    private int ctWeek;
    /**
     * 星期几
     */
    @Min(value = 1,message = "请输入正确的天数")
    @Max(value = 7,message = "请输入正确的天数")
    private int ctOneday;
    /**
     * 节次
     */
    @Min(value = 1,message = "请输入正确的节次")
    @Max(value = 5,message = "请输入正确的节次")
    private int ctPeriod;
    /**
     * 哪一个机房 地点
     */
    @NotBlank(message = "请输入机房")
    private String labName;
    /**
     * 学期
     */
    @NotBlank(message = "请输入学期")
    private String termName;
    /**
     * 教学班号
     */
    @NotBlank(message = "请输入教学班号")
    private String classId;
    /**
     * 课程号
     */
    @NotBlank(message = "请输入课程号")
    private String courseId;

}
